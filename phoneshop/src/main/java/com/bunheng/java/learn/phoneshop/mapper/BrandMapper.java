package com.bunheng.java.learn.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bunheng.java.learn.phoneshop.dto.BrandDto;
import com.bunheng.java.learn.phoneshop.entity.Brand;

@Mapper(componentModel = "spring") // ✅ Enables Spring integration
public interface BrandMapper {
     BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class); // ✅ Add an instance

    Brand toBrand(BrandDto dto); // ✅ Map DTO to Entity
    BrandDto toBrandDto(Brand brand); // ✅ Add reverse mapping (optional)
}
