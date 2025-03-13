package com.bunheng.java.learn.phoneshop.specification.color;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.bunheng.java.learn.phoneshop.entity.Color;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class ColorSpecification implements Specification<Color>{

    private final ColorFilter colorFilter ;

   
    List<Predicate> predicates = new ArrayList<>();

    @Override
    @Nullable
    public Predicate toPredicate(Root<Color> color, @Nullable CriteriaQuery<?> query, CriteriaBuilder cb) {

        if(colorFilter.getName() != null && colorFilter.getName().isEmpty()){
            predicates.add(cb.like(cb.upper(color.get("name")), "%" + colorFilter.getName().toUpperCase() + "%"));
        }

        if(colorFilter.getId() != null){
            predicates.add(cb.equal(color.get("id"), colorFilter.getId()));
        }
        return cb.and(predicates.toArray(new Predicate[0]));
    }


}
