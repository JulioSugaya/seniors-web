package org.seniors.dao;

import org.seniors.model.AuthenticationHash;

public class AuthenticationHashDAO extends GenericDAO<AuthenticationHash, Long>  {

	public AuthenticationHashDAO() {
		super(AuthenticationHash.class);
	}
	
	/**
	 * @param token
	 * @return An {@link AuthenticationHash} based on token
	 */
	public AuthenticationHash getAuthenticationByToken(String token){
		
		try {
			AuthenticationHash auth = singleSearch("hash", token);
			return auth;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}