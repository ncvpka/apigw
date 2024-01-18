package vn.supperapp.apigw.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TruongLe
 * @Date 21/02/2022
 * @see ProcessCode
 */
public enum ProcessCode {

    //    register
    REGISTER_CHECK_311050("311050", "Register check info"),
    REGISTER_CONFIRM_002000("002000", "Register confirm info"),

    //    cashout
    CASH_OUT_INFO_311006("311006", "CashOut info"),
    CASH_OUT_CHECK_311005("311005", "CashOut Check info"),
    CASH_OUT_CONFIRM_SHOWROOM_010010("010010", "CashOut confirm showroom"),
    CASH_OUT_CONFIRM_AGENT_010101("010101", "CashOut confirm agent"),

    // change pin
    CHANGE_PIN_CONFIRM_001002("001002", "Change pin confirm info"),
    //top up
    TOP_UP_CHECK_ACCOUNT_311005("311005", "TopUp check info"),
    TOP_UP_CONFIRM_571010("571010", "TopUp confirm info"),

    // buyData
    BUY_DATA_INFO_311005("311005", "Buy data info"),

    TOP_UP_OTHER_CONFIRM_571010("571010", "TopUp someone confirm info"),
    TOP_UP_CONFIRM_571000("571000", "TopUp confirm info"),
    // bill
    BILL_CHECK_ACCOUNT_311051("311051", "Bill check info"),
    BILL_CONFIRM_571000("571000", "TopUp confirm info");
    private final String code;
    private final String description;

    private static final Map<String, ProcessCode> mCodeValues = new HashMap<String, ProcessCode>();

    static {
        for (ProcessCode pc : ProcessCode.values()) {
            mCodeValues.put(pc.code(), pc);
        }
    }

    private ProcessCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String code() {
        return this.code;
    }

    public String description() {
        return this.description;
    }

    public static ProcessCode get(String code) {
        return mCodeValues.get(code);
    }

    public boolean is(String code) {
        return this.code.equals(code);
    }

}
