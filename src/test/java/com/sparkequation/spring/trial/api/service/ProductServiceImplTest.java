package com.sparkequation.spring.trial.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sparkequation.spring.trial.api.exception.ProductAlreadyExists;
import com.sparkequation.spring.trial.api.exception.ProductNotFoundException;
import com.sparkequation.spring.trial.api.model.Product;
import com.sparkequation.spring.trial.api.repository.ProductRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

  @InjectMocks
  private ProductServiceImpl service;
  @Mock
  private ProductRepository repository;

  @Test
  void create_productNotExist_expect_created() {
    final Product product = new Product();
    product.setId(1);
    when(repository.save(any(Product.class))).thenAnswer(i -> i.getArgument(0));
    final Product result = service.create(product);
    assertNotNull(result);
    assertEquals(product, result);
  }

  @Test
  void create_productAlreadyExist_expect_exception() {
    final Product product = new Product();
    product.setId(1);
    when(repository.existsById(anyInt())).thenReturn(true);
    assertThrows(ProductAlreadyExists.class, () -> service.create(product));
  }

  @Test
  void getProducts() {
    final Product product = new Product();
    product.setId(1);
    when(repository.findAll()).thenReturn(Collections.singletonList(product));
    final List<Product> products = service.getProducts();
    assertNotNull(products);
    assertFalse(products.isEmpty());
    assertEquals(product, products.get(0));
  }

  @Test
  void getProductById_productExists_expect_returned() {
    final Product product = new Product();
    product.setId(1);
    when(repository.findById(1)).thenReturn(Optional.of(product));
    final Product actual = service.getProductById(1);
    assertNotNull(actual);
    assertEquals(product, actual);
  }

  @Test
  void getProductById_productDoesNotExists_expect_exception() {
    final Product product = new Product();
    product.setId(1);
    when(repository.findById(anyInt())).thenReturn(Optional.empty());
    assertThrows(ProductNotFoundException.class, () -> service.getProductById(1));
  }

  @Test
  void deleteProductById() {
    service.deleteProductById(1);
    verify(repository).deleteById(1);
  }

  @Test
  void updateProduct_exists_expect_ok() {
    final Product product = new Product();
    product.setId(1111111);
    when(repository.existsById(anyInt())).thenReturn(true);
    when(repository.save(any(Product.class))).thenAnswer(i -> i.getArgument(0));
    final Product result = service.updateProduct(1, product);
    assertNotNull(result);
    assertEquals(product.getId(), result.getId());
  }

  @Test
  void updateProduct_notExists_expect_exception() {
    final Product product = new Product();
    product.setId(1);
    when(repository.existsById(anyInt())).thenReturn(false);
    assertThrows(ProductNotFoundException.class, () -> service.updateProduct(1, product));
  }
}