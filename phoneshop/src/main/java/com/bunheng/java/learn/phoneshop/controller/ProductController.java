package com.bunheng.java.learn.phoneshop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bunheng.java.learn.phoneshop.dto.PageDTO;
import com.bunheng.java.learn.phoneshop.dto.ProductDTO;
import com.bunheng.java.learn.phoneshop.entity.Product;
import com.bunheng.java.learn.phoneshop.mapper.ProductMapper;
import com.bunheng.java.learn.phoneshop.service.ProductService;


@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO){
        Product product = ProductMapper.INSTANCE.toEntity(productDTO);
        product = productService.create(product);
        return ResponseEntity.ok(ProductMapper.INSTANCE.toDto(product));
    }

    @GetMapping
    public ResponseEntity<?> getProducts(@RequestParam Map<String , String > params) { 
        System.out.println("\n" + params + "\n");

        Page <Product> page = productService.getProducts(params);
        PageDTO pageDTO = new PageDTO(page);
        
        return ResponseEntity.ok(pageDTO) ;
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id , @RequestBody ProductDTO productDTO ){
        Product product = ProductMapper.INSTANCE.toEntity(productDTO);
        product = productService.update(id, product);
        return ResponseEntity.ok(ProductMapper.INSTANCE.toDto(product));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> productDetail(@PathVariable("id") Long id){
        System.out.println("hello");
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(ProductMapper.INSTANCE.toDto(product));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        // Calling the delete method from the service
        productService.delete(id);
        return ResponseEntity.noContent().build(); // Return a 204 No Content response
    }

    @GetMapping("/lowest-price")
    public List<Product> getProductsWithLowestPrice() {
        return productService.getProductsWithLowestPrice();
    }

    @GetMapping("/highest-price")
    public List<Product> getProductsWithHighestPrice() {
        return productService.getProductsWithHighestPrice();
    }
}
