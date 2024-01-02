
package com.example.demo1.pages.executor;

import java.lang.reflect.Type;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.demo1.AppData;
import com.example.demo1.CreateSpec;
import com.example.demo1.DialogBox;
import com.example.demo1.RequestsBuilder;
import com.example.demo1.models.Order;
import com.example.demo1.models.ProcurementArchive;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProcurementArchiveController {
    @FXML
    public TableView<ProcurementArchive> archiveTable;
    @FXML
    public TableColumn<ProcurementArchive, String> orderId;
    @FXML
    public TableColumn<ProcurementArchive, String> orderDate;
    @FXML
    public TableColumn<ProcurementArchive, String> applicationId;
    @FXML
    public TableColumn<ProcurementArchive, String> procurementOrganization;
    @FXML
    public TableColumn<ProcurementArchive, String> unp;
    @FXML
    public TableColumn<ProcurementArchive, String> priceWithVat;
    @FXML
    public TableColumn<ProcurementArchive, String> status;
    @FXML
    public TableColumn<ProcurementArchive, String> user;
    @FXML
    public TableColumn<ProcurementArchive, String> procurementDate;
    @FXML
    public TableColumn<ProcurementArchive, String> comment;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Button AppealsButton;

    @FXML
    private Button ApplicationsButton;

    @FXML
    private Button OrdersButton;

    @FXML
    private Button ProcurementArchiveButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Button manualButton;
    @FXML
    private Label userNameLabel;
    AppData appData = AppData.getInstance();

    @FXML
    void toAppealsTable(ActionEvent event) {
        AppData.toNextStage("executor/AppealsPage.fxml", AppealsButton, "Executor Page");
    }

    @FXML
    void toApplicationsTable(ActionEvent event) {
        AppData.toNextStage("executor/ApplicationsPage.fxml", ApplicationsButton, "Executor Page");
    }

    @FXML
    void toManual(ActionEvent event) {
        AppData.toNextStage("executor/ManualPage.fxml", manualButton, "Executor Page");
    }

    @FXML
    void toOrdersTable(ActionEvent event) {
        AppData.toNextStage("executor/OrdersPage.fxml", OrdersButton, "Executor Page");
    }

    @FXML
    void toProcurementArchiveTable(ActionEvent event) {
//        AppData.toNextStage("executor/ProcurementArchivePage.fxml", ProcurementArchiveButton, "Executor Page");
    }

    @FXML
    void logOut(ActionEvent event) {
        AppData.toNextStage("login.fxml", logOutButton, "login");
    }

    @FXML
    void initialize() {
        ProcurementArchiveButton.setStyle("-fx-background-color: #fff; -fx-text-fill: #555");
        userNameLabel.setText(appData.getUser().getUserName());
        getArchive();
        initTableColumns();
        archiveTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }


    public void getArchive() {
        HttpResponse<String> response = RequestsBuilder.getRequest("/admin/getAll/procurementarchive");
        Type listType = new TypeToken<ArrayList<ProcurementArchive>>() {
        }.getType();
        List<ProcurementArchive> archive = appData.getGson().fromJson(response.body(), listType);
        for (ProcurementArchive part : archive) {
            archiveTable.getItems().add(part);
        }
    }

    public void initTableColumns() {
        orderId.setCellValueFactory(cellData -> {
            ProcurementArchive archive = cellData.getValue();
            return new SimpleStringProperty(archive.getOrder().getOrderId().toString());
        });
        orderDate.setCellValueFactory(cellData -> {
            ProcurementArchive archive = cellData.getValue();
            return new SimpleStringProperty(appData.getDateForClient(archive.getOrder().getOrderDate()));
        });
        applicationId.setCellValueFactory(cellData -> {
            ProcurementArchive archive = cellData.getValue();
            String applicationIdStr = String.valueOf(archive.getOrder().getApplication().getApplicationId());
            return new SimpleStringProperty(applicationIdStr);
        });
        procurementOrganization.setCellValueFactory(cellData -> {
            ProcurementArchive archive = cellData.getValue();
            return new SimpleStringProperty(archive.getOrder().getProcurementOrganization());
        });
        unp.setCellValueFactory(cellData -> {
            ProcurementArchive archive = cellData.getValue();
            return new SimpleStringProperty(archive.getOrder().getUnp().toString());
        });

        priceWithVat.setCellValueFactory(cellData -> {
            ProcurementArchive archive = cellData.getValue();
            return new SimpleStringProperty(archive.getOrder().getPriceWithVat().toString());
        });

        status.setCellValueFactory(cellData -> {
            ProcurementArchive archive = cellData.getValue();
            String statusName = archive.getStatus().getStatusName();
            return new SimpleStringProperty(statusName);
        });

        user.setCellValueFactory(cellData -> {
            ProcurementArchive archive = cellData.getValue();
            return new SimpleStringProperty(archive.getUser().getUserName());
        });

        procurementDate.setCellValueFactory(cellData -> {
            ProcurementArchive archive = cellData.getValue();
            return new SimpleStringProperty(appData.getDateForClient(archive.getProcurementDate()));
        });

        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));

        status.setCellFactory(column -> new TableCell<>() {


            @Override
            public void updateItem(String item, boolean empty) {

                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item);
                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem menuItem4 = new MenuItem("Выгрузить спецификацию");
                    menuItem4.setOnAction(actionEvent -> {
                        ProcurementArchive procurementArchive = getTableView().getItems().get(getIndex());
                        CreateSpec.createSpec(procurementArchive);
                    });
                    contextMenu.getItems().addAll(menuItem4);
                    setContextMenu(contextMenu);
                }
            }
        });
    }


}
