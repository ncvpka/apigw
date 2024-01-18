package vn.supperapp.apigw.wso2.objs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InviteCollection {

    @SerializedName("phonenumberEwallet")
    private List<Invite> invites;

    public List<Invite> getInvites() {
        return invites;
    }

    public void setInvites(List<Invite> invites) {
        this.invites = invites;
    }
}
