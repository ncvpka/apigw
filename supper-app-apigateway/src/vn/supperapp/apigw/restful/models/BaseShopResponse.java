/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.restful.models;


import com.google.gson.Gson;
import com.viettel.ewallet.utils.iso.msg.IsoObject;
import org.jpos.iso.ISOException;
import org.openide.util.Exceptions;
import vn.supperapp.apigw.services.OtpService;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.ErrorCode;
import vn.supperapp.apigw.utils.LanguageUtils;
import vn.supperapp.apigw.utils.enums.CurrencyCode;

import java.util.List;

/**
 * @author Taink
 */
public class BaseShopResponse {
    private int status;
    private String code;
    private String message;

    public BaseShopResponse() {
    }

    public BaseShopResponse(int status, String code) {
        this.status = status;
        this.code = code;
        this.message = "";
    }

    public BaseShopResponse(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public BaseShopResponse(ErrorCode error, String language) {
        this.status = error.status();
        this.code = error.code();
        this.message = LanguageUtils.getString(this.code, language);
        if (CommonUtils.isNullOrEmpty(this.message)) {
            this.message = error.message();
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static BaseShopResponse success(String language) {
        return new BaseShopResponse(ErrorCode.SUCCESS, language);
    }

    public static BaseShopResponse commonError(String language) {
        return new BaseShopResponse(ErrorCode.ERR_COMMON, language);
    }

    public static BaseShopResponse missingParameters(String language) {
        return new BaseShopResponse(ErrorCode.ERR_MISSING_PARAMETERS, language);
    }

    public static BaseShopResponse build(ErrorCode error, String language) {
        return new BaseShopResponse(error, language);
    }

    public void setErrorCode(ErrorCode errorCode, String language) {
        this.status = errorCode.status();
        this.code = errorCode.code();
        this.message = LanguageUtils.getString(this.code, language);
    }

    public static BaseShopResponse build(String responseCode, String language) {
        BaseShopResponse response = new BaseShopResponse();
        String msg = LanguageUtils.getString(responseCode, language);
        if (CommonUtils.isNullOrEmpty(msg)) {
            response.setErrorCode(ErrorCode.ERR_COMMON, language);
        } else {
            response.setStatus(Integer.valueOf(responseCode));
            response.setCode(responseCode);
            response.setMessage(msg);
        }
        return response;
    }

    public static BaseShopResponse buildError(IsoObject isoResponse, String language) throws ISOException {

        String responseCode = isoResponse.getResponseCode();
        String description = isoResponse.getResponseDescription();

        BaseShopResponse response = new BaseShopResponse();
        String msg = LanguageUtils.getString(responseCode, language);

        if (!CommonUtils.isNullOrEmpty(description)) {
            response.setStatus(Integer.valueOf(responseCode));
            response.setCode(responseCode);
            response.setMessage(description);
            return response;
        } else if (!CommonUtils.isNullOrEmpty(msg)) {
            response.setStatus(Integer.valueOf(responseCode));
            response.setCode(responseCode);
            response.setMessage(msg);
            return response;
        } else {
            response.setErrorCode(ErrorCode.ERR_COMMON, language);
        }
        return response;
    }


    public String toLogString() {
        try {
            Gson gson = new Gson();
            String tmp = gson.toJson(this);
            if (tmp != null) {
                tmp = tmp.replaceAll("(\"pin\":\"[0-9]{6}\")", "\"pin\":\"******\"");
            }
            return tmp;
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

        return "";
    }
}
