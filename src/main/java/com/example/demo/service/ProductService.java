package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CreateProductModel;
import com.example.demo.model.ProductModel;

public interface ProductService {
	
	List<ProductModel> searchProductByName(String productName);
	
	ProductModel readProductById(String productId);
	
	String createProduct(CreateProductModel productModel);

}
