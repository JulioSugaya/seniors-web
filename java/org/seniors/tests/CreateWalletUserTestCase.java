package org.server.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.server.model.Role;
import org.server.model.User;
import org.server.util.EncryptionUtil;

public class CreateUserTestCase {

	public static void main(String[] a) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("seniors-web");
		EntityManager em = emf.createEntityManager();;
		
		em.getTransaction().begin();
		
		User user = new User();
		
		user.setName("Admin");
		user.setEmail("admin@seniors.com");
		user.setPassword(EncryptionUtil.encrypt("123456"));
		user.setRole(Role.ADMIN);
//		user.addRole(Role.ADMIN);
//		user.addRole(Role.USER);
		
		User user2 = new User();
		
		user2.setName("Root");
		user2.setEmail("root@seniors.com");
		user2.setPassword(EncryptionUtil.encrypt("123321"));
		user2.setRole(Role.USER);
//		user2.addRole(Role.USER);

		em.persist(user);
		em.persist(user2);
		
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}