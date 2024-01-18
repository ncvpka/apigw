package vn.supperapp.apigw.wso2.objs;

import com.google.gson.annotations.SerializedName;

public class TransactionHistoryResponse {

    @SerializedName("transactionCollection")
    TransactionCollection transactionCollection;

    public TransactionCollection getTransactionCollection() {
        return transactionCollection;
    }

    public void setTransactionCollection(TransactionCollection transactionCollection) {
        this.transactionCollection = transactionCollection;
    }
}
