package com.example.my_project.entity;

import java.util.List;

public class CategoryProduct {
	private List<Product> products;
	private List<Category> categories;
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
