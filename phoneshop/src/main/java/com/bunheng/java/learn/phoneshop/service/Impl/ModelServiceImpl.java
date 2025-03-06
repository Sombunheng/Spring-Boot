package com.bunheng.java.learn.phoneshop.service.Impl;

import org.springframework.stereotype.Service;

import com.bunheng.java.learn.phoneshop.entity.Model;
import com.bunheng.java.learn.phoneshop.repository.ModelRepository;
import com.bunheng.java.learn.phoneshop.service.ModelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ModelServiceImpl implements ModelService{

   private final   ModelRepository modelRepository ;

    @Override
    public Model create(Model model) {
       return modelRepository.save(model) ; 
    }
    
}
