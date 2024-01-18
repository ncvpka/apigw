package vn.supperapp.apigw.services;

import com.emv.qrcode.decoder.cpm.DecoderCpm;
import com.emv.qrcode.decoder.mpm.DecoderMpm;
import com.emv.qrcode.model.cpm.ConsumerPresentedMode;
import com.emv.qrcode.model.mpm.MerchantPresentedMode;
import vn.supperapp.apigw.emvqrcode.QrCodeInfo;
import vn.supperapp.apigw.emvqrcode.consumer.QrCodeConsumerInfo;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.enums.QrCodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EMVQrCodeService {
    private static Logger logger = LoggerFactory.getLogger(EMVQrCodeService.class);;

    public static final String applicationName = "Natcash";
    //version: 0.0.1 = 00 00 01
    public static final String applicationVersion = "000001";


    private static volatile EMVQrCodeService instance;
    private static Object mutex = new Object();

    public EMVQrCodeService() {
    }

    public static EMVQrCodeService shared() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new EMVQrCodeService();
                }
            }
        }
        return instance;
    }

    public QrCodeInfo decodeQrCode(String qrCode) {
        try {
            if (CommonUtils.isNullOrEmpty(qrCode)) {
                return null;
            }

            MerchantPresentedMode mpm = null;
            try {
                mpm = DecoderMpm.decode(qrCode, MerchantPresentedMode.class);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("# - Cannot decode MPM");
            }

            ConsumerPresentedMode cpm = null;
            if (mpm == null) {
                try {
                    cpm = DecoderCpm.decode(qrCode, ConsumerPresentedMode.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("# - Cannot decode CPM");
                }
            }

            if (mpm == null && cpm == null) {
                return null;
            }

            QrCodeInfo qrInfo = null;
            if (mpm != null) {
                logger.info("# - QR Code is Merchant - Start to parse Merchant object");
                qrInfo = new QrCodeInfo(QrCodeType.MERCHANT);
            } else {
                logger.info("# - QR Code is Consumer - Start to parse Consumer object");
                qrInfo = new QrCodeInfo(QrCodeType.CONSUMER);

                QrCodeConsumerInfo consumer = new QrCodeConsumerInfo(qrCode, cpm);
                qrInfo.setQrConsumerInfo(consumer);
            }

            return qrInfo;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#decodeQrCode - EXCEPTION: ", ex);
        }
        return null;
    }

    public QrCodeConsumerInfo generateConsumerQrCode(String accountNumber, String PAN, String phoneNumber, String cardHolderName, String languagePreference) {
        try {
            QrCodeConsumerInfo consumer = new QrCodeConsumerInfo();

            consumer.setApplicationLabel(applicationName);
            consumer.setApplicationVersionNumber(applicationVersion);
            consumer.setPaymentAccountReference(accountNumber);
            consumer.setIssuerUrl(phoneNumber);
            consumer.setCardHolderName(cardHolderName);
            consumer.setLanguagePreference(languagePreference);
            if (PAN != null && PAN.length() >= 4) {
                consumer.setLast4DigitsPAN(PAN.substring(PAN.length() - 4, PAN.length()));
            }

            consumer.buildBase64QrCode();
            return consumer;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#generateConsumerQrCode - EXCEPTION: ", ex);
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            QrCodeConsumerInfo t = EMVQrCodeService.shared().generateConsumerQrCode("509888555666", "1234", "0888288688", "Louis Dang", "English");
            System.out.println(t.getQrCode());

            QrCodeInfo qrCodeInfo = EMVQrCodeService.shared().decodeQrCode("hQVDUFYwMWEPUAdOYXRjYXNonwgDAAABYjhfIApMb3VpcyBEYW5nXy0HRW5nbGlzaJ8kDDUwOTg4ODU1NTY2Np8lAhI0X1AKMDg4ODI4ODY4OA==");
            System.out.println("TEST");
//            String tmp = "hQVDUFYwMWETTwegAAAAVVVVUAhQcm9kdWN0MWETTwegAAAAZmZmUAhQcm9kdWN0MmJJWggSNFZ4kBI0WF8gDkNBUkRIT0xERVIvRU1WXy0IcnVlc2RlZW5kIZ8QBwYBCgMAAACfJghYT9OF+iNLzJ82AgABnzcEbVjvEw==";
//            final ConsumerPresentedMode merchantPresentedMode1 = DecoderCpm.decode(tmp, ConsumerPresentedMode.class);
//
//            Gson gson = new Gson();
//            System.out.println(gson.toJson(merchantPresentedMode1));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
