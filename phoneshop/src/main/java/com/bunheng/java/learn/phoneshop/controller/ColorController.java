package com.bunheng.java.learn.phoneshop.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bunheng.java.learn.phoneshop.dto.ColorDTO;
import com.bunheng.java.learn.phoneshop.dto.PageDTO;
import com.bunheng.java.learn.phoneshop.entity.Color;
import com.bunheng.java.learn.phoneshop.mapper.ColorMapper;
import com.bunheng.java.learn.phoneshop.service.ColorService;


@RestController
@RequestMapping("colors")
public class ColorController {
    @Autowired
    private ColorService colorService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody  ColorDTO colorDTO){
        Color color  = ColorMapper.INSTANCE.toColor(colorDTO);
        color = colorService.create(color);
        return ResponseEntity.ok(ColorMapper.INSTANCE.tColorDTO(color));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getColorById(@PathVariable("id") Long id){
        Color color = colorService.getColorById(id);
        return ResponseEntity.ok(ColorMapper.INSTANCE.tColorDTO(color)); 
    }

    @GetMapping
    public ResponseEntity<?> getColors(@RequestParam Map<String , String> params){
         Page <Color> page = colorService.getColors(params);
            PageDTO pageDTO = new PageDTO(page);
            
            return ResponseEntity.ok(pageDTO) ;
    }

     @PutMapping("{id}")
        public ResponseEntity<?> update(@PathVariable("id") Long colorId , @RequestBody ColorDTO colorDTO) { //pathvariable get id from url
            Color color = ColorMapper.INSTANCE.toColor(colorDTO);
            color = colorService.update(colorId , color);    // go to implement in service imp
            return ResponseEntity.ok(ColorMapper.INSTANCE.tColorDTO(color));
        }



}
