package com.example.demo1.pages.executor;

import java.lang.reflect.Type;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.demo1.AppData;
import com.example.demo1.DialogBox;
import com.example.demo1.RequestsBuilder;
import com.example.demo1.models.Appeal;
import com.example.demo1.models.Application;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ApplicationsController {

    @FXML
    public TableView<Application> applicationsTable;
    @FXML
    public TableColumn<Application, Long> applicationId;
    @FXML
    public TableColumn<Application, String> applicationDate;
    @FXML
    public TableColumn<Application, String> appealId;
    @FXML
    public TableColumn<Application, String> amount;
    @FXML
    public TableColumn<Application, String> productName;
    @FXML
    public TableColumn<Application, String> finalPrice;
    @FXML
    public TableColumn<Application, String> status;
    @FXML
    public TableColumn<Application, String> closingDate;
    @FXML
    public TableColumn<Application, String> comment;
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
//        AppData.toNextStage("executor/ApplicationsPage.fxml", ApplicationsButton, "Executor Page");
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
        AppData.toNextStage("executor/ProcurementArchivePage.fxml", ProcurementArchiveButton, "Executor Page");
    }

    @FXML
    void logOut(ActionEvent event) {
        AppData.toNextStage("login.fxml", logOutButton, "login");
    }

    @FXML
    void initialize() {
        ApplicationsButton.setStyle("-fx-background-color: #fff; -fx-text-fill: #555");
        userNameLabel.setText(appData.getUser().getUserName());
        getApplications();
        initTableColumns();
        applicationsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void getApplications() {
        HttpResponse<String> response = RequestsBuilder.getRequest("/admin/getAll/application");
        Type listType = new TypeToken<ArrayList<Application>>() {
        }.getType();
        List<Application> Applications = appData.getGson().fromJson(response.body(), listType);
        for (Application Application : Applications) {
            applicationsTable.getItems().add(Application);
        }
    }

    public void initTableColumns() {
        applicationId.setCellValueFactory(new PropertyValueFactory<>("applicationId"));
        applicationDate.setCellValueFactory(cellData -> {
            Application application = cellData.getValue();
            return new SimpleStringProperty(appData.getDateForClient(application.getApplicationDate()));
        });
        appealId.setCellValueFactory(cellData -> {
            Application Application = cellData.getValue();
            String appealIdStr = String.valueOf(Application.getAppeal().getAppealId());
            return new SimpleStringProperty(appealIdStr);
        });
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        finalPrice.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));
        status.setCellValueFactory(cellData -> {
            Application Application = cellData.getValue();
            String statusName = Application.getStatus().getStatusName();

            return new SimpleStringProperty(statusName);
        });
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
                    MenuItem menuItem1 = new MenuItem("Закупить");
                    menuItem1.setOnAction(actionEvent -> {
                        appData.setPutModelId(getTableView().getItems().get(getIndex()).getApplicationId());
                        AppData.toNextStage("executor/NewOrderPage.fxml", logOutButton, "Executor Page");
                    });
                    MenuItem menuItem2 = new MenuItem("Закрыть");
                    menuItem2.setOnAction(actionEvent -> {
                        appData.setPutModelId(getTableView().getItems().get(getIndex()).getApplicationId());
                        DialogBox dialogBox = new DialogBox("Вы действительно хотите закрыть заявку с Id=" + appData.getPutModelId());
                        dialogBox.showAndWait();
                        if (dialogBox.getResult() == DialogBox.Result.OK) {
                            AppData.toNextStage("executor/ApplicationCommentPage.fxml", logOutButton, "Executor Page");
                        }
                    });
                    contextMenu.getItems().addAll(menuItem1, menuItem2);
                    setContextMenu(contextMenu);
                }
            }
        });


        closingDate.setCellValueFactory(cellData -> {
            Application application = cellData.getValue();
            if (application.getClosingDate() == null) {
                return new SimpleStringProperty("");
            }
            return new SimpleStringProperty(appData.getDateForClient(application.getClosingDate()));
        });
        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
    }


}
