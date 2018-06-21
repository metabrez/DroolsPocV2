package com.app.drools.api;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.drools.core.command.assertion.AssertEquals;
import org.drools.core.common.DefaultFactHandle;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.util.ReflectionTestUtils;

import com.app.drools.api.model.Product;
import com.app.drools.api.model.ProductResponse;
import com.app.drools.api.repo.ProductRepo;
import com.app.drools.api.service.ProductServiceImpl;

import junit.framework.Assert;

public class TestProductServiceImpl {

	ProductRepo productRepo;
	ProductServiceImpl productServiceImpl;

	KieContainer kieContainer;
	Product product = new Product();
	
	KieSession kieSession;
	AgendaEventListener agendaEventListener;
	
	@Before
	public void setUp() {
		productRepo = Mockito.mock(ProductRepo.class);
		kieContainer = Mockito.mock(KieContainer.class);
		productServiceImpl = new ProductServiceImpl(kieContainer);
		kieSession = Mockito.mock(KieSession.class);
		agendaEventListener = Mockito.mock(AgendaEventListener.class);
		
		

			
		
		ReflectionTestUtils.setField(productServiceImpl, "kieContainer", kieContainer, KieContainer.class);
		ReflectionTestUtils.setField(productServiceImpl, "productRepo", productRepo, ProductRepo.class);
		//ReflectionTestUtils.setField(productServiceImpl, "kieSession", kieSession, KieSession.class);
		//ReflectionTestUtils.setField(productServiceImpl, "agendaEventListener", agendaEventListener, AgendaEventListener.class);

		// mock and setField of kiession and addEventListener

		product.setType("diamond");
		product.setQuality("a");
		product.setMade("uk");
		product.setPrice(100);
		product.setPurchasedDate(new Date(12 - 4 - 2015));
	}

	@Test
	public void testSave() {
		when(productRepo.save(product)).thenReturn(product);
		Product p = productServiceImpl.save(product);
		Assert.assertEquals(p, product);
	}

	@Test
	public void testApplyDiscount() {
		
	    
		List<Product> productList = new ArrayList<>();
		productList.add(product);				
		
		/*for(Product p: productList) {
			when(kieSession.insert(product)).thenReturn(f1);
		}*/
		
		doNothing().when(kieSession).addEventListener(agendaEventListener);
		when(kieSession.fireAllRules()).thenReturn(3);
		doNothing().when(kieSession).dispose();
		
		List<ProductResponse> productResponseList = new ArrayList<>();
		
		productServiceImpl.applyDiscount(productList);
		
		productResponseList = productServiceImpl.getOutputAfterRulefire();
		Assert.assertEquals(true, productResponseList.size() > 0);
		

	}

	@Test
	public void testFindAll() {
		List<Product> products = new ArrayList<>();
		products.add(product);
		System.out.println(products);
		// when(productRepo.findAll(any(Sort.class))).thenReturn(products);
		when(productRepo.findAll()).thenReturn(products);
		List<Product> expectedList = new ArrayList<>();
		expectedList = productServiceImpl.findAll();
		Assert.assertEquals(expectedList, products);
	}

}
