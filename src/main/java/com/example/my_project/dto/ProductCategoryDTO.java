package com.example.my_project.dto;

import java.util.List;
public class ProductCategoryDTO {
	List<ProductDTO> productDTOs;
	List<CategoryDTO> categoryDTOs;
	public List<ProductDTO> getProductDTOs() {
		return productDTOs;
	}
	public void setProductDTOs(List<ProductDTO> productDTOs) {
		this.productDTOs = productDTOs;
	}
	public List<CategoryDTO> getCategoryDTOs() {
		return categoryDTOs;
	}
	public void setCategoryDTOs(List<CategoryDTO> categoryDTOs) {
		this.categoryDTOs = categoryDTOs;
	}
	
}
