package org.seniors.rest.api;

import static org.seniors.config.SeniorsServerMessages.SOME_WRONG_INFO;
import static org.seniors.util.ValidationUtils.isNotNull;

import java.util.Date;
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

import org.codehaus.jettison.json.JSONObject;
import org.seniors.config.SeniorsServerMessages;
import org.seniors.exceptions.SeniorsServerException;
import org.seniors.exceptions.SeniorsServerInternalException;
import org.seniors.model.Localizacao;
import org.seniors.model.SeniorsUser;
import org.seniors.pushnotification.impl.android.AndroidNotification;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@Path("/local")
public class LocalizacaoRestService extends GenericRequest {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Localizacao> getAllLocalizacaoInJSON(@PathParam("id") long id_usuario) {
        try {
        	List<Localizacao> Localizacao = controllerFacade.getLocalizacaoController().listLocalizacao(id_usuario);
			return Localizacao;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
    
    @POST
	@Path("/senior")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createLocal( @RequestBody String req) {
    	try {
    		JSONObject obj = new JSONObject(req);
    		String phone = obj.get("phone").toString();
    		String key = obj.get("key").toString();
    		String lat = obj.get("lat").toString();
    		String lon = obj.get("lon").toString();
    		String resp = "OK";
    		if(key != null && phone != null){
            	String[] attr = {"celular", "chave"}; 
            	String[] value = {phone, key}; 
	    		SeniorsUser user = controllerFacade.getSeniorsUserController().verificaSenior(attr, value);
	    		Localizacao local = new Localizacao();
	    		local.setId_usuario(user.getId());
	    		local.setData(new Date());
	    		local.setLat(lat);
	    		local.setLon(lon);
				controllerFacade.getLocalizacaoController().createLocalizacao(local);
    		}
        	return resp;
		} catch (Exception e) {
			throw new SeniorsServerException(SeniorsServerMessages.INVALID_DATA);
		}
    }    
//
//    @GET
//    @Path("{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Localizacao getLocalizacaoById(@PathParam("id") long id) {
//        try {
//			return controllerFacade.getLocalizacaoController().searchById(id);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        return null;
//    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Localizacao create(Localizacao Localizacao) {
//    	try {
//    		if(isNotNull(Localizacao)){
//    			controllerFacade.getLocalizacaoController().createLocalizacao(Localizacao);	
//    		}else{
//    			throw new SeniorsServerException(SOME_EMPTY_FIELD);	
//    		}
//    		
//        	AndroidNotification android = new AndroidNotification();
//        	String reg = controllerFacade.getSeniorsUserController().getSeniorRegistratoinId(Localizacao.getId_usuario());
//        	android.sendMessage(Localizacao, AndroidNotification.OPER_ADD, reg);
//    	} 
//    	catch (SeniorsServerInternalException e) {
//			e.printStackTrace();
//			
//			throw new SeniorsServerException(SOME_WRONG_INFO);
//		} 
//    	catch (Exception e) {
//			e.printStackTrace();
//			throw new SeniorsServerException(SOME_WRONG_INFO);
//		}
//    	return Localizacao;
//    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Localizacao update(Localizacao Localizacao) {
        try {
        	if(isNotNull(Localizacao)){
        		controllerFacade.getLocalizacaoController().updateLocalizacao(Localizacao);
            	
            	AndroidNotification android = new AndroidNotification();
            	String reg = controllerFacade.getSeniorsUserController().getSeniorRegistratoinId(Localizacao.getId_usuario());
            	android.sendMessage(Localizacao, AndroidNotification.OPER_MODIFIED, reg);
        	}
		} 
        catch (SeniorsServerInternalException e) {
			e.printStackTrace();
			
			throw new SeniorsServerException(SOME_WRONG_INFO);
		} 
        catch (Exception e) {
			e.printStackTrace();
			throw new SeniorsServerException(SOME_WRONG_INFO);
		}
        
        return Localizacao;
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void remove(@PathParam("id") long id) {
        try {
			Localizacao Localizacao = controllerFacade.getLocalizacaoController().remove(id);
			
        	AndroidNotification android = new AndroidNotification();
        	String reg = controllerFacade.getSeniorsUserController().getSeniorRegistratoinId(Localizacao.getId_usuario());
        	android.sendMessage(Localizacao, AndroidNotification.OPER_REMOVED, reg);
        	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
