package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.entity.Product;
import com.example.demo.model.CreateProductModel;
import com.example.demo.model.ProductModel;

import java.util.List;

@Mapper
public interface ProductMapper {
	
	public static final ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
	
	Product createProductModelToProductEntity(CreateProductModel productModel);
	
	ProductModel productEntityToProductModel(Product productEnity);
	
	List<ProductModel> listProductEntityToListProductModel(List<Product> productEntity);

}
