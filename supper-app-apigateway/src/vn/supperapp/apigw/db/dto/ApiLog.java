package vn.supperapp.apigw.db.dto;
// Generated Feb 28, 2018 5:41:20 PM by Hibernate Tools 4.3.1

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "API_LOG")
public class ApiLog implements java.io.Serializable {
    
    public static final String TABLE_NAME = "API_LOG";
    public static final String SEQ_NAME = "API_LOG_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "api_log_generator")
    @SequenceGenerator(name = "api_log_generator", allocationSize = 1, sequenceName = "API_LOG_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "APP_DEVICE_ID")
    private Long appDeviceId;
    @Column(name = "APP_USER_ID")
    private Long appUserId;
    @Column(name = "TRANS_ID")
    private String transId;
    @Column(name = "MSISDN")
    private String msisdn;
    @Column(name = "CLIENT_TYPE")
    private String clientType;
    @Column(name = "CLIENT_VERSION")
    private String clientVersion;
    @Column(name = "API_PATH")
    private String apiPath;
    @Column(name = "CREATE_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(name = "START_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "END_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date endTime;
    @Column(name = "HTTP_METHOD")
    private String httpMethod;
    @Column(name = "HTTP_STATUS")
    private Integer httpStatus;
    @Column(name = "REQUEST_CONTENT")
    private String requestContent;
    @Column(name = "RESPONSE_CONTENT")
    private String responseContent;
    @Column(name = "API_STATUS")
    private Integer apiStatus;
    @Column(name = "API_CODE")
    private String apiCode;
    @Column(name = "API_MESSAGE")
    private String apiMessage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppDeviceId() {
        return appDeviceId;
    }

    public void setAppDeviceId(Long appDeviceId) {
        this.appDeviceId = appDeviceId;
    }

    public Long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
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

    public Integer getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(Integer apiStatus) {
        this.apiStatus = apiStatus;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public String getApiMessage() {
        return apiMessage;
    }

    public void setApiMessage(String apiMessage) {
        this.apiMessage = apiMessage;
    }
}
