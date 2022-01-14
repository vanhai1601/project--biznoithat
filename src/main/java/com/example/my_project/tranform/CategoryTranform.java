package com.example.my_project.tranform;

import com.example.my_project.dto.CategoryDTO;
import com.example.my_project.entity.Category;

public class CategoryTranform {
	public Category apply(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setNameCategory(categoryDTO.getCategoryName());
		category.setCodeCategory(categoryDTO.getCategoryCode());
		return category;
	}
	public CategoryDTO apply(Category c) {
		CategoryDTO categoryDTO = new CategoryDTO();
		if(c.getId() != null) {
			categoryDTO.setId(c.getId());
		}
		categoryDTO.setCategoryName(c.getNameCategory());
		categoryDTO.setCategoryCode(c.getCodeCategory());
		return categoryDTO;
	}
	public void apply(Category category, CategoryDTO dto) {
		if (dto.getCategoryCode() != null)
			category.setCodeCategory(dto.getCategoryCode());
		if (dto.getCategoryName() != null)
			category.setNameCategory(dto.getCategoryName());
	}

}
