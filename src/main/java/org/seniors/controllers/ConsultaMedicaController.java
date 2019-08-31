package org.seniors.controllers;

import static org.seniors.config.SeniorsServerMessages.CONSULTAMEDICA_ALREADY_EXISTS;

import java.util.Date;
import java.util.List;

import org.seniors.dao.ConsultaMedicaDAO;
import org.seniors.exceptions.SeniorsServerInternalException;
import org.seniors.model.Atividade;
import org.seniors.model.ConsultaMedica;

/**
 * User Controller class
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 * 
 */
public class ConsultaMedicaController {

	private static ConsultaMedicaController instance = null;
	private static ConsultaMedicaDAO consultaMedicaDAO;
	private ConsultaMedicaController() {}

	/**
	 * @return Single instance of <code>UserController</code> class.
	 */
	public static ConsultaMedicaController getInstance() {
		if (instance == null) {
			instance = new ConsultaMedicaController();
			consultaMedicaDAO = new ConsultaMedicaDAO();

		}

		return instance;
	}

	/**
	 * Creates a new user and add it to the database.
	 * 
	 * @param name
	 * @param cpf
	 * @param email
	 * @param password
	 * @throws Exception
	 */

	public ConsultaMedica createConsultaMedica(ConsultaMedica consultaMedica) throws Exception {

		return consultaMedicaDAO.persist(consultaMedica);
	}
	
	public ConsultaMedica updateConsultaMedica(ConsultaMedica consultaMedica) throws Exception {
		List<ConsultaMedica> consultaMedicas = consultaMedicaDAO.search("id", consultaMedica.getId()); 
		if (consultaMedicas.isEmpty() || consultaMedicas.get(0).getId() == consultaMedica.getId()) {
			consultaMedicaDAO.persist(consultaMedica);
			return consultaMedica;
		} else {
			throw new SeniorsServerInternalException(CONSULTAMEDICA_ALREADY_EXISTS);
		}
	}
	
	public List<ConsultaMedica> listConsultaMedicas(long id) throws Exception{
		return consultaMedicaDAO.search("id_usuario", id);
	}
	
	public List<ConsultaMedica> listConsultasMedicasPorData(String id, String dataInicio, String dataFim) throws Exception{
	    long lid = Long.parseLong(id);
	    Date dataIni = new Date();
	    Date dataF = new Date();
		return consultaMedicaDAO.search("id_usuario", lid, "data", dataIni, "data", dataF);
	}
	
	public ConsultaMedica remove(Long id) throws Exception{
		ConsultaMedica consultaMedica = consultaMedicaDAO.search(id);
		consultaMedicaDAO.remove(consultaMedica);
		return consultaMedica;
	}
	
	public void createConsultaMedicaStub(){

		ConsultaMedica consultaMedica = new ConsultaMedica();
		consultaMedica.setNomeMedico("Dr Rey");
		consultaMedica.setDescricao("consulta pre cirurgica");
		consultaMedica.setData(new Date());
		try {
			consultaMedicaDAO.persist(consultaMedica);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param id
	 * @return Search a medicine by id
	 * @throws Exception
	 */
	public ConsultaMedica searchById(Long id) throws Exception{
		return consultaMedicaDAO.search(id);
	}

}