package vn.supperapp.apigw.restful.models.common;

import vn.supperapp.apigw.restful.models.BaseRequest;

public class FeatureInfoRequest extends BaseRequest {
    private String featureCode;
    private String partnerCode;
    private String serviceCode;
    private String type; // ALL

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}
}
