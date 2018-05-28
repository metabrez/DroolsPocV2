package com.app.drools.api.service;

import java.util.List;

import com.app.drools.api.model.Product;
import com.app.drools.api.model.ProductResponse;

public interface ProductService {

	/* void applyDiscount(Product product); */

	void applyDiscount(List<Product> product);

	List<Integer> getRuleIdList();
	Product save(Product product);

	List<Product> save(List<Product> product);

	List<ProductResponse> getOutputAfterRulefire();

	List<Product> findAll();
}
