package org.seniors.util;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validation class to check object values.
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 */
public class ValidationUtils {
	
	
	public static final Logger LOGGER = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

	/**
	 * @param value - Object [String, Collection or Array] value.
	 * @return If some of each type is null.
	 */
	public static boolean isNotNullAndEmpty(Object value){
		
		if(value!=null){

			if(value instanceof String){
				if(!((String)value).trim().isEmpty()){
					return true;
				}
			}else if(value instanceof Collection<?>){	
				
				if(!((Collection<?>) value).isEmpty()){
					return true;
				}
			}else if(value instanceof Object[]){
				if(((Object[]) value).length>0){
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * @param values - Validate if exists any element in an Array null or empty.
	 * @return If there is an element in an Array of Strings.
	 */
	public static boolean isNotNullAndEmpty(String... values){
		
		if(values!=null){
			for (String string : values) {
				if(isNotNullAndEmpty(string)){
					continue;
				}else{
					return false;
				}
			}	
		}else{
			return false;
		}
		
		return true;
	}

	
	/**
	 * @param value - Object value.
	 * @return If the object is null or empty.
	 */
	public static boolean isNullOrEmpty(Object value){
		return !isNotNullAndEmpty(value);
	}
	
	/**
	 * @param value - Object value
	 * @return If an object is null.
	 */
	public static boolean isNotNull(Object value){
		
		if(value!=null){
			return true;
		}
		
		return false;
	}
	
	/**
	 * @param value - Object value
	 * @return If an object is null.
	 */
	public static boolean isNull(Object value){
		return !isNotNull(value);
	}
	
	
}