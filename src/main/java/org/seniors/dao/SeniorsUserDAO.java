package org.seniors.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.seniors.model.SeniorsUser;

/**
 * SeniorsUser Entity DAO
 *  
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 */
public class SeniorsUserDAO extends GenericDAO<SeniorsUser, Long> implements UserDetailsService{

	public SeniorsUserDAO() {
		super(SeniorsUser.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			SeniorsUser user = singleSearch("email", username);
			return user;
		} catch (Exception e) {
			//TODO: Add logs
			//try with userName
			try {
				SeniorsUser user = singleSearch("name", username);
				return user;
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return null;
	}
}