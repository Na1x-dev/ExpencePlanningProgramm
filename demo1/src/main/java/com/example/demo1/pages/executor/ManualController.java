package com.example.demo1.pages.executor;

import java.net.URL;
import java.time.format.TextStyle;
import java.util.ResourceBundle;

import com.example.demo1.AppData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.fxmisc.richtext.StyledTextArea;
import org.fxmisc.richtext.model.Paragraph;
import org.fxmisc.richtext.model.StyleSpans;

public class ManualController {
    @FXML
    public HBox hbox2;

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
    private Label userNameLabel;
    AppData appData = AppData.getInstance();

    @FXML
    void toAppealsTable(ActionEvent event) {
        AppData.toNextStage("executor/AppealsPage.fxml", AppealsButton, "Executor Page");
    }

    @FXML
    void toApplicationsTable(ActionEvent event) {
        AppData.toNextStage("executor/ApplicationsPage.fxml", ApplicationsButton, "Executor Page");
    }

    @FXML
    void toManual(ActionEvent event) {
//        AppData.toNextStage("executor/ManualPage.fxml", manualButton, "Executor Page");
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
        manualButton.setStyle("-fx-background-color: #fff; -fx-text-fill: #555");
        userNameLabel.setText(appData.getUser().getUserName());

        Image image1 = new Image("manual1.png");
        ImageView imageView1 = new ImageView(image1);
        Image image2 = new Image("manual2.png");
        HBox.setHgrow(imageView1, Priority.ALWAYS);
        ImageView imageView2 = new ImageView(image2);
        HBox.setHgrow(imageView2, Priority.ALWAYS);
        hbox2.getChildren().addAll(imageView1, imageView2);
        hbox2.setAlignment(Pos.CENTER);
//        hbox2.setSpacing(10);
    }

}
