package org.seniors.controllers;

import java.util.List;

import org.seniors.dao.LocalizacaoDAO;
import org.seniors.model.Localizacao;

/**
 * Localizacao Controller class
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 * 
 */
public class LocalizacaoController {

	private static LocalizacaoController instance = null;
	private static LocalizacaoDAO LocalizacaoDAO;
	private LocalizacaoController() {}

	/**
	 * @return Single instance of <code>LocalizacaoController</code> class.
	 */
	public static LocalizacaoController getInstance() {
		if (instance == null) {
			instance = new LocalizacaoController();
			LocalizacaoDAO = new LocalizacaoDAO();

		}

		return instance;
	}

	/**
	 * Creates a new Localizacao and add it to the database.
	 * 
	 * @param nome
	 * @param numero
	 * @param id_usuario
	 * @throws Exception
	 */

	public Localizacao createLocalizacao(Localizacao local) throws Exception {

		return LocalizacaoDAO.persist(local);
	}
	
	public Localizacao updateLocalizacao(Localizacao local) throws Exception {
		LocalizacaoDAO.persist(local);
		return local;
	}
	
	public List<Localizacao> listLocalizacao(long id) throws Exception{
		return LocalizacaoDAO.search("id_usuario", id);
	}
	
	public Localizacao remove(Long id) throws Exception{
		Localizacao local = LocalizacaoDAO.search(id);
		LocalizacaoDAO.remove(local);
		
		return local;
	}
	
	public void createLocalizacaoStub(){

		Localizacao local = new Localizacao();
		local.setId_usuario(3);
		local.setLat("-8.0984673");
		local.setLon("-34.9530931");
		
		try {
			LocalizacaoDAO.persist(local);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param id
	 * @return Search a medicine by id
	 * @throws Exception
	 */
	public Localizacao searchById(Long id) throws Exception{
		return LocalizacaoDAO.search(id);
	}

}