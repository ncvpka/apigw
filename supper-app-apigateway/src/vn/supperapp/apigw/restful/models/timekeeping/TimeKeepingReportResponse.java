package vn.supperapp.apigw.restful.models.timekeeping;


import vn.supperapp.apigw.beans.TimeKeepingInfo;
import vn.supperapp.apigw.db.dto.TimeKeeping;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

import java.util.List;

public class TimeKeepingReportResponse extends BaseResponse {

    private String title;
    private double lateEarlyDays;
    private double totalWorkingDays;
    private double remainingLeaveDays;
    private double salary;
    private double bonus;
    private List<TimeKeepingInfo> details;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLateEarlyDays() {
        return lateEarlyDays;
    }

    public void setLateEarlyDays(double lateEarlyDays) {
        this.lateEarlyDays = lateEarlyDays;
    }

    public double getTotalWorkingDays() {
        return totalWorkingDays;
    }

    public void setTotalWorkingDays(double totalWorkingDays) {
        this.totalWorkingDays = totalWorkingDays;
    }

    public double getRemainingLeaveDays() {
        return remainingLeaveDays;
    }

    public void setRemainingLeaveDays(double remainingLeaveDays) {
        this.remainingLeaveDays = remainingLeaveDays;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public List<TimeKeepingInfo> getDetails() {
        return details;
    }

    public void setDetails(List<TimeKeepingInfo> details) {
        this.details = details;
    }

    public TimeKeepingReportResponse(ErrorCode error, String language) {
        super(error, language);
    }

}
