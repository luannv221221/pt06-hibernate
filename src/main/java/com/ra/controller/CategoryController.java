package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/category")
    public String index(Model model){
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories",categories);
        return "category/index";
    }
    @GetMapping("/add-category")
    public String add(Model model){
        Category category = new Category();
        model.addAttribute("category",category);
        return "category/add";
    }
    @PostMapping("/add-category")
    public String create(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes){
        if(categoryService.saveOrUpdate(category)){
            redirectAttributes.addFlashAttribute("success","Thêm mới thành công");
            return "redirect:/category";
        }
        return "redirect:/add-category";
    }
}
