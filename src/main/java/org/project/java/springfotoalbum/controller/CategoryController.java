package org.project.java.springfotoalbum.controller;

import org.project.java.springfotoalbum.model.Category;
import org.project.java.springfotoalbum.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        return "/categories/index";
    }


}
