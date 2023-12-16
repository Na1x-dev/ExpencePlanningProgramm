package com.example.demo1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.example.demo1.models.Status;
import com.example.demo1.models.User;
import com.example.demo1.models.Role;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.Data;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@Data
public class AppData {
    private static AppData instance = null;
    private User user;
    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.create();
    private List<Status> statuses;
    private List<Role> roles;
    SimpleDateFormat formatForServer;
    SimpleDateFormat formatForClient;

    private AppData() {
       formatForServer = new SimpleDateFormat("yyyy-MM-dd");
       formatForClient = new SimpleDateFormat("dd.MM.yyyy");
    }

    public Date getDateFromString(String strDate){
        try {
            return formatForServer.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDateForClient(String serverDate){
        return formatForClient.format(getDateFromString(serverDate));
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
        stage.show();
    }

    public Status findStatus(String statusName) {
        for(Status status : statuses){
            if(status.getStatusName().equals(statusName)){
                return status;
            }
        }
        return null;
    }
}