/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.utils.enums;

/**
 * @author truonglq
 */
public enum TransactionType {
    TRANSFER_CHECK_FEE("311005", "021000", "", "", "Check fee for transfer money"),
    TRANSFER_CHECK_RECEIVER("311006", "021001", "", "", "Transfer check receiver"),
    TRANSFER_MONEY("021000", "021001", "", "", "Transfer check receiver"),
    CASH_OUT_INFO("311006", "010001", "", "", "CashOut info"),
    CASH_OUT_CHECK_FEE("311005", "", "", "", "CashOut check fee"),
    CASH_OUT_CONFIRM_SHOWROOM("010010", "", "", "", "CashOut confirm showroom"),
    CASH_OUT_CONFIRM_AGENT_010101("010101", "", "", "", "CashOut confirm agent"),
    CASH_OUT_CONFIRM_AGENT_010001("010001", "", "", "", "CashOut confirm agent"),
    REGISTER_UPGRADE_ACCOUNT("003300", "", "", "", "Update Customer Account"),
    REGISTER_CONFIRM("002000", "", "", "", "Register confirm info"),
    REGISTER_CHECK_INFO("311050", "", "", "", "Register check info"),
    BUY_DATA_INFO("311005", "571000", "", "", "Buy data info"),
    BUY_DATA_CONFIRM("571000", "", "", "", "Buy data confirm"),
    BALANCE_CHECK("311001", "", "", "", "Balance check info"),
    FEE_CHECK_TRANSFER("311016", "021000", "", "", "Check fee transfer to wallet"),
    FEE_CHECK_CASH_OUT("311016", "010001", "", "", "Check fee cash out"),
    CHANGE_PIN_CONFIRM("001002", "", "", "", "Change pin confirm info"),
    TOP_UP_CHECK_ACCOUNT("311005", "", "LUMITEL", "AIRTIME", "TopUp check info"),
    TOP_UP_OTHER_CONFIRM("571010", "", "LUMITEL", "AIRTIME", "TopUp someone confirm info"),
    TOP_UP_CONFIRM("571000", "", "LUMITEL", "AIRTIME", "TopUp confirm info"),
    BILL_CHECK_EXCHANGE("311051", "", "NATCOM", "EXCHANGE_RATE_POST_PAID", "Bill check exchange"),
    BILL_CHECK_ACCOUNT("311051", "", "NATCOM", "ENQUIRY_INFO_POST_PAID", "Bill check info"),
    BILL_CONFIRM("571000", "", "NATCOM", "POST_PAID", "Postpaid confirm info"),
    BILL_FTTH_CONFIRM("571000", "", "NATCOM", "FTTH", "FTTH confirm info"),
    BILL_CHECK_FEE("311005", "571000", "NATCOM", "POST_PAID", "Bill check fee"),
    BILL_FTTH_CHECK_FEE("311005", "571000", "NATCOM", "FTTH", "Bill FTTH check fee"),
    CHANGE_LANGUAGE("001100", "", "", "", "Change language"),
    CHECK_FEE("311016", "", "", "", "Check fee "),
    CHECK_FEE_LOTTO("311005", "576000", "NATCOM", "LOTTO_BUY_TICKET", "Check fee lotto"),
    PAYMENT_LOTTO("576000", "", "NATCOM", "LOTTO_BUY_TICKET", "Payment lotto"),
    REVERT_TRANSACTION_LOTTO("577000", "", "NATCOM", "LOTTO_BUY_TICKET", "Revert transaction lotto"),
    UPGRADE_TO_FULL_WALLET("003306","BCCS","","","Update Account Info By API"),
    CHECK_FEE_DGI("311005", "572000","DGI","BUY_TAX_DGI","Check fee DGI package"),
    ACCOUNTING_DGI("576000","","DGI","BUY_TAX_DGI_APP","Accounting for DGI service"),
    RESET_PIN("001003","","","","Reset Pin"),
    ;

    private final String processCode;
    private String transactionCode;
    private final String partnerCode;
    private final String serviceCode;
    private final String description;


    private TransactionType(String processCode, String transactionCode, String partnerCode, String serviceCode, String description) {
        this.processCode = processCode;
        this.transactionCode = transactionCode;
        this.partnerCode = partnerCode;
        this.serviceCode = serviceCode;
        this.description = description;
    }

    public String processCode() {
        return this.processCode;
    }

    public String transactionCode() {
        return this.transactionCode;
    }

    public String partnerCode() {
        return this.partnerCode;
    }

    public String serviceCode() {
        return this.serviceCode;
    }

    public String description() {
        return this.description;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }
}
