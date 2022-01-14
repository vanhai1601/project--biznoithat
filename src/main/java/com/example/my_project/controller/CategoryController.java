package com.example.my_project.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.my_project.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.my_project.dto.CategoryDTO;
import com.example.my_project.entity.Category;
import com.example.my_project.service.CategoryService;
import com.example.my_project.tranform.CategoryTranform;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/categorys")
public class CategoryController extends BaseController{
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public List<CategoryDTO> allCategorys() {
        CategoryTranform categoryTranform = new CategoryTranform();
        List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
        List<Category> categories = categoryService.findAll();
        for (Category c : categories) {
            CategoryDTO categoryDTO = categoryTranform.apply(c);
            categoryDTOs.add(categoryDTO);
        }
        return categoryDTOs;
    }

    @PostMapping
    public Category insertCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryTranform categoryTranform = new CategoryTranform();
        categoryService.insert(categoryTranform.apply(categoryDTO));
        return categoryTranform.apply(categoryDTO);
    }

    @GetMapping("/{id}")
    @Cacheable(value = "categories", key = "#id")
    public CategoryDTO getCategoryById(@PathVariable(value = "id") Long id){
        CategoryTranform categoryTranform = new CategoryTranform();
        Category category = categoryService.findById(id);
        CategoryDTO categoryDTO = categoryTranform.apply(category);
        return categoryDTO;
    }

    @PutMapping("/{id}")
    @CachePut(value = "categories", key = "#id")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO dto, @PathVariable Long id) {
        CategoryTranform categoryTranform = new CategoryTranform();
        Category category = categoryService.findById(id);
        categoryTranform.apply(category, dto);
        categoryService.update(category);
        CategoryDTO categoryDTO = categoryTranform.apply(category);
        return categoryDTO;
    }

    @DeleteMapping()
    public String deleteCategory(@RequestParam(value = "ids") String ids) {
        String[] arrs = ids.split(",");
        for (String id : arrs) {
            try {
                if (!id.isEmpty()) {
                    categoryService.delete(Long.valueOf(id.trim()));
                }
            } catch (NumberFormatException e) {
                return "Input không hợp lệ";
            }
        }
        return "ok";
    }

}
