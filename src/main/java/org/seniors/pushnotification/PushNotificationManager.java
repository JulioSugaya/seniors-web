package org.seniors.pushnotification;

import java.util.List;

import javax.inject.Inject;
import javax.management.Notification;

import org.jboss.logging.Logger;
import org.seniors.pushnotification.annotation.Android;
import org.seniors.pushnotification.annotation.IOS;
import org.seniors.pushnotification.exception.PayloadException;
import org.seniors.pushnotification.exception.SendMessageException;
import org.seniors.pushnotification.impl.android.AndroidPayLoad;

/**
 * Proxy to send the push notification messages for correct platform
 */
public class PushNotificationManager {

    private static final Logger log = Logger.getLogger(PushNotificationManager.class);

    @Inject
    @Android
    private PushNotification pushAndroid;

    @Inject
    @IOS
    private PushNotification pushIOS;

//    @Inject
//    private GCMControll gcmControll;

//    @Inject
//    private IPostRead postRead;

    /**
     * Prepare the payload to send the notification
     *
     * @param notification
     */
    public void sendNotification(final Notification notification) {
//        final String userNameSource = userSource.getTxUserName();
//        final User userTarget = notification.getNbUserIdReceiver();
//        final Authentication authenticationTarget = userTarget.getAuthentication();
//
//        gcmControll.saveNotification(userSource, notification);
//
//        if(authenticationTarget != null) {
//            if (authenticationTarget.getNbDeviceGroup() != null) {
//                if (authenticationTarget.getNbDeviceGroup().intValue() != DeviceGroupEnum.IOS.getId()) {
//                    try {
//                        final Payload payload = fillPayload(notification, authenticationTarget, userNameSource);
//                        log.info("Sending notification to " + userTarget.getTxUserName());
//                        pushAndroid.sendMessage(payload);
//                        log.debug("Notification was sent");
//                    } catch (SendMessageException e) {
//                        log.error("Error on send the payload", e);
//                    } catch (PayloadException e) {
//                        log.error("Error on process the payload", e);
//                    }
//                }else{
//                    try {
//                        final Payload payload = fillPayload(notification, authenticationTarget, userNameSource);
//                        log.info("Sending notification to " + userTarget.getTxUserName());
//                        pushIOS.sendMessage(payload);
//                        log.debug("Notification was sent");
//                    } catch (SendMessageException e) {
//                        log.error("Error on send the payload", e);
//                    } catch (PayloadException e) {
//                        log.error("Error on process the payload", e);
//                    }
//               	
//                }
//            } else {
//                log.error("Device group from authentication target id " + authenticationTarget.getIdAuthentication() + " is not filled ");
//            }
//        } else {
//            log.error("User target does not has login information");
//        }
    }

    /**
     * Send notifications in batch way
     * @param notifications
     */
    public void sendBatchNotification(final List<Notification> notifications) {
        log.info("Prepare to send notifications in batch");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.info("Send notification in batch");
                for(final Notification notification : notifications) {
                    //sendNotification(notification, userSource);
                }
            }
        };
        new Thread(runnable).start();
    }

    /**
     * Fill the payload with notification and user informations
     * @param notification
     * @param authSource
     * @param userNameSource
     * @return
     */
    private Payload fillPayload(final Notification notification, final String userNameSource) {
        Payload payLoad = null;
//        if(authSource.getPushNotificationId() != null) {
//            if (authSource.getNbDeviceGroup().intValue() != DeviceGroupEnum.IOS.getId()) {
//                payLoad = new AndroidPayLoad.Build()
//                        .action(notification.getNbActionType())
//                        .notifyId(notification.getNotificationId())
//                        .notifyType(notification.getNbNotificationType())
//                        .postId(notification.getNbPostId())
//                        .registrationId(authSource.getPushNotificationId())
//                        .url(getUrlFromPost(notification))
//                        .userName(userNameSource)
//                        .build();
//            } else {
//                //TODO Pyaload to IOS, implements-me
//                payLoad = new IOSPayload.Build()
//                .action(notification.getNbActionType())
//                .notifyId(notification.getNotificationId().toString())
//                .notifyType(notification.getNbNotificationType())
//                .postId(notification.getNbPostId())
//                .url(getUrlFromPost(notification))
//                .userName(userNameSource)
//                .build();
//            }
//        } else {
//            log.error("Push notification ID from authentication id " + authSource.getIdAuthentication() + " is not filled");
//        }

        return payLoad;
    }

    /**
     * Get url from post
     *
     * @param notification
     * @return
     */
    public String getUrlFromPost(final Notification notification) {
//        final Post post = postRead.findByField(Post.class, PostConst.ID, notification.getNbPostId());
        String url = "";
//        if(notification.getNbPostId() != null) {
//            url = post.getCardInfo().getTxUrl();
//        }
        return url;
    }


}
