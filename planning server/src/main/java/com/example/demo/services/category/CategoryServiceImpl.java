package com.example.demo.services.category;


import com.example.demo.repositories.category.CategoryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.Category;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryJpaRepository categoryJpaRepository;

    @Override
    public Category create(Category category) {
        return categoryJpaRepository.save(category);
    }

    @Override
    public List<Category> readAll() {
        return categoryJpaRepository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        if (categoryJpaRepository.existsById(id)) {
            categoryJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, Category category) {
        if (categoryJpaRepository.existsById(id)) {
            category.setCategoryId(id);
            categoryJpaRepository.save(category);
            return true;
        }
        return false;
    }


}
