package com.bunheng.java.learn.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
// import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bunheng.java.learn.phoneshop.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand , Integer> 
, JpaSpecificationExecutor<Brand> 
{  
    //JpaRepository It automatically generates SQL queries like: SELECT * FROM brands WHERE id = 1;
    List <Brand> findByNameContainingIgnoreCase(String name) ;

}
