package com.example.demo.entity;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "product")
public class Product {
	
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
    
    @Column(name = "product_name", nullable = false, unique = true)
    private String productName;
    
    @Column(name = "price", nullable = false)
    private long price;
    
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @UpdateTimestamp
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

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", price=" + price + "]";
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
