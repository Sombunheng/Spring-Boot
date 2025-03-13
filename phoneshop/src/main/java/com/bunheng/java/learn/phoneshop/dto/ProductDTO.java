package com.bunheng.java.learn.phoneshop.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String imagePath;
    private Integer availableUnit;
    private Long modelId;
    private Long colorId;
    private BigDecimal salePrice;
}

