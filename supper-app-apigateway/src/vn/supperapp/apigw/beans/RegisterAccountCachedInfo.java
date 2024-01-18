package vn.supperapp.apigw.beans;

public class RegisterAccountCachedInfo {
    private String accountNumber;
    private String fullName;
    private String pass;
    private int gender;
    private String idNo;
    private String dob;
    private String dobStr;
    private String paperType;
    private Integer paperTypeInt;
    private String desTransaction;
    private String referenceNumber;

    public RegisterAccountCachedInfo() {
    }

    public Integer getPaperTypeInt() {
        return paperTypeInt;
    }

    public void setPaperTypeInt(Integer paperTypeInt) {
        this.paperTypeInt = paperTypeInt;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public String getDesTransaction() {
        return desTransaction;
    }

    public void setDesTransaction(String desTransaction) {
        this.desTransaction = desTransaction;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDobStr() {
        return dobStr;
    }

    public void setDobStr(String dobStr) {
        this.dobStr = dobStr;
    }
}
