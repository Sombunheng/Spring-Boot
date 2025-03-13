package com.bunheng.java.learn.phoneshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bunheng.java.learn.phoneshop.entity.Color;

@Repository
public interface ColorRepository extends JpaRepository <Color , Long>
, JpaSpecificationExecutor<Color> 
{
    
}
