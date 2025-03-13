package com.bunheng.java.learn.phoneshop.specification.product;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductFilter {

    private Long id;
    private String name;
    private String imagePath;
    private Integer availableUnit;
    private Long modelId;
    private Long colorId;
    private BigDecimal salePrice;
    private BigDecimal minPrice;  // Range filter
    private BigDecimal maxPrice;  // Range filter
   
}


