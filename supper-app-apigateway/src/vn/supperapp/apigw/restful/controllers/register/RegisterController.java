package vn.supperapp.apigw.restful.controllers.register;

import com.viettel.ewallet.utils.iso.msg.IsoObject;
import vn.supperapp.apigw.beans.RegisterAccountCachedInfo;
import vn.supperapp.apigw.configs.AppConfigurations;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.RegisterDAO;
import vn.supperapp.apigw.db.dao.UserDAO;
import vn.supperapp.apigw.db.dto.AppUser;
import vn.supperapp.apigw.db.dto.Register;
import vn.supperapp.apigw.restful.controllers.BaseController;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.register.RegisterRequest;
import vn.supperapp.apigw.restful.models.register.RegisterResponse;
import vn.supperapp.apigw.utils.Constants;
import vn.supperapp.apigw.utils.ErrorCode;
import vn.supperapp.apigw.utils.Gender;
import vn.supperapp.apigw.utils.PaperType;
import vn.supperapp.apigw.messaging.MessagingClient;
import vn.supperapp.apigw.messaging.beans.Message;
import vn.supperapp.apigw.messaging.beans.MessageContent;
import vn.supperapp.apigw.services.OtpService;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.DateTimeUtils;
import vn.supperapp.apigw.utils.LanguageUtils;
import vn.supperapp.apigw.utils.enums.ChannelType;
import vn.supperapp.apigw.utils.enums.Language;
import vn.supperapp.apigw.utils.enums.TransactionType;
import org.hibernate.Session;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * @author TruongLe
 * @Date 16/02/2022
 * @see RegisterController
 */

@Path("/register")
@RsDefaultFilterMapping
@RsResponseFilterMapping
public class RegisterController extends BaseController {
    private RegisterDAO registerDAO;

    public RegisterController() {
        this.logger = LoggerFactory.getLogger(RegisterController.class);
        registerDAO = new RegisterDAO();
    }

    @POST
    @Path("/check")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse registerCheck(@NotNull RegisterRequest request) {
        logger.info("#registerCheck - Start: " + request.toLogString());
        RegisterResponse response = null;

        try {

            if (CommonUtils.isNullOrEmpty(request.getMsisdn())) {
                logger.info("#registerCheck - Validate: isdn is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            String accountNumber = CommonUtils.getMsisdn(request.getMsisdn().trim());
            if (!CommonUtils.isPhoneNumber(accountNumber)) {
                logger.info("#registerCheck - Validate: accountNumber invalid");
                return BaseResponse.build(ErrorCode.ERR_ACCOUNT_INVALID, language);
            }

            Session session = DbSessionMgt.shared().getSession();
            AppUser acc = new UserDAO().getAppUserByMsisdn(session, accountNumber);

            if (acc != null && Constants.ACCOUNT_STATE_ACTIVE.equals(acc.getStatus())) {
                logger.info("#registerCheck - TX: account number exist");
                return BaseResponse.build(ErrorCode.ERR_ACCOUNT_EXISTS, language);
            }

            if (acc != null && Constants.ACCOUNT_STATE_BLOCKED.equals(acc.getStatus())) {
                logger.info("#registerCheck - TX: account number blocked");
                return BaseResponse.build(acc.getStatus().toString(), language);
            }

            logger.info("#registerCheck - Response: Init response");
            response = new RegisterResponse(ErrorCode.SUCCESS, language);
            String transId = CommonUtils.generateUUID();
            response.setTransId(transId);

            logger.info("#registerCheck - Save data into cache");
            OtpService otp = new OtpService(accountNumber, AppConfigurations.shared().getOtpConfig());
            otp.setData("registerCheck");
            otp.setTransId(transId);
            setOtpTransaction(otp);

            return response;

        } catch (Exception e) {
            logExceptions("#registerCheck - Error: ", e);
            e.printStackTrace();
        }
        return BaseResponse.commonError(language);
    }

    @POST
    @Path("/confirm")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse registerConfirm(@NotNull RegisterRequest request) {
        logger.info("#registerConfirm - Start: " + request.toLogString());
        RegisterResponse response = null;

        try {

            if (!verifySignature(request.getSignature(), request.getTransId(), request.getMsisdn())) {
                logger.info("#cashOutConfirm - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }

            logger.info("#registerConfirm - Validate data");
            if (CommonUtils.isNullOrEmpty(request.getMsisdn())) {
                logger.info("#registerConfirm - Validate: msisdn is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            String accountNumber = CommonUtils.getMsisdn(request.getMsisdn().trim());
            if (!CommonUtils.isPhoneNumber(accountNumber)) {
                logger.info("#registerConfirm - Validate: accountNumber invalid");
                return BaseResponse.build(ErrorCode.ERR_ACCOUNT_INVALID, language);
            }

            if (CommonUtils.isNullOrEmpty(request.getFullName())) {
                logger.info("#registerConfirm - Validate: fullName is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            if (CommonUtils.isNullOrEmpty(request.getDob())) {
                logger.info("#registerConfirm - Validate: dob is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            String dob = CommonUtils.convertDateBirthDay(request.getDob());
            if (CommonUtils.isNullOrEmpty(dob)) {
                logger.info("#registerConfirm - dob not enough age");
                return BaseResponse.build(ErrorCode.NOT_ENOUGH_AGE, language);
            }

            if (CommonUtils.isNullOrEmpty(request.getIdNo())) {
                logger.info("#registerConfirm - Validate: idNo is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            String pin = request.getPass();
            if (!CommonUtils.validatePin(pin) || CommonUtils.isNullOrEmpty(pin)) {
                logger.info("#registerConfirm - Validate: PIN null or invalid");
                return BaseResponse.build(ErrorCode.ERR_PIN_INVALID, language);
            }

            //TODO: get data from cached
            OtpService otpCached = getOtpTransaction(request.getTransId());
            if (otpCached == null) {
                logger.info("#registerConfirm: Transaction invalid or expired");
                return BaseResponse.build(ErrorCode.ERR_TRANSACTION_EXPIRED, language);
            }

            String desTransaction = otpCached.getData(String.class);

            logger.info("#registerConfirm - Response: Init response");
            response = new RegisterResponse(ErrorCode.SUCCESS, language);
            String transId = CommonUtils.generateUUID();
            response.setRequireOtp(true);
            response.setTransId(transId);

            logger.info("#registerConfirm - Set data into cached");
            RegisterAccountCachedInfo cachedInfo = new RegisterAccountCachedInfo();
            cachedInfo.setAccountNumber(accountNumber);
            cachedInfo.setFullName(request.getFullName());
            cachedInfo.setPass(pin);
            cachedInfo.setGender(request.getGender());
            cachedInfo.setIdNo(request.getIdNo());
            cachedInfo.setDob(dob);
            cachedInfo.setDobStr(request.getDob());
            cachedInfo.setPaperType(getPaperType(String.valueOf(request.getPaperType())));
            cachedInfo.setPaperTypeInt(request.getPaperType());
            cachedInfo.setDesTransaction(desTransaction);
            cachedInfo.setReferenceNumber(request.getReferenceNumber());

            logger.info("#registerConfirm - send otp sms notify");
            OtpService otp = new OtpService(accountNumber, AppConfigurations.shared().getOtpConfig());
            otp.setTransId(transId);
            otp.setData(cachedInfo);
            otp.setOtpMessage(sendOtpRegisterToCustomer(otp, request.getMsisdn()));
            otp.startCountdown(false);
            setOtpTransaction(otp);
            String temp = otp.getOtpMessage();
            temp = temp.replace(otp.getOtp(), "******");
            response.setOtpMessage(temp);

            return response;

        } catch (Exception e) {
            logExceptions("#registerConfirm - Error: ", e);
            e.printStackTrace();
        }
        return BaseResponse.commonError(language);
    }

    @POST
    @Path("/verify")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse registerConfirmOtp(@NotNull RegisterRequest request) {
        logger.info("#registerConfirm - Start: " + request.toLogString());
        RegisterResponse response = null;
        Session session = null;

        try {

            if (!verifySignature(request.getSignature(), request.getTransId(), request.getOtp())) {
                logger.info("#transferVerify - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }

            logger.info("#registerConfirm - Validate data");
            if (CommonUtils.isNullOrEmpty(request.getOtp())) {
                logger.info("#registerConfirm - Validate: otp is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            if (CommonUtils.isNullOrEmpty(request.getTransId())) {
                logger.info("#registerConfirm - Validate: otp is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            logger.info("#getListMessages Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#getListMessages Check session");
            if (!checkDbSession(session)) {
                logger.info("#getListMessages DB Connection error");
                return BaseResponse.commonError(language);
            }

            OtpService otp = getOtpTransaction(request.getTransId());
            if (otp == null) {
                logger.info("#registerConfirm: OTP Trans invalid or expired");
                return BaseResponse.build(ErrorCode.ERR_TRANSACTION_EXPIRED, language);
            }

            if (!otp.verifyOtp(request.getOtp())) {
                logger.info("#registerConfirmOtp - VALIDATE: OTP Transaction was expired");
                return BaseResponse.build(ErrorCode.ERR_OTP_ID_INVALID, language);
            }

            logger.info("#registerConfirmOtp - get RegisterAccountCachedInfo data");
            RegisterAccountCachedInfo cachedInfo = otp.getData(RegisterAccountCachedInfo.class);

            logger.info("#registerConfirmOtp - Response: Init response");
            response = new RegisterResponse(ErrorCode.SUCCESS, language);

            logger.info("#registerConfirmOtp - Save register into db");
            Register register = new Register();
            register.setPhoneNumber(cachedInfo.getAccountNumber());
            register.setFullName(cachedInfo.getFullName());
            register.setDob(DateTimeUtils.toDate(cachedInfo.getDobStr()));
            register.setGender(cachedInfo.getGender());
            register.setPaperType(cachedInfo.getPaperTypeInt());
            register.setIdNo(cachedInfo.getIdNo());
            if (!CommonUtils.isNullOrEmpty(cachedInfo.getReferenceNumber())) {
                register.setReferenceNumber(cachedInfo.getReferenceNumber());
            }
            register.setStatus(1);
            register.setCreateDate(new Date());

            registerDAO.save(session, register);

            logger.info("#registerConfirmOtp - remove key cached");
            removeOtpTransaction(request.getTransId());

            return response;

        } catch (Exception e) {
            logExceptions("#registerConfirmOtp - Error: ", e);
            e.printStackTrace();
        }
        return BaseResponse.commonError(language);
    }


    @POST
    @Path("/upgrade-info")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse upgradeAccount(@NotNull RegisterRequest request) {
        logger.info("#cashInInfo - Start: " + request.toLogString());

        try {

            String accountNumberRQ = request.getMsisdn().trim();
            if (CommonUtils.isNullOrEmpty(accountNumberRQ)) {
                logger.info("#upgradeAccount - Validate: msisdn is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            String accountNumber = CommonUtils.getMsisdn(accountNumberRQ);
            if (!CommonUtils.isPhoneNumber(accountNumber)) {
                logger.info("#transferInfo - Validate: accountNumber invalid");
                return BaseResponse.build(ErrorCode.ERR_RECEIVER_ACCOUNT_INVALID, language);
            }

            if (CommonUtils.isNullOrEmpty(request.getImageCustomer())) {
                logger.info("#upgradeAccount - Validate: Image customer is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            if (CommonUtils.isNullOrEmpty(request.getImageIdentityFront())) {
                logger.info("#upgradeAccount - Validate: Image Identity Front is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            if (CommonUtils.isNullOrEmpty(request.getImageIdentityFront())) {
                logger.info("#upgradeAccount - Validate: Image Identity Back is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            // upload image into (ftp server)

            // send otp sms
            logger.info("#upgradeAccount - Send sms, notify && save data into cache");
//            OtpService otp = new OtpService(AppConfigurations.shared().isProductionMode());
            OtpService otp = new OtpService(accountNumber, AppConfigurations.shared().getOtpConfig());
            String tmp = sendOtpUpgradeAccount(otp, accountNumber);

            String transIdUpgrade = CommonUtils.generateUUID();
            otp.startCountdown(false);
            otp.setOtpMessage(tmp);
            otp.setTransId(transIdUpgrade);
            otp.setData(accountNumber);
            setOtpTransaction(otp);

            logger.info("#upgradeAccount - Response: Init response");
            RegisterResponse response = new RegisterResponse(ErrorCode.SUCCESS, language);
            response.setTransId(transIdUpgrade);
            String temp = otp.getOtpMessage();
            temp = temp.replace(otp.getOtp(), "******");
            response.setOtpMessage(temp);

            return response;

        } catch (Exception e) {
            logExceptions("#upgradeAccount - Error: ", e);
            e.printStackTrace();
        }
        return BaseResponse.commonError(language);
    }

    @POST
    @Path("/upgrade-confirm")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse upgradeConfirm(@NotNull RegisterRequest request) {
        logger.info("#upgradeConfirm - Start: " + request.toLogString());
        IsoObject isoRequest = null;
        IsoObject isoResponse = null;
        RegisterResponse response = null;
        try {

            if (!verifySignature(request.getSignature(), request.getTransId(), request.getOtp())) {
                logger.info("#upgradeConfirm - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }

            logger.info("#upgradeConfirm - Validate data");
            if (CommonUtils.isNullOrEmpty(request.getTransId())) {
                logger.info("#upgradeConfirm - Validate: TransactionId is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            if (CommonUtils.isNullOrEmpty(request.getOtp())) {
                logger.info("#upgradeConfirm - Validate: otp is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            if (CommonUtils.isNullOrEmpty(request.getSignature())) {
                logger.info("#upgradeConfirm - Validate: Signature is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            OtpService otp = getOtpTransaction(request.getTransId());
            if (otp == null) {
                logger.info("#upgradeConfirm: OTP Trans invalid or expired");
                return BaseResponse.build(ErrorCode.ERR_TRANSACTION_EXPIRED, language);
            }

            if (!otp.verifyOtp(request.getOtp())) {
                logger.info("#upgradeConfirm: OTP invalid");
                return BaseResponse.build(ErrorCode.ERR_OTP_ID_INVALID, language);
            }

            logger.info("#upgradeConfirm - TX: Get account from cached");
            String accountNumber = otp.getData(String.class);

            logger.info("#upgradeConfirm - Init response");
            response = new RegisterResponse(ErrorCode.SUCCESS, language);
            response.setTitle(LanguageUtils.getString("MSG_UPGRADE_TITLE_SUCCESS", language));
            String content = LanguageUtils.getString("MSG_UPGRADE_CONTENT_SUCCESS", language);
            content = content.concat(" " + accountNumber);
            response.setContent(content);
            response.setSubContent(LanguageUtils.getString("MSG_UPGRADE_SUB_CONTENT_SUCCESS", language));

            logger.info("#upgradeConfirm - remove key cached");
            removeOtpTransaction(request.getTransId());

            return response;

        } catch (Exception e) {
            logExceptions("#upgradeConfirm - Error: ", e);
            e.printStackTrace();
        }
        return BaseResponse.commonError(language);
    }

    private String sendOtpRegisterToCustomer(OtpService otp, String phoneNumber) {

        try {
            String msgKey = "MSG_REGISTER_OTP_TO_CUSTOMER";

            MessageContent mc = MessageContent.builder()
                    .addReplacement("[OTP]", otp.getOtp())
                    .addTemplate(Language.ENGLISH.key(), String.format("%s", LanguageUtils.getString(msgKey, Language.ENGLISH.key())))
                    .addTemplate(Language.VIETNAMESE.key(), String.format("%s", LanguageUtils.getString(msgKey, Language.VIETNAMESE.key())))
                    .addTitles(MessagingClient.shared().getApsTitle("registerAccount"))
                    .build();

            Message message = Message.builder()
                    .setChannel(ChannelType.APIGW_ENDUSER.code())
                    .setRefId(otp.getTransId())
                    .setReceiverObj("enduser")
                    .setSender(MessagingClient.shared().getSender())
                    .setReceiver(phoneNumber)
                    .setMessageContent(mc)
                    .setPushNotification(false)
                    .setSaveNotificationLog(false)
                    .build();

            logger.info(message.toJsonString());
            MessagingClient.shared().sendAsyncTask(message);

            return mc.getContents().get(language);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#sendOtpRegisterToCustomer - EXCEPTION: ", ex);
        }
        return "";
    }

    private String sendOtpUpgradeAccount(OtpService otp, String accountNumber) {
        try {
            String msgKey = "MSG_UPGRADE_ACCOUNT_OTP_TO_CUSTOMER";

            MessageContent mc = MessageContent.builder()
                    .addReplacement("[OTP]", otp.getOtp())
                    .addTemplate(Language.ENGLISH.key(), String.format("%s", LanguageUtils.getString(msgKey, Language.ENGLISH.key())))
                    .addTemplate(Language.VIETNAMESE.key(), String.format("%s", LanguageUtils.getString(msgKey, Language.VIETNAMESE.key())))
                    .addTitles(MessagingClient.shared().getApsTitle("upgradeAccount"))
                    .build();

            Message message = Message.builder()
                    .setChannel(ChannelType.APIGW_ENDUSER.code())
                    .setRefId(otp.getTransId())
                    .setReceiverObj("enduser")
                    .setSender(MessagingClient.shared().getSender())
                    .setReceiver(accountNumber)
                    .setMessageContent(mc)
                    .setPushNotification(false)
                    .setSaveNotificationLog(false)
                    .build();

            logger.info(message.toJsonString());
            MessagingClient.shared().sendAsyncTask(message);

            return mc.getContents().get(language);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#sendOtpRegisterToCustomer - EXCEPTION: ", ex);
        }
        return "";
    }

    private IsoObject makeIsoToUpgradeConfirm(TransactionType transType, IsoObject isoData) {
        IsoObject iso = null;
        try {
            iso = initIsoObject2CoreApp();
            iso.setRequestId(CommonUtils.generateUUID());
            iso.setProcessCode(transType.processCode());
            iso.setPhoneNumber(isoData.getPhoneNumber());
            iso.setCustomerName(isoData.getCustomerName());
            iso.setCustomerBirthday(isoData.getCustomerBirthday());
            iso.setCustomerGender(isoData.getCustomerGender());
            iso.setActionNode("7");
            iso.setCarriedCode("test");
            iso.setPaperType(isoData.getPaperType());
            iso.setPaperNumber(isoData.getPaperNumber());
            iso.setCustomerAddress(isoData.getCustomerAddress());  // co van de
            iso.setCustomerPhone(isoData.getPhoneNumber());
            iso.setEffectType("0");
            iso.setTier("1");
            iso.setTransactionDescription(transType.description());

        } catch (Exception ex) {
            logger.error("#makeIsoToUpgradeConfirm - EXCEPTION: ", ex);
            ex.printStackTrace();
        }
        return iso;
    }

    private IsoObject makeIsoToRegisterConfirm(RegisterAccountCachedInfo data, TransactionType transType) {
        IsoObject iso = null;
        try {
            iso = initIsoObject2CoreApp();
            iso.setRequestId(CommonUtils.generateUUID());
            iso.setProcessCode(transType.processCode());
            iso.setCustomerName(data.getFullName());
            iso.setPhoneNumber(data.getAccountNumber());
            iso.setCarriedPhone(data.getAccountNumber());
            iso.setPINNew(data.getPass());
            if(language.equals("en")){
                iso.setLanguage(Constants.English);
            }else if(language.equals("fr")){
                iso.setLanguage(Constants.France);
            }else if(language.equals("ht")) {
                iso.setLanguage(Constants.CREOLE);
            }
            iso.setCustomerGender(getGender(String.valueOf(data.getGender())));
            iso.setCustomerBirthday(data.getDob());

            iso.setRoleId(Constants.CUSTOMER);
            iso.setTransactionDescription(data.getDesTransaction());
            iso.setPaperType(data.getPaperType());
            iso.setPaperNumber(data.getIdNo());

        } catch (Exception ex) {
            logger.error("#makeIsoToRegisterConfirm - EXCEPTION: ", ex);
            ex.printStackTrace();
        }
        return iso;
    }

    private IsoObject makeIsoRegisterCheck(String accountNumber, TransactionType transType) {
        IsoObject iso = null;
        try {
            iso = initIsoObject2CoreApp();
            iso.setRequestId(CommonUtils.generateUUID());
            iso.setProcessCode(transType.processCode());
            iso.setPhoneNumber(accountNumber);
            iso.setCustomerPhone(accountNumber);
            iso.setCarriedPhone(accountNumber);
            if(language.equals("en")){
                iso.setLanguage(Constants.English);
            }else if(language.equals("fr")){
                iso.setLanguage(Constants.France);
            }else if(language.equals("ht")) {
                iso.setLanguage(Constants.CREOLE);
            }

        } catch (Exception ex) {
            logger.error("#makeIsoRegisterCheck - EXCEPTION: ", ex);
            ex.printStackTrace();
        }
        return iso;
    }

    public String getGender(String code) {
        Gender gender = Gender.get(code);
        return gender.description();
    }

    public String getPaperType(String code) {
        PaperType paperType = PaperType.get(code);
        return paperType.description();
    }
}
