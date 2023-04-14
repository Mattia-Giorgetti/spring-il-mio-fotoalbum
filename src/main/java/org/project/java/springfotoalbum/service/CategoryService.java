package org.project.java.springfotoalbum.service;

import org.project.java.springfotoalbum.exceptions.CategoryNotFoundException;
import org.project.java.springfotoalbum.model.Category;
import org.project.java.springfotoalbum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id){
       return categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("Category Not Found"));
    }
    public Category create(Category formCategory){
        Category newCategory = new Category();
        newCategory.setName(formCategory.getName());
        newCategory.setDescription(formCategory.getDescription());
        return categoryRepository.save(formCategory);
    }

    public Category update(Category formCategory, Integer id){
        Category catToUpdate = getCategoryById(id);
        catToUpdate.setName(formCategory.getName());
        catToUpdate.setDescription(formCategory.getDescription());
        return categoryRepository.save(catToUpdate);
    }

    public boolean delete(Integer id){
        categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("Category Not Found"));
        try {
            categoryRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
