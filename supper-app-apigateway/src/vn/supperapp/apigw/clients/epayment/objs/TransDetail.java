/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.clients.epayment.objs;

import vn.supperapp.apigw.utils.enums.CurrencyCode;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author TruongLe
 */
public class TransDetail implements Serializable {


    public TransDetail() {
    }

    public TransDetail(Long transDetailId) {
        this.transDetailId = transDetailId;
    }

    private Long transDetailId;
    private String transDetailCode;
    private Long merchantId;
    private String merchantCode;
    private Long merchantServiceId;
    private String merchantServiceType;
    private Date transTime;
    private Long emServiceProvideId;
    private Long emServiceId;
    private String emServiceCode;
    private Long status;
    private Long paymentType;
    private String refId;
    private String transDescription;
    private Double transAmount;
    private String currencyCode;
    private Double transAmountConvert;
    private Double currencyExchangeRate;
    private String acceptPaymentCurrencyCode;
    private String discountType;
    private Double discountAmount;
    private Double commissionAmount;
    private Double transFee;
    private Double transTotalAmount;
    private Double transTotalAmountConvert;
    private String customerPhoneNumber;
    private String customerName;
    private String description;
    private Date paidTime;
    private Long paidTid;
    private String paidEmoneyAccount;
    private Double paidFee;
    private String paidChanel;
    private Double paidAmount;
    private String paidCurrencyCode;
    private Double paidTip;
    private Double paidTotalAmount;
    private Long emCoporationId;
    private String emCoporationMsisdn;
    private String refNo;
    private Long srcTransId;
    private Long srcRequestTid;
    private String txPaymentTokenId;
    private String paymentQrCode;
    private String statusDescription;
    private Double lastBalance;

    public Long getTransDetailId() {
        return transDetailId;
    }

    public void setTransDetailId(Long transDetailId) {
        this.transDetailId = transDetailId;
    }

    public String getTransDetailCode() {
        return transDetailCode;
    }

    public void setTransDetailCode(String transDetailCode) {
        this.transDetailCode = transDetailCode;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public Long getMerchantServiceId() {
        return merchantServiceId;
    }

    public void setMerchantServiceId(Long merchantServiceId) {
        this.merchantServiceId = merchantServiceId;
    }

    public String getMerchantServiceType() {
        return merchantServiceType;
    }

    public void setMerchantServiceType(String merchantServiceType) {
        this.merchantServiceType = merchantServiceType;
    }

    public Date getTransTime() {
        return transTime;
    }

    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    public Long getEmServiceProvideId() {
        return emServiceProvideId;
    }

    public void setEmServiceProvideId(Long emServiceProvideId) {
        this.emServiceProvideId = emServiceProvideId;
    }

    public Long getEmServiceId() {
        return emServiceId;
    }

    public void setEmServiceId(Long emServiceId) {
        this.emServiceId = emServiceId;
    }

    public String getEmServiceCode() {
        return emServiceCode;
    }

    public void setEmServiceCode(String emServiceCode) {
        this.emServiceCode = emServiceCode;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Long paymentType) {
        this.paymentType = paymentType;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getTransDescription() {
        return transDescription;
    }

    public void setTransDescription(String transDescription) {
        this.transDescription = transDescription;
    }

    public Double getCurrencyExchangeRate() {
        return currencyExchangeRate;
    }

    public void setCurrencyExchangeRate(Double currencyExchangeRate) {
        this.currencyExchangeRate = currencyExchangeRate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
    }

    public Long getPaidTid() {
        return paidTid;
    }

    public void setPaidTid(Long paidTid) {
        this.paidTid = paidTid;
    }

    public String getPaidEmoneyAccount() {
        return paidEmoneyAccount;
    }

    public void setPaidEmoneyAccount(String paidEmoneyAccount) {
        this.paidEmoneyAccount = paidEmoneyAccount;
    }

    public Double getPaidFee() {
        return paidFee;
    }

    public void setPaidFee(Double paidFee) {
        this.paidFee = paidFee;
    }

    public String getPaidChanel() {
        return paidChanel;
    }

    public void setPaidChanel(String paidChanel) {
        this.paidChanel = paidChanel;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getPaidCurrencyCode() {
        return paidCurrencyCode;
    }

    public void setPaidCurrencyCode(String paidCurrencyCode) {
        this.paidCurrencyCode = paidCurrencyCode;
    }

    public Double getPaidTip() {
        return paidTip;
    }

    public void setPaidTip(Double paidTip) {
        this.paidTip = paidTip;
    }

    public Double getPaidTotalAmount() {
        return paidTotalAmount;
    }

    public void setPaidTotalAmount(Double paidTotalAmount) {
        this.paidTotalAmount = paidTotalAmount;
    }

    public Double getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Double transAmount) {
        this.transAmount = transAmount;
    }

    public Double getTransAmountConvert() {
        return transAmountConvert;
    }

    public void setTransAmountConvert(Double transAmountConvert) {
        this.transAmountConvert = transAmountConvert;
    }

    public String getAcceptPaymentCurrencyCode() {
        return acceptPaymentCurrencyCode;
    }

    public void setAcceptPaymentCurrencyCode(String acceptPaymentCurrencyCode) {
        this.acceptPaymentCurrencyCode = acceptPaymentCurrencyCode;
    }

    public String getTxPaymentTokenId() {
        return txPaymentTokenId;
    }

    public void setTxPaymentTokenId(String txPaymentTokenId) {
        this.txPaymentTokenId = txPaymentTokenId;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(Double commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public Double getTransFee() {
        return transFee;
    }

    public void setTransFee(Double transFee) {
        this.transFee = transFee;
    }

    public Double getTransTotalAmount() {
        return transTotalAmount;
    }

    public void setTransTotalAmount(Double transTotalAmount) {
        this.transTotalAmount = transTotalAmount;
    }

    public Double getTransTotalAmountConvert() {
        return transTotalAmountConvert;
    }

    public void setTransTotalAmountConvert(Double transTotalAmountConvert) {
        this.transTotalAmountConvert = transTotalAmountConvert;
    }

    public String getPaymentQrCode() {
        return paymentQrCode;
    }

    public void setPaymentQrCode(String paymentQrCode) {
        this.paymentQrCode = paymentQrCode;
    }

    public Long getEmCoporationId() {
        return emCoporationId;
    }

    public void setEmCoporationId(Long emCoporationId) {
        this.emCoporationId = emCoporationId;
    }

    public String getEmCoporationMsisdn() {
        return emCoporationMsisdn;
    }

    public void setEmCoporationMsisdn(String emCoporationMsisdn) {
        this.emCoporationMsisdn = emCoporationMsisdn;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public Double getLastBalance() {
        return lastBalance;
    }

    public void setLastBalance(Double lastBalance) {
        this.lastBalance = lastBalance;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public Long getSrcTransId() {
        return srcTransId;
    }

    public void setSrcTransId(Long srcTransId) {
        this.srcTransId = srcTransId;
    }

    public Long getSrcRequestTid() {
        return srcRequestTid;
    }

    public void setSrcRequestTid(Long srcRequestTid) {
        this.srcRequestTid = srcRequestTid;
    }

    public TransDetail buildResponse() {
        TransDetail tmp = new TransDetail(this.transDetailId);
        tmp.setTransAmount(this.transAmount);
        CurrencyCode ccy = CurrencyCode.get(this.currencyCode);
        tmp.setCurrencyCode(ccy != null ? ccy.description() : null);
        tmp.setTransDetailCode(this.transDetailCode);
        tmp.setMerchantCode(this.merchantCode);
        tmp.setMerchantServiceType(this.merchantServiceType);
        tmp.setTransTime(this.transTime);
        tmp.setStatus(this.status);
        tmp.setPaymentType(this.paymentType);
        tmp.setRefId(this.refId);
        tmp.setTransDescription(this.transDescription);
        tmp.setTransAmountConvert(this.transAmountConvert);
        tmp.setCurrencyExchangeRate(this.currencyExchangeRate);
        tmp.setDiscountType(discountType);
        tmp.setDiscountAmount(discountAmount);
        tmp.setCommissionAmount(commissionAmount);
        tmp.setTransFee(transFee);
        tmp.setTransTotalAmount(transTotalAmount);
        tmp.setTransTotalAmountConvert(transTotalAmountConvert);
        tmp.setPaymentQrCode(paymentQrCode);
        tmp.setCustomerName(customerName);

        CurrencyCode ccyAccept = CurrencyCode.get(this.acceptPaymentCurrencyCode);
        tmp.setAcceptPaymentCurrencyCode(ccyAccept != null ? ccyAccept.description() : null);
        tmp.setCustomerPhoneNumber(this.customerPhoneNumber);
        tmp.setDescription(this.description);
        tmp.setPaidTime(this.paidTime);
        tmp.setPaidTid(this.paidTid);
        tmp.setPaidEmoneyAccount(paidEmoneyAccount);
        tmp.setPaidFee(paidFee);
        tmp.setPaidChanel(paidChanel);
        tmp.setPaidAmount(paidAmount);

        CurrencyCode ccyPaid = CurrencyCode.get(this.paidCurrencyCode);
        tmp.setPaidCurrencyCode(ccyPaid != null ? ccyPaid.description() : null);
        tmp.setPaidTip(paidTip);
        tmp.setPaidTotalAmount(paidTotalAmount);
        tmp.setLastBalance(this.lastBalance);
        tmp.setRefNo(this.refNo);

        tmp.setSrcRequestTid(srcRequestTid);
        tmp.setSrcTransId(srcTransId);
        return tmp;
    }

    public TransDetail buildCallbackDataResponse() {
        TransDetail tmp = new TransDetail(this.transDetailId);
        tmp.setStatus(status);
        tmp.setRefId(refId);
        tmp.setPaidTid(paidTid);
        tmp.setPaidFee(paidFee);
        tmp.setPaidAmount(paidAmount);
        CurrencyCode ccyPaid = CurrencyCode.get(paidCurrencyCode);
        tmp.setPaidCurrencyCode(ccyPaid != null ? ccyPaid.description() : null);
        tmp.setPaidTotalAmount(paidTotalAmount);
        tmp.setRefNo(this.refNo);

        return tmp;
    }
}
