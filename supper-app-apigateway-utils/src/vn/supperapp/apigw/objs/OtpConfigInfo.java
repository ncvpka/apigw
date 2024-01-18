package vn.supperapp.apigw.objs;

import java.util.List;
import java.util.Set;

public class OtpConfigInfo {
    private int expiredInSecond = 90;
    private int maxFailAllow = 5;
    private int maxResendAllow = 5;
    private Set<String> whitelistAccWithDefaultOtp;
    private String whitelistDefaultOtp;
    private String releaseMode;

    public int getExpiredInSecond() {
        return expiredInSecond;
    }

    public void setExpiredInSecond(int expiredInSecond) {
        this.expiredInSecond = expiredInSecond;
    }

    public int getMaxFailAllow() {
        return maxFailAllow;
    }

    public void setMaxFailAllow(int maxFailAllow) {
        this.maxFailAllow = maxFailAllow;
    }

    public int getMaxResendAllow() {
        return maxResendAllow;
    }

    public void setMaxResendAllow(int maxResendAllow) {
        this.maxResendAllow = maxResendAllow;
    }

    public Set<String> getWhitelistAccWithDefaultOtp() {
        return whitelistAccWithDefaultOtp;
    }

    public void setWhitelistAccWithDefaultOtp(Set<String> whitelistAccWithDefaultOtp) {
        this.whitelistAccWithDefaultOtp = whitelistAccWithDefaultOtp;
    }

    public String getWhitelistDefaultOtp() {
        return whitelistDefaultOtp;
    }

    public void setWhitelistDefaultOtp(String whitelistDefaultOtp) {
        this.whitelistDefaultOtp = whitelistDefaultOtp;
    }

    public String getReleaseMode() {
        return releaseMode;
    }

    public void setReleaseMode(String releaseMode) {
        this.releaseMode = releaseMode;
    }
}
