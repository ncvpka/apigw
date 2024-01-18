package vn.supperapp.apigw.restful.models.checkin;

import vn.supperapp.apigw.restful.models.BaseRequest;

/**
 * @author taink
 * @Date 20/06/2022
 * @see CheckInRequest
 */

public class CheckInRequest extends BaseRequest {
    private Long id;
    private String type;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
