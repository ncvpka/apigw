package vn.supperapp.apigw.clients.firebase.objs;

import java.util.Map;

public class FirebaseHttpConfigInfo {

    private String url;

    private String domainUriPrefix;
    private String link;
    private String androidPackageName;
    private String iosBundleId;
    private String defaultReferralLink;

    private Map<String, Object> configurations;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDomainUriPrefix() {
        return domainUriPrefix;
    }

    public void setDomainUriPrefix(String domainUriPrefix) {
        this.domainUriPrefix = domainUriPrefix;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAndroidPackageName() {
        return androidPackageName;
    }

    public void setAndroidPackageName(String androidPackageName) {
        this.androidPackageName = androidPackageName;
    }

    public String getIosBundleId() {
        return iosBundleId;
    }

    public void setIosBundleId(String iosBundleId) {
        this.iosBundleId = iosBundleId;
    }

    public Map<String, Object> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(Map<String, Object> configurations) {
        this.configurations = configurations;
    }

    public String getDefaultReferralLink() {
        return defaultReferralLink;
    }

    public void setDefaultReferralLink(String defaultReferralLink) {
        this.defaultReferralLink = defaultReferralLink;
    }

}
