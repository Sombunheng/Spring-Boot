package com.bunheng.java.learn.phoneshop.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.bunheng.java.learn.phoneshop.entity.Brand;

public interface BrandService {
        Brand create(Brand brand); // Method defined in interface
        Brand getById (Long id);
        Brand update(Long id , Brand brandUpdated);
        Page<Brand> getbBrands(Map <String , String> params);


        List<Brand> filterBrand(String name);

        void delete(Long id);

}
