package vn.supperapp.apigw.restful.models.register;

import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

/**
 * @author TruongLe
 * @Date 16/02/2022
 * @see RegisterResponse
 */

public class RegisterResponse extends BaseResponse {
    private String msisdn;
    private String title;
    private String content;
    private String subContent;

    public RegisterResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubContent() {
        return subContent;
    }

    public void setSubContent(String subContent) {
        this.subContent = subContent;
    }
}
