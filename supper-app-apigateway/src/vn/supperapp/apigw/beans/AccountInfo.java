package vn.supperapp.apigw.beans;

import com.viettel.ewallet.utils.iso.msg.IsoObject;
import vn.supperapp.apigw.utils.enums.CurrencyCode;

public class AccountInfo {
    private String accountId;
    private String accountNumber;
    private String accountName;
    private String accountCurrency = CurrencyCode.VND.code();
    private String pan;
    private Integer accountStatus;
    private String carriedAccountId;

    public AccountInfo() {
    }

    public AccountInfo(IsoObject isoReceiver) {
        try {
            this.accountId = isoReceiver.getToAccountID();
            this.accountNumber = isoReceiver.getToPhone();
            this.accountName = isoReceiver.getToName();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountCurrency() {
        return accountCurrency;
    }

    public void setAccountCurrency(String accountCurrency) {
        this.accountCurrency = accountCurrency;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getCarriedAccountId() {
        return carriedAccountId;
    }

    public void setCarriedAccountId(String carriedAccountId) {
        this.carriedAccountId = carriedAccountId;
    }
}
