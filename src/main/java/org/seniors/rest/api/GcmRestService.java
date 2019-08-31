package org.seniors.rest.api;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.seniors.config.SeniorsServerResponse;
import org.seniors.model.Contato;
import org.seniors.pushnotification.exception.PayloadException;
import org.seniors.pushnotification.exception.SendMessageException;
import org.seniors.pushnotification.impl.android.AndroidNotification;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Action class to treat a login request on SeniorsServer
 * 
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 */
@Component
@Path("/gcm")
public class GcmRestService extends GenericRequest implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 778265595185600461L;

	@POST
	@Path("/send")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public SeniorsServerResponse post(@RequestBody String req) {
		JSONObject obj;
		try {
			obj = new JSONObject(req);
			String reg = obj.get("registration").toString();
			
			AndroidNotification android = new AndroidNotification();
			
			Contato c = new Contato();
			c.setId_usuario(2);
			c.setNome("Teste GCM");
			c.setNumero("1234567890");
			
			android.sendMessage(c, AndroidNotification.OPER_ADD, reg);
		} catch (SendMessageException | PayloadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
	}
 
}