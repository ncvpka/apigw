package vn.supperapp.apigw.wso2.objs;

import com.google.gson.annotations.SerializedName;

public class Invite {

    @SerializedName("phoneNumber")
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
