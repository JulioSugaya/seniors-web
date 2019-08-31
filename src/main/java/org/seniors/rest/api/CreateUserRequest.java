package org.seniors.rest.api;

import static org.seniors.config.SeniorsServerMessages.SOME_EMPTY_FIELD;
import static org.seniors.config.SeniorsServerMessages.SUCCESS;
import static org.seniors.util.ValidationUtils.LOGGER;
import static org.seniors.util.ValidationUtils.isNotNullAndEmpty;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.seniors.config.SeniorsServerResponse;

/**
 * Action class to treat a login request on WalletServer
 * 
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 * 
 */
@Component
@Path("/new_user")
public class CreateUserRequest extends GenericRequest implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 7376193391672656446L;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public SeniorsServerResponse newUser(@FormParam("name") String name,
			@FormParam("cpf") String cpf, @FormParam("email") String email,
			@FormParam("password") String password,
			@FormParam("device_id") String deviceId){
		try {
			if (isNotNullAndEmpty(name, cpf, email, password,deviceId)) {
				controllerFacade.getUserController().createUser(name, cpf, email, password,	deviceId);
				return SeniorsServerResponse.build().status(SUCCESS);
			}
			
			return SeniorsServerResponse.build().errorCode(SOME_EMPTY_FIELD);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}
}