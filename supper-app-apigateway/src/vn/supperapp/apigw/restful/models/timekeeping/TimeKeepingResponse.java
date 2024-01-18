package vn.supperapp.apigw.restful.models.timekeeping;


import vn.supperapp.apigw.beans.TimeKeepingInfo;
import vn.supperapp.apigw.db.dto.DayOffType;
import vn.supperapp.apigw.db.dto.TimeKeeping;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

import java.sql.Time;
import java.util.List;

public class TimeKeepingResponse extends BaseResponse {


    List<TimeKeepingInfo> timeKeepingList;
    private TimeKeepingInfo timeKeepingInfo;
    private TimeKeeping timeKeeping;
    public TimeKeepingResponse() {
    }

    public List<TimeKeepingInfo> getTimeKeepingList() {
        return timeKeepingList;
    }

    public void setTimeKeepingList(List<TimeKeepingInfo> timeKeepingList) {
        this.timeKeepingList = timeKeepingList;
    }

    public TimeKeepingInfo getTimeKeepingInfo() {
        return timeKeepingInfo;
    }

    public void setTimeKeepingInfo(TimeKeepingInfo timeKeepingInfo) {
        this.timeKeepingInfo = timeKeepingInfo;
    }

    public TimeKeeping getTimeKeeping() {
        return timeKeeping;
    }

    public void setTimeKeeping(TimeKeeping timeKeeping) {
        this.timeKeeping = timeKeeping;
    }

    public TimeKeepingResponse(ErrorCode error, String language) {
        super(error, language);
    }

}
