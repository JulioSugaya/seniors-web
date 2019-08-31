package org.seniors.dao;

import org.seniors.model.Medicacao;

/**
 * User Entity DAO
 *  
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 */
public class MedicacaoDAO extends GenericDAO<Medicacao, Long> {

	public MedicacaoDAO() {
		super(Medicacao.class);
	}
}