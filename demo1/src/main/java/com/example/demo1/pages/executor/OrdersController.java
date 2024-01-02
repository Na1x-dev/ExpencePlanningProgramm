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
import com.example.demo1.models.Application;
import com.example.demo1.models.Order;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrdersController {

    @FXML
    public TableView<Order> ordersTable;
    @FXML
    public TableColumn<Order, String> orderId;
    @FXML
    public TableColumn<Order, String> orderDate;
    @FXML
    public TableColumn<Order, String> applicationId;
    @FXML
    public TableColumn<Order, String> procurementOrganization;
    @FXML
    public TableColumn<Order, String> unp;
    @FXML
    public TableColumn<Order, String> priceWithVat;
    @FXML
    public TableColumn<Order, String> status;
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
//        AppData.toNextStage("executor/OrdersPage.fxml", OrdersButton, "Executor Page");
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
        OrdersButton.setStyle("-fx-background-color: #fff; -fx-text-fill: #555");
        getOrders();
        initTableColumns();
        userNameLabel.setText(appData.getUser().getUserName());
        ordersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void getOrders() {
        HttpResponse<String> response = RequestsBuilder.getRequest("/admin/getAll/order");
        Type listType = new TypeToken<ArrayList<Order>>() {
        }.getType();
        List<Order> orders = appData.getGson().fromJson(response.body(), listType);
        for (Order order : orders) {
            ordersTable.getItems().add(order);
        }
    }

    public void initTableColumns() {
        orderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        orderDate.setCellValueFactory(cellData -> {
            Order order = cellData.getValue();
            return new SimpleStringProperty(appData.getDateForClient(order.getOrderDate()));
        });
        applicationId.setCellValueFactory(cellData -> {
            Order order = cellData.getValue();
            String applicationIdStr = String.valueOf(order.getApplication().getApplicationId());
            return new SimpleStringProperty(applicationIdStr);
        });
        procurementOrganization.setCellValueFactory(new PropertyValueFactory<>("procurementOrganization"));
        unp.setCellValueFactory(new PropertyValueFactory<>("unp"));
        priceWithVat.setCellValueFactory(new PropertyValueFactory<>("priceWithVat"));

        status.setCellValueFactory(cellData -> {
            Order order = cellData.getValue();
            String statusName = order.getStatus().getStatusName();
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
                    MenuItem menuItem4 = new MenuItem("Закрыть");
                    menuItem4.setOnAction(actionEvent -> {
                        appData.setPutModelId(getTableView().getItems().get(getIndex()).getOrderId());
                        DialogBox dialogBox = new DialogBox("Вы действительно хотите закрыть заказ с Id=" + appData.getPutModelId());
                        dialogBox.showAndWait();
                        if (dialogBox.getResult() == DialogBox.Result.OK) {
                            AppData.toNextStage("executor/OrderCommentPage.fxml", logOutButton, "Executor Page");
                        }
                    });
                    contextMenu.getItems().addAll(menuItem4);
                    setContextMenu(contextMenu);
                }
            }
        });
    }

}
