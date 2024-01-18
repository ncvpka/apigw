package vn.supperapp.apigw.restful.models.common;

import vn.supperapp.apigw.restful.models.BaseRequest;

public class CheckSubRequest extends BaseRequest {
    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
