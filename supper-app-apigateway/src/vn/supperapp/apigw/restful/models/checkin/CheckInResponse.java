package vn.supperapp.apigw.restful.models.checkin;


import vn.supperapp.apigw.beans.CheckInInfo;
import vn.supperapp.apigw.db.dto.CheckInImages;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

import java.util.List;

public class CheckInResponse extends BaseResponse {
    private CheckInImages checkInImages;
    private List<CheckInInfo> checkIns;
    private CheckInInfo checkInInfo;

    public CheckInResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public CheckInImages getCheckInImages() {
        return checkInImages;
    }

    public void setCheckInImages(CheckInImages checkInImages) {
        this.checkInImages = checkInImages;
    }

    public List<CheckInInfo> getCheckIns() {
        return checkIns;
    }

    public void setCheckIns(List<CheckInInfo> checkIns) {
        this.checkIns = checkIns;
    }

    public CheckInInfo getCheckInInfo() {
        return checkInInfo;
    }

    public void setCheckInInfo(CheckInInfo checkInInfo) {
        this.checkInInfo = checkInInfo;
    }
}
