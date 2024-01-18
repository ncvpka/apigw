package vn.supperapp.apigw.restful.models.timekeeping;


import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

import java.util.List;

public class TimeKeepingReportCMS extends BaseResponse {

    private List<vn.supperapp.apigw.beans.TimeKeepingReportCMSResponse> data;

    public List<vn.supperapp.apigw.beans.TimeKeepingReportCMSResponse> getData() {
        return data;
    }

    public void setData(List<vn.supperapp.apigw.beans.TimeKeepingReportCMSResponse> data) {
        this.data = data;
    }
    public TimeKeepingReportCMS(ErrorCode error, String language) {
        super(error, language);
    }
}
