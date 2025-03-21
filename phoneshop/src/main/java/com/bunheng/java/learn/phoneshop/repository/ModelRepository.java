package com.bunheng.java.learn.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bunheng.java.learn.phoneshop.entity.Model;

@Repository
public interface ModelRepository extends JpaRepository <Model , Long> 
,JpaSpecificationExecutor<Model>  
{
    List<Model> findByBrandId(Long brandId);

}
