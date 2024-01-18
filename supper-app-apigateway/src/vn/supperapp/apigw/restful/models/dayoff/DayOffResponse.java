package vn.supperapp.apigw.restful.models.dayoff;


import vn.supperapp.apigw.beans.DayOffInfo;
import vn.supperapp.apigw.db.dto.DayOff;
import vn.supperapp.apigw.db.dto.DayOffType;
import vn.supperapp.apigw.db.dto.TimeKeeping;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

import java.util.List;

public class DayOffResponse extends BaseResponse {

    List<DayOffType> dayOffTypeList;
    List<DayOffInfo> dayOffs;
    private DayOff dayOff;
    private DayOffInfo dayOffInfo;
    public DayOffResponse() {
    }

    public DayOff getDayOff() {
        return dayOff;
    }

    public void setDayOff(DayOff dayOff) {
        this.dayOff = dayOff;
    }

    public DayOffResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public List<DayOffType> getDayOffTypeList() {
        return dayOffTypeList;
    }

    public void setDayOffTypeList(List<DayOffType> dayOffTypeList) {
        this.dayOffTypeList = dayOffTypeList;
    }


    public List<DayOffInfo> getDayOffs() {
        return dayOffs;
    }

    public void setDayOffs(List<DayOffInfo> dayOffs) {
        this.dayOffs = dayOffs;
    }

    public DayOffInfo getDayOffInfo() {
        return dayOffInfo;
    }

    public void setDayOffInfo(DayOffInfo dayOffInfo) {
        this.dayOffInfo = dayOffInfo;
    }
}
