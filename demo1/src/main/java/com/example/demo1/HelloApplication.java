package com.example.demo1;

import com.example.demo1.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.google.gson.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.io.IOException;

public class HelloApplication extends Application {

    public static final String SERVER_URL = "http://localhost:8080/api/login";
    private Stage primaryStage;
    private StackPane loginPage;
    private StackPane nextPage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Авторизация");
        this.primaryStage = primaryStage;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Label usernameLabel = new Label("Логин:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Пароль:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Войти");
        Label resultLabel = new Label();

        loginPage = new StackPane();
        loginPage.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton, resultLabel);
        loginPage.setVisible(false);

        Label textNextPage = new Label("nextPage");
        nextPage = new StackPane();
        nextPage.getChildren().addAll(textNextPage);
        nextPage.setVisible(false);

        loginButton.setOnAction(event -> {
            User user = new User();
            user.setUserName(usernameField.getText());
            user.setPassword(passwordField.getText());

            // Создание HTTP-клиента
            HttpClient client = HttpClient.newHttpClient();

            String requestBody = gson.toJson(user);


            // Отправка запроса на сервер
            try {
                HttpResponse<String> response = client.send(RequestsBuilder.postRequest(requestBody), HttpResponse.BodyHandlers.ofString());
                // Обработка ответа сервера
                if (response.statusCode() == 200) {
                    resultLabel.setText("Успешная авторизация");
                    openNextPage();
                } else {
                    resultLabel.setText("Неверные логин или пароль");
                }
            } catch (Exception e) {
                resultLabel.setText("Сервер недоступен");
            }
        });

        VBox vbox = new VBox(loginPage, usernameLabel, usernameField, passwordLabel, passwordField, loginButton, resultLabel, nextPage, textNextPage);


        vbox.setSpacing(10);
        Scene scene = new Scene(vbox, 700, 500);
        vbox.setPadding(new Insets(0.25 * vbox.getHeight()));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openNextPage() {
        loginPage.setVisible(false);
        nextPage.setVisible(true);
    }

}