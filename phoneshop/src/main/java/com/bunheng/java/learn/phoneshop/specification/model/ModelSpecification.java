package com.bunheng.java.learn.phoneshop.specification.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.bunheng.java.learn.phoneshop.entity.Model;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class ModelSpecification implements Specification<Model>{

    private final ModelFilter modelFilter ;

    List < Predicate > predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<Model> model, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (modelFilter.getName() != null){
            Predicate name = cb.like(cb.upper(model.get("name")) , "%" + modelFilter.getName().toUpperCase() + "%") ;
                predicates.add(name) ; 
        }

        if (modelFilter.getId() != null){
            Predicate id = model.get("id").in(modelFilter.getId());
                predicates.add(id) ; 
        }

        if (modelFilter.getBrandId() != null) {
            predicates.add(cb.equal(model.get("brand").get("id"), modelFilter.getBrandId()));  // Filtering by brandId
        }


        return cb.and(predicates.toArray(Predicate[]:: new));
    }

    public static Specification<Model> hasBrandId(Integer brandId) {
        return (root, query, criteriaBuilder) -> {
            if (brandId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("brand").get("id"), brandId);
        };
    }
    
}
