package vn.supperapp.apigw.db.dto;
// Generated Feb 28, 2018 5:41:20 PM by Hibernate Tools 4.3.1

import vn.supperapp.apigw.beans.DayOffInfo;
import vn.supperapp.apigw.beans.TimeKeepingInfo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DAY_OFF")
@SqlResultSetMapping(name = "DAY_OFF_MAPPING", classes = {@ConstructorResult(
        targetClass = DayOffInfo.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "userId", type = Long.class),
        @ColumnResult(name = "fullName", type = String.class),
        @ColumnResult(name = "dateStart", type = Date.class),
        @ColumnResult(name = "dateEnd", type = Date.class),
        @ColumnResult(name = "totalDay", type = Long.class),
        @ColumnResult(name = "reason", type = String.class),
        @ColumnResult(name = "status", type = Long.class),
        @ColumnResult(name = "type", type = String.class),
})})
public class DayOff implements java.io.Serializable {
    
    public static final String TABLE_NAME = "DAY_OFF";
    public static final String SEQ_NAME = "DAY_OFF_SEQ";
    public static final String DAY_OFF_MAPPING = "DAY_OFF_MAPPING";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "day_off_generator")
    @SequenceGenerator(name = "day_off_generator", allocationSize = 1, sequenceName = "DAY_OFF_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "TYPE_ID")
    private Long typeId;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "DATE_START")
    private Date dateStart;
    @Column(name = "DATE_END")
    private Date dateEnd;
    @Column(name = "TOTAL_DAY")
    private Long totalDay;
    @Column(name = "REASON")
    private String reason;
    @Column(name = "MINUTE_LATE")
    private Long minuteLate;
    @Column(name = "MINUTE_EARLY")
    private Long minuteEarly;
    @Column(name = "APPROVE_AT")
    private Date approveAt;
    @Column(name = "APPROVE_BY")
    private String approveBy;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "ORG_ID")
    private Long orgId;
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Column(name = "UPDATE_BY")
    private String updateBy;
    @Column(name = "UPDATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
    @Column(name = "TYPE")
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Long getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(Long totalDay) {
        this.totalDay = totalDay;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getMinuteLate() {
        return minuteLate;
    }

    public void setMinuteLate(Long minuteLate) {
        this.minuteLate = minuteLate;
    }

    public Long getMinuteEarly() {
        return minuteEarly;
    }

    public void setMinuteEarly(Long minuteEarly) {
        this.minuteEarly = minuteEarly;
    }

    public Date getApproveAt() {
        return approveAt;
    }

    public void setApproveAt(Date approveAt) {
        this.approveAt = approveAt;
    }

    public String getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(String approveBy) {
        this.approveBy = approveBy;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
