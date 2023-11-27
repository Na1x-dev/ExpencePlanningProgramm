package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "positions")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Position {
    @Id
    @Column(name = "position_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long positionId;

    @Column(name = "title")
    @NonNull
    String title;

    public Position(){
        title = "";
    }

//    public Position(String positionTitle) {
//        this.positionTitle=positionTitle;
//    }
}
