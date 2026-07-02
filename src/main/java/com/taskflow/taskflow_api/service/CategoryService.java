package com.taskflow.taskflow_api.service;

import com.taskflow.taskflow_api.entity.Category;
import com.taskflow.taskflow_api.exception.ResourceNotFoundException;
import com.taskflow.taskflow_api.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

//    GetAll
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

//    GetById
    public Category findById (Long id){
        return categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category not found with id " + id));
    }

//    Post
    public Category save (Category category){
        return categoryRepository.save(category);
    }
}
