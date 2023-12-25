package com.example.demo1.pages.admin;

import java.net.http.HttpResponse;

import com.example.demo1.models.*;
import com.example.demo1.DialogBox;
import com.google.gson.internal.LinkedTreeMap;

import java.util.*;

import com.example.demo1.RequestsBuilder;

import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import com.example.demo1.AppData;
import javafx.util.Duration;

public class AdminMainController {
    @FXML
    public AnchorPane upperAnchorPane;
    @FXML
    public TableView<Map<String, Object>> mainTable;
    @FXML
    public AnchorPane bottomAnchorPane;
    @FXML
    public HBox hbox;
    @FXML
    public Button logOutButton;
    @FXML
    public Label pageNameLabel;
    @FXML
    public Label userNameLabel;
    @FXML
    private Button AppealsButton;
    @FXML
    private Button ApplicationsButton;
    @FXML
    private Button BudgetButton;
    @FXML
    private Button CategoriesButton;
    @FXML
    private Button DepartmentsButton;
    @FXML
    private Button ManagementsButton;
    @FXML
    private Button OrdersButton;
    @FXML
    private Button PositionsButton;
    @FXML
    private Button ProcurementArchiveButton;
    @FXML
    private Button UsersButton;
    @FXML
    private Button CreateButton;
    @FXML
    private TextField searchField;
    Class modelClass;
    AppData appData = AppData.getInstance();

    @FXML
    void toAppeals(ActionEvent event) {
        appData.setAdminMode(0);
        AppData.toNextStage("admin/AdminPage.fxml", AppealsButton, "Admin Page");
    }

    @FXML
    void toApplications(ActionEvent event) {
        appData.setAdminMode(1);
        AppData.toNextStage("admin/AdminPage.fxml", ApplicationsButton, "Admin Page");
        System.out.println(Arrays.toString(appData.getModelsList().get(1).getDeclaredFields()));
    }

    @FXML
    void toBudget(ActionEvent event) {
        appData.setAdminMode(2);
        AppData.toNextStage("admin/AdminPage.fxml", BudgetButton, "Admin Page");
    }

    @FXML
    void toCategories(ActionEvent event) {
        appData.setAdminMode(3);
        AppData.toNextStage("admin/AdminPage.fxml", CategoriesButton, "Admin Page");
    }

    @FXML
    void toDepartments(ActionEvent event) {
        appData.setAdminMode(4);
        AppData.toNextStage("admin/AdminPage.fxml", DepartmentsButton, "Admin Page");
    }

    @FXML
    void toManagements(ActionEvent event) {
        appData.setAdminMode(5);
        AppData.toNextStage("admin/AdminPage.fxml", ManagementsButton, "Admin Page");
    }

    @FXML
    void toOrders(ActionEvent event) {
        appData.setAdminMode(6);
        AppData.toNextStage("admin/AdminPage.fxml", OrdersButton, "Admin Page");
    }

    @FXML
    void toPositions(ActionEvent event) {
        appData.setAdminMode(7);
        AppData.toNextStage("admin/AdminPage.fxml", PositionsButton, "Admin Page");
    }

    @FXML
    void toProcurementButton(ActionEvent event) {
        appData.setAdminMode(8);
        AppData.toNextStage("admin/AdminPage.fxml", ProcurementArchiveButton, "Admin Page");
    }

    @FXML
    void toUsers(ActionEvent event) {
        appData.setAdminMode(9);
        AppData.toNextStage("admin/AdminPage.fxml", UsersButton, "Admin Page");
    }


    @FXML
    void logOut(ActionEvent event) {
        AppData.toNextStage("login.fxml", logOutButton, "login");
    }

    @FXML
    void toCreatePage(ActionEvent event) {
        appData.setPutModelId(-1L);
        AppData.toNextStage("admin/AdminFields.fxml", CreateButton, "Admin Page");
    }

    @FXML
    void initialize() {
        modelClass = appData.getModelsList().get(appData.getAdminMode());
        mainTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        changeButtonsColor();
        responseIntoTable();
        initSearchField();
        userNameLabel.setText(appData.getUser().getUserName());
        pageNameLabel.setText("Страница модели " + modelClass.getSimpleName());
    }

    public void responseIntoTable() {
        HttpResponse<String> response = RequestsBuilder.getRequest("/admin/getAll/" + modelClass.getSimpleName().toLowerCase());
        if (response.statusCode() == 200) {
            TypeToken<List<Map<String, Object>>> typeToken = new TypeToken<>() {
            };
            List<Map<String, Object>> objectList = appData.getGson().fromJson(response.body(), typeToken.getType());
            if (!objectList.isEmpty() && mainTable.getColumns().isEmpty()) {
                Map<String, Object> firstObject = objectList.getFirst();
                createTableColumns(firstObject);
            }
            mainTable.getItems().addAll(objectList);
        } else {
            System.out.println("null");
        }
    }

    public void createTableColumns(Map<String, Object> object) {
        for (String propertyName : object.keySet()) {
            TableColumn<Map<String, Object>, Object> fieldColumn = new TableColumn<>();
            fieldColumn.setCellValueFactory(data -> {
                Object modelProperty = data.getValue().get(propertyName);
                if (modelProperty != null) {
                    if (modelProperty.getClass().equals(Double.class)) {
                        return new SimpleObjectProperty<>(((Double) modelProperty).intValue());
                    }
                    if (modelProperty.getClass().equals(LinkedTreeMap.class)) {
                        return new SimpleObjectProperty<>(((Double) returnIdFromModels(modelProperty)).intValue());
                    }
                    if (modelProperty.getClass().equals(String.class) && appData.isValidDate((String) modelProperty)) {
                        return new SimpleObjectProperty<>(appData.getDateForAdmin((String) modelProperty));
                    }
                    return new SimpleObjectProperty<>(modelProperty);
                }
                return null;
            });
            Tooltip tooltip = new Tooltip(propertyName);
            tooltip.setShowDelay(Duration.millis(100));
            fieldColumn.setGraphic(new Label(propertyName));
            Tooltip.install(fieldColumn.getGraphic(), tooltip);
            mainTable.getColumns().add(fieldColumn);
        }
        mainTable.getColumns().addAll(createUpdateButtons(), createDeleteButtons());

    }

    TableColumn<Map<String, Object>, Void> createUpdateButtons() {
        TableColumn<Map<String, Object>, Void> editColumn = new TableColumn<>("Изменить");
        editColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Изменить");

            {
                editButton.setOnAction(event -> {
                    String fieldName = modelClass.getDeclaredFields()[0].getName();
                    appData.setPutModelId(((Double) getTableView().getItems().get(getIndex()).get(fieldName)).longValue());
                    AppData.toNextStage("admin/AdminFields.fxml", CreateButton, "Admin Page");
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
                }
            }
        });
        return editColumn;
    }

    TableColumn<Map<String, Object>, Void> createDeleteButtons() {
        TableColumn<Map<String, Object>, Void> deleteColumn = new TableColumn<>("Удалить");
        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Удалить");

            {
                deleteButton.setOnAction(event -> {
                    String fieldName = modelClass.getDeclaredFields()[0].getName();
                    Long id = ((Double) getTableView().getItems().get(getIndex()).get(fieldName)).longValue();
                    DialogBox dialogBox = new DialogBox("Вы действительно хотите удалить " + modelClass.getSimpleName() + " с Id=" + id);
                    dialogBox.showAndWait();
                    if (dialogBox.getResult() == DialogBox.Result.OK) {
                        HttpResponse<String> response = RequestsBuilder.deleteRequest("/admin/delete/" + modelClass.getSimpleName().toLowerCase() + "/" + id);
                        getTableView().getItems().clear();
                        responseIntoTable();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });
        return deleteColumn;
    }


    Object returnIdFromModels(Object modelProperty) {
        if (((LinkedTreeMap<?, ?>) modelProperty).get(Appeal.class.getDeclaredFields()[0].getName()) != null) {
            return ((LinkedTreeMap<?, ?>) modelProperty).get(Appeal.class.getDeclaredFields()[0].getName());
        } else if (((LinkedTreeMap<?, ?>) modelProperty).get(Application.class.getDeclaredFields()[0].getName()) != null) {
            return ((LinkedTreeMap<?, ?>) modelProperty).get(Application.class.getDeclaredFields()[0].getName());
        } else if (((LinkedTreeMap<?, ?>) modelProperty).get(Budget.class.getDeclaredFields()[0].getName()) != null) {
            return ((LinkedTreeMap<?, ?>) modelProperty).get(Budget.class.getDeclaredFields()[0].getName());
        } else if (((LinkedTreeMap<?, ?>) modelProperty).get(Category.class.getDeclaredFields()[0].getName()) != null) {
            return ((LinkedTreeMap<?, ?>) modelProperty).get(Category.class.getDeclaredFields()[0].getName());
        } else if (((LinkedTreeMap<?, ?>) modelProperty).get(Department.class.getDeclaredFields()[0].getName()) != null) {
            return ((LinkedTreeMap<?, ?>) modelProperty).get(Department.class.getDeclaredFields()[0].getName());
        } else if (((LinkedTreeMap<?, ?>) modelProperty).get(Management.class.getDeclaredFields()[0].getName()) != null) {
            return ((LinkedTreeMap<?, ?>) modelProperty).get(Management.class.getDeclaredFields()[0].getName());
        } else if (((LinkedTreeMap<?, ?>) modelProperty).get(Order.class.getDeclaredFields()[0].getName()) != null) {
            return ((LinkedTreeMap<?, ?>) modelProperty).get(Order.class.getDeclaredFields()[0].getName());
        } else if (((LinkedTreeMap<?, ?>) modelProperty).get(Position.class.getDeclaredFields()[0].getName()) != null) {
            return ((LinkedTreeMap<?, ?>) modelProperty).get(Position.class.getDeclaredFields()[0].getName());
        } else if (((LinkedTreeMap<?, ?>) modelProperty).get(ProcurementArchive.class.getDeclaredFields()[0].getName()) != null) {
            return ((LinkedTreeMap<?, ?>) modelProperty).get(ProcurementArchive.class.getDeclaredFields()[0].getName());
        } else if (((LinkedTreeMap<?, ?>) modelProperty).get(Status.class.getDeclaredFields()[0].getName()) != null) {
            return ((LinkedTreeMap<?, ?>) modelProperty).get(Status.class.getDeclaredFields()[0].getName());
        } else if (((LinkedTreeMap<?, ?>) modelProperty).get(Role.class.getDeclaredFields()[0].getName()) != null) {
            return ((LinkedTreeMap<?, ?>) modelProperty).get(Role.class.getDeclaredFields()[0].getName());
        } else if (((LinkedTreeMap<?, ?>) modelProperty).get(User.class.getDeclaredFields()[0].getName()) != null) {
            return ((LinkedTreeMap<?, ?>) modelProperty).get(User.class.getDeclaredFields()[0].getName());
        }
        return null;
    }

    void initSearchField(){
        FilteredList<Map<String, Object>> filteredData = new FilteredList<>(mainTable.getItems(), p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(row -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                for (Object value : row.values()) {
                    if (value != null) {
                        String cellValue = value.toString().toLowerCase();
                        if (cellValue.contains(lowerCaseFilter)) {
                            return true;
                        }
                    }
                }
                return false;
            });
        });
        mainTable.setItems(filteredData);
    }


    public void changeButtonsColor() {
        ObservableList<Node> headerButtons = hbox.getChildren();
        for (Node button : headerButtons) {
            button.setStyle("-fx-background-color: #5082ff; -fx-text-fill: #fff;");
            button.setOnMouseEntered(mouseEvent -> button.setStyle("-fx-background-color: rgb(72, 92, 255); -fx-text-fill: #fff;"));
            button.setOnMouseExited(mouseEvent -> button.setStyle("-fx-background-color: #5082ff; -fx-text-fill: #fff;"));

        }
        headerButtons.get(appData.getAdminMode()).setStyle("-fx-text-fill: #777; -fx-background-color: #fff");
        headerButtons.get(appData.getAdminMode()).setOnMouseEntered(mouseEvent -> headerButtons.get(appData.getAdminMode()).setStyle("-fx-background-color: #ddd;-fx-text-fill: #777;"));
        headerButtons.get(appData.getAdminMode()).setOnMouseExited(mouseEvent -> headerButtons.get(appData.getAdminMode()).setStyle("-fx-background-color: #fff;-fx-text-fill: #777;"));
    }


}