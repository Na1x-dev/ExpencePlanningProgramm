package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;


@Getter
@Setter
@JsonIgnoreProperties("hibernateLazyInitializer")
@Entity
@Table(name = "appeals")
public class Appeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appeal_id")
    private Long appealId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "registration_date")
    private Date registrationDate;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "appeal_text")
    private String appealText;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "closing_date")
    private Date closingDate;

    @ManyToOne
    @JoinColumn(name = "closing_user_id", referencedColumnName = "user_id")
    private User closingUser;

    @Column(name = "comment")
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

    public void setAppealId(Long id) {
        appealId = id;
    }
}

