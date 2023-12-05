package com.example.server.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;


@JsonIgnoreProperties("hibernateLazyInitializer")
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "create_user_id", referencedColumnName = "user_id")
    private User createUser;

    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "application_id")
    private Application application;

    @Column(name = "procurement_organization")
    private String procurementOrganization;

    @Column(name = "unp")
    private Integer unp;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "vat")
    private Integer vat;

    @Column(name = "price_with_vat")
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

    public void setOrderId(Long id) {
        orderId = id;
    }
}