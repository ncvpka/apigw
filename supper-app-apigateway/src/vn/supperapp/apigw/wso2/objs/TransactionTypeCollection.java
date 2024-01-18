package vn.supperapp.apigw.wso2.objs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionTypeCollection {

    @SerializedName("transactionType")
    private List<TransactionType> transactionTypes;

    public List<TransactionType> getTransactionTypes() {
        return transactionTypes;
    }

    public void setTransactionTypes(List<TransactionType> transactionTypes) {
        this.transactionTypes = transactionTypes;
    }
}
