package vn.supperapp.apigw.restful.controllers;

import vn.supperapp.apigw.beans.PagingResult;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.configs.AppConfigurations;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.MessageDAO;
import vn.supperapp.apigw.db.dto.NotificationLog;
import vn.supperapp.apigw.restful.models.NotificationRequest;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.NotificationResponse;
import vn.supperapp.apigw.utils.ErrorCode;
import vn.supperapp.apigw.wso2.objs.Transaction;
import vn.supperapp.apigw.wso2.service.TransactionHistoryService;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.LanguageUtils;
import org.hibernate.Session;
import org.openide.util.Exceptions;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author TruongLe
 * @Date 14/03/2022
 * @see NotificationController
 */

@Path("/notification")
@RsDefaultFilterMapping
@RsAuthFilterMapping
@RsResponseFilterMapping
public class NotificationController extends BaseController {
    private static final String CFG_NOTIFY_PAGE_SIZE = "notify.list.page.size";
    private TransactionHistoryService historyService;
    private static final String FORMAT_DDMMMYYYY = "dd-MMM-yyyy";
    private static final String HTG = " HTG";
    private static final String TRANSFER = "7";
    private static final String CASH_IN = "1";
    private static final String CASH_OUT = "2";
    private static final String PAYMENT = "5";

    public NotificationController() {
        this.logger = LoggerFactory.getLogger(NotificationController.class);
        historyService = new TransactionHistoryService();
    }

    @POST
    @Path("/messages")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BaseResponse getListMessages(@NotNull NotificationRequest request) {
        logger.info("#getListMessages - Start: " + request.toLogString());
        NotificationResponse response = null;
        UserLoggedInfo loggedInfo;
        MessageDAO messageDao;
        Session session = null;
        try {
            loggedInfo = getUserLoggedInfo();

            if (request.getPage() <= 0) {
                logger.info("#getListMessages - Validate: ERR_PAGE_NUMBER_INVALID");
                return BaseResponse.build(ErrorCode.ERR_COMMON, language);
            }

            messageDao = new MessageDAO();
            logger.info("#getListMessages Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#getListMessages Check session");
            if (!checkDbSession(session)) {
                logger.info("#getListMessages DB Connection error");
                return BaseResponse.commonError(language);
            }

            int pageSize = AppConfigurations.shared().getConfigAsInt(CFG_NOTIFY_PAGE_SIZE);
            PagingResult messages = messageDao.getListMessages(session, this.getLanguage(), loggedInfo, pageSize, request.getPage(), request.getType(), request);

            int totalRecordUnread = messageDao.getTotalRecordUnread(session, loggedInfo);

            response = new NotificationResponse(ErrorCode.SUCCESS, language);
            response.setMessages(messages);
            response.setTotalNumberUnread(totalRecordUnread);
            return response;

        } catch (Exception e) {
            logger.error("#getListMessages - ERROR: ", e);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return BaseResponse.commonError(language);
    }

    @POST
    @Path("/update-status")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BaseResponse updateStatus(@NotNull NotificationRequest request) {
        logger.info("#updateStatus - Start: " + (request != null ? request.toLogString() : "null"));
        Session session = null;
        MessageDAO messageDao;
        UserLoggedInfo loggedInfo;
        NotificationResponse response = null;
        try {
            loggedInfo = getUserLoggedInfo();
            if (CommonUtils.isNullOrEmpty(request.getId().toString())) {
                logger.info("#registerConfirm - Validate: getRefId is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }
            messageDao = new MessageDAO();
            logger.info("#updateStatus Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#updateStatus Check session");
            if (!checkDbSession(session)) {
                logger.info("#updateStatus DB Connection error");
                return BaseResponse.commonError(language);
            }

            logger.info("#updateStatus get NotificationLog by Id");
            NotificationLog notificationLog = messageDao.getNotificationById(session, request, loggedInfo);
            if (notificationLog != null) {
                if (notificationLog.getStatus() != 1l) {
                    notificationLog.setStatus(1l);
                    messageDao.update(session, notificationLog);
                }
            }
            response = new NotificationResponse(ErrorCode.SUCCESS, language);
            return response;

        } catch (Exception e) {
            logger.error("#updateStatus - ERROR: ", e);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return BaseResponse.commonError(language);
    }

    @POST
    @Path("/update-all-read")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BaseResponse updateStatusAllRead(@NotNull NotificationRequest request) {
        logger.info("#updateStatusAllRead - Start: " + request.toLogString());
        Session session;
        MessageDAO messageDao;
        UserLoggedInfo loggedInfo;
        NotificationResponse response = null;

        try {

            if (CommonUtils.isNullOrEmpty(request.getType())) {
                logger.info("#registerConfirm - Validate: type is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            loggedInfo = getUserLoggedInfo();

            messageDao = new MessageDAO();
            logger.info("#updateStatusAllRead Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#updateStatusAllRead Check session");
            if (!checkDbSession(session)) {
                logger.info("#updateStatusAllRead DB Connection error");
                return BaseResponse.commonError(language);
            }

            messageDao.updateAllNotificationToRead(session, loggedInfo, request);

            response = new NotificationResponse(ErrorCode.SUCCESS, language);
            return response;

        } catch (Exception e) {
            logger.error("#updateStatusAllRead - ERROR: ", e);
            Exceptions.printStackTrace(e);
        }

        return BaseResponse.commonError(language);
    }

    private void setContentByTransaction(Transaction tx, UserLoggedInfo loggedInfo) {
        logger.error("#setContentByTransaction -- START ");
        try {

            String transType = tx.getTransType();
            String carriedPhone = loggedInfo.getUserInfo().getAccountNumber();

            logger.error("#setContentByTransaction transType: " + transType);
            if (TRANSFER.equals(transType)) {
                String content = LanguageUtils.getString("MSG_TRANS_HIS_TRANSFER", language);
                logger.info("#setContentByTransaction content: " + content);
                content = content.replace("[AMOUNT]", CommonUtils.isNullOrEmpty(tx.getAmount()) ? "" : tx.getAmount())
                        .replace("[FEE]", CommonUtils.isNullOrEmpty(tx.getFee()) ? "" : tx.getFee())
                        .replace("[TO_ACCOUNT]", CommonUtils.isNullOrEmpty(tx.getToAccountNumber()) ? "" : tx.getToAccountNumber())
                        .replace("[CONTENT]", CommonUtils.isNullOrEmpty(tx.getContent()) ? "" : tx.getContent());

                tx.setContent(content);
                tx.setService(LanguageUtils.getMessage("TRANS_SERVICE_TRANSFER", language));

                if (!carriedPhone.equals(tx.getToAccountNumber())) {
                    tx.setAmount("-" + convertNumber(Double.parseDouble(tx.getAmount())) + HTG);
                } else {
                    tx.setAmount("+" + convertNumber(Double.parseDouble(tx.getAmount())) + HTG);
                }

                return;
            }

            if (CASH_IN.equals(transType)) {
                String content = LanguageUtils.getString("MSG_CASH_IN", language);
                logger.info("#setContentByTransaction content: " + content);
                content = content.replace("[ACCOUNT_NUMBER]", CommonUtils.isNullOrEmpty(tx.getAccountNumber()) ? "" : tx.getAccountNumber())
                        .replace("[AMOUNT]", CommonUtils.isNullOrEmpty(tx.getAmount()) ? "" : tx.getAmount())
                        .replace("[FEE]", CommonUtils.isNullOrEmpty(tx.getFee()) ? "" : tx.getFee())
                        .replace("[TOTAL_AMOUNT]", CommonUtils.isNullOrEmpty(tx.getTotalAmount()) ? "" : tx.getTotalAmount());
                tx.setContent(content);
                tx.setAmount("+" + convertNumber(Double.parseDouble(tx.getAmount())) + HTG);

                return;
            }

//            if (transType.equals(TOPUP)) {
//                Double Amount = Double.parseDouble(tx.getAmount());
//                Double amountTax = CommonUtils.round(Amount - (CommonUtils.round(Amount / AppConfigurations.shared().getConfigAsDouble(CFG_TOPUP_TAX))));
//
//                String content = LanguageUtils.getString("MSG_TRANS_HIS_TOPUP", language);
//                logger.error("#setContentByTransaction content: " + content);
//                content = content.replace("[AMOUNT]", CommonUtils.isNullOrEmpty(tx.getAmount()) ? "" : tx.getAmount())
//                        .replace("[FEE]", CommonUtils.isNullOrEmpty(tx.getFee()) ? "" : tx.getFee())
//                        .replace("[DISCOUNT]", CommonUtils.isNullOrEmpty(tx.getDiscount()) ? "" : tx.getDiscount())
//                        .replace("[TAX]", CommonUtils.formatMoneyWithUnit(amountTax, CurrencyCode.HTG))
//                        .replace("[FROM_ACC_NUMBER]", CommonUtils.isNullOrEmpty(tx.getAccountNumber()) ? "" : tx.getAccountNumber());
//                return content;
//            }
//
//            if (transType.equals(BUY_DATA)) {
//                String content = LanguageUtils.getString("MSG_TRANS_HIS_BUY_DATA", language);
//                logger.error("#setContentByTransaction content: " + content);
//                content = content.replace("PACKAGE", "package")
//                        .replace("[AMOUNT]", CommonUtils.isNullOrEmpty(tx.getAmount()) ? "" : tx.getAmount())
//                        .replace("[FEE]", CommonUtils.isNullOrEmpty(tx.getFee()) ? "" : tx.getFee())
//                        .replace("[DISCOUNT]", CommonUtils.isNullOrEmpty(tx.getDiscount()) ? "" : tx.getDiscount())
//                        .replace("[FROM_ACC_NUMBER]", CommonUtils.isNullOrEmpty(tx.getAccountNumber()) ? "" : tx.getAccountNumber());
//                return content;
//            }

            if (CASH_OUT.equals(transType)) {
                String content = LanguageUtils.getString("MSG_TRANS_HIS_CASH_OUT", language);
                logger.info("#setContentByTransaction content: " + content);
                content = content.replace("[AMOUNT]", CommonUtils.isNullOrEmpty(tx.getAmount()) ? "" : tx.getAmount())
                        .replace("[AGENT]", "Agent");
                tx.setContent(content);
                tx.setService(LanguageUtils.getMessage("TRANS_SERVICE_CASH_OUT", language));
                tx.setAmount("-" + convertNumber(Double.parseDouble(tx.getAmount())) + HTG);
                return;
            }

//            if (transType.equals(PAYMENT)) {  /// bill
//                String content = LanguageUtils.getString("MSG_TRANS_HIS_BILL", language);
//                logger.info("#setContentByTransaction content: " + content);
//                content = content.replace("[AMOUNT]", CommonUtils.isNullOrEmpty(tx.getAmount()) ? "" : tx.getAmount())
//                        .replace("[INVOICE]", Constants.CARRIER);
//                tx.setContent(content);
//                tx.setService(LanguageUtils.getMessage("TRANS_SERVICE_PAYMENT", language));
//                tx.setContent(LanguageUtils.getMessage("TRANS_PROVIDER_PAYMENT", language));
//                return;
//            }

            if (!carriedPhone.equals(tx.getToAccountNumber())) {
                tx.setAmount("-" + convertNumber(Double.parseDouble(tx.getAmount())) + HTG);
            } else {
                tx.setAmount("+" + convertNumber(Double.parseDouble(tx.getAmount())) + HTG);
            }
        } catch (Exception e) {
            logger.error("#setContentByTransaction Exception: " , e);
            e.printStackTrace();
        }
        return;
    }

}
