package com.example.demo1.pages.customer;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import com.example.demo1.AppData;

public class CustomerPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button createAppealButton;

    @FXML
    private TableView<Appeal> customerTable;

    @FXML
    private HBox hbox;

    @FXML
    private Button logOutButton;
    @FXML
    private TableColumn<Appeal, Long> appealId;

    @FXML
    private TableColumn<Appeal, String> closeDate;

    @FXML
    private TableColumn<Appeal, String> comment;

    @FXML
    private TableColumn<Appeal, String> registrationDate;

    @FXML
    private TableColumn<Appeal, String> status;

    @FXML
    private TableColumn<Appeal, String> text;
    @FXML
    private Label userNameLabel;


    AppData appData = AppData.getInstance();

    @FXML
    void logOut(ActionEvent event) {
        AppData.toNextStage("login.fxml", logOutButton, "login");
    }

    @FXML
    void toCreateAppeal(ActionEvent event) {
        AppData.toNextStage("customer/CreateAppealPage.fxml", createAppealButton, "Create Appeal");
    }


    @FXML
    void initialize() {
        initTableColumns();
        createAppealButton.setMinWidth(hbox.getPrefWidth() * 2);
        logOutButton.setMinWidth(hbox.getPrefWidth());
        getUserAppeals();
        customerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        userNameLabel.setText(appData.getUser().getUserName());
    }

    public void getUserAppeals() {
        HttpResponse<String> response = RequestsBuilder.getRequestWithProperty("/appeals/getByUser", appData.getUser().getUserName());
        Type listType = new TypeToken<ArrayList<Appeal>>() {
        }.getType();
        List<Appeal> appeals = appData.getGson().fromJson(response.body(), listType);
        for (Appeal appeal : appeals) {
            customerTable.getItems().add(appeal);
        }
    }

    public void initTableColumns() {
        appealId.setCellValueFactory(new PropertyValueFactory<>("appealId"));
        closeDate.setCellValueFactory(new PropertyValueFactory<>("closingDate"));
        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        registrationDate.setCellValueFactory(cellData -> {
            Appeal appeal = cellData.getValue();
            return new SimpleStringProperty(appData.getDateForClient(appeal.getRegistrationDate()));
        });
        status.setCellValueFactory(cellData -> {
            Appeal appeal = cellData.getValue();
            String statusName = appeal.getStatus().getStatusName();
            return new SimpleStringProperty(statusName);
        });
        text.setCellValueFactory(new PropertyValueFactory<>("appealText"));
    }

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