package com.example.demo1.models;
import com.example.demo1.AppData;
import lombok.*;
import java.util.Date;
@Getter
@Setter
public class ProcurementArchive implements Model {
    private Long procurementId;

    private String procurementDate;

    private Status status;

    private User user;

    private Order order;

    private String comment;

    public ProcurementArchive(){
        procurementDate = AppData.getInstance().getFormatForServer().format(new Date());
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
