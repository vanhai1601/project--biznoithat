package com.example.my_project.repository;

import java.util.List;

import com.example.my_project.entity.Product;

public interface ProductRepository {
	Product findByProductCode(String code);
    
	List<Product> findAll();
	List<Product> findBySort(int position, int pageSize, int sort);
	List<Product> findPageable(int position, int pageSize);
	
	long totalItem();
	
	Product findById(long id);
	List<Product> findByName(String nameProduct,int position, int pageSize);

	void insert(Product p);

	void update(Product p);

	void delete(Product p);
	
}
