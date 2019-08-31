package org.server.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.server.model.User;
import org.server.util.EncryptionUtil;

public class CreateUserTestCase {

	public static void main(String[] a) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("seniors-web");
		EntityManager em = emf.createEntityManager();;
		
		em.getTransaction().begin();
		
		User user = new User();
		
		user.setName("Admin");
		user.setCpf("12312809834");
		user.setEmail("admin@seniors.com");
		user.setPassword(EncryptionUtil.encrypt("123456"));
		
		em.persist(user);
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
}