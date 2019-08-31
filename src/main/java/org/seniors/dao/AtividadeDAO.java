package org.seniors.dao;

import org.seniors.model.Atividade;

/**
 * User Entity DAO
 *  
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 */
public class AtividadeDAO extends GenericDAO<Atividade, Long> {

	public AtividadeDAO() {
		super(Atividade.class);
	}
}