/**
 * Sample Skeleton for 'CreateAppealPage.fxml' Controller Class
 */

package com.example.demo1.pages.customer;

import com.example.demo1.AppData;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

import com.example.demo1.RequestsBuilder;
import com.example.demo1.models.Appeal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class CreateAppealController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button backButton;

    @FXML
    private Button createButton;

    @FXML
    private HBox hbox;

    @FXML
    private TextArea textArea;

    AppData appData;

    @FXML
    void back(ActionEvent event) {
        AppData.toNextStage("customer/CustomerPage.fxml", backButton, "Customer Page");
    }

    @FXML
    void create(ActionEvent event) {
        if (!textArea.getText().isEmpty()) {
            Appeal appeal = new Appeal();
            appeal.setAppealText(textArea.getText());
            HttpResponse<String> response = RequestsBuilder.postRequest(appData.getGson().toJson(appeal), "/appeals/create");
            System.out.println(response);
            AppData.toNextStage("customer/CustomerPage.fxml", backButton, "Customer Page");
        }
    }

    @FXML
    void initialize() {
        appData = AppData.getInstance();
    }

}
