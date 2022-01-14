package com.example.my_project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.my_project.entity.Product;
import com.example.my_project.repository.ProductRepository;
import com.example.my_project.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Product findByProductCode(String code) {
		return productRepository.findByProductCode(code);

	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id);
	}

	@Override
	@Transactional
	public void insert(Product p) {
		// TODO Auto-generated method stub
		productRepository.insert(p);
	}

	@Override
	@Transactional
	public void update(Product p) {
		// TODO Auto-generated method stub
		productRepository.update(p);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Product product = productRepository.findById(id);
		productRepository.delete(product);
	}

	@Override
	@Transactional
	public List<Product> findPageable(int position, int pageSize) {
		// TODO Auto-generated method stub
		return productRepository.findPageable(position, pageSize);
	}

	@Override
	public long totalItem() {
		// TODO Auto-generated method stub
		return productRepository.totalItem();
	}

	@Override
	public List<Product> findBySort(int position, int pageSize,int sort) {
		// TODO Auto-generated method stub
		return productRepository.findBySort(position, pageSize, sort);
	}

	@Override
	public List<Product> findByName(String nameProduct, int position, int pageSize) {
		// TODO Auto-generated method stub
		return productRepository.findByName(nameProduct, position, pageSize);
	}
}
