package vn.supperapp.apigw.restful.models.ChangePass;

import vn.supperapp.apigw.restful.models.BaseRequest;

public class ChangePassRequest extends BaseRequest {
    private String pinNew;

    public String getPassNew() {
        return pinNew;
    }

    public void setPassNew(String pinNew) {
        this.pinNew = pinNew;
    }
}
