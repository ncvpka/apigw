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
public enum NewsType {
    UNKNOWN(-1, "UNKNOWN", "UNKNOWN"),
    NEWS(1, "NEWS", "NEWS"),
    PROMOTION(2, "PROMOTION", "PROMOTION"),

    ;

    private final int value;
    private final String code;
    private final String description;

    private static final Map<Integer, NewsType> mValues = new HashMap<Integer, NewsType>();
    private static final Map<String, NewsType> mCodeValues = new HashMap<String, NewsType>();

    static {
        for (NewsType ccy : NewsType.values()) {
            mValues.put(ccy.value(), ccy);
            mCodeValues.put(ccy.code(), ccy);
        }
    }

    private NewsType(int value, String code, String description) {
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

    public static NewsType get(int value) {
        return mValues.get(value);
    }

    public static NewsType get(String code) {
        return mCodeValues.get(code);
    }
    
}
