package com.example.server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "categories")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Category {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long categoryId;

    @Column(name = "category_name")
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

    public void setCategoryId(Long id) {
    categoryId = id;
    }
}
