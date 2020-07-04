package com.sparkequation.spring.trial.api.service;

import com.sparkequation.spring.trial.api.model.Product;
import java.util.List;

public interface ProductService {

  Product create(Product product);

  List<Product> getProducts();

  Product getProductById(int id);

  void deleteProductById(int id);

  Product updateProduct(int id, Product product);

}
