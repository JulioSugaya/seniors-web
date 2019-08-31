package org.seniors.controllers;

import static org.seniors.config.SeniorsServerMessages.MEDICACAO_ALREADY_EXISTS;

import java.util.Date;
import java.util.List;

import org.seniors.dao.MedicacaoDAO;
import org.seniors.exceptions.SeniorsServerInternalException;
import org.seniors.model.ConsultaMedica;
import org.seniors.model.Medicacao;

/**
 * User Controller class
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 * 
 */
public class MedicacaoController {

	private static MedicacaoController instance = null;
	private static MedicacaoDAO medicacaoDAO;
	private MedicacaoController() {}

	/**
	 * @return Single instance of <code>UserController</code> class.
	 */
	public static MedicacaoController getInstance() {
		if (instance == null) {
			instance = new MedicacaoController();
			medicacaoDAO = new MedicacaoDAO();

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

	public Medicacao createMedicacao(Medicacao med) throws Exception {

		return medicacaoDAO.persist(med);
	}
	
	public Medicacao updateMedicacao(Medicacao med) throws Exception {
		List<Medicacao> meds = medicacaoDAO.search("id", med.getId()); 
		if (meds.isEmpty() || meds.get(0).getId() == med.getId()) {
			medicacaoDAO.persist(med);
			return med;
		} else {
			throw new SeniorsServerInternalException(MEDICACAO_ALREADY_EXISTS);
		}
	}
	
	public List<Medicacao> listMedicacoes(long id_usuario) throws Exception{
		return medicacaoDAO.search("id_usuario", id_usuario);
	}
	
	public List<Medicacao> listMedicacoesPorData(String id, String dataInicio, String dataFim) throws Exception{
	    long lid = Long.parseLong(id);
	    Date dataIni = new Date();
	    Date dataF = new Date();
		return medicacaoDAO.search("id_usuario", lid, "dataInicial", dataIni, "dataInicial", dataF);
	}
	
	public Medicacao remove(Long id) throws Exception{
		Medicacao med = medicacaoDAO.search(id);
		medicacaoDAO.remove(med);
		return med;
	}
	
	public void createMedicacaoStub(){

		Medicacao med = new Medicacao();
		med.setNome("Neosaldina");
		med.setObs("tomar sempre que quiser");;
		med.setPrioridade(1);
		
		try {
			medicacaoDAO.persist(med);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param id
	 * @return Search a medicine by id
	 * @throws Exception
	 */
	public Medicacao searchById(Long id) throws Exception{
		return medicacaoDAO.search(id);
	}

}