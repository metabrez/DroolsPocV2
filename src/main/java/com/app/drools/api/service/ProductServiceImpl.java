package com.app.drools.api.service;

import com.app.drools.api.controller.HomeController;
import com.app.drools.api.listener.TrackingAgendaEventListener;
import com.app.drools.api.model.Product;
import com.app.drools.api.model.ProductResponse;
import com.app.drools.api.repo.ProductRepo;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.command.Command;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.internal.utils.KieService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Match;
import org.kie.internal.command.CommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	private ProductRepo productRepo;
	private KieContainer kieContainer;

	private List<Integer> ruleIdList;

	@Autowired
	public ProductServiceImpl(KieContainer kieContainer) {
		this.kieContainer = kieContainer;

	}

	@Override
	public void applyDiscount(Product product) {
		KieSession kSession = kieContainer.newKieSession("ksession-rule");
		AgendaEventListener trackingAgendaEventListener = new TrackingAgendaEventListener();

		kSession.insert(product);

		kSession.addEventListener(trackingAgendaEventListener);
		kSession.fireAllRules();

		ruleIdList = ((TrackingAgendaEventListener) trackingAgendaEventListener).getRuleId();

		kSession.dispose();

	}

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

}
