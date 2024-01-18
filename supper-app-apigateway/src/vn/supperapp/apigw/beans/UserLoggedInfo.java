package vn.supperapp.apigw.beans;

import vn.supperapp.apigw.db.dto.AppDevice;
import vn.supperapp.apigw.db.dto.AppUser;

import java.util.List;

public class UserLoggedInfo {
    private String transId;
    private String accessToken;
    private String refreshToken;
    private UserInfo userInfo;
    private List<AccountInfo> accounts;
    private AppDevice appDevice;
    private AppUser appUser;
    private String pan;
    private long startTime = 0;
    private long updateTime = 0;

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
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

    public List<AccountInfo> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountInfo> accounts) {
        this.accounts = accounts;
    }

    public AppDevice getAppDevice() {
        return appDevice;
    }

    public void setAppDevice(AppDevice appDevice) {
        this.appDevice = appDevice;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }



}
