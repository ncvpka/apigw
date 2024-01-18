package vn.supperapp.apigw.restful.controllers.dayoff;

import org.hibernate.Session;
import org.slf4j.LoggerFactory;
import vn.supperapp.apigw.beans.DayOffInfo;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.*;
import vn.supperapp.apigw.db.dto.*;
import vn.supperapp.apigw.restful.controllers.BaseController;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.dayoff.DayOffRequest;
import vn.supperapp.apigw.restful.models.dayoff.DayOffResponse;
import vn.supperapp.apigw.restful.models.timekeeping.TimeKeepingRequest;
import vn.supperapp.apigw.restful.models.timekeeping.TimeKeepingResponse;
import vn.supperapp.apigw.restful.models.user.UserResponse;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.ErrorCode;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/day-off")
@RsDefaultFilterMapping
@RsAuthFilterMapping
@RsResponseFilterMapping
public class DayOffController extends BaseController {
    private DayOffDAO dayOffDAO;
    private AccountDAO accountDAO;
    private ShiftDAO shiftDAO;

    private UserBankDAO userBankDAO;
    public DayOffController() {
        this.logger = LoggerFactory.getLogger(DayOffController.class);
        dayOffDAO = new DayOffDAO();
        accountDAO = new AccountDAO();
        shiftDAO = new ShiftDAO();
    }
    @POST
    @Path("/create-dayoff")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse confirmTimeKeeping(@NotNull DayOffRequest request) {
        logger.info("#checkOut - Start: " + request.toLogString());
        Session session = null;
        DayOffResponse response = new DayOffResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#info Check session");
            if (!checkDbSession(session)) {
                logger.info("#info DB Connection error");
                return BaseResponse.commonError(language);
            }
            if (CommonUtils.isNullOrEmpty(request.getFromDate()) || CommonUtils.isNullOrEmpty(request.getToDate()) || CommonUtils.isNullOrEmpty(request.getIdType().toString()) ||
            CommonUtils.isNullOrEmpty(request.getPhone().toString()))
             {
                logger.info("#checkIn - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            DayOff dayOff = new DayOff();
            if(!CommonUtils.isNullOrEmpty(request.getIdType().toString()))
            {
                DayOffType dayOffType = dayOffDAO.getDayOffTypeById(session, loggedInfo.getAppUser().getOrgId(), request.getIdType());
                if (dayOffType == null) {
                    logger.info("#checkIn - Validate: valid shift");
                    return BaseResponse.build(ErrorCode.ERR_MISSING_SHIFT, language);
                }
                else
                {
                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date startDate = formatter.parse(request.getFromDate());
                    Date toDate = formatter.parse(request.getToDate());
                    dayOff.setTypeId(dayOffType.getId());
                    dayOff.setUserId(loggedInfo.getAppUser().getId());
                    dayOff.setPhone(request.getPhone());
                    dayOff.setDateStart(startDate);
                    dayOff.setDateEnd(toDate);
                    dayOff.setTotalDay(request.getTotalDay());
                    dayOff.setReason(request.getReason());
                    dayOff.setStatus(0L);
                    dayOff.setType("OFF");
                    dayOff.setOrgId(loggedInfo.getAppUser().getOrgId());
                    dayOff.setCreateBy(loggedInfo.getAppUser().getMsisdn());
                    dayOff.setCreateAt(new Date());
                    dayOffDAO.save(session, dayOff);
                }
                response.setDayOff(dayOff);
                return response;
            }
        } catch (Exception ex) {
            logExceptions("#login - Error: ", ex);
            ex.printStackTrace();
            logger.error("#login - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    @POST
    @Path("/get-day-off-type")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getDayoffType(@NotNull DayOffRequest request) {
        logger.info("#getDayOffType - Start: " + request.toLogString());
        Session session = null;
        DayOffResponse response = new DayOffResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#info Check session");
            if (!checkDbSession(session)) {
                logger.info("#info DB Connection error");
                return BaseResponse.commonError(language);
            }
            List<DayOffType> dayOffTypes = dayOffDAO.getDayOffType(session, loggedInfo.getAppUser().getOrgId());
            if(dayOffTypes != null && dayOffTypes.size() > 0)
            {
                response.setDayOffTypeList(dayOffTypes);
                return response;
            }
            else
            {
                logger.info("#getDayOffType: not data");
                return BaseResponse.commonError(ErrorCode.ERR_MISSING_DATA.name());
            }
        } catch (Exception ex) {
            logExceptions("#login - Error: ", ex);
            ex.printStackTrace();
            logger.error("#login - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

    @POST
    @Path("/get-day-off-by-id")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getDayOffByid(@NotNull DayOffRequest request) {
        logger.info("#checkOut - Start: " + request.toLogString());
        Session session = null;
        DayOffResponse response = new DayOffResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#info Check session");
            if (!checkDbSession(session)) {
                logger.info("#info DB Connection error");
                return BaseResponse.commonError(language);
            }
            if (CommonUtils.isNullOrEmpty(request.getId().toString()))
            {
                logger.info("#checkIn - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            DayOffInfo dayOff = dayOffDAO.getDayOffInfoById(session, loggedInfo.getAppUser().getOrgId(), request.getId());
            if(dayOff ==  null)
            {
                logger.info("#getDayOffType: not data");
                return BaseResponse.commonError(ErrorCode.ERR_MISSING_DATA.name());
            }
            else
            {
                response.setDayOffInfo(dayOff);
                return response;
            }
        } catch (Exception ex) {
            logExceptions("#login - Error: ", ex);
            ex.printStackTrace();
            logger.error("#login - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    @POST
    @Path("/delete-day-off")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteDayOff(@NotNull DayOffRequest request) {
        logger.info("#getDayOffById - Start: " + request.toLogString());
        Session session = null;
        DayOffResponse response = new DayOffResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#info Check session");
            if (!checkDbSession(session)) {
                logger.info("#info DB Connection error");
                return BaseResponse.commonError(language);
            }
            if (CommonUtils.isNullOrEmpty(request.getListId()))
            {
                logger.info("#checkIn - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            List<Long> ids = null;
            if(!CommonUtils.isNullOrEmpty(request.getListId())){
                String [] strings = request.getListId().split(",");
                ids = Stream.of(strings).map(Long::valueOf).collect(Collectors.toList());
            }
            List<DayOff> dayOffs = dayOffDAO.getDayOffByListId(session, loggedInfo.getAppUser().getOrgId(), ids);
            if(dayOffs ==  null || dayOffs.size() == 0)
            {
                logger.info("#getDayOffType: not data");
                return BaseResponse.commonError(ErrorCode.ERR_MISSING_DATA.name());
            }
            else
            {
                if(dayOffDAO.deleteDayOffIds(session, ids))
                {
                    response = new DayOffResponse(ErrorCode.SUCCESS, language);
                    return response;
                }
            }
            response = new DayOffResponse(ErrorCode.ERR_COMMON, language);
            return response;
        } catch (Exception ex) {
            logExceptions("#login - Error: ", ex);
            ex.printStackTrace();
            logger.error("#login - EXCEPTION: ", ex);
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
    public BaseResponse approveDayOff(@NotNull DayOffRequest request) {
        logger.info("#approveDayOff - Start: " + request.toLogString());
        Session session = null;
        DayOffResponse response = new DayOffResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#info Check session");
            if (!checkDbSession(session)) {
                logger.info("#info DB Connection error");
                return BaseResponse.commonError(language);
            }
            if (CommonUtils.isNullOrEmpty(request.getListId()))
            {
                logger.info("#approveDayOff - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            else
            {
                String [] arrIDList = request.getListId().split(",");
                if (arrIDList.length > 0){
                    for(String item : arrIDList){
                        DayOff dayOff = dayOffDAO.getDayOffById(session, loggedInfo.getAppUser().getOrgId(), Long.valueOf(item));
                        dayOff.setStatus(1L);
                        dayOff.setApproveAt(new Date());
                        dayOff.setApproveBy(loggedInfo.getAppUser().getMsisdn());
                        dayOff.setUpdateAt(new Date());
                        dayOff.setUpdateBy(loggedInfo.getAppUser().getMsisdn());
                        dayOffDAO.update(session, dayOff);
                    }
                    response = new DayOffResponse(ErrorCode.SUCCESS, language);
                    return response;
                }
            }
        } catch (Exception ex) {
            logExceptions("#login - Error: ", ex);
            ex.printStackTrace();
            logger.error("#login - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    @POST
    @Path("/get-day-off-approve")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getDayOff(@NotNull DayOffRequest request) {
        logger.info("#getDayOffById - Start: " + request.toLogString());
        Session session = null;
        DayOffResponse response = new DayOffResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#info Check session");
            if (!checkDbSession(session)) {
                logger.info("#info DB Connection error");
                return BaseResponse.commonError(language);
            }
            List<DayOffInfo> dayOffs = dayOffDAO.getDayOff(session, loggedInfo.getAppUser().getOrgId(), request);
            if(dayOffs.size() > 0 && dayOffs != null)
            {
                response.setDayOffs(dayOffs);
                return response;
            }
            else
            {
                logger.info("#getDayOffType: not data");
                return BaseResponse.commonError(ErrorCode.ERR_MISSING_DATA.name());
            }
        } catch (Exception ex) {
            logExceptions("#login - Error: ", ex);
            ex.printStackTrace();
            logger.error("#login - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    @POST
    @Path("/get-day-off-user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getDayOffByUser(@NotNull DayOffRequest request) {
        logger.info("#getDayOffByUser - Start: " + request.toLogString());
        Session session = null;
        DayOffResponse response = new DayOffResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#info Check session");
            if (!checkDbSession(session)) {
                logger.info("#info DB Connection error");
                return BaseResponse.commonError(language);
            }
            List<DayOffInfo> dayOffs = dayOffDAO.getDayOffByUser(session, loggedInfo.getAppUser().getOrgId(), loggedInfo.getAppUser().getId());
            if(dayOffs.size() > 0 && dayOffs != null)
            {
                response.setDayOffs(dayOffs);
                return response;
            }
            else
            {
                logger.info("#getDayOffByUser: not data");
                return response;
            }
        } catch (Exception ex) {
            logExceptions("#login - Error: ", ex);
            ex.printStackTrace();
            logger.error("#login - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

}
