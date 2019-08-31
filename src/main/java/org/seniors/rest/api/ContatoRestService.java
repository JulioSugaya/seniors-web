package org.seniors.rest.api;

import static org.seniors.config.SeniorsServerMessages.CONTATO_ALREADY_EXISTS;
import static org.seniors.config.SeniorsServerMessages.SOME_EMPTY_FIELD;
import static org.seniors.config.SeniorsServerMessages.SOME_WRONG_INFO;
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

import org.seniors.exceptions.SeniorsServerException;
import org.seniors.exceptions.SeniorsServerInternalException;
import org.seniors.model.Contato;
import org.seniors.pushnotification.impl.android.AndroidNotification;
import org.springframework.stereotype.Component;

@Component
@Path("/contato")
public class ContatoRestService extends GenericRequest {

    @POST
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Contato> getAllContatosInJSON(@PathParam("id") long id_usuario) {
        try {
        	List<Contato> Contatos = controllerFacade.getContatoController().listContatos(id_usuario);
			return Contatos;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Contato getContatoById(@PathParam("id") long id) {
        try {
			return controllerFacade.getContatoController().searchById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Contato create(Contato contato) {
    	try {
    		if(isNotNull(contato)){
    			controllerFacade.getContatoController().createContato(contato);	
    		}else{
    			throw new SeniorsServerException(SOME_EMPTY_FIELD);	
    		}
    		
        	AndroidNotification android = new AndroidNotification();
        	String reg = controllerFacade.getSeniorsUserController().getSeniorRegistratoinId(contato.getId_usuario());
        	android.sendMessage(contato, AndroidNotification.OPER_ADD, reg);
    	} 
    	catch (SeniorsServerInternalException e) {
			e.printStackTrace();
			
			if(e.getMessage().equals(CONTATO_ALREADY_EXISTS)) {
				throw new SeniorsServerException(CONTATO_ALREADY_EXISTS, Status.INTERNAL_SERVER_ERROR);
			}
			
			throw new SeniorsServerException(SOME_WRONG_INFO);
		} 
    	catch (Exception e) {
			e.printStackTrace();
			throw new SeniorsServerException(SOME_WRONG_INFO);
		}
    	return contato;
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Contato update(Contato contato) {
        try {
        	if(isNotNull(contato)){
        		controllerFacade.getContatoController().updateContato(contato);
            	
            	AndroidNotification android = new AndroidNotification();
            	String reg = controllerFacade.getSeniorsUserController().getSeniorRegistratoinId(contato.getId_usuario());
            	android.sendMessage(contato, AndroidNotification.OPER_MODIFIED, reg);
        	}
		} 
        catch (SeniorsServerInternalException e) {
			e.printStackTrace();
			
			if(e.getMessage().equals(CONTATO_ALREADY_EXISTS)) {
				throw new SeniorsServerException(CONTATO_ALREADY_EXISTS, Status.INTERNAL_SERVER_ERROR);
			}
			
			throw new SeniorsServerException(SOME_WRONG_INFO);
		} 
        catch (Exception e) {
			e.printStackTrace();
			throw new SeniorsServerException(SOME_WRONG_INFO);
		}
        
        return contato;
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void remove(@PathParam("id") long id) {
        try {
			Contato contato = controllerFacade.getContatoController().remove(id);
			
        	AndroidNotification android = new AndroidNotification();
        	String reg = controllerFacade.getSeniorsUserController().getSeniorRegistratoinId(contato.getId_usuario());
        	android.sendMessage(contato, AndroidNotification.OPER_REMOVED, reg);
        	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
