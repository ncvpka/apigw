package vn.supperapp.apigw.db.dto;


import vn.supperapp.apigw.beans.GroupDataInfo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "GROUP_DATA")
@SqlResultSetMapping(name = "GROUP_DATA_MAPPING", classes = {@ConstructorResult(
        targetClass = GroupDataInfo.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "code", type = String.class),
        @ColumnResult(name = "name", type = String.class),
        @ColumnResult(name = "serviceCode", type = String.class),
        @ColumnResult(name = "partnerCode", type = String.class),
        @ColumnResult(name = "status", type = Integer.class),
})})

public class GroupData implements java.io.Serializable {
    public static final String GROUP_DATA_MAPPING = "GROUP_DATA_MAPPING";
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SERVICE_CODE")
    private String serviceCode;

    @Column(name = "PARTNER_CODE")
    private String partnerCode;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "CREATE_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
