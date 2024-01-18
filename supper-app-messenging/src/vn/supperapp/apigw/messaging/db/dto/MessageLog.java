package vn.supperapp.apigw.messaging.db.dto;

import vn.supperapp.apigw.messaging.beans.MessageDataInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "MESSAGE_LOG")
public class MessageLog implements Serializable {
    public static final String TABLE_NAME = "MESSAGE_LOG";
    public static final String SEQ_NAME = "MESSAGE_LOG_SEQ";

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_log_generator")
    @SequenceGenerator(name="message_log_generator", allocationSize=1, sequenceName = "MESSAGE_LOG_SEQ")
    private Long id;
    @Column(name = "APP_USER_ID")
    private Long appUserId;
    @Column(name = "APP_DEVICE_ID")
    private Long appDeviceId;
    @Column(name = "REF_ID")
    private String refId;
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @Column(name = "SENDER")
    private String sender;
    @Column(name = "RECEIVER_MSISDN", nullable = false)
    private String receiverMsisdn;
    @Column(name = "CHANNEL")
    private String channel;
    @Column(name = "MESSAGE_TYPE")
    private String messageType; //SMS; APN (App Push Notification)
    @Column(name = "STATUS")
    private Long status; //0: New; 1: Send success; 2: Fail

    @Column(name = "CONTENT_TYPE")
    private String contentType; //agent (message for agent), customer, others
    @Column(name = "CONTENT_LANGUAGE")
    private String contentLanguage; //en, km, vi, zh
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "OBJ_DATA")
    private String objData; //JSON data
    @Column(name = "FIREBASE_TOKEN")
    private String firebaseToken;

    @Column(name = "OBJ_RESPONSE")
    private String objResponse;
    @Column(name = "SENDER_SOURCE")
    private String senderSource;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "SHORT_CONTENT")
    private String shortContent;

    public MessageLog() {
    }

    public MessageLog(MessageDataInfo msg, String messageType) {
        this.refId = msg.getRefId();
        this.sender = msg.getSender();
        this.receiverMsisdn = msg.getReceiver();
        this.channel = channel;
        this.messageType = messageType;
        this.status = 0L; //Insert new
        this.contentType = msg.getContentType();
        this.contentLanguage = msg.getContentLanguage();
        this.content = msg.getContent();
        this.channel = msg.getChannel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }

    public Long getAppDeviceId() {
        return appDeviceId;
    }

    public void setAppDeviceId(Long appDeviceId) {
        this.appDeviceId = appDeviceId;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiverMsisdn() {
        return receiverMsisdn;
    }

    public void setReceiverMsisdn(String receiverMsisdn) {
        this.receiverMsisdn = receiverMsisdn;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentLanguage() {
        return contentLanguage;
    }

    public void setContentLanguage(String contentLanguage) {
        this.contentLanguage = contentLanguage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getObjData() {
        return objData;
    }

    public void setObjData(String objData) {
        this.objData = objData;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public String getObjResponse() {
        return objResponse;
    }

    public void setObjResponse(String objResponse) {
        this.objResponse = objResponse;
    }

    public String getSenderSource() {
        return senderSource;
    }

    public void setSenderSource(String senderSource) {
        this.senderSource = senderSource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }
}
