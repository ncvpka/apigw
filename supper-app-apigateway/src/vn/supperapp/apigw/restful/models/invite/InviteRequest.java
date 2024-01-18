package vn.supperapp.apigw.restful.models.invite;

import vn.supperapp.apigw.restful.models.BaseRequest;

public class InviteRequest extends BaseRequest {
    private String msisdnArr;
    private String type;

    public String getMsisdnArr() {
        return msisdnArr;
    }

    public void setMsisdnArr(String msisdnArr) {
        this.msisdnArr = msisdnArr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
