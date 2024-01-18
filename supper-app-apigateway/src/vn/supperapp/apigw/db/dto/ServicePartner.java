package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SERVICE_PARTNER")
public class ServicePartner implements Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "PARTNER_CODE")
    private String partnerCode;

    @Column(name = "PARTNER_NAME")
    private String partnerName;

    @Column(name = "FEATURE_CODE")
    private String featureCode;

    @Column(name = "PROCESS_CODE")
    private String processCode;

    @Column(name = "SERVICE_CODE")
    private String serviceCode;

    @Column(name = "BASE_URL")
    private String baseUrl;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "CREATE_DATE")
    private String createDate;

    @Column(name = "UPDATE_DATE")
    private String updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
