package com.bunheng.java.learn.phoneshop.specification.brand;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.bunheng.java.learn.phoneshop.entity.Brand;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class BrandSpecification implements Specification<Brand> {

    private final BrandFilter brandFilter ; 

    public BrandSpecification(BrandFilter brandFilter) {
        this.brandFilter = brandFilter;
    }
    // @Override
    // public Predicate toPredicate(Root<Brand> brand, CriteriaQuery<?> query, CriteriaBuilder cb) {
    //    if (brandFilter.getName() !=null){
    //     Predicate name = brand.get("name").in(brandFilter.getName()); // Predicate is SQL query 
    //     predicates.add(name);
    //    }

    //    if (brandFilter.getId() !=null){
    //     Predicate id = brand.get("id").in(brandFilter.getId());
    //     predicates.add(id);
    //    }

    //    return cb.and(predicates.toArray(Predicate[] ::new ));
    // }

    @Override
    public Predicate toPredicate(Root<Brand> brand, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        // JOIN FETCH models to retrieve brands with their models
        
        if (brandFilter.getName() != null && !brandFilter.getName().isEmpty()) {
            predicates.add(cb.like(cb.upper(brand.get("name")), "%" + brandFilter.getName().toUpperCase() + "%"));
        }

        if (brandFilter.getId() != null) {
            predicates.add(cb.equal(brand.get("id"), brandFilter.getId()));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }

    
}
