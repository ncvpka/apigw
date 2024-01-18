package vn.supperapp.apigw.wso2.objs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccountCollection {

    @SerializedName("ACCOUNT")
    List<Account> accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "AccountCollection{" +
                "accounts=" + accounts +
                '}';
    }
}
