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
public enum AccountType {
    UNKNOWN(-1, "UNKNOWN", "UNKNOWN"),
    USER_BASIC(1, "USER_BASIC", "USER_BASIC"),
    USER_GOLD(2, "USER_GOLD", "AGENT MERCHANT USER_GOLD"),
    AGENT(3, "AGENT", "AGENT MERCHANT AGENT"),
    AGENT_SUPER(4, "AGENT_SUPER", "AGENT AGENT_SUPER APP"),
    MERCHANT(5, "MERCHANT", "AGENT MERCHANT APP"),
    ;

    private final int value;
    private final String code;
    private final String description;

    private static final Map<Integer, AccountType> mValues = new HashMap<>();
    private static final Map<String, AccountType> mCodeValues = new HashMap<String, AccountType>();

    static {
        for (AccountType ccy : AccountType.values()) {
            mValues.put(ccy.value(), ccy);
            mCodeValues.put(ccy.code(), ccy);
        }
    }

    private AccountType(int value, String code, String description) {
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

    public static AccountType get(int value) {
        return mValues.get(value);
    }

    public static AccountType get(String code) {
        return mCodeValues.get(code);
    }
    
}
