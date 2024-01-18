package vn.supperapp.apigw.wso2.objs;

import com.google.gson.annotations.SerializedName;

public class TransactionDetailResponse {

    @SerializedName("transactionDetailCollection")
    TransactionDetailCollection transactionDetailCollection;

    public TransactionDetailCollection getTransactionDetailCollection() {
        return transactionDetailCollection;
    }

    public void setTransactionDetailCollection(TransactionDetailCollection transactionDetailCollection) {
        this.transactionDetailCollection = transactionDetailCollection;
    }
}
