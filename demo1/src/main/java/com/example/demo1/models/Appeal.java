package com.example.demo1.models;


import com.example.demo1.AppData;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


@Data
public class Appeal implements Model{

    private Long appealId;

    private String registrationDate;

    private Status status;

    private User user;

    private String appealText;

    private String closingDate;

    private User closingUser;

    private String comment;

    public Appeal(){
        registrationDate = AppData.getInstance().getFormatForServer().format(new Date());
        status = AppData.getInstance().findStatus("создано");
        user = AppData.getInstance().getUser();
        appealText = "";
        closingDate = null;
        closingUser = null;
        comment = "";
    }

    @Override
    public String toString() {
        return "Appeal{" +
                "appealId=" + appealId +
                ", registrationDate=" + registrationDate +
                ", status=" + status +
                ", user=" + user +
                ", appealText='" + appealText + '\'' +
                ", closingDate=" + closingDate +
                ", closingUser=" + closingUser +
                ", comment='" + comment + '\'' +
                '}';
    }


    @Override
    public String getRussianField(String fieldName) {
        Map<String, String> fields = new HashMap<>();
        fields.put("appealId","Id обращения");
        fields.put("registrationDate","Дата регистрации");
        fields.put("status","Статус");
        fields.put("user","Пользователь-создатель");
        fields.put("appealText","Текст обращения");
        fields.put("closingDate","Дата закрытия");
        fields.put("closingUser","Закрывший пользователь");
        fields.put("comment","Комментарий");
        return null;
    }
}

