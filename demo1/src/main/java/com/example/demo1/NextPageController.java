package com.example.demo1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class NextPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logOutButton;

    @FXML
    private VBox vbox;

    @FXML
    void logOut(ActionEvent event) {
        AppData.toNextStage("login.fxml", logOutButton, "login");
        AppData appData = AppData.getInstance();
        System.out.println(appData.getRoles());
        System.out.println(appData.getStatuses());
        System.out.println(appData.getUser());
    }

    @FXML
    void initialize() {
        assert logOutButton != null : "fx:id=\"logOutButton\" was not injected: check your FXML file 'nextPage.fxml'.";
        assert vbox != null : "fx:id=\"vbox\" was not injected: check your FXML file 'nextPage.fxml'.";

    }

}
