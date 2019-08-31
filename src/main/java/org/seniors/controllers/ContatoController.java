package org.seniors.controllers;

import static org.seniors.config.SeniorsServerMessages.CONTATO_ALREADY_EXISTS;

import java.util.List;

import org.seniors.dao.ContatoDAO;
import org.seniors.exceptions.SeniorsServerInternalException;
import org.seniors.model.Contato;

/**
 * Contato Controller class
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 * 
 */
public class ContatoController {

	private static ContatoController instance = null;
	private static ContatoDAO ContatoDAO;
	private ContatoController() {}

	/**
	 * @return Single instance of <code>ContatoController</code> class.
	 */
	public static ContatoController getInstance() {
		if (instance == null) {
			instance = new ContatoController();
			ContatoDAO = new ContatoDAO();

		}

		return instance;
	}

	/**
	 * Creates a new contato and add it to the database.
	 * 
	 * @param nome
	 * @param numero
	 * @param id_usuario
	 * @throws Exception
	 */

	public Contato createContato(Contato Contato) throws Exception {

		return ContatoDAO.persist(Contato);
	}
	
	public Contato updateContato(Contato Contato) throws Exception {
		List<Contato> Contatos = ContatoDAO.search("id", Contato.getId()); 
		if (Contatos.isEmpty() || Contatos.get(0).getId() == Contato.getId()) {
			ContatoDAO.persist(Contato);
			return Contato;
		} else {
			throw new SeniorsServerInternalException(CONTATO_ALREADY_EXISTS);
		}
	}
	
	public List<Contato> listContatos(long id) throws Exception{
		return ContatoDAO.search("id_usuario", id);
	}
	
	public Contato remove(Long id) throws Exception{
		Contato contato = ContatoDAO.search(id);
		ContatoDAO.remove(contato);
		
		return contato;
	}
	
	public void createContatoStub(){

		Contato Contato = new Contato();
		Contato.setNome("Policia");
		Contato.setNumero("190");
		Contato.setId_usuario(3);
		
		try {
			ContatoDAO.persist(Contato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param id
	 * @return Search a medicine by id
	 * @throws Exception
	 */
	public Contato searchById(Long id) throws Exception{
		return ContatoDAO.search(id);
	}

}