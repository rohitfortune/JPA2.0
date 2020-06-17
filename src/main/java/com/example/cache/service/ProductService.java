package com.example.cache.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cache.dao.CartItemRepository;
import com.example.cache.dao.ProductCategoryRepository;
import com.example.cache.dao.ProductRepository;
import com.example.cache.dao.SellerRepository;
import com.example.cache.dao.UserRepository;
import com.example.cache.entities.CartItem;
import com.example.cache.entities.Product;
import com.example.cache.entities.ProductCategory;
import com.example.cache.entities.Seller;
import com.example.cache.entities.User;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductCategoryRepository categoryRepo;
	@Autowired
	private SellerRepository sellerRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CartItemRepository cartItemRepo;

	@Cacheable(value = "allProductCache", key = "#root.methodName")
	public List<Product> findAll() {

		return productRepository.findAll();
	}

	@Cacheable(value = "allProductCache", key = "#root.methodName")
	public Map<String, List<Product>> getProductGroupedByBrand() {

		List<Product> products = productRepository.findAll();

		return products.stream().collect(Collectors.groupingBy(p -> p.getBrand(), Collectors.toList()));
	}

	@Cacheable(value = "allProductCache", key = "#root.methodName")
	public Map<String, List<Product>> getProductGroupedByCategory() {

		List<Product> products = productRepository.findAll();

		return products.stream().collect(Collectors.groupingBy(p -> p.getCategory().getName(), Collectors.toList()));
	}

	@Cacheable(value = "allProductCache", key = "#root.methodName")
	public Map<BigDecimal, List<Product>> getProductGroupedByPrice() {

		List<Product> products = productRepository.findAll();

		return products.stream().collect(Collectors.groupingBy(p -> p.getPrice(), Collectors.toList()));
	}

	@Cacheable(value = "allProductCache", key = "#root.methodName")
	public Map<Integer, List<Product>> getProductGroupedBySize() {

		List<Product> products = productRepository.findAll();

		return products.stream().collect(Collectors.groupingBy(p -> p.getSize(), Collectors.toList()));
	}

	@Cacheable(value = "allProductCache", key = "#root.methodName")
	public Map<String, List<Product>> getProductGroupedByColor() {

		List<Product> products = productRepository.findAll();

		return products.stream().collect(Collectors.groupingBy(p -> p.getColor(), Collectors.toList()));
	}

	@Cacheable(value = "allProductCache", key = "#root.methodName")
	public Map<String, List<Product>> getProductGroupedBySku() {

		List<Product> products = productRepository.findAll();

		return products.stream().collect(Collectors.groupingBy(p -> p.getSku(), Collectors.toList()));
	}

	// Triggers cache population.
	@Cacheable(value = "allProductCache", key = "#root.methodName")
	public Map<Seller, Long> getProductCountBySeller() {

		List<Product> products = productRepository.findAll();

		return products.stream().collect(Collectors.groupingBy(p -> p.getSeller(), Collectors.counting()));
	}

	// Triggers cache eviction.
	@Caching(evict = { @CacheEvict(cacheNames = "allProductCache", allEntries = true),
			@CacheEvict(cacheNames = "productCache", key = "#p0") })
	public void deleteProduct(String sku) {
		productRepository.deleteBySku(sku);
	}

	// @CachePut Updates the cache without interfering with the method execution.
	@Caching(evict = { @CacheEvict(cacheNames = "allProductCache", allEntries = true) }, put = {
			@CachePut(cacheNames = "productCache", key = "#result.sku") })
	public Product saveProduct(Product product) {
		Product exitingProduct = productRepository.findBySku(product.getSku());
		if (Optional.ofNullable(exitingProduct).isPresent()) {
			exitingProduct.setPrice(product.getPrice());
			exitingProduct.setQuantity(product.getQuantity());
			return productRepository.save(exitingProduct);
		}
		ProductCategory category = categoryRepo.findByName(product.getCategory().getName());
		product.setCategory(category);
		Seller seller = sellerRepo.findByName(product.getSeller().getName());
		product.setSeller(seller);
		return productRepository.save(product);
	}

	@Cacheable(cacheNames = { "productCache" }, key = "#p0")
	public Product getProduct(String sku) {
		return productRepository.findBySku(sku);
	}

	public List<CartItem> getCartItemsForUser(Long userId) {

		List<CartItem> cartItems = new ArrayList<>();
		Optional<User> user = userRepo.findById(userId);
		if (user.isPresent()) {
			cartItems = cartItemRepo.findByUser(user.get());
		}

		return cartItems;
	}

	public ResponseEntity<String> addItemsToCart(Long userId, String sku, Integer quantity) {

		Optional<User> user = userRepo.findById(userId);
		if (user.isPresent()) {

			Product product = productRepository.findBySku(sku);
			if(product == null)
				return ResponseEntity.badRequest().body("product not found");
			CartItem item = new CartItem();
			item.setProduct(product);
			item.setBuyQuantity(quantity);
			item.setUser(user.get());
			cartItemRepo.save(item);

			return ResponseEntity.ok("added Successfully");
		}

		return ResponseEntity.badRequest().body("User not found");
	}

	public Optional<CartItem> removeItemsFromCart(Long userId, Long cartId) {

		Optional<CartItem> cartItem = cartItemRepo.findById(cartId);
		if (cartItem.isPresent()) {
			User user = cartItem.get().getUser();
			if(user.getId() == userId) {
				cartItemRepo.deleteById(cartId);
				return cartItem;
			}				
		}
		return Optional.ofNullable(null);
	}

}
