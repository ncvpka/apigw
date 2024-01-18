package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "MESSAGE_LOG", schema = "SUPPERAPP", catalog = "")
public class MessageLog {
    public static final String TABLE_NAME = "MESSAGE_LOG";
    public static final String SEQ_NAME = "MESSAGE_LOG_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_log_generator")
    @SequenceGenerator(name = "message_log_generator", allocationSize = 1, sequenceName = "message_log_SEQ")
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "APP_DEVICE_ID")
    private Long appDeviceId;
    @Basic
    @Column(name = "APP_USER_ID")
    private Long appUserId;
    @Basic
    @Column(name = "REF_ID")
    private String refId;
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Basic
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    @Basic
    @Column(name = "SENDER")
    private String sender;
    @Basic
    @Column(name = "RECEIVER_MSISDN")
    private String receiverMsisdn;
    @Basic
    @Column(name = "CHANNEL")
    private String channel;
    @Basic
    @Column(name = "MESSAGE_TYPE")
    private String messageType;
    @Basic
    @Column(name = "STATUS")
    private Long status;
    @Basic
    @Column(name = "CONTENT_TYPE")
    private String contentType;
    @Basic
    @Column(name = "CONTENT_LANGUAGE")
    private String contentLanguage;
    @Basic
    @Column(name = "CONTENT")
    private String content;
    @Basic
    @Column(name = "OBJ_DATA")
    private String objData;
    @Basic
    @Column(name = "FIREBASE_TOKEN")
    private String firebaseToken;
    @Basic
    @Column(name = "OBJ_RESPONSE")
    private String objResponse;
    @Basic
    @Column(name = "SENDER_SOURCE")
    private String senderSource;
    @Basic
    @Column(name = "TITLE")
    private String title;
    @Basic
    @Column(name = "SHORT_CONTENT")
    private String shortContent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getAppDeviceId() {
        return appDeviceId;
    }

    public void setAppDeviceId(Long appDeviceId) {
        this.appDeviceId = appDeviceId;
    }

    public Long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageLog that = (MessageLog) o;

        if (id != that.id) return false;
        if (appDeviceId != null ? !appDeviceId.equals(that.appDeviceId) : that.appDeviceId != null) return false;
        if (appUserId != null ? !appUserId.equals(that.appUserId) : that.appUserId != null) return false;
        if (refId != null ? !refId.equals(that.refId) : that.refId != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (sender != null ? !sender.equals(that.sender) : that.sender != null) return false;
        if (receiverMsisdn != null ? !receiverMsisdn.equals(that.receiverMsisdn) : that.receiverMsisdn != null)
            return false;
        if (channel != null ? !channel.equals(that.channel) : that.channel != null) return false;
        if (messageType != null ? !messageType.equals(that.messageType) : that.messageType != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (contentType != null ? !contentType.equals(that.contentType) : that.contentType != null) return false;
        if (contentLanguage != null ? !contentLanguage.equals(that.contentLanguage) : that.contentLanguage != null)
            return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (objData != null ? !objData.equals(that.objData) : that.objData != null) return false;
        if (firebaseToken != null ? !firebaseToken.equals(that.firebaseToken) : that.firebaseToken != null)
            return false;
        if (objResponse != null ? !objResponse.equals(that.objResponse) : that.objResponse != null) return false;
        if (senderSource != null ? !senderSource.equals(that.senderSource) : that.senderSource != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (shortContent != null ? !shortContent.equals(that.shortContent) : that.shortContent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (appDeviceId != null ? appDeviceId.hashCode() : 0);
        result = 31 * result + (appUserId != null ? appUserId.hashCode() : 0);
        result = 31 * result + (refId != null ? refId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (receiverMsisdn != null ? receiverMsisdn.hashCode() : 0);
        result = 31 * result + (channel != null ? channel.hashCode() : 0);
        result = 31 * result + (messageType != null ? messageType.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (contentType != null ? contentType.hashCode() : 0);
        result = 31 * result + (contentLanguage != null ? contentLanguage.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (objData != null ? objData.hashCode() : 0);
        result = 31 * result + (firebaseToken != null ? firebaseToken.hashCode() : 0);
        result = 31 * result + (objResponse != null ? objResponse.hashCode() : 0);
        result = 31 * result + (senderSource != null ? senderSource.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (shortContent != null ? shortContent.hashCode() : 0);
        return result;
    }
}
