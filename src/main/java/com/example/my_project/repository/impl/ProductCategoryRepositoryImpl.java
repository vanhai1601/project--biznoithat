package com.example.my_project.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.my_project.dto.CategoryDTO;
import com.example.my_project.dto.ProductCategoryDTO;
import com.example.my_project.dto.ProductDTO;
import com.example.my_project.entity.Category;
import com.example.my_project.entity.Product;
import com.example.my_project.repository.ProductCategoryRepository;
import com.example.my_project.service.CategoryService;
import com.example.my_project.tranform.CategoryTranform;
import com.example.my_project.tranform.ProductCategoryTranform;
import com.example.my_project.tranform.ProductTranform;

@Repository
public class ProductCategoryRepositoryImpl implements ProductCategoryRepository {
	private SessionFactory sessionFactory;
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	public ProductCategoryRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public ProductCategoryDTO getAllProductCategory() {
		// TODO Auto-generated method stub
		ProductCategoryTranform pcTranform = new ProductCategoryTranform();
		ProductTranform productTranform = new ProductTranform(categoryService);
		CategoryTranform categoryTranform = new CategoryTranform();
		
		Session session = sessionFactory.getCurrentSession();
		Query<Product> query1 = session.createQuery("select p from Product p");
		List<Product> listProducts = query1.list();
		Query<Category> query2 = session.createQuery("select c from Category c");
		List<Category> listCategories = query2.list();
		
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
		
		for(Product p:listProducts) {
			ProductDTO productDTO = productTranform.apply(p);
			productDTOs.add(productDTO);
		}
		for(Category c:listCategories) {
			CategoryDTO categoryDTO = categoryTranform.apply(c);
			categoryDTOs.add(categoryDTO);
		}
		for(Product p:listProducts) {
			ProductDTO productDTO = productTranform.apply(p);
			productDTOs.add(productDTO);
		}
		ProductCategoryDTO productCategoryDTO = pcTranform.apply(productDTOs, categoryDTOs);
		return productCategoryDTO;
	}

}
