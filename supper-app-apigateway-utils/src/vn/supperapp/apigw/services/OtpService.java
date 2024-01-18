package vn.supperapp.apigw.services;

import com.google.gson.Gson;
import com.viettel.ewallet.utils.iso.msg.IsoObject;
import vn.supperapp.apigw.objs.OtpConfigInfo;
import vn.supperapp.apigw.utils.CommonUtils;
import org.jpos.iso.ISOException;
import org.openide.util.Exceptions;

import java.util.Map;

public class OtpService {
    public static final long DEFAULT_EXPIRED_TIME = 60;
    private boolean isProduction = false;
    private String transId;
    private String pin;
    private String otp;
    private String data;
    private String isoData;
    private String isoFeeData;
    private int failCounter = 0;
    private long startTime = 0;
    private long expiredInSecond = DEFAULT_EXPIRED_TIME;
    private String otpMessage;

    private OtpConfigInfo configs;
    private String msisdn;

//    public OtpService(boolean isProduction) {
//        this.isProduction = isProduction;
//        if (this.isProduction) {
//            this.otp = CommonUtils.generateNumberRandom(6);
//        } else {
//            this.otp = "123456";
//        }
//        this.expiredInSecond = DEFAULT_EXPIRED_TIME;
//    }

    public OtpService(String msisdn, OtpConfigInfo configs) {
        this.isProduction = "production".equals(configs.getReleaseMode());
        this.msisdn = msisdn;
        this.expiredInSecond = configs.getExpiredInSecond();
        this.configs = configs;
        if (this.isProduction) {
            if (msisdn != null && configs.getWhitelistAccWithDefaultOtp().contains(msisdn)) {
                this.otp = configs.getWhitelistDefaultOtp();
            } else {
                this.otp = CommonUtils.generateNumberRandom(6);
            }
        } else {
            this.otp = "123456";
        }
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getOtpMessage() {
        return otpMessage;
    }

    public void setOtpMessage(String otpMessage) {
        this.otpMessage = otpMessage;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        try {
            Gson gson = new Gson();
            this.data = gson.toJson(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public IsoObject getIso(String data) {
        try {
            Gson gson = new Gson();
            Map<String, Object> rs = gson.fromJson(data, Map.class);
            if (rs == null || rs.isEmpty()) {
                return null;
            }

            Map<String, Object> fields = (Map<String, Object>) rs.get("fields");
            if (fields == null || fields.isEmpty()) {
                return null;
            }

            IsoObject iso = new IsoObject();
            for (Map.Entry<String, Object> entry : fields.entrySet()) {
                String key = entry.getKey();
                Map<String, String> value = (Map<String, String>) entry.getValue();
                try {
                    iso.set(Integer.valueOf(key), value.get("value"));
                } catch (ISOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
            return iso;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public IsoObject getIsoData() {
        return getIso(this.isoData);
    }

    public void setIsoData(IsoObject isoData) {
        try {
            if (isoData == null) {
                return;
            }

            Gson gson = new Gson();
            this.isoData = gson.toJson(isoData);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setIsoFeeData(IsoObject isoFeeData) {
        try {
            if (isoFeeData == null) {
                return;
            }

            Gson gson = new Gson();
            this.isoFeeData = gson.toJson(isoFeeData);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public IsoObject getIsoFeeData() {
        return getIso(this.isoFeeData);
    }

    public int getFailCounter() {
        return failCounter;
    }

    public void setFailCounter(int failCounter) {
        this.failCounter = failCounter;
    }

    public <T> T getData(Class<T> clazz) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(this.data, clazz);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean verifyOtp(String otp) {
        boolean result = false;
        try {
            result = !CommonUtils.isNullOrEmpty(this.otp) && this.otp.equals(otp);
            Long t = System.currentTimeMillis();
            long check = t - this.startTime;
            result = result & (check < this.expiredInSecond * 1000);

            //TODO: Verify more fail count here
//            result = result & (failCounter < configs.getMaxFailAllow());

            //TODO: Lock OTP in case success
            if (result) {
                this.otp = CommonUtils.generateNumberRandom(6);
                if (this.msisdn != null && this.configs.getWhitelistAccWithDefaultOtp().contains(this.msisdn)) {
                    this.otp = this.configs.getWhitelistDefaultOtp();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public void startCountdown(boolean isResend) {
        if (isResend) {
            String tmpOtp = "654321";
            if (this.msisdn != null && this.configs.getWhitelistAccWithDefaultOtp().contains(this.msisdn)) {
                tmpOtp = this.configs.getWhitelistDefaultOtp();
            } else if (this.isProduction) {
                tmpOtp = CommonUtils.generateNumberRandom(6);
            }
            this.otp = tmpOtp;
        }
        this.startTime = System.currentTimeMillis();
    }
}
