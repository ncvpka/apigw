/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author TruongLe
 */
public class Constants {
    public static ArrayList<Integer> ISO_FIELD_SIGN_LIST = new ArrayList<Integer>(Arrays.asList(0, 3, 4, 5, 7, 32, 40, 44, 54, 55, 60, 62, 63, 100, 101, 102, 103, 123, 125));
    public static final String MG_BANK_CODE = "FMC";
    public static final String BENEFIC_BANK_CODE = "FMC";
    public static final String VIETTEL_BANK_CODE = "FMC";
    public static final String MG_CLIENT_APP = "mobileapp";
    public static final String MG_TRANS_TYPE_2 = "2"; //Define
    public static final String MG_ISO_SUCCESS_00 = "00";
    public static String SERVICE_INDICATOR = "000018";
    public static String SERVICE_INDICATOR_NONE = "000020";
    public static String SERVICE_INDICATOR_NONE_OR_NONE = "000023";
    public static final String USER_LOGGED_INFO_KEY = "USER_LOGGED_INFO_KEY";
    public static final String USER_DEVICE_INFO_KEY = "USER_DEVICE_INFO_KEY";
    public static final String CLIENT_INFO_KEY = "CLIENT_INFO_KEY";
    public static final int USSD = 1;
    public static final int SMS = 2;
    public static String CASH_OUT = "000021";
    public static final String WEB_APP_LANGUAGE = "WEB_APP_LANGUAGE";
    public static final String ENGLISH = "en";
    public static final String VIETNAMESE = "vi";
    public static final String KHMER = "km";
    public static final String CHINESE = "zh";

    public static final String LOG_ITEM_MSISDN = "LOG_ITEM_MSISDN";
    public static final String LOG_ITEM_REQ = "LOG_ITEM_REQ";
    public static final String LOG_ITEM_START_TIME = "LOG_ITEM_START_TIME";
    public static String TOP_UP_AMOUNT_MIN = "10833";
    public static String TOP_UP_AMOUNT_MAX = "10834";
    public static final String ACCOUNT_STATE_RESET_PIN = "RESET PIN";
    // EXTRA ACCOUNT
    public static final Integer EXTRA_TRANS_TYPE = 1;

    // OCS API
    public static final String ERR_OCS_CODE = "1";
    public static final String ERR_RESPONSE = "2";
    public static final String ERR_OCS = "3";
    public static final String OCS_SUCCESS = "0";
    public static final String ERROR_CODE_VALUE = "99";
    public static final String OCS_SUCCESS_VALUE = "00";
    // End OCS API

    // Register Extra Account

    public static final String EXTRA_ACCOUNT = "Metfone promotion account";
    public static final String EXTRA_ACC_DESC = "Promotion account for new Metfone eWallet";
    public static final String PROGRAME_TYPE = "INIT_EWALLET";
    public static final Double BALANCE_INIT_ACCOUNT = 0D;

    // End Register Extra Account

    // Transaction
    public static final Integer EXTRA_TRANSACTION_PAGE_SIZE = 20;
    public static final String PREFIX_TID_CODE = "E";
    public static final Long TRAN_INDICATOR_CREDIT = 1L;
    public static final Long TRAN_INDICATOR_DEBIT = -1L;
    public static final String PROGRAME_CAT_CODE = "TRANSFER";
    public static final String OBJ_TYPE_TRANSFER = "EXTRA_PROGRAME";
    public static final String OBJ_TYPE_PAYMENT = "EXTRA_PRODUCT";
    public static final String TRANSFER_SUCCESS_CODE = "00";
    // End Transaction
    public static final String MDEALER_CASHIN = "MDEALER_CASHIN";

    public static final int REDIS_DB_DEVICE_CACHED = 0;
    public static final int REDIS_DB_LOGIN_CACHED = 1;
    public static final int REDIS_DB_OTP_TRANS_CACHED = 2;
    public static final int REDIS_DB_APP_CONFIG_CACHED = 3;
    //Role
    public static final String CUSTOMER = "4";
    public static final String SUPER_AGENT = "7";
    //End Role

    //PARTNER_SERVICE
    public static final String PARTNER_CODE = "LUMITEL";
    public static final String SERVICE_CODE = "AIRTIME";
    public static final String BCCS_TOPUP = "LUMITEL_AIRTIME";
    public static final String PARTNER_CODE_BILL = "NATCOM";
    public static final String SERVICE_CODE_BILL = "ENQUIRY_INFO_POST_PAID";
    public static final String SERVICE_CHANGE_RATE_BILL = "EXCHANGE_RATE_POST_PAID";
    public static final String CARRIER = "Natcash";
    public static final String POST_PAID = "POST_PAID";
    public static final String SERVICE_NAME = "Natcom Bill";
    public static final String POSTPAID_SERVICE = "MOBILE";

    //End PARTNER_SERVICE

    //FEE SERVICE
    public static final String FEE_TRANSFER = "1";
    public static final String FEE_CASH_OUT = "2";
    //END FEE SERVICE

    //ServiceIndicator
    public static final String SUB_PRE = "000000";
    public static final String SUB_POS = "000001";
    //End ServiceIndicator

    //Account State
    public static final String ACCOUNT_STATE_ACTIVE = "1";
    public static final String ACCOUNT_STATE_ID_INVALID_PIN = "2";
    public static final String ACCOUNT_STATE_ID_RESET_PIN = "4";
    public static final String ACCOUNT_STATE_BLOCKED = "7";
    public static final String ACCOUNT_STATE_REGISTER = "0";
    //Language configuration
    public static final String France = "fr_FR";
    public static final String English = "en_US";
    public static final String CREOLE = "rn_BI";
    //bill
    public static final Integer SUCCESS = 0;
    public static final String SERVICE = "MOBILE";

    // Transaction notification Type
    public static final Long NOTIFICATION_TYPE_TRANSFER = 1L;
    public static final Long NOTIFICATION_TYPE_PERSONAL_SCHEDULE = 2L;
    public static final Long NOTIFICATION_TYPE_BLOG = 3L;
    public static final Long NOTIFICATION_TYPE_BUY_DATA = 3L;
    public static final Long NOTIFICATION_TYPE_TOPUP = 4L;
    public static final Long NOTIFICATION_TYPE_BILL = 5L;
    public static final Long NOTIFICATION_TYPE_LOTTO = 6L;
    // ObjType notification type
    public static final String OBJ_TYPE_TRANSACTION = "1";

    public static final String ANDROID_CURRENT_VERSION_CACHED_KEY = "ANDROID_CURRENT_VERSION";
    public static final String IOS_CURRENT_VERSION_CACHED_KEY = "IOS_CURRENT_VERSION";

    //lotto
    public static final String MERCHANT_CODE_LOTTO = "NATCOM_LOTTERY";
    public static final String API_CODE_LOTTO = "CONFIRM_BUY_TICKET";
    public static final String SERVICE_CODE_LOTTO = "LOTTO_BUY_TICKET";
    public static final String PARTNER_CODE_LOTTO = "NATCOM";
    public static final String CONTENT_TYPE_LOTTO =  "application/json";
    public static final String CHANNEL_LOTTO =  "WALLET";
    public static final String IS_SUCCESS_LOTTO =  "0";
    public static final String SYSTEM_ERROR =  "-1";
    public static final String LOTTO_CONNECT_TIMEOUT ="2";
    public static final String LOTTO_TRANS_TYPE ="LOTTO";
    public static final String LOTTO_SERVICE ="Lotto";
    public static final Integer TRANSACTION_INIT = 0;
    public static final Integer TRANSACTION_PAYMENT_SUCCESS = 1;
    public static final Integer TRANSACTION_PAYMENT_FAILED = -1;
    public static final Integer TRANSACTION_PARTNER_TIMEOUT = 98;
    public static final Integer TRANSACTION_PARTNER_SUCCESS = 2;
    public static final Integer TRANSACTION_PARTNER_FAILED = -2;
    public static final Integer TRANSACTION_PAYMENT_REVERT_SUCESSS = 3;
    public static final Integer TRANSACTION_PAYMENT_REVERT_FAILED = -3;
    public static final Integer PARTNER_UNCHECKED = 2; //chưa đối soát
    public static final Integer PARTNER_SUCCESS = 1;
    public static final Integer PARTNER_FAILED = -1;
    public static final Integer REVERT_SUCCESS = 1;
    public static final Integer REVERT_FAILED = -1;
    public static final Integer PAYMENT_SUCCESS = 1;
    public static final Integer PAYMENT_FAILED = -1;
}
