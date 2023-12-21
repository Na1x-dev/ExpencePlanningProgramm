package com.example.demo1.pages.admin;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import java.text.SimpleDateFormat;
import java.util.*;

import com.example.demo1.RequestsBuilder;
import com.example.demo1.models.Appeal;
import com.example.demo1.models.Model;
import com.example.demo1.models.Status;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import com.example.demo1.AppData;
import javafx.util.Duration;

public class AdminMainController {
    @FXML
    public AnchorPane upperAnchorPane;
    @FXML
    public TableView<Object> mainTable;
    @FXML
    public AnchorPane bottomAnchorPane;
    @FXML
    public HBox hbox;
    @FXML
    public Button logOutButton;
    @FXML
    private Button AppealsButton;
    @FXML
    private Button ApplicationsButton;
    @FXML
    private Button BudgetButton;
    @FXML
    private Button CategoriesButton;
    @FXML
    private Button DepartmentsButton;
    @FXML
    private Button ManagementsButton;
    @FXML
    private Button OrdersButton;
    @FXML
    private Button PositionsButton;
    @FXML
    private Button ProcurementArchiveButton;
    @FXML
    private Button UsersButton;

    Class modelClass;
    AppData appData = AppData.getInstance();

    @FXML
    void toAppeals(ActionEvent event) {
        appData.setAdminMode(0);
        AppData.toNextStage("admin/AdminPage.fxml", AppealsButton, "Admin Page");
    }

    @FXML
    void toApplications(ActionEvent event) {
        appData.setAdminMode(1);
        AppData.toNextStage("admin/AdminPage.fxml", ApplicationsButton, "Admin Page");
        System.out.println(Arrays.toString(appData.getModelsList().get(1).getDeclaredFields()));
    }

    @FXML
    void toBudget(ActionEvent event) {
        appData.setAdminMode(2);
        AppData.toNextStage("admin/AdminPage.fxml", BudgetButton, "Admin Page");
    }

    @FXML
    void toCategories(ActionEvent event) {
        appData.setAdminMode(3);
        AppData.toNextStage("admin/AdminPage.fxml", CategoriesButton, "Admin Page");
    }

    @FXML
    void toDepartments(ActionEvent event) {
        appData.setAdminMode(4);
        AppData.toNextStage("admin/AdminPage.fxml", DepartmentsButton, "Admin Page");
    }

    @FXML
    void toManagements(ActionEvent event) {
        appData.setAdminMode(5);
        AppData.toNextStage("admin/AdminPage.fxml", ManagementsButton, "Admin Page");
    }

    @FXML
    void toOrders(ActionEvent event) {
        appData.setAdminMode(6);
        AppData.toNextStage("admin/AdminPage.fxml", OrdersButton, "Admin Page");
    }

    @FXML
    void toPositions(ActionEvent event) {
        appData.setAdminMode(7);
        AppData.toNextStage("admin/AdminPage.fxml", PositionsButton, "Admin Page");
    }

    @FXML
    void toProcurementButton(ActionEvent event) {
        appData.setAdminMode(8);
        AppData.toNextStage("admin/AdminPage.fxml", ProcurementArchiveButton, "Admin Page");
    }

    @FXML
    void toUsers(ActionEvent event) {
        appData.setAdminMode(9);
        AppData.toNextStage("admin/AdminPage.fxml", UsersButton, "Admin Page");
    }


    @FXML
    void logOut(ActionEvent event) {
        AppData.toNextStage("login.fxml", logOutButton, "login");
    }


    @FXML
    void initialize() {
        modelClass = appData.getModelsList().get(appData.getAdminMode());
        mainTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        createTableColumns();
        changeButtonsColor();
        responseIntoTable();
    }

    public void createTableColumns() {
        for (Field field : modelClass.getDeclaredFields()) {
            TableColumn<Object, String> fieldColumn = new TableColumn<>();
            fieldColumn.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            Tooltip tooltip = new Tooltip(field.getName());
            tooltip.setShowDelay(Duration.millis(100));
            fieldColumn.setGraphic(new Label(field.getName()));
            Tooltip.install(fieldColumn.getGraphic(), tooltip);
            mainTable.getColumns().add(fieldColumn);
        }
    }

    public void responseIntoTable() {
        HttpResponse<String> response = RequestsBuilder.getRequest("/admin/getAll/" + modelClass.getSimpleName().toLowerCase());
        Type listType = new TypeToken<ArrayList<LinkedTreeMap>>() {
        }.getType();
//        List<LinkedTreeMap> responseList = appData.getGson().fromJson(response.body(), listType);
//        for (LinkedTreeMap model : responseList) {
//            mainTable.getItems().add(object);
//            JsonObject mapModel = appData.getGson().toJsonTree(model).getAsJsonObject();
//            Model newModel = (Model) appData.getGson().fromJson(mapModel, modelClass);
//            System.out.println(newModel);
//        }
    }

    public void changeButtonsColor() {
        ObservableList<Node> headerButtons = hbox.getChildren();
        for (Node button : headerButtons) {
            button.setStyle("-fx-background-color: #5082ff; -fx-text-fill: #fff;");
            button.setOnMouseEntered(mouseEvent -> button.setStyle("-fx-background-color: rgb(72, 92, 255); -fx-text-fill: #fff;"));
            button.setOnMouseExited(mouseEvent -> button.setStyle("-fx-background-color: #5082ff; -fx-text-fill: #fff;"));

        }
        headerButtons.get(appData.getAdminMode()).setStyle("-fx-text-fill: #777; -fx-background-color: #fff");
        headerButtons.get(appData.getAdminMode()).setOnMouseEntered(mouseEvent -> headerButtons.get(appData.getAdminMode()).setStyle("-fx-background-color: #ddd;-fx-text-fill: #777;"));
        headerButtons.get(appData.getAdminMode()).setOnMouseExited(mouseEvent -> headerButtons.get(appData.getAdminMode()).setStyle("-fx-background-color: #fff;-fx-text-fill: #777;"));
    }

    //        System.out.println(appData.getModelsList().get(0));
//        initTableColumns();
//        createAppealButton.setMinWidth(hbox.getPrefWidth() * 2);
//        logOutButton.setMinWidth(hbox.getPrefWidth());
//        getUserAppeals();

//    public void getUserAppeals() {
//        HttpResponse<String> response = RequestsBuilder.getRequestWithProperty("/appeals/getByUser", appData.getUser().getUserName());
//        Type listType = new TypeToken<ArrayList<Appeal>>() {
//        }.getType();
//        List<Appeal> appeals = appData.getGson().fromJson(response.body(), listType);
//        for (Appeal appeal : appeals) {
//            customerTable.getItems().add(appeal);
//        }
//    }

//    public void initTableColumns(){
//        appealId.setCellValueFactory(new PropertyValueFactory<>("appealId"));
//        closeDate.setCellValueFactory(new PropertyValueFactory<>("closingDate"));
//        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
//        registrationDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
//        registrationDate.setCellValueFactory(cellData -> {
//            Appeal appeal = cellData.getValue();
//            return new SimpleStringProperty(appData.getDateForClient(appeal.getRegistrationDate()));
//        });
//        status.setCellValueFactory(cellData -> {
//            Appeal appeal = cellData.getValue();
//            String statusName = appeal.getStatus().getStatusName();
//            return new SimpleStringProperty(statusName);
//        });
//        text.setCellValueFactory(new PropertyValueFactory<>("appealText"));
//
//    }

}


//        buttonColumn.setCellFactory(column -> new TableCell<>() {
//            private final Button button = new Button("Click me");
//
//            {
//                button.setOnAction(event -> {
//                    String rowData = getItem();
//                    System.out.println("Button clicked for: " + rowData);
//                });
//            }
//
//            @Override
//            protected void updateItem(String item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty) {
//                    setGraphic(null);
//                } else {
//                    setGraphic(button);
//                }
//            }
//        });
//        customerTable.getColumns().add(buttonColumn);