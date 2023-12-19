package com.example.demo1.pages.admin;

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
    public TableView mainTable;
    @FXML
    public AnchorPane bottomAnchorPane;
    @FXML
    public HBox hbox;


    AppData appData = AppData.getInstance();

//    @FXML
//    void logOut(ActionEvent event) {
//        AppData.toNextStage("login.fxml", logOutButton, "login");
//    }


    @FXML
    void initialize() {
//        initTableColumns();
//        createAppealButton.setMinWidth(hbox.getPrefWidth() * 2);
//        logOutButton.setMinWidth(hbox.getPrefWidth());
//        getUserAppeals();
    }

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