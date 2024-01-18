package vn.supperapp.apigw.beans;

import vn.supperapp.apigw.objs.OtpConfigInfo;
import vn.supperapp.apigw.objs.UploadConfigInfo;

import java.util.Map;
import java.util.Set;

public class AppConfigInfo {
    private String releaseMode;
    private String appPrivateKey;
    private String appPublicKey;
    private long loginTokenExpired = 0;
    private long loginTokenCachedExpired = 0;
    private Set<String> clientRestriction;
    private OtpConfigInfo otpConfigs;
    private String lottoPublicKey;
    private String lottoPrivateKey;
    private UploadConfigInfo uploadConfigs;

    private Map<String, Object> othersConfigs;

    public long getLoginTokenCachedExpired() {
        return loginTokenCachedExpired;
    }

    public void setLoginTokenCachedExpired(long loginTokenCachedExpired) {
        this.loginTokenCachedExpired = loginTokenCachedExpired;
    }

    public long getLoginTokenExpired() {
        return loginTokenExpired;
    }

    public void setLoginTokenExpired(long loginTokenExpired) {
        this.loginTokenExpired = loginTokenExpired;
    }

    public Map<String, Object> getOthersConfigs() {
        return othersConfigs;
    }

    public void setOthersConfigs(Map<String, Object> othersConfigs) {
        this.othersConfigs = othersConfigs;
    }

    public String getReleaseMode() {
        return releaseMode;
    }

    public void setReleaseMode(String releaseMode) {
        this.releaseMode = releaseMode;
    }

    public String getAppPrivateKey() {
        return appPrivateKey;
    }

    public void setAppPrivateKey(String appPrivateKey) {
        this.appPrivateKey = appPrivateKey;
    }

    public String getAppPublicKey() {
        return appPublicKey;
    }

    public void setAppPublicKey(String appPublicKey) {
        this.appPublicKey = appPublicKey;
    }

    public Set<String> getClientRestriction() {
        return clientRestriction;
    }

    public void setClientRestriction(Set<String> clientRestriction) {
        this.clientRestriction = clientRestriction;
    }

    public OtpConfigInfo getOtpConfigs() {
        return otpConfigs;
    }

    public void setOtpConfigs(OtpConfigInfo otpConfigs) {
        this.otpConfigs = otpConfigs;
    }

    public String getLottoPublicKey() {
        return lottoPublicKey;
    }

    public void setLottoPublicKey(String lottoPublicKey) {
        this.lottoPublicKey = lottoPublicKey;
    }

    public String getLottoPrivateKey() {
        return lottoPrivateKey;
    }

    public void setLottoPrivateKey(String lottoPrivateKey) {
        this.lottoPrivateKey = lottoPrivateKey;
    }

    public UploadConfigInfo getUploadConfigs() {
        return uploadConfigs;
    }

    public void setUploadConfigs(UploadConfigInfo uploadConfigs) {
        this.uploadConfigs = uploadConfigs;
    }
}
