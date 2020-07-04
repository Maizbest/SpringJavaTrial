package com.sparkequation.spring.trial.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparkequation.spring.trial.api.dto.ProductDto;
import com.sparkequation.spring.trial.api.exception.ProductNotFoundException;
import com.sparkequation.spring.trial.api.mapper.ProductMapper;
import com.sparkequation.spring.trial.api.model.Brand;
import com.sparkequation.spring.trial.api.model.Category;
import com.sparkequation.spring.trial.api.model.Product;
import com.sparkequation.spring.trial.api.service.ProductService;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {ProductController.class})
@ComponentScan(basePackageClasses = {ProductMapper.class})
class ProductControllerIT {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private ProductService service;
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void getProducts() throws Exception {
    final Product product = new Product();
    product.setId(123);
    when(service.getProducts()).thenReturn(Collections.singletonList(product));
    mockMvc.perform(get("/api/product"))
        .andExpect(matchAll(
            status().isOk(),
            jsonPath("$").isNotEmpty(),
            jsonPath("$[0].id").value(product.getId())
        ));
  }

  @Test
  void getProduct() throws Exception {
    final Product product = new Product();
    product.setId(1);

    when(service.getProductById(eq(product.getId()))).thenReturn(product);
    mockMvc.perform(get("/api/product/" + product.getId()))
        .andExpect(matchAll(
            status().isOk(),
            jsonPath("$").isNotEmpty(),
            jsonPath("$.id").value(product.getId())
        ));

    when(service.getProductById(anyInt())).thenThrow(ProductNotFoundException.class);
    mockMvc.perform(get("/api/product/" + 1))
        .andExpect(status().isNotFound());
  }

  @Test
  void createProduct() throws Exception {

    final ProductDto productDto = new ProductDto();
    productDto.setId(1);
    productDto.setReceiptDate(new Date());
    productDto.setName("test");
    productDto.setItemsInStock(3);
    productDto.setRating(8);
    final Brand brand = new Brand();
    brand.setName("test brand");
    brand.setCountry("ru");
    brand.setId(1);
    productDto.setBrand(brand);
    productDto.setExpirationDate(Date.from(Instant.now().plus(Duration.ofDays(31))));
    productDto
        .setCategories(IntStream.range(1, 6).mapToObj(i -> getCategory(i, "Test" + i)).collect(
            Collectors.toSet()));

    when(service.create(any())).thenAnswer(inv -> inv.getArgument(0));
    mockMvc.perform(post("/api/product").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(productDto)))
        .andExpect(matchAll(
            status().isOk(),
            jsonPath("$").isNotEmpty(),
            jsonPath("$.id").value(productDto.getId())
        ));
  }

  private Category getCategory(int i, String s) {
    final Category category = new Category();
    category.setId(i);
    category.setName(s);
    return category;
  }

  @Test
  void deleteProduct() throws Exception {
    final Product product = new Product();
    product.setId(1);

    when(service.getProductById(eq(product.getId()))).thenReturn(product);
    mockMvc.perform(delete("/api/product/" + product.getId()))
        .andExpect(matchAll(
            status().isOk()
        ));
  }

  @Test
  void updateProduct() throws Exception {
    final ProductDto productDto = new ProductDto();
    productDto.setId(123);
    productDto.setReceiptDate(new Date());
    productDto.setName("test");
    productDto.setItemsInStock(3);
    productDto.setRating(8);
    final Brand brand = new Brand();
    brand.setName("test brand");
    brand.setCountry("ru");
    brand.setId(1);
    productDto.setBrand(brand);
    productDto.setExpirationDate(Date.from(Instant.now().plus(Duration.ofDays(31))));
    productDto
        .setCategories(IntStream.range(1, 6).mapToObj(i -> getCategory(i, "Test" + i)).collect(
            Collectors.toSet()));
    when(service.updateProduct(anyInt(), any())).thenAnswer(inv -> inv.getArgument(1));
    mockMvc
        .perform(put("/api/product/" + 1).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productDto)))
        .andExpect(matchAll(
            status().isOk(),
            jsonPath("$").isNotEmpty()
        ));

    productDto.setBrand(null);
    productDto.setCategories(Collections.emptySet());
    productDto.setExpirationDate(new Date());
    productDto.setItemsInStock(-1);
    productDto.setName("");
    productDto.setRating(-1);
    final byte[] bytes = new byte[300];
    new Random().nextBytes(bytes);
    productDto.setName(new String(bytes, Charset.defaultCharset()));
    mockMvc
        .perform(put("/api/product/" + 1).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productDto)))
        .andExpect(matchAll(
            status().isBadRequest()
        ));

    ;
  }
}