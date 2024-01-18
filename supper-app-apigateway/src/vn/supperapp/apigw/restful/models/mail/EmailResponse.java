package vn.supperapp.apigw.restful.models.mail;


import vn.supperapp.apigw.beans.PersonalScheduleInfo;
import vn.supperapp.apigw.db.dto.PersonalSchedule;
import vn.supperapp.apigw.db.dto.ScheduleConfig;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

import java.util.List;

public class EmailResponse extends BaseResponse {

    List<ScheduleConfig> remind;
    List<ScheduleConfig> repeat;
    List<PersonalScheduleInfo> personalScheduleList;
    private PersonalSchedule schedule;
    private PersonalScheduleInfo personalSchedule;
    public EmailResponse() {
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

    public EmailResponse(ErrorCode error, String language) {
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
