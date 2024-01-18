package vn.supperapp.apigw.beans;

import java.util.Date;
public class TimeKeepingReportCMSInfo {
    private Long userId;
    private String code;
    private String fullName;
    private Date timeStart;
    private Date timeEnd;
    private float totalDay;
    private Long minuteLate;
    private Long minuteEarly;
    private Long overTime;
    private Float coeffcientOvertime;

    private String type;

    private Long shiftId;

    private String shiftCode;
    private Date dateWork;
    private String fromTime;
    private String toTime;
    private Long timekeepingType;
    private Long isFullSaturday;

    public TimeKeepingReportCMSInfo() {
    }

    public TimeKeepingReportCMSInfo(Long userId, String code, String fullName, Date timeStart, Date timeEnd, float totalDay, Long minuteLate, Long minuteEarly, Long overTime, Float coeffcientOvertime, String type, Long shiftId, String shiftCode, Date dateWork, String fromTime, String toTime, Long timekeepingType, Long isFullSaturday) {
        this.userId = userId;
        this.code = code;
        this.fullName = fullName;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.totalDay = totalDay;
        this.minuteLate = minuteLate;
        this.minuteEarly = minuteEarly;
        this.overTime = overTime;
        this.coeffcientOvertime = coeffcientOvertime;
        this.type = type;
        this.shiftId = shiftId;
        this.shiftCode = shiftCode;
        this.dateWork = dateWork;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.timekeepingType = timekeepingType;
        this.isFullSaturday = isFullSaturday;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public float getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(float totalDay) {
        this.totalDay = totalDay;
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

    public Long getOverTime() {
        return overTime;
    }

    public void setOverTime(Long overTime) {
        this.overTime = overTime;
    }

    public Float getCoeffcientOvertime() {
        return coeffcientOvertime;
    }

    public void setCoeffcientOvertime(Float coeffcientOvertime) {
        this.coeffcientOvertime = coeffcientOvertime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public String getShiftCode() {
        return shiftCode;
    }

    public void setShiftCode(String shiftCode) {
        this.shiftCode = shiftCode;
    }

    public Date getDateWork() {
        return dateWork;
    }

    public void setDateWork(Date dateWork) {
        this.dateWork = dateWork;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public Long getTimekeepingType() {
        return timekeepingType;
    }

    public void setTimekeepingType(Long timekeepingType) {
        this.timekeepingType = timekeepingType;
    }

    public Long getIsFullSaturday() {
        return isFullSaturday;
    }

    public void setIsFullSaturday(Long isFullSaturday) {
        this.isFullSaturday = isFullSaturday;
    }
}

