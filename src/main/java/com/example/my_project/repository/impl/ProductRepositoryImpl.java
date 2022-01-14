package com.example.my_project.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.my_project.entity.Product;
import com.example.my_project.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	private SessionFactory sessionFactory;

	@Autowired
	public ProductRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Product findByProductCode(String code) {
		Session session = sessionFactory.getCurrentSession();
		Query<Product> query = session.createQuery("select p from Product p where p.codeProduct = '" + code + "'",
				Product.class);
		List<Product> resultList = query.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		}
		throw new RuntimeException("More than one Role returned");
	}

	@Override
	public List<Product> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<Product> query = session.createQuery("select p from Product p");
		List<Product> list = query.list();
		return list;
	}

	@Override
	public Product findById(long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(Product.class, id);
	}

	@Override
	public void insert(Product p) {
		Session session = sessionFactory.getCurrentSession();
		session.save(p);

	}

	@Override
	public void update(Product p) {
		Session session = sessionFactory.getCurrentSession();
		session.update(p);
	}

	@Override
	public void delete(Product p) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(p);

	}

	@Override
	public List<Product> findPageable(int position, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Query<Product> query = session.createQuery("select p from Product p").setFirstResult(position).setMaxResults(pageSize);
		List<Product> list = query.list();
		return list;
	}

	@Override
	public long totalItem() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.createQuery("select count(*) from Product").getSingleResult();
	}

	@Override
	public List<Product> findBySort(int position, int pageSize, int sort) {
		Session session = sessionFactory.getCurrentSession();
		Query<Product> query;
		if(sort == 1) {
			query = session.createQuery("select p from Product p order by p.price ").setFirstResult(position).setMaxResults(pageSize);
		} else {
			query = session.createQuery("select p from Product p order by p.price desc ").setFirstResult(position).setMaxResults(pageSize);
		}
		
		List<Product> list = query.list();
		return list;
	}

	@Override
	public List<Product> findByName(String name,int position, int pageSize) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query<Product> query = session.createQuery("select p from Product p where p.nameProduct like '%" + name + "%'");
		List<Product> resultList = query.setFirstResult(position).setMaxResults(pageSize).list();
		return resultList;
	}	
}
