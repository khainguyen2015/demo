package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.event.producer.ProductEventProducer;
import com.example.demo.model.CreateProductModel;
import com.example.demo.model.ProductModel;
import com.example.demo.service.ProductService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class DemoController {
	
	private ProductService productService;
	
	private ProductEventProducer productEventProducer;
	
	@Autowired
	public DemoController(ProductService productService, ProductEventProducer productEventProducer) {
		this.productService = productService;
		this.productEventProducer = productEventProducer;
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductModel>> searchProductByName(@RequestHeader(value = "userId") String userId, @RequestParam(required = false, name = "name") String productName) {
		List<ProductModel> products = productService.searchProductByName(productName);
		productEventProducer.writeSearchEvent(userId, productName);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductModel> readProductById(@RequestHeader(value = "userId") String userId, @PathVariable("id") String productId) {
		ProductModel product = productService.readProductById(productId);
		productEventProducer.writeClickEvent(userId, product);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@PostMapping("/products")
	public ResponseEntity<String> createProduct(@RequestBody CreateProductModel product) {
		String createdProductId = productService.createProduct(product);
		return new ResponseEntity<>(createdProductId, HttpStatus.CREATED);
	}
	
	

}
