package org.seniors.rest.api;

import static org.seniors.config.SeniorsServerMessages.CONSULTAMEDICA_ALREADY_EXISTS;
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
import org.seniors.model.ConsultaMedica;
import org.seniors.pushnotification.impl.android.AndroidNotification;
import org.springframework.stereotype.Component;


@Component
@Path("/consultaMedica")
public class ConsultaMedicaRestService extends GenericRequest {

    @POST
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConsultaMedica> getAllConsultaMedicasInJSON(@PathParam("id") long id_cuidador) {
        try {
        	List<ConsultaMedica> consultaMedicas = controllerFacade.getConsultaMedicaController().listConsultaMedicas(id_cuidador);
			return consultaMedicas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ConsultaMedica getConsultaMedicaById(@PathParam("id") long id) {
        try {
			return controllerFacade.getConsultaMedicaController().searchById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ConsultaMedica create(ConsultaMedica consultaMedica) {
    	try {
    		if(isNotNull(consultaMedica)){
    			controllerFacade.getConsultaMedicaController().createConsultaMedica(consultaMedica);	
    		}else{
    			throw new SeniorsServerException(SOME_EMPTY_FIELD);	
    		}
    		
        	AndroidNotification android = new AndroidNotification();
        	String reg = controllerFacade.getSeniorsUserController().getSeniorRegistratoinId(consultaMedica.getId_usuario());
        	android.sendMessage(consultaMedica, AndroidNotification.OPER_ADD, reg);
    	
    	} 
    	catch (SeniorsServerInternalException e) {
			e.printStackTrace();
			
			if(e.getMessage().equals(CONSULTAMEDICA_ALREADY_EXISTS)) {
				throw new SeniorsServerException(CONSULTAMEDICA_ALREADY_EXISTS, Status.INTERNAL_SERVER_ERROR);
			}
			
			throw new SeniorsServerException(SOME_WRONG_INFO);
		} 
    	catch (Exception e) {
			e.printStackTrace();
			throw new SeniorsServerException(SOME_WRONG_INFO);
		}
    	return consultaMedica;
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ConsultaMedica update(ConsultaMedica consultaMedica) {
        try {
        	if(isNotNull(consultaMedica)){
        		controllerFacade.getConsultaMedicaController().updateConsultaMedica(consultaMedica);
        	}
        	
        	AndroidNotification android = new AndroidNotification();
        	String reg = controllerFacade.getSeniorsUserController().getSeniorRegistratoinId(consultaMedica.getId_usuario());
        	android.sendMessage(consultaMedica, AndroidNotification.OPER_MODIFIED, reg);

		} 
        catch (SeniorsServerInternalException e) {
			e.printStackTrace();
			
			if(e.getMessage().equals(CONSULTAMEDICA_ALREADY_EXISTS)) {
				throw new SeniorsServerException(CONSULTAMEDICA_ALREADY_EXISTS, Status.INTERNAL_SERVER_ERROR);
			}
			
			throw new SeniorsServerException(SOME_WRONG_INFO);
		} 
        catch (Exception e) {
			e.printStackTrace();
			throw new SeniorsServerException(SOME_WRONG_INFO);
		}
        
        return consultaMedica;
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void remove(@PathParam("id") long id) {
        try {
        	ConsultaMedica consultaMedica = controllerFacade.getConsultaMedicaController().remove(id);

        	AndroidNotification android = new AndroidNotification();
        	String reg = controllerFacade.getSeniorsUserController().getSeniorRegistratoinId(consultaMedica.getId_usuario());
        	android.sendMessage(consultaMedica, AndroidNotification.OPER_REMOVED, reg);
        	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
