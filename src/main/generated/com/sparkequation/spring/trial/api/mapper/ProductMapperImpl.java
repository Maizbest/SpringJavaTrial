package com.sparkequation.spring.trial.api.mapper;

import com.sparkequation.spring.trial.api.dto.ProductDto;
import com.sparkequation.spring.trial.api.model.Category;
import com.sparkequation.spring.trial.api.model.Product;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-04T16:10:58+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_252 (BellSoft)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( dto.getId() );
        product.setName( dto.getName() );
        product.setFeatured( dto.isFeatured() );
        product.setExpirationDate( dto.getExpirationDate() );
        product.setItemsInStock( dto.getItemsInStock() );
        product.setReceiptDate( dto.getReceiptDate() );
        product.setRating( dto.getRating() );
        product.setBrand( dto.getBrand() );
        Set<Category> set = dto.getCategories();
        if ( set != null ) {
            product.setCategories( new HashSet<Category>( set ) );
        }

        return product;
    }
}
