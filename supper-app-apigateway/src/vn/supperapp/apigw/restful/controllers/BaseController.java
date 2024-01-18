/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.restful.controllers;

import com.google.gson.Gson;
import com.viettel.ewallet.pin.PinUtils;
import com.viettel.ewallet.utils.Utils;
import com.viettel.ewallet.utils.iso.msg.IsoObject;
import vn.supperapp.apigw.beans.UserInfo;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.beans.V_AccountStatusBean;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.BaseDAO;
import vn.supperapp.apigw.db.dao.UserDAO;
import vn.supperapp.apigw.db.dto.AppDevice;
import vn.supperapp.apigw.db.dto.AppUser;
import vn.supperapp.apigw.restful.models.FieldInformation;
import vn.supperapp.apigw.utils.Constants;
import vn.supperapp.apigw.utils.RedisUtils;
import vn.supperapp.apigw.redis.RedisService;
import vn.supperapp.apigw.services.OtpService;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.CryptUtils;
import vn.supperapp.apigw.utils.LanguageUtils;
import vn.supperapp.apigw.utils.enums.CurrencyCode;
import vn.supperapp.apigw.utils.enums.TransactionType;
import org.hibernate.Session;
import org.jpos.iso.ISOException;
import org.slf4j.Logger;
import redis.clients.jedis.JedisCluster;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author TruongLev
 */
public class BaseController {

    protected Logger logger;

    @HeaderParam("x-device-id")
    protected String deviceId;

    @HeaderParam("x-app-version")
    protected String appVersion;

    protected String language;

    private UserLoggedInfo userLoggedInfo;
    private AppDevice appDevice;

    @Context
    protected ContainerRequestContext context;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    @HeaderParam("language")
    public void setLanguage(String language) {
        if (CommonUtils.isNullOrEmpty(language)) {
            this.language = "ht";
        } else {
            this.language = language;
        }
    }

    public String getLanguage() {
        return language;
    }

    public UserLoggedInfo getUserLoggedInfo() {
        if (userLoggedInfo == null) {
            userLoggedInfo = (UserLoggedInfo) context.getProperty(Constants.USER_LOGGED_INFO_KEY);
        }
        return userLoggedInfo;
    }

    public AppDevice getAppDevice() {
        if (appDevice == null) {
            appDevice = (AppDevice) context.getProperty(Constants.USER_DEVICE_INFO_KEY);
        }
        return appDevice;
    }

    protected void logApiInfo(String method, String api, Object request) {
        if (logger != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("\n############################################################################################");
            sb.append("\n# method: " + method);
            sb.append("\n# api: " + api);
            if (request != null) {
                sb.append("\n# request: " + toJsonString(request));
            }
            sb.append("\n############################################################################################");
            logger.info(sb.toString());
        }
    }

    protected void logApiInfo(String method, String api, Object... request) {
        if (logger != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("\n--------------------------------------------------------------------------------\n");
            sb.append("\n- method: " + method);
            sb.append("\n- api: " + api);
            if (request != null) {
                sb.append("\n- request: " + getParamLogItem(request));
            }
            sb.append("\n--------------------------------------------------------------------------------\n");
            logger.info(sb.toString());
        }
    }

    protected void logExceptions(String message, Exception ex) {
        if (ex != null) {
            ex.printStackTrace();
        }
        logger.error(message, ex);
    }

    protected String getParamLogItem(Object... params) {
        if (params == null || params.length == 0) {
            return "";
        }
        StringBuilder sbLogs = new StringBuilder();
        for (int i = 0; i < params.length; i += 2) {
            sbLogs.append("\n").append(params[i]).append(": ");
            if (i + 1 < params.length) {
                sbLogs.append(String.valueOf(params[i + 1]));
            }
        }
        return sbLogs.toString();
    }

    protected String toJsonString(Object obj) {
        try {
            Gson gson = new Gson();
            String req = gson.toJson(obj);
            if (req != null) {
                req = req.replaceAll("(\"pin\":\"[0-9]{6}\")", "\"pin\":\"******\"");
            }
            return req;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    protected boolean checkDbSession(Session session) {
        return BaseDAO.checkSession(session);
    }

    public String convertNumber(Double number) {
        logger.info("#convertNumber - START");
        try {
            DecimalFormat formatter = new DecimalFormat("###,###,###.##");
            return formatter.format(number);
        } catch (Exception e) {
            logger.info("#convertNumber - ERROR", e);
            e.printStackTrace();
        }
        return number.toString();
    }

    protected static IsoObject initIsoObject2CoreApp() throws ISOException {
        IsoObject isoObj = new IsoObject();
        isoObj.setMTI(com.viettel.ewallet.utils.config.Constants.TRANSACTION_REQUEST_MTI);
        isoObj.setActionNode(com.viettel.ewallet.utils.config.Constants.ACTION_NODE_BUR_STR.MOBILE_APP);
        isoObj.setTransmissionDateTime(Utils.getCurrentDateFullString());
        isoObj.setCurrencyCode(CurrencyCode.VND.number());
        isoObj.setRequestId(CommonUtils.generateUUID());

        try {
            InetAddress IP = InetAddress.getLocalHost();
            isoObj.setClientIP(IP.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return isoObj;
    }




    public boolean verifySignature(String signature, Object... data) {
        try {
            StringBuilder sb = new StringBuilder();
            for (Object it : data) {
                if (it != null) {
                    if (it instanceof Double) {
                        sb.append(String.format("%.2f", (Double) it));
                    } else {
                        sb.append(it.toString());
                    }
                }
            }
            AppDevice device = getAppDevice();
            String tmp = CryptUtils.hmac256Encrypt(sb.toString(), device.getSignatureKey());
            if (CommonUtils.isNullOrEmpty(tmp)) {
                return false;
            }

            return tmp.equals(signature);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    protected IsoObject createIso2CheckSub(String msisdn) {
        IsoObject iso = null;
        try {
            iso = initIsoObject2CoreApp();
            iso.setRequestId(CommonUtils.generateUUID());
            iso.setProcessCode("311100");
            iso.setPhoneNumber(msisdn);
            iso.setCarriedPhone(msisdn);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#createIso2CheckSub - EXCEPTION: ", ex);
        }
        return iso;
    }

//    public void sendSms(String msisdn, String message, String refId) {
//        try {
//            StringBuilder logStr = new StringBuilder();
//            logStr.append("\n=======================================================================");
//            logStr.append("\n msisdn: ").append(msisdn); o//            logStr.append("\n message: ").append(message);
//            logStr.append("\n=======================================================================");
//            logger.info(logStr.toString());
//            if (CommonUtils.isNullOrEmpty(msisdn) || CommonUtils.isNullOrEmpty(message)) {
//                return;
//            }
//
//            if (CommonUtils.isNullOrEmpty(refId)) {
//                refId = "MOBILEGATEV2";
//            }
//            MfSmscgwClient.shared().sendSmsAsyncTask(msisdn, message, refId, this.getLanguage());
//        } catch (Exception ex) {
//            logger.error("#sendSms - ERROR", ex);
//            Exceptions.printStackTrace(ex);
//        }
//    }

    public void setOtpTransaction(OtpService otp) {
        try {
            if (otp == null || CommonUtils.isNullOrEmpty(otp.getTransId())) {
                logger.info("#setOtpTransaction - Otp is null or TransId invalid");
                return;
            }

            String cachedKey = String.format("TX_%s", otp.getTransId());
            RedisService.shared().set(Constants.REDIS_DB_OTP_TRANS_CACHED, cachedKey, otp, 10 * 60);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#setOtpTransaction - EXCEPTION: ", ex);
        }
    }

    public OtpService getOtpTransaction(String transId) {
        try {
            String cachedKey = String.format("TX_%s", transId);
            OtpService otp = RedisService.shared().get(OtpService.class, Constants.REDIS_DB_OTP_TRANS_CACHED, cachedKey);
            if (otp == null) {
                logger.info("#getOtpTransaction - OTP is null or expired");
            }
            return otp;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#getOtpTransaction - EXCEPTION: ", ex);
        }
        return null;
    }

    public void removeOtpTransaction(String transId) {
        try {
            String cachedKey = String.format("TX_%s", transId);
            RedisService.shared().remove(Constants.REDIS_DB_OTP_TRANS_CACHED, cachedKey);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#removeOtpTransaction - EXCEPTION: ", ex);
        }
    }

    public FieldInformation createFieldInformation(String keyName, String language, Map<String, String> mapField) {
        FieldInformation transactionDetail = new FieldInformation();
        transactionDetail.setNameTitle(LanguageUtils.getString(keyName, language));
        transactionDetail.setListField(mapField);
        return transactionDetail;
    }


    /**
     * @param msisdn
     * @return
     */
    protected V_AccountStatusBean getDataFromRedis(String msisdn) {
        JedisCluster jedis = null;
        String response = "";
        V_AccountStatusBean v_accountStatusBean = null;
        try {
            jedis = RedisUtils.getInstance().createJedis();
            response = jedis.get(msisdn);
            logger.info(response);
            if (response != null && !"".equals(response)) {
                v_accountStatusBean = convertStringJsonToObject(response);
                if (v_accountStatusBean != null) {
                    logger.info("getDataFromRedis: " + msisdn);
                } else {
                    logger.info("getDataFromRedis is null send 311100 ");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return v_accountStatusBean;
    }

    protected String getAccountIdFromRedis(String msisdn) {
        try {
            V_AccountStatusBean v_accountStatusBean = getDataFromRedis(msisdn);
            if (v_accountStatusBean == null || v_accountStatusBean.getAccountStateId() == com.viettel.ewallet.utils.config.Constants.ACCOUNT_STATE_ID.CANCELED
                    || v_accountStatusBean.getAccountStateId() == com.viettel.ewallet.utils.config.Constants.ACCOUNT_STATE_ID.BLOCKED
                    || v_accountStatusBean.getAccountStateId() == com.viettel.ewallet.utils.config.Constants.ACCOUNT_STATE_ID.INVALID_PIN
            ) {
                // khong co trong redis thi goi ban tin binh thuong
                logger.info("#login - Get user info");
                Session session = null;
                logger.info("#user Open db app session");
                session = DbSessionMgt.shared().getSession();
                AppUser user = new UserDAO().getAppUserByMsisdn(session, msisdn);
                if (user != null) {
                    return user.getId().toString();
                }
            } else {
                return String.valueOf(v_accountStatusBean.getAccountId());
            }
        } catch (Exception exception) {
            logger.error("Error:", exception);
        }
        return null;
    }

    /**
     * @param objString
     * @return
     */
    private static V_AccountStatusBean convertStringJsonToObject(String objString) {
        Gson gson = new Gson();
        return gson.fromJson(objString, V_AccountStatusBean.class);

    }

    public static String getClientIPAddress() {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        return  addr.getHostAddress();
                    }
                }
            }
        } catch (Exception ex) { } // for now eat exceptions
        return "";
    }
}
