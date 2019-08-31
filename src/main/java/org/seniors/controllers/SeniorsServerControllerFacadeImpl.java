package org.seniors.controllers;

import org.seniors.controllers.UserController;
import org.seniors.controllers.SeniorsUserController;


/**
 * seniors Controller Facade Implementation, that provides a single way to access all
 * controllers.
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 *
 */
public class SeniorsServerControllerFacadeImpl implements SeniorsServerControllerFacade {

	private static SeniorsServerControllerFacadeImpl instance = null;

	//Controllers
	private static MedicacaoController medicacaoController;
	private static AtividadeController atividadeController;
	private static UserController userController;
	private static SeniorsUserController seniorsUserController;
	private static ConsultaMedicaController consultaMedicaController;
	private static ContatoController contatoController;
	private static LocalizacaoController localizacaoController;
	
	//Private constructor
	private SeniorsServerControllerFacadeImpl() {}

	/**
	 * @return Single instance of <code>seniorsControllerFacadeImpl</code> class.
	 */
	public static SeniorsServerControllerFacadeImpl getInstance() {
		if (instance == null) {
			instance = new SeniorsServerControllerFacadeImpl();
			seniorsUserController = SeniorsUserController.getInstance();
			userController = UserController.getInstance();
			medicacaoController = MedicacaoController.getInstance();
			atividadeController = AtividadeController.getInstance();
			consultaMedicaController = ConsultaMedicaController.getInstance();
			contatoController = ContatoController.getInstance();
			localizacaoController = LocalizacaoController.getInstance();
		}
		return instance;
	}
	

	@Override
	public SeniorsUserController getSeniorsUserController() {
		return seniorsUserController;
	}

	public MedicacaoController getMedicacaoController() {
		return medicacaoController;
	}

	public AtividadeController getAtividadeController() {
		return atividadeController;
	}

	public UserController getUserController() {
		return userController;
	}

	public ConsultaMedicaController getConsultaMedicaController() {
		return consultaMedicaController;
	}

	public ContatoController getContatoController() {
		return contatoController;
	}

	public LocalizacaoController getLocalizacaoController() {
		return localizacaoController;
	}

}