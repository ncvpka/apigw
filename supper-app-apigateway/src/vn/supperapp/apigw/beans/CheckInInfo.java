package vn.supperapp.apigw.beans;

import vn.supperapp.apigw.db.dto.CheckInImages;

import java.util.Date;
import java.util.List;

public class CheckInInfo {
    private long id;
    private String msisdn;
    private String type;
    private Long referencesId;
    private Date checkInTime;
    private Date createTime;
    private Date updateTime;
    private String createBy;
    private String updateBy;
    private String address;
    private int countImages;
    private Double latitude;
    private Double longitude;

    private List<CheckInImages> urls;

    public CheckInInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<CheckInImages> getUrls() {
        return urls;
    }

    public void setUrls(List<CheckInImages> urls) {
        this.urls = urls;
    }

    public int getCountImages() {
        return countImages;
    }

    public void setCountImages(int countImages) {
        this.countImages = countImages;
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
