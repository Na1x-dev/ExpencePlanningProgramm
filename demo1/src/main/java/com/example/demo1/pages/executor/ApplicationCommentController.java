package com.example.demo1.pages.executor;

import java.net.URL;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

import com.example.demo1.AppData;
import com.example.demo1.RequestsBuilder;
import com.example.demo1.models.Appeal;
import com.example.demo1.models.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ApplicationCommentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button backButton;

    @FXML
    private Button closeButton;

    @FXML
    private GridPane gridPane;

    @FXML
    private HBox hbox;

    @FXML
    private Label resultLabel;

    @FXML
    private TextArea textArea;

    AppData appData = AppData.getInstance();

    @FXML
    void back(ActionEvent event) {
        AppData.toNextStage("executor/ApplicationsPage.fxml", backButton, "Executor Page");
    }

    @FXML
    void close(ActionEvent event) {
        if (!appData.checkAreas(appData.getAreas(anchorPane), resultLabel)) {
        } else {
            HttpResponse<String> getResponse = RequestsBuilder.getRequestWithProperty("/admin/get/application", String.valueOf(appData.getPutModelId()));
            Application application = appData.getGson().fromJson(getResponse.body(), Application.class);
            application.setStatus(appData.findStatus("закрыто"));
            application.setClosingUser(appData.getUser());
            application.setComment(textArea.getText());
            System.out.println(application);
            HttpResponse<String> putResponse = RequestsBuilder.putRequest(appData.getGson().toJson(application), "/admin/update/application/" + appData.getPutModelId());
            AppData.toNextStage("executor/ApplicationsPage.fxml", backButton, "Executor Page");
        }
    }

    @FXML
    void initialize() {

    }

}
