package vn.supperapp.apigw.wso2.objs;

import com.google.gson.annotations.SerializedName;


public class InviteResponse {

    @SerializedName("phonenumberEwalletCollection")
    InviteCollection inviteCollection;

    public InviteCollection getInviteCollection() {
        return inviteCollection;
    }

    public void setInviteCollection(InviteCollection inviteCollection) {
        this.inviteCollection = inviteCollection;
    }
}
