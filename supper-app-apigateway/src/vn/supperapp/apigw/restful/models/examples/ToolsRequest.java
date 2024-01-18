package vn.supperapp.apigw.restful.models.examples;

import vn.supperapp.apigw.restful.models.BaseRequest;

public class ToolsRequest extends BaseRequest {
    private String deviceId;
    private String buildKey;
    private String rsaPublicKey;
    private String rsaPrivateKey;

    private String signatureKey;
    private String signatureData;

    private String cryptData;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getBuildKey() {
        return buildKey;
    }

    public void setBuildKey(String buildKey) {
        this.buildKey = buildKey;
    }

    public String getRsaPublicKey() {
        return rsaPublicKey;
    }

    public void setRsaPublicKey(String rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
    }

    public String getSignatureKey() {
        return signatureKey;
    }

    public void setSignatureKey(String signatureKey) {
        this.signatureKey = signatureKey;
    }

    public String getSignatureData() {
        return signatureData;
    }

    public void setSignatureData(String signatureData) {
        this.signatureData = signatureData;
    }

    public String getCryptData() {
        return cryptData;
    }

    public void setCryptData(String cryptData) {
        this.cryptData = cryptData;
    }

    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }
}
