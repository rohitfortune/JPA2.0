package com.example.cache.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.cache.entities.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
	
	@Transactional
	@Modifying
	@Query("delete from Product p where p.sku = ?1")
	public void deleteBySku(String sku);

	public Product findBySku(String sku);
	
}
