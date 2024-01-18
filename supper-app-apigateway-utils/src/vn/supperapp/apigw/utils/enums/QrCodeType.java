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
public enum QrCodeType {
    UNKNOWN(-1, "UNKNOWN", "UNKNOWN"),
    CONSUMER(1, "CONSUMER", "CONSUMER"),
    MERCHANT(2, "MERCHANT", "MERCHANT"),
    ;

    private final int value;
    private final String code;
    private final String description;

    private static final Map<Integer, QrCodeType> mValues = new HashMap<Integer, QrCodeType>();
    private static final Map<String, QrCodeType> mCodeValues = new HashMap<String, QrCodeType>();

    static {
        for (QrCodeType ccy : QrCodeType.values()) {
            mValues.put(ccy.value(), ccy);
            mCodeValues.put(ccy.code(), ccy);
        }
    }

    private QrCodeType(int value, String code, String description) {
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

    public static QrCodeType get(int value) {
        return mValues.get(value);
    }

    public static QrCodeType get(String code) {
        return mCodeValues.get(code);
    }
    
}
