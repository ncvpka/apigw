package vn.supperapp.apigw.beans;

import vn.supperapp.apigw.db.dto.AppImage;
import vn.supperapp.apigw.db.dto.BlogTag;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public class DayOffInfo {
    private Long id;
    private Long userId;
    private String fullName;
    private Date dateStart;
    private Date dateEnd;
    private Long totalDay;
    private String reason;
    private Long status;
    private String type;


    public DayOffInfo(Long id, Long userId, String fullName, Date dateStart, Date dateEnd, Long totalDay, String reason, Long status, String type) {
        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.totalDay = totalDay;
        this.reason = reason;
        this.status = status;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
