package com.example.my_project.service;

import java.util.List;

import com.example.my_project.entity.Product;

public interface ProductService {
	Product findByProductCode(String code);

	List<Product> findAll();
	List<Product> findPageable(int position, int pageSize);
	List<Product> findBySort(int position, int pageSize,int sort);
	List<Product> findByName(String nameProduct,int position, int pageSize);
	
	long totalItem();

	Product findById(long id);

	void insert(Product p);

	void update(Product p);

	void delete(Long id);
}
