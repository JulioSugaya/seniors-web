package org.seniors.model;

/**
 * User Roles 
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 */
public enum Role {
	ADMINISTRADOR("Administrador"), CUIDADOR("Cuidador"), SENIOR("Senior");
	
	private final String name;
	
	Role(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}