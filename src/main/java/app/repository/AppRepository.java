package app.repository;

import app.dao.DaoImpl;
import app.entity.CompPart;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AppRepository {

	private final SessionFactory sessionFactory;

	@Autowired
	public AppRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public <T> Serializable create(final T entity) {
		return sessionFactory.getCurrentSession().save(entity);
	}

	public <T> T update(final T entity) {
		sessionFactory.getCurrentSession().update(entity);
		return entity;
	}

	public <T> void delete(final T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<CompPart> fetchAll() {
		List<Object[]> list = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM part").list();
		List<CompPart> parts = new ArrayList<>();
		DaoImpl.createList(list, parts);
		return parts;
//		return sessionFactory.getCurrentSession().createQuery(" FROM "+entityClass.getName()).list();
	}

	@SuppressWarnings("rawtypes")
	public <T> List fetchAll(String query) {
		return sessionFactory.getCurrentSession().createSQLQuery(query).list();
	}

	@SuppressWarnings("unchecked")
	public <T> T fetchById(Serializable id, Class<T> entityClass) {
		return sessionFactory.getCurrentSession().get(entityClass, id);
	}
}

