package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "supplies")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Supply {
    @Id
    @Column(name = "supply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long supplyId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "supply_date")
    @NonNull
    Date supplyDate;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false, referencedColumnName = "supplier_id")
    @NonNull
    Supplier supplier;

    @ManyToMany
    @JoinTable(
            name = "supplies_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "supply_id"))
    Set<Product> products;

    public Supply() {
    }


    @Override
    public String toString() {
        return "Supply{" +
                "supplyId=" + supplyId +
                ", supplyDate=" + supplyDate +
                ", supplier=" + supplier +
                ", products=" + products +
                '}';
    }

    public String getDateInNormalFormat() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(supplyDate);
    }
}
