package com.bunheng.java.learn.phoneshop.service;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.bunheng.java.learn.phoneshop.entity.Model;

public interface ModelService {
   Model create(Model model) ; 
   Model getById( Long id);
   Page <Model> getmodels(Map<String ,String > params);

   Model update(Model modelUpdate , Long id);
   void delete(Long id);

   List<Model> getModelsByBrand(Long brandId);

}
