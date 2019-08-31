package org.seniors.controllers;

import static org.seniors.config.SeniorsServerMessages.ATIVIDADE_ALREADY_EXISTS;

import java.util.Date;
import java.util.List;

import org.seniors.dao.AtividadeDAO;
import org.seniors.exceptions.SeniorsServerInternalException;
import org.seniors.model.Atividade;
import org.seniors.util.DateUtils;

/**
 * User Controller class
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 * 
 */
public class AtividadeController {

	private static AtividadeController instance = null;
	private static AtividadeDAO atividadeDAO;
	private AtividadeController() {}

	/**
	 * @return Single instance of <code>UserController</code> class.
	 */
	public static AtividadeController getInstance() {
		if (instance == null) {
			instance = new AtividadeController();
			atividadeDAO = new AtividadeDAO();

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

	public Atividade createAtividade(Atividade atividade) throws Exception {

		return atividadeDAO.persist(atividade);
	}
	
	public Atividade updateAtividade(Atividade atividade) throws Exception {
		List<Atividade> atividades = atividadeDAO.search("id", atividade.getId()); 
		if (atividades.isEmpty() || atividades.get(0).getId() == atividade.getId()) {
			atividadeDAO.persist(atividade);
			return atividade;
		} else {
			throw new SeniorsServerInternalException(ATIVIDADE_ALREADY_EXISTS);
		}
	}
	
	public List<Atividade> listAtividades(long id) throws Exception{
		return atividadeDAO.search("id_usuario", id);
	}
	
	public List<Atividade> listAtividadesPorData(String id, String dataInicio, String dataFim) throws Exception{
	    long lid = Long.parseLong(id);
	    Date dataIni = DateUtils.stringToDateGMT(dataInicio);
	    Date dataF = DateUtils.stringToDateGMT(dataFim);
	    
		return atividadeDAO.search("id_usuario", lid, "dataInicial", dataIni, "dataFim", dataF);
	}
	
	public Atividade remove(Long id) throws Exception{
		Atividade atividade = atividadeDAO.search(id);
		atividadeDAO.remove(atividade);
		return atividade;
	}
	
	public void createAtividadeStub(){

		Atividade atividade = new Atividade();
		atividade.setDescricao("maratona");
		
		try {
			atividadeDAO.persist(atividade);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param id
	 * @return Search a medicine by id
	 * @throws Exception
	 */
	public Atividade searchById(Long id) throws Exception{
		return atividadeDAO.search(id);
	}

}