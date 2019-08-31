package org.seniors.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

/**
 * Class that encapsulate a HTTP Exception
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 *
 */
public class SeniorsServerException extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 180807182278069699L;

   /**
    * Create a HTTP 401 (Unauthorized) exception.
    */
    public SeniorsServerException() {
        super(Response.status(Status.UNAUTHORIZED).build());
    }

    /**
     * Create a HTTP 404 (Not Found) exception.
     * @param message the String that is the entity of the 404 response.
     */
    public SeniorsServerException(String message) {
        super(Response.status(Status.UNAUTHORIZED).entity(message).type("text/plain").build());
    }
    
    public SeniorsServerException(String message, StatusType statusType) {
        super(Response.status(statusType).entity(message).type("text/plain").build());
    }
}