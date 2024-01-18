package vn.supperapp.apigw.restful.models.hr;

import vn.supperapp.apigw.beans.HREmployeeEducationInfo;
import vn.supperapp.apigw.beans.HREmployeeInfoDetail;
import vn.supperapp.apigw.beans.PagingResult;
import vn.supperapp.apigw.db.dto.HrEmployee;
import vn.supperapp.apigw.db.dto.HrEmployeeEducation;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

import java.util.List;

public class HrEmployeeEducationResponse extends BaseResponse {

    private HrEmployeeEducation info;
    private List<HREmployeeEducationInfo> list;

    public HrEmployeeEducationResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public HrEmployeeEducation getInfo() {
        return info;
    }

    public void setInfo(HrEmployeeEducation info) {
        this.info = info;
    }

    public List<HREmployeeEducationInfo> getList() {
        return list;
    }

    public void setList(List<HREmployeeEducationInfo> list) {
        this.list = list;
    }
}
