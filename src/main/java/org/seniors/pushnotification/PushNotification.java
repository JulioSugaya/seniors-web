/*
 * Copyright (c) 2015 Samsung Electronics Co., Ltd.
 * All rights reserved.
 *
 * This software is a confidential and proprietary information of Samsung
 * Electronics, Inc. ("Confidential Information").  You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with Samsung Electronics.
 */
package org.seniors.pushnotification;

import org.seniors.pushnotification.exception.PayloadException;
import org.seniors.pushnotification.exception.SendMessageException;

public interface PushNotification {

	/**
	 * Send the message with push notification
	 *
	 * @param payload
	 * @throws SendMessageException
	 * @throws PayloadException
	 */
	void sendMessage(Content payload) throws SendMessageException,
			PayloadException;

}
