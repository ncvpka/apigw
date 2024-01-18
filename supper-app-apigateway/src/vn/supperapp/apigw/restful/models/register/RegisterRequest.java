package vn.supperapp.apigw.restful.models.register;

import vn.supperapp.apigw.restful.models.BaseRequest;

/**
 * @author TruongLe
 * @Date 16/02/2022
 * @see RegisterRequest
 */

public class RegisterRequest extends BaseRequest {
    private String fullName;
    private String dob;
    private int gender;
    private int paperType;
    private String idNo;
    private String referenceNumber;
    private String imageCustomer;
    private String imageIdentityFront;
    private String imageIdentityBack;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getPaperType() {
        return paperType;
    }

    public void setPaperType(int paperType) {
        this.paperType = paperType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getImageCustomer() {
        return imageCustomer;
    }

    public void setImageCustomer(String imageCustomer) {
        this.imageCustomer = imageCustomer;
    }

    public String getImageIdentityFront() {
        return imageIdentityFront;
    }

    public void setImageIdentityFront(String imageIdentityFront) {
        this.imageIdentityFront = imageIdentityFront;
    }

    public String getImageIdentityBack() {
        return imageIdentityBack;
    }

    public void setImageIdentityBack(String imageIdentityBack) {
        this.imageIdentityBack = imageIdentityBack;
    }
}

