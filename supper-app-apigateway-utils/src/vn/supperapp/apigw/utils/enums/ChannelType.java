package vn.supperapp.apigw.utils.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author truonglq
 */
public enum ChannelType {
    Unknown("Unknown", "Unknown"),
    APIGW_ENDUSER("APIGW_ENDUSER", "APIGW_ENDUSER"),
    APIGW_AGENT_MERCHANT("APIGW_AGENT_MERCHANT", "APIGW_AGENT_MERCHANT"),
    CMS("CMS", "CMS"),
    ;

    private static final Map<String, ChannelType> mCodeValues = new HashMap<String, ChannelType>();
    static {
        for (ChannelType it : ChannelType.values()) {
            mCodeValues.put(it.code(), it);
        }
    }

    private final String code;
    private final String description;

    ChannelType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String code() {
        return this.code;
    }

    public String description() {
        return this.description;
    }

    public static ChannelType get(String code) {
        return mCodeValues.get(code);
    }

}
