package vn.supperapp.apigw.beans;

public class TransferMoneyCachedInfo {
    private String transId;
    private AccountInfo receiver;
    private Double amount;
    private Double fee;
    private Double discountAmount;
    private Double discountPercent;
    private Double totalAmount;
    private Double subAmount;
    private String content;

    public AccountInfo getReceiver() {
        return receiver;
    }

    public void setReceiver(AccountInfo receiver) {
        this.receiver = receiver;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getSubAmount() {
        return subAmount;
    }

    public void setSubAmount(Double subAmount) {
        this.subAmount = subAmount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
