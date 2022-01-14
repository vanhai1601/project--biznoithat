package com.example.my_project.dto;

import com.example.my_project.dto.response.IResponseData;

import java.io.Serializable;

public class CategoryDTO implements IResponseData, Serializable {
	private long id;
	private String categoryName;
	private String categoryCode;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

}
