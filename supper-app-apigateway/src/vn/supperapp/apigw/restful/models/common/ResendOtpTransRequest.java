package vn.supperapp.apigw.restful.models.common;

import vn.supperapp.apigw.restful.models.BaseRequest;

public class ResendOtpTransRequest extends BaseRequest {
    private String featureCode;

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }
}
