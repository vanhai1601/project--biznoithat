package com.example.my_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.my_project.dto.ProductCategoryDTO;
import com.example.my_project.service.ProductCategoryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/productcategory")
public class ProductCategoryController {
	private ProductCategoryService productCategoryService;

	@Autowired
	public ProductCategoryController(ProductCategoryService productCategoryService) {
		this.productCategoryService = productCategoryService;
	}
	
	@GetMapping
	public ProductCategoryDTO getAll() {
		return productCategoryService.getAllProductCategory();
	}
}
