package vn.supperapp.apigw.clients.epayment.objs;

import com.google.gson.Gson;
import org.openide.util.Exceptions;

public class EPaymentRequest {

    private Long transDetailId;
    private int paymentType; //0: to eMoney; 1: to Non-eMoney
    private Double transAmount;
    private String currency; //USD; KHR; BOTH
    private String customerPhoneNumber; //Receiver eMoney account
    private String refId; //Required and Unique
    private String content; //Content for eMoney to send SMS
    private String txPaymentTokenId;
    private String refNo;

    public Long getTransDetailId() {
        return transDetailId;
    }

    public void setTransDetailId(Long transDetailId) {
        this.transDetailId = transDetailId;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public Double getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Double transAmount) {
        this.transAmount = transAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTxPaymentTokenId() {
        return txPaymentTokenId;
    }

    public void setTxPaymentTokenId(String txPaymentTokenId) {
        this.txPaymentTokenId = txPaymentTokenId;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String toLogString() {
        try {
            Gson gson = new Gson();
            String req = gson.toJson(this);
            if (req != null) {
                req = req.replaceAll("(\"pin\":\"[0-9]{6}\")", "\"pin\":\"******\"");
            }
            return req;
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

        return "";
    }
}
