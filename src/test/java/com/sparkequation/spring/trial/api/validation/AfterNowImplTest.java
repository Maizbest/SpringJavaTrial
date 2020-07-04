package com.sparkequation.spring.trial.api.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.sparkequation.spring.trial.api.dto.ProductDto;
import com.sparkequation.spring.trial.api.model.Category;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AfterNowImplTest {

  private Validator validator;

  @BeforeEach
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void testContactSuccess() {
    final ProductDto productDto = new ProductDto();
    productDto.setId(-1);
    final byte[] bytes = new byte[300];
    new Random().nextBytes(bytes);
    productDto.setName(new String(bytes, Charset.defaultCharset()));
    productDto.setRating(-1);
    productDto.setBrand(null);
    productDto.setCategories(Collections.singleton(new Category()));
    productDto.setExpirationDate(new Date());
    Set<ConstraintViolation<ProductDto>> violations = validator.validate(productDto);
    assertFalse(violations.isEmpty());
    assertEquals(6, violations.size());
  }
}