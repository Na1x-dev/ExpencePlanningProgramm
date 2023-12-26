package com.example.demo1.pages.executor;

import java.lang.reflect.Type;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.demo1.AppData;
import com.example.demo1.DialogBox;
import com.example.demo1.RequestsBuilder;
import com.example.demo1.models.Appeal;
import com.example.demo1.models.Application;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private Button logOutButton;

    @FXML
    private Button manualButton;

    @FXML
    private HBox hbox;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableColumn<Appeal, Long> appealId;

    @FXML
    private TableView<Appeal> appealsTable;

    @FXML
    private TableColumn<Appeal, String> closeDate;

    @FXML
    private TableColumn<Appeal, String> comment;

    @FXML
    private TableColumn<Appeal, String> registrationDate;

    @FXML
    private TableColumn<Appeal, String> status;

    @FXML
    private TableColumn<Appeal, String> text;

    @FXML
    private Button createAppealButton;
    @FXML
    private Label userNameLabel;

    AppData appData = AppData.getInstance();

    @FXML
    void logOut(ActionEvent event) {
        AppData.toNextStage("login.fxml", logOutButton, "login");
    }

    @FXML
    void toAppealsTable(ActionEvent event) {
//        AppData.toNextStage("executor/AdminPage.fxml", AppealsButton, "Executor Page");
    }

    @FXML
    void toApplicationsTable(ActionEvent event) {
        AppData.toNextStage("executor/ApplicationsPage.fxml", ApplicationsButton, "Executor Page");
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
    void toCreateAppeal(ActionEvent actionEvent) {
        AppData.toNextStage("executor/CreateAppealPage.fxml", createAppealButton, "Executor Page");
    }

    @FXML
    void initialize() {
        AppealsButton.setStyle("-fx-background-color: #fff; -fx-text-fill: #555");
        appealsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        getAppeals();
        initTableColumns();
        userNameLabel.setText(appData.getUser().getUserName());
    }

    public void getAppeals() {
        HttpResponse<String> response = RequestsBuilder.getRequest("/admin/getAll/appeal");
        Type listType = new TypeToken<ArrayList<Appeal>>() {
        }.getType();
        List<Appeal> appeals = appData.getGson().fromJson(response.body(), listType);
        for (Appeal appeal : appeals) {
            appealsTable.getItems().add(appeal);
        }
    }

    public void initTableColumns() {
        appealId.setCellValueFactory(new PropertyValueFactory<>("appealId"));
        closeDate.setCellValueFactory(cellData -> {
            Appeal appeal = cellData.getValue();
            if (appeal.getClosingDate() == null) {
                return new SimpleStringProperty("");
            }
            return new SimpleStringProperty(appData.getDateForClient(appeal.getClosingDate()));
        });
        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        registrationDate.setCellValueFactory(cellData -> {
            Appeal appeal = cellData.getValue();
            return new SimpleStringProperty(appData.getDateForClient(appeal.getRegistrationDate()));
        });
        status.setCellValueFactory(cellData -> {
            Appeal appeal = cellData.getValue();
            String statusName = appeal.getStatus().getStatusName();
            return new SimpleStringProperty(statusName);
        });
        status.setCellFactory(column -> {
            return new TableCell<Appeal, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(item);
                        ContextMenu contextMenu = new ContextMenu();
                        MenuItem menuItem1 = new MenuItem("Создать запрос");
                        menuItem1.setOnAction(actionEvent -> {
//                            Alert alert = new Alert(Alert.AlertType.ERROR);
//                            alert.setTitle("Ошибка");
//                            alert.setHeaderText("Ошибка");
//                            alert.setContentText("Недостаточное финансирование для создания заявки.");
//                            alert.showAndWait();

                            AppData.toNextStage("executor/NewApplicationPage.fxml", logOutButton, "Executor Page");
                        });
                        MenuItem menuItem4 = new MenuItem("Закрыть");
                        menuItem4.setOnAction(actionEvent -> {
                            appData.setPutModelId(getTableView().getItems().get(getIndex()).getAppealId());
                            DialogBox dialogBox = new DialogBox("Вы действительно хотите закрыть обращение с Id=" + appData.getPutModelId());
                            dialogBox.showAndWait();
                            if (dialogBox.getResult() == DialogBox.Result.OK) {
//                                HttpResponse<String> response = RequestsBuilder.deleteRequest("/admin/delete/" + modelClass.getSimpleName().toLowerCase() + "/" + id);
//                                getTableView().getItems().clear();
//                                responseIntoTable();

                                AppData.toNextStage("executor/AppealCommentPage.fxml", logOutButton, "Executor Page");
                            }
                        });
                        contextMenu.getItems().addAll(menuItem1, menuItem4);
                        setContextMenu(contextMenu);
                    }
                }
            };
        });

        text.setCellValueFactory(new PropertyValueFactory<>("appealText"));
    }

}

