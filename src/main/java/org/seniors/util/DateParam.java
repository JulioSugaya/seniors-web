package org.seniors.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

/**
 * This class can be used as a Jersey <code>@FormParam</code> parameter to parse
 * a String format to a Brazilian Date format.
 * 
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 */
public class DateParam {

	private Date date;

	public DateParam(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.date = sdf.parse(dateStr);
		} catch (ParseException pe) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
	}

	/**
	 * @return A date formated.
	 */
	public Date getDate() {
		return this.date;
	}
}