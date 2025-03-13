package com.bunheng.java.learn.phoneshop.specification.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.bunheng.java.learn.phoneshop.entity.Product;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class ProductSpecification implements Specification<Product>{
    private final ProductFilter productFilter ;

    public ProductSpecification(ProductFilter productFilter) {
        this.productFilter = productFilter;
    }

    @Override
    @Nullable
    public Predicate toPredicate(Root<Product> product, @Nullable CriteriaQuery<?> query, CriteriaBuilder cb) {

        List < Predicate > predicates = new ArrayList<>();

         // Filter by name (if provided)
        if (productFilter.getName() != null && !productFilter.getName().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(product.get("name")), "%" + productFilter.getName().toLowerCase() + "%"));
        }

        if (productFilter.getId() != null) {
            predicates.add(cb.equal(product.get("id"), productFilter.getId()));
        }

        // Filter by model ID (if provided)
        if (productFilter.getModelId() != null) {
            predicates.add(cb.equal(product.get("model").get("id"), productFilter.getModelId()));
        }

        // Filter by color ID (if provided)
        if (productFilter.getColorId() != null) {
            predicates.add(cb.equal(product.get("color").get("id"), productFilter.getColorId()));
        }

        // Filter by sale price range
        if (productFilter.getMinPrice() != null && productFilter.getMaxPrice() != null) {
            predicates.add(cb.between(product.get("salePrice"), productFilter.getMinPrice(), productFilter.getMaxPrice()));
        } else if (productFilter.getMinPrice() != null) {
            predicates.add(cb.greaterThanOrEqualTo(product.get("salePrice"), productFilter.getMinPrice()));
        } else if (productFilter.getMaxPrice() != null) {
            predicates.add(cb.lessThanOrEqualTo(product.get("salePrice"), productFilter.getMaxPrice()));
        }
        
        // Exact sale price filtering (if needed)
        if (productFilter.getSalePrice() != null) {
            predicates.add(cb.equal(product.get("salePrice"), productFilter.getSalePrice()));
        }
        

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
