package vn.supperapp.apigw.beans;

import vn.supperapp.apigw.db.dto.AppDevice;
import vn.supperapp.apigw.restful.models.BaseResponse;

import java.util.Date;

public class ApiLogData {
    private String path;
    private String requestContent;
    private String responseContent;
    private Date startTime;
    private Date endTime;
    private String httpMethod;
    private int httpStatus;

    private BaseResponse responseObj;
    private UserLoggedInfo loggedInfo;
    private AppDevice appDevice;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public BaseResponse getResponseObj() {
        return responseObj;
    }

    public void setResponseObj(BaseResponse responseObj) {
        this.responseObj = responseObj;
    }

    public UserLoggedInfo getLoggedInfo() {
        return loggedInfo;
    }

    public void setLoggedInfo(UserLoggedInfo loggedInfo) {
        this.loggedInfo = loggedInfo;
    }

    public AppDevice getAppDevice() {
        return appDevice;
    }

    public void setAppDevice(AppDevice appDevice) {
        this.appDevice = appDevice;
    }
}
