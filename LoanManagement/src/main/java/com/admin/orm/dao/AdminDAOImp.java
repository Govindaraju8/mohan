package com.admin.orm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.admin.orm.model.Users;

public class AdminDAOImp implements AdminDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void saveUser(Users u) {
		em.persist(u);
	}

	@Override
	public List<Users> getAllusers() {
		return em.createQuery("SELECT u FROM Users u").getResultList();
	}
}
