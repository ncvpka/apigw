package vn.supperapp.apigw.wso2.objs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionDetailCollection {

    @SerializedName("transactionDetail")
    private List<Transaction> transactionDetail;

    public List<Transaction> getTransactionDetail() {
        return transactionDetail;
    }

    public void setTransactionDetail(List<Transaction> transactionDetail) {
        this.transactionDetail = transactionDetail;
    }
}
