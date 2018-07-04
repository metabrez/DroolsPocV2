/*package com.app.drools.api;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.app.drools.api.controller.HomeController;
import com.app.drools.api.model.Product;
import com.app.drools.api.repo.ProductRepo;
import com.app.drools.api.service.ProductServiceImpl;

public class HomeControllerTest {
	
	HomeController homeController;
	ProductServiceImpl  productServiceImpl;
	Product product = new Product();

	@Before
	public void setUp() {
		
		productServiceImpl = Mockito.mock(ProductServiceImpl.class);
		homeController = new HomeController(productServiceImpl);
		
		ReflectionTestUtils.setField(homeController, "productServiceImpl", productServiceImpl, ProductServiceImpl.class);
		
		// mock and setField of kiession and addEventListener
		
        product.setType("diamond");
        product.setQuality("a");
        product.setMade("uk");
        product.setPrice(100);
        product.setPurchasedDate(new Date(12-4-2015));
	}
	
	
	@Test
	public void testGetAllProduct() {
		List<Product> productList= new ArrayList<>();
		productList.add(product);
		when(productServiceImpl.findAll()).thenReturn(productList);
		//doNothing()
		
	}
}
*/