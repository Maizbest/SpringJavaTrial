package com.sparkequation.spring.trial.api.service;

import com.sparkequation.spring.trial.api.model.Category;
import com.sparkequation.spring.trial.api.repository.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepository repository;

  @Override
  public List<Category> getCategories() {
    return repository.findAll();
  }
}
