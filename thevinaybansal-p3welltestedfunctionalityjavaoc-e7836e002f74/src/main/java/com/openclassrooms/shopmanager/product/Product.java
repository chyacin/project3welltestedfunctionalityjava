package com.openclassrooms.shopmanager.product;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Range;

public class Product {

    private Long id;
    
    @NotBlank
    private String name;        // Required
    private String description;
    private String details;
    
    @DecimalMin(value = ".01") @Positive @NotNull 
    private Integer quantity;   // Required, Integer, Greater than zero
    
    @Positive  @Range(min = 1, max = Integer.MAX_VALUE) @NotNull
    private Double price;       // Required, Numeric, Greater than zero

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
