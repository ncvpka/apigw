package vn.supperapp.apigw.restful.controllers.payrequest;

import org.hibernate.Session;
import org.slf4j.LoggerFactory;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.PaymentRequestDAO;
import vn.supperapp.apigw.db.dto.Branch;
import vn.supperapp.apigw.db.dto.PaymentRequest;
import vn.supperapp.apigw.db.dto.TimeKeeping;
import vn.supperapp.apigw.restful.controllers.BaseController;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.payrequest.PaymentReqRequest;
import vn.supperapp.apigw.restful.models.payrequest.PaymentResponse;
import vn.supperapp.apigw.restful.models.timekeeping.TimeKeepingRequest;
import vn.supperapp.apigw.restful.models.timekeeping.TimeKeepingResponse;
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

@Path("/payment-request")
@RsDefaultFilterMapping
@RsAuthFilterMapping
@RsResponseFilterMapping
public class PaymentRequestController extends BaseController {

    private static final Long CANCEL = 0L;
    private static final Long REFUSE = 1L;
    private static final Long PENDING = 2L;
    private static final Long APPROVE = 3L;
    private static final Long PAID = 4L;
    private static final Long CLOSE = 5L;

    private PaymentRequestDAO dao;

    public PaymentRequestController() {
        this.logger = LoggerFactory.getLogger(PaymentRequestController.class);
        dao = new PaymentRequestDAO();
    }
    @POST
    @Path("/get-branch")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getBranch(@NotNull PaymentReqRequest request) {
        logger.info("#getBranch - Start: " + request.toLogString());
        Session session = null;
        PaymentResponse response = new PaymentResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            session = DbSessionMgt.shared().getSession();
            logger.info("#getBranch Check session");
            if (!checkDbSession(session)) {
                logger.info("#getBranch DB Connection error");
                return BaseResponse.commonError(language);
            }
            loggedInfo = getUserLoggedInfo();
            response = new PaymentResponse(ErrorCode.SUCCESS, language);
            List<Branch> list = dao.getListBranch(session, loggedInfo.getAppUser().getOrgId());
            response.setBranchList(list);
            return response;
        } catch (Exception ex) {
            logExceptions("#getPaymentRequest - Error: ", ex);
            ex.printStackTrace();
            logger.error("#getPaymentRequest - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

    @POST
    @Path("/get-list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getPaymentRequest(@NotNull PaymentReqRequest request) {
        logger.info("#getPaymentRequest - Start: " + request.toLogString());
        Session session = null;
        PaymentResponse response = new PaymentResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            session = DbSessionMgt.shared().getSession();
            logger.info("#getPaymentRequest Check session");
            if (!checkDbSession(session)) {
                logger.info("#getPaymentRequest DB Connection error");
                return BaseResponse.commonError(language);
            }
            loggedInfo = getUserLoggedInfo();
            response = new PaymentResponse(ErrorCode.SUCCESS, language);
            List<PaymentRequest> list = dao.getPaymentRequestByUser(session, loggedInfo.getAppUser().getOrgId(), loggedInfo.getAppUser().getId(), request);
            response.setList(list);
            return response;
        } catch (Exception ex) {
            logExceptions("#getPaymentRequest - Error: ", ex);
            ex.printStackTrace();
            logger.error("#getPaymentRequest - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse insert(@NotNull PaymentReqRequest request) {
        logger.info("#insertPaymentRequest - Start: " + request.toLogString());
        Session session = null;
        PaymentResponse response = new PaymentResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            logger.info("#insertPaymentRequest validate data");
            if (CommonUtils.isNullOrEmpty(request.getValue()) || CommonUtils.isNullOrEmpty(request.getReason()))
             {
                logger.info("#insertPaymentRequest - Validate: missing data");
                return BaseResponse.build(ErrorCode.ERR_MISSING_DATA, language);
            }
            loggedInfo = getUserLoggedInfo();
            session = DbSessionMgt.shared().getSession();
            logger.info("#insertPaymentRequest Check session");
            if (!checkDbSession(session)) {
                logger.info("#insertPaymentRequest DB Connection error");
                return BaseResponse.commonError(language);
            }
            if(request.getId() == null) {
                logger.info("#insertPaymentRequest start insert");
                int number = dao.getTotalRecord(session, loggedInfo.getAppUser().getOrgId());
                String billNumber = "DNTT." + loggedInfo.getUserInfo().getBranchCode() + "." + number;
                PaymentRequest paymentRequest = new PaymentRequest();
                paymentRequest.setBillNumber(billNumber);
                paymentRequest.setBeneficiary(request.getBeneficiary());
                paymentRequest.setNote(request.getNote());
                paymentRequest.setReason(request.getReason());
                paymentRequest.setBankNumber(request.getBankNumber());
                paymentRequest.setBank(request.getBank());
                paymentRequest.setBankBranch(request.getBankBranch());
                paymentRequest.setCreateAt(new Date());
                paymentRequest.setIdUser(loggedInfo.getAppUser().getId());
                paymentRequest.setCreateBy(loggedInfo.getAppUser().getMsisdn());
                paymentRequest.setStatus(2L);
                paymentRequest.setOrgId(loggedInfo.getAppUser().getOrgId());
                if(!CommonUtils.isNullOrEmpty(request.getValue())){
                    String strNew = request.getValue().replace(",", "");
                    paymentRequest.setValue(strNew);
                }
                paymentRequest.setBranchId(request.getBranchId());
                dao.save(session, paymentRequest);
                if (paymentRequest != null) {
                    response.setPayment(paymentRequest);
                } else {
                    response = new PaymentResponse(ErrorCode.ERR_COMMON, language);
                }
            }
            else
            {
                logger.info("#insertPaymentRequest start update");
                PaymentRequest paymentRequest = dao.getPaymentRequestById(session, request.getId(), loggedInfo.getAppUser().getOrgId());
                if(paymentRequest == null){
                    response = new PaymentResponse(ErrorCode.ERR_PARAMETERS_INVALID, language);
                }
                else
                {
                    paymentRequest.setStatus(2L);
                    paymentRequest.setBeneficiary(request.getBeneficiary());
                    paymentRequest.setNote(request.getNote());
                    paymentRequest.setReason(request.getReason());
                    paymentRequest.setBankNumber(request.getBankNumber());
                    paymentRequest.setBank(request.getBank());
                    paymentRequest.setBankBranch(request.getBankBranch());
                    paymentRequest.setUpdateAt(new Date());
                    paymentRequest.setIdUser(loggedInfo.getAppUser().getId());
                    paymentRequest.setUpdateBy(loggedInfo.getAppUser().getMsisdn());
                    paymentRequest.setOrgId(loggedInfo.getAppUser().getOrgId());
                    if(!CommonUtils.isNullOrEmpty(request.getValue())){
                        String strNew = request.getValue().replace(",", "");
                        paymentRequest.setValue(strNew);
                    }
                    paymentRequest.setBranchId(request.getBranchId());
                    dao.update(session, paymentRequest);
                    response.setPayment(paymentRequest);
                }
            }
            return response;
        } catch (Exception ex) {
            logExceptions("#insertPaymentRequest - Error: ", ex);
            ex.printStackTrace();
            logger.error("#insertPaymentRequest - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    @POST
    @Path("/approve")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse approvePaymentRequest(@NotNull PaymentReqRequest request) {
        logger.info("#approvePaymentRequest - Start: " + request.toLogString());
        Session session = null;
        PaymentResponse response = new PaymentResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#approvePaymentRequest Check session");
            if (!checkDbSession(session)) {
                logger.info("#approvePaymentRequest DB Connection error");
                return BaseResponse.commonError(language);
            }
            if (CommonUtils.isNullOrEmpty(request.getListId()))
            {
                logger.info("#approvePaymentRequest - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            else
            {
                String [] arrIDList = request.getListId().split(",");
                if (arrIDList.length > 0){
                    for(String item : arrIDList){
                        PaymentRequest paymentRequest = dao.getPaymentRequestById(session, Long.valueOf(item), loggedInfo.getAppUser().getOrgId());
                        if(paymentRequest == null){
                            response = new PaymentResponse(ErrorCode.ERR_PARAMETERS_INVALID, language);
                        }
                        else
                        {
                            paymentRequest.setStatus(3L);
                            paymentRequest.setUpdateBy(loggedInfo.getAppUser().getMsisdn());
                            paymentRequest.setUpdateAt(new Date());
                            dao.update(session, paymentRequest);
                            response = new PaymentResponse(ErrorCode.SUCCESS, language);
                        }
                    }
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
    @Path("/return")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse returnPaymentRequest(@NotNull PaymentReqRequest request) {
        logger.info("#returnPaymentRequest - Start: " + request.toLogString());
        Session session = null;
        PaymentResponse response = new PaymentResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#returnPaymentRequest Check session");
            if (!checkDbSession(session)) {
                logger.info("#returnPaymentRequest DB Connection error");
                return BaseResponse.commonError(language);
            }
            if (CommonUtils.isNullOrEmpty(request.getListId()))
            {
                logger.info("#returnPaymentRequest - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            else
            {
                String [] arrIDList = request.getListId().split(",");
                if (arrIDList.length > 0){
                    for(String item : arrIDList){
                        PaymentRequest paymentRequest = dao.getPaymentRequestById(session, Long.valueOf(item), loggedInfo.getAppUser().getOrgId());
                        if(paymentRequest == null){
                            response = new PaymentResponse(ErrorCode.ERR_PARAMETERS_INVALID, language);
                        }
                        else
                        {
                            paymentRequest.setStatus(1L);
                            paymentRequest.setReason(request.getReason());
                            paymentRequest.setUpdateBy(loggedInfo.getAppUser().getMsisdn());
                            paymentRequest.setUpdateAt(new Date());
                            dao.update(session, paymentRequest);
                            response = new PaymentResponse(ErrorCode.SUCCESS, language);
                        }
                    }
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
    public BaseResponse deletePaymentRequest(@NotNull PaymentReqRequest request) {
        logger.info("#approvePaymentRequest - Start: " + request.toLogString());
        Session session = null;
        PaymentResponse response = new PaymentResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#approvePaymentRequest Check session");
            if (!checkDbSession(session)) {
                logger.info("#approvePaymentRequest DB Connection error");
                return BaseResponse.commonError(language);
            }
            if (CommonUtils.isNullOrEmpty(request.getId().toString()))
            {
                logger.info("#approvePaymentRequest - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            else
            {
                PaymentRequest paymentRequest = dao.getPaymentRequestById(session, request.getId(), loggedInfo.getAppUser().getOrgId());
                if(paymentRequest == null){
                    response = new PaymentResponse(ErrorCode.ERR_PARAMETERS_INVALID, language);
                }
                else
                {
                    dao.delete(session, paymentRequest.getClass(), request.getId());
                    response = new PaymentResponse(ErrorCode.SUCCESS, language);
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
    @Path("/get-list-approve")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getPaymentRequestApprove(@NotNull PaymentReqRequest request) {
        logger.info("#getPaymentRequestApprove - Start: " + request.toLogString());
        Session session = null;
        PaymentResponse response = new PaymentResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            session = DbSessionMgt.shared().getSession();
            logger.info("#getPaymentRequestApprove Check session");
            if (!checkDbSession(session)) {
                logger.info("#getPaymentRequestApprove DB Connection error");
                return BaseResponse.commonError(language);
            }
            loggedInfo = getUserLoggedInfo();
            response = new PaymentResponse(ErrorCode.SUCCESS, language);
            List<PaymentRequest> list = dao.getPaymentRequestApprove(session, loggedInfo.getAppUser().getOrgId(), request);
            response.setList(list);
            return response;
        } catch (Exception ex) {
            logExceptions("#getPaymentRequestApprove - Error: ", ex);
            ex.printStackTrace();
            logger.error("#getPaymentRequestApprove - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

    @POST
    @Path("/update-status")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse updateStatus(@NotNull PaymentReqRequest request) {
        logger.info("#updateStatus - Start: " + request.toLogString());
        Session session = null;
        PaymentResponse response = new PaymentResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            session = DbSessionMgt.shared().getSession();
            logger.info("#updateStatus Check session");
            if (!checkDbSession(session)) {
                logger.info("#updateStatus DB Connection error");
                return BaseResponse.commonError(language);
            }
            if (CommonUtils.isNullOrEmpty(request.getId().toString()) || request.getStatus() == null)
            {
                logger.info("#updateStatus - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            loggedInfo = getUserLoggedInfo();
            PaymentRequest pay = dao.getPaymentRequestById(session, request.getId(), loggedInfo.getAppUser().getOrgId());
            if(pay == null){
                response = new PaymentResponse(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            else
            {
                pay.setStatus(request.getStatus());
                dao.update(session, pay);
            }
            response = new PaymentResponse(ErrorCode.SUCCESS, language);
            return response;
        } catch (Exception ex) {
            logExceptions("#updateStatus - Error: ", ex);
            ex.printStackTrace();
            logger.error("#updateStatus - EXCEPTION: ", ex);
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
    public BaseResponse getPaymentRequestDetail(@NotNull PaymentReqRequest request) {
        logger.info("#getPaymentRequestDetail - Start: " + request.toLogString());
        Session session = null;
        PaymentResponse response = new PaymentResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            logger.info("#updateInfor validate data");
            //TODO: Validate phone
            if (CommonUtils.isNullOrEmpty(request.getId().toString())
            ) {
                logger.info("#updateInfor - Validate: Account number invalid");
                return BaseResponse.build(ErrorCode.ERR_INVALID_INFORMATION, language);
            }
            loggedInfo = getUserLoggedInfo();
            session = DbSessionMgt.shared().getSession();
            logger.info("#getPaymentRequestDetail Check session");
            if (!checkDbSession(session)) {
                logger.info("#getPaymentRequestDetail DB Connection error");
                return BaseResponse.commonError(language);
            }
            response = new PaymentResponse(ErrorCode.SUCCESS, language);
            PaymentRequest pay = dao.getPaymentRequestById(session, request.getId(), loggedInfo.getAppUser().getOrgId() );
            response.setPayment(pay);
            return response;
        } catch (Exception ex) {
            logExceptions("#getPaymentRequestDetail - Error: ", ex);
            ex.printStackTrace();
            logger.error("#getPaymentRequestDetail - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
}
