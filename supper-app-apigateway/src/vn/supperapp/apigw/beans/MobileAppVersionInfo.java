/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.beans;

import vn.supperapp.apigw.db.dto.AppConfig;
import vn.supperapp.apigw.utils.enums.Language;

/**
 *
 * @author TruongLe
 */
public class MobileAppVersionInfo {
    private int clientType;
    private String osName;
    private String appVersion;
    private String description;
    private String appStoreUrl;

    private int requireToUpgrade;
    private int showDialogUpgrade;

    public MobileAppVersionInfo() {
    }

    public MobileAppVersionInfo(int clientType, String osName, String language, AppConfig currentVersion) {
        this.clientType = clientType;
        this.osName = osName;
        this.appVersion = currentVersion.getConfigValue();
        if (Language.ENGLISH.is(language)) {
            this.description = currentVersion.getObjData3();
        } else if (Language.VIETNAMESE.is(language)) {
            this.description = currentVersion.getObjData4();
        } else {
            this.description = currentVersion.getObjData5();
        }
        this.appStoreUrl = currentVersion.getObjData1();
        if ("1".equals(currentVersion.getConfigName())) { //Required upgrade will be stored at CONFIG_NAME column
            this.requireToUpgrade = 1;
        } else {
            this.requireToUpgrade = 0;
        }
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppStoreUrl() {
        return appStoreUrl;
    }

    public void setAppStoreUrl(String appStoreUrl) {
        this.appStoreUrl = appStoreUrl;
    }

    public int getRequireToUpgrade() {
        return requireToUpgrade;
    }

    public void setRequireToUpgrade(int requireToUpgrade) {
        this.requireToUpgrade = requireToUpgrade;
    }

    public int getShowDialogUpgrade() {
        return showDialogUpgrade;
    }

    public void setShowDialogUpgrade(int showDialogUpgrade) {
        this.showDialogUpgrade = showDialogUpgrade;
    }
}
