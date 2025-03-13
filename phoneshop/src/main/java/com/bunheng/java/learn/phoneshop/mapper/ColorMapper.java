package com.bunheng.java.learn.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bunheng.java.learn.phoneshop.dto.ColorDTO;
import com.bunheng.java.learn.phoneshop.entity.Color;

@Mapper(componentModel = "spring") // ✅ Enables Spring integration
public interface ColorMapper {
    ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);

    Color toColor(ColorDTO colorDTO)  ; 
    ColorDTO tColorDTO(Color color);
}
