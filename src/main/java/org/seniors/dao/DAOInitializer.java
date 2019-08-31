package org.seniors.dao;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.seniors.util.SeniorsConfig;

/**
 * DAO Initializer Class, that encapsulates DAO's dependencies injection 
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 */
public class DAOInitializer {

//	private AuthenticationHashDAO authDAO;
//	private CategoryDAO categoryDAO;
//	private OfferDAO offerDAO;
//	private UserDAO userDAO;
	private UserDAO DAO;
	private PasswordEncoder passwordEncoder;

	public DAOInitializer(){
		System.out.println("TESTE");
	}

//	public DAOInitializer(AuthenticationHashDAO authDAO,
//			CategoryDAO categoryDAO, OfferDAO offerDAO, UserDAO userDAO,
//			UserDAO DAO, PasswordEncoder passwordEncoder) {
//		this.authDAO = authDAO;
//		this.categoryDAO = categoryDAO;
//		this.offerDAO = offerDAO;
//		this.userDAO = userDAO;
//		this.UserDAO = DAO;
//		this.passwordEncoder = passwordEncoder;
//	}
	
	
	public void initDataBase() {
		try {
			
			
//			ServerControllerFacadeImpl.getInstance().getCategoryController().createCategoriesStub();
			// ServerControllerFacadeImpl.getInstance().getCategoryController().createCategoriesStub();
		} catch (Exception e) {
			// TODO: Add Logs
			e.printStackTrace();
		}
	}

//	public AuthenticationHashDAO getAuthDAO() {
//		return authDAO;
//	}
//
//	public void setAuthDAO(AuthenticationHashDAO authDAO) {
//		this.authDAO = authDAO;
//	}
//
//	public CategoryDAO getCategoryDAO() {
//		return categoryDAO;
//	}
//
//	public void setCategoryDAO(CategoryDAO categoryDAO) {
//		this.categoryDAO = categoryDAO;
//	}
//
//	public OfferDAO getOfferDAO() {
//		return offerDAO;
//	}
//
//	public void setOfferDAO(OfferDAO offerDAO) {
//		this.offerDAO = offerDAO;
//	}
//
//	public UserDAO getUserDAO() {
//		return userDAO;
//	}
//
//	public void setUserDAO(UserDAO userDAO) {
//		this.userDAO = userDAO;
//	}

	public DAOInitializer(
			PasswordEncoder passwordEncoder) {
		super();
//		this.DAO = DAO;
		this.passwordEncoder = passwordEncoder;
		
		SeniorsConfig.setPasswordEncoder(passwordEncoder);
	}

	public UserDAO getDAO() {
		return DAO;
	}

	public void setDAO(UserDAO DAO) {
		this.DAO = DAO;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}