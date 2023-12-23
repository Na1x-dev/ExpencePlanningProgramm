package com.example.server.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@Data
@Table(name = "applications")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long applicationId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "application_date")
    private Date applicationDate;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "create_user_id", referencedColumnName = "user_id")
    private User createUser;

    @ManyToOne
    @JoinColumn(name = "customer_user_id", referencedColumnName = "user_id")
    private User customerUser;

    @ManyToOne
    @JoinColumn(name = "appeal_id", referencedColumnName = "appeal_id")
    private Appeal appeal;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_characteristic")
    private String productCharacteristic;

    @Column(name = "price_for_one")
    private Double priceForOne;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "application_comment")
    private String applicationComment;

    @Column(name = "final_price")
    private Double finalPrice;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "closing_date")
    private Date closingDate;

    @ManyToOne
    @JoinColumn(name = "closing_user_id", referencedColumnName = "user_id")
    private User closingUser;

    @Column(name = "comment")
    private String comment;


    public Application() {
        applicationDate = new Date();
        closingDate = new Date();
        productName = "";
        productCharacteristic = "";
        priceForOne = 0.0;
        amount = 0;
        applicationComment = "";
        finalPrice = amount * priceForOne;
        comment = "";
        status = new Status();
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicationId=" + applicationId +
                ", applicationDate=" + applicationDate +
                ", status=" + status +
                ", createUser=" + createUser +
                ", customerUser=" + customerUser +
                ", appeal=" + appeal +
                ", category=" + category +
                ", productName='" + productName + '\'' +
                ", productCharacteristic='" + productCharacteristic + '\'' +
                ", priceForOne=" + priceForOne +
                ", amount=" + amount +
                ", applicationComment='" + applicationComment + '\'' +
                ", finalPrice=" + finalPrice +
                ", closingDate=" + closingDate +
                ", closingUser=" + closingUser +
                ", comment='" + comment + '\'' +
                '}';
    }

    public void setApplicationId(Long id) {
        applicationId = id;
    }

//    public String formatOpenDateForChange() {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        return format.format(openDate);
//    }
//
//    public String formatCloseDateForChange() {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        return format.format(new Date());
//    }
////
//    public String getOpenDateInNormalFormat() {
//        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
//        return format.format(openDate);
//    }
//
//    public String getCloseDateInNormalFormat() {
//        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
//        return format.format(closeDate);
//    }

}
