package com.sparkequation.spring.trial.api.service;

import com.sparkequation.spring.trial.api.exception.ProductAlreadyExists;
import com.sparkequation.spring.trial.api.exception.ProductNotFoundException;
import com.sparkequation.spring.trial.api.model.Product;
import com.sparkequation.spring.trial.api.repository.ProductRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Override
  public Product create(Product product) {
    if (!productRepository.existsById(product.getId())) {
      return productRepository.save(product);
    }
    throw new ProductAlreadyExists();
  }

  @Override
  public List<Product> getProducts() {
    return productRepository.findAll();
  }

  @Override
  public Product getProductById(int id) {
    return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
  }

  @Override
  public void deleteProductById(int id) {
    productRepository.deleteById(id);
  }

  @Override
  public Product updateProduct(int id, Product product) {
    product.setId(id);
    if (productRepository.existsById(id)) {
      return productRepository.save(product);
    }
    throw new ProductNotFoundException();
  }

}
