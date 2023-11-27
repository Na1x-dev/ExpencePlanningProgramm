package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
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

    @Column(name = "title")
    @NonNull
    String title;

    public Category() {
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", title='" + title + '\'' +
                '}';
    }
}
