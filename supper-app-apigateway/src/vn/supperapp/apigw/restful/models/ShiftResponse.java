package vn.supperapp.apigw.restful.models;


import vn.supperapp.apigw.beans.DayOffInfo;
import vn.supperapp.apigw.db.dto.DayOff;
import vn.supperapp.apigw.db.dto.DayOffType;
import vn.supperapp.apigw.db.dto.Shift;
import vn.supperapp.apigw.utils.ErrorCode;

import java.util.List;

public class ShiftResponse extends BaseResponse {

    List<Shift> list;
    private DayOffInfo dayOffInfo;
    public ShiftResponse() {
    }

    public ShiftResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public List<Shift> getList() {
        return list;
    }

    public void setList(List<Shift> list) {
        this.list = list;
    }

    public DayOffInfo getDayOffInfo() {
        return dayOffInfo;
    }

    public void setDayOffInfo(DayOffInfo dayOffInfo) {
        this.dayOffInfo = dayOffInfo;
    }
}
