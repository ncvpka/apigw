package vn.supperapp.apigw.restful.models.hr;

import vn.supperapp.apigw.beans.DropDown;
import vn.supperapp.apigw.db.dto.HrDepartment;
import vn.supperapp.apigw.db.dto.HrParamValue;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

import java.util.List;

public class HrCommonResponse  extends BaseResponse {
    private List<HrParamValue> listParamValues;
    private HrParamValue paramValue;
    private List<HrDepartment> listDepartment;
    private HrDepartment department;
    private List<DropDown> dropdown;

    public List<DropDown> getDropdown() {
        return dropdown;
    }

    public void setDropdown(List<DropDown> dropdown) {
        this.dropdown = dropdown;
    }

    public HrCommonResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public List<HrParamValue> getListParamValues() {
        return listParamValues;
    }

    public void setListParamValues(List<HrParamValue> listParamValues) {
        this.listParamValues = listParamValues;
    }

    public HrParamValue getParamValue() {
        return paramValue;
    }

    public void setParamValue(HrParamValue paramValue) {
        this.paramValue = paramValue;
    }

    public List<HrDepartment> getListDepartment() {
        return listDepartment;
    }

    public void setListDepartment(List<HrDepartment> listDepartment) {
        this.listDepartment = listDepartment;
    }

    public HrDepartment getDepartment() {
        return department;
    }

    public void setDepartment(HrDepartment department) {
        this.department = department;
    }
}
