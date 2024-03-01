package com.ra.model.dao;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getAll();
    Boolean saveOrUpdate(Product product);
    Product findById(Integer id);
    void delete(Integer id);
}
