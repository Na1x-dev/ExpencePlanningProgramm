package com.example.demo1.pages.executor;

import java.net.URL;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

import com.example.demo1.AppData;
import com.example.demo1.RequestsBuilder;
import com.example.demo1.models.Appeal;
import com.example.demo1.models.Application;
import com.example.demo1.models.Order;
import com.example.demo1.models.ProcurementArchive;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class OrderCommentController {

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
        AppData.toNextStage("executor/OrdersPage.fxml", backButton, "Executor Page");
    }

    @FXML
    void close(ActionEvent event) {
        if (!appData.checkAreas(appData.getAreas(anchorPane), resultLabel)) {
        } else {
            HttpResponse<String> getResponse = RequestsBuilder.getRequestWithProperty("/admin/get/order", String.valueOf(appData.getPutModelId()));
            Order order = appData.getGson().fromJson(getResponse.body(), Order.class);
            order.setStatus(appData.findStatus("закрыто"));
            Application application = order.getApplication();
            Appeal appeal = application.getAppeal();
            appeal.setStatus(appData.findStatus("закрыто"));
            application.setStatus(appData.findStatus("закрыто"));
            HttpResponse<String> putAppealResponse = RequestsBuilder.putRequest(appData.getGson().toJson(appeal), "/admin/update/appeal/" + appeal.getAppealId());
            HttpResponse<String> putApplicationResponse = RequestsBuilder.putRequest(appData.getGson().toJson(application), "/admin/update/application/" + application.getApplicationId());
            HttpResponse<String> putOrderResponse = RequestsBuilder.putRequest(appData.getGson().toJson(order), "/admin/update/order/" + appData.getPutModelId());
            HttpResponse<String> postResponse = RequestsBuilder.postRequest(appData.getGson().toJson(createArchive(order, textArea.getText())), "/admin/create/procurementarchive");
            AppData.toNextStage("executor/AppealsPage.fxml", backButton, "Executor Page");
        }
    }

    @FXML
    void initialize() {

    }

    ProcurementArchive createArchive(Order order, String comment){
        ProcurementArchive archive = new ProcurementArchive();
        archive.setComment(comment);
        archive.setOrder(order);
        archive.setUser(appData.getUser());
        archive.setStatus(appData.findStatus("закрыто"));
        return archive;
    }

}
