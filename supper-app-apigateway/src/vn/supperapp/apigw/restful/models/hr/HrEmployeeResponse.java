package vn.supperapp.apigw.restful.models.hr;

import vn.supperapp.apigw.beans.HREmployeeInfoDetail;
import vn.supperapp.apigw.beans.PagingResult;
import vn.supperapp.apigw.db.dto.HrEmployee;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

import java.util.List;

public class HrEmployeeResponse extends BaseResponse {
    private PagingResult data;
    private List<HrEmployee> list;
    private HrEmployee employee;
    private HREmployeeInfoDetail detail;

    public HrEmployeeResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public List<HrEmployee> getList() {
        return list;
    }

    public void setList(List<HrEmployee> list) {
        this.list = list;
    }

    public HrEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(HrEmployee employee) {
        this.employee = employee;
    }

    public PagingResult getData() {
        return data;
    }

    public void setData(PagingResult data) {
        this.data = data;
    }

    public HREmployeeInfoDetail getDetail() {
        return detail;
    }

    public void setDetail(HREmployeeInfoDetail detail) {
        this.detail = detail;
    }
}
