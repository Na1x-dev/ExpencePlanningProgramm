package com.example.demo1.models;
import lombok.*;
import java.util.Date;
@Getter
@Setter
public class ProcurementArchive {
    private Long procurementId;

    private Date procurementDate;

    private Status status;

    private User user;

    private Order order;

    private String comment;

    public ProcurementArchive(){
        procurementDate = new Date();
        status = new Status();
        user = new User();
        order = new Order();
        comment = "";
    }

    @Override
    public String toString() {
        return "ProcurementArchive{" +
                "procurementId=" + procurementId +
                ", procurementDate=" + procurementDate +
                ", status=" + status +
                ", user=" + user +
                ", order=" + order +
                ", comment='" + comment + '\'' +
                '}';
    }

}
