package com.bunheng.java.learn.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bunheng.java.learn.phoneshop.dto.ModelDTO;
import com.bunheng.java.learn.phoneshop.entity.Model;

@Mapper(uses = BrandMapper.class) // ✅ Use BrandMapper for nested mapping
public interface ModelMapper {

    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

    @Mapping(source = "brandId", target = "brand.id")
    Model toModel(ModelDTO modelDTO);

     // Map brand from Model to brandId in ModelDTO
     @Mapping(source = "brand.id", target = "brandId")
     ModelDTO toModelDto(Model model);

    

}
