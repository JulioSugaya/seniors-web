package org.seniors.dao;

import org.seniors.model.Localizacao;

/**
 * Contato Entity DAO
 *  
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 */
public class LocalizacaoDAO extends GenericDAO<Localizacao, Long> {

	public LocalizacaoDAO() {
		super(Localizacao.class);
	}
}