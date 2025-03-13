package com.bunheng.java.learn.phoneshop.service.Impl;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bunheng.java.learn.phoneshop.entity.Product;
import com.bunheng.java.learn.phoneshop.exception.ResourceNotFoundException;
import com.bunheng.java.learn.phoneshop.repository.ProductRepository;
import com.bunheng.java.learn.phoneshop.service.ProductService;
import com.bunheng.java.learn.phoneshop.service.util.PageUtil;
import com.bunheng.java.learn.phoneshop.specification.product.ProductFilter;
import com.bunheng.java.learn.phoneshop.specification.product.ProductSpecification;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository ;

    @Override
    public Product create(Product product) {
       return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Product", id)) ;
    }

    @Override
    public Product update(Long id, Product productUpdate) {

        Product product = getProductById(id);
        product.setName(productUpdate.getName());
        product.setColor(productUpdate.getColor());
        product.setImagePath(productUpdate.getImagePath());
        product.setModel(productUpdate.getModel());
        product.setSalePrice(productUpdate.getSalePrice());
        product.setAvailableUnit(productUpdate.getAvailableUnit());


        return productRepository.save(product);
    }

    @Override
    public Page<Product> getProducts(Map<String, String> params) {

        ProductFilter productFilter = new ProductFilter();

        System.out.println("\n" + params + "\n");


        if(params.containsKey("name")){
        String name = params.get("name");
        System.out.println("\n" + name + "\n");

        productFilter.setName(name);
        }

        if(params.containsKey("id")){
        String id = params.get("id");
        System.out.println("\n" + id + "\n");

        productFilter.setId(Long.parseLong(id));
        }


        if(params.containsKey("color_id")){
            String color = params.get("color_id");
            productFilter.setColorId(Long.parseLong(color));
        }

        if(params.containsKey("minPrice")){
            String minPrice = params.get("minPrice");
            productFilter.setMinPrice(new BigDecimal(minPrice));
        }

        if(params.containsKey("maxPrice")){
            String maxPrice = params.get("maxPrice");
            productFilter.setMaxPrice(new BigDecimal(maxPrice));
        }

        int pageLimit = 10;
        if (params.containsKey(PageUtil.PAGE_LIMIT)){
            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }

        int pageNumber = 0 ;
        if (params.containsKey(PageUtil.PAGE_NUMBER)){
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER ));
        }

        ProductSpecification productSpecification = new ProductSpecification(productFilter);

        System.out.println("\n " + productSpecification + " \n");

        

        PageRequest pagaable = PageUtil.getPageable(pageNumber , pageLimit );

        System.out.println("\n " + pagaable + " pagaable \n");

        
        Page <Product> page = productRepository.findAll(productSpecification , pagaable); 
        System.out.println("\n page " + page + " pagaable \n");

        return page ;
    }

    @Override
    public void delete(Long id) {
        if(!productRepository.existsById(id)){
            throw new ResourceNotFoundException("Product", id) ;
          }
          productRepository.deleteById(id);
    }
    
}
