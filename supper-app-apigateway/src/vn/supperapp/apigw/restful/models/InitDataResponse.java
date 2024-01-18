package vn.supperapp.apigw.restful.models;

import vn.supperapp.apigw.utils.ErrorCode;

public class InitDataResponse extends BaseResponse {
    private String rsaPrivateKey;
    private String rsaPublicKey;
    private String signatureKey;

    public InitDataResponse() {
    }

    public InitDataResponse(ErrorCode error, String language) {
        super(error, language);
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

    public String getSignatureKey() {
        return signatureKey;
    }

    public void setSignatureKey(String signatureKey) {
        this.signatureKey = signatureKey;
    }
}
