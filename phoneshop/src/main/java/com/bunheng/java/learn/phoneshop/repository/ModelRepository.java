package com.bunheng.java.learn.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bunheng.java.learn.phoneshop.entity.Model;

public interface ModelRepository extends JpaRepository <Model , Integer> 
,JpaSpecificationExecutor<Module>  
{
    
}
