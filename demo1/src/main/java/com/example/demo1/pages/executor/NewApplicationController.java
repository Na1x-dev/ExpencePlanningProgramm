package com.example.demo1.pages.executor;

import com.example.demo1.AppData;
import com.example.demo1.models.Category;
import com.example.demo1.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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

    @FXML
    public void create(ActionEvent actionEvent) {

        AppData.toNextStage("executor/ApplicationsPage.fxml", createButton, "Executor Page");
    }

    @FXML
    public void back(ActionEvent actionEvent) {
        AppData.toNextStage("executor/AppealsPage.fxml", backButton, "Executor Page");
    }

    @FXML
    void initialize() {
    }
}
