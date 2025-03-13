package com.bunheng.java.learn.phoneshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bunheng.java.learn.phoneshop.entity.Brand;
import com.bunheng.java.learn.phoneshop.exception.ResourceNotFoundException;
import com.bunheng.java.learn.phoneshop.repository.BrandRepository;
import com.bunheng.java.learn.phoneshop.service.Impl.BrandServiceImpl;

@ExtendWith(MockitoExtension.class)
public class brandServiceTest {
    
    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandServiceImpl brandService;

    @Test
    public void testCreate(){
        //given
        Brand brand = new Brand();
        brand.setName("Apple");
        //when
        brandService.create(brand);
        //then
        verify(brandRepository, times(1)).save(brand);
    }

    @Test
    public void testGetById(){

        // Given: A valid brand exists in the repository
        Brand brand = new Brand();
        brand.setName("Apple");
        brand.setId(1L);

        brandRepository.save(brand);

        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
        
        // When: getById is called
        Brand brands = brandService.getById(1L) ; 

        // Then: It should return the expected brand
        assertEquals(1, brands.getId());
        assertEquals("Apple", brands.getName());

        // Verify that the repository was called once
        verify(brandRepository, times(1)).findById(1L);


    }

    public void testGetByIdThrow(){
       // Given: No brand exists with the given ID
        Long id = (long) 100;
        when(brandRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then: It should throw ResourceNotFoundException
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            brandService.getById(id);
        });

        assertEquals("Brand", exception.getResourceName());
        assertEquals(id, exception.getResourceId());

        // Verify that the repository was called once
        verify(brandRepository, times(1)).findById(id);
    }

     @Test
    void testUpdate_Success() {
        // Given: A brand exists with ID 1
        Long id = (long) 1;
        Brand existingBrand = new Brand();
        existingBrand.setId(id);
        existingBrand.setName("Nike");

        Brand updatedBrand = new Brand();
        updatedBrand.setName("Adidas");

        // Mocking: getById returns an existing brand
        when(brandRepository.findById(id)).thenReturn(Optional.of(existingBrand));

        // Mocking: brandRepository.save returns the updated brand
        when(brandRepository.save(any(Brand.class))).thenReturn(updatedBrand);

        // When: update is called
        Brand result = brandService.update(id, updatedBrand);

        // Then: Verify the update was successful
        assertNotNull(result);
        assertEquals("Adidas", result.getName());

        // Verify interactions
        verify(brandRepository, times(1)).findById(id);
        verify(brandRepository, times(1)).save(existingBrand);
    }

     @Test
    void testUpdate_BrandNotFound() {
        // Given: No brand exists with the given ID
        Long id = (long) 100;
        Brand updatedBrand = new Brand();
        updatedBrand.setName("Adidas");

        // Mocking: getById throws ResourceNotFoundException
        when(brandRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then: It should throw ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> brandService.update(id, updatedBrand));

        // Verify that findById was called
        verify(brandRepository, times(1)).findById(id);
        verify(brandRepository, never()).save(any(Brand.class));
    }

}
