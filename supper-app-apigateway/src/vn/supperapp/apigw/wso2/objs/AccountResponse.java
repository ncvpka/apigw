package vn.supperapp.apigw.wso2.objs;

import com.google.gson.annotations.SerializedName;

public class AccountResponse {

    @SerializedName("ACCOUNTCollection")
    AccountCollection accountCollection;

    public AccountCollection getAccountCollection() {
        return accountCollection;
    }

    public void setAccountCollection(AccountCollection accountCollection) {
        this.accountCollection = accountCollection;
    }

    @Override
    public String toString() {
        return "AccountResponse{" +
                "accountCollection=" + accountCollection +
                '}';
    }
}
