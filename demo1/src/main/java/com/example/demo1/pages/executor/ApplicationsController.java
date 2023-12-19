package com.example.demo1.pages.executor;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.demo1.AppData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class ApplicationsController {

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
    private Button logOutButton;

    @FXML
    private Button manualButton;

    @FXML
    void toAppealsTable(ActionEvent event) {
        AppData.toNextStage("executor/AdminPage.fxml", AppealsButton, "Executor Page");
    }

    @FXML
    void toApplicationsTable(ActionEvent event) {
//        AppData.toNextStage("executor/ApplicationsPage.fxml", ApplicationsButton, "Executor Page");
    }

    @FXML
    void toManual(ActionEvent event) {
        AppData.toNextStage("executor/ManualPage.fxml", manualButton, "Executor Page");
    }

    @FXML
    void toOrdersTable(ActionEvent event) {
        AppData.toNextStage("executor/OrdersPage.fxml", OrdersButton, "Executor Page");
    }

    @FXML
    void toProcurementArchiveTable(ActionEvent event) {
        AppData.toNextStage("executor/ProcurementArchivePage.fxml", ProcurementArchiveButton, "Executor Page");
    }

    @FXML
    void logOut(ActionEvent event) {
        AppData.toNextStage("login.fxml", logOutButton, "login");
    }
    @FXML
    void initialize() {
    ApplicationsButton.setStyle("-fx-background-color: #fff; -fx-text-fill: #555");
    }

}
