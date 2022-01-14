package com.example.my_project.repository.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.my_project.entity.User;
import com.example.my_project.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	private SessionFactory sessionFactory;

	@Autowired
	public UserRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<User> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("select u from User u");
		List<User> list = query.list();
		return list;
	}

	@Override
	public User findById(long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(User.class, id);
	}

	@Override
	public void insert(User u) {
		Session session = sessionFactory.getCurrentSession();
		session.save(u);

	}

	@Override
	public void update(User u) {
		Session session = sessionFactory.getCurrentSession();
		session.update(u);
	}

	@Override
	public void delete(User u) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(u);
	}

	@Override
	public User findByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		Predicate equal = criteriaBuilder.equal(root.get("username"), username);

		criteriaQuery.select(root).where(equal);
		Query<User> query = session.createQuery(criteriaQuery);
		List<User> users = query.getResultList();
		if (users.size() > 0 && users.get(0) != null) {
			return users.get(0);
		}
		throw new RuntimeException("User not found");
	}

}
