package com.example.cache.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cache.entities.Seller;
@Repository
public interface SellerRepository extends JpaRepository<Seller, Long>{

	Seller findByName(String name);
	
}
