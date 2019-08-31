package org.seniors.pushnotification.impl.android;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jboss.logging.Logger;
import org.seniors.pushnotification.annotation.Android;
import org.seniors.pushnotification.exception.PayloadException;
import org.seniors.pushnotification.exception.SendMessageException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;

/**
 * Push notification implementation to android targets
 */
@Android
public class AndroidNotification {

    private static final Logger log = Logger.getLogger(AndroidNotification.class);


    private static final String KEY_MESSAGE = "message";
    private static final String OPERATION = "operation";
    private static final String CLASSE = "class";

    public static final String OPER_ADD = "ADD";
    public static final String OPER_MODIFIED = "MODIFIED";
    public static final String OPER_REMOVED = "REMOVED";

    private String credential;
    private final Sender sender;
    private String messageKey;
    private Integer timeToLive;
    private Boolean deleyWithIdle;


    /**
     * Constructor with parameters
     */
    public AndroidNotification() {
        loadProperties();
        sender = new Sender(this.credential);
    }

    private void loadProperties() {
        Properties props = new Properties();
        try {
            final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            final InputStream propertyFile = classloader.getResourceAsStream("push.properties");

            props.load(propertyFile);

            this.credential = props.getProperty("gcm.project.credential");
            this.messageKey = props.getProperty("gcm.message.key");
            this.timeToLive = Integer.parseInt(props.getProperty("gcm.ttl"));
            this.deleyWithIdle = Boolean.parseBoolean(props.getProperty("gcm.delay.with.idle"));
        } catch (Exception e) {
            log.error("Error on load the push notification file properties", e);
            this.credential = "";
        }
    }

    public void sendMessage(Object obj, String oper, String reg) throws SendMessageException, PayloadException {
		
		String dados = convertPayladToString(obj);
		if(reg != null){
	        final Message messageObject = configMessage(obj.getClass().getSimpleName(), dados, oper);
	        try {
	            final Result cast = sender.send(messageObject, reg, 2);
	            if(cast.getMessageId() == null) {
	                String errorCode = cast.getErrorCodeName();
	                log.error("Error to send push notifications. Error code: " + errorCode);
	            }
	        } catch (IOException e) {
	            log.error("Error on send a multicast push notification", e);
	            throw new SendMessageException(e);
	        }
		}
    }

    /**
     * Message object configurations
     * @param message
     * @return
     */
    private Message configMessage(String classe, String data, String oper) {
        final Message pushMessage = new Message.Builder()
                .collapseKey(messageKey)
                .timeToLive(timeToLive)
                .delayWhileIdle(deleyWithIdle)
                .addData(OPERATION, oper)
                .addData(CLASSE, classe)
                .addData(KEY_MESSAGE, data)
                .build();

        return pushMessage;
    }

    /**
     * Convert a payload object to string
     *
     * @param payload
     * @return
     * @throws PayloadException
     */
    public String convertPayladToString(Object payload) throws PayloadException {
        if(payload == null) {
            throw new PayloadException("Error on convert payload messages");
        }
        Gson gson = new Gson();
    	String json = gson.toJson(payload);
        return json;
    }

//    /**
//     * Get target
//     *
//     * @param payload
//     * @return
//     */
//    private List<String> getTargets(Content payload) {
//        final String registration = payload.getRegistration_ids();
//        final List<String> targets = new ArrayList<String>();
//        targets.add(registration);
//        return targets;
//    }
    
}
