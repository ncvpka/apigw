package vn.supperapp.apigw.restful.models.common;

import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

public class BalanceInfoResponse extends BaseResponse {
    private String accountId;
    private String accountName;
    private String accountNumber;

    public BalanceInfoResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

}
