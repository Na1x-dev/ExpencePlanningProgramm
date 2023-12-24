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

    void createMainLabel() {
        Label mainLabel = new Label(getRussianMainLabel(modelClass.getSimpleName()));
        mainLabel.setPadding(new Insets(0, 0, 50, 0));
        vbox.getChildren().add(mainLabel);
    }

    void createFields() {
        for (Field field : modelClass.getDeclaredFields()) {
            if (field.getType().equals(String.class) || field.getType().equals(Double.class)) {
                createLabel(field);
                TextField textField = new TextField();
                textField.setPromptText(getRussianField(field.getName()));
                textField.setId(field.getName());
                textField.setPrefHeight(40);
                vbox.getChildren().add(textField);
            }
        }
    }

    void createComboBoxes() {
        for (Field field : modelClass.getDeclaredFields()) {
            if (appData.getFullModelsList().contains(field.getType())) {
                createLabel(field);
                HttpResponse<String> response = RequestsBuilder.getRequest("/admin/getAll/" + field.getType().getSimpleName().toLowerCase());
                Type listType = new TypeToken<ArrayList<JsonObject>>() {
                }.getType();
                ArrayList<JsonObject> jsonObjectList = appData.getGson().fromJson(response.body(), listType);
                ComboBox<String> comboBox = new ComboBox<>();
                comboBox.setId(field.getName());
                for (JsonObject jObject : jsonObjectList) {
                    String comboItem = jObject.get(field.getType().getDeclaredFields()[0].getName()) + ". " + jObject.get(field.getType().getDeclaredFields()[1].getName());
                    comboBox.getItems().add(comboItem);
                }
                comboBox.setPrefWidth(Double.MAX_VALUE);
                vbox.getChildren().add(comboBox);
            }
        }
    }

    void createLabel(Field field) {
        Label label = new Label(getRussianField(field.getName()));
        vbox.getChildren().add(label);
    }

    void createModelObject() {
        Set<Node> textFieldNodes = vbox.lookupAll(".text-field");
        JsonObject jsonObject = new JsonObject();
        for (Node field : textFieldNodes) {
            jsonObject.addProperty((field).getId(), ((TextField) field).getText());
        }
        Set<Node> comboBoxNodes = vbox.lookupAll(".combo-box");
//        for (Node combo : comboBoxNodes) {
//            HttpResponse<String> response = RequestsBuilder.getRequest("/admin/get/" + (combo).getId() + "/" + ((ComboBox<?>) combo).getValue().toString().charAt(0));
//            JsonObject newJson = new JsonObject();

//            jsonObject.addProperty((combo).getId(), newJson);
//            System.out.println(jsonObject);
//        }

        HttpResponse<String> response = RequestsBuilder.postRequest(String.valueOf(jsonObject), "/admin/create/" + modelClass.getSimpleName().toLowerCase());
        System.out.println(response + "   " + response.body());
    }

    String getRussianField(String fieldName) {
        Map<String, String> fields = new HashMap<>();
        fields.put("registrationDate", "Дата регистрации");
        fields.put("appealText", "Текст обращения");
        fields.put("closingDate", "Дата закрытия");
        fields.put("comment", "Комментарий");
        fields.put("status", "Статус");
        fields.put("user", "Пользователь");
        fields.put("closingUser", "Закрывший пользователь");
        fields.put("productName", "Наименование товара");
        fields.put("productCharacteristic", "Характеристика товара");
        fields.put("applicationComment", "Комментарий");
        fields.put("createUser", "Пользователь-создатель");
        fields.put("customerUser", "Пользователь-заказчик");
        fields.put("appeal", "Обращение");
        fields.put("category", "Категория");
        fields.put("management", "Управление");
        fields.put("categoryName", "Название категории");
        fields.put("departmentName", "Название отдел");
        fields.put("managementName", "Название управления");
        fields.put("procurementOrganization", "Поставщик");
        fields.put("contactPerson", "Контактное лицо");
        fields.put("contactNumber", "Контактный номер");
        fields.put("application", "Заявка");
        fields.put("positionName", "Название должности");
        fields.put("order", "Заказ");
        fields.put("lastName", "Фамилия");
        fields.put("firstName", "Имя");
        fields.put("patronymic", "Отчество");
        fields.put("userName", "Логин");
        fields.put("password", "Пароль");
        fields.put("position", "Должность");
        fields.put("role", "Роль");
        fields.put("department", "Отдел");
        fields.put("budgetCategory1", "Бюджет первой категории");
        fields.put("budgetCategory2", "Бюджет второй категории");
        fields.put("budgetCategory3", "Бюджет третьей категории");
        fields.put("priceForOne", "Цена за единицу");
        fields.put("finalPrice", "Итоговая цена");
        fields.put("finalBudget", "Итоговый бюджет");
        fields.put("priceWithVat", "Итоговая цена с учетом НДС");
        return fields.get(fieldName);
    }

    String getRussianMainLabel(String labelText) {
        Map<String, String> labelsText = new HashMap<>();
        labelsText.put("Appeal", "Новое обращение");
        labelsText.put("Application", "Новая Заявка");
        labelsText.put("Budget", "Новый бюджет");
        labelsText.put("Category", "Новая категория");
        labelsText.put("Department", "Новый отдел");
        labelsText.put("Management", "Новое управление");
        labelsText.put("Order", "Новая закупка");
        labelsText.put("Position", "Новая должность");
        labelsText.put("ProcurementArchive", "Новый архив закупок");
        labelsText.put("Role", "Новая роль");
        labelsText.put("Status", "Новый статус");
        labelsText.put("User", "Новый пользователь");
        return labelsText.get(labelText);
    }

}
