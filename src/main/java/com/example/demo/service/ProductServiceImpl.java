package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.CreateProductModel;
import com.example.demo.model.ProductModel;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	
	
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<ProductModel> searchProductByName(String productName) {
		List<Product> products = new ArrayList<>();
		
		if (Objects.isNull(productName) || productName.isBlank()) {
			products = productRepository.findAll();
		} else {
			products = productRepository.findByNameContaining(productName);
		}
		
		return ProductMapper.INSTANCE.listProductEntityToListProductModel(products);
	}

	@Override
	public ProductModel readProductById(String productId) {
		Optional<Product> productEntity = productRepository.findById(productId);
		if (productEntity.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Product with id [%s] not found.", productId));
		}
		
		Product product = productEntity.get();
		
		return ProductMapper.INSTANCE.productEntityToProductModel(product);
	}

	@Override
	public String createProduct(CreateProductModel productModel) {
		Product productEntity = ProductMapper.INSTANCE.createProductModelToProductEntity(productModel);
		productEntity = productRepository.save(productEntity);
		return productEntity.getId();
	}

}
