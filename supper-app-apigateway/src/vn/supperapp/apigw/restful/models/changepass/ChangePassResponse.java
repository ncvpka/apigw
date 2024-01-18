package vn.supperapp.apigw.restful.models.ChangePass;

import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

public class ChangePassResponse extends BaseResponse {
    private String accountId;
    private String pinNew;


    public ChangePassResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }



    public String getPassNew() {
        return pinNew;
    }

    public void setPassNew(String pinNew) {
        this.pinNew = pinNew;
    }

}
