package com.bunheng.java.learn.phoneshop.specification;

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

    List < Predicate > predicates = new ArrayList<>();

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
       if (brandFilter.getName() != null){
        // Predicate is SQL query 
        Predicate name = cb.like(cb.upper(brand.get("name")) , "%" + brandFilter.getName().toUpperCase() + "%") ; 
            predicates.add(name);
       }

       if (brandFilter.getId() != null){
        Predicate id = brand.get("id").in(brandFilter.getId());
        predicates.add(id);
        }
        return cb.and(predicates.toArray(Predicate[]:: new));
    }
    
}
