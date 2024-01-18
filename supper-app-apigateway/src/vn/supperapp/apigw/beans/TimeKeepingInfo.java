package vn.supperapp.apigw.beans;

import vn.supperapp.apigw.db.dto.CheckInImages;

import javax.persistence.Transient;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TimeKeepingInfo {
    private SimpleDateFormat smDate = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat smHour = new SimpleDateFormat("hh:mm");

    private Long id;
    private String shiftName;
    private String fullName;
    private Long status;
    private Date dateWork;
    private Date timeStart;
    private Date timeEnd;
    private Double workDay;
    private Long overTime;
    private String note;

    private String strDateWork;

    private String strTimeStart;

    private String strTimeEnd;

    public TimeKeepingInfo() {
    }

    public TimeKeepingInfo(Long id, String shiftName, String fullName, Long status, Date dateWork, Date timeStart, Date timeEnd, Double workDay, Long overTime, String note) {
        this.id = id;
        this.shiftName = shiftName;
        this.fullName = fullName;
        this.status = status;
        this.dateWork = dateWork;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.workDay = workDay;
        this.overTime = overTime;
        this.note = note;
        this.strDateWork = dateWork == null ? null : smDate.format(dateWork);
        this.strTimeStart = timeStart == null ? null : smHour.format(timeStart);
        this.strTimeEnd = timeEnd == null ? null : smHour.format(timeEnd);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
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

    public Double getWorkDay() {
        return workDay;
    }

    public void setWorkDay(Double workDay) {
        this.workDay = workDay;
    }

    public Long getOverTime() {
        return overTime;
    }

    public void setOverTime(Long overTime) {
        this.overTime = overTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
//
//    public String getStrDateWork() {
//        strDateWork = dateWork == null ? null : smDate.format(dateWork);
//        return strDateWork;
//    }
//
//    public void setStrDateWork(String strDateWork) {
//        this.strDateWork = strDateWork;
//    }
//
//    public String getStrTimeStart() {
//        strTimeStart = timeStart == null ? null : smHour.format(timeStart);
//        return strTimeStart;
//    }
//
//    public void setStrTimeStart(String strTimeStart) {
//        this.strTimeStart = strTimeStart;
//    }
//
//    public String getStrTimeEnd() {
//        strTimeEnd = timeEnd == null ? null : smHour.format(timeEnd);
//        return strTimeEnd;
//    }
//
//    public void setStrTimeEnd(String strTimeEnd) {
//        this.strTimeEnd = strTimeEnd;
//    }


    public String getStrDateWork() {
        return strDateWork;
    }

    public void setStrDateWork(String strDateWork) {
        this.strDateWork = strDateWork;
    }

    public String getStrTimeStart() {
        return strTimeStart;
    }

    public void setStrTimeStart(String strTimeStart) {
        this.strTimeStart = strTimeStart;
    }

    public String getStrTimeEnd() {
        return strTimeEnd;
    }

    public void setStrTimeEnd(String strTimeEnd) {
        this.strTimeEnd = strTimeEnd;
    }
}
