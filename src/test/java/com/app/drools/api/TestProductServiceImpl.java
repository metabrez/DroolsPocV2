package com.app.drools.api;

import static org.mockito.Mockito.when;

import java.util.Date;

import org.drools.core.command.assertion.AssertEquals;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import com.app.drools.api.model.Product;
import com.app.drools.api.repo.ProductRepo;
import com.app.drools.api.service.ProductServiceImpl;

import junit.framework.Assert;

public class TestProductServiceImpl{
	
	ProductRepo  pr;
	ProductServiceImpl productServiceImpl; 
	
	KieContainer kieContainer;
	
	@Before
	public void setUp() {
		pr = Mockito.mock(ProductRepo.class);
		kieContainer = Mockito.mock(KieContainer.class);
		productServiceImpl = new ProductServiceImpl(kieContainer);
		
		//ReflectionTestUtils.setField(productServiceImpl, "kieContainer", kieContainer, KieContainer.class);
		ReflectionTestUtils.setField(productServiceImpl, "pr", pr, ProductRepo.class);
		
		// mock and setField of kiession and addEventListener
	}
	
	@Test
	public void testSave() {
		Product product = new Product();
        product.setType("diamond");
        product.setQuality("a");
        product.setMade("uk");
        product.setPrice(100);
        product.setPurchasedDate(new Date(12-4-2015));
        
        when(pr.save(product)).thenReturn(product);

        
        
        Product p = productServiceImpl.save(product);
        Assert.assertEquals(p, product);
             
		
	}
	@Test
	public void testApplyDiscount() {
		
		when().thenReturn(); // for kiesession and addeventlistner
		call method applyDiscount();
		assertEquals(expected, actual) // test
	}
	
	
}
