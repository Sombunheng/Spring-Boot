package com.bunheng.java.learn.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bunheng.java.learn.phoneshop.entity.SaleDetail;

public interface SaleDetailRepository extends JpaRepository < SaleDetail , Long>
, JpaSpecificationExecutor<SaleDetail> 
{

}
