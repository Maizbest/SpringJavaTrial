package com.sparkequation.spring.trial.api.mapper;

import com.sparkequation.spring.trial.api.dto.ProductDto;
import com.sparkequation.spring.trial.api.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  Product toEntity(ProductDto dto);
}
