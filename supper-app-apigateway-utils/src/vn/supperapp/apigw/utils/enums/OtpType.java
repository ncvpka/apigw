/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.utils.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author truonglq
 */
public enum OtpType {
    NONE(0, "0", "NONE"),
    USSD(1, "1", "USSD"),
    SMS(2, "2", "SMS"),
    ;

    private final int value;
    private final String code;
    private final String description;

    private static final Map<Integer, OtpType> mValues = new HashMap<Integer, OtpType>();
    private static final Map<String, OtpType> mCodeValues = new HashMap<String, OtpType>();

    static {
        for (OtpType ccy : OtpType.values()) {
            mValues.put(ccy.value(), ccy);
            mCodeValues.put(ccy.code(), ccy);
        }
    }

    private OtpType(int value, String code, String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    public int value() {
        return this.value;
    }

    public String code() {
        return this.code;
    }

    public String description() {
        return this.description;
    }

    public boolean is(int value) {
        return this.value == value;
    }
    
    public boolean is(OtpType otpType) {
        return otpType != null && otpType.value == this.value;
    }

    public static OtpType get(int value) {
        return mValues.get(value);
    }

    public static OtpType get(String code) {
        return mCodeValues.get(code);
    }
    
}
