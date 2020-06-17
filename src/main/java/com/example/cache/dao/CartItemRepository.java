package com.example.cache.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cache.entities.CartItem;
import com.example.cache.entities.User;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	public List<CartItem> findByUser(User user);
}
