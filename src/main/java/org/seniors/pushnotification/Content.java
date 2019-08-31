package org.seniors.pushnotification;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Content implements Serializable {

    private String registration_ids;
    private Map<String,String> data;
    
    public void createData(String title, String message){
        if(data == null)
            data = new HashMap<String,String>();

        data.put("title", title);
        data.put("message", message);
    }
    
    public Map<String, String> getData(){
    	return data;
    } 

	public String getRegistration_ids() {
		return registration_ids;
	}

	public void setRegistration_ids(String registration_ids) {
		this.registration_ids = registration_ids;
	}
}
