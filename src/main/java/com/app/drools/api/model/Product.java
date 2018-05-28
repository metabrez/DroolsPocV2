package com.app.drools.api.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Product {
	@Id
	@GeneratedValue
	private int id;
	private String type;
	private String quality;
	private String made;
	private int price;
	
	// SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");

	//@DateTimeFormat(pattern="dd-MM-yyyy")
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	 
	private Date purchasedDate;
	
	private int rule;

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getMade() {
		return made;
	}

	public void setMade(String made) {
		this.made = made;
	}

	private int discount;
	// private String ruleName;

	public Product() {

	}

	public Product(String type, int discount) {
		this.type = type;
		this.discount = discount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	

	public int getRule() {
		return rule;
	}

	public void setRule(int rule) {
		this.rule = rule;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(Date purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", type=" + type + ", quality=" + quality + ", made=" + made + ", price=" + price
				+ ", purchasedDate=" + purchasedDate + ", rule=" + rule + ", discount=" + discount + "]";
	}

}