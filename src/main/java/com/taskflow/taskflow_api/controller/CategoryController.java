package com.taskflow.taskflow_api.controller;

import com.taskflow.taskflow_api.entity.Category;
import com.taskflow.taskflow_api.service.CategoryService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

//    GET /categories
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

//    POST /categories
    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category){
        Category savedCategory = categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }
}
