package org.seniors.dao;

import org.seniors.model.Contato;

/**
 * Contato Entity DAO
 *  
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 */
public class ContatoDAO extends GenericDAO<Contato, Long> {

	public ContatoDAO() {
		super(Contato.class);
	}
}