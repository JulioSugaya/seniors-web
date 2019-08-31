package org.seniors.config;

/**
 * Server error codes
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 */
public interface SeniorsServerMessages {
	
	public static final String SUCCESS 						= "WS00";
	public static final String USER_NOT_FOUND 				= "WS01";
	public static final String USER_ALREADY_EXISTS   		= "WS02";
	public static final String PASSWORD_INCORRECT  			= "WS03";
	public static final String SOME_EMPTY_FIELD 			= "WS04";
	public static final String SOME_WRONG_INFO 				= "WS05";
	public static final String EMAIL_OR_CPF_INCORRECT	    = "WS06";
	public static final String OFFER_WITH_ZERO_POINTS	    = "WS07";
	public static final String MEDICACAO_ALREADY_EXISTS 	= "WS08";
	public static final String ATIVIDADE_ALREADY_EXISTS 	= "WS09";
	public static final String CONSULTAMEDICA_ALREADY_EXISTS 	= "WS09";
	public static final String CONTATO_ALREADY_EXISTS 	= "WS11";
	public static final String INVALID_DATA 	= "Invalid Data";
}