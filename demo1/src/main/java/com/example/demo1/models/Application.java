package com.example.demo1.models;


import com.example.demo1.AppData;
import lombok.Data;

import java.util.Date;

@Data
public class Application implements Model {


    private Long applicationId;

    private String applicationDate;

    private Status status;

    private User createUser;

    private User customerUser;

    private Appeal appeal;

    private Category category;

    private String productName;

    private String productCharacteristic;

    private Double priceForOne;

    private Integer amount;

    private String applicationComment;

    private Double finalPrice;

    private String closingDate;

    private User closingUser;

    private String comment;


    public Application() {
        applicationDate = AppData.getInstance().getFormatForServer().format(new Date());
        closingDate = AppData.getInstance().getFormatForServer().format(new Date());
        createUser = new User();
        customerUser = new User();
        appeal = new Appeal();
        category = new Category();
        productName = "";
        productCharacteristic = "";
        priceForOne = 0.0;
        amount = 0;
        applicationComment = "";
        finalPrice = amount * priceForOne;
        closingUser = new User();
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
