package com.example.cache.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cache.entities.CartItem;
import com.example.cache.entities.Product;
import com.example.cache.entities.Seller;
import com.example.cache.exception.RecordNotFoundException;
import com.example.cache.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/productByBrand")
	public Map<String, List<Product>> getProductByBrand() {

		return productService.getProductGroupedByBrand();
	}

	@GetMapping("/productByPrice")
	public Map<BigDecimal, List<Product>> getProductByPrice() {

		return productService.getProductGroupedByPrice();
	}

	@GetMapping("/productBySize")
	public Map<Integer, List<Product>> getProductBySize() {

		return productService.getProductGroupedBySize();
	}

	@GetMapping("/productByColor")
	public Map<String, List<Product>> getProductByColor() {

		return productService.getProductGroupedByColor();
	}

	@GetMapping("/productBySku")
	public Map<String, List<Product>> getProductBySku() {

		return productService.getProductGroupedBySku();
	}

	@GetMapping("/productCountBySeller")
	public Map<Seller, Long> getProductCountBySeller() {

		return productService.getProductCountBySeller();
	}

	@GetMapping("/product")
	public List<Product> getProducts() {

		return productService.findAll();
	}

	@PostMapping("/product")
	public Product saveProduct(@RequestBody Product product) {

		return productService.saveProduct(product);
	}

	@DeleteMapping("/product/{sku}")
	public ResponseEntity<String> deleteProduct(@PathVariable String sku) {

		productService.deleteProduct(sku);
		return ResponseEntity.ok("Successfully Deleted");
	}

	@GetMapping("/product/{sku}")
	public ResponseEntity<Product> getProduct(@PathVariable String sku) {

		Product product = productService.getProduct(sku);
		return ResponseEntity.ok(product);
	}

	@GetMapping("/productByCategory")
	public ResponseEntity<Map<String, List<Product>>> getProductByCategory() {

		Map<String, List<Product>> product = productService.getProductGroupedByCategory();
		return ResponseEntity.ok(product);
	}

	@GetMapping("/cart/{userId}")
	public ResponseEntity<List<CartItem>> getCartItemsForUser(@PathVariable("userId") Long userId) {

		List<CartItem> cartItems = productService.getCartItemsForUser(userId);
		return ResponseEntity.ok(cartItems);
	}

	@PostMapping("/cart/{userId}/{sku}/{quantity}")
	public ResponseEntity<String> addItemsToCart(@PathVariable("userId") Long userId, @PathVariable("sku") String sku,
			@PathVariable("quantity") Integer quantity) {

		return productService.addItemsToCart(userId, sku, quantity);

	}

	@DeleteMapping("/cart/{userId}/{cartId}")
	public CartItem removeItemsFromCart(@PathVariable("userId") Long userId, @PathVariable("cartId") Long cartId) {

		return productService.removeItemsFromCart(userId, cartId).orElseThrow(()-> new RecordNotFoundException());		
	}
}
