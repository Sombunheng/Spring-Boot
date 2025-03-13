package com.bunheng.java.learn.phoneshop.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.bunheng.java.learn.phoneshop.entity.Color;

public interface ColorService {
    Color create(Color collect) ;
    Page<Color> getColors(Map <String , String> params) ; 
    Color getColorById (Long id);
    Color update (Long id , Color color);
    void delete (Long id);

}
