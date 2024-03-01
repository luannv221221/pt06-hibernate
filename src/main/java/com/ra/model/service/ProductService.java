package com.ra.model.service;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Boolean saveOrUpdate(Product product);
    Product findById(Integer id);
    void delete(Integer id);
}
