package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "TRANS_PARTNER")
public class TransPartner implements Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_partner_generator")
    @SequenceGenerator(name = "trans_partner_generator", allocationSize = 1, sequenceName = "TRANS_PARTNER_SEQ")
    private Long id;

    @Column(name = "TRANS_ID")
    private String transId;

    @Column(name = "REQUEST_ID")
    private String requestId;

    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @Column(name = "SERVICE_CODE")
    private String serviceCode;

    @Column(name = "TRANS_ID_RESPONSE_API_GW")
    private String transIdResponseApiGw;

    @Column(name = "PARTNER_CODE")
    private String partnerCode;

    @Column(name = "PARTNER_ID")
    private String partnerId;

    @Column(name = "PARTNER_URL")
    private String partnerUrl;

    @Column(name = "PARTNER_API_NAME")
    private String partnerApiName;

    @Column(name = "PARTNER_API_REQUEST")
    private String partnerApiRequest;

    @Column(name = "PARTNER_API_RESPONSE")
    private String partnerApiResponse;

    @Column(name = "PARTNER_API_ERROR_CODE")
    private String partnerApiErrorCode;

    @Column(name = "PARTNER_API_DESCRIPTION")
    private String partnerApiDescription;

    @Column(name = "PARTNER_API_STATUS")
    private Integer partnerApiStatus;

    @Column(name = "PARTNER_EXCEPTION_LOG")
    private String partnerExceptionLog;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "AMOUNT")
    private String amount;

    @Column(name = "FEE")
    private String fee;

    @Column(name = "DISCOUNT")
    private String discount;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "TRANS_ID_BY_TICKET")
    private String transIdByTicket;

    @Column(name = "ORDER_ID")
    private String orderId;

    @Column(name = "LIST_TICKET_ID")
    private String listTicketId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getTransIdResponseApiGw() {
        return transIdResponseApiGw;
    }

    public void setTransIdResponseApiGw(String transIdResponseApiGw) {
        this.transIdResponseApiGw = transIdResponseApiGw;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerUrl() {
        return partnerUrl;
    }

    public void setPartnerUrl(String partnerUrl) {
        this.partnerUrl = partnerUrl;
    }

    public String getPartnerApiName() {
        return partnerApiName;
    }

    public void setPartnerApiName(String partnerApiName) {
        this.partnerApiName = partnerApiName;
    }

    public String getPartnerApiRequest() {
        return partnerApiRequest;
    }

    public void setPartnerApiRequest(String partnerApiRequest) {
        this.partnerApiRequest = partnerApiRequest;
    }

    public String getPartnerApiResponse() {
        return partnerApiResponse;
    }

    public void setPartnerApiResponse(String partnerApiResponse) {
        this.partnerApiResponse = partnerApiResponse;
    }

    public String getPartnerApiErrorCode() {
        return partnerApiErrorCode;
    }

    public void setPartnerApiErrorCode(String partnerApiErrorCode) {
        this.partnerApiErrorCode = partnerApiErrorCode;
    }

    public String getPartnerApiDescription() {
        return partnerApiDescription;
    }

    public void setPartnerApiDescription(String partnerApiDescription) {
        this.partnerApiDescription = partnerApiDescription;
    }

    public Integer getPartnerApiStatus() {
        return partnerApiStatus;
    }

    public void setPartnerApiStatus(Integer partnerApiStatus) {
        this.partnerApiStatus = partnerApiStatus;
    }

    public String getPartnerExceptionLog() {
        return partnerExceptionLog;
    }

    public void setPartnerExceptionLog(String partnerExceptionLog) {
        this.partnerExceptionLog = partnerExceptionLog;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getTransIdByTicket() {
        return transIdByTicket;
    }

    public void setTransIdByTicket(String transIdByTicket) {
        this.transIdByTicket = transIdByTicket;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getListTicketId() {
        return listTicketId;
    }

    public void setListTicketId(String listTicketId) {
        this.listTicketId = listTicketId;
    }
}
