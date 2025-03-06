package com.bunheng.java.learn.phoneshop.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bunheng.java.learn.phoneshop.entity.Brand;
import com.bunheng.java.learn.phoneshop.exception.ResourceNotFoundException;
import com.bunheng.java.learn.phoneshop.repository.BrandRepository;
import com.bunheng.java.learn.phoneshop.service.BrandService;
import com.bunheng.java.learn.phoneshop.service.util.PageUtil;
import com.bunheng.java.learn.phoneshop.specification.BrandFilter;
import com.bunheng.java.learn.phoneshop.specification.BrandSpecification;


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
            .orElseThrow(() -> new ResourceNotFoundException("Brand", id)) ;
    }

    @Override
    public Brand update(Integer id, Brand brandUpdated) {
        Brand brand = getById(id);
        brand.setName(brandUpdated.getName());
       return brandRepository.save(brand) ;
    }

    @Override
    public List<Brand> filterBrand(String name) {
       return brandRepository.findByNameContainingIgnoreCase(name);
    }

    // @Override
    // public List<Brand> getbBrands(Map<String, String> params) {
    //     BrandFilter brandFilter = new BrandFilter();
        
    //     if (params.containsKey("name")){
    //         String name = params.get("name");
    //         brandFilter.setName(name);
    //     }
    //     if (params.containsKey("id")){
    //         String id = params.get("id");
    //         brandFilter.setId(Integer.parseInt(id));
    //     }

    //     int pageLimit = 1 ;
    //     if (params.containsKey(PageUtil.PAGE_LIMIT)){
    //         pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
    //     }

    //     int pageNumber = 1 ;
    //     if (params.containsKey(PageUtil.PAGE_NUMBER)){
    //         pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
    //     }
        
    //     BrandSpecification brandSpecification = new BrandSpecification(brandFilter);

    //     PageRequest pageable = PageUtil.getPageable(pageLimit, pageNumber);

    //     return brandRepository.findAll(brandSpecification);
    // }


    @Override
    public Page<Brand> getbBrands(Map<String, String> params) {
        BrandFilter brandFilter = new BrandFilter();
        
        if (params.containsKey("name")){
            String name = params.get("name");
            brandFilter.setName(name);
        }
        if (params.containsKey("id")){
            String id = params.get("id");
            brandFilter.setId(Integer.parseInt(id));
        }

        int pageLimit = 2 ;
        if (params.containsKey(PageUtil.PAGE_LIMIT)){
            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }

        int pageNumber = 1 ;
        if (params.containsKey(PageUtil.PAGE_NUMBER)){
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }
        
        BrandSpecification brandSpecification = new BrandSpecification(brandFilter);

        PageRequest pageable = PageUtil.getPageable(pageNumber, pageLimit);

        Page<Brand> page =brandRepository.findAll(brandSpecification , pageable);

        return page ;
    }

    @Override
    public void delete(Integer id) {
        if (!brandRepository.existsById(id)) {
            throw new ResourceNotFoundException("Brand", id) ;
        }
        brandRepository.deleteById(id); // Deletes the brand with the given ID

    }
}
