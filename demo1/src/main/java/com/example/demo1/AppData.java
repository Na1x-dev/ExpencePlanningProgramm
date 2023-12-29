package com.example.demo1;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.example.demo1.models.*;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class AppData {
    private static AppData instance = null;
    private User user;
    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
    private List<Status> statuses;
    private List<Role> roles;
    SimpleDateFormat formatForServer;
    SimpleDateFormat formatForClient;
    SimpleDateFormat formatForAdmin;
    private List<Class> modelsList;
    private List<Class> fullModelsList;
    private int adminMode = 0;
    private Long putModelId;

    private AppData() {
        formatForServer = new SimpleDateFormat("yyyy-MM-dd");
        formatForClient = new SimpleDateFormat("dd.MM.yyyy");
//        formatForAdmin = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        formatForAdmin = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        initModelsList();
        initFullModelsList();
    }

    public boolean isValidDate(String date) {
        try {
            formatForAdmin.setLenient(false);
            Date parsedDate = formatForAdmin.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void initModelsList() {
        modelsList = new ArrayList<>();
        modelsList.add(Appeal.class);
        modelsList.add(com.example.demo1.models.Application.class);
        modelsList.add(Budget.class);
        modelsList.add(Category.class);
        modelsList.add(Department.class);
        modelsList.add(Management.class);
        modelsList.add(Order.class);
        modelsList.add(Position.class);
        modelsList.add(ProcurementArchive.class);
        modelsList.add(User.class);
    }

    private void initFullModelsList() {
        fullModelsList = new ArrayList<>();
        fullModelsList.add(Appeal.class);
        fullModelsList.add(com.example.demo1.models.Application.class);
        fullModelsList.add(Budget.class);
        fullModelsList.add(Category.class);
        fullModelsList.add(Department.class);
        fullModelsList.add(Management.class);
        fullModelsList.add(Order.class);
        fullModelsList.add(Position.class);
        fullModelsList.add(ProcurementArchive.class);
        fullModelsList.add(User.class);
        fullModelsList.add(Role.class);
        fullModelsList.add(Status.class);
    }

     public Date getDateFromString(String strDate, SimpleDateFormat format) {
        try {
            return format.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDateForClient(String serverDate) {
        return formatForClient.format(getDateFromString(serverDate, formatForServer));
    }

    public String getDateForAdmin(String adminDate) {
        return formatForClient.format(getDateFromString(adminDate, formatForAdmin));
    }

    public static AppData getInstance() {
        if (instance == null) {
            instance = new AppData();
        }
        return instance;
    }

    public static void toNextStage(String fxmlFile, Button button, String pageTitle) {
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(AppData.class.getResource(fxmlFile));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle(pageTitle);
        Scene scene = new Scene(root1);
        String css = AppData.class.getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        stage.show();
    }

    public Status findStatus(String statusName) {
        for (Status status : statuses) {
            if (status.getStatusName().equals(statusName)) {
                return status;
            }
        }
        return null;
    }

    public boolean checkFields(List<TextField> fields, Label resultLabel) {
        boolean checkFlag = true;
        int emptyFieldsCounter = 0;
        for (TextField field : fields) {
            if (field.getText().isEmpty()) {
                checkFlag = false;
                field.setStyle("-fx-border-color: rgb(222,27,63)");
                emptyFieldsCounter++;
            } else {
                field.setStyle("-fx-border-color: #5082ff");
            }
        }
        if (emptyFieldsCounter == 1) {
            resultLabel.setText("Поле должно быть заполнено");
        } else if (emptyFieldsCounter > 1) {
            resultLabel.setText("Поля должны быть заполнены");
        }
        return checkFlag;
    }

    public boolean checkComboBoxes(List<ComboBox> boxes){
        boolean checkFlag = true;
        for (ComboBox comboBox : boxes) {
            if (comboBox.getValue() == null) {
                checkFlag = false;
                comboBox.setStyle("-fx-border-color: rgb(222,27,63)");
            } else {
                comboBox.setStyle("-fx-border-color: #5082ff");
            }
        }
        return checkFlag;
    }

    public List<TextField> getFields(Node pane) {
        List<TextField> fields = new ArrayList<>();
        Set<Node> nodes = pane.lookupAll(".text-field");
        for (Node node : nodes) {
            if (node instanceof TextField) {
                fields.add((TextField) node);
            }
        }
        return fields;
    }

    public List<ComboBox> getBoxes(Node pane) {
        List<ComboBox> boxes = new ArrayList<>();
        Set<Node> nodes = pane.lookupAll(".combo-box");
        for (Node node : nodes) {
            if (node instanceof ComboBox<?>) {
                boxes.add((ComboBox) node);
            }
        }
        return boxes;
    }

    public List<TextArea> getAreas(Node pane) {
        List<TextArea> areas = new ArrayList<>();
        Set<Node> nodes = pane.lookupAll(".text-area");
        for (Node node : nodes) {
            if (node instanceof TextArea) {
                areas.add((TextArea) node);
            }
        }
        return areas;
    }

    public boolean checkAreas(List<TextArea> areas, Label resultLabel) {
        boolean checkFlag = true;
        int emptyAreasCounter = 0;
        for (TextArea area : areas) {
            if (area.getText().isEmpty()) {
                checkFlag = false;
                area.setStyle("-fx-border-color: rgb(222,27,63)");
                emptyAreasCounter++;
            } else {
                area.setStyle("-fx-border-color: #5082ff");
            }
        }
        if (emptyAreasCounter == 1) {
            resultLabel.setText("Поле должно быть заполнено");
        } else if (emptyAreasCounter > 1) {
            resultLabel.setText("Поля должны быть заполнены");
        }
        return checkFlag;
    }
}