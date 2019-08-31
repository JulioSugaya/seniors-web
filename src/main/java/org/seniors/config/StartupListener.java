package org.seniors.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.seniors.controllers.SeniorsServerControllerFacadeImpl;

/**
 * Application Lifecycle Listener implementation class seniorsStartupListener
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 */
@WebListener
public class StartupListener implements ServletContextListener {

	
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {}

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
    	try {
    		//Force initialization of DAO layer
    		//TODO: Add logs
			SeniorsServerControllerFacadeImpl.getInstance().getSeniorsUserController().searchById(1L);
			SeniorsServerControllerFacadeImpl.getInstance().getUserController().createUsersStub();
			SeniorsServerControllerFacadeImpl.getInstance().getMedicacaoController().createMedicacaoStub();
			SeniorsServerControllerFacadeImpl.getInstance().getAtividadeController().createAtividadeStub();
		} catch (Exception e) {
			
			//TODO: Add logs
			e.printStackTrace();
		} finally {
		}
    }
}