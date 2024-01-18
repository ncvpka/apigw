package vn.supperapp.apigw.restful.models.user;

import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

public class UploadResponse extends BaseResponse {

    private String url;

    public UploadResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
