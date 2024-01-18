package vn.supperapp.apigw.db.dto;
// Generated Feb 28, 2018 5:41:20 PM by Hibernate Tools 4.3.1

import vn.supperapp.apigw.beans.TimeKeepingInfo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TIMEKEEPING")
@SqlResultSetMapping(name = "TIMEKEEPING_MAPPING", classes = {@ConstructorResult(
        targetClass = TimeKeepingInfo.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "shiftName", type = String.class),
        @ColumnResult(name = "fullName", type = String.class),
        @ColumnResult(name = "status", type = Long.class),
        @ColumnResult(name = "dateWork", type = Date.class),
        @ColumnResult(name = "timeStart", type = Date.class),
        @ColumnResult(name = "timeEnd", type = Date.class),
        @ColumnResult(name = "workDay", type = Double.class),
        @ColumnResult(name = "overTime", type = Long.class),
        @ColumnResult(name = "note", type = String.class),

})})
public class TimeKeeping implements java.io.Serializable {
    
    public static final String TABLE_NAME = "TIMEKEEPING";
    public static final String SEQ_NAME = "TIMEKEEPING_SEQ";
    public static final String TIMEKEEPING_MAPPING = "TIMEKEEPING_MAPPING";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timekeeping_generator")
    @SequenceGenerator(name = "timekeeping_generator", allocationSize = 1, sequenceName = "TIMEKEEPING_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "SHIFT_ID")
    private Long shiftId;
    @Column(name = "BRANCH_ID")
    private Long branchId;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "DATE_WORK")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateWork;
    @Column(name = "TIME_START")
    private Date timeStart;
    @Column(name = "TIME_END")
    private Date timeEnd;
    @Column(name = "HALF_DAY")
    private Long halfDay;
    @Column(name = "OVERTIME")
    private Long overTime;
    @Column(name = "COEFFICIENT_OVERTIME")
    private float coefficientOvertime;
    @Column(name = "ISPROPOSE")
    private Long isPropose;
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
    @Column(name = "LATITUDE")
    private double latitude;
    @Column(name = "LONGITUDE")
    private double longitude;

    @Transient
    private double workDay;

    @Column(name = "NOTE")
    private String note;

    @Transient
    private String shiftName;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDateWork() {
        return dateWork;
    }

    public void setDateWork(Date dateWork) {
        this.dateWork = dateWork;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Long getHalfDay() {
        return halfDay;
    }

    public void setHalfDay(Long halfDay) {
        this.halfDay = halfDay;
    }

    public Long getOverTime() {
        return overTime;
    }

    public void setOverTime(Long overTime) {
        this.overTime = overTime;
    }

    public float getCoefficientOvertime() {
        return coefficientOvertime;
    }

    public void setCoefficientOvertime(float coefficientOvertime) {
        this.coefficientOvertime = coefficientOvertime;
    }

    public Long getIsPropose() {
        return isPropose;
    }

    public void setIsPropose(Long isPropose) {
        this.isPropose = isPropose;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getWorkDay() {
        return workDay;
    }

    public void setWorkDay(double workDay) {
        this.workDay = workDay;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }
}
