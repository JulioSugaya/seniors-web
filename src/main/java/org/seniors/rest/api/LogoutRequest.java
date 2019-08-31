package org.seniors.rest.api;

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

/**
 * Action class to treat a logout request on WalletServer
 * 
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 */
@Component
@Path("/logout")
public class LogoutRequest extends GenericRequest implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 778265595185600461L;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String post(@FormParam("device_id") String deviceId) {
		try {
			if (isNotNullAndEmpty(deviceId)) {
				controllerFacade.getSeniorsUserController().logout(deviceId);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}

		return null;
	}
}