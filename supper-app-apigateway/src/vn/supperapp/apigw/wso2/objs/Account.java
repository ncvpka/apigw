package vn.supperapp.apigw.wso2.objs;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Account {

    @SerializedName("ACCOUNT_ID")
    private Long accountId;
    @SerializedName("PAN")
    private String pan;
    @SerializedName("ACCOUNT_STATE_ID")
    private Long accountStateId;
    @SerializedName("ACCOUNT_TYPE_ID")
    private Long accountTypeId;
    @SerializedName("MODIFIED_DATE")
    private Date modifiedDate;
    @SerializedName("CURRENCY_ID")
    private Long currencyId;
    @SerializedName("PARTY_ROLE_ID")
    private Long partyRoleId;
    @SerializedName("CURRENCY")
    private String currency;
    @SerializedName("PARTITION_KEY")
    private String partitionKey;
    @SerializedName("CREATED_DATE")
    private Date createdDate;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public Long getAccountStateId() {
        return accountStateId;
    }

    public void setAccountStateId(Long accountStateId) {
        this.accountStateId = accountStateId;
    }

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Long getPartyRoleId() {
        return partyRoleId;
    }

    public void setPartyRoleId(Long partyRoleId) {
        this.partyRoleId = partyRoleId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPartitionKey() {
        return partitionKey;
    }

    public void setPartitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", pan='" + pan + '\'' +
                ", accountStateId=" + accountStateId +
                ", accountTypeId=" + accountTypeId +
                ", modifiedDate=" + modifiedDate +
                ", currencyId=" + currencyId +
                ", partyRoleId=" + partyRoleId +
                ", currency='" + currency + '\'' +
                ", partitionKey='" + partitionKey + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
