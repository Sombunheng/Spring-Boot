package com.bunheng.java.learn.phoneshop.service;

import com.bunheng.java.learn.phoneshop.entity.Brand;

public interface BrandService {
        Brand create(Brand brand); // Method defined in interface
        Brand getById (Integer id);
        Brand update(Integer id , Brand brandUpdated);
        // Brand delete(Integer id );

}
