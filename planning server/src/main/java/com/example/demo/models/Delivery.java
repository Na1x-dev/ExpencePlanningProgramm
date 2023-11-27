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

@Entity
@Getter
@Setter
@Table(name = "deliveries")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Delivery {
    @Id
    @Column(name = "delivery_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long deliveryId;

    @NonNull
    @Column(name = "city")
    String city;

    @NonNull
    @Column(name = "street")
    String street;

    @NonNull
    @Column(name = "house")
    Integer house;

    @NonNull
    @Column(name = "client_name")
    String clientName;

    @NonNull
    @Column(name = "client_phone")
    String clientPhone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "delivery_date")
    @NonNull
    Date deliveryDate;


    @ManyToOne
    @JoinColumn(name = "courier_id", nullable = false, referencedColumnName = "courier_id")
    @NonNull
    Courier courier;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @NonNull
    List<Order> orders;


    public Delivery() {
        orders = new ArrayList<>();
    }

    //    public String getIdAndName() {
//        if (!surname.equals("") || !name.equals("") || !patronymic.equals(""))
//            return unionMemberId + ". " + surname + " " + name.charAt(0) + "." + patronymic.charAt(0) + ".";
//        return "";
//    }
//
    public String getDateInNormalFormat() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(deliveryDate);
    }
//
    public String formatDateForChange() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(deliveryDate);
    }
//
//


    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryId=" + deliveryId +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house=" + house +
                ", clientName='" + clientName + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", courier=" + courier +
                ", orders=" + orders +
                '}';
    }
}
