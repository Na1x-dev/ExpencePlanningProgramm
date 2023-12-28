package com.example.demo1.pages.executor;

import com.example.demo1.AppData;
import com.example.demo1.RequestsBuilder;
import com.example.demo1.models.Appeal;
import com.example.demo1.models.Application;
import com.example.demo1.models.Category;
import com.example.demo1.models.User;
import com.google.gson.reflect.TypeToken;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class NewApplicationController {
    @FXML
    public ComboBox<User> customerUser;
    @FXML
    public ComboBox<Category> category;
    @FXML
    public TextField productName;
    @FXML
    public TextField productCharacteristic;
    @FXML
    public TextField priceForOne;
    @FXML
    public TextField amount;
    @FXML
    public TextField applicationComment;
    @FXML
    public TextField finalPrice;
    @FXML
    public Button createButton;
    @FXML
    public Button backButton;

    AppData appData = AppData.getInstance();

    List<User> users;
    List<Category> categories;

    @FXML
    public void create(ActionEvent actionEvent) {
        createApplication();
        AppData.toNextStage("executor/ApplicationsPage.fxml", createButton, "Executor Page");
    }

    @FXML
    public void back(ActionEvent actionEvent) {
        AppData.toNextStage("executor/AppealsPage.fxml", backButton, "Executor Page");
    }

    @FXML
    void initialize() {
        initComboBoxes();
    }

    void createApplication() {
        Application application = new Application();
        application.setProductName(productName.getText());
        application.setProductCharacteristic(productCharacteristic.getText());
        application.setPriceForOne(Double.valueOf(priceForOne.getText()));
        application.setAmount(Integer.valueOf(amount.getText()));
        application.setApplicationComment(applicationComment.getText());
        application.setFinalPrice(Double.valueOf(finalPrice.getText()));
        application.setCategory(category.getValue());
        application.setCustomerUser(customerUser.getValue());
        application.setCreateUser(appData.getUser());
        application.setStatus(appData.findStatus("создано"));
        Appeal appeal = appData.getGson().fromJson(RequestsBuilder.getRequest("/admin/get/appeal/" + appData.getPutModelId()).body(), Appeal.class);
        application.setClosingUser(null);
        application.setAppeal(appeal);

        HttpResponse<String> response = RequestsBuilder.postRequest(appData.getGson().toJson(application), "/admin/create/application");
        System.out.println(response.body());

    }

    void initComboBoxes() {
        HttpResponse<String> usersResponse = RequestsBuilder.getRequest("/admin/getAll/user");
        HttpResponse<String> categoriesResponse = RequestsBuilder.getRequest("/admin/getAll/category");
        Type usersListType = new TypeToken<ArrayList<User>>() {
        }.getType();
        Type categoriesListType = new TypeToken<ArrayList<Category>>() {
        }.getType();
        users = appData.getGson().fromJson(usersResponse.body(), usersListType);
        categories = appData.getGson().fromJson(categoriesResponse.body(), categoriesListType);
        customerUser.getItems().addAll(users);
        category.getItems().addAll(categories);
    }
}
