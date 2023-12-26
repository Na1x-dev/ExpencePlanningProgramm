package com.example.demo1.pages.executor;

import java.net.URL;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.ResourceBundle;

import com.example.demo1.AppData;
import com.example.demo1.RequestsBuilder;
import com.example.demo1.models.Appeal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import lombok.Data;

public class AppealCommentController {

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
        AppData.toNextStage("executor/AppealsPage.fxml", backButton, "Executor Page");
    }

    @FXML
    void close(ActionEvent event) {
        HttpResponse<String> getResponse = RequestsBuilder.getRequestWithProperty("/admin/get/appeal" , String.valueOf(appData.getPutModelId()));
        Appeal appeal = appData.getGson().fromJson(getResponse.body(), Appeal.class);
        appeal.setStatus(appData.findStatus("закрыто"));
        appeal.setClosingUser(appData.getUser());
        appeal.setComment(textArea.getText());
        System.out.println(appeal);
        HttpResponse<String> putResponse = RequestsBuilder.putRequest(appData.getGson().toJson(appeal),"/admin/update/appeal/" + String.valueOf(appData.getPutModelId()));
        AppData.toNextStage("executor/AppealsPage.fxml", backButton, "Executor Page");
    }

    @FXML
    void initialize() {

    }

}
