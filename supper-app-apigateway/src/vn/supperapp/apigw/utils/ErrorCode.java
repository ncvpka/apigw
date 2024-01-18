/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TruongLe
 */
public enum ErrorCode {
    SUCCESS(0, "MSG_SUCCESS", "Successfully"),
    ERR_COMMON(1, "ERR_COMMON", "System error, please try again!"),
    ERR_MISSING_DATA(1, "ERR_COMMON", "Not found data, please try again!"),
    ERR_MISSING_PARAMETERS(2, "ERR_MISSING_PARAMETERS", "Missing parameters"),
    ERR_MISSING_SHIFT(2, "ERR_MISSING_SHIFT", "Missing shift in system"),
    ERR_MISSING_TIMEKEEPING(2, "ERR_MISSING_TIMEKEEPING", "Missing timekeeping in system"),
    ERR_PARAMETERS_INVALID(2, "ERR_PARAMETERS_INVALID", "Invalid data"),
    ERR_TRANSACTION_EXPIRED(2, "ERR_TRANSACTION_EXPIRED", "Transaction expired"),
    ERR_RESEND_OTP_OVER_LIMITED(5, "ERR_RESEND_OTP_OVER_LIMITED", "Cannot resend OTP, over limited"),
    ERR_PIN_INVALID(2, "ERR_PIN_INVALID", "Pin invalid"),
    ERR_OTP_ID_INVALID(3, "ERR_OTP_ID_INVALID", "OTP ID invalid"),
    ERR_AMOUNT_INVALID(5, "ERR_AMOUNT_INVALID", "Amount is invalid"),
    ERR_ACCOUNT_EXISTS(5, "ERR_ACCOUNT_EXISTS", "Account exists"),
    ERR_LANGUAGE_NOT_EXISTS(5, "ERR_LANGUAGE_NOT_EXISTS", "Language is not exist"),
    ERR_DEBIT_AMOUNT(5, "ERR_DEBIT_AMOUNT", "Amount can not be greater than debit amount"),

    //Common
    ERR_RECEIVER_ACCOUNT_INVALID(2, "ERR_RECEIVER_ACCOUNT_INVALID", "Receiver's account number invalid"),
    ERR_ACCOUNT_INVALID(2, "ERR_ACCOUNT_INVALID", "Account number invalid"),

    //Auth
    ERR_USER_LOCKED(2, "ERR_USER_LOCKED", "App User locked, not allow to use app"),
    ERR_ACCOUNT_NOT_EXISTS(2, "ERR_ACCOUNT_NOT_EXISTS", "Account not exists"),
    ERR_LOGIN_INVALID_INFORMATION(2, "ERR_LOGIN_INVALID_INFORMATION", "Login information invalid, please check and try again! Your account will be locked after entering the wrong password 5 times."),
    ERR_INVALID_INFORMATION(2, "ERR_LOGIN_INVALID_INFORMATION", "Information invalid, please check and try again!"),
    ERR_ACCOUNT_NOT_ACTIVE(2, "ERR_ACCOUNT_NOT_ACTIVE", "Your account is not active, please check and try again."),
    ERR_ACCOUNT_NOT_END_USER(2, "ERR_ACCOUNT_NOT_END_USER", "Your account is not End User, please check and try again."),
    ERR_OUT_LOCATION(2, "ERR_OUT_LOCATION", "Your account out of location, please try again."),
    ERR_LOGIN_ACCOUNT_RESET_PIN(2, "ERR_LOGIN_ACCOUNT_RESET_PIN", "Please dial *202# to setup the new PIN."),
    ERR_LOGIN_ACCOUNT_INVALID_PIN(2, "ERR_LOGIN_ACCOUNT_INVALID_PIN", "Account is in invalid PIN state."),
    ERR_CHECK_IN(2, "ERR_CHECK_IN", "Not found check in."),
    //REGISTER
    NOT_ENOUGH_AGE(5, "NOT_ENOUGH_AGE", "Not enough age"),

    USER_NOT_ADD_BANK(5, "USER_NOT_ADD_BANK", "User don't add bank. Please contact to admin"),

    //QR PAYMENT
    ERR_QRCODE_INVALID(2, "ERR_QRCODE_INVALID", "Qr Code invalid"),

    //BUY-DATA
    ERR_PHONE_NUMBER_USE_THIS_DATA_PLAN(5, "ERR_PHONE_NUMBER_USE_THIS_DATA_PLAN", "Sorry! This phone number is using this data package. Please check again!"),

    // TRANSFER
    ERR_TRANSACTION_NOT_CONFIG_FEE(5, "ERR_TRANSACTION_NOT_CONFIG_FEE", "Transaction is not configured fee! Please try again later!"),
    ERR_DUPLICATE_PHONE_NUMBER(5, "ERR_DUPLICATE_PHONE_NUMBER", "The transferred account cannot match your phone number"),
    ERR_RECEIVER_IS_NOT_ENDUSER(5, "ERR_RECEIVER_IS_NOT_ENDUSER", "The receiver is not end user! Please check again"),
    //BILL
    ERR_GET_EXCHANGE_RATE_NOT_SUCCESS(5, "ERR_GET_EXCHANGE_RATE_NOT_SUCCESS", "Get exchange rate fail! Please try again later!"),

    //Invite
    ERR_INVITED_PHONE_GREATER(5, "ERR_INVITED_PHONE_GREATER", "The number of invited phone numbers is greater than 10."),
    CAN_NOT_DELETE_FILE(2, "CAN_NOT_DELETE_FILE", "Can not delete file image"),
    ERR_INVITED_WALLET_NUMBER(5, "ERR_INVITED_WALLET_NUMBER", "This phone number is using wallet"),

    //lotto
    ERR_REVERT_TRANSACTION(5, "ERR_REVERT_TRANSACTION", "Transaction failed. Refund successful");


    private final int status;
    private final String code;
    private final String message;

    private static final Map<Integer, ErrorCode> mStatusValues = new HashMap<Integer, ErrorCode>();
    private static final Map<String, ErrorCode> mCodeValues = new HashMap<String, ErrorCode>();

    static {
        for (ErrorCode ec : ErrorCode.values()) {
            mStatusValues.put(ec.status(), ec);
            mCodeValues.put(ec.code(), ec);
        }
    }

    private ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int status() {
        return this.status;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public boolean is(int status) {
        return this.status == status;
    }

    public static ErrorCode get(int status) {
        return mStatusValues.get(status);
    }

    public static ErrorCode get(String code) {
        return mCodeValues.get(code);
    }
}
