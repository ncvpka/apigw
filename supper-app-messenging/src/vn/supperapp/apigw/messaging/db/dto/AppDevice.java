/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.messaging.db.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author luand
 */
@Entity
@Table(name = "APP_DEVICE")
public class AppDevice implements Serializable {

    public static final String TABLE_NAME = "APP_DEVICE";
    public static final String SEQ_NAME = "APP_DEVICE_SEQ";

    public AppDevice() {
    }

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_device_generator")
    @SequenceGenerator(name="app_device_generator", allocationSize=1, sequenceName = "APP_DEVICE_SEQ")
    private Long id;
    @Column(name = "APP_USER_ID")
    private Long appUserId;
    @Column(name = "MSISDN")
    private String msisdn;
    @Column(name = "DEVICE_ID")
    private String deviceId;
    @Column(name = "DEVICE_MODEL")
    private String deviceModel;
    @Column(name = "OS_NAME")
    private String osName;
    @Column(name = "OS_VERSION")
    private String osVersion;
    @Column(name = "APP_VERSION")
    private String appVersion;
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "USE_BIOMETRIC")
    private Integer useBiometric = 0;
    @Column(name = "CLIENT_TYPE")
    private Integer clientType;
    @Column(name = "LAST_LOGIN_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginTime;
    @Column(name = "LAST_LOGOUT_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogoutTime;
    @Column(name = "LAST_SYNC_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSyncTime;
    @Column(name = "FIREBASE_TOKEN")
    private String firebaseToken;
    @Column(name = "APP_PRIVATE_KEY")
    private String appPrivateKey;
    @Column(name = "APP_PUBLIC_KEY")
    private String appPublicKey;
    @Column(name = "ACCESS_TOKEN")
    private String accessToken;
    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;
    @Column(name = "LANGUAGE")
    private String language;
    @Column(name = "SIGNATURE_KEY")
    private String signatureKey;
    @Column(name = "CLIENT_PRIVATE_KEY")
    private String clientPrivateKey;
    @Column(name = "CLIENT_PUBLIC_KEY")
    private String clientPublicKey;

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

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUseBiometric() {
        return useBiometric;
    }

    public void setUseBiometric(Integer useBiometric) {
        this.useBiometric = useBiometric;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLogoutTime() {
        return lastLogoutTime;
    }

    public void setLastLogoutTime(Date lastLogoutTime) {
        this.lastLogoutTime = lastLogoutTime;
    }

    public Date getLastSyncTime() {
        return lastSyncTime;
    }

    public void setLastSyncTime(Date lastSyncTime) {
        this.lastSyncTime = lastSyncTime;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public String getAppPrivateKey() {
        return appPrivateKey;
    }

    public void setAppPrivateKey(String appPrivateKey) {
        this.appPrivateKey = appPrivateKey;
    }

    public String getAppPublicKey() {
        return appPublicKey;
    }

    public void setAppPublicKey(String appPublicKey) {
        this.appPublicKey = appPublicKey;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getSignatureKey() {
        return signatureKey;
    }

    public void setSignatureKey(String signatureKey) {
        this.signatureKey = signatureKey;
    }

    public String getClientPrivateKey() {
        return clientPrivateKey;
    }

    public void setClientPrivateKey(String clientPrivateKey) {
        this.clientPrivateKey = clientPrivateKey;
    }

    public String getClientPublicKey() {
        return clientPublicKey;
    }

    public void setClientPublicKey(String clientPublicKey) {
        this.clientPublicKey = clientPublicKey;
    }
}
