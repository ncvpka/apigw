package vn.supperapp.apigw.messaging.db.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "NOTIFICATION_LOG")
public class NotificationLog implements Serializable {
    public static final String TABLE_NAME = "NOTIFICATION_LOG";
    public static final String SEQ_NAME = "NOTIFICATION_LOG_SEQ";

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_log_generator")
    @SequenceGenerator(name = "notification_log_generator", allocationSize = 1, sequenceName = "NOTIFICATION_LOG_SEQ")
    private Long id;
    @Column(name = "REF_ID")
    private String refId;
    @Column(name = "APP_USER_ID")
    private Long appUserId;
    @Column(name = "MSISDN", nullable = false)
    private String msisdn;
    @Column(name = "STATUS")
    private Long status; //0: New; 1: Read
    @Column(name = "CLIENT_TYPE", nullable = false)
    private int clientType = -1;
    @Column(name = "OBJ_TYPE")
    private String objType;
    @Column(name = "OBJ_ID")
    private Long objId;
    @Column(name = "OBJ_VALUE")
    private String objValue;
    @Column(name = "NOTIFICATION_TYPE")
    private Long notificationType;
    @Column(name = "TITLE")
    private String title; //JSON data (localizable)
    @Column(name = "SHORT_CONTENT")
    private String shortContent; //JSON data (localizable)
    @Column(name = "FULL_CONTENT")
    private String fullContent; //JSON data (localizable)
    @Column(name = "OBJ_DATA")
    private String objData; //JSON data
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @Column(name = "ACCOUNT_ID")
    private String accountId;
    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    public NotificationLog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public Long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public Long getObjId() {
        return objId;
    }

    public void setObjId(Long objId) {
        this.objId = objId;
    }

    public String getObjValue() {
        return objValue;
    }

    public void setObjValue(String objValue) {
        this.objValue = objValue;
    }

    public Long getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(Long notificationType) {
        this.notificationType = notificationType;
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

    public String getFullContent() {
        return fullContent;
    }

    public void setFullContent(String fullContent) {
        this.fullContent = fullContent;
    }

    public String getObjData() {
        return objData;
    }

    public void setObjData(String objData) {
        this.objData = objData;
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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
