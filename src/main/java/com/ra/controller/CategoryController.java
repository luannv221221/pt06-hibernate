package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        System.out.println(category.getId());
        if(categoryService.saveOrUpdate(category)){
            redirectAttributes.addFlashAttribute("success","Thêm mới thành công");
            return "redirect:/category";
        }
        return "redirect:/add-category";
    }

    @GetMapping("/edit-category/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){
        Category category = categoryService.findById(id);
        model.addAttribute("category",category);
        return "category/edit";
    }
    @PostMapping("/edit-category/{id}")
    public String update(@ModelAttribute("category") Category category,
                         RedirectAttributes redirectAttributes,@PathVariable Integer id){

        if(categoryService.saveOrUpdate(category)){
            redirectAttributes.addFlashAttribute("success","Cap nhat thanh cong");
            return "redirect:/category";
        }
        return "redirect:/edit-category/"+id;
    }

    @GetMapping("/delete-category/{id}")
    public String delete(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        categoryService.delete(id);
        redirectAttributes.addFlashAttribute("success","Xoa thanh cong");
        return "redirect:/category";
    }
}
