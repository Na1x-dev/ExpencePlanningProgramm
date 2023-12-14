package com.example.demo1.models;


import com.example.demo1.AppData;
import lombok.Data;

import java.util.*;


@Data
public class Appeal {

    private Long appealId;

    private Date registrationDate;

    private Status status;

    private User user;

    private String appealText;

    private Date closingDate;

    private User closingUser;

    private String comment;

    public Appeal(){
        registrationDate = new Date();
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

}

