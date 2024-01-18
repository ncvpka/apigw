package vn.supperapp.apigw.restful.models;

import vn.supperapp.apigw.beans.AccountInfo;
import vn.supperapp.apigw.beans.OrganizationInfo;
import vn.supperapp.apigw.beans.UserInfo;
import vn.supperapp.apigw.db.dto.AppDevice;
import vn.supperapp.apigw.utils.ErrorCode;

import java.util.List;

public class InitResponse extends BaseResponse {
    private String accessToken;
    private String rsaPrivateKey;
    private String rsaPublicKey;
    private String signature;

    public InitResponse() {
    }

    public InitResponse(ErrorCode error, String language) {
        super(error, language);
    }

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
