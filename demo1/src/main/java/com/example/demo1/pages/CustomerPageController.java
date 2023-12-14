package com.example.demo1.pages;

import java.lang.reflect.Type;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.demo1.RequestsBuilder;
import com.example.demo1.models.Appeal;
import com.example.demo1.models.Status;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableView<?> customerTable;

    @FXML
    private HBox hbox;

    @FXML
    private Button logOutButton;
    @FXML
    private TableColumn<?, ?> appealId;

    @FXML
    private TableColumn<?, ?> closeDate;

    @FXML
    private TableColumn<?, ?> comment;

    @FXML
    private TableColumn<?, ?> registrationDate;

    @FXML
    private TableColumn<?, ?> status;

    @FXML
    private TableColumn<?, ?> text;


    AppData appData = AppData.getInstance();

    @FXML
    void logOut(ActionEvent event) {
        AppData.toNextStage("login.fxml", logOutButton, "login");
    }

    @FXML
    void toCreateAppeal(ActionEvent event) {
        AppData.toNextStage("createAppealPage.fxml", createAppealButton, "Create Appeal");
    }


    @FXML
    void initialize() {
        createAppealButton.setMinWidth(hbox.getPrefWidth() * 2);
        logOutButton.setMinWidth(hbox.getPrefWidth());
        getUserAppeals();
    }

    public void getUserAppeals() {
        HttpResponse<String> response = RequestsBuilder.getRequestWithProperty("/appeals/getByUser", appData.getUser().getUserName());
        Type listType = new TypeToken<ArrayList<Appeal>>() {
        }.getType();
        List<Appeal> appeals = appData.getGson().fromJson(response.body(), listType);
        System.out.println(appeals);


        for (Appeal appeal : appeals) {


// Добавьте новую строку в TableView
            customerTable.getItems().add(appeal);

        }
    }

}
