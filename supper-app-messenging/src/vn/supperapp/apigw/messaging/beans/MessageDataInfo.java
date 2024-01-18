package vn.supperapp.apigw.messaging.beans;

import java.util.Map;

public class MessageDataInfo {
    private Long messageLogId;
    private String refId;
    private String sender;
    private String receiver;
    private String contentType;
    private String content;
    private String contentLanguage;
    private boolean isUnicode;
    private boolean sendSms;

    private String apsTitle;
    private Map<String, String> apsData;
    private String channel;

    public Long getMessageLogId() {
        return messageLogId;
    }

    public void setMessageLogId(Long messageLogId) {
        this.messageLogId = messageLogId;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentLanguage() {
        return contentLanguage;
    }

    public void setContentLanguage(String contentLanguage) {
        this.contentLanguage = contentLanguage;
    }

    public boolean isUnicode() {
        return isUnicode;
    }

    public void setUnicode(boolean unicode) {
        isUnicode = unicode;
    }

    public String getApsTitle() {
        return apsTitle;
    }

    public void setApsTitle(String apsTitle) {
        this.apsTitle = apsTitle;
    }

    public Map<String, String> getApsData() {
        return apsData;
    }

    public void setApsData(Map<String, String> apsData) {
        this.apsData = apsData;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean isSendSms() {
        return sendSms;
    }

    public void setSendSms(boolean sendSms) {
        this.sendSms = sendSms;
    }
}
