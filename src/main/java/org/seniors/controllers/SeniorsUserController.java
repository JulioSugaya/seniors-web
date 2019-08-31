package org.seniors.controllers;

import static org.seniors.config.SeniorsServerMessages.USER_ALREADY_EXISTS;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import org.seniors.dao.SeniorsUserDAO;
import org.seniors.exceptions.SeniorsServerInternalException;
import org.seniors.model.Role;
import org.seniors.model.SeniorsUser;
import org.seniors.rest.security.TokenUtils;
import org.seniors.util.EncryptionUtil;
import org.seniors.util.SeniorsConfig;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * User Controller class
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 * 
 */
public class SeniorsUserController {

	private static SeniorsUserController instance = null;
	private static SeniorsUserDAO userDao;

	private SeniorsUserController() {}

	/**
	 * @return Single instance of <code>UserController</code> class.
	 */
	public static SeniorsUserController getInstance() {
		if (instance == null) {
			instance = new SeniorsUserController();
			userDao = new SeniorsUserDAO();
		}

		return instance;
	}

	/**
	 * Creates a new user and add it to the database.
	 * 
	 * @param name
	 * @param cpf
	 * @param email
	 * @param password
	 * @throws Exception
	 */

	public SeniorsUser createUser(SeniorsUser user) throws Exception {

		if (!userDao.exists("email", user.getEmail())) {
			user.setPassword(SeniorsConfig.getPasswordEncoder().encode(user.getPassword()));
			userDao.persist(user);
			return user;

		} else {
			throw new SeniorsServerInternalException(USER_ALREADY_EXISTS);
		}
	}

	/**
	 * Authenticate user and generate a new hash token.
	 * 
	 * @param email
	 * @param password
	 * @param deviceId
	 * @return Generate hash token.
	 * @throws Exception
	 */
	public String authenticateUser(String login, String password,
			String deviceId) throws Exception {

		List<SeniorsUser> users = userDao.search("name", login);
		if (users.size() == 0) {
			return null;
		}

		SeniorsUser user = users.get(0);
		if (!user.getPassword().equals(EncryptionUtil.encrypt(password))) {
			return null;
		}
		
		UserDetails userDetails = userDao.loadUserByUsername(user.getName());
		return TokenUtils.createToken(userDetails);

	}

	/**
	 * @param id
	 * @return Search a user by id
	 * @throws Exception
	 */
	public SeniorsUser searchById(Long id) throws Exception{
		return userDao.search(id);
	}
	
	public String getSeniorRegistratoinId(Long id){
		SeniorsUser user;
		String reg = "";
		try {
			user = searchById(id);
			reg = user.getRegistrationId();
		} catch (Exception e) {
		}
		return reg;
	}
	
	public SeniorsUser addSenior(Long id_cuidador, Long id_senior) throws Exception{
		SeniorsUser user = searchById(id_senior);
		user.setId_cuidador(id_cuidador);
		userDao.persist(user);
		return user;
	}
	
	public SeniorsUser verificaSenior(String[] attr, Object[] values) throws Exception{
		List<SeniorsUser> user = userDao.search(attr, values);
		if(user.size() > 0){
			return user.get(0);
		}
		return null;
	}
	
	public String addSenior(String phone, String name, String reg) throws Exception{
		if(phone != null && name != null){
			List<SeniorsUser> user = userDao.search("celular", phone);
			if(user.isEmpty()){
				SeniorsUser senior = new SeniorsUser();
				senior.setCelular(phone);
				senior.setNomeCompleto(name);
				senior.setRegistrationId(reg);
				senior.setRole("SENIOR");
				SecureRandom random = new SecureRandom();
				String key = new BigInteger(30, random).toString(32);
				senior.setChave(key);
				userDao.persist(senior);
				return key;
			} else {
				throw new Exception();
			}
		} else {
			throw new Exception();
		}
	}
	
	public SeniorsUser updateUser(SeniorsUser user) throws Exception {
		List<SeniorsUser> users = userDao.search("email", user.getEmail()); 
		if (users.isEmpty() || users.get(0).getId().equals(user.getId())) {
			user.setPassword(SeniorsConfig.getPasswordEncoder().encode(user.getPassword()));
			userDao.persist(user);
			return user;
		} else {
			throw new SeniorsServerInternalException(USER_ALREADY_EXISTS);
		}
	}
	
	public List<SeniorsUser> listUsersCuidador(Long id) throws Exception{
		return userDao.search("id_cuidador", id);
	}
	
	public List<SeniorsUser> listUsers() throws Exception{
		return userDao.search();
	}
	
	public List<SeniorsUser> listSeniorsAvailable(String[] attr, Object[] obj) throws Exception{
		return userDao.search(attr, obj);
	}
	
	public void remove(Long id) throws Exception{
		SeniorsUser user = userDao.search(id);
		userDao.remove(user);
	}
	
	public void logout(String deviceId) {
	}
	
	public void createUserStub(){
		try {
			SeniorsUser user = new SeniorsUser();
			user.setName("Admin");
			user.setEmail("admin@seniors.com");
			user.setPassword(SeniorsConfig.getPasswordEncoder().encode("123456"));
			user.setRole(Role.ADMINISTRADOR.getName());
			user.setIdade(0);
			user.setAltura(9);
			user.setPeso(9);
			user.setRaio_seg(9);
			user.setCelular("9999-9999");
			if (!userDao.exists("email", user.getEmail())) {
				userDao.persist(user);
			}
//			SeniorsUser user1 = new SeniorsUser();
//
//			user1.setName("Cuidador");
//			user1.setEmail("cuidador@seniors.com");
//			user1.setPassword(SeniorsConfig.getPasswordEncoder().encode("123456"));
//			user1.setRole(Role.CUIDADOR.getName());
//			user1.setCelular("9999-9999");
//			if (!userDao.exists("email", user1.getEmail())) {
//				SeniorsUser u = userDao.persist(user1);
//
//				SeniorsUser user2 = new SeniorsUser();
//
//				user2.setName("Senior1");
//				user2.setEmail("senior1@seniors.com");
//				user2.setPassword(SeniorsConfig.getPasswordEncoder().encode("123456"));
//				user2.setRole(Role.SENIOR.getName());
//				user2.setCelular("9999-9999");
//				user2.setId_cuidador(u.getId());;
//				if (!userDao.exists("email", user2.getEmail())) {
//					userDao.persist(user2);
//				}
//
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}