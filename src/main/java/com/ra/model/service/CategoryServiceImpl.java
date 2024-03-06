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

    @Override
    public Category findById(Integer id) {
        return categoryDAO.findById(id);
    }

    @Override
    public void delete(Integer id) {
        categoryDAO.delete(id);
    }

    @Override
    public Boolean checkNameExits(String name) {
        return categoryDAO.checkNameExits(name);
    }

    @Override
    public List<Category> pagination(Integer noPage,Integer limit) {
        return categoryDAO.pagination(noPage,limit);
    }
}
