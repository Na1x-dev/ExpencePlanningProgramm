package com.example.demo1.models;

import lombok.Data;

@Data
public class Position implements Model {
    private Long positionId;

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
