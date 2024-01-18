package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "TRANS_EWALLET")
public class TransEWallet implements Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_ewallet_generator")
    @SequenceGenerator(name = "trans_ewallet_generator", allocationSize = 1, sequenceName = "TRANS_EWALLET_SEQ")
    private Long id;

    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @Column(name = "SERVICE_CODE")
    private String serviceCode;

    @Column(name = "SERVICE_NAME")
    private String serviceName;

    @Column(name = "PARTNER_CODE")
    private String partnerCode;

    @Column(name = "PROCESS_CODE")
    private String processCode;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "AMOUNT")
    private String amount;

    @Column(name = "FEE")
    private String fee;

    @Column(name = "DISCOUNT")
    private String discount;

    @Column(name = "EWALLET_RESPONSE_CODE")
    private String eWalletResponseCode;

    @Column(name = "EWALLET_RESPONSE_DESCIPTION")
    private String eWalletResponseDescription;

    @Column(name = "EWALLET_TRANSACTION_DESCIPTION")
    private String eWalletTransactionDescription;

    @Column(name = "EWALLET_REQUEST")
    private String eWalletRequest;

    @Column(name = "EWALLET_RESPONSE")
    private String eWalletResponse;

    @Column(name = "EWALLET_EXCEPTION_LOG")
    private String eWalletExceptionLog;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "TRANS_ID")
    private String transId;

    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "TRANS_TYPE")
    private String transType;

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
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

    public String geteWalletResponseCode() {
        return eWalletResponseCode;
    }

    public void seteWalletResponseCode(String eWalletResponseCode) {
        this.eWalletResponseCode = eWalletResponseCode;
    }

    public String geteWalletResponseDescription() {
        return eWalletResponseDescription;
    }

    public void seteWalletResponseDescription(String eWalletResponseDescription) {
        this.eWalletResponseDescription = eWalletResponseDescription;
    }

    public String geteWalletTransactionDescription() {
        return eWalletTransactionDescription;
    }

    public void seteWalletTransactionDescription(String eWalletTransactionDescription) {
        this.eWalletTransactionDescription = eWalletTransactionDescription;
    }

    public String geteWalletRequest() {
        return eWalletRequest;
    }

    public void seteWalletRequest(String eWalletRequest) {
        this.eWalletRequest = eWalletRequest;
    }

    public String geteWalletResponse() {
        return eWalletResponse;
    }

    public void seteWalletResponse(String eWalletResponse) {
        this.eWalletResponse = eWalletResponse;
    }

    public String geteWalletExceptionLog() {
        return eWalletExceptionLog;
    }

    public void seteWalletExceptionLog(String eWalletExceptionLog) {
        this.eWalletExceptionLog = eWalletExceptionLog;
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

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }
}
