package com.sparkequation.spring.trial.api.controller;

import com.sparkequation.spring.trial.api.model.Category;
import com.sparkequation.spring.trial.api.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;


  @GetMapping
  public ResponseEntity<List<Category>> getProducts() {
    return ResponseEntity.ok(categoryService.getCategories());
  }

}
