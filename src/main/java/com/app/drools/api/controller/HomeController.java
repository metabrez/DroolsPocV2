package com.app.drools.api.controller;

import com.app.drools.api.listener.TrackingAgendaEventListener;
import com.app.drools.api.model.Product;
import com.app.drools.api.model.ProductResponse;
import com.app.drools.api.service.ProductService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import io.swagger.annotations.ApiParam;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/getDiscount")
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ProductService productService;

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
	}

	private AgendaEventListener trackingAgendaEventListener;


	@Autowired
	public HomeController(ProductService productService) {
		this.productService = productService;

	}



	@RequestMapping(value = "/type/product", method = RequestMethod.POST)
	public ResponseEntity<List<Product>> save(@RequestBody List<Product> product) {
		long beginTime = System.currentTimeMillis();
		List<Product> saveProducts = productService.save(product);
		long responseTime = System.currentTimeMillis() - beginTime;
		logger.info("Response time for the call was " + responseTime);

		return new ResponseEntity<>(saveProducts, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/type/product", method = RequestMethod.GET)
	public List<ProductResponse> getAllProduct() {
		long beginTime = System.currentTimeMillis();
		List<Product> inputProducts = productService.findAll();
		List<ProductResponse> productResponseList = new ArrayList<>();
		
		productService.applyDiscount(inputProducts);
		productResponseList=productService.getOutputAfterRulefire();
				
		long responseTime = System.currentTimeMillis() - beginTime;
		logger.info("Response time for the call was " + responseTime);

		return productResponseList;
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {

		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
