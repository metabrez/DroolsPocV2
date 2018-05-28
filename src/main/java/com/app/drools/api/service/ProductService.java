package com.app.drools.api.service;

import java.util.List;

import com.app.drools.api.model.Product;
import com.app.drools.api.model.ProductResponse;

public interface ProductService {

	void applyDiscount(Product product);

	List<Integer> getRuleIdList();

	List<Product> save(List<Product> product);

	Product save(Product product);

	List<Product> findAll();
}
