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
 * @author taink
 */
@Entity
@Table(name = "APP_USER")
public class AppUser implements Serializable {

    public static final String TABLE_NAME = "APP_USER";
    public static final String SEQ_NAME = "APP_USER_SEQ";

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_generator")
    @SequenceGenerator(name="app_user_generator", allocationSize=1, sequenceName = "APP_USER_SEQ")
    private Long id;
    @Column(name = "MSISDN", nullable = false)
    private String msisdn;
    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;
    @Column(name = "ACCOUNT_TYPE", nullable = false)
    private Long accountType = -1L;
    @Column(name = "OTP_TYPE", nullable = false)
    private Long otpType = 0L;
    @Column(name = "ALLOW_MULTIPLE_DEVICE", nullable = false)
    private Long allowMultipleDevice = 0L;
    @Column(name = "ALLOW_CHANGE_DEVICE", nullable = false)
    private Long allowChangeDevice = 0L;
    @Column(name = "NUM_LOGIN_FAIL", nullable = false)
    private Long numLoginFail = 0L;
    @Column(name = "CHECK_LOGIN_FAIL_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date checkLoginFailTime = new Date();
    @Column(name = "LAST_LOGIN_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastLogLongime = new Date();
    @Column(name = "CREATE_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createTime = new Date();
    @Column(name = "STATUS", nullable = false)
    private Long status = 1L;
    @Column(name = "EXPIRED_TOKEN", nullable = false)
    private Long expiredToken = 30L; //Default 30 minutes
    @Column(name = "CLIENT_TYPE", nullable = false)
    private Long clientType = -1L;
    @Column(name = "LANGUAGE")
    private String language;
    @Column(name = "CONSUMER_QRCODE")
    private String consumerQrCode;
    @Column(name = "ROLE_ID")
    private Long roleId;
    @Column(name = "AVATAR_URL")
    private String avatarUrl;
    @Column(name = "ACCOUNT_ID")
    private Long accountId;
    @Column(name = "ORG_ID")
    private Long orgId;
    @Column(name = "SUB_ID")
    private Long subId;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "SUB_EXPIRED_DATE")
    private Date subExpiredDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getAccountType() {
        return accountType;
    }

    public void setAccountType(Long accountType) {
        this.accountType = accountType;
    }

    public Long getOtpType() {
        return otpType;
    }

    public void setOtpType(Long otpType) {
        this.otpType = otpType;
    }

    public Long getAllowMultipleDevice() {
        return allowMultipleDevice;
    }

    public void setAllowMultipleDevice(Long allowMultipleDevice) {
        this.allowMultipleDevice = allowMultipleDevice;
    }

    public Long getAllowChangeDevice() {
        return allowChangeDevice;
    }

    public void setAllowChangeDevice(Long allowChangeDevice) {
        this.allowChangeDevice = allowChangeDevice;
    }

    public Long getNumLoginFail() {
        return numLoginFail;
    }

    public void setNumLoginFail(Long numLoginFail) {
        this.numLoginFail = numLoginFail;
    }

    public Date getCheckLoginFailTime() {
        return checkLoginFailTime;
    }

    public void setCheckLoginFailTime(Date checkLoginFailTime) {
        this.checkLoginFailTime = checkLoginFailTime;
    }

    public Date getLastLogLongime() {
        return lastLogLongime;
    }

    public void setLastLogLongime(Date lastLogLongime) {
        this.lastLogLongime = lastLogLongime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getExpiredToken() {
        return expiredToken;
    }

    public void setExpiredToken(Long expiredToken) {
        this.expiredToken = expiredToken;
    }

    public Long getClientType() {
        return clientType;
    }

    public void setClientType(Long clientType) {
        this.clientType = clientType;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getConsumerQrCode() {
        return consumerQrCode;
    }

    public void setConsumerQrCode(String consumerQrCode) {
        this.consumerQrCode = consumerQrCode;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public Date getSubExpiredDate() {
        return subExpiredDate;
    }

    public void setSubExpiredDate(Date subExpiredDate) {
        this.subExpiredDate = subExpiredDate;
    }

}
