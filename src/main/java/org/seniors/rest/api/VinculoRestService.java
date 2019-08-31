package org.seniors.rest.api;

import static org.seniors.config.SeniorsServerMessages.SOME_EMPTY_FIELD;
import static org.seniors.config.SeniorsServerMessages.SOME_WRONG_INFO;
import static org.seniors.config.SeniorsServerMessages.USER_ALREADY_EXISTS;
import static org.seniors.util.ValidationUtils.isNotNull;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.seniors.exceptions.SeniorsServerException;
import org.seniors.exceptions.SeniorsServerInternalException;
import org.seniors.model.SeniorsUser;
import org.springframework.stereotype.Component;

@Component
@Path("/vinculo")
public class VinculoRestService extends GenericRequest {

    @PUT
    @Path("{id}/{id2}")
    @Produces(MediaType.APPLICATION_JSON)
    public SeniorsUser addSenior(@PathParam("id") long id_cuidador, @PathParam("id2") long id) {
    	try {
    		if(isNotNull(id_cuidador) || isNotNull(id)){
    			return controllerFacade.getSeniorsUserController().addSenior(id_cuidador,id);	
    		}else{
    			throw new SeniorsServerException(SOME_EMPTY_FIELD);	
    		}
    	} 
    	catch (SeniorsServerInternalException e) {
			e.printStackTrace();
			
			if(e.getMessage().equals(USER_ALREADY_EXISTS)) {
				throw new SeniorsServerException(USER_ALREADY_EXISTS, Status.INTERNAL_SERVER_ERROR);
			}
			
			throw new SeniorsServerException(SOME_WRONG_INFO);
		} 
    	catch (Exception e) {
			e.printStackTrace();
			throw new SeniorsServerException(SOME_WRONG_INFO);
		}
    }

    @GET
    @Path("{id}/{id2}")
    @Produces(MediaType.APPLICATION_JSON)
    public SeniorsUser verificasenior(@PathParam("id") String phone, @PathParam("id2") String chave) {
    	try {
    		if(isNotNull(chave)){
            	String[] attr = {"celular", "chave"}; 
            	String[] value = {phone, chave}; 
    			return controllerFacade.getSeniorsUserController().verificaSenior(attr, value);	
    		}else{
    			throw new SeniorsServerException(SOME_EMPTY_FIELD);	
    		}
    	} 
    	catch (SeniorsServerInternalException e) {
			e.printStackTrace();
			
			if(e.getMessage().equals(USER_ALREADY_EXISTS)) {
				throw new SeniorsServerException(USER_ALREADY_EXISTS, Status.INTERNAL_SERVER_ERROR);
			}
			
			throw new SeniorsServerException(SOME_WRONG_INFO);
		} 
    	catch (Exception e) {
			e.printStackTrace();
			throw new SeniorsServerException(SOME_WRONG_INFO);
		}
    }
    
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void remove(@PathParam("id") long id) {
        try {
			controllerFacade.getSeniorsUserController().remove(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
