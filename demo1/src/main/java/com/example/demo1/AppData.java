package com.example.demo1;

import com.example.demo1.models.Status;
import com.example.demo1.models.User;
import com.example.demo1.models.Role;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.Data;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Data
public class AppData {
    private static AppData instance = null;
    private User user;
    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.create();
    private List<Status> statuses;
    private List<Role> roles;

    private AppData() {
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
        stage.setScene(new Scene(root1));
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