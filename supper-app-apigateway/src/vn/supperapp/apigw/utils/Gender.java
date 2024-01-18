package vn.supperapp.apigw.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TruongLe
 * @Date 21/02/2022
 * @see Gender
 */

public enum Gender {

    MALE("0", "Male"),
    FEMALE("1", "Female");

    private final String code;
    private final String description;

    private static final Map<String, Gender> mCodeValues = new HashMap<String, Gender>();

    static {
        for (Gender pc : Gender.values()) {
            mCodeValues.put(pc.code(), pc);
        }
    }

    private Gender(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String code() {
        return this.code;
    }

    public String description() {
        return this.description;
    }

    public static Gender get(String code) {
        return mCodeValues.get(code);
    }

    public boolean is(String code) {
        return this.code.equals(code);
    }

}
