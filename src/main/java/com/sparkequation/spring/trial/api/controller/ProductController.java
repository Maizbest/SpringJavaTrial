package com.sparkequation.spring.trial.api.controller;

import com.sparkequation.spring.trial.api.dto.ProductDto;
import com.sparkequation.spring.trial.api.mapper.ProductMapper;
import com.sparkequation.spring.trial.api.model.Product;
import com.sparkequation.spring.trial.api.service.ProductService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product")
public class ProductController {

  @Autowired
  private ProductService productService;
  @Autowired
  private ProductMapper productMapper;


  @GetMapping
  public ResponseEntity<List<Product>> getProducts() {
    return ResponseEntity.ok(productService.getProducts());
  }

  @GetMapping("{id}")
  public ResponseEntity<Product> getProduct(@PathVariable int id) {
    return ResponseEntity.ok(productService.getProductById(id));
  }

  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductDto productDto) {
    return ResponseEntity.ok(productService.create(productMapper.toEntity(productDto)));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
    productService.deleteProductById(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable int id,
      @RequestBody @Valid ProductDto product) {
    return ResponseEntity.ok(productService.updateProduct(id, productMapper.toEntity(product)));
  }
}
