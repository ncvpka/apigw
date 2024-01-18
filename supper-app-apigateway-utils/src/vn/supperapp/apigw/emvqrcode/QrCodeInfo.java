package vn.supperapp.apigw.emvqrcode;

import vn.supperapp.apigw.emvqrcode.consumer.QrCodeConsumerInfo;
import vn.supperapp.apigw.emvqrcode.merchant.QrCodeMerchantInfo;
import vn.supperapp.apigw.utils.enums.QrCodeType;

public class QrCodeInfo {
    private int qrType = QrCodeType.UNKNOWN.value();
    private String qrTypeCode = QrCodeType.UNKNOWN.code();
    private String qrCode;

    private QrCodeConsumerInfo qrConsumerInfo;
    private QrCodeMerchantInfo qrMerchantInfo;

    public QrCodeInfo() {
    }

    public QrCodeInfo(QrCodeType type) {
        this.qrType = type.value();
        this.qrTypeCode = type.code();
    }

    public int getQrType() {
        return qrType;
    }

    public void setQrType(int qrType) {
        this.qrType = qrType;
    }

    public String getQrTypeCode() {
        return qrTypeCode;
    }

    public void setQrTypeCode(String qrTypeCode) {
        this.qrTypeCode = qrTypeCode;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public QrCodeConsumerInfo getQrConsumerInfo() {
        return qrConsumerInfo;
    }

    public void setQrConsumerInfo(QrCodeConsumerInfo qrConsumerInfo) {
        this.qrConsumerInfo = qrConsumerInfo;
    }

    public QrCodeMerchantInfo getQrMerchantInfo() {
        return qrMerchantInfo;
    }

    public void setQrMerchantInfo(QrCodeMerchantInfo qrMerchantInfo) {
        this.qrMerchantInfo = qrMerchantInfo;
    }
}
