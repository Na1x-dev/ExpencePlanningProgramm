package com.example.demo1.pages;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.*;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.List;

import com.example.demo1.AppData;
import com.example.demo1.RequestsBuilder;
import com.example.demo1.models.Role;
import com.example.demo1.models.Status;
import com.example.demo1.models.User;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    public GridPane gridPane1;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label resultLabel;

    @FXML
    private TextField userNameField;

    @FXML
    private VBox vbox;

    AppData appData;

    @FXML
    void initialize() {
        appData = AppData.getInstance();
        userNameField.setText("admin");
        passwordField.setText("admin");
    }

    @FXML
    void login(ActionEvent event) {
        appData.setUser(new User(userNameField.getText(), passwordField.getText()));
        HttpResponse<String> response = RequestsBuilder.postRequest(appData.getGson().toJson(appData.getUser()), "/login");
        if (!appData.checkFields(appData.getFields(gridPane1), resultLabel)) {
        } else {
            if (response == null) {
                serverOffInfo(appData.getFields(gridPane1), resultLabel);
            } else {
                if (response.statusCode() == 200) {
                    resultLabel.setText("Успешная авторизация");
                    getUserFromServer(appData.getUser());
                    getRolesFromServer();
                    getStatusesFromServer();
                if (appData.getUser().getRole().getRoleName().equals("заказчик"))
                    AppData.toNextStage("customer/CustomerPage.fxml", loginButton, "Customer Page");
                else if (appData.getUser().getRole().getRoleName().equals("исполнитель"))
                    AppData.toNextStage("executor/AppealsPage.fxml", loginButton, "Executor Page");
                    else if(appData.getUser().getRole().getRoleName().equals("администратор"))
                    AppData.toNextStage("admin/AdminPage.fxml", loginButton, "Admin Page");
                } else {
                    resultLabel.setText("Неверные логин или пароль");
                    userNameField.setStyle("-fx-border-color: rgb(222,27,63)");
                    passwordField.setStyle("-fx-border-color: rgb(222,27,63)");
                }
            }
        }
    }

    void serverOffInfo(List<TextField> fields, Label resultLabel) {
        for (TextField field : fields) {
            field.setStyle("-fx-border-color: #ffc250");
            resultLabel.setText("Сервер недоступен");
        }
    }


    private void getUserFromServer(User user) {
        HttpResponse<String> response = RequestsBuilder.getRequestWithProperty("/login/user", user.getUserName());
        appData.setUser(appData.getGson().fromJson(response.body(), User.class));
        appData.getUser().setUserName(userNameField.getText());
    }

    private void getStatusesFromServer() {
        HttpResponse<String> response = RequestsBuilder.getRequest("/login/statuses");
        Type listType = new TypeToken<ArrayList<Status>>() {
        }.getType();
        appData.setStatuses(appData.getGson().fromJson(response.body(), listType));
    }

    private void getRolesFromServer() {
        HttpResponse<String> response = RequestsBuilder.getRequest("/login/roles");
        Type listType = new TypeToken<ArrayList<Role>>() {
        }.getType();
        appData.setRoles(appData.getGson().fromJson(response.body(), listType));
    }

}
