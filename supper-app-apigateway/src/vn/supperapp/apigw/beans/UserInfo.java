package vn.supperapp.apigw.beans;

import vn.supperapp.apigw.db.dto.Account;

import javax.xml.crypto.Data;
import java.util.Date;

public class UserInfo {
    private String accountNumber;
    private String fullName;
    private Integer accountType;
    private Integer accountStatus;
    private Long gender;
    private String accountId;
    private Date birthDay;
    private String pin;
    private String carriedAccountId;
    private String bank;
    private String bankNumber;
    private String email;
    private String address;
    private String paperNumber;
    private String avatarUrl;
    private Long timeKeepingType;
    private Long orgId;
    private String orgName;
    private Long branchId;
    private String branchCode;
    private String branchName;
    private Long departmentId;
    private String departmentName;

    public UserInfo() {
    }

    public UserInfo(Account account, String pin , String avatarUrl, String accountNumber) {
        try {
            this.accountNumber = accountNumber;
            this.fullName = account.getFullName();
            this.accountStatus = account.getStatus().intValue();
            this.accountId = account.getId().toString();
            this.pin = pin;
            this.carriedAccountId = account.getCarriedAccountId().toString();
            this.accountType = account.getTier().intValue();
            this.avatarUrl = avatarUrl;
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getPass() {
        return pin;
    }

    public void setPass(String pin) {
        this.pin = pin;
    }

    public String getCarriedAccountId() {
        return carriedAccountId;
    }

    public void setCarriedAccountId(String carriedAccountId) {
        this.carriedAccountId = carriedAccountId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaperNumber() {
        return paperNumber;
    }

    public void setPaperNumber(String paperNumber) {
        this.paperNumber = paperNumber;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Long getTimeKeepingType() {
        return timeKeepingType;
    }

    public void setTimeKeepingType(Long timeKeepingType) {
        this.timeKeepingType = timeKeepingType;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
}
