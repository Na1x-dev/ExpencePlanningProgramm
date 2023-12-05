package com.example.demo1.models;

import java.util.Date;

import lombok.Data;


@Data
public class Order {
    private Long orderId;

    private Date orderDate;

    private Status status;

    private User createUser;

    private Application application;

    private String procurementOrganization;

    private Integer unp;

    private String contactPerson;

    private String contactNumber;

    private Integer vat;

    private Double priceWithVat;

    public Order(){
        orderDate = new Date();
        status = new Status();
        createUser = new User();
        application = new Application();
        procurementOrganization = "";
        unp = 111111111;
        contactPerson = "";
        contactNumber = "+375290000000";
        vat = 0;
//        priceWithVat = vat* application.getFinalPrice() + application.getFinalPrice();
    priceWithVat = 0.0;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", status=" + status +
                ", createUser=" + createUser +
                ", application=" + application +
                ", procurementOrganization='" + procurementOrganization + '\'' +
                ", unp=" + unp +
                ", contactPerson='" + contactPerson + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", vat=" + vat +
                ", priceWithVat=" + priceWithVat +
                '}';
    }

}