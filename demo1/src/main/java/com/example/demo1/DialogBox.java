package com.example.demo1;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;

public class DialogBox extends Stage {
    public enum Result {
        OK,
        CANCEL
    }

    private Button okButton, cancelButton;
    private Label messageLabel;
    @Getter
    private Result result;

    public DialogBox(String message) {
        // Установка заголовка окна
        setTitle("Dialog Box");

        // Создание метки с сообщением
        messageLabel = new Label(message);

        // Создание кнопок "OK" и "Cancel"
        okButton = new Button("Да");
        okButton.setPrefHeight(40);
        okButton.setPrefWidth(100);
        cancelButton = new Button("Нет");
        cancelButton.setPrefHeight(40);
        cancelButton.setPrefWidth(100);
        // Установка обработчиков событий для кнопок
        okButton.setOnAction(e -> {
            // Действия при нажатии на кнопку "OK"
            result = Result.OK;
            close();
        });
        cancelButton.setOnAction(e -> {
            // Действия при нажатии на кнопку "Cancel"
            result = Result.CANCEL;
            close();
        });

        // Создание панели с кнопками
        HBox buttonPane = new HBox(20);
        buttonPane.setPrefHeight(40);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.getChildren().addAll(okButton, cancelButton);

        // Создание корневой панели и добавление на нее метки и панели с кнопками
        VBox rootPane = new VBox(40);
        rootPane.setAlignment(Pos.CENTER);
        rootPane.getChildren().addAll(messageLabel, buttonPane);

        String css = getClass().getResource("style.css").toExternalForm();
        Scene scene = new Scene(rootPane, 600, 200);
        scene.getStylesheets().add(css);
        setScene(scene);
    }

}