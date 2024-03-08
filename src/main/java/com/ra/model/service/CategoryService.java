package com.ra.model.service;

import com.ra.model.entity.Category;

import java.util.List;

public interface CategoryService{
    List<Category> getAll();
    Boolean saveOrUpdate(Category category);

    Category findById(Integer id);
    void delete(Integer id);
    Boolean checkNameExits(String string);
    List<Category> pagination(Integer noPage,Integer limit);
    List<Category> search(Integer noPage,Integer limit,String name);
    int getTotalPage();
}
