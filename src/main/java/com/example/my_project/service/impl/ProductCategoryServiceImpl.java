package com.example.my_project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.my_project.dto.ProductCategoryDTO;
import com.example.my_project.repository.ProductCategoryRepository;
import com.example.my_project.service.ProductCategoryService;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	
private ProductCategoryRepository productCategoryRepository;
	
	@Autowired
	public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
		this.productCategoryRepository = productCategoryRepository;
	}
	@Override
	public ProductCategoryDTO getAllProductCategory() {
		return productCategoryRepository.getAllProductCategory();
	}

}
