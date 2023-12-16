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
        String css = getClass().getResource("style.css").toExternalForm();
        Scene scene = new Scene(root, 700, 600);
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
//        primaryStage.setMaximized(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}