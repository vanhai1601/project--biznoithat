package com.example.my_project.tranform;

import java.util.List;

import com.example.my_project.dto.CategoryDTO;
import com.example.my_project.dto.ProductCategoryDTO;
import com.example.my_project.dto.ProductDTO;

public class ProductCategoryTranform {
	public ProductCategoryDTO apply(List<ProductDTO> products, List<CategoryDTO> categories){
		ProductCategoryDTO pc = new ProductCategoryDTO();
		pc.setCategoryDTOs(categories);
		pc.setProductDTOs(products);
		return pc;
	}
}
