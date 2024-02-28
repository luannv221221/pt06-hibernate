package com.ra.model.service;

import com.ra.model.dao.CategoryDAO;
import com.ra.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryDAO categoryDAO;
    @Override
    public List<Category> getAll() {
        return categoryDAO.getAll();
    }
    @Override
    public Boolean saveOrUpdate(Category category) {
        return categoryDAO.saveOrUpdate(category);
    }
}
