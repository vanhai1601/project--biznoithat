package com.example.my_project.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.my_project.entity.Category;
import com.example.my_project.repository.CategoryRepository;
@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

	private SessionFactory sessionFactory;

	@Autowired
	public CategoryRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Category> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<Category> query = session.createQuery("select c from Category c");
		List<Category> list = query.list();
		return list;
	}

	@Override
	public Category findById(long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(Category.class, id);
	}

	@Override
	public void insert(Category c) {
		Session session = sessionFactory.getCurrentSession();
		session.save(c);
	}

	@Override
	public void update(Category c) {
		Session session = sessionFactory.getCurrentSession();
		session.update(c);
	}

	@Override
	public void delete(Category c) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(c);

	}

	@Override
	public Category findByCategoryCode(String code) {
		Session session = sessionFactory.getCurrentSession();
		Query<Category> query = session.createQuery("select c from Category c where c.codeCategory = '" + code + "'",
				Category.class);
		List<Category> resultList = query.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		}
		throw new RuntimeException("More than one Role returned");
	}

}
