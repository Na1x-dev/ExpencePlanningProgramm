package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter

@RequiredArgsConstructor
@Table(name = "applications")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Application {
    @Id
    @Column(name = "application_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long applicationId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "open_date")
    @NonNull
    Date openDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "close_date")
    @NonNull
    Date closeDate;

    @Column(name="description")
    @NonNull
    String description;

    @Column(name="comment")
    @NonNull
    String comment;

    @ManyToOne
    @JoinColumn(name = "user_id_open", nullable = false, referencedColumnName = "id")
    @NonNull
    User userOpen;
    @ManyToOne
    @JoinColumn(name = "user_id_close", referencedColumnName = "id")
    User userClose;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false, referencedColumnName = "status_id")
    @NonNull
    Status status;

    public Application() {
        openDate = new Date();
        closeDate = new Date();
        description = "";
        comment = "";
        userOpen = new User();
        status = new Status();
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicationId=" + applicationId +
                ", openDate=" + openDate +
                ", closeDate=" + closeDate +
                ", description='" + description + '\'' +
                ", comment='" + comment + '\'' +
                ", userOpen=" + userOpen +
                ", userClose=" + userClose +
                ", status=" + status +
                '}';
    }

    public String formatOpenDateForChange() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(openDate);
    }

    public String formatCloseDateForChange() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }
//
    public String getOpenDateInNormalFormat() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(openDate);
    }

    public String getCloseDateInNormalFormat() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(closeDate);
    }

}
