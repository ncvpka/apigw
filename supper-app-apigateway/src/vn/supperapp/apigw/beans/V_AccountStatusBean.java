package vn.supperapp.apigw.beans;

import java.util.Date;

public class V_AccountStatusBean {

    private Date birthday;
    private String preferredLanguage;
    private Long accountId;
    private Long partyId;
    private Date expiredDate;
    private String paperType;
    private String currencyNumCode;
    private String roleName;
    private String accountTypeName;
    private Long currencyId;
    private Long roleId;
    private String lastName;
    private String accountStateName;
    private Date issuedDate;
    private String paperNumber;
    private String firstName;
    private Long accountStateId;
    private String gender;
    private Long accountTypeId;
    private String msisdn;
    private String currencyCode;
    private String pan;
    private Long isCurrent;
    private Long partyRoleId;
    private String partitionKey;
    private String currencySymbol;
    private String currencyDecimal;
    private Double balance;
    private String subType;
    private String serviceType;
    private Long tier;
    private Date activeTime;
    private String address;

    public V_AccountStatusBean() {
    }

    public V_AccountStatusBean(Long accountId) {
        this.accountId = accountId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public String getCurrencyNumCode() {
        return currencyNumCode;
    }

    public void setCurrencyNumCode(String currencyNumCode) {
        this.currencyNumCode = currencyNumCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccountStateName() {
        return accountStateName;
    }

    public void setAccountStateName(String accountStateName) {
        this.accountStateName = accountStateName;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getPaperNumber() {
        return paperNumber;
    }

    public void setPaperNumber(String paperNumber) {
        this.paperNumber = paperNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getAccountStateId() {
        return accountStateId;
    }

    public void setAccountStateId(Long accountStateId) {
        this.accountStateId = accountStateId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public Long getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Long isCurrent) {
        this.isCurrent = isCurrent;
    }

    public Long getPartyRoleId() {
        return partyRoleId;
    }

    public void setPartyRoleId(Long partyRoleId) {
        this.partyRoleId = partyRoleId;
    }

    public String getPartitionKey() {
        return partitionKey;
    }

    public void setPartitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getCurrencyDecimal() {
        return currencyDecimal;
    }

    public void setCurrencyDecimal(String currencyDecimal) {
        this.currencyDecimal = currencyDecimal;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Long getTier() {
        return tier;
    }

    public void setTier(Long tier) {
        this.tier = tier;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "V_AccountStatusBean{" +
                "birthday=" + birthday +
                ", preferredLanguage='" + preferredLanguage + '\'' +
                ", accountId=" + accountId +
                ", partyId=" + partyId +
                ", expiredDate=" + expiredDate +
                ", paperType='" + paperType + '\'' +
                ", currencyNumCode='" + currencyNumCode + '\'' +
                ", roleName='" + roleName + '\'' +
                ", accountTypeName='" + accountTypeName + '\'' +
                ", currencyId=" + currencyId +
                ", roleId=" + roleId +
                ", lastName='" + lastName + '\'' +
                ", accountStateName='" + accountStateName + '\'' +
                ", issuedDate=" + issuedDate +
                ", paperNumber='" + paperNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", accountStateId=" + accountStateId +
                ", gender='" + gender + '\'' +
                ", accountTypeId=" + accountTypeId +
                ", msisdn='" + msisdn + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", pan='" + pan + '\'' +
                ", isCurrent=" + isCurrent +
                ", partyRoleId=" + partyRoleId +
                ", partitionKey='" + partitionKey + '\'' +
                ", currencySymbol='" + currencySymbol + '\'' +
                ", currencyDecimal='" + currencyDecimal + '\'' +
                ", balance=" + balance +
                ", subType='" + subType + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", tier=" + tier +
                ", activeTime=" + activeTime +
                ", address='" + address + '\'' +
                '}';
    }
}
