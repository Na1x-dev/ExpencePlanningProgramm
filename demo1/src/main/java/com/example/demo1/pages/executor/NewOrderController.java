package com.example.demo1.pages.executor;

import com.example.demo1.AppData;
import com.example.demo1.RequestsBuilder;
import com.example.demo1.models.*;
import com.google.gson.reflect.TypeToken;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class NewOrderController {
    @FXML
    public ComboBox<Integer> vat;
    @FXML
    public TextField procurementOrganization;
    @FXML
    public TextField contactPerson;
    @FXML
    public TextField unp;
    @FXML
    public TextField contactNumber;
    @FXML
    public TextField comment;
    @FXML
    public TextField finalPrice;
    @FXML
    public Button createButton;
    @FXML
    public Button backButton;
    public VBox vbox;

    Application application;
    AppData appData = AppData.getInstance();

    @FXML
    public void create(ActionEvent actionEvent) {
        if (!appData.checkFields(appData.getFields(vbox), new Label()) && !appData.checkComboBoxes(appData.getBoxes(vbox)) ) {

        } else {
            createOrder();
            AppData.toNextStage("executor/OrdersPage.fxml", createButton, "Executor Page");
        }
    }

    @FXML
    public void back(ActionEvent actionEvent) {
        AppData.toNextStage("executor/ApplicationsPage.fxml", backButton, "Executor Page");
    }

    @FXML
    void initialize() {
        initComboBoxes();
        application = appData.getGson().fromJson(RequestsBuilder.getRequest("/admin/get/application/" + appData.getPutModelId()).body(), Application.class);
        textFieldSetFormat(unp, "\\d{0,9}");
//        textFieldSetFormat(contactNumber, "^(\\+375|80)\\s?\\(?(17|25|29|33|44)\\)?\\s?\\d{3}(-|\\s)?\\d{2}(-|\\s)?\\d{2}$");
        calculateFinalPrice(vat);
    }

    void createOrder() {
        Order order = new Order();
        order.setProcurementOrganization(procurementOrganization.getText());
        order.setContactPerson(contactPerson.getText());
        order.setContactNumber(contactNumber.getText());
        order.setUnp(Integer.valueOf(unp.getText()));
        order.setPriceWithVat(Double.valueOf(finalPrice.getText()));
        order.setStatus(appData.findStatus("создано"));
        order.setCreateUser(appData.getUser());
        order.setVat(vat.getValue());
        order.setApplication(application);
        HttpResponse<String> response = RequestsBuilder.postRequest(appData.getGson().toJson(order), "/admin/create/order");
        System.out.println(response.body());
    }

    void initComboBoxes() {
        List<Integer> vats = new ArrayList<>();
        vats.add(5);
        vats.add(10);
        vats.add(15);
        vats.add(20);
        vats.add(25);
        vat.getItems().addAll(vats);
    }

    void textFieldSetFormat(TextField textField, String format) {
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches(format)) {
                return change;
            }
            return null;
        });
        textField.setTextFormatter(textFormatter);
    }


    void calculateFinalPrice(ComboBox<Integer> vat) {
        vat.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            if ()
                finalPrice.setText(String.valueOf((application.getFinalPrice()  * vat.getValue() *0.01) + application.getFinalPrice() ));
//            else finalPrice.setText("");
        });
    }

}
