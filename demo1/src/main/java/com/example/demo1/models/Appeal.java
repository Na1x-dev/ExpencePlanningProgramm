package com.example.demo1.models;


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
        status = new Status();
        user = new User();
        appealText = "";
        closingDate = new Date();
        closingUser = new User();
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

