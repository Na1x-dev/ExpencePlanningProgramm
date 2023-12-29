package com.example.demo1.pages.admin;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.demo1.AppData;
import com.example.demo1.RequestsBuilder;
import com.example.demo1.models.Appeal;
import com.example.demo1.models.User;
import com.google.gson.JsonElement;
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
import javafx.scene.layout.*;

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

    JsonObject updateUnit;

    @FXML
    void create(ActionEvent event) {

        if (appData.getPutModelId() == -1L)
            createModelObject();
        else
            updateModelObject();
        AppData.toNextStage("admin/AdminPage.fxml", CreateButton, "Admin Page");
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
        initUpdateUnit();
        createFields();
        createComboBoxes();
        vbox.getChildren().add(ButtonBox);
    }

    void initUpdateUnit() {
        if (appData.getPutModelId() > -1L) {
            HttpResponse<String> response = RequestsBuilder.getRequest("/admin/get/" + modelClass.getSimpleName().toLowerCase() + "/" + appData.getPutModelId());
            updateUnit = appData.getGson().fromJson(response.body(), JsonObject.class);
        }
    }

    void createMainLabel() {
        Label mainLabel;
        if (appData.getPutModelId().equals(-1L)) {
            mainLabel = new Label(getRussianMainLabel(modelClass.getSimpleName()));
        } else {
            mainLabel = new Label(getRussianUpdateMainLabel(modelClass.getSimpleName()));
            CreateButton.setText("Изменить");
        }
        mainLabel.setPadding(new Insets(0, 0, 50, 0));
        vbox.getChildren().add(mainLabel);
    }

    void createFields() {
        List<Field> fields = new ArrayList<>();
        for (Field field : modelClass.getDeclaredFields()) {
            if ((field.getType().equals(String.class) || field.getType().equals(Double.class)|| field.getType().equals(Integer.class)) && !blackListFields().contains(field.getName())) {
                fields.add(field);
            }
        }

        HBox hbox = new HBox();
        hbox.setSpacing(10);

        int numFields = 0;

        Map<String, String> updateValues = new HashMap<>();
        if (appData.getPutModelId() > -1L) {
            JsonObject updateUnitJson = appData.getGson().toJsonTree(updateUnit).getAsJsonObject();
            for (Field field : fields) {
                String fieldName = field.getName();
                String fieldValue = updateUnitJson.get(fieldName).getAsString();
                updateValues.put(fieldName, fieldValue);
            }
        }

        for (Field field : fields) {
            TextField textField = new TextField();
            HBox.setHgrow(textField, Priority.ALWAYS);
            textField.setPromptText(getRussianField(field.getName()));
            textField.setId(field.getName());
            textField.setPrefHeight(40);

//            createLabel(field);

            String fieldValue = updateValues.get(field.getName());
            if (fieldValue != null) {
                textField.setText(fieldValue);
            }

            hbox.getChildren().add(textField);

            numFields++;
            if (numFields % 2 == 0) {
                vbox.getChildren().add(hbox);
                hbox = new HBox();
                hbox.setSpacing(10);
            }
        }

//        if (numFields % 2 != 0) {
//            hbox.getChildren().add(new TextField());
//        }
        if (!hbox.getChildren().isEmpty()) {
            vbox.getChildren().add(hbox);
        }
    }


    void createComboBoxes() {
        List<Field> fields = new ArrayList<>();
        for (Field field : modelClass.getDeclaredFields()) {
            if (appData.getFullModelsList().contains(field.getType())) {
                fields.add(field);
            }
        }

        HBox hbox = new HBox();
        hbox.setSpacing(10);

        Map<String, List<JsonObject>> objectsByClass = new HashMap<>();
        for (Field field : fields) {
//            createLabel(field);

            String className = field.getType().getSimpleName();
            if (!objectsByClass.containsKey(className)) {
                HttpResponse<String> response = RequestsBuilder.getRequest("/admin/getAll/" + className.toLowerCase());
                Type listType = new TypeToken<ArrayList<JsonObject>>() {
                }.getType();
                ArrayList<JsonObject> jsonObjectList = appData.getGson().fromJson(response.body(), listType);
                objectsByClass.put(className, jsonObjectList);
            }

            List<JsonObject> jsonObjectList = objectsByClass.get(className);

            ComboBox<String> comboBox = new ComboBox<>();
            HBox.setHgrow(comboBox, Priority.ALWAYS);
            comboBox.setPromptText(getRussianField(field.getName()));
            comboBox.setId(field.getName());

            for (JsonObject jObject : jsonObjectList) {
                String idPropertyName = field.getType().getDeclaredFields()[0].getName();
                JsonElement objectId = jObject.get(idPropertyName);
                String comboItem = objectId + ". " + jObject.get(field.getType().getDeclaredFields()[1].getName());
                comboBox.getItems().add(comboItem);

                if (appData.getPutModelId() > -1L) {
                    JsonObject buf = null;
                    try {
                        buf = (JsonObject) updateUnit.get(field.getName());
                        if (buf.get(idPropertyName).equals(objectId)) {
                            comboBox.setValue(comboItem);
                        }
                    } catch (ClassCastException e) {
                        System.out.println("пустое поле");
                    }
                }
            }

            comboBox.setPrefWidth(10000);
            hbox.getChildren().add(comboBox);

            if (hbox.getChildren().size() == 2) {
                vbox.getChildren().add(hbox);
                hbox = new HBox();
                hbox.setSpacing(10);
            }
        }

        if (!hbox.getChildren().isEmpty()) {
            vbox.getChildren().add(hbox);
        }
    }


    void createLabel(Field field) {
        Label label = new Label(getRussianField(field.getName()));
        vbox.getChildren().add(label);
    }

    void createModelObject() {
        Pattern pattern = Pattern.compile("^(\\d+)\\.");
        Matcher matcher;
        Set<Node> textFieldNodes = vbox.lookupAll(".text-field");
        JsonObject jsonObject = new JsonObject();
        for (Node field : textFieldNodes) {
            jsonObject.addProperty((field).getId(), ((TextField) field).getText());
        }
        Set<Node> comboBoxNodes = vbox.lookupAll(".combo-box");
        for (Node combo : comboBoxNodes) {
            matcher = pattern.matcher(((ComboBox<?>)combo).getValue().toString());
            System.out.println((combo).getId() + "/" + ((ComboBox<?>) combo).getValue().toString());
            String objectId = matcher.find() ? matcher.group(1) : "";
            HttpResponse<String> response = RequestsBuilder.getRequest("/admin/get/" + (combo).getId() + "/" + objectId);
            System.out.println(response.body());
            JsonObject innerObject = appData.getGson().fromJson(response.body(), JsonObject.class);
            jsonObject.add((combo).getId(), innerObject);
        }
        HttpResponse<String> response = RequestsBuilder.postRequest(String.valueOf(jsonObject), "/admin/create/" + modelClass.getSimpleName().toLowerCase());
    }

    void updateModelObject() {
        Pattern pattern = Pattern.compile("^(\\d+)\\.");
        Matcher matcher;
        Set<Node> textFieldNodes = vbox.lookupAll(".text-field");
        for (Node field : textFieldNodes) {
            updateUnit.addProperty((field).getId(), ((TextField) field).getText());
        }
        Set<Node> comboBoxNodes = vbox.lookupAll(".combo-box");
        for (Node combo : comboBoxNodes) {
            matcher = pattern.matcher((combo).getId() + "/" + ((ComboBox<?>) combo).getValue().toString());
            String objectId = matcher.find() ? matcher.group(1) : "";
            HttpResponse<String> response = RequestsBuilder.getRequest("/admin/get/" + (combo).getId() + "/" + objectId);
            JsonObject innerObject = appData.getGson().fromJson(response.body(), JsonObject.class);
            updateUnit.add((combo).getId(), innerObject);
        }
        HttpResponse<String> response = RequestsBuilder.putRequest(String.valueOf(updateUnit), "/admin/update/" + modelClass.getSimpleName().toLowerCase() + "/" + appData.getPutModelId());
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
        fields.put("amount", "Количество");
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

    String getRussianUpdateMainLabel(String labelText) {
        Map<String, String> labelsText = new HashMap<>();
        labelsText.put("Appeal", "Изменение обращения");
        labelsText.put("Application", "Изменение Заявки");
        labelsText.put("Budget", "Изменение бюджета");
        labelsText.put("Category", "Изменение категории");
        labelsText.put("Department", "Изменение отдела");
        labelsText.put("Management", "Изменение управления");
        labelsText.put("Order", "Изменение закупки");
        labelsText.put("Position", "Изменение должности");
        labelsText.put("ProcurementArchive", "Изменение архива закупок");
        labelsText.put("Role", "Изменение роли");
        labelsText.put("Status", "Изменение статуса");
        labelsText.put("User", "Изменение пользователя");
        return labelsText.get(labelText);
    }

    ArrayList<String> blackListFields(){
        ArrayList<String> fields = new ArrayList<>();
        fields.add("registrationDate");
        fields.add("closingDate");
        fields.add("applicationDate");
        fields.add("applicationComment");
        fields.add("closingDate");
        return fields;
    }

}
