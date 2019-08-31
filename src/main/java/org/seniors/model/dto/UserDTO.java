package org.seniors.model.dto;

/**
 * DTO to User model
 * 
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 */
public class UserDTO {

	private String name;
	private String cpf;
	private String email;
	private String token;
	private int points;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}