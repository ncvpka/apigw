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
 *
 * @author taink
 */

@Entity
@Table(name = "CHECK_IN")
@SqlResultSetMapping(name = "CHECKIN_MAPPING", classes = {@ConstructorResult(
        targetClass = CheckIn.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "msisdn", type = String.class),
        @ColumnResult(name = "type", type = String.class),
        @ColumnResult(name = "referencesId", type = Long.class),
        @ColumnResult(name = "checkInTime", type = Date.class),
        @ColumnResult(name = "createTime", type = Date.class),
        @ColumnResult(name = "updateTime", type = Date.class),
        @ColumnResult(name = "createBy", type = String.class),
        @ColumnResult(name = "updateBy", type = String.class),

})})
public class CheckIn implements Serializable {

    public static final String TABLE_NAME = "CHECK_IN";
    public static final String SEQ_NAME = "CHECK_IN_SEQ";
    public static final String CHECKIN_MAPPING = "CHECKIN_MAPPING";

    public CheckIn() {
    }

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "check_in_generator")
    @SequenceGenerator(name="check_in_generator", allocationSize=1, sequenceName = "CHECK_IN_SEQ")
    private Long id;
    @Column(name = "MSISDN")
    private String msisdn;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "REFERENCES_ID")
    private Long referencesId;

    @Column(name = "CHECK_IN_TIME")
    private Date checkInTime;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "CREATE_BY")
    private String createBy;

    @Column(name = "UPDATE_BY")
    private String updateBy;
    @Column(name = "ORG_ID")
    private Long orgId;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "LATITUDE")
    private Double latitude;
    @Column(name = "LONGITUDE")
    private Double longitude;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getReferencesId() {
        return referencesId;
    }

    public void setReferencesId(Long referencesId) {
        this.referencesId = referencesId;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
