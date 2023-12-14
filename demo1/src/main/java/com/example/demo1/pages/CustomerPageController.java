package com.example.demo1.pages;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.demo1.models.Appeal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import com.example.demo1.AppData;

public class CustomerPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableColumn<?, ?> appealId;

    @FXML
    private TableColumn<?, ?> closeDate;

    @FXML
    private TableColumn<?, ?> comment;

    @FXML
    private Button createAppealButton;

    @FXML
    private TableView<?> customerTable;

    @FXML
    private HBox hbox;

    @FXML
    private Button logOutButton;

    @FXML
    private TableColumn<?, ?> registrationDate;

    @FXML
    private TableColumn<?, ?> status;

    @FXML
    private TableColumn<?, ?> text;

    @FXML
    void logOut(ActionEvent event) {
        AppData.toNextStage("login.fxml", logOutButton, "login");
    }

    @FXML
    void toCreateAppeal(ActionEvent event) {
        AppData.toNextStage("createAppealPage.fxml", createAppealButton, "Create Appeal");
    }


    @FXML
    void initialize() {
        createAppealButton.setMinWidth(hbox.getPrefWidth() * 2);
        logOutButton.setMinWidth(hbox.getPrefWidth());
        System.out.println(hbox.getMaxWidth());
    }

}
