package com.example.demo1.pages.admin;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import com.example.demo1.AppData;
import com.example.demo1.RequestsBuilder;
import com.example.demo1.models.Appeal;
import com.example.demo1.models.User;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AdminFieldsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button CreateButton;
    @FXML
    private Button BackButton;

    @FXML
    private GridPane gridPane1;

    @FXML
    private Label resultLabel;

    @FXML
    private VBox vbox;
    @FXML
    private HBox ButtonBox;

    AppData appData = AppData.getInstance();
    Class modelClass;

    @FXML
    void create(ActionEvent event) {
        AppData.toNextStage("admin/AdminPage.fxml", CreateButton, "Admin Page");
        createModelObject();

    }

    @FXML
    void back(ActionEvent event) {
        AppData.toNextStage("admin/AdminPage.fxml", BackButton, "Admin Page");
    }

    @FXML
    void initialize() {
        modelClass = appData.getModelsList().get(appData.getAdminMode());
        createMainLabel();
        vbox.getChildren().remove(ButtonBox);
        createFields();
        createComboBoxes();
        vbox.getChildren().add(ButtonBox);
    }

    void createMainLabel(){
        Label mainLabel = new Label("New " + modelClass.getSimpleName());
        mainLabel.setPadding(new Insets(0, 0, 50, 0));
        vbox.getChildren().add(mainLabel);
    }

    void createFields() {
        for (Field field : modelClass.getDeclaredFields()) {
            if (field.getType().equals(String.class)) {
                createLabel(field);
                TextField textField = new TextField();
                textField.setPromptText(field.getName());
                textField.setId(field.getName());
                textField.setPrefHeight(40);
                vbox.getChildren().add(textField);
            }
        }
    }

    void createComboBoxes(){for (Field field : modelClass.getDeclaredFields()) {
        if (appData.getFullModelsList().contains(field.getType())) {
            createLabel(field);
            HttpResponse<String> response = RequestsBuilder.getRequest("/admin/getAll/" + field.getType().getSimpleName().toLowerCase());
            Type listType = new TypeToken<ArrayList<JsonObject>>() {
            }.getType();
            ArrayList<JsonObject> jsonObjectList = appData.getGson().fromJson(response.body(), listType);
            ComboBox<String> comboBox = new ComboBox<>();
            for (JsonObject jObject : jsonObjectList) {
                String comboItem = jObject.get(field.getType().getDeclaredFields()[0].getName()) + ". " + jObject.get(field.getType().getDeclaredFields()[1].getName());
                comboBox.getItems().add(comboItem);
            }
            comboBox.setPrefWidth(Double.MAX_VALUE);
            vbox.getChildren().add(comboBox);
        }
    }}

    void createLabel(Field field){
        Label label = new Label(field.getName());
        vbox.getChildren().add(label);
    }

    void createModelObject() {
        Set<Node> nodes = vbox.lookupAll(".text-field");
        JsonObject jsonObject = new JsonObject();
        for (Node field : nodes) {
            jsonObject.addProperty((field).getId(), ((TextField) field).getText());
        }
        HttpResponse<String> response = RequestsBuilder.postRequest(String.valueOf(jsonObject), "/admin/create/" + modelClass.getSimpleName().toLowerCase());
    }


}
