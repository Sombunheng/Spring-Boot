package com.bunheng.java.learn.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bunheng.java.learn.phoneshop.dto.ProductDTO;
import com.bunheng.java.learn.phoneshop.entity.Product;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "model.id", target = "modelId")
    @Mapping(source = "color.id", target = "colorId")
    ProductDTO toDto(Product product);

    @Mapping(source = "modelId", target = "model.id")
    @Mapping(source = "colorId", target = "color.id")
    Product toEntity(ProductDTO productDTO);
}
