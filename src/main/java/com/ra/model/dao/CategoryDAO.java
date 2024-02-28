package com.ra.model.dao;

import com.ra.model.entity.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getAll();
    Boolean saveOrUpdate(Category category);
}
