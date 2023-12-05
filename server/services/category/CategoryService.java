package com.example.server.services.category;

import com.example.server.models.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category);

    List<Category> readAll();


    boolean delete(Long id);

    boolean update(Long id, Category category);
}
