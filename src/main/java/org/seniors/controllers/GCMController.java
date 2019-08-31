package org.seniors.controllers;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class GCMController {
	
	Sender sender = new Sender("");
	
	public Result sendMessage(String registrationId) throws IOException{
        Message message = new Message.Builder().build();
        Result result = sender.send(message, registrationId, 5);
        //status = "Sent message to one device: " + result;
        
		return result;
	}

}
