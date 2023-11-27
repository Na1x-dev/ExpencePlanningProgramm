package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@JsonIgnoreProperties("hibernateLazyInitializer")
@Entity
@Table(name = "procurement_archive")
public class ProcurementArchive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "procurement_id")
    private Integer procurementId;

    @Column(name = "procurement_date")

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date procurementDate;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "comment")
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

    // Геттеры и сеттеры

//    public String getDateInNormalFormat() {
//        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
//        return format.format(bestBeforeDate);
//    }
//    public String formatDateForChange() {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        return format.format(bestBeforeDate);
//    }


}
