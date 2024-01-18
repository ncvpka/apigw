package vn.supperapp.apigw.restful.controllers.payrequest;

import org.hibernate.Session;
import org.slf4j.LoggerFactory;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.PaymentInfoUserDAO;
import vn.supperapp.apigw.db.dao.PaymentRequestDAO;
import vn.supperapp.apigw.db.dto.PaymentInfoUser;
import vn.supperapp.apigw.db.dto.PaymentRequest;
import vn.supperapp.apigw.restful.controllers.BaseController;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.payrequest.PaymentInfoUserRequest;
import vn.supperapp.apigw.restful.models.payrequest.PaymentInfoUserResponse;
import vn.supperapp.apigw.restful.models.payrequest.PaymentReqRequest;
import vn.supperapp.apigw.restful.models.payrequest.PaymentResponse;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.ErrorCode;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

import static vn.supperapp.apigw.redis.RedisService.logger;

@Path("/payment-info")
@RsDefaultFilterMapping
@RsAuthFilterMapping
@RsResponseFilterMapping
public class PaymentInfoUserController extends BaseController {
    private PaymentInfoUserDAO dao;

    public PaymentInfoUserController() {
        this.logger = LoggerFactory.getLogger(PaymentInfoUserController.class);
        dao = new PaymentInfoUserDAO();
    }

//    @POST
//    @Path("/get-list")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public BaseResponse getPaymentInfoUser(@NotNull PaymentInfoUserRequest request) {
//        logger.info("#getPaymentInfoUser - Start: " + request.toLogString());
//        Session session = null;
//        PaymentInfoUserResponse response = new PaymentInfoUserResponse(ErrorCode.SUCCESS, language);
//        UserLoggedInfo loggedInfo;
//        try {
//            logger.info("#updateInfor validate data");
//            //TODO: Validate phone
//            if (CommonUtils.isNullOrEmpty(request.getType())
//            ) {
//                logger.info("#updateInfor - Validate: Account number invalid");
//                return BaseResponse.build(ErrorCode.ERR_INVALID_INFORMATION, language);
//            }
//
//            session = DbSessionMgt.shared().getSession();
//            logger.info("#getPaymentRequest Check session");
//            if (!checkDbSession(session)) {
//                logger.info("#getPaymentRequest DB Connection error");
//                return BaseResponse.commonError(language);
//            }
//            loggedInfo = getUserLoggedInfo();
//            response = new PaymentResponse(ErrorCode.SUCCESS, language);
//            List<PaymentRequest> list = dao.getPaymentRequestByUser(session, loggedInfo.getAppUser().getOrgId(), loggedInfo.getAppUser().getId());
//            response.setList(list);
//            return response;
//        } catch (Exception ex) {
//            logExceptions("#getPaymentRequest - Error: ", ex);
//            ex.printStackTrace();
//            logger.error("#getPaymentRequest - EXCEPTION: ", ex);
//            DbSessionMgt.shared().rollbackObject(session);
//        } finally {
//            DbSessionMgt.shared().closeObject(session);
//        }
//        return new BaseResponse();
//    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse savePaymentInfoUser(@NotNull PaymentInfoUserRequest request) {
        logger.info("#savePaymentInfoUser - Start: " + request.toLogString());
        Session session = null;
        PaymentInfoUserResponse response = new PaymentInfoUserResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            logger.info("#savePaymentInfoUser validate data");
            if (CommonUtils.isNullOrEmpty(request.getBankBranch()) || CommonUtils.isNullOrEmpty(request.getBankNumber()) || CommonUtils.isNullOrEmpty(request.getBank().toString()) || CommonUtils.isNullOrEmpty(request.getBeneficiary()))
             {
                logger.info("#savePaymentInfoUser - Validate: missing data");
                return BaseResponse.build(ErrorCode.ERR_MISSING_DATA, language);
            }
            loggedInfo = getUserLoggedInfo();
            session = DbSessionMgt.shared().getSession();
            logger.info("#savePaymentInfoUser Check session");
            if (!checkDbSession(session)) {
                logger.info("#savePaymentInfoUser DB Connection error");
                return BaseResponse.commonError(language);
            }
            if(request.getId() == null) {
                logger.info("#savePaymentInfoUser start insert");
                PaymentInfoUser paymentInfoUser = new PaymentInfoUser();
                paymentInfoUser.setBeneficiary(request.getBeneficiary());
                paymentInfoUser.setBankNumber(request.getBankNumber());
                paymentInfoUser.setBank(request.getBank());
                paymentInfoUser.setBankBranch(request.getBankBranch());
                paymentInfoUser.setCreateAt(new Date());
                paymentInfoUser.setIdUser(loggedInfo.getAppUser().getId());
                paymentInfoUser.setCreateBy(loggedInfo.getAppUser().getMsisdn());
                paymentInfoUser.setOrgId(loggedInfo.getAppUser().getOrgId());
                dao.save(session, paymentInfoUser);
                if (paymentInfoUser != null) {
                    response.setPaymentInfoUser(paymentInfoUser);
                } else {
                    response = new PaymentInfoUserResponse(ErrorCode.ERR_COMMON, language);
                }
            }
            else
            {
                logger.info("#savePaymentInfoUser start update");
                PaymentInfoUser paymentInfoUser = dao.getPaymentInfoUserById(session, request.getId(), loggedInfo.getAppUser().getOrgId());
                if(paymentInfoUser == null){
                    response = new PaymentInfoUserResponse(ErrorCode.ERR_PARAMETERS_INVALID, language);
                }
                else
                {
                    paymentInfoUser.setBeneficiary(request.getBeneficiary());
                    paymentInfoUser.setBankNumber(request.getBankNumber());
                    paymentInfoUser.setBank(request.getBank());
                    paymentInfoUser.setBankBranch(request.getBankBranch());
                    paymentInfoUser.setUpdateAt(new Date());
                    paymentInfoUser.setIdUser(loggedInfo.getAppUser().getId());
                    paymentInfoUser.setUpdateBy(loggedInfo.getAppUser().getMsisdn());
                    paymentInfoUser.setOrgId(loggedInfo.getAppUser().getOrgId());
                    dao.update(session, paymentInfoUser);
                    response.setPaymentInfoUser(paymentInfoUser);
                }
            }
            return response;
        } catch (Exception ex) {
            logExceptions("#savePaymentInfoUser - Error: ", ex);
            ex.printStackTrace();
            logger.error("#savePaymentInfoUser - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    @POST
    @Path("/get-by-id")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getPaymentInfoUserById(@NotNull PaymentInfoUserRequest request) {
        logger.info("#getPaymentInfoUserById - Start: " + request.toLogString());
        Session session = null;
        PaymentInfoUserResponse response = new PaymentInfoUserResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#getPaymentInfoUserById Check session");
            if (!checkDbSession(session)) {
                logger.info("#getPaymentInfoUserById DB Connection error");
                return BaseResponse.commonError(language);
            }
            if (CommonUtils.isNullOrEmpty(request.getId().toString()))
            {
                logger.info("#getPaymentInfoUserById - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            else
            {
                PaymentInfoUser paymentInfoUser = dao.getPaymentInfoUserById(session, request.getId(), loggedInfo.getAppUser().getOrgId());
                if(paymentInfoUser == null){
                    response = new PaymentInfoUserResponse(ErrorCode.ERR_PARAMETERS_INVALID, language);
                }
                else
                {
                    response.setPaymentInfoUser(paymentInfoUser);
                }
                return response;
            }
        } catch (Exception ex) {
            logExceptions("#approveTimeKeeping - Error: ", ex);
            ex.printStackTrace();
            logger.error("#approveTimeKeeping - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deletePaymentInfoUser(@NotNull PaymentInfoUserRequest request) {
        logger.info("#deletePaymentInfoUser - Start: " + request.toLogString());
        Session session = null;
        PaymentInfoUserResponse response = new PaymentInfoUserResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#deletePaymentInfoUser Check session");
            if (!checkDbSession(session)) {
                logger.info("#deletePaymentInfoUser DB Connection error");
                return BaseResponse.commonError(language);
            }
            if (CommonUtils.isNullOrEmpty(request.getId().toString()))
            {
                logger.info("#deletePaymentInfoUser - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            else
            {
                PaymentInfoUser paymentInfoUser = dao.getPaymentInfoUserById(session, request.getId(), loggedInfo.getAppUser().getOrgId());
                if(paymentInfoUser == null){
                    response = new PaymentInfoUserResponse(ErrorCode.ERR_PARAMETERS_INVALID, language);
                }
                else
                {
                    dao.delete(session, paymentInfoUser.getClass(), request.getId());
                    response = new PaymentInfoUserResponse(ErrorCode.SUCCESS, language);
                }
                return response;
            }
        } catch (Exception ex) {
            logExceptions("#deletePaymentInfoUser - Error: ", ex);
            ex.printStackTrace();
            logger.error("#deletePaymentInfoUser - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
}
