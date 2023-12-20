package com.example.demo1.models;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

@Data
public class Category implements Model {

    Long categoryId;

    private String categoryName;

    public Category() {
        categoryName = "";
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

}
