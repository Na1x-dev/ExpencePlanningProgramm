package com.example.demo1.pages.executor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class AppealsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AppealsButton;

    @FXML
    private Button ApplicationsButton;

    @FXML
    private Button OrdersButton;

    @FXML
    private Button ProcurementArchiveButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<?> customerTable;

    @FXML
    private HBox hbox;

    @FXML
    private Button logOutButton;

    @FXML
    private Button logOutButton3;

    @FXML
    void logOut(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}
