package vn.supperapp.apigw.restful.models.common;

import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

public class CheckSubResponse extends BaseResponse {
    private String accountId;
    private String accountNumber;
    private String accountName;

    public CheckSubResponse() {
    }

    public CheckSubResponse(ErrorCode error, String language) {
        super(error, language);
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
}
