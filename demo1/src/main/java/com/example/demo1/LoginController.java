package com.example.demo1;

import java.net.URL;
import java.net.http.HttpClient;
import java.util.ResourceBundle;
import java.net.http.HttpResponse;

import com.example.demo1.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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
    }

    @FXML
    void login(ActionEvent event) {
        User user = new User(userNameField.getText(), passwordField.getText());
        HttpResponse<String> response = RequestsBuilder.sendRequest(appData.getGson().toJson(user), "/login");
        if (response == null) {
            resultLabel.setText("Сервер недоступен");
        } else {
            if (response.statusCode() == 200) {
                resultLabel.setText("Успешная авторизация");
            } else {
                resultLabel.setText("Неверные логин или пароль");

            }
        }
    }


}
