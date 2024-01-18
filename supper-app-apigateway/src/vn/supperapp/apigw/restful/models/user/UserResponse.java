package vn.supperapp.apigw.restful.models.user;

import vn.supperapp.apigw.beans.MobileAppVersionInfo;
import vn.supperapp.apigw.beans.PagingResult;
import vn.supperapp.apigw.beans.UserInfo;
import vn.supperapp.apigw.db.dto.AppUser;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.BaseShopResponse;
import vn.supperapp.apigw.utils.ErrorCode;

import java.util.List;
import java.util.Map;

public class UserResponse extends BaseResponse {

    private UserInfo userInfo;
    private List<AppUser> userList;

    public UserResponse() {
    }

    public UserResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<AppUser> getUserList() {
        return userList;
    }

    public void setUserList(List<AppUser> userList) {
        this.userList = userList;
    }
}
