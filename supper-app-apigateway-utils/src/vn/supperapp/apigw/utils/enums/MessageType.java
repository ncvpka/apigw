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
public enum MessageType {
    UNKNOWN(-1, "UNKNOWN", "UNKNOWN"),
    INFO(1, "INFO", "INFO"),
    WARNING(2, "WARNING", "WARNING"),
    ALERT(3, "ALERT", "ALERT"),
    ;

    private final int value;
    private final String code;
    private final String description;

    private static final Map<Integer, MessageType> mValues = new HashMap<Integer, MessageType>();
    private static final Map<String, MessageType> mCodeValues = new HashMap<String, MessageType>();

    static {
        for (MessageType ccy : MessageType.values()) {
            mValues.put(ccy.value(), ccy);
            mCodeValues.put(ccy.code(), ccy);
        }
    }

    private MessageType(int value, String code, String description) {
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

    public static MessageType get(int value) {
        return mValues.get(value);
    }

    public static MessageType get(String code) {
        return mCodeValues.get(code);
    }
    
}
