package com.bunheng.java.learn.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bunheng.java.learn.phoneshop.entity.Sale;


@Repository
public interface SaleRepository extends JpaRepository<Sale , Long>
, JpaSpecificationExecutor<Sale> 
{
    
}
