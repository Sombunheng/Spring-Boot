package com.bunheng.java.learn.phoneshop.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.bunheng.java.learn.phoneshop.entity.Brand;
import com.bunheng.java.learn.phoneshop.exception.ApiException;
import com.bunheng.java.learn.phoneshop.repository.BrandRepository;
import com.bunheng.java.learn.phoneshop.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{

    @Autowired
    private BrandRepository brandRepository;  // go to brandResponsitory to run JpaRepository 
    


    @Override
    public Brand create(Brand brand){
        return brandRepository.save(brand);
    }

    @Override
    public Brand getById(Integer id) {
        return brandRepository.findById(id)
            .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND , "Brand with id %d not found".formatted(id)  )) ;
    }

    @Override
    public Brand update(Integer id, Brand brandUpdated) {
        Brand brand = getById(id);
        brand.setName(brandUpdated.getName());
       return brandRepository.save(brand) ;
    }

    // @Override
    // public Brand delete(Integer id) {
    //     Brand brand = getById(id) ;
    //     brandRepository.deleteById(id); // Delete the brand

    //     return 

    // }

    

}
