package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/category")
    public String index(Model model,
                        @RequestParam(value = "page",defaultValue = "1") Integer noPage,
                        @RequestParam(value = "limit",defaultValue = "4") Integer limit,
                        @RequestParam(value = "keyword",required = false) String keyword){
        List<Category> categories = new ArrayList<>();

        if(keyword != null){
             categories = categoryService.search(noPage,limit,keyword);
             model.addAttribute("keyword",keyword);
        } else {
            categories = categoryService.pagination(noPage,limit);
        }
        float totalPage = (float) categoryService.getTotalPage() / limit;
        model.addAttribute("categories",categories);
        model.addAttribute("totalPage",Math.ceil(totalPage));
        model.addAttribute("curentpage",noPage);
        return "category/index";
    }
    @GetMapping("/add-category")
    public String add(Model model){
        Category category = new Category();
        model.addAttribute("category",category);
        return "category/add";
    }
    @PostMapping("/add-category")
    public String create(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult){

        if(categoryService.checkNameExits(category.getName())){
            bindingResult.rejectValue("name","category.exists",category.getName()+" Đã tồn tại");
        }
        if(bindingResult.hasErrors()){

           return "category/add";
       }
        if(categoryService.saveOrUpdate(category)){
//            redirectAttributes.addFlashAttribute("success","Thêm mới thành công");
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
