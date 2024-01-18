package vn.supperapp.apigw.beans;

import java.util.Date;
import java.util.List;

public class TimeKeepingReportCMSResponse {
    private String code;
    private String fullName;
    private List<TimeKeepingEveryDayInfo> workingDays;
    private float totalWorking;
    private Long paidLeave;
    private Long goLateLeaveEarlyMinutes;
    private float overtime;
    private float parkingSupport;

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

    public List<TimeKeepingEveryDayInfo> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(List<TimeKeepingEveryDayInfo> workingDays) {
        this.workingDays = workingDays;
    }

    public float getTotalWorking() {
        return totalWorking;
    }

    public void setTotalWorking(float totalWorking) {
        this.totalWorking = totalWorking;
    }

    public Long getPaidLeave() {
        return paidLeave;
    }

    public void setPaidLeave(Long paidLeave) {
        this.paidLeave = paidLeave;
    }

    public Long getGoLateLeaveEarlyMinutes() {
        return goLateLeaveEarlyMinutes;
    }

    public void setGoLateLeaveEarlyMinutes(Long goLateLeaveEarlyMinutes) {
        this.goLateLeaveEarlyMinutes = goLateLeaveEarlyMinutes;
    }

    public float getOvertime() {
        return overtime;
    }

    public void setOvertime(float overtime) {
        this.overtime = overtime;
    }

    public float getParkingSupport() {
        return parkingSupport;
    }

    public void setParkingSupport(float parkingSupport) {
        this.parkingSupport = parkingSupport;
    }
}

