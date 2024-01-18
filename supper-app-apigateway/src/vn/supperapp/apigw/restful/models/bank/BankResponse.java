package vn.supperapp.apigw.restful.models.bank;

import vn.supperapp.apigw.beans.UserInfo;
import vn.supperapp.apigw.db.dto.Bank;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

import java.util.List;

public class BankResponse extends BaseResponse {

    private List<Bank> bankList;

    public BankResponse() {
    }

    public BankResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public List<Bank> getBankList() {
        return bankList;
    }

    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
    }
}
