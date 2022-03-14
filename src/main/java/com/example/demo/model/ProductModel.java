package com.example.demo.model;

import java.time.OffsetDateTime;

public class ProductModel {
	
	private String id;
	
	private String productName;
	
	private long price;
	
	private OffsetDateTime createdAt;
	
	private OffsetDateTime updatedAt;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the productName
	 */
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
	public long getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(long price) {
		this.price = price;
	}

	/**
	 * @return the createdAt
	 */
	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
