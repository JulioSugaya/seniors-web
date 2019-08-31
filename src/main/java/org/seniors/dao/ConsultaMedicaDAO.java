package org.seniors.dao;

import org.seniors.model.ConsultaMedica;

/**
 * Connsultas Entity DAO
 *  
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 */
public class ConsultaMedicaDAO extends GenericDAO<ConsultaMedica, Long> {

	public ConsultaMedicaDAO() {
		super(ConsultaMedica.class);
	}
}