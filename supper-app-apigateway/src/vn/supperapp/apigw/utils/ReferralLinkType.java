package vn.supperapp.apigw.utils;

import java.util.HashMap;
import java.util.Map;

public enum ReferralLinkType {
    UNKNOWN(-1, "-1", "UNKNOWN"),
    REGISTER(1, "1", "REGISTER"),
    ;

    private final int value;
    private final String code;
    private final String description;

    private static final Map<Integer, ReferralLinkType> mValues = new HashMap<Integer, ReferralLinkType>();
    private static final Map<String, ReferralLinkType> mCodeValues = new HashMap<String, ReferralLinkType>();

    static {
        for (ReferralLinkType ccy : ReferralLinkType.values()) {
            mValues.put(ccy.value(), ccy);
            mCodeValues.put(ccy.code(), ccy);
        }
    }

    private ReferralLinkType(int value, String code, String description) {
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

    public static ReferralLinkType get(int value) {
        return mValues.get(value);
    }

    public static ReferralLinkType get(String code) {
        return mCodeValues.get(code);
    }
}
