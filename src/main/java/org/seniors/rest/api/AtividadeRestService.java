package org.seniors.rest.api;

import static org.seniors.config.SeniorsServerMessages.ATIVIDADE_ALREADY_EXISTS;
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
import org.seniors.model.Atividade;
import org.seniors.pushnotification.impl.android.AndroidNotification;
import org.springframework.stereotype.Component;

@Component
@Path("/atividade")
public class AtividadeRestService extends GenericRequest {

    @POST
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Atividade> getAllAtividadesInJSON(@PathParam("id") long id_usuario) {
        try {
        	List<Atividade> atividades = controllerFacade.getAtividadeController().listAtividades(id_usuario);
			return atividades;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Atividade getAtividadeById(@PathParam("id") long id) {
        try {
			return controllerFacade.getAtividadeController().searchById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Atividade create(Atividade atividade) {
    	try {
    		if(isNotNull(atividade)){
    			controllerFacade.getAtividadeController().createAtividade(atividade);	
    		}else{
    			throw new SeniorsServerException(SOME_EMPTY_FIELD);	
    		}
    		
        	AndroidNotification android = new AndroidNotification();
        	String reg = controllerFacade.getSeniorsUserController().getSeniorRegistratoinId(atividade.getId_usuario());
        	android.sendMessage(atividade, AndroidNotification.OPER_ADD, reg);
    	} 
    	catch (SeniorsServerInternalException e) {
			e.printStackTrace();
			
			if(e.getMessage().equals(ATIVIDADE_ALREADY_EXISTS)) {
				throw new SeniorsServerException(ATIVIDADE_ALREADY_EXISTS, Status.INTERNAL_SERVER_ERROR);
			}
			
			throw new SeniorsServerException(SOME_WRONG_INFO);
		} 
    	catch (Exception e) {
			e.printStackTrace();
			throw new SeniorsServerException(SOME_WRONG_INFO);
		}
    	return atividade;
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Atividade update(Atividade atividade) {
        try {
        	if(isNotNull(atividade)){
        		controllerFacade.getAtividadeController().updateAtividade(atividade);
        	}
        	
        	AndroidNotification android = new AndroidNotification();
        	String reg = controllerFacade.getSeniorsUserController().getSeniorRegistratoinId(atividade.getId_usuario());
        	android.sendMessage(atividade, AndroidNotification.OPER_MODIFIED, reg);
		} 
        catch (SeniorsServerInternalException e) {
			e.printStackTrace();
			
			if(e.getMessage().equals(ATIVIDADE_ALREADY_EXISTS)) {
				throw new SeniorsServerException(ATIVIDADE_ALREADY_EXISTS, Status.INTERNAL_SERVER_ERROR);
			}
			
			throw new SeniorsServerException(SOME_WRONG_INFO);
		} 
        catch (Exception e) {
			e.printStackTrace();
			throw new SeniorsServerException(SOME_WRONG_INFO);
		}
        
        return atividade;
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void remove(@PathParam("id") long id) {
        try {
			Atividade atividade = controllerFacade.getAtividadeController().remove(id);
			
        	AndroidNotification android = new AndroidNotification();
        	String reg = controllerFacade.getSeniorsUserController().getSeniorRegistratoinId(atividade.getId_usuario());
        	android.sendMessage(atividade, AndroidNotification.OPER_REMOVED, reg);
        	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
