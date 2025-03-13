package com.bunheng.java.learn.phoneshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bunheng.java.learn.phoneshop.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository <Product , Long>
, JpaSpecificationExecutor<Product> 
{
    
}
