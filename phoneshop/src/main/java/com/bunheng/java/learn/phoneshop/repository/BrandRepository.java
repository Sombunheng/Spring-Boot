package com.bunheng.java.learn.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bunheng.java.learn.phoneshop.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand , Integer>{  
    //JpaRepository It automatically generates SQL queries like: SELECT * FROM brands WHERE id = 1;


}
