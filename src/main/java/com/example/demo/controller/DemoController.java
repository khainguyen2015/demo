package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.event.producer.ProductEventProducer;
import com.example.demo.event.streamprocessing.StreamProcessingService;
import com.example.demo.model.CreateProductModel;
import com.example.demo.model.CreateUserModel;
import com.example.demo.model.ProductModel;
import com.example.demo.model.UserModel;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class DemoController {
	
	private ProductService productService;
	
	private ProductEventProducer productEventProducer;
	
	private UserService userService;
	
	private StreamProcessingService streamProcessingService;
	
	@Autowired
	public DemoController(ProductService productService, ProductEventProducer productEventProducer, UserService userService, StreamProcessingService streamProcessingService) {
		this.productService = productService;
		this.productEventProducer = productEventProducer;
		this.userService = userService;
		this.streamProcessingService = streamProcessingService;
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
	public ResponseEntity<String> createProduct(@RequestBody @Valid @NotNull CreateProductModel product) {
		String createdProductId = productService.createProduct(product);
		return new ResponseEntity<>(createdProductId, HttpStatus.CREATED);
	}
	
	@PostMapping("/user")
	public ResponseEntity<String> createUser(@RequestBody CreateUserModel user) {
		String createdUserId = userService.createUser(user);
		return new ResponseEntity<>(createdUserId, HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<UserModel> readUserById(@PathVariable("id") String userId) {
		UserModel user = userService.readUserById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PutMapping("/stream-processing/start")
	public ResponseEntity<String> startStreamProcessing() {
		streamProcessingService.startStreamProcessing();
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	@PutMapping("/stream-processing/stop")
	public ResponseEntity<String> stopStreamProcessing() {
		streamProcessingService.stopStreamProcessing();
		return new ResponseEntity<>("", HttpStatus.OK);
	}
}
