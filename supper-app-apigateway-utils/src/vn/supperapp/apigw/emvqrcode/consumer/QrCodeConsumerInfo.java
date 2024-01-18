package vn.supperapp.apigw.emvqrcode.consumer;

import com.emv.qrcode.model.cpm.ApplicationTemplate;
import com.emv.qrcode.model.cpm.CommonDataTemplate;
import com.emv.qrcode.model.cpm.ConsumerPresentedMode;
import com.emv.qrcode.model.cpm.PayloadFormatIndicator;

import java.util.List;

public class QrCodeConsumerInfo {
    private String applicationDefinitionFileName;       //4F
    private String applicationLabel;                    //50
    private String applicationVersionNumber;            //9F08
    private String applicationPAN;                      //5A - Card holder Account number
    private String cardHolderName;                      //5F20
    private String languagePreference;                  //5F2D
    private String issuerUrl;                           //5F50 - Phone or email
    private String tokenRequestorId;                    //9F19
    private String paymentAccountReference;             //9F24
    private String last4DigitsPAN;                      //9F25

    private String qrCode;

    public QrCodeConsumerInfo() {
    }

    public QrCodeConsumerInfo(String qrCode, ConsumerPresentedMode cpm) {
        try {
            this.qrCode = qrCode;
            List<ApplicationTemplate> lstApps = cpm.getApplicationTemplates();
            if (lstApps != null && !lstApps.isEmpty()) {
                ApplicationTemplate at = lstApps.get(0);
                this.applicationDefinitionFileName = at.getApplicationDefinitionFileName() != null ? at.getApplicationDefinitionFileName().getStringValue() : null;
                this.applicationLabel = at.getApplicationLabel() != null ? at.getApplicationLabel().getStringValue() : null;
                this.applicationVersionNumber = at.getApplicationVersionNumber() != null ? at.getApplicationVersionNumber().getStringValue() : null;

            }

            CommonDataTemplate cdt = cpm.getCommonDataTemplate();
            if (cdt != null) {
                this.applicationPAN = cdt.getApplicationPAN() != null ? cdt.getApplicationPAN().getStringValue() : null;
                this.cardHolderName = cdt.getCardholderName() != null ? cdt.getCardholderName().getStringValue() : null;
                this.languagePreference = cdt.getLanguagePreference() != null ? cdt.getLanguagePreference().getStringValue() : null;
                this.paymentAccountReference = cdt.getPaymentAccountReference() != null ? cdt.getPaymentAccountReference().getStringValue() : null;
                this.issuerUrl = cdt.getIssuerURL() != null ? cdt.getIssuerURL().getStringValue() : null;
                this.tokenRequestorId = cdt.getTokenRequestorID() != null ? cdt.getTokenRequestorID().getStringValue() : null;
                this.last4DigitsPAN = cdt.getLast4DigitsOfPAN() != null ? cdt.getLast4DigitsOfPAN().getStringValue() : null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getApplicationDefinitionFileName() {
        return applicationDefinitionFileName;
    }

    public void setApplicationDefinitionFileName(String applicationDefinitionFileName) {
        this.applicationDefinitionFileName = applicationDefinitionFileName;
    }

    public String getApplicationLabel() {
        return applicationLabel;
    }

    public void setApplicationLabel(String applicationLabel) {
        this.applicationLabel = applicationLabel;
    }

    public String getApplicationVersionNumber() {
        return applicationVersionNumber;
    }

    public void setApplicationVersionNumber(String applicationVersionNumber) {
        this.applicationVersionNumber = applicationVersionNumber;
    }

    public String getApplicationPAN() {
        return applicationPAN;
    }

    public void setApplicationPAN(String applicationPAN) {
        this.applicationPAN = applicationPAN;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getLanguagePreference() {
        return languagePreference;
    }

    public void setLanguagePreference(String languagePreference) {
        this.languagePreference = languagePreference;
    }

    public String getIssuerUrl() {
        return issuerUrl;
    }

    public void setIssuerUrl(String issuerUrl) {
        this.issuerUrl = issuerUrl;
    }

    public String getTokenRequestorId() {
        return tokenRequestorId;
    }

    public void setTokenRequestorId(String tokenRequestorId) {
        this.tokenRequestorId = tokenRequestorId;
    }

    public String getPaymentAccountReference() {
        return paymentAccountReference;
    }

    public void setPaymentAccountReference(String paymentAccountReference) {
        this.paymentAccountReference = paymentAccountReference;
    }

    public String getLast4DigitsPAN() {
        return last4DigitsPAN;
    }

    public void setLast4DigitsPAN(String last4DigitsPAN) {
        this.last4DigitsPAN = last4DigitsPAN;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public void buildBase64QrCode() {
        try {
            ConsumerPresentedMode cpm = new ConsumerPresentedMode();
            ApplicationTemplate at = new ApplicationTemplate();
            at.setApplicationLabel(this.applicationLabel);
            at.setApplicationVersionNumber(this.applicationVersionNumber);

            CommonDataTemplate commonDataTemplate = new CommonDataTemplate();
            commonDataTemplate.setCardholderName(this.cardHolderName);
            commonDataTemplate.setLanguagePreference(this.languagePreference);
            commonDataTemplate.setPaymentAccountReference(this.paymentAccountReference);
            commonDataTemplate.setLast4DigitsOfPAN(this.last4DigitsPAN);
            commonDataTemplate.setTokenRequestorID(this.tokenRequestorId);
            commonDataTemplate.setIssuerURL(this.issuerUrl);

            cpm.setPayloadFormatIndicator(new PayloadFormatIndicator());
            cpm.addApplicationTemplate(at);
            cpm.setCommonDataTemplate(commonDataTemplate);

            this.qrCode = cpm.toBase64();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
