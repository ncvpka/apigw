package vn.supperapp.apigw.utils.enums;

import java.util.HashMap;
import java.util.Map;

public enum AppUserStatus {
    LOCKED(0L, 0L, "Locked"),
    ACTIVE(1L, 1L, "Active"),

    ;

    private final Long code;
    private final Long value;
    private final String description;

    private static final Map<Long, AppUserStatus> mCodes = new HashMap<>();

    static {
        for (AppUserStatus as : AppUserStatus.values()) {
            mCodes.put(as.code(), as);
        }
    }

    AppUserStatus(Long value, Long code, String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    public Long value() { return value; }

    public Long code() {
        return code;
    }

    public String description() {
        return description;
    }

    public boolean is(Long code) {
        return this.code == code;
    }

}
