package vn.supperapp.apigw.restful.controllers.timekeeping;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.slf4j.LoggerFactory;
import vn.supperapp.apigw.beans.*;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.*;
import vn.supperapp.apigw.db.dto.*;
import vn.supperapp.apigw.restful.controllers.BaseController;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.auth.LoginRequest;
import vn.supperapp.apigw.restful.models.dayoff.DayOffRequest;
import vn.supperapp.apigw.restful.models.dayoff.DayOffResponse;
import vn.supperapp.apigw.restful.models.timekeeping.TimeKeepingReportCMS;
import vn.supperapp.apigw.restful.models.timekeeping.TimeKeepingReportResponse;
import vn.supperapp.apigw.restful.models.timekeeping.TimeKeepingRequest;
import vn.supperapp.apigw.restful.models.timekeeping.TimeKeepingResponse;
import vn.supperapp.apigw.restful.models.user.UserRequest;
import vn.supperapp.apigw.restful.models.user.UserResponse;
import vn.supperapp.apigw.utils.*;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/time-keeping")
@RsDefaultFilterMapping
@RsAuthFilterMapping
@RsResponseFilterMapping
public class TimeKeepingController extends BaseController {
    private TimeKeepingDAO timeKeepingDAO;
    private AccountDAO accountDAO;
    private ShiftDAO shiftDAO;
    private BranchDAO branchDAO;

    private UserBankDAO userBankDAO;

    public TimeKeepingController() {
        this.logger = LoggerFactory.getLogger(TimeKeepingController.class);
        timeKeepingDAO = new TimeKeepingDAO();
        accountDAO = new AccountDAO();
        shiftDAO = new ShiftDAO();
        branchDAO = new BranchDAO();
    }

    @POST
    @Path("/check-in")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse info(@NotNull TimeKeepingRequest request) {
        logger.info("#checkIn - Start: " + request.toLogString());
        TimeKeepingResponse response = new TimeKeepingResponse(ErrorCode.SUCCESS, language);
        Session session = null;
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }
            if (request.getShiftId() == null && (loggedInfo.getUserInfo().getTimeKeepingType().equals(1))) {
                logger.info("#checkIn - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#checkIn Check session");
            if (!checkDbSession(session)) {
                logger.info("#checkIn DB Connection error");
                return BaseResponse.commonError(language);
            }
            Branch branch = branchDAO.getBranchById(session, loggedInfo.getAppUser().getOrgId(), loggedInfo.getUserInfo().getBranchId());
            if (branch == null) {
                logger.info("#checkIn DB Connection error");
                return BaseResponse.commonError(language);
            }
            Location location = new Location(branch.getCode(), branch.getName(), request.getLatitude(), request.getLongitude());
            int rs = new LocationUtils().CheckLocation(location, branch);
            if (rs == 1) {
                logger.info("#checkIn DB Connection error");
                return BaseResponse.commonError(language);
            } else if (rs == 2) {
                logger.info("#checkIn DB Connection error");
                return BaseResponse.build(ErrorCode.ERR_OUT_LOCATION, language);
            }
            Long shiftId = Long.valueOf(0);
            if (!CommonUtils.isNullOrEmpty(request.getShiftId().toString()) && (loggedInfo.getUserInfo().getTimeKeepingType().equals(1))) {
                shiftId = request.getShiftId();
            }
            if (request.getShiftId() == null) {
                Shift shift = shiftDAO.getShiftById(session, request.getShiftId(), loggedInfo.getAppUser().getOrgId(), "en");
                if (shift == null) {
                    return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
                } else {
                    shiftId = shift.getId();
                }
            }
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");
            String strDate = formatter.format(date);
            if (loggedInfo.getUserInfo().getTimeKeepingType().equals(2)) {
                Shift shift = shiftDAO.getShiftAdministrative(session, loggedInfo.getAppUser().getOrgId(), "en");
                if (shift == null) {
                    logger.info("#checkIn - Validate: Invalid shift");
                    return BaseResponse.build(ErrorCode.ERR_MISSING_SHIFT, language);
                } else {
                    shiftId = shift.getId();
                }
            }

            TimeKeeping timeKeepingOld = timeKeepingDAO.getTimeKeepingToday(session, loggedInfo.getAppUser().getOrgId(), loggedInfo.getAppUser().getId(), strDate);
            TimeKeeping timeKeeping = new TimeKeeping();
            if (timeKeepingOld == null) {
                timeKeeping.setShiftId(shiftId);
                timeKeeping.setBranchId(loggedInfo.getUserInfo().getBranchId());
                timeKeeping.setUserId(loggedInfo.getAppUser().getId());
                timeKeeping.setDateWork(new Date());
                timeKeeping.setTimeStart(new Date());
                timeKeeping.setIsPropose(0L);
                timeKeeping.setStatus(1L);
                timeKeeping.setOrgId(loggedInfo.getAppUser().getOrgId());
                timeKeeping.setLatitude(request.getLatitude());
                timeKeeping.setLongitude(request.getLongitude());
                timeKeeping.setCreateAt(new Date());
                timeKeeping.setCreateBy(loggedInfo.getAppUser().getMsisdn());
                timeKeepingDAO.save(session, timeKeeping);
            } else {
                timeKeepingOld.setTimeStart(new Date());
                timeKeepingDAO.update(session, timeKeepingOld);
            }
            response.setTimeKeeping(timeKeeping);
            return response;
        } catch (Exception ex) {
            logExceptions("#login - Error: ", ex);
            ex.printStackTrace();
            logger.error("#login - EXCEPTION: ", ex);

        }
        return new BaseResponse();
    }

    @POST
    @Path("/check-out")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse update(@NotNull TimeKeepingRequest request) {
        logger.info("#checkOut - Start: " + request.toLogString());
        Session session = null;
        TimeKeepingResponse response = new TimeKeepingResponse(ErrorCode.SUCCESS, language);
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
            Branch branch = branchDAO.getBranchById(session, loggedInfo.getAppUser().getOrgId(), loggedInfo.getUserInfo().getBranchId());
            if (branch == null) {
                logger.info("#checkIn DB Connection error");
                return BaseResponse.commonError(language);
            }
            Location location = new Location(branch.getCode(), branch.getName(), request.getLatitude(), request.getLongitude());
            int rs = new LocationUtils().CheckLocation(location, branch);
            if (rs == 1) {
                logger.info("#checkIn DB Connection error");
                return BaseResponse.commonError(language);
            } else if (rs == 2) {
                logger.info("#checkIn DB Connection error");
                return BaseResponse.build(ErrorCode.ERR_OUT_LOCATION, language);
            }
            if (CommonUtils.isNullOrEmpty(request.getId().toString())) {
                logger.info("#checkIn - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            Long overTime = 0L;
            if (CommonUtils.isNullOrEmpty(request.getOverTime().toString())) {
                request.setOverTime(overTime);
            }
            TimeKeeping timeKeeping = timeKeepingDAO.getTimeKeepingById(session, loggedInfo.getAppUser().getOrgId(), request.getId());
            if (timeKeeping == null) {
                logger.info("#checkIn - Validate: Invalid timekeeping Id");
                return BaseResponse.build(ErrorCode.ERR_MISSING_TIMEKEEPING, language);
            } else {
                timeKeeping.setTimeEnd(new Date());
                timeKeeping.setOverTime(overTime);
                timeKeepingDAO.update(session, timeKeeping);
            }
            response.setTimeKeeping(timeKeeping);
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
    @Path("/get-today")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getToDay(@NotNull TimeKeepingRequest request) {
        logger.info("#checkOut - Start: " + request.toLogString());
        Session session = null;
        TimeKeepingResponse response = new TimeKeepingResponse(ErrorCode.SUCCESS, language);
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
            Branch branch = branchDAO.getBranchById(session, loggedInfo.getAppUser().getOrgId(), loggedInfo.getUserInfo().getBranchId());
            if (branch == null) {
                logger.info("#checkIn DB Connection error");
                return BaseResponse.commonError(language);
            }
            Location location = new Location(branch.getCode(), branch.getName(), request.getLatitude(), request.getLongitude());
            int rs = new LocationUtils().CheckLocation(location, branch);
            if (rs == 1) {
                logger.info("#checkIn DB Connection error");
                return BaseResponse.commonError(language);
            } else if (rs == 2) {
                return BaseResponse.build(ErrorCode.ERR_OUT_LOCATION, language);
            }
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");
            String strDate = formatter.format(date);
            TimeKeeping timeKeeping = timeKeepingDAO.getTimeKeepingToday(session, loggedInfo.getAppUser().getOrgId(), loggedInfo.getAppUser().getId(), strDate);
            if (timeKeeping == null) {
                logger.info("#checkIn - Validate: Invalid timekeeping Id");
                return BaseResponse.build(ErrorCode.ERR_MISSING_TIMEKEEPING, language);
            } else {
                response.setTimeKeeping(timeKeeping);
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
    @Path("/create-confirm-timekeeping")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse confirmTimeKeeping(@NotNull TimeKeepingRequest request) {
        logger.info("#checkOut - Start: " + request.toLogString());
        Session session = null;
        TimeKeepingResponse response = new TimeKeepingResponse(ErrorCode.SUCCESS, language);
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
            if (CommonUtils.isNullOrEmpty(request.getShiftId().toString()) || CommonUtils.isNullOrEmpty(request.getDateWork()) || CommonUtils.isNullOrEmpty(request.getIsHalfDay().toString()) ||
                    CommonUtils.isNullOrEmpty(request.getOverTime().toString())) {
                logger.info("#checkIn - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            TimeKeeping timeKeeping = new TimeKeeping();
            if (!CommonUtils.isNullOrEmpty(request.getShiftId().toString())) {
                Shift shift = shiftDAO.getShiftById(session, request.getShiftId(), loggedInfo.getAppUser().getOrgId(), language);
                if (shift == null) {
                    logger.info("#checkIn - Validate: valid shift");
                    return BaseResponse.build(ErrorCode.ERR_MISSING_SHIFT, language);
                }
                if (!shift.getCode().equals("CG")) {
                    String fromTime = request.getDateWork() + " " + shift.getStartTime();
                    String toTime = request.getDateWork() + " " + shift.getToTime();
                    DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    Date workDate = formatter1.parse(request.getDateWork());
                    Date startDate = formatter.parse(fromTime);
                    Date toDate = formatter.parse(toTime);
                    timeKeeping.setShiftId(shift.getId());
                    timeKeeping.setBranchId(loggedInfo.getUserInfo().getBranchId());
                    timeKeeping.setUserId(loggedInfo.getAppUser().getId());
                    timeKeeping.setDateWork(workDate);
                    timeKeeping.setTimeStart(startDate);
                    timeKeeping.setTimeEnd(toDate);
                    timeKeeping.setHalfDay(request.getIsHalfDay());
                    timeKeeping.setOverTime(request.getOverTime());
                    timeKeeping.setCoefficientOvertime(request.getCoefficient());
                    timeKeeping.setIsPropose(1L);
                    timeKeeping.setNote(request.getNote());
                    timeKeeping.setStatus(0L);
                    timeKeeping.setOrgId(loggedInfo.getAppUser().getOrgId());
                    timeKeeping.setCreateAt(new Date());
                    timeKeeping.setCreateBy(loggedInfo.getAppUser().getMsisdn());
                    timeKeepingDAO.save(session, timeKeeping);
                }
                if (shift.getCode().equals("CG") && (CommonUtils.isNullOrEmpty(request.getFromTime()) || CommonUtils.isNullOrEmpty(request.getToTime()))) {
                    logger.info("#checkIn - Validate: Signature invalid");
                    return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
                } else {
                    String fromTime = request.getDateWork() + " " + request.getFromTime();
                    String toTime = request.getDateWork() + " " + request.getToTime();
                    String dateWork = request.getDateWork() + " " + "00:00:00";
                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date workDate = formatter.parse(dateWork);
                    Date startDate = formatter.parse(fromTime);
                    Date toDate = formatter.parse(toTime);
                    timeKeeping.setShiftId(shift.getId());
                    timeKeeping.setBranchId(loggedInfo.getUserInfo().getBranchId());
                    timeKeeping.setUserId(loggedInfo.getAppUser().getId());
                    timeKeeping.setDateWork(workDate);
                    timeKeeping.setTimeStart(startDate);
                    timeKeeping.setTimeEnd(toDate);
                    timeKeeping.setHalfDay(request.getIsHalfDay());
                    timeKeeping.setOverTime(request.getOverTime());
                    timeKeeping.setCoefficientOvertime(request.getCoefficient());
                    timeKeeping.setIsPropose(1L);
                    timeKeeping.setNote(request.getNote());
                    timeKeeping.setStatus(0L);
                    timeKeeping.setOrgId(loggedInfo.getAppUser().getOrgId());
                    timeKeeping.setCreateAt(new Date());
                    timeKeeping.setCreateBy(loggedInfo.getAppUser().getMsisdn());
                    timeKeepingDAO.save(session, timeKeeping);
                }
                response.setTimeKeeping(timeKeeping);
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
    @Path("/approve")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse approveTimeKeeping(@NotNull TimeKeepingRequest request) {
        logger.info("#approveTimeKeeping - Start: " + request.toLogString());
        Session session = null;
        TimeKeepingResponse response = new TimeKeepingResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#approveTimeKeeping Check session");
            if (!checkDbSession(session)) {
                logger.info("#approveTimeKeeping DB Connection error");
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
                        TimeKeeping timeKeeping = timeKeepingDAO.getTimeKeepingById(session, loggedInfo.getAppUser().getOrgId(), Long.valueOf(item));
                        timeKeeping.setStatus(1L);
                        timeKeeping.setApproveAt(new Date());
                        timeKeeping.setApproveBy(loggedInfo.getAppUser().getMsisdn());
                        timeKeeping.setUpdateAt(new Date());
                        timeKeeping.setUpdateBy(loggedInfo.getAppUser().getMsisdn());
                        timeKeepingDAO.update(session, timeKeeping);
                    }
                    response = new TimeKeepingResponse(ErrorCode.SUCCESS, language);
                    return response;
                }
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
    @Path("/get-confirm-work-approve")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getConfirmWorkApprove(@NotNull TimeKeepingRequest request) {
        logger.info("#getDayOffById - Start: " + request.toLogString());
        Session session = null;
        TimeKeepingResponse response = new TimeKeepingResponse(ErrorCode.SUCCESS, language);
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
            List<TimeKeepingInfo> timeKeeping = timeKeepingDAO.getTimeKeepingApprove(session, loggedInfo.getAppUser().getOrgId(), request);
            if (timeKeeping.size() > 0 && timeKeeping != null) {
                response.setTimeKeepingList(timeKeeping);
                return response;
            } else {
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
    @Path("/get-confirm-work-user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getConfirmWorkUser(@NotNull TimeKeepingRequest request) {
        logger.info("#getConfirmWorkUser - Start: " + request.toLogString());
        Session session = null;
        TimeKeepingResponse response = new TimeKeepingResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#getConfirmWorkUser Check session");
            if (!checkDbSession(session)) {
                logger.info("#getConfirmWorkUser DB Connection error");
                return BaseResponse.commonError(language);
            }
            List<TimeKeepingInfo> timeKeeping = timeKeepingDAO.getTimeKeepingByUser(session, loggedInfo.getAppUser().getOrgId(), loggedInfo.getAppUser().getId());
            if (timeKeeping.size() > 0 && timeKeeping != null) {
                response.setTimeKeepingList(timeKeeping);
                return response;
            } else {
                logger.info("#getConfirmWorkUser: not data");
                return response;
            }
        } catch (Exception ex) {
            logExceptions("#getConfirmWorkUser - Error: ", ex);
            ex.printStackTrace();
            logger.error("#getConfirmWorkUser - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

    @POST
    @Path("/time-keeping-report")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse report(@NotNull TimeKeepingRequest request) {
        logger.info("#report - Start: " + request.toLogString());
        Session session = null;
        TimeKeepingReportResponse response = new TimeKeepingReportResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("report error");
                return BaseResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#report Check session");
            if (!checkDbSession(session)) {
                logger.info("#report DB Connection error");
                return BaseResponse.commonError(language);
            }

            /* fake data */
            SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
            response.setTitle("Tháng 2 - 2020");
            response.setLateEarlyDays(6.0);
            response.setTotalWorkingDays(15.2);
            response.setRemainingLeaveDays(10.0);
            response.setSalary(60000000);
            response.setBonus(40000000);
            List<TimeKeepingInfo> details = timeKeepingDAO.getTimeKeepingReport(session, loggedInfo.getAppUser().getOrgId());
            response.setDetails(details);
            return response;
        } catch (Exception ex) {
            logExceptions("#report - Error: ", ex);
            ex.printStackTrace();
            logger.error("#report - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

    @POST
    @Path("/cms-report")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse cmsReport(@NotNull TimeKeepingRequest request) {
        logger.info("#report - Start: " + request.toLogString());
        Session session = null;
        TimeKeepingReportCMS response = new TimeKeepingReportCMS(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("report error");
                return BaseResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#report Check session");
            if (!checkDbSession(session)) {
                logger.info("#report DB Connection error");
                return BaseResponse.commonError(language);
            }

            /* fake data */
            SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
            List<TimeKeepingReportCMSResponse> details = timeKeepingDAO.extracData(session, loggedInfo);
            response.setData(details);
            return response;
        } catch (Exception ex) {
            logExceptions("#report - Error: ", ex);
            ex.printStackTrace();
            logger.error("#report - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

    @POST
    @Path("/cms-report-export")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse cmsReportExport(@NotNull TimeKeepingRequest request) {
        logger.info("#report - Start: " + request.toLogString());
        Session session = null;
        TimeKeepingReportCMS response = new TimeKeepingReportCMS(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("report error");
                return BaseResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#report Check session");
            if (!checkDbSession(session)) {
                logger.info("#report DB Connection error");
                return BaseResponse.commonError(language);
            }
            List<TimeKeepingReportCMSResponse> details = timeKeepingDAO.extracData(session, loggedInfo);
            response.setData(details);
            return response;
        } catch (Exception ex) {
            logExceptions("#report - Error: ", ex);
            ex.printStackTrace();
            logger.error("#report - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    @POST
    @Path("/get-time-keeping-by-id")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getTimekeepingById(@NotNull TimeKeepingRequest request) {
        logger.info("#getTimekeepingById - Start: " + request.toLogString());
        Session session = null;
        TimeKeepingResponse response = new TimeKeepingResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#getTimekeepingById Check session");
            if (!checkDbSession(session)) {
                logger.info("#getTimekeepingById DB Connection error");
                return BaseResponse.commonError(language);
            }
            if (CommonUtils.isNullOrEmpty(request.getId().toString()))
            {
                logger.info("#getTimekeepingById - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            TimeKeepingInfo timeKeeping = timeKeepingDAO.getTimeKeepingInforById(session, loggedInfo.getAppUser().getOrgId(), request.getId());
            if(timeKeeping ==  null)
            {
                logger.info("#getTimekeepingById: not data");
                return BaseResponse.commonError(ErrorCode.ERR_MISSING_DATA.name());
            }
            else
            {
                response.setTimeKeepingInfo(timeKeeping);
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
    @Path("/delete-time-keeping")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteTimeKeeping(@NotNull TimeKeepingRequest request) {
        logger.info("#deleteTimeKeeping - Start: " + request.toLogString());
        Session session = null;
        TimeKeepingResponse response = new TimeKeepingResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#deleteTimeKeeping Check session");
            if (!checkDbSession(session)) {
                logger.info("#deleteTimeKeeping DB Connection error");
                return BaseResponse.commonError(language);
            }
            if (CommonUtils.isNullOrEmpty(request.getListId()))
            {
                logger.info("#deleteTimeKeeping - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            List<Long> ids = null;
            if(!CommonUtils.isNullOrEmpty(request.getListId())){
                String [] strings = request.getListId().split(",");
                ids = Stream.of(strings).map(Long::valueOf).collect(Collectors.toList());
            }
            List<TimeKeeping> timeKeepings = timeKeepingDAO.getTimeKeepingByListId(session, loggedInfo.getAppUser().getOrgId(), ids);
            if(timeKeepings ==  null || timeKeepings.size() == 0)
            {
                logger.info("#deleteTimeKeeping: not data");
                return BaseResponse.commonError(ErrorCode.ERR_MISSING_DATA.name());
            }
            else
            {
                if(timeKeepingDAO.deleteTimeKeepingIds(session, ids))
                {
                    response = new TimeKeepingResponse(ErrorCode.SUCCESS, language);
                    return response;
                }
            }
            response = new TimeKeepingResponse(ErrorCode.ERR_COMMON, language);
            return response;
        } catch (Exception ex) {
            logExceptions("#deleteTimeKeeping - Error: ", ex);
            ex.printStackTrace();
            logger.error("#deleteTimeKeeping - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

}
