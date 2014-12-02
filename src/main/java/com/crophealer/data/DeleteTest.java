package com.crophealer.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.crophealer.domain.Country;

@Component
public class DeleteTest {

	// Injected database connection:
	@PersistenceContext
	private EntityManager em;

	// TODO rewrite the persistance/db functions

	@Transactional
	public void persist(Country form) {
		em.persist(form);
	}

	public void getAllRequests() {
		Query query = em.createQuery("DELETE FROM Country c");
		int i = query.executeUpdate();
	}
}
