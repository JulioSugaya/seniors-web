package org.seniors.rest.security;

import java.util.Map;

/**
 * User Transfer Object
 * @author jms
 */
public class UserTO {

	private final String name;

	private final Map<String, Boolean> roles;

	public UserTO(String userName, Map<String, Boolean> roles) {
		this.name = userName;
		this.roles = roles;
	}

	public String getName() {
		return this.name;
	}

	public Map<String, Boolean> getRoles() {
		return this.roles;
	}

}