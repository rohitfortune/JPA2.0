package com.example.cache;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.example.cache.dao.ProductCategoryRepository;
import com.example.cache.dao.ProductRepository;
import com.example.cache.dao.RoleRepository;
import com.example.cache.dao.SellerRepository;
import com.example.cache.dao.UserRepository;
import com.example.cache.entities.Address;
import com.example.cache.entities.Product;
import com.example.cache.entities.ProductCategory;
import com.example.cache.entities.Role;
import com.example.cache.entities.Seller;
import com.example.cache.entities.User;
import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;

@SpringBootApplication
@EnableCaching
public class SpringCacheApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringCacheApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringCacheApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProductRepository productRepository, ProductCategoryRepository categoryRepo,
			SellerRepository sellerRepo, UserRepository userRepo, RoleRepository roleRepo) {
		return (args) -> {

			Seller seller1 = new Seller("Jack");
			Seller seller2 = new Seller("Ryan");
			sellerRepo.save(seller1);
			sellerRepo.save(seller2);

			ProductCategory shirt = new ProductCategory("shirt");
			ProductCategory denims = new ProductCategory("denims");
			categoryRepo.save(shirt);
			categoryRepo.save(denims);

			Product product1 = new Product("123skui9", new BigDecimal(5000.00), "black", "pepe", 30, 5, seller1,
					denims);
			Product product2 = new Product("173skui9", new BigDecimal(6000.00), "white", "Raymond", 30, 5, seller2,
					shirt);

			// save a few products
			productRepository.save(product1);
			productRepository.save(product2);

			// Let's create one user with address and Role
			Role customer = new Role("customer");
			User user = new User("Rohit Prasad", "rohitt", "pass", "abc@gmail.com", "9999988888", customer);
			Address address = new Address("chowk", "bengaluru", "karnataka", "india", "121212", user);
			user.getAddresses().add(address);

			// save user
			roleRepo.save(customer);
			userRepo.save(user);

			// fetch all products
			log.info("products found with findAll():");
			log.info("-------------------------------");
			for (Product product : productRepository.findAll()) {
				log.info(product.toString());
			}
			log.info("");

			// fetch all User
			log.info("user found with findAll():");
			log.info("-------------------------------");
			for (User u : userRepo.findAll()) {
				log.info(u.toString());
			}

		};
	}

	@Bean
	public Config hazelCastConfig() {
		return new Config().setInstanceName("hazelcast-instance")
				.addMapConfig(new MapConfig().setName("allProductCache")
						.setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
						.setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(20))
				.addMapConfig(new MapConfig().setName("productCache")
						// .setTimeToLiveSeconds(2000)
						// .setEvictionConfig(new
						// EvictionConfig().setEvictionPolicy(EvictionPolicy.LRU).setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE).setSize(200)));
						.setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
						.setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(20));
	}

}
