package org.seniors.config;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeniorsServerResponse implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8509060510640153408L;
	private String code;
	private String detail;
	private String status;

	public SeniorsServerResponse(String detail) {
		super();
		this.detail = detail;
	}

	public SeniorsServerResponse() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return A Builder pattern of  Server 	
	 */
	public static SeniorsServerResponse build(){
		return new SeniorsServerResponse();
	}
	
	public SeniorsServerResponse errorCode(String errorCode){
		setCode(errorCode);
		return this;
	}
	
	public SeniorsServerResponse errorDetail(String errorDetail){
		setDetail(errorDetail);
		return this;
	}
	
	public SeniorsServerResponse status(String status){
		setStatus(status);
		return this;
	}
}