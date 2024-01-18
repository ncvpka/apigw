package vn.supperapp.apigw.restful.controllers.changepass;

import com.viettel.ewallet.pin.PinUtils;
import com.viettel.ewallet.utils.iso.msg.IsoObject;
import org.hibernate.Session;
import vn.supperapp.apigw.beans.ChangePassCachedInfo;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.configs.AppConfigurations;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.AccountDAO;
import vn.supperapp.apigw.db.dao.UserDAO;
import vn.supperapp.apigw.db.dto.Account;
import vn.supperapp.apigw.db.dto.AppUser;
import vn.supperapp.apigw.restful.controllers.BaseController;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.ChangePass.ChangePassRequest;

import vn.supperapp.apigw.restful.models.ChangePass.ChangePassResponse;
import vn.supperapp.apigw.utils.Constants;
import vn.supperapp.apigw.utils.ErrorCode;
import vn.supperapp.apigw.messaging.MessagingClient;
import vn.supperapp.apigw.messaging.beans.Message;
import vn.supperapp.apigw.messaging.beans.MessageContent;
import vn.supperapp.apigw.redis.RedisService;
import vn.supperapp.apigw.services.OtpService;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.CryptUtils;
import vn.supperapp.apigw.utils.LanguageUtils;
import vn.supperapp.apigw.utils.enums.ChannelType;
import vn.supperapp.apigw.utils.enums.Language;
import vn.supperapp.apigw.utils.enums.TransactionType;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static vn.supperapp.apigw.utils.CommonUtils.PIN_REG;

/**
 * @author TruongLe
 * @Date 15/02/2022
 * @see ChangePassController
 */

@Path("/changePass")
@RsDefaultFilterMapping
@RsResponseFilterMapping
@RsAuthFilterMapping
public class ChangePassController extends BaseController {
    public ChangePassController() {
        this.logger = LoggerFactory.getLogger(ChangePassController.class);
    }

    @POST
    @Path("/confirm")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse ChangePassInfo(@NotNull ChangePassRequest request) {
        logger.info("#ChangePassConfirm - Start: " + request.toLogString());

        UserLoggedInfo loggedInfo = null;
        ChangePassResponse response = null;
        try {

            if (!verifySignature(request.getSignature(), request.getPass(), request.getPassNew())) {
                logger.info("#ChangePassConfirm - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }

            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }

            logger.info("#ChangePassConfirm - Validate data");
            //region - Validate


            if (CommonUtils.isNullOrEmpty(request.getPass()) || CommonUtils.isNullOrEmpty(request.getPassNew())) {
                logger.info("#ChangePassConfirm - Validate: Pin is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            if (CommonUtils.validate(request.getPass(), PIN_REG) || CommonUtils.validate(request.getPassNew(), PIN_REG)) {
                logger.info("#ChangePassConfirm - Validate:  PIN invalid");
                return BaseResponse.build(ErrorCode.ERR_PIN_INVALID, language);
            }
            //endregion

            // verify pin old

            Session session = DbSessionMgt.shared().getSession();
            IsoObject isoResponsePin = null;
            Account account = new AccountDAO().getAccountById(session, Long.valueOf(loggedInfo.getUserInfo().getAccountNumber()));
            if (!request.getPass().equals(account.getPass())) {
                logger.info("#login - TX: ResponsePin is NOT success");
                isoResponsePin.setResponseCode("10155");
                isoResponsePin.setResponseDescription(null);
                return BaseResponse.buildError(isoResponsePin, language);
            }
            //end verify pin old

            String transId = CommonUtils.generateUUID();
            ChangePassCachedInfo ChangePassCached = new ChangePassCachedInfo();
            ChangePassCached.setTransId(transId);
            ChangePassCached.setPass(CryptUtils.encrypt(request.getPass()));
            ChangePassCached.setPassNew(CryptUtils.encrypt(request.getPassNew()));
//            OtpService otp = new OtpService(AppConfigurations.shared().isProductionMode());
            OtpService otp = new OtpService(loggedInfo.getUserInfo().getAccountNumber(), AppConfigurations.shared().getOtpConfig());
            otp.setData(ChangePassCached);
            otp.setTransId(transId);

            logger.info("#ChangePassConfirm - Send otp and Start countdown OTP");
            otp.startCountdown(false);
            String tmp = sendOtpTransaction(loggedInfo, otp.getOtp(), ChangePassCached);
            otp.setOtpMessage(tmp);
            setOtpTransaction(otp);

            logger.info("#ChangePassConfirm - Generate response");
            response = new ChangePassResponse(ErrorCode.SUCCESS, language);
            response.setRequireOtp(true);
            String temp = otp.getOtpMessage();
            temp = temp.replace(otp.getOtp(), "******");
            response.setOtpMessage(temp);
            response.setTransId(transId);

            return response;
        } catch (Exception ex) {
            logger.error("#ChangePassConfirm Exception: ", ex);
            ex.printStackTrace();
        }
        return BaseResponse.commonError(language);
    }

    private String sendOtpTransaction(UserLoggedInfo loggedInfo, String otp, ChangePassCachedInfo ChangePassCached) {
        try {
            //Please DO NOT provide OTP for anyone.
            String msgKey = "MSG_CHANGE_PIN_MONEY_OTP";

            String otpPrefix = LanguageUtils.getString("MSG_OTP", language);
            MessageContent mc = MessageContent.builder()
                    .addReplacement("#OTP#", otp)
                    .addTemplate(Language.ENGLISH.key(), String.format("%s %s", otpPrefix, LanguageUtils.getString(msgKey, Language.ENGLISH.key())))
                    .addTemplate(Language.VIETNAMESE.key(), String.format("%s %s", otpPrefix, LanguageUtils.getString(msgKey, Language.VIETNAMESE.key())))
                    .addTitles(MessagingClient.shared().getApsTitle("ChangePass"))
                    .build();

            Message message = Message.builder()
                    .setChannel(ChannelType.APIGW_ENDUSER.code())
                    .setRefId(ChangePassCached.getTransId())
                    .setReceiverObj("enduser")
                    .setSender(MessagingClient.shared().getSender())
                    .setReceiver(loggedInfo.getUserInfo().getAccountNumber())
                    .setMessageContent(mc)
                    .setPushNotification(false)
                    .setSaveNotificationLog(false)
                    .build();

            logger.info(message.toJsonString());
            MessagingClient.shared().sendAsyncTask(message);

            return mc.getContents().get(language);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#sendOtpTransaction - EXCEPTION: ", ex);
        }
        return "";
    }


    @POST
    @Path("/verify")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public BaseResponse ChangePassVerify(@NotNull ChangePassRequest request) {
        logger.info("#ChangePassVerify - Start: " + request.toLogString());

        UserLoggedInfo loggedInfo = null;
        ChangePassResponse response = null;
        try {

            if (!verifySignature(request.getSignature(), request.getOtp(), request.getTransId())) {
                logger.info("#ChangePassVerify - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }

            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }

            logger.info("#ChangePassVerify - Validate data");
            if (CommonUtils.isNullOrEmpty(request.getOtp()) || CommonUtils.isNullOrEmpty(request.getTransId())) {
                logger.info("#ChangePassVerify - Validate: Pin is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            OtpService otp = getOtpTransaction(request.getTransId());
            if (otp == null) {
                logger.info("#ChangePassVerify: OTP Trans invalid or expired");
                return BaseResponse.build(ErrorCode.ERR_TRANSACTION_EXPIRED, language);
            }

            if (!otp.verifyOtp(request.getOtp())) {
                logger.info("#ChangePassVerify: OTP invalid");
                return BaseResponse.build(ErrorCode.ERR_OTP_ID_INVALID, language);
            }

            //endregion
            ChangePassCachedInfo data = otp.getData(ChangePassCachedInfo.class);

            Session session = DbSessionMgt.shared().getSession();
            AppUser user = new UserDAO().getAppUserByMsisdn(session, loggedInfo.getUserInfo().getAccountNumber());
            user.setLanguage(language);
            new UserDAO().update(session, user);
            response = new ChangePassResponse(ErrorCode.SUCCESS, data.getPassNew());
            logger.info("#ChangePassVerify - Save token to cached");
            String loginCachedKey = String.format("TOKEN_%s", deviceId);
            loggedInfo.getUserInfo().setPass(data.getPassNew());
            RedisService.shared().set(Constants.REDIS_DB_LOGIN_CACHED, loginCachedKey, loggedInfo, AppConfigurations.shared().getLoginTokenExpired());

            logger.info("#ChangePassVerify  - Remove transaction from cached after success");
            removeOtpTransaction(request.getTransId());
            return response;
        } catch (Exception ex) {
            logger.error("#ChangePassVerify Exception: ", ex);
            ex.printStackTrace();
        }
        return BaseResponse.commonError(language);
    }

    private IsoObject makeIsoChangePass(ChangePassCachedInfo cachedInfo, UserLoggedInfo loggedInfo, TransactionType transType) {
        IsoObject iso = null;
        try {
            iso = initIsoObject2CoreApp();
            iso.setProcessCode(transType.processCode());
            iso.setAccountID(loggedInfo.getUserInfo().getAccountId());
            iso.setCarriedAccountID(loggedInfo.getUserInfo().getAccountId());
            iso.setPhoneNumber(loggedInfo.getUserInfo().getAccountNumber());
            iso.setPINNew(PinUtils.buildPinBlock(loggedInfo.getPan(), CryptUtils.decrypt(cachedInfo.getPassNew())));
            iso.setPIN(PinUtils.buildPinBlock(loggedInfo.getPan(), CryptUtils.decrypt(cachedInfo.getPass())));
            iso.setCarriedPhone(loggedInfo.getUserInfo().getAccountNumber());
            iso.setRoleId(Constants.CUSTOMER);
            iso.setPAN(loggedInfo.getPan());
            iso.setCarriedName(loggedInfo.getUserInfo().getFullName());

        } catch (Exception ex) {
            logger.error("#makeIsoChangePassVerify - EXCEPTION: ", ex);
            ex.printStackTrace();
        }
        return iso;
    }

}
