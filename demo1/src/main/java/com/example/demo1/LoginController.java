package com.example.demo1;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.List;

import com.example.demo1.models.Role;
import com.example.demo1.models.Status;
import com.example.demo1.models.User;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {

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
        if (response == null) {
            resultLabel.setText("Сервер недоступен");
        } else {
            if (response.statusCode() == 200) {
                resultLabel.setText("Успешная авторизация");
                getUserFromServer(appData.getUser());
                getRolesFromServer();
                getStatusesFromServer();
                AppData.toNextStage("nextPage.fxml", loginButton, "nextPage");
            } else {
                resultLabel.setText("Неверные логин или пароль");
            }
        }
    }

    private void getUserFromServer(User user) {
        HttpResponse<String> response = RequestsBuilder.getRequestWithProperty("/login/user", user.getUserName());
        appData.setUser(appData.getGson().fromJson(response.body(), User.class));
    }

    private void getStatusesFromServer() {
        HttpResponse<String> response = RequestsBuilder.getRequest("/login/statuses");
        Type listType = new TypeToken<ArrayList<Status>>(){}.getType();
        appData.setStatuses(appData.getGson().fromJson(response.body(), listType));
    }

    private void getRolesFromServer() {
        HttpResponse<String> response = RequestsBuilder.getRequest("/login/roles");
        Type listType = new TypeToken<ArrayList<Role>>(){}.getType();
        appData.setRoles(appData.getGson().fromJson(response.body(), listType));
    }

}
