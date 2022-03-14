package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
	
	@Query("select p from Product p where p.productName like %:name%")
	public List<Product> findByNameContaining(@Param("name") String name);

}
