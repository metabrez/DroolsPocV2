package com.app.drools.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.drools.api.listener.TrackingAgendaEventListener;
import com.app.drools.api.model.Product;
import com.app.drools.api.model.ProductResponse;
import com.app.drools.api.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	private ProductRepo productRepo;
	
	/*
	 * @Autowired TrackingAgendaEventListener trackingAgendaEventListener;
	 */
	private KieContainer kieContainer;
	// private final KieSession kSession;

	private List<Integer> ruleIdList;
	private List<ProductResponse> outputAfterRulefire;

	@Autowired
	public ProductServiceImpl(KieContainer kieContainer) {
		this.kieContainer = kieContainer;

	}

	/*
	 * @Override public void applyDiscount(Product product) { KieSession kSession =
	 * kieContainer.newKieSession("ksession-rule"); AgendaEventListener
	 * trackingAgendaEventListener = new TrackingAgendaEventListener();
	 * 
	 * 
	 * //kSession.execute(CommandFactory.newInsertElements(inputProducts));
	 * kSession.insert(product);
	 * 
	 * 
	 * kSession.addEventListener(trackingAgendaEventListener);
	 * kSession.fireAllRules();
	 * 
	 * ruleIdList = ((TrackingAgendaEventListener)
	 * trackingAgendaEventListener).getRuleId();
	 * 
	 * kSession.dispose();
	 * 
	 * 
	 * }
	 */

	@Override
	public List<Integer> getRuleIdList() {
		return ruleIdList;
	}

	@Override
	public Product save(Product product) {

		return productRepo.save(product);

	}

	@Override
	public List<Product> findAll() {
		return productRepo.findAll();
	}

	@Override
	public List<Product> save(List<Product> product) {
		return productRepo.save(product);
	}

	@Override
	public void applyDiscount(List<Product> products) {
		long beginTime = System.currentTimeMillis();
		KieSession kSession = kieContainer.newKieSession("ksession-rule");
		AgendaEventListener trackingAgendaEventListener = new TrackingAgendaEventListener();

		for (Product daru : products) {
			
			kSession.insert(daru);
			
		}
		kSession.addEventListener(trackingAgendaEventListener);
		//ruleIdList = ((TrackingAgendaEventListener) trackingAgendaEventListener).getRuleId();
		
		kSession.fireAllRules();

		kSession.dispose();

		outputAfterRulefire = new ArrayList<>();
		/*Map<String, List<Integer>> ruleIdMap = new HashMap<>();
		ruleIdMap = ((TrackingAgendaEventListener) trackingAgendaEventListener).getRuleIdMap();
		Object[] keys = ruleIdMap.keySet().toArray();
		for(Object o: keys) {
			System.out.println(o.toString());
		}*/
		
		for (Product product : products) {
			ProductResponse response = new ProductResponse();
			response.setType(product.getType());
			response.setQuality(product.getQuality());
			response.setMade(product.getMade());
			response.setPrice(product.getPrice());
			response.setPurchasedDate(product.getPurchasedDate());
			response.setDiscount(product.getDiscount());
			//response.setRule(ruleIdMap.get(keys[i]));
			//response.setRule(getRuleIdList());
			response.setRule(product.getRuleList());
			outputAfterRulefire.add(response);
		}

	}

	public List<ProductResponse> getOutputAfterRulefire() {
		return outputAfterRulefire;
	}

}
