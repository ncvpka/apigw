package vn.supperapp.apigw.wso2.objs;

import com.google.gson.annotations.SerializedName;

public class TransactionTypeResponse {

    @SerializedName("transactionTypeCollection")
    TransactionTypeCollection transactionCollection;

    public TransactionTypeCollection getTransactionCollection() {
        return transactionCollection;
    }

    public void setTransactionCollection(TransactionTypeCollection transactionCollection) {
        this.transactionCollection = transactionCollection;
    }
}
