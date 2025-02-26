package com.bunheng.java.learn.phoneshop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bunheng.java.learn.phoneshop.entity.Brand;

@DataJpaTest
public class BrandRepositoryTest {
    @Autowired
    private BrandRepository brandRepository;
    
    @Test
    public void testFindByNameContainingIgnoreCase(){
        // given 
        Brand brand = new Brand();
        brand.setName("Apple");

        brandRepository.save(brand);

        //when
        List<Brand> brands = brandRepository.findByNameContainingIgnoreCase("A");

        //then
        assertEquals(1, brands.size());
        assertEquals("Apple", brands.get(0).getName());
        assertEquals(1, brands.get(0).getId());
    }
}
