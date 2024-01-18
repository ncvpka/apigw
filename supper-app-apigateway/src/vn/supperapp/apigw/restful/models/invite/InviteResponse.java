package vn.supperapp.apigw.restful.models.invite;

import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

public class InviteResponse extends BaseResponse {
    private String listPhoneNumberWithWallet;
    private String listInviteNumber;
    private String listNotAccountEWallet;
    private String setReferralLink;

    public InviteResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public String getListPhoneNumberWithWallet() {
        return listPhoneNumberWithWallet;
    }

    public void setListPhoneNumberWithWallet(String listPhoneNumberWithWallet) {
        this.listPhoneNumberWithWallet = listPhoneNumberWithWallet;
    }

    public String getListInviteNumber() {
        return listInviteNumber;
    }

    public void setListInviteNumber(String listInviteNumber) {
        this.listInviteNumber = listInviteNumber;
    }

    public String getListNotAccountEWallet() {
        return listNotAccountEWallet;
    }

    public void setListNotAccountEWallet(String listNotAccountEWallet) {
        this.listNotAccountEWallet = listNotAccountEWallet;
    }

    public String getSetReferralLink() {
        return setReferralLink;
    }

    public void setSetReferralLink(String setReferralLink) {
        this.setReferralLink = setReferralLink;
    }
}
