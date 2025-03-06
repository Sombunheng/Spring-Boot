package com.bunheng.java.learn.phoneshop.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import com.bunheng.java.learn.phoneshop.dto.BrandDto;
import com.bunheng.java.learn.phoneshop.dto.PageDTO;
import com.bunheng.java.learn.phoneshop.entity.Brand;
import com.bunheng.java.learn.phoneshop.mapper.BrandMapper;
import com.bunheng.java.learn.phoneshop.service.BrandService;

@RestController
@RequestMapping("brands")
public class BrandController {

        @Autowired
        private BrandService brandService;
        
        @RequestMapping(method = RequestMethod.POST)
        public ResponseEntity<?> create(@RequestBody BrandDto brandDto){
            Brand brand = BrandMapper.INSTANCE.toBrand(brandDto);
            brand = brandService.create(brand);
            return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(brand));
        }
        
        @GetMapping("{id}")
        public ResponseEntity<?> getBrandById(@PathVariable("id") Integer brandId) { //pathvariable get id from url
            Brand brand = brandService.getById(brandId);    // go to implement in service imp
            return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(brand));
        }

        @PutMapping("{id}")
        public ResponseEntity<?> update(@PathVariable("id") Integer brandId , @RequestBody BrandDto brandDtoUpdate) { //pathvariable get id from url
            Brand brand = BrandMapper.INSTANCE.toBrand(brandDtoUpdate);
            brand = brandService.update(brandId , brand);    // go to implement in service imp
            return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(brand));
        }

        @GetMapping
        public ResponseEntity<?> getBrands(@RequestParam Map<String , String > params) { 
            Page <Brand> page = brandService.getbBrands(params);
            PageDTO pageDTO = new PageDTO(page);
            // List <BrandDto> list = brandService.getbBrands(params)
            // .stream()
            // .map(brand -> BrandMapper.INSTANCE.toBrandDto(brand))
            // .collect(Collectors.toList());

            return ResponseEntity.ok(pageDTO) ;
        }

        @GetMapping("filter")
        public ResponseEntity<?>  filterBrand(@RequestParam("name") String name ){ 
            List <BrandDto> list = brandService.filterBrand(name)
            .stream()
            .map(brand -> BrandMapper.INSTANCE.toBrandDto(brand))
            .collect(Collectors.toList());
            return ResponseEntity.ok(list) ;
        }

        @DeleteMapping("{id}")
        public ResponseEntity<Void> deleteBrand(@PathVariable Integer id) {
            // Calling the delete method from the service
            brandService.delete(id);
            return ResponseEntity.noContent().build(); // Return a 204 No Content response
        }




}

