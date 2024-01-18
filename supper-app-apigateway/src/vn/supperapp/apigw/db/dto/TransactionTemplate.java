/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author TruongLe
 */
@Entity
@Table(name = "TRANSACTION_TEMPLATE")
public class TransactionTemplate implements Serializable {

    public static final String TABLE_NAME = "TRANSACTION_TEMPLATE";
    public static final String SEQ_NAME = "TRANSACTION_TEMPLATE_SEQ";

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_template_generator")
    @SequenceGenerator(name = "transaction_template_generator", allocationSize = 1, sequenceName = "TRANSACTION_TEMPLATE_SEQ")
    private Long id;
    @Column(name = "APP_DEVICE_ID")
    private Long appDeviceId;
    @Column(name = "APP_USER_ID", nullable = false)
    private Long appUserId;
    @Column(name = "MSISDN", nullable = false)
    private String msisdn;
    @Column(name = "STATUS", nullable = false)
    private int status = 1;
    @Column(name = "FEATURE_CODE", nullable = false)
    private String featureCode;
    @Column(name = "TRANS_ID")
    private String transId;
    @Column(name = "TPL_PARTNER_CODE")
    private String tplPartnerCode;
    @Column(name = "TPL_SERVICE_CODE")
    private String tplServiceCode;
    @Column(name = "TPL_ACCOUNT_CODE")
    private String tplAccountCode;
    @Column(name = "TPL_AMOUNT")
    private String tplAmount;
    @Column(name = "TPL_CONTENT")
    private String tplContent;
    @Column(name = "TPL_NAME")
    private String tplName;
    @Column(name = "TPL_DATA1")
    private String tplData1;
    @Column(name = "TPL_DATA2")
    private String tplData2;
    @Column(name = "TPL_DATA3")
    private String tplData3;
    @Column(name = "TPL_DATA4")
    private String tplData4;
    @Column(name = "TPL_DATA5")
    private String tplData5;
    @Column(name = "TPL_TEXT_SEARCH")
    private String tplTextSearch;
    @Column(name = "CREATE_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createTime = new Date();
    @Column(name = "UPDATE_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updateTime;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "RESPONSE_TYPE")
    private String responseType;
    @Column(name = "RESPONSE_CONTENT")
    private String responseContent;
    @Column(name = "ACCOUNT_ID")
    private String accountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getTplPartnerCode() {
        return tplPartnerCode;
    }

    public void setTplPartnerCode(String tplPartnerCode) {
        this.tplPartnerCode = tplPartnerCode;
    }

    public String getTplServiceCode() {
        return tplServiceCode;
    }

    public void setTplServiceCode(String tplServiceCode) {
        this.tplServiceCode = tplServiceCode;
    }

    public String getTplAccountCode() {
        return tplAccountCode;
    }

    public void setTplAccountCode(String tplAccountCode) {
        this.tplAccountCode = tplAccountCode;
    }

    public String getTplAmount() {
        return tplAmount;
    }

    public void setTplAmount(String tplAmount) {
        this.tplAmount = tplAmount;
    }

    public String getTplContent() {
        return tplContent;
    }

    public void setTplContent(String tplContent) {
        this.tplContent = tplContent;
    }

    public String getTplName() {
        return tplName;
    }

    public void setTplName(String tplName) {
        this.tplName = tplName;
    }

    public String getTplData1() {
        return tplData1;
    }

    public void setTplData1(String tplData1) {
        this.tplData1 = tplData1;
    }

    public String getTplData2() {
        return tplData2;
    }

    public void setTplData2(String tplData2) {
        this.tplData2 = tplData2;
    }

    public String getTplData3() {
        return tplData3;
    }

    public void setTplData3(String tplData3) {
        this.tplData3 = tplData3;
    }

    public String getTplData4() {
        return tplData4;
    }

    public void setTplData4(String tplData4) {
        this.tplData4 = tplData4;
    }

    public String getTplData5() {
        return tplData5;
    }

    public void setTplData5(String tplData5) {
        this.tplData5 = tplData5;
    }

    public String getTplTextSearch() {
        return tplTextSearch;
    }

    public void setTplTextSearch(String tplTextSearch) {
        this.tplTextSearch = tplTextSearch;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void buildTextSearch() {

    }
}
