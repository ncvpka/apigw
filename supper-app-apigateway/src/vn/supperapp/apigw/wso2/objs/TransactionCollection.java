package vn.supperapp.apigw.wso2.objs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionCollection {

    @SerializedName("transaction")
    private List<Transaction> transaction;

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }
}
