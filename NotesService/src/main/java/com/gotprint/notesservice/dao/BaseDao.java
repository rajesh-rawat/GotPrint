package com.gotprint.notesservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.Configuration;

import com.gotprint.notesservice.domain.object.Persistable;

/**
 * @author Rajesh Rawat
 * Base Dao which has basic operations like 
 * a) Session creation/end
 * b) Transaction creation/commit/rollback
 * c) SessionFactory creation 
 *
 */
public class BaseDao {

	private Session currentSession;

	private static SessionFactory sessionFactory;

	static {
		sessionFactory = getSessionFactory();
	}

	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		final StandardServiceRegistry registry = builder.build();
		configuration.setSessionFactoryObserver(new SessionFactoryObserver() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 8839946326443611828L;

			public void sessionFactoryCreated(SessionFactory arg0) {
			}

			public void sessionFactoryClosed(SessionFactory arg0) {
				((StandardServiceRegistryImpl) registry).destroy();
			}
		});
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		return sessionFactory;
	}

	public Session openCurrentSession() {
		currentSession = sessionFactory.openSession();
		return currentSession;
	}

	public void closeCurrentSession() {
		currentSession.close();
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public Transaction createTransaction() {
		return currentSession.beginTransaction();
	}

	public void rollbackTransaction(Transaction tx) {
		if (tx != null) {
			tx.rollback();
		}
	}

	public void commitTransaction(Transaction tx) {
		if (tx != null) {
			tx.commit();
		}
	}

	public static void closeSessionFactory() {
		if (sessionFactory != null && !sessionFactory.isClosed())
			sessionFactory.close();
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public void persist(Persistable entity) {
		getCurrentSession().persist(entity);
	}

	public void update(Persistable entity) {
		getCurrentSession().update(entity);
	}

	public Persistable findById(long id, @SuppressWarnings("rawtypes") Class clazz) {
		return (Persistable) getCurrentSession().get(clazz, id);
	}

	public void delete(Persistable entity) {
		getCurrentSession().delete(entity);
	}
}
