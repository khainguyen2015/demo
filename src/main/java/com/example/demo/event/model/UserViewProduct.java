package com.example.demo.event.model;

import com.example.demo.model.ProductModel;

public class UserViewProduct {
	
	private String userId;
	
	private ProductModel productClicked;

	public UserViewProduct(String userId, ProductModel productClicked) {
		this.userId = userId;
		this.productClicked = productClicked;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the productClicked
	 */
	public ProductModel getProductClicked() {
		return productClicked;
	}

	/**
	 * @param productClicked the productClicked to set
	 */
	public void setProductClicked(ProductModel productClicked) {
		this.productClicked = productClicked;
	}
}
