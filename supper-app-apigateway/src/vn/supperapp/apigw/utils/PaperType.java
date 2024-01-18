package vn.supperapp.apigw.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TruongLe
 * @Date 28/02/2022
 * @see Gender
 */

public enum PaperType {

    MALE("0", "PPCC"), // CMND
    FEMALE("1", "PPRT"); // Passport

    private final String code;
    private final String description;

    private static final Map<String, PaperType> mCodeValues = new HashMap<String, PaperType>();

    static {
        for (PaperType pc : PaperType.values()) {
            mCodeValues.put(pc.code(), pc);
        }
    }

    PaperType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String code() {
        return this.code;
    }

    public String description() {
        return this.description;
    }

    public static PaperType get(String code) {
        return mCodeValues.get(code);
    }

    public boolean is(String code) {
        return this.code.equals(code);
    }
}
