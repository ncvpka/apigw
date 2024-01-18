package vn.supperapp.apigw.restful.models.language;

import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

public class LanguageResponse extends BaseResponse {
    private String language;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LanguageResponse(ErrorCode error, String language) {
        super(error, language);
    }
}
