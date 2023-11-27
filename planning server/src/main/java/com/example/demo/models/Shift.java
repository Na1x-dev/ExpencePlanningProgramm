package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "shifts")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Shift {
    @Id
    @Column(name = "shift_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long shiftId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "open_date")
    @NonNull
    Date openDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "close_date")
    Date closeDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    @NonNull
    User user;

    public Shift(User user) {
        this.user = user;
        openDate = new Date();
        closeDate = new Date(0);
    }

    public Shift(){}

    public String formatOpenDateForChange() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//yyyy-MM-ddThh:mm
        return format.format(openDate);
    }

    public String getOpenDateInNormalFormat() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm dd.MM.yyyy");
        return format.format(openDate);
    }

    public String formatCloseDateForChange() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(closeDate);
    }

    public String getCloseDateInNormalFormat() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm dd.MM.yyyy");
        return format.format(closeDate);
    }

    public void closeShift() {
        this.closeDate = new Date();
    }
}
