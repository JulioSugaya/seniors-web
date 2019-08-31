package org.seniors.rest.security;

/**
 * User Transfer Object
 * @author jms
 */
public class MedicacaoTO {

	private final String nome;

	public MedicacaoTO(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

}