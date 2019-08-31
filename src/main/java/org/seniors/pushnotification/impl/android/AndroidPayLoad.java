/*
* Copyright (c) 2015 Samsung Electronics Co., Ltd.
* All rights reserved.
*
* This software is a confidential and proprietary information of Samsung
* Electronics, Inc. ("Confidential Information").  You shall not disclose such
* Confidential Information and shall use it only in accordance with the terms
* of the license agreement you entered into with Samsung Electronics.
*/
package org.seniors.pushnotification.impl.android;

import org.seniors.pushnotification.Payload;

/**
 * Android payload
 */
public class AndroidPayLoad implements Payload {

    private String registration_id;
    private DataInfo data;


    public String getRegistration_id() {
        return registration_id;
    }

    public DataInfo getData() {
        return data;
    }

    /**
     * Data info used to agregate data info
     */
    public static class DataInfo {

        private Long notfId;
        private Long postId;
        private String userName;
        private String url;
        private Integer action;
        private Integer notifyType;

        public Long getNotfId() {
            return notfId;
        }

        public Long getPostId() {
            return postId;
        }

        public String getUserName() {
            return userName;
        }

        public String getUrl() {
            return url;
        }

        public Integer getAction() {
            return action;
        }

        public Integer getNotifyType() {
            return notifyType;
        }

    }

    /**
     * Builder to payload
     */
    public static class Build {

        private final AndroidPayLoad payLoad;
        private final DataInfo dateInfo;

        public Build() {
            this.payLoad = new AndroidPayLoad();
            this.dateInfo = new DataInfo();
        }

        public Build registrationId(final String registration_id) {
            this.payLoad.registration_id = registration_id;
            return this;
        }

        public Build notifyId(final Long notifyId) {
            this.dateInfo.notfId = notifyId;
            return this;
        }

        public Build postId(final Long postId) {
            this.dateInfo.postId = postId;
            return this;
        }

        public Build userName(final String userName) {
            this.dateInfo.userName = userName;
            return this;
        }

        public Build url(final String url) {
            this.dateInfo.url = url;
            return this;
        }

        public Build action(final Integer action) {
            this.dateInfo.action = action;
            return this;
        }

        public Build notifyType(final Integer notifyType) {
            this.dateInfo.notifyType = notifyType;
            return this;
        }

        public AndroidPayLoad build() {
            this.payLoad.data = dateInfo;
            return this.payLoad;
        }

    }

}
