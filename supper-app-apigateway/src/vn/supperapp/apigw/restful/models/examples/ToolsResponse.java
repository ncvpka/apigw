package vn.supperapp.apigw.restful.models.examples;

import vn.supperapp.apigw.restful.models.BaseResponse;

public class ToolsResponse extends BaseResponse {

    private String accessToken;
    private String signature;

    private String cryptData;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getCryptData() {
        return cryptData;
    }

    public void setCryptData(String cryptData) {
        this.cryptData = cryptData;
    }
}
