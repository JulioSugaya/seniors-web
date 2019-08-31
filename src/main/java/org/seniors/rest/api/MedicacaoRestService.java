package org.seniors.rest.api;

import static org.seniors.config.SeniorsServerMessages.MEDICACAO_ALREADY_EXISTS;
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
import org.seniors.model.Medicacao;
import org.seniors.pushnotification.impl.android.AndroidNotification;
import org.springframework.stereotype.Component;

@Component
@Path("/medicacao")
public class MedicacaoRestService extends GenericRequest {

    @POST
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Medicacao> getAllMedicacoesInJSON(@PathParam("id") long id_usuario) {
        try {
        	List<Medicacao> meds = controllerFacade.getMedicacaoController().listMedicacoes(id_usuario);
			return meds;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Medicacao getMedicacaoById(@PathParam("id") long id) {
        try {
			return controllerFacade.getMedicacaoController().searchById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Medicacao create(Medicacao med) {
    	try {
    		if(isNotNull(med)){
    			controllerFacade.getMedicacaoController().createMedicacao(med);	
    		}else{
    			throw new SeniorsServerException(SOME_EMPTY_FIELD);	
    		}
    		    		
        	AndroidNotification android = new AndroidNotification();
        	String reg = controllerFacade.getSeniorsUserController().getSeniorRegistratoinId(med.getId_usuario());
        	android.sendMessage(med, AndroidNotification.OPER_ADD, reg);

    	} 
    	catch (SeniorsServerInternalException e) {
			e.printStackTrace();
			
			if(e.getMessage().equals(MEDICACAO_ALREADY_EXISTS)) {
				throw new SeniorsServerException(MEDICACAO_ALREADY_EXISTS, Status.INTERNAL_SERVER_ERROR);
			}
			
			throw new SeniorsServerException(SOME_WRONG_INFO);
		} 
    	catch (Exception e) {
			e.printStackTrace();
			throw new SeniorsServerException(SOME_WRONG_INFO);
		}
    	
    	return med;
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Medicacao update(Medicacao med) {
        try {
        	if(isNotNull(med)){
        		controllerFacade.getMedicacaoController().updateMedicacao(med);
        	}
        	
        	AndroidNotification android = new AndroidNotification();
        	String reg = controllerFacade.getSeniorsUserController().getSeniorRegistratoinId(med.getId_usuario());
        	android.sendMessage(med, AndroidNotification.OPER_MODIFIED, reg);

		} 
        catch (SeniorsServerInternalException e) {
			e.printStackTrace();
			
			if(e.getMessage().equals(MEDICACAO_ALREADY_EXISTS)) {
				throw new SeniorsServerException(MEDICACAO_ALREADY_EXISTS, Status.INTERNAL_SERVER_ERROR);
			}
			
			throw new SeniorsServerException(SOME_WRONG_INFO);
		} 
        catch (Exception e) {
			e.printStackTrace();
			throw new SeniorsServerException(SOME_WRONG_INFO);
		}
        
        return med;
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void remove(@PathParam("id") long id) {
        try {
			Medicacao med = controllerFacade.getMedicacaoController().remove(id);
			
        	AndroidNotification android = new AndroidNotification();
        	String reg = controllerFacade.getSeniorsUserController().getSeniorRegistratoinId(med.getId_usuario());
        	android.sendMessage(med, AndroidNotification.OPER_REMOVED, reg);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
