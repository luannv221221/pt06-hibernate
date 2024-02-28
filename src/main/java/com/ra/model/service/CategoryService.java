package com.ra.model.service;

import com.ra.model.entity.Category;

import java.util.List;

public interface CategoryService{
    List<Category> getAll();
    Boolean saveOrUpdate(Category category);
}
