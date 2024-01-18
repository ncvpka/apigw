package vn.supperapp.apigw.wso2.objs;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Transaction {
    @SerializedName("transactionName")
    private String transactionName;

    @SerializedName("transactionId")
    private Long transactionId;

    @SerializedName("accountNumber")
    private String accountNumber;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("toAccountNumber")
    private String toAccountNumber;

    @SerializedName("toFullName")
    private String toFullName;

    @SerializedName("amount")
    private String amount;

    @SerializedName("fee")
    private String fee;

    @SerializedName("discount")
    private String discount;

    @SerializedName("totalAmount")
    private String totalAmount;

    @SerializedName("createDate")
    private Date createDate;

    @SerializedName("content")
    private String content;

    @SerializedName("totalRecords")
    private String totalRecords;

    @SerializedName("transType")
    private String transType;

    private Integer dateKey;

    private String createDateString;
    private String dateKeyFormat;
    private List<Transaction> listAllTransaction;

    private String service;
    private String provider;
    private String amountDetail;
    private String tax;

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getAmountDetail() {
        return amountDetail;
    }

    public void setAmountDetail(String amountDetail) {
        this.amountDetail = amountDetail;
    }

    public String getCreateDateString() {
        return createDateString;
    }

    public void setCreateDateString(String createDateString) {
        this.createDateString = createDateString;
    }

    public Integer getDateKey() {
        return dateKey;
    }

    public void setDateKey(Integer dateKey) {
        this.dateKey = dateKey;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public String getToFullName() {
        return toFullName;
    }

    public void setToFullName(String toFullName) {
        this.toFullName = toFullName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(String totalRecords) {
        this.totalRecords = totalRecords;
    }

    public String getDateKeyFormat() {
        return dateKeyFormat;
    }

    public void setDateKeyFormat(String dateKeyFormat) {
        this.dateKeyFormat = dateKeyFormat;
    }

    public List<Transaction> getListAllTransaction() {
        return listAllTransaction;
    }

    public void setListAllTransaction(List<Transaction> listAllTransaction) {
        this.listAllTransaction = listAllTransaction;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }
}
