package vn.supperapp.apigw.utils.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author truonglq
 */
public enum UserCheckCode {
    R00000("00000",  "Success"),
    R10116("10116", "Account not exists"),
    R10117("10117", "Account cancelled"),
    R10118("10118", "Account blocked"),
    R10129("10129", "Account is in invalid PIN state"),
    ;

    private final String code;
    private final String description;

    private static final Map<String, UserCheckCode> mCodeValues = new HashMap<String, UserCheckCode>();

    static {
        for (UserCheckCode it : UserCheckCode.values()) {
            mCodeValues.put(it.code(), it);
        }
    }

    UserCheckCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String code() {
        return this.code;
    }
    public String description() {
        return this.description;
    }


    public boolean compare(UserCheckCode code) {
        return code != null && this.compareTo(code) == 0;
    }
    public boolean compareCode(String code) {
        return this.code.equals(code);
    }

    public static UserCheckCode get(String code) {
        return mCodeValues.get(code);
    }


}
