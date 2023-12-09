package com.example.demo1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        AppData appData = AppData.getInstance();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("login");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);

//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Авторизация");

//
//        Label usernameLabel = new Label("Логин:");
//        TextField usernameField = new TextField();
//        Label passwordLabel = new Label("Пароль:");
//        PasswordField passwordField = new PasswordField();
//        Button loginButton = new Button("Войти");
//        Label resultLabel = new Label();
//
//        loginButton.setOnAction(event -> {
//            User user = new User();
//            user.setUserName(usernameField.getText());
//            user.setPassword(passwordField.getText());
//
//            // Создание HTTP-клиента
//
//
//            String requestBody = gson.toJson(user);
//
//
//            // Отправка запроса на сервер
//            try {
//                HttpResponse<String> response = client.send(RequestsBuilder.postRequest(requestBody, "/login"), HttpResponse.BodyHandlers.ofString());
//                // Обработка ответа сервера
//                if (response.statusCode() == 200) {
//                    resultLabel.setText("Успешная авторизация");
//
//                } else {
//                    resultLabel.setText("Неверные логин или пароль");
//                }
//            } catch (Exception e) {
//                resultLabel.setText("Сервер недоступен");
//            }
//        });
//
//        VBox vbox = new VBox(loginPage, usernameLabel, usernameField, passwordLabel, passwordField, loginButton, resultLabel);
//
//
//        vbox.setSpacing(10);
//        Scene scene = new Scene(vbox, 700, 500);
//        vbox.setPadding(new Insets(0.25 * vbox.getHeight()));
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

    }
}