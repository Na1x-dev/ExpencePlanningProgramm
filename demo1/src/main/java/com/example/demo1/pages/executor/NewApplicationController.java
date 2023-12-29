package com.example.demo1.pages.executor;

import com.example.demo1.AppData;
import com.example.demo1.RequestsBuilder;
import com.example.demo1.models.Appeal;
import com.example.demo1.models.Application;
import com.example.demo1.models.Category;
import com.example.demo1.models.User;
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
    public VBox vbox;

    AppData appData = AppData.getInstance();

    List<User> users;
    List<Category> categories;

    @FXML
    public void create(ActionEvent actionEvent) {
        if (!appData.checkFields(appData.getFields(vbox), new Label()) && !appData.checkComboBoxes(appData.getBoxes(vbox))) {

        } else {
            createApplication();
            AppData.toNextStage("executor/ApplicationsPage.fxml", createButton, "Executor Page");
        }
    }

    @FXML
    public void back(ActionEvent actionEvent) {
        AppData.toNextStage("executor/AppealsPage.fxml", backButton, "Executor Page");
    }

    @FXML
    void initialize() {
        initComboBoxes();
        textFieldSetFormat(amount, "\\d*");
        textFieldSetFormat(priceForOne, "\\d*\\.?\\d{0,2}");
        calculateFinalPrice(amount);
        calculateFinalPrice(priceForOne);
        initComboText();
    }

    void createApplication() {
        Application application = new Application();
        application.setProductName(productName.getText());
        application.setProductCharacteristic(productCharacteristic.getText());
        application.setPriceForOne(Double.valueOf(priceForOne.getText()));
        application.setAmount(Integer.valueOf(amount.getText()));
        application.setApplicationComment(applicationComment.getText());
        application.setComment(applicationComment.getText());
        application.setFinalPrice(Double.valueOf(finalPrice.getText()));
        application.setCategory(category.getValue());
        application.setCustomerUser(customerUser.getValue());
        application.setCreateUser(appData.getUser());
        application.setStatus(appData.findStatus("создано"));
        application.setComment(applicationComment.getText());
        Appeal appeal = appData.getGson().fromJson(RequestsBuilder.getRequest("/admin/get/appeal/" + appData.getPutModelId()).body(), Appeal.class);
        application.setClosingUser(null);
        application.setAppeal(appeal);
        HttpResponse<String> response = RequestsBuilder.postRequest(appData.getGson().toJson(application), "/admin/create/application");
    }

    void initComboBoxes() {
        HttpResponse<String> usersResponse = RequestsBuilder.getRequest("/admin/getAll/user");
        HttpResponse<String> categoriesResponse = RequestsBuilder.getRequest("/admin/getAll/category");
        Type usersListType = new TypeToken<ArrayList<User>>() {
        }.getType();
        Type categoriesListType = new TypeToken<ArrayList<Category>>() {
        }.getType();
        users = appData.getGson().fromJson(usersResponse.body(), usersListType);
        categories = appData.getGson().fromJson(categoriesResponse.body(), categoriesListType);
        customerUser.getItems().addAll(users);
        category.getItems().addAll(categories);
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


    void calculateFinalPrice(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!priceForOne.getText().isEmpty() && !amount.getText().isEmpty())
                finalPrice.setText(String.valueOf(Double.parseDouble(priceForOne.getText()) * Integer.parseInt(amount.getText())));
            else finalPrice.setText("");
        });
    }

    void initComboText() {
        customerUser.setCellFactory(new Callback<>() {
            @Override
            public ListCell<User> call(ListView<User> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(User item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.toComboBox());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
        customerUser.setConverter(new StringConverter<>() {
            @Override
            public String toString(User user) {
                if (user == null) {
                    return null;
                } else {
                    return user.toComboBox();
                }
            }

            @Override
            public User fromString(String string) {
                return null;
            }
        });

        category.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Category> call(ListView<Category> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Category item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.toComboBox());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        category.setConverter(new StringConverter<>() {
            @Override
            public String toString(Category categoryItem) {
                if (categoryItem == null) {
                    return null;
                } else {
                    return categoryItem.toComboBox();
                }
            }

            @Override
            public Category fromString(String string) {
                return null;
            }
        });
    }
}
