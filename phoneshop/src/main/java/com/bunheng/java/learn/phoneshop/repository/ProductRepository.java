package com.bunheng.java.learn.phoneshop.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bunheng.java.learn.phoneshop.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository <Product , Long>
, JpaSpecificationExecutor<Product> 
{
    // Custom query to get the lowest price
    @Query("SELECT p FROM Product p WHERE p.salePrice = (SELECT MIN(p2.salePrice) FROM Product p2)")
    List<Product> findProductsWithLowestPrice();

    // Custom query to get the highest price
    @Query("SELECT p FROM Product p WHERE p.salePrice = (SELECT MAX(p2.salePrice) FROM Product p2)")
    List<Product> findProductsWithHighestPrice();
}
