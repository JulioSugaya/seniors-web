package org.seniors.dao;

import org.seniors.model.User;

/**
 * User Entity DAO
 *  
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 */
public class UserDAO extends GenericDAO<User, Long> {

	public UserDAO() {
		super(User.class);
	}
}