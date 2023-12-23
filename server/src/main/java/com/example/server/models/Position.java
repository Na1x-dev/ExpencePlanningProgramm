package com.example.server.models;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long positionId;

    @Column(name = "position_name")
    private String positionName;

    public Position(){
        positionName = "";
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionId=" + positionId +
                ", positionName='" + positionName + '\'' +
                '}';
    }
}
