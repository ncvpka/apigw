package vn.supperapp.apigw.restful.models.schedule;


import vn.supperapp.apigw.beans.PersonalScheduleInfo;
import vn.supperapp.apigw.db.dto.PersonalSchedule;
import vn.supperapp.apigw.db.dto.ScheduleConfig;
import vn.supperapp.apigw.db.dto.TimeKeeping;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;
import vn.supperapp.apigw.utils.Gender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonalScheduleResponse extends BaseResponse {

    List<ScheduleConfig> remind;
    List<ScheduleConfig> repeat;
    List<PersonalScheduleInfo> personalScheduleList;
    private PersonalSchedule schedule;
    private PersonalScheduleInfo personalSchedule;
    public PersonalScheduleResponse() {
    }


    public List<PersonalScheduleInfo> getPersonalScheduleList() {
        return personalScheduleList;
    }

    public void setPersonalScheduleList(List<PersonalScheduleInfo> personalScheduleList) {
        this.personalScheduleList = personalScheduleList;
    }

    public PersonalScheduleInfo getPersonalSchedule() {
        return personalSchedule;
    }

    public void setPersonalSchedule(PersonalScheduleInfo personalSchedule) {
        this.personalSchedule = personalSchedule;
    }

    public PersonalScheduleResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public List<ScheduleConfig> getRemind() {
        return remind;
    }

    public void setRemind(List<ScheduleConfig> remind) {
        this.remind = remind;
    }

    public List<ScheduleConfig> getRepeat() {
        return repeat;
    }

    public void setRepeat(List<ScheduleConfig> repeat) {
        this.repeat = repeat;
    }

    public PersonalSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(PersonalSchedule schedule) {
        this.schedule = schedule;
    }
}
