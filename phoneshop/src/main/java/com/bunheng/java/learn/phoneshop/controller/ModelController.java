package com.bunheng.java.learn.phoneshop.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bunheng.java.learn.phoneshop.dto.ModelDTO;
import com.bunheng.java.learn.phoneshop.dto.PageDTO;
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
        Model model = ModelMapper.INSTANCE.toModel(modelDTO) ;
        model = modelService.create(model);
        return ResponseEntity.ok(ModelMapper.INSTANCE.toModelDto(model));
    }

    @GetMapping("{id}")
    public ResponseEntity <?> getModelById (@PathVariable("id" ) Long id){
        Model model = modelService.getById(id);
        return ResponseEntity.ok(ModelMapper.INSTANCE.toModelDto(model)) ;
    }

    @PutMapping("{id}")
    public ResponseEntity <?> update(@PathVariable("id") Long id , @RequestBody ModelDTO modelDTO){
        Model model = ModelMapper.INSTANCE.toModel(modelDTO);
        model = modelService.update(model, id);
        return ResponseEntity.ok(ModelMapper.INSTANCE.toModelDto(model));
    }

    @GetMapping
    public ResponseEntity <?> getModels(Map<String , String> params){
        Page <Model> page = modelService.getmodels(params);
        PageDTO pageDTO = new PageDTO(page);

        return ResponseEntity.ok(pageDTO) ; 
    }


    // Delete model by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        modelService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Filter Brand by model 
    // @GetMapping("/brand/{brandId}")
    // public List<Model> getModelsByBrand(@PathVariable Integer brandId) {
    //     return modelService.getModelsByBrand(brandId);
    // }
}



