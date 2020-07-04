package com.sparkequation.spring.trial.api.dto;

import com.sparkequation.spring.trial.api.model.Brand;
import com.sparkequation.spring.trial.api.model.Category;
import com.sparkequation.spring.trial.api.validation.AfterNow;
import java.util.Date;
import java.util.Set;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public class ProductDto {

  @Positive
  @Max((long) 1E10)
  @NotNull
  private int id;
  @NotBlank
  @Length(max = 255)
  private String name;
  private boolean featured;
  @AfterNow(plusDuration = "P30D", message = "Expiration date value must be more than 30 days after now.")
  private Date expirationDate;
  @PositiveOrZero(message = "ItemsInStock value cannot be negative.")
  @Max((long) 1E10)
  private int itemsInStock;
  private Date receiptDate;
  @PositiveOrZero(message = "Rating cannot be negative.")
  private double rating;
  @NotNull
  private Brand brand;
  @NotEmpty
  @Size(min = 5, message = "Product must contain at least 5 categories.")
  private Set<Category> categories;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isFeatured() {
    return featured;
  }

  public void setFeatured(boolean featured) {
    this.featured = featured;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }

  public int getItemsInStock() {
    return itemsInStock;
  }

  public void setItemsInStock(int itemsInStock) {
    this.itemsInStock = itemsInStock;
  }

  public Date getReceiptDate() {
    return receiptDate;
  }

  public void setReceiptDate(Date receiptDate) {
    this.receiptDate = receiptDate;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  public Brand getBrand() {
    return brand;
  }

  public void setBrand(Brand brand) {
    this.brand = brand;
  }

  public Set<Category> getCategories() {
    return categories;
  }

  public void setCategories(Set<Category> categories) {
    this.categories = categories;
  }
}
