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
    }
}