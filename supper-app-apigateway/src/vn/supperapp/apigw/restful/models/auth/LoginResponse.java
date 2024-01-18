package vn.supperapp.apigw.restful.models.auth;

import vn.supperapp.apigw.beans.AccountInfo;
import vn.supperapp.apigw.beans.OrganizationInfo;
import vn.supperapp.apigw.beans.UserInfo;
import vn.supperapp.apigw.db.dto.AppDevice;
import vn.supperapp.apigw.db.dto.Organization;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

import java.util.List;

public class LoginResponse extends BaseResponse {
    private String accessToken;
    private String refreshToken;
    private UserInfo userInfo;
    private List<AccountInfo> otherAccounts;
    private AppDevice device;
    private boolean showHideDeactiveDevice = false;
    private String url;
    List<OrganizationInfo> listOrg;
    private String cryptData;

    public LoginResponse() {
    }

    public LoginResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<AccountInfo> getOtherAccounts() {
        return otherAccounts;
    }

    public void setOtherAccounts(List<AccountInfo> otherAccounts) {
        this.otherAccounts = otherAccounts;
    }

    public AppDevice getDevice() {
        return device;
    }

    public void setDevice(AppDevice device) {
        this.device = device;
    }

    public boolean isShowHideDeactiveDevice() {
        return showHideDeactiveDevice;
    }

    public void setShowHideDeactiveDevice(boolean showHideDeactiveDevice) {
        this.showHideDeactiveDevice = showHideDeactiveDevice;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<OrganizationInfo> getListOrg() {
        return listOrg;
    }

    public void setListOrg(List<OrganizationInfo> listOrg) {
        this.listOrg = listOrg;
    }

    public String getCryptData() {
        return cryptData;
    }

    public void setCryptData(String cryptData) {
        this.cryptData = cryptData;
    }
}
