package vn.supperapp.apigw.restful.models.hr;

import vn.supperapp.apigw.restful.models.BaseRequest;

public class HrCommonRequest  extends BaseRequest {
    private Long id;
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
