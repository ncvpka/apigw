package vn.supperapp.apigw.messaging.beans;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.enums.ChannelType;
import vn.supperapp.apigw.utils.enums.Language;

import java.util.HashMap;
import java.util.Map;

public class Message {
    private String channel;
    private String receiverObj;
    private String refId;
    private final Map<String, String> contents;
    private final Map<String, String> titles;
    private String sender;
    private String receiver;
    private AppPushNotificationInfo appPushNotificationInfo;
    private boolean pushNotification = false;
    private String preferLanguage;
    private boolean saveNotificationLog = true;
    private Long notificationType;
    private String accountId;
    private boolean sendSms = true;
    private String transactionId;
    private String objType;

    private Message(Builder builder) {
        this.channel = builder.channel;
        this.receiverObj = builder.receiverObj;
        this.refId = builder.refId;
        this.contents = builder.contents.isEmpty() ? null : ImmutableMap.copyOf(builder.contents);
        this.titles = builder.titles.isEmpty() ? null : ImmutableMap.copyOf(builder.titles);
        this.sender = builder.sender;
        this.receiver = builder.receiver;
        this.appPushNotificationInfo = builder.appPushNotificationInfo;
        this.pushNotification = builder.pushNotification;
        this.preferLanguage = builder.preferLanguage;
        this.saveNotificationLog = builder.saveNotificationLog;
        this.notificationType = builder.notificationType;
        this.accountId = builder.accountId;
        this.sendSms = builder.sendSms;
        this.transactionId = builder.transactionId;
        this.objType = builder.objType;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getReceiverObj() {
        return receiverObj;
    }

    public void setReceiverObj(String receiverObj) {
        this.receiverObj = receiverObj;
    }

    public String getPreferLanguage() {
        return preferLanguage;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(Long notificationType) {
        this.notificationType = notificationType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }


    public boolean validateRequiredFields() {
        return !(CommonUtils.isNullOrEmpty(sender) || CommonUtils.isNullOrEmpty(receiver) || CommonUtils.isNullOrEmpty(refId) || contents == null || contents.isEmpty());
    }

    public String toJsonString() {
        Gson g = new Gson();
        return g.toJson(this);
    }

    public static class Builder {
        private String channel;
        private String receiverObj;
        private String refId;
        private final Map<String, String> contents;
        private final Map<String, String> titles;
        private String sender; //Apply for SMS channel. Alias name (branch name)
        private String receiver;
        private AppPushNotificationInfo appPushNotificationInfo;
        private boolean pushNotification;
        private String preferLanguage;
        private boolean saveNotificationLog;
        private Long notificationType;
        private String accountId;
        private boolean sendSms;
        private String transactionId;
        private String objType;

        public Message build() {
            return new Message(this);
        }

        public String buildJson() {
            Message m = new Message(this);
            Gson g = new Gson();
            return g.toJson(m);
        }

        private Builder() {
            this.contents = new HashMap();
            this.titles = new HashMap();
            this.channel = ChannelType.Unknown.code();
            this.refId = String.format("AUTO%d", System.currentTimeMillis());
            this.pushNotification = false;
            this.saveNotificationLog = true;
            this.sendSms = true;
        }

        public Builder setSendSms(boolean sendSms) {
            this.sendSms = sendSms;
            return this;
        }

        public Builder setPushNotification(boolean pushNotification) {
            this.pushNotification = pushNotification;
            return this;
        }

        public Builder setChannel(String channelType) {
            this.channel = channelType;
            return this;
        }

        public Builder setReceiverObj(String receiverType) {
            this.receiverObj = receiverType;
            return this;
        }

        public Builder setNotificationType(Long notificationType) {
            this.notificationType = notificationType;
            return this;
        }

        public Builder setAccountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder setRefId(String refId) {
            this.refId = refId;
            return this;
        }

        public Builder setSender(String sender) {
            this.sender = sender;
            return this;
        }

        public Builder setReceiver(String receiver) {
            this.receiver = CommonUtils.getMsisdn(receiver);
            return this;
        }

        public Builder setObjType(String objType) {
            this.objType = objType;
            return this;
        }

        public Builder setAppPushNotificationInfo(AppPushNotificationInfo appPushNotificationInfo) {
            this.appPushNotificationInfo = appPushNotificationInfo;
            return this;
        }

        public Builder setSaveNotificationLog(boolean saveNotificationLog) {
            this.saveNotificationLog = saveNotificationLog;
            return this;
        }

        public Builder setTransactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder addContent(String content, Language language) {
            contents.put(language.key(), content);
            return this;
        }

        public Builder setMessageContent(MessageContent messageContent) {
            if (messageContent.getContents() != null && !messageContent.getContents().isEmpty()) {
                this.contents.putAll(messageContent.getContents());
            }
            if (messageContent.getTitles() != null && !messageContent.getTitles().isEmpty()) {
                this.titles.putAll(messageContent.getTitles());
            }
            return this;
        }

        public Builder setPreferLanguage(String preferLanguage) {
            this.preferLanguage = preferLanguage;
            return this;
        }
    }
}
