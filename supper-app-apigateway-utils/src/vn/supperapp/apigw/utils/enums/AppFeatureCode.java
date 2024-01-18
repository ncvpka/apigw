package vn.supperapp.apigw.utils.enums;

import java.util.HashMap;
import java.util.Map;

public enum AppFeatureCode {
    LOGIN(1, "LOGIN", "Login"),
    TRANSFER_MONEY(10, "TRANSFER_MONEY", "Transfer money (ewallet)"),
    TRANSFER_DOMESTIC_BANK(11, "TRANSFER_DOMESTIC_BANK", "Transfer to domestic bank"),
    TOP_UP_MONEY(12, "TOP_UP_MONEY", "Top up money (ewallet)"),
    BILL_MONEY(13,"BILL_MONEY","Bill money (ewallet)"),
    DATA_MONEY(14,"DATA_MONEY","Data money (ewallet)"),
    USER_SCAN_QR(15,"USER_SCAN_QR","User scan qr"),
    LINK_BANK(16,"LINK_BANK","Link bank"),
    BILL_INTERNET(17,"BILL_INTERNET","NatCom  internet"),
    CASH_OUT_MONEY(18,"CASH_OUT_MONEY","Cashout money"),
    CASH_IN(19,"CASH_IN","Cashin"),
    ;

    private final int value;
    private final String code;
    private final String description;

    private static final Map<Integer, AppFeatureCode> mValues = new HashMap<>();
    private static final Map<String, AppFeatureCode> mCodes = new HashMap<>();

    static {
        for (AppFeatureCode as : AppFeatureCode.values()) {
            mValues.put(as.value(), as);
            mCodes.put(as.code(), as);
        }
    }

    AppFeatureCode(int value, String code, String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    public int value() { return value; }

    public String code() {
        return code;
    }

    public String description() {
        return description;
    }

    public boolean is(String code) {
        return this.code.equals(code);
    }

    public static AppFeatureCode get(String code) {
        return mCodes.get(code);
    }

}
