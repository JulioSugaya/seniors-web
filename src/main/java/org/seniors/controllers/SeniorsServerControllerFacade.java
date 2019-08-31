package org.seniors.controllers;

import org.seniors.controllers.SeniorsUserController;

/**
 * seniors Controller Facade, that provides a single way to access all
 * controllers.
 * 
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 *
 */
public interface SeniorsServerControllerFacade {

	public AtividadeController getAtividadeController();

	public MedicacaoController getMedicacaoController();
	
	public UserController getUserController();
	
	public SeniorsUserController getSeniorsUserController();

	public ConsultaMedicaController getConsultaMedicaController();

	public ContatoController getContatoController();

	public LocalizacaoController getLocalizacaoController();

}