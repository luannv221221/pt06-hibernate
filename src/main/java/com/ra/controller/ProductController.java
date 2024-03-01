package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/product")
    public String index(Model model){
        List<Product> products = productService.getAll();
        model.addAttribute("products",products);
        return "product/index";
    }
    @GetMapping("/add-product")
    public String add(){
        return "product/add";
    }
}
