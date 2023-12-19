package com.example.demo1.pages.admin;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.example.demo1.RequestsBuilder;
import com.example.demo1.models.Appeal;
import com.example.demo1.models.Status;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import com.example.demo1.AppData;

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
    @FXML
    private Button logOutButton10;

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
    }

    public void createTableColumns(){
        for(Field field : modelClass.getDeclaredFields()){
            TableColumn<Object, String> fieldColumn = new TableColumn<>(field.getName());
            fieldColumn.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            mainTable.getColumns().add(fieldColumn);
        }
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