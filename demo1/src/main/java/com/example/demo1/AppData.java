package com.example.demo1;

import com.example.demo1.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;

@Data
public class AppData {
    private static AppData instance = null;
    private User user;
    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.create();

    private AppData() {
    }

    public static AppData getInstance() {
        if (instance == null) {
            instance = new AppData();
        }
        return instance;
    }

}