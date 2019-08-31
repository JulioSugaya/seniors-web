package org.seniors.rest.api;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

//import org.glassfish.jersey.media.multipart.MultiPartFeature;
//import org.glassfish.jersey.server.ResourceConfig;
import org.seniors.controllers.SeniorsServerControllerFacade;
import org.seniors.controllers.SeniorsServerControllerFacadeImpl;


/**
 * Generic Request class that provides a generic methods to child class.
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 */
public abstract class GenericRequest {//extends ResourceConfig {
	
	@Resource ServletContext servletContext;

	protected SeniorsServerControllerFacade controllerFacade = SeniorsServerControllerFacadeImpl.getInstance();
	
	public GenericRequest() {
//	    super(MultiPartFeature.class);
	}
	
	@Context
    public void setServletContext(ServletContext context) {
        this.servletContext = context;
    }
}