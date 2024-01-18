package vn.supperapp.apigw.utils.enums;

import java.util.HashMap;
import java.util.Map;

public enum HrCategory {
    UNKNOWN(-1, "UNKNOWN", "UNKNOWN"),
    DEPARTMENT(1, "HR_DEPARTMENT", "DEPARTMENT"),
    ALLOWANCE(2, "HR_PARAM", "ALLOWANCE"),
    CERTIFICATE(3, "HR_PARAM", "CERTIFICATE"),
    CONTRACT_DURATION(4, "HR_PARAM", "CONTRACT_DURATION"),
    CONTRACT_TYPE(5, "HR_PARAM", "CONTRACT_TYPE"),
    EDUCATION_LEVEL(6, "HR_PARAM", "EDUCATION_LEVEL"),
    EDUCATION_TYPE(7, "HR_PARAM", "EDUCATION_TYPE"),
    ETHNIC(8, "HR_PARAM", "ETHNIC"),
    FOREIGN_LANG_LEVEL(9, "HR_PARAM", "FOREIGN_LANG_LEVEL"),
    IT_LEVEL(10, "HR_PARAM", "IT_LEVEL"),
    MARITAL_STATUS(11, "HR_PARAM", "MARITAL_STATUS"),
    NATIONALITY(12, "HR_PARAM", "NATIONALITY"),
    POSITION(13, "HR_PARAM", "POSITION"),
    RELATIONSHIP(14, "HR_PARAM", "RELATIONSHIP"),
    RELIGION(15, "HR_PARAM", "RELIGION"),
    SALARY_LEVEL(16, "HR_PARAM", "SALARY_LEVEL"),
    SALARY_PAY_METHOD(17, "HR_PARAM", "SALARY_PAY_METHOD"),
    SPECIALIZED_TRAINING(18, "HR_PARAM", "SPECIALIZED_TRAINING"),
    TRAINING_TYPE(19, "HR_PARAM", "TRAINING_TYPE"),
    WORK_TYPE(20, "HR_PARAM", "WORK_TYPE"),

    ;

    private final int key;
    private final String param;
    private final String paramValue;

    private static final Map<Integer, HrCategory> mKey = new HashMap<>();
    private static final Map<String, HrCategory> mParamKeys = new HashMap<>();

    static {
        for (HrCategory ccy : HrCategory.values()) {
            mKey.put(ccy.key(), ccy);
            mParamKeys.put(ccy.param(), ccy);
        }
    }

    private HrCategory(int key, String param, String paramValue) {
        this.key = key;
        this.param = param;
        this.paramValue = paramValue;
    }

    public int key() {
        return this.key;
    }

    public String param() {
        return this.param;
    }

    public String paramValue() {
        return this.paramValue;
    }

    public boolean is(int key) {
        return this.key == key;
    }

    public static HrCategory get(int key) {
        return mKey.get(key);
    }

    public static HrCategory get(String param) {
        return mParamKeys.get(param);
    }

    public boolean is(String param) {
        return this.paramValue.equals(param.toUpperCase());
    }
}
