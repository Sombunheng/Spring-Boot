package com.bunheng.java.learn.phoneshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bunheng.java.learn.phoneshop.dto.ModelDTO;
import com.bunheng.java.learn.phoneshop.entity.Model;
import com.bunheng.java.learn.phoneshop.mapper.ModelMapper;
import com.bunheng.java.learn.phoneshop.service.ModelService;

@RestController
@RequestMapping("models")
public class ModelController {
    
    @Autowired
    private ModelService modelService ;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity <?> create(@RequestBody ModelDTO modelDTO){
        Model model = ModelMapper.INSTANCE.toModel(modelDTO);
        model = modelService.create(model);
        return ResponseEntity.ok(ModelMapper.INSTANCE.toModelDto(model)) ;
    }

}
