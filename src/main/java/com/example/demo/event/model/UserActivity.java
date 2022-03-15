package com.example.demo.event.model;

public class UserActivity {
	
	private String userId;
	
	private String userName;
	
	private String searchTerm;
	
	private String productViewedId;
	
	private String productViewedName;

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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the searchTerm
	 */
	public String getSearchTerm() {
		return searchTerm;
	}

	/**
	 * @param searchTerm the searchTerm to set
	 */
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	/**
	 * @return the productViewedId
	 */
	public String getProductViewedId() {
		return productViewedId;
	}

	/**
	 * @param productViewedId the productViewedId to set
	 */
	public void setProductViewedId(String productViewedId) {
		this.productViewedId = productViewedId;
	}

	/**
	 * @return the productViewedName
	 */
	public String getProductViewedName() {
		return productViewedName;
	}

	/**
	 * @param productViewedName the productViewedName to set
	 */
	public void setProductViewedName(String productViewedName) {
		this.productViewedName = productViewedName;
	}
	
}
