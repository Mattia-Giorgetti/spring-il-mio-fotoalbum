package org.project.java.springfotoalbum.controller;

import jakarta.validation.Valid;
import org.project.java.springfotoalbum.exceptions.CategoryNotFoundException;
import org.project.java.springfotoalbum.model.Category;
import org.project.java.springfotoalbum.model.FlashMessage;
import org.project.java.springfotoalbum.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("category", new Category());
        return "/categories/index";
    }

    @PostMapping("/store")
    public String store(@Valid @ModelAttribute(name = "category") Category formCategory, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCategories());
            return "/categories/index";
        }
        categoryService.create(formCategory);
        redirectAttributes.addFlashAttribute("message", new FlashMessage(FlashMessage.FleshMessageType.SUCCESS,"New Category created"));
        return "redirect:/categories";
    }






    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        try {
            Category category = categoryService.getCategoryById(id);
            model.addAttribute("category", category);
            return "/categories/edit";
        } catch (CategoryNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("category") Category formCategory, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/categories/edit";
        }
        try {
            Category categoryToUpdate = categoryService.update(formCategory, id);
            return "redirect:/categories";
        } catch (CategoryNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        try {
            boolean success= categoryService.delete(id);
            if (success){
                redirectAttributes.addFlashAttribute("message", new FlashMessage(FlashMessage.FleshMessageType.SUCCESS, "Category Deleted"));
            } else {
                redirectAttributes.addFlashAttribute("message", new FlashMessage(FlashMessage.FleshMessageType.ERROR, "Unable to delete this Category"));
            }
        } catch (CategoryNotFoundException e){
            redirectAttributes.addFlashAttribute("message", new FlashMessage(FlashMessage.FleshMessageType.ERROR, "Category with id: " + id + "not found"));
        }
        return "redirect:/categories";
    }


}
