package vn.supperapp.apigw.restful.models.auth;

import vn.supperapp.apigw.restful.models.BaseRequest;

public class LoginRequest extends BaseRequest {
    private String deviceId;
    private String buildKey;
    private String accountNumber;
    private Long orgId;
    private Long branchId;
    private int deactiveOthersDevice = 0;
    private String firebaseToken;
    private String rsaPrivateKey;
    private String rsaPublicKey;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public int getDeactiveOthersDevice() {
        return deactiveOthersDevice;
    }

    public void setDeactiveOthersDevice(int deactiveOthersDevice) {
        this.deactiveOthersDevice = deactiveOthersDevice;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

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

    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }

    public String getRsaPublicKey() {
        return rsaPublicKey;
    }

    public void setRsaPublicKey(String rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
    }
}
