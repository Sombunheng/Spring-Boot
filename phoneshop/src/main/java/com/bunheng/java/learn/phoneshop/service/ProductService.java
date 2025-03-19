package com.bunheng.java.learn.phoneshop.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.bunheng.java.learn.phoneshop.entity.Product;

public interface ProductService {
     Product create (Product product);
     Product getProductById (Long id);
     Product update (Long id , Product product);
     Page<Product> getProducts(Map <String , String>  params) ;
     void delete (Long id);
     List<Product> getProductsWithLowestPrice();   
     List<Product> getProductsWithHighestPrice();

}
