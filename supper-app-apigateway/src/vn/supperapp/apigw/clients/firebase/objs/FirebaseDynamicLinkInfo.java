package vn.supperapp.apigw.clients.firebase.objs;

import com.google.gson.Gson;
import vn.supperapp.apigw.utils.CommonUtils;

import java.util.HashMap;
import java.util.Map;

public class FirebaseDynamicLinkInfo {
    private DynamicLinkInfo dynamicLinkInfo;
    private Map<String, Object> suffix;

    public FirebaseDynamicLinkInfo(FirebaseDynamicLinkInfo.Builder builder) {
        this.dynamicLinkInfo = new DynamicLinkInfo(builder.domainUriPrefix, builder.link, builder.androidPackageName, builder.iosBundleId);
        if (builder.suffix != null) {
            this.suffix = new HashMap<>();
            this.suffix.put("option", builder.suffix.code());
        }
    }

    public static FirebaseDynamicLinkInfo.Builder builder() {
        return new FirebaseDynamicLinkInfo.Builder();
    }

    public static class DynamicLinkInfo {

        private String domainUriPrefix;
        private String link;
        private Map<String, Object> androidInfo = new HashMap<>();
        private Map<String, Object> iosInfo = new HashMap<>();

        public DynamicLinkInfo(String domainUriPrefix, String link, String androidPackageName, String iosBundleId) {
            this.domainUriPrefix = domainUriPrefix;
            this.link = link;
            if (!CommonUtils.isNullOrEmpty(androidPackageName)) {
                this.androidInfo.put("androidPackageName", androidPackageName);
            }
            if (!CommonUtils.isNullOrEmpty(iosBundleId)) {
                this.iosInfo.put("iosBundleId", iosBundleId);
            }
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

        public Map<String, Object> getAndroidInfo() {
            return androidInfo;
        }

        public void setAndroidInfo(Map<String, Object> androidInfo) {
            this.androidInfo = androidInfo;
        }

        public Map<String, Object> getIosInfo() {
            return iosInfo;
        }

        public void setIosInfo(Map<String, Object> iosInfo) {
            this.iosInfo = iosInfo;
        }
    }

    public static class Builder {
        private String domainUriPrefix;
        private String link;
        private String androidPackageName;
        private String iosBundleId;
        private FirebaseDynamicLinkSuffixType suffix;

        public FirebaseDynamicLinkInfo build() {
            return new FirebaseDynamicLinkInfo(this);
        }

        public String buildJson() {
            FirebaseDynamicLinkInfo m = new FirebaseDynamicLinkInfo(this);
            Gson g = new Gson();
            return g.toJson(m);
        }

        public FirebaseDynamicLinkInfo.Builder setDomainUriPrefix(String domainUriPrefix) {
            this.domainUriPrefix = domainUriPrefix;
            return this;
        }

        public FirebaseDynamicLinkInfo.Builder setLink(String link) {
            this.link = link;
            return this;
        }

        public FirebaseDynamicLinkInfo.Builder setAndroidPackageName(String androidPackageName) {
            this.androidPackageName = androidPackageName;
            return this;
        }

        public FirebaseDynamicLinkInfo.Builder setIosBundleId(String iosBundleId) {
            this.iosBundleId = iosBundleId;
            return this;
        }

        public FirebaseDynamicLinkInfo.Builder setSuffix(FirebaseDynamicLinkSuffixType suffixType) {
            this.suffix = suffixType;
            return this;
        }
    }
}
