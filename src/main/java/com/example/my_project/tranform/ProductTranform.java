package com.example.my_project.tranform;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.my_project.dto.ProductDTO;
import com.example.my_project.entity.Product;
import com.example.my_project.service.CategoryService;

public class ProductTranform {
	private CategoryService categoryService;
	
	@Autowired
	public ProductTranform(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public Product apply(ProductDTO dto) {
		Product product = new Product();
		if(categoryService.findByCategoryCode(dto.getCategory()) != null) {
			product.setNameProduct(dto.getName());
			product.setCodeProduct(dto.getCode());
			product.setCategory(categoryService.findByCategoryCode(dto.getCategory()));
			product.setImg(dto.getImg());
			product.setQuantity(dto.getQuantity());
			product.setQuantitySold(dto.getQuantitySold());
			product.setPrice(dto.getPrice());
			product.setSale(dto.getSale());
			product.setDescriptions(dto.getDescriptions());
		}
		return product;
	}
	
	public ProductDTO apply(Product p) {
		ProductDTO dto = new ProductDTO();
		dto.setName(p.getNameProduct());
		dto.setCode(p.getCodeProduct());
		dto.setImg(p.getImg());
		dto.setNumber(1);
		dto.setQuantity(p.getQuantity());
		dto.setQuantitySold(p.getQuantitySold());
		dto.setPrice(p.getPrice());
		dto.setSale(p.getSale());
		dto.setDescriptions(p.getDescriptions());
		dto.setCategory(p.getCategory().getCodeCategory());
		dto.setId(p.getId());
		return dto;
	}
	
}
