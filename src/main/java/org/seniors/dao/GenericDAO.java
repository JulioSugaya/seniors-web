package org.seniors.dao;


import static org.seniors.util.ValidationUtils.LOGGER;
import static org.seniors.util.ValidationUtils.isNotNullAndEmpty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

/**
 * <p>This is a generic class that provides a CRUD service.<br>
 * Another queries like: JPQL, NamedQueries, etc. 
 * Needed to be implemented by inheritance.
 * </p>
 * 
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 */
public abstract	 class GenericDAO<T, ID extends Serializable> {

	/**
	 * Factory Entity Manager
	 */
	protected static EntityManagerFactory emf = null;
	protected Class<T> persistentClass;
	
	static {
		connectDB();
	}
	
	/**
	 * @param clazz
	 */
	public GenericDAO(Class<T> clazz) {
		this.persistentClass = clazz;
	}
	
	private static synchronized void connectDB() {
		try {
			LOGGER.info("Creating connection pool...");

			emf = Persistence.createEntityManagerFactory("seniors_web");

			LOGGER.info("Entity manager creation done!");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error creating EntityManagerFactory, system will be finished!");
			System.exit(0);
		}
	}
	
	protected boolean reconnect(EntityManager em) {
		
		for (int i = 0; i < 3; i++) {
			try {

				em.getTransaction().begin();
				em.getTransaction().commit();
				
				return true;
			} catch (Throwable e) {
				LOGGER.error("Error reconnection session, attempt: " + (i+1)
						+ " waiting for 3 seconds to try again...", e);
				try {
					Thread.sleep(3000);
				} catch (Exception e2) {}
			}
		}
		
		return false;
	}
	
	public void beginTransaction() throws Exception {
		EntityManager em = null;

		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
		} catch (Exception e) {
			LOGGER.error(e.toString());
			throw e;
		}
	}
	
	/**
	 * Persist an entity on database.
	 * @param object - The object to be persisted
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public T persist(T object) throws Exception {
		
		EntityManager em = null;

		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			T mergeResult = em.merge(object);
			em.persist(mergeResult);
			em.getTransaction().commit();
			return mergeResult;
		} catch (Exception e) {
			
			LOGGER.error(e.toString());

			try {
				em.getTransaction().rollback();
			} catch (Exception e2) {
				throw e2;
			}
			
			throw e;
		} finally {
			em.close();
		}
	}
	
	/**
	 * Generic search by  primary key
	 * @param key The primary key described on T class
	 * @return Typified object
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public T search(ID key) throws Exception {
		
		T returnObject = null;
		
		EntityManager em = null;
		
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			returnObject = em.find(persistentClass, key);
			em.getTransaction().commit();
		} catch (Exception e) {
			
			LOGGER.error(e.toString());

			try {
				em.getTransaction().rollback();
			} catch (Exception e2) {
				if (reconnect(em)) {
					return search(key);
				}
				throw e2;
			}
			
			throw e;
		} finally {
			em.close();
		}

		return returnObject;
	}

	/**
	 * Return a full list with all typified objects
	 * @return Typified object List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> search() throws Exception {
		
		EntityManager em = null;
		
		List<T> objectList = null;
		
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			objectList = em.createQuery(
					"select t from " + persistentClass.getSimpleName() + " t")
					.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			
			LOGGER.error(e.toString());

			try {
				em.getTransaction().rollback();
			} catch (Exception e2) {
				if (reconnect(em)) {
					return search();
				}
				throw e2;
			}
			
			throw e;
		} finally {
			em.close();
		}

		return objectList;
	}
	
	
	/**
	 * Return a list with all typified objects.
	 * @param The attribute name of filtered object.
	 * @return A typified object list.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> orderedSearch(String fieldName) throws Exception {
		
		EntityManager em = null;
		
		List<T> retorno = null;
		
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			String simpleName = persistentClass.getSimpleName();
			retorno = em.createQuery(
					"select t from " + simpleName + " t order by t."+fieldName)
					.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			
			LOGGER.error(e.toString());

			try {
				em.getTransaction().rollback();
			} catch (Exception e2) {
				if (reconnect(em)) {
					return search();
				}
				throw e2;
			}
			
			throw e;
		} finally {
			em.close();
		}

		return retorno;
	}
	
	
	/**
	 * 
	 * Return a filtered list by attribute value
	 * @param attribute The attribute to be filtered
	 * @param value The attribute value
	 * @return A typified object list by attribute value
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> search(String attribute, Object value) throws Exception {

		EntityManager em = null;
		
		List<T> searchList = null;
		
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query = em.createQuery(
					"select t from "
					+ persistentClass.getSimpleName() + " t where t."
					+ attribute + " = :attribute");
			query.setParameter("attribute", value);
			searchList = query.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			
			LOGGER.error(e.toString());

			try {
				em.getTransaction().rollback();
			} catch (Exception e2) {
				if (reconnect(em)) {
					return search(attribute, value);
				}
				throw e2;
			}
			
			throw e;
		} finally {
			em.close();
		}

		return searchList;
	}
	
	/**
	 * 
	 * Return a filtered list by attribute value
	 * @param attribute The attribute to be filtered
	 * @param value The attribute value
	 * @return A typified object list by attribute value
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> search(String attribute, Object value, String nomeDtInicio, Date datainicio, String nomeDtFim, Date datafim) throws Exception {

		EntityManager em = null;
		
		List<T> searchList = null;
		
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query = em.createQuery(
					"select t from "
					+ persistentClass.getSimpleName() + " t where t."
					+ attribute + " = :attribute"
					+ " and " + nomeDtInicio + " >= :datainicio"
					+ " and " + nomeDtFim + " <= :datafim");
			query.setParameter("attribute", value);
			query.setParameter("datainicio", datainicio);
			query.setParameter("datafim", datafim);
			searchList = query.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			
			LOGGER.error(e.toString());

			try {
				em.getTransaction().rollback();
			} catch (Exception e2) {
				if (reconnect(em)) {
					return search(attribute, value);
				}
				throw e2;
			}
			
			throw e;
		} finally {
			em.close();
		}

		return searchList;
	}
	
	/**
	 * 
	 * Return a filtered list by attribute value
	 * @param attribute The attribute to be filtered
	 * @param value The attribute value
	 * @return A typified object list by attribute value
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> search(String[] attribute, Object[] value) throws Exception {

		EntityManager em = null;
		
		List<T> searchList = null;
		
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			String sql = "select t from "
					+ persistentClass.getSimpleName() + " t where t."
					+ attribute[0] + " = '" + value[0].toString() + "'";
			for (int i = 1; i < attribute.length; i++) {
				if(value[i].equals("null")){
					sql += " AND t." + attribute[i] + " IS NULL";
				}else{
					sql += " AND t." + attribute[i] + " = '" + value[i].toString() + "'";
				}
			}
			Query query = em.createQuery(sql);
			searchList = query.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			
			LOGGER.error(e.toString());

			try {
				em.getTransaction().rollback();
			} catch (Exception e2) {
				if (reconnect(em)) {
					return search(attribute, value);
				}
				throw e2;
			}
			
			throw e;
		} finally {
			em.close();
		}

		return searchList;
	}
	
	/**
	 * 
	 * Returns a single result according attribute value.
	 * @param attribute The attribute to be filtered.
	 * @param value The attribute value.
	 * @return A typified single result.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public T singleSearch(String attribute, Object value) throws Exception {

		EntityManager em = null;
		
		T single = null;
		
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query = em.createQuery(
					"select t from "
					+ persistentClass.getSimpleName() + " t where t."
					+ attribute + " = :attribute");
			query.setParameter("attribute", value);
			single = (T)query.getSingleResult();
			em.getTransaction().commit();
		} catch (Exception e) {
			
			LOGGER.error(e.toString());

			try {
				em.getTransaction().rollback();
			} catch (Exception e2) {
				if (reconnect(em)) {
					return singleSearch(attribute, value);
				}
				throw e2;
			}
			
			throw e;
		} finally {
			em.close();
		}

		return single;
	}
	
	
	/**
	 * @param attribute The attribute to be filtered.
	 * @param value The attribute value.
	 * @return If the value exists in an specified object.
	 * @throws Exception
	 */
	public boolean exists(String attribute, Object value) throws Exception{
		
		List<T> objectList = search(attribute, value);
		
		if(isNotNullAndEmpty(objectList)){
			return true;
		}
		
		return false;
	}

	/**
	 * Remove a specific object.
	 * @param object The typified object.
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public void remove(T object) throws Exception {
		
		EntityManager em = null;
		
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(object));
			em.getTransaction().commit();
		} catch (Exception e) {
			
			LOGGER.error(e.toString());

			try {
				em.getTransaction().rollback();
			} catch (Exception e2) {
				if (reconnect(em)) {
					remove(object);
				} else {
					throw e2;
				}
			}
			
			throw e;
		} finally {
			em.close();
		}
	}
}