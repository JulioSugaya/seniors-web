package org.seniors.rest.api;

import static org.seniors.config.SeniorsServerMessages.SOME_EMPTY_FIELD;
import static org.seniors.config.SeniorsServerMessages.SOME_WRONG_INFO;
import static org.seniors.config.SeniorsServerMessages.USER_ALREADY_EXISTS;
import static org.seniors.util.ValidationUtils.isNotNull;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;
import org.seniors.exceptions.SeniorsServerException;
import org.seniors.exceptions.SeniorsServerInternalException;
import org.seniors.model.SeniorsUser;

@Component
@Path("/users")
public class UserRestService extends GenericRequest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SeniorsUser> getAllUsersInJSON() {
        try {
			List<SeniorsUser> users = controllerFacade.getSeniorsUserController().listUsers();
			return users;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SeniorsUser getUserById(@PathParam("id") long id) {
        try {
			return controllerFacade.getSeniorsUserController().searchById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SeniorsUser create(SeniorsUser user) {
    	try {
    		if(isNotNull(user)){
    			return controllerFacade.getSeniorsUserController().createUser(user);	
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

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SeniorsUser update(SeniorsUser user) {
        try {
        	if(isNotNull(user)){
        		return controllerFacade.getSeniorsUserController().updateUser(user);
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
        
        return null;
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
