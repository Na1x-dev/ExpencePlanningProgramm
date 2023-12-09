package com.example.demo1;

import java.io.IOException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ResourceBundle;
import java.net.http.HttpResponse;

import com.example.demo1.models.User;
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
        HttpRequest request = RequestsBuilder.postRequest(appData.getGson().toJson(appData.getUser()), "/login");
        HttpResponse<String> response = RequestsBuilder.sendRequest(request);
        if (response == null) {
            resultLabel.setText("Сервер недоступен");
        } else {
            if (response.statusCode() == 200) {
                resultLabel.setText("Успешная авторизация");
                getUserFromServer(appData.getUser());
                AppData.toNextStage("nextPage.fxml", loginButton, "nextPage");
            } else {
                resultLabel.setText("Неверные логин или пароль");
            }
        }
    }

    private void getUserFromServer(User user) {
        HttpRequest request = RequestsBuilder.getRequestWithProperty("/login/user", user.getUserName());
        HttpResponse<String> response = RequestsBuilder.sendRequest(request);
        appData.setUser(appData.getGson().fromJson(response.body(), User.class));

        System.out.println(appData.getUser());
    }

}
