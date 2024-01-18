package vn.supperapp.apigw.beans;

import com.google.gson.reflect.TypeToken;
import vn.supperapp.apigw.db.dto.NotificationLog;
import vn.supperapp.apigw.utils.CommonUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TruongLe
 * @Date 14/03/2022
 * @see NotificationLogInfo
 */

public class NotificationLogInfo {
    private Long id;
    private String refId;
    private Date createTime = new Date();
    private Date updateTime;

    private String msisdn;
    private Long status; //0: New; 1: Read
    private int clientType = -1;

    private Long notificationType;
    private String title; //JSON data (localizable)
    private String shortContent; //JSON data (localizable)
    private String fullContent; //JSON data (localizable)
    private String transactionId;
    private String objType;
    private String deepLink;
    private String refNotiId;
    private Long appUserId;

    public NotificationLogInfo() {
    }

    public NotificationLogInfo(NotificationLog nl, String language) {
        this.id = nl.getId();
        this.refId = nl.getRefId();
        this.createTime = nl.getCreateTime();
        this.updateTime = nl.getUpdateTime();
        this.msisdn = nl.getMsisdn();
        this.status = nl.getStatus();
        this.clientType = nl.getClientType();
        this.notificationType = nl.getNotificationType();
        this.objType = nl.getObjType();
        this.deepLink = nl.getDeepLink();
        this.appUserId= nl.getAppUserId();
        Map<String, String> mTmp = null;

        if (!CommonUtils.isNullOrEmpty(nl.getTitle())) {
            try {
                mTmp = CommonUtils.parseJsonToObjectByType(TypeToken.getParameterized(HashMap.class, String.class, String.class).getType(), nl.getTitle());
                this.title = mTmp.get(language);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (!CommonUtils.isNullOrEmpty(nl.getShortContent())) {
            try {
                mTmp = CommonUtils.parseJsonToObjectByType(TypeToken.getParameterized(HashMap.class, String.class, String.class).getType(), nl.getShortContent());
                this.shortContent = mTmp.get(language);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (!CommonUtils.isNullOrEmpty(nl.getFullContent())) {
            try {
                mTmp = CommonUtils.parseJsonToObjectByType(TypeToken.getParameterized(HashMap.class, String.class, String.class).getType(), nl.getFullContent());
                this.fullContent = mTmp.get(language);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getDeepLink() {
        return deepLink;
    }

    public void setDeepLink(String deepLink) {
        this.deepLink = deepLink;
    }

    public Long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }

    public String getRefNotiId() {
        return refNotiId;
    }

    public void setRefNotiId(String refNotiId) {
        this.refNotiId = refNotiId;
    }
}
