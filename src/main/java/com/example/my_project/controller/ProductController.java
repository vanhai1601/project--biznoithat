package com.example.my_project.controller;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.my_project.dto.ProductDTO;
import com.example.my_project.entity.Product;
import com.example.my_project.service.CategoryService;
import com.example.my_project.service.ProductService;
import com.example.my_project.tranform.ProductTranform;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;

	@Autowired
	public ProductController(ProductService productService) {
		// TODO Auto-generated constructor stub
		this.productService = productService;
	}

	@PostMapping
	public Product insertProduct(@RequestBody ProductDTO dto) {
		ProductTranform productTranform = new ProductTranform(categoryService);
		Product product = productTranform.apply(dto);
		productService.insert(product);
		return product;
	}
	
	@GetMapping("/pagination")
	public List<ProductDTO> productPage(@RequestParam("page") int position) {
		ProductTranform productTranform = new ProductTranform(categoryService);
		List<ProductDTO> productDTOs = new ArrayList<>();
		int pageSize = 8;
		List<Product> products = productService.findPageable((position - 1)*pageSize, pageSize);
		for (Product p : products) {
			ProductDTO productDTO = productTranform.apply(p);
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}
	
	@GetMapping("/sort")
	public List<ProductDTO> productSort(@RequestParam("page") int position,@RequestParam("sort") int sort){
		ProductTranform productTranform = new ProductTranform(categoryService);
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		int pageSize = 8;
		List<Product> products = productService.findBySort((position - 1)*pageSize, pageSize,sort);
		for (Product p : products) {
			ProductDTO productDTO = productTranform.apply(p);
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}
	
	
	@GetMapping("/search")
	public List<ProductDTO> findByName(@RequestParam("page") int position, @RequestBody String name){
		ProductTranform productTranform = new ProductTranform(categoryService);
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		int pageSize = 8;
		List<Product> products = productService.findByName(name, (position -1)*pageSize, pageSize);
		for (Product p : products) {
			ProductDTO productDTO = productTranform.apply(p);
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}

	
	@GetMapping("/totalProduct")
	public long totalProduct() {
		return productService.totalItem();
	}
	
	@GetMapping
	public List<ProductDTO> allProduct() {
		ProductTranform productTranform = new ProductTranform(categoryService);
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		List<Product> products = productService.findAll();
		for (Product p : products) {
			ProductDTO productDTO = productTranform.apply(p);
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}
	@PutMapping("/{id}")
	public void updateProduct(@RequestBody ProductDTO dto, @PathVariable("id") Long id) {
		ProductTranform productTranform = new ProductTranform(categoryService);
		Product product = productService.findById(id);
		product.setNameProduct(dto.getName());
		product.setCodeProduct(dto.getCode());
		product.setCategory(categoryService.findByCategoryCode(dto.getCategory()));
		product.setImg(dto.getImg());
		product.setQuantity(dto.getQuantity());
		product.setQuantitySold(dto.getQuantitySold());
		product.setPrice(dto.getPrice());
		product.setSale(dto.getSale());
		product.setDescriptions(dto.getDescriptions());
		productService.update(product);
	}
	
	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public String deleteProducts(@PathVariable long id) {
		productService.delete(id);
		return "ok";
	}
}
