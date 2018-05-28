package com.app.drools.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.drools.api.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

	public Product save(Product product);
	
	public List<Product> findAll();
	
}
