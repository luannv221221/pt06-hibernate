package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.service.CategoryService;
import com.ra.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Controller
@PropertySource("classpath:application.properties")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Value("${path}")
    private String path;
    @GetMapping("/product")
    public String index(Model model){
        List<Product> products = productService.getAll();
        model.addAttribute("products",products);
        return "product/index";
    }
    @GetMapping("/add-product")
    public String add(Model model,Product product){
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories",categories);
        model.addAttribute("product",product);
        return "product/add";
    }
    @PostMapping("/add-product")
    public String create(@ModelAttribute("product") Product product,
                         @RequestParam("fileImage") MultipartFile file){
        // upload file
        String fileName = file.getOriginalFilename();
        File destination = new File(path+"/"+fileName);
        try {
            Files.write(destination.toPath(),file.getBytes(), StandardOpenOption.CREATE);
            product.setImage(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // thêm sản phẩm
        System.out.println(product.toString());
        productService.saveOrUpdate(product);
        return "redirect:/product";
    }
}
