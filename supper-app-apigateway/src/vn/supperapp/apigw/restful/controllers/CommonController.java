package vn.supperapp.apigw.restful.controllers;

import com.viettel.ewallet.utils.iso.msg.IsoObject;
import vn.supperapp.apigw.beans.IntroductionInfo;
import vn.supperapp.apigw.beans.TipMessageInfo;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.configs.AppConfigurations;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.BaseDAO;
import vn.supperapp.apigw.db.dao.TransactionDAO;
import vn.supperapp.apigw.db.dao.UserDAO;
import vn.supperapp.apigw.db.dto.AppConfig;
import vn.supperapp.apigw.db.dto.AppUser;
import vn.supperapp.apigw.db.dto.TransactionTemplate;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.common.*;
import vn.supperapp.apigw.utils.ErrorCode;
import vn.supperapp.apigw.messaging.MessagingClient;
import vn.supperapp.apigw.messaging.beans.Message;
import vn.supperapp.apigw.messaging.beans.MessageContent;
import vn.supperapp.apigw.services.OtpService;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.LanguageUtils;
import org.hibernate.Session;
import org.openide.util.Exceptions;
import org.slf4j.LoggerFactory;
import vn.supperapp.apigw.utils.enums.*;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/")
@RsDefaultFilterMapping
@RsResponseFilterMapping
public class CommonController extends BaseController {
    private BaseDAO baseDAO;
    private TransactionDAO transactionDAO;

    public CommonController() {
        this.logger = LoggerFactory.getLogger(CommonController.class);
        baseDAO = new BaseDAO();
        transactionDAO = new TransactionDAO();
    }

    private static final String CFG_TXTEMPLATE_ALL_PAGESIZE = "txtemplate.all.page-size";
    private static final String CFG_TXTEMPLATE_RECENT_PAGESIZE = "txtemplate.recent.page-size";

    @POST
    @Path("/check-sub")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse checkSubscriber(@NotNull CheckSubRequest request) {
        logger.info("#checkSubscriber - Start: " + request.toLogString());
        Session session = null;
        CheckSubResponse response = new CheckSubResponse(ErrorCode.SUCCESS, language);
        try {
            logger.info("#checkSubscriber validate data");
            //region - Validate
            if (CommonUtils.isNullOrEmpty(request.getAccountNumber())) {
                logger.info("#checkSubscriber - Validate: Missing parameters");
                return BaseResponse.missingParameters(this.language);
            }

            String msisdn = CommonUtils.getMsisdn(request.getAccountNumber().trim());
            //endregion

            logger.info("#user Open db app session");
            session = DbSessionMgt.shared().getSession();
            AppUser user = new UserDAO().getAppUserByMsisdn(session, msisdn);
            if (user == null) {
                logger.info("#checkUser: Fail to check response");
                return BaseResponse.commonError(this.language);
            }


            logger.info("#checkSubscriber: Response success, generate response");
            response.setAccountId(user.getId().toString());
            response.setAccountNumber(msisdn);
            response.setAccountName(user.getFullName());

            logger.info("#checkSubscriber - Response");
            return response;
        } catch (Exception ex) {
            logExceptions("#checkSubscriber - Error: ", ex);
//            DbSessionMgt.shared().rollbackObject(session);
        } finally {
//            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

    @POST
    @Path("otp/resend")
    @Produces(MediaType.APPLICATION_JSON)
    //@RsAuthFilterMapping register có sử dụng otp thì chưa có auth
    @RsAuthFilterMapping
    @RsDefaultFilterMapping
    public BaseResponse resendOtpTrans(ResendOtpTransRequest request) {
        logger.info("#resendOtpTrans - Start: {}" + request.toLogString());
        BaseResponse response = new BaseResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo = null;
        try {
            loggedInfo = getUserLoggedInfo();
            /*if (loggedInfo == null) {
                logger.info("#resendOtpTrans: Logged info invalid");
                return BaseResponse.commonError(language);
            }*/

            //<editor-fold defaultstate="collapsed" desc="Validate input">
            if (CommonUtils.isNullOrEmpty(request.getTransId())) {
                logger.info("#resendOtpTrans - Validate: ERR_MISSING_PARAMETERS");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            //</editor-fold>

            logger.info("#resendOtpTrans - Get otp cached");
            OtpService otp = getOtpTransaction(request.getTransId());
            if (otp == null) {
                logger.info("#transferConfirm: OTP Trans invalid or expired");
                return BaseResponse.build(ErrorCode.ERR_TRANSACTION_EXPIRED, language);
            }

            otp.startCountdown(true);
            resendOtpTransaction(loggedInfo, otp.getOtp(), otp.getOtpMessage(), otp.getTransId());
            setOtpTransaction(otp);

            logger.info("#resendOtpTrans - Generate response");
            String temp = otp.getOtpMessage();
            temp = temp.replace(otp.getOtp(), "******");
            //response.setOtpMessage(temp);
            //response.setRequireOtp(true);
            return response;
        } catch (Exception ex) {
            logger.error("#resendAuthOtp - ERROR: ", ex);
            Exceptions.printStackTrace(ex);
        }
        return BaseResponse.commonError(language);
    }

    @POST
    @Path("/check-account-info")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RsAuthFilterMapping
    public BaseResponse checkAccountInfo(@NotNull CheckSubRequest request) {
        logger.info("#checkAccountInfo - Start: " + request.toLogString());
        Session session = null;
        CheckSubResponse response = new CheckSubResponse(ErrorCode.SUCCESS, language);
        try {
            logger.info("#checkAccountInfo validate data");
            //region - Validate
            if (!verifySignature(request.getSignature(), request.getAccountNumber())) {
                logger.info("#loginOtp - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }

            if (CommonUtils.isNullOrEmpty(request.getAccountNumber())) {
                logger.info("#checkAccountInfo - Validate: Missing parameters");
                return BaseResponse.missingParameters(this.language);
            }

            String msisdn = CommonUtils.getMsisdn(request.getAccountNumber().trim());
            //endregion

            logger.info("#user Open db app session");
            session = DbSessionMgt.shared().getSession();
            AppUser user = new UserDAO().getAppUserByMsisdn(session, msisdn);
            if (user == null) {
                logger.info("#checkUser: Fail to check response");
                return BaseResponse.commonError(this.language);
            }

            logger.info("#checkSubscriber: Response success, generate response");
            response.setAccountId(user.getId().toString());
            response.setAccountNumber(msisdn);
            response.setAccountName(user.getFullName());

            logger.info("#checkSubscriber - Response");
            return response;
        } catch (Exception ex) {
            logExceptions("#checkAccountInfo - Error: ", ex);
//            DbSessionMgt.shared().rollbackObject(session);
        } finally {
//            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

    @POST
    @Path("/feature-info")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RsAuthFilterMapping
    public BaseResponse featureInfo(@NotNull FeatureInfoRequest request) {
        logger.info("#featureInfo - Start: " + request.toLogString());
        Session session = null;
        FeatureInfoResponse response = new FeatureInfoResponse(ErrorCode.SUCCESS, language);
        TransactionDAO dao = null;
        IsoObject isoRequest = null;
        IsoObject isoResponse = null;
        UserLoggedInfo loggedInfo = null;
        try {
            loggedInfo = getUserLoggedInfo();

            logger.info("#featureInfo validate data");
            //region - Validate
            if (!verifySignature(request.getSignature(), request.getFeatureCode())) {
                logger.info("#featureInfo - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }

            AppFeatureCode feature = AppFeatureCode.get(request.getFeatureCode());
            if (feature == null) {
                logger.info("#featureInfo - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }

            //endregion

            logger.info("#featureInfo Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#featureInfo Check session");
            if (!checkDbSession(session)) {
                logger.info("#featureInfo DB Connection error");
                return BaseResponse.commonError(language);
            }

            int numRecord = AppConfigurations.shared().getConfigAsInt(CFG_TXTEMPLATE_RECENT_PAGESIZE);
            logger.info("#featureInfo - Config: Num recent record = {}", numRecord);
            dao = new TransactionDAO();
            List<TransactionTemplate> favouriteList = dao.getListTransactionTemplate(session, loggedInfo.getUserInfo().getAccountNumber(), feature, 2, numRecord, loggedInfo,"");

            if (favouriteList != null && !favouriteList.isEmpty()) {
                numRecord = numRecord - favouriteList.size();
            }
            logger.info("#featureInfo - Config: Num remain record = {}", numRecord);

            List<TransactionTemplate> remainList = null;
            if (numRecord > 0) {
                remainList = dao.getListTransactionTemplate(session, loggedInfo.getUserInfo().getAccountNumber(), feature, 1, numRecord, loggedInfo,"");
            }

            List<TransactionTemplate> recentTrans = new ArrayList<>();
            if (favouriteList != null) {
                recentTrans.addAll(favouriteList);
            }
            if (remainList != null) {
                recentTrans.addAll(remainList);
            }

            logger.info("#featureInfo - Get screen configuration");
            TipMessageInfo messageInfo = new TipMessageInfo();
            messageInfo.setMessageType(MessageType.INFO.code());

            AppConfig appConfig = baseDAO.getConfigValueHeader(session, feature.code(), language);
            if (appConfig == null) {
                messageInfo.setMessageContent("");
            } else {
                messageInfo.setMessageContent(appConfig.getConfigValue());
            }

            List<AppConfig> listIntroduce = baseDAO.getListIntroduce(session, feature.code(), language);
            if (!listIntroduce.isEmpty()) {
                IntroductionInfo info = new IntroductionInfo();
                ArrayList<String> content = new ArrayList<>();
                for (int i = 0; i < listIntroduce.size(); i++) {
                    AppConfig a = listIntroduce.get(i);
                    if (a.getObjId() == 1L) {
                        info.setTitle(a.getConfigValue());
                        continue;
                    } else if (a.getObjId() == 2L) {
                        info.setImage(a.getConfigValue());
                        continue;
                    }else {
                        content.add(a.getConfigValue());
                    }
                }
                info.setContent(content);
                response.setIntroduction(info);
            }
            logger.info("#featureInfo - Response");
            response.setRecentTrans(recentTrans);
            response.setMessageInfo(messageInfo);

            return response;
        } catch (Exception ex) {
            logExceptions("#featureInfo - Error: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

    @POST
    @Path("/transaction-template")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RsAuthFilterMapping
    public BaseResponse transactionTemplate(@NotNull FeatureInfoRequest request) {
        logger.info("#transactionTemplate - Start: " + request.toLogString());
        Session session = null;
        FeatureInfoResponse response = new FeatureInfoResponse(ErrorCode.SUCCESS, language);
        TransactionDAO dao = null;
        IsoObject isoRequest = null;
        IsoObject isoResponse = null;
        UserLoggedInfo loggedInfo = null;
        try {
            loggedInfo = getUserLoggedInfo();

            logger.info("#transactionTemplate validate data");
            //region - Validate
            if (!verifySignature(request.getSignature(), request.getFeatureCode())) {
                logger.info("#transactionTemplate - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }

            AppFeatureCode feature = AppFeatureCode.get(request.getFeatureCode());
            if (feature == null) {
                logger.info("#transactionTemplate - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }

            //endregion

            logger.info("#transactionTemplate Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#transactionTemplate Check session");
            if (!checkDbSession(session)) {
                logger.info("#transactionTemplate DB Connection error");
                return BaseResponse.commonError(language);
            }

            int numRecord = AppConfigurations.shared().getConfigAsInt(CFG_TXTEMPLATE_ALL_PAGESIZE);
            logger.info("#transactionTemplate - Config: Num recent record = {}", numRecord);
            dao = new TransactionDAO();
            List<TransactionTemplate> recentTrans = dao.getListTransactionTemplate(session, loggedInfo.getUserInfo().getAccountNumber(), feature, numRecord);

            logger.info("#transactionTemplate - Response");
            response.setRecentTrans(recentTrans);

            return response;
        } catch (Exception ex) {
            logExceptions("#transactionTemplate - Error: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    private void resendOtpTransaction(UserLoggedInfo loggedInfo, String otp, String messageOtp, String transId) {
        try {

            String msgKey = "MSG_RESEND_OTP";
            String otpPrefix = LanguageUtils.getString("MSG_OTP", language);

            MessageContent mc = MessageContent.builder()
                    .addReplacement("#OTP#", otp)
                    .addTemplate(Language.ENGLISH.key(), String.format("%s %s", otpPrefix, LanguageUtils.getString(msgKey, Language.ENGLISH.key())))
                    .addTemplate(Language.VIETNAMESE.key(), String.format("%s %s", otpPrefix, LanguageUtils.getString(msgKey, Language.VIETNAMESE.key())))
                    .addTitles(MessagingClient.shared().getApsTitle("resendOtp"))
                    .build();

            Message message = Message.builder()
                    .setChannel(ChannelType.APIGW_ENDUSER.code())
                    .setRefId(transId)
                    .setReceiverObj("enduser")
                    .setSender(MessagingClient.shared().getSender())
                    .setReceiver(loggedInfo == null ? "" : loggedInfo.getUserInfo().getAccountNumber())
                    .setMessageContent(mc)
                    .setPushNotification(false)
                    .build();

            logger.info(message.toJsonString());
            MessagingClient.shared().sendAsyncTask(message);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#resendOtpTransaction - EXCEPTION: ", ex);
        }
    }

    @POST
    @Path("/delete-phone-recent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RsAuthFilterMapping
    public BaseResponse deleteRecentTransaction(@NotNull FeatureInfoRequest request) {
        logger.info("#deleteRecentTransaction - Start: " + request.toLogString());
        Session session = null;
        FeatureInfoResponse response;
        TransactionDAO dao;
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();

            logger.info("#deleteRecentTransaction validate data");
            //region - Validate
            if (!verifySignature(request.getSignature(), request.getFeatureCode(),request.getMsisdn())) {
                logger.info("#deleteRecentTransaction - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }

            AppFeatureCode feature = AppFeatureCode.get(request.getFeatureCode());
            if (feature == null) {
                logger.info("#deleteRecentTransaction - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            if (CommonUtils.isNullOrEmpty(request.getMsisdn())) {
                logger.info("#deleteRecentTransaction - Validate: msisdn is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            //endregion
            dao = new TransactionDAO();
            logger.info("#deleteRecentTransaction Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#deleteRecentTransaction Check session");
            if (!checkDbSession(session)) {
                logger.info("#deleteRecentTransaction DB Connection error");
                return BaseResponse.commonError(language);
            }
            dao.updateRecentTransaction(session, request.getMsisdn(), feature, loggedInfo);

            response = new FeatureInfoResponse(ErrorCode.SUCCESS, language);
            return response;

        } catch (Exception ex) {
            logExceptions("#deleteRecentTransaction - Error: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

    @POST
    @Path("/recent-all-transaction")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RsAuthFilterMapping
    public BaseResponse recentAllTransaction(@NotNull FeatureInfoRequest request) {
        logger.info("#recentAllTransaction - Start: " + request.toLogString());
        Session session = null;
        FeatureInfoResponse response = new FeatureInfoResponse(ErrorCode.SUCCESS, language);
        TransactionDAO dao = null;
        IsoObject isoRequest = null;
        IsoObject isoResponse = null;
        UserLoggedInfo loggedInfo = null;
        try {
            loggedInfo = getUserLoggedInfo();

            logger.info("#recentAllTransaction validate data");
            //region - Validate
            if (!verifySignature(request.getSignature(), request.getFeatureCode(),request.getType())) {
                logger.info("#recentAllTransaction - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }

            AppFeatureCode feature = AppFeatureCode.get(request.getFeatureCode());
            if (feature == null) {
                logger.info("#recentAllTransaction - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }

            if (CommonUtils.isNullOrEmpty(request.getType())) {
                logger.info("#recentAllTransaction - Validate: type invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }

            //endregion

            logger.info("#recentAllTransaction Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#recentAllTransaction Check session");
            if (!checkDbSession(session)) {
                logger.info("#recentAllTransaction DB Connection error");
                return BaseResponse.commonError(language);
            }

            int numRecord = AppConfigurations.shared().getConfigAsInt(CFG_TXTEMPLATE_RECENT_PAGESIZE);
            logger.info("#recentAllTransaction - Config: Num recent record = {}", numRecord);
            dao = new TransactionDAO();
            List<TransactionTemplate> favouriteList = dao.getListTransactionTemplate(session, loggedInfo.getUserInfo().getAccountNumber(), feature, 2, numRecord, loggedInfo,request.getType());

            if (favouriteList != null && !favouriteList.isEmpty()) {
                numRecord = numRecord - favouriteList.size();
            }
            logger.info("#recentAllTransaction - Config: Num remain record = {}", numRecord);

            List<TransactionTemplate> remainList = null;
            if (numRecord > 0) {
                remainList = dao.getListTransactionTemplate(session, loggedInfo.getUserInfo().getAccountNumber(), feature, 1, numRecord, loggedInfo,request.getType());
            }

            List<TransactionTemplate> recentTrans = new ArrayList<>();
            if (favouriteList != null) {
                recentTrans.addAll(favouriteList);
            }
            if (remainList != null) {
                recentTrans.addAll(remainList);
            }

            logger.info("#recentAllTransaction - Response");
            response.setRecentTrans(recentTrans);

            return response;
        } catch (Exception ex) {
            logExceptions("#recentAllTransaction - Error: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

}
