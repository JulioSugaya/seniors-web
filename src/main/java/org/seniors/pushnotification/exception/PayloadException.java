/*
* Copyright (c) 2015 Samsung Electronics Co., Ltd.
* All rights reserved.
*
* This software is a confidential and proprietary information of Samsung
* Electronics, Inc. ("Confidential Information").  You shall not disclose such
* Confidential Information and shall use it only in accordance with the terms
* of the license agreement you entered into with Samsung Electronics.
*/
package org.seniors.pushnotification.exception;

/**
 * Exception used to throw problems on entity process
 */
public class PayloadException extends Exception {

    public PayloadException() {
    }

    public PayloadException(final String message) {
        super(message);
    }

    public PayloadException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
