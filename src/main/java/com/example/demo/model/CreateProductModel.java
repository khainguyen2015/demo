package com.example.demo.model;

import javax.validation.constraints.NotNull;

public class CreateProductModel {
	
	private String productName;
	
	private long price;
	
	public CreateProductModel productName(String productName) {
		this.productName = productName;
		return this;
	}
	
	public CreateProductModel price(long price) {
		this.price = price;
		return this;
	}

	/**
	 * @return the productName
	 */
	@NotNull
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the price
	 */
	@NotNull
	public long getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(long price) {
		this.price = price;
	}

}
