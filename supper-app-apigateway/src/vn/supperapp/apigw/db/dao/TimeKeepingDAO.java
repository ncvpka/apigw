/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import vn.supperapp.apigw.beans.*;
import vn.supperapp.apigw.db.dto.TimeKeeping;
import vn.supperapp.apigw.restful.models.timekeeping.TimeKeepingRequest;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.DateTimeUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Taink
 */
public class TimeKeepingDAO extends BaseDAO {

    public TimeKeepingDAO() {
        this.logger = LogManager.getLogger(TimeKeepingDAO.class);
    }

    public TimeKeeping getTimeKeepingToday(Session session, Long orgId, Long userId,  String dateNow) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From TimeKeeping t ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And t.status = 1");
            sql.append("   And t.orgId = :orgId");
            sql.append("   And t.userId = :userId");
            sql.append("   And TO_CHAR(t.dateWork, 'DD-MM-YYYY') = :dateNow");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameter("userId", userId);
            query.setParameter("dateNow", dateNow);

            return (TimeKeeping) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public TimeKeeping getTimeKeepingById(Session session, Long orgId, Long id) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From TimeKeeping t ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And t.orgId = :orgId");
            sql.append("   And t.id = :id");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameter("id", id);

            return (TimeKeeping) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public TimeKeepingInfo getTimeKeepingInforById(Session session, Long orgId, Long id) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" Select n.id, s.NAME as shiftName, h.FULL_NAME as fullName, n.status as status, n.DATE_WORK dateWork, n.TIME_START timeStart, n.TIME_END timeEnd, 1 as workDay, n.OVERTIME as overTime, n.NOTE as note ");
            sql.append(" From TIMEKEEPING n JOIN SHIFT s on n.SHIFT_ID = s.ID JOIN HR_EMPLOYEE h on n.USER_ID = h.APP_USER_ID ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And n.org_Id = :orgId");
            sql.append("   And n.id = :id");

            NativeQuery<TimeKeepingInfo> query = session.createNativeQuery(sql.toString(), TimeKeeping.TIMEKEEPING_MAPPING);
            query.setParameter("orgId", orgId);
            query.setParameter("id", id);

            return query.getSingleResult();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public TimeKeeping getTimeKeepingByIdNotStatus(Session session, Long orgId, Long id) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From TimeKeeping t ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And t.orgId = :orgId");
            sql.append("   And t.id = :id");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameter("id", id);

            return (TimeKeeping) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public List<TimeKeepingInfo> getTimeKeepingApprove(Session session, Long orgId, TimeKeepingRequest request) throws Exception {
        Date fromDate = null;
        Date toDate = null;
        if(!CommonUtils.isNullOrEmpty(request.getFromDate()) && !CommonUtils.isNullOrEmpty(request.getToDate())) {
            fromDate = DateTimeUtils.toDate(request.getFromDate());
            toDate = DateTimeUtils.toDate(request.getToDate());
        }
        logger.info("#getTimeKeeping");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" Select n.id, s.NAME as shiftName, h.FULL_NAME as fullName, n.status as status, n.DATE_WORK dateWork, n.TIME_START timeStart, n.TIME_END timeEnd, 1 as workDay, n.OVERTIME as overTime, n.NOTE as note ");
            sql.append(" From TIMEKEEPING n JOIN SHIFT s on n.SHIFT_ID = s.ID JOIN HR_EMPLOYEE h on n.USER_ID = h.APP_USER_ID ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And n.org_Id = :orgId");
            sql.append("   And n.isPropose = 1");
            if (fromDate != null && toDate != null) {
                sql.append("  and (n.date_Work between :fromDate and :toDate)");
            }
            if(request.getBranchId() != null) {
                sql.append(" And h.branch_Id = :branchId");
            }
            if(request.getStatus() != null) {
                sql.append(" And n.status = :status");
            }
            if(request.getIdDepartment() != null) {
                sql.append(" And h.department_Id = :departmentId");
            }
            sql.append(" order by n.create_At desc ");

            NativeQuery<TimeKeepingInfo> query = session.createNativeQuery(sql.toString(), TimeKeeping.TIMEKEEPING_MAPPING);
            query.setParameter("orgId", orgId);
            if (fromDate != null && toDate != null) {
                query.setParameter("fromDate", fromDate);
                query.setParameter("toDate", toDate);
            }
            if(request.getBranchId() != null) {
                query.setParameter("branchId", request.getBranchId());
            }
            if(request.getStatus() != null) {
                query.setParameter("status", request.getStatus());
            }
            if(request.getIdDepartment() != null) {
                query.setParameter("departmentId", request.getIdDepartment());
            }
            return query.getResultList();
        } catch (Exception ex) {
            logger.error("#getDayOff ERROR ", ex);
            throw ex;
        }
    }
    public List<TimeKeepingInfo> getTimeKeepingByUser(Session session, Long orgId, Long userId) throws Exception {
        logger.info("#getTimeKeeping");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" Select n.id, s.NAME as shiftName, a.FULL_NAME as fullName, n.status as status, n.DATE_WORK dateWork, n.TIME_START timeStart, n.TIME_END timeEnd, 1 as workDay, n.OVERTIME as overTime, n.NOTE as note ");
            sql.append(" From TIMEKEEPING n JOIN SHIFT s on n.SHIFT_ID = s.ID JOIN APP_USER a on n.USER_ID = a.ID ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And n.org_Id = :orgId");
            sql.append("   And n.isPropose = 1");
            sql.append("   And n.status = 0");
            sql.append("   And n.user_Id = :userId");
            sql.append(" order by n.create_At desc ");
            NativeQuery<TimeKeepingInfo> query = session.createNativeQuery(sql.toString(), TimeKeeping.TIMEKEEPING_MAPPING);
            query.setParameter("orgId", orgId);
            query.setParameter("userId", userId);
            return query.getResultList();
        } catch (Exception ex) {
            logger.error("#getDayOff ERROR ", ex);
            throw ex;
        }
    }
    public List<TimeKeepingInfo> getTimeKeepingReport(Session session, Long orgId) throws Exception {
        logger.info("#getTimeKeepingReport");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" Select n.id, s.NAME as shiftName, a.FULL_NAME as fullName, n.status as status, n.DATE_WORK dateWork, n.TIME_START timeStart, n.TIME_END timeEnd, 1 as workDay, n.OverTime as overTime, n.Note as note, '' strDateWork, '' strTimeStart, '' strTimeEnd ");
            sql.append(" From TIMEKEEPING n JOIN SHIFT s on n.SHIFT_ID = s.ID JOIN APP_USER a on n.USER_ID = a.ID ");
            sql.append(" Where 1 = 1 ");
            sql.append("   order by n.CREATE_AT desc ");
            NativeQuery<TimeKeepingInfo> query = session.createNativeQuery(sql.toString(), TimeKeeping.TIMEKEEPING_MAPPING);
            return query.getResultList();
        } catch (Exception ex) {
            logger.error("#getTimeKeepingReport ERROR ", ex);
            throw ex;
        }
    }
    public List<TimeKeepingReportCMSInfo> getTimeKeepingReportCMS(Session session, Long orgId) throws Exception {
        logger.info("#getTimeKeepingReport");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT A1.USER_ID as userId, B1.CODE as code, B1.FULL_NAME as fullName, A1.DATE_START AS timeStart, A1.DATE_END AS timeEnd, NVL(A1.TOTAL_DAY,0) AS totalDay, ");
            sql.append(" 0  as minuteLate, 0 as minuteEarly, 0 as overTime, 1 as coeffcientOvertime, A1.TYPE as type, ");
            sql.append(" 0 as shiftId, '' as shiftCode, sysdate as dateWork, ''  as fromTime, '' as toTime, NVL(B1.TIMEKEEPING_TYPE, 1) AS timekeepingType  ");
            sql.append(" FROM DAY_OFF A1\n" +
                    "            INNER JOIN DAY_OFF_TYPE E1 ON A1.TYPE_ID = E1.ID\n" +
                    "            INNER JOIN HR_EMPLOYEE B1 ON A1.USER_ID = B1.APP_USER_ID\n" +
                    "            INNER JOIN hr_department C1 ON B1.DEPARTMENT_ID = C1.ID\n" +
                    "        WHERE TO_CHAR(A1.DATE_START, 'MM') = :month \n" +
                    "            AND TO_CHAR(A1.DATE_START, 'YYYY') = :year\n" +
                    "            AND A1.TOTAL_DAY > 0\n" +
                    "            AND A1.STATUS = 1\n" +
                    "            AND E1.COEFFICIENT = 1\n" +
                    "            AND A1.ORG_ID = :orgId\n" +
                    "            AND A1.TYPE = 'OFF' ");
            sql.append(" UNION ALL\n" +
                    "        SELECT \n" +
                    "            A1.USER_ID as userId,\n" +
                    "            B1.CODE as code,\n" +
                    "            B1.FULL_NAME as fullName,\n" +
                    "            A1.DATE_START as timeStart,\n" +
                    "            A1.DATE_END AS timeEnd,\n" +
                    "            NVL(A1.TOTAL_DAY,0) AS totalDay,\n" +
                    "            0 as minuteLate,\n" +
                    "            0 as minuteLate,\n" +
                    "            0 as overTime,\n" +
                    "            1 as coeffcientOvertime,\n" +
                    "            A1.TYPE as type,\n" +
                    "            0 AS shiftId,\n" +
                    "            '' as shiftCode,\n" +
                    "            sysdate as dateWork,\n" +
                    "            '' as fromTime,\n" +
                    "            '' as toTime,\n" +
                    "            NVL(B1.TIMEKEEPING_TYPE, 1) AS timekeepingType\n" +
                    "        FROM DAY_OFF A1\n" +
                    "            INNER JOIN DAY_OFF_TYPE E1 ON A1.TYPE_ID = E1.ID\n" +
                    "            INNER JOIN HR_EMPLOYEE B1 ON A1.USER_ID = B1.APP_USER_ID\n" +
                    "        WHERE TO_CHAR(A1.DATE_START, 'MM') = :month \n" +
                    "            AND TO_CHAR(A1.DATE_START, 'YYYY') = :year\n" +
                    "            AND (A1.MINUTE_LATE > 0 OR A1.MINUTE_EARLY > 0)\n" +
                    "            AND A1.STATUS = 1\n" +
                    "            AND A1.ORG_ID = :orgId\n" +
                    "            AND A1.TYPE <> 'OFF' ");
            sql.append(" UNION ALL\n" +
                    "        SELECT \n" +
                    "            A1.USER_ID,\n" +
                    "            B1.CODE as code,\n" +
                    "            B1.FULL_NAME as fullName,\n" +
                    "            A1.TIME_START as timeStart,\n" +
                    "            A1.TIME_END AS timeEnd,\n" +
                    "            0 AS totalDay,\n" +
                    "            0 as minuteLate,\n" +
                    "            0 as minuteLate,\n" +
                    "            NVL(A1.OVERTIME, 0) as overTime,\n" +
                    "            NVL(A1.COEFFICIENT_OVERTIME, 1) as coeffcientOvertime,\n" +
                    "            'TIMEKEEPING' as type,\n" +
                    "            A1.SHIFT_ID as shiftId,\n" +
                    "            F1.Code as shiftCode,\n" +
                    "            A1.DATE_WORK as dateWork,\n" +
                    "            F1.START_TIME as fromTime,\n" +
                    "            F1.TO_TIME as toTime,\n" +
                    "            NVL(B1.TIMEKEEPING_TYPE, 1) AS timekeepingType\n" +
                    "        FROM TIMEKEEPING A1\n" +
                    "            INNER JOIN SHIFT F1 ON A1.SHIFT_ID = F1.ID\n" +
                    "            INNER JOIN HR_EMPLOYEE B1 ON A1.USER_ID = B1.APP_USER_ID\n" +
                    "        WHERE TO_CHAR(A1.DATE_WORK, 'MM') = :month \n" +
                    "            AND TO_CHAR(A1.DATE_WORK, 'YYYY') = :year\n" +
                    "            AND A1.STATUS = 1\n" +
                    "            AND A1.ORG_ID = :orgId\n" +
                    "            AND A1.TIME_START IS NOT NULL ");

            NativeQuery<TimeKeepingReportCMSInfo> query = session.createNativeQuery(sql.toString(), TimeKeepingReportCMSInfo.class);
            query.setParameter("orgId", 1);
            query.setParameter("month", 11);
            query.setParameter("year", 2022);
            List<TimeKeepingReportCMSInfo> items = (List<TimeKeepingReportCMSInfo>) query.getResultList();
            return query.getResultList();
        } catch (Exception ex) {
            logger.error("#getTimeKeepingReport ERROR ", ex);
            throw ex;
        }
    }

    public List<TimeKeeping> getTimeKeepingByListId(Session session, Long orgId, List<Long> objIds) throws Exception {
        logger.info("#getTimeKeepingByListId");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From TimeKeeping d ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And d.orgId = :orgId");
            sql.append("   And d.id in (:objIds) ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameterList("objIds", objIds);
            return (List<TimeKeeping>) query.getResultList();
        } catch (Exception ex) {
            logger.error("#getTimeKeepingByListId ERROR ", ex);
            throw ex;
        }
    }

    public List<TimeKeepingReportCMSInfo> getListTimeKeepingReportCms(Session session) throws Exception {
        logger.info("#getListAgent");
        List<TimeKeepingReportCMSInfo> list = new ArrayList<>();
        CallableStatement callableStatement;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("CALL sp_TimeKeeping_Report(?,?,?,?)");
            SessionImpl sessionImpl = (SessionImpl) session;
            Connection conn = sessionImpl.connection();
            callableStatement = conn.prepareCall(sql.toString());

            callableStatement.setLong(1, 11);
            callableStatement.setLong(2, 2022);
            callableStatement.setLong(3, 1);
            callableStatement.registerOutParameter(4, -10);
            callableStatement.executeUpdate();
            TimeKeepingReportCMSInfo timeKeepingReportCMSInfo;
            ResultSet rs = (ResultSet) callableStatement.getObject(4);
            while (rs.next()) {
                timeKeepingReportCMSInfo  = new TimeKeepingReportCMSInfo();
                timeKeepingReportCMSInfo.setUserId(rs.getLong("USER_ID"));
                timeKeepingReportCMSInfo.setCode(rs.getString("CODE"));
                timeKeepingReportCMSInfo.setFullName(rs.getString("FULL_NAME"));
                timeKeepingReportCMSInfo.setTimeStart(rs.getDate("TIME_START"));
                timeKeepingReportCMSInfo.setTimeEnd(rs.getDate("TIME_END"));
                timeKeepingReportCMSInfo.setTotalDay(rs.getFloat("TOTAL_DAY"));
                timeKeepingReportCMSInfo.setMinuteLate(rs.getLong("MINUTE_LATE"));
                timeKeepingReportCMSInfo.setMinuteEarly(rs.getLong("MINUTE_EARLY"));
                timeKeepingReportCMSInfo.setOverTime(rs.getLong("OVERTIME"));
                timeKeepingReportCMSInfo.setCoeffcientOvertime(rs.getFloat("COEFFICIENT_OVERTIME"));
                timeKeepingReportCMSInfo.setType(rs.getString("TYPE"));
                timeKeepingReportCMSInfo.setShiftId(rs.getLong("SHIFT_ID"));
                timeKeepingReportCMSInfo.setShiftCode(rs.getString("SHIFT_CODE"));
                timeKeepingReportCMSInfo.setDateWork(rs.getDate("DATE_WORK"));
                timeKeepingReportCMSInfo.setFromTime(rs.getString("FROM_TIME"));
                timeKeepingReportCMSInfo.setToTime(rs.getString("TO_TIME"));
                timeKeepingReportCMSInfo.setTimekeepingType(rs.getLong("TIMEKEEPING_TYPE"));
                timeKeepingReportCMSInfo.setIsFullSaturday(rs.getLong("TIMEKEEPING_TYPE"));
                list.add(timeKeepingReportCMSInfo);
            }
        } catch (Exception ex) {
            logger.error("#getListAgent ERROR ", ex);
            throw ex;
        }
        return list;
    }
    public List<TimeKeepingReportCMSResponse> extracData(Session session, UserLoggedInfo userLoggedInfo) throws Exception {
        logger.info("#getListAgent");
        List<TimeKeepingReportCMSResponse> timeKeepingReportCMSResponses = new ArrayList<>();
        List<TimeKeepingReportCMSInfo> list = new ArrayList<>();
        CallableStatement callableStatement;
        try {
            YearMonth yearMonthObject = YearMonth.of(2022, 11);
            int daysInMonth = yearMonthObject.lengthOfMonth();
             list = getListTimeKeepingReportCms(session);
             List<TimeKeepingReportCMSInfo> groupUser = list.stream().filter( distinctByKey(p -> p.getUserId())).collect(Collectors.toList());
             List<TimeKeepingEveryDayInfo> timeKeepingEveryDayInfoList = new ArrayList<>();

            for(TimeKeepingReportCMSInfo user : groupUser)
            {
                int tienHoTroGuiXe = 0;
                int total = 0;
                List<TimeKeepingReportCMSInfo> timekeeping = list.stream().filter(p -> p.getUserId().equals(user.getUserId()) && p.getType().equals("TIMEKEEPING")).collect(Collectors.toList());
                List<TimeKeepingReportCMSInfo> dayOff = list.stream().filter(p -> p.getUserId() == user.getUserId() && p.getType().equals("OFF")).collect(Collectors.toList());
                List<TimeKeepingReportCMSInfo> lateOrEarly = list.stream().filter(p -> p.getUserId() == user.getUserId() && p.getType().equals("LATE")).collect(Collectors.toList());
                List<TimeKeepingReportCMSInfo> holiday = list.stream().filter(p -> p.getUserId() == user.getUserId() && p.getType().equals("HOLIDAY")).collect(Collectors.toList());

                if (user.getTimekeepingType() == 1)
                {
                    for (int i = 11; i <= daysInMonth; i++)
                        {
                            TimeKeepingReportCMSInfo checkHoliday = null;
                            TimeKeepingReportCMSInfo dayLeave = null;
                            TimeKeepingReportCMSInfo late = null;
                            TimeKeepingReportCMSInfo timekeepingInDay = null;
                            int finalI2 = i;
                            List<TimeKeepingReportCMSInfo> inDay = timekeeping.stream().filter(p -> finalI2 == (numberOfDaysBetween(p.getTimeStart()))).collect(Collectors.toList());
                            if(inDay.size() > 0 && inDay != null) timekeepingInDay = inDay.get(0);
                            List<TimeKeepingReportCMSInfo> isHoliday = holiday.stream().filter(p -> finalI2 == (numberOfDaysBetween(p.getTimeStart()))).collect(Collectors.toList());
                            if(isHoliday.size() > 0 && isHoliday != null) checkHoliday = isHoliday.get(0);
                            List<TimeKeepingReportCMSInfo> isDayLeave = dayOff.stream().filter(p -> finalI2 == (numberOfDaysBetween(p.getTimeStart()))).collect(Collectors.toList());
                            if(isDayLeave.size() > 0 && isDayLeave != null) dayLeave = isDayLeave.get(0);
                            List<TimeKeepingReportCMSInfo> isLate = lateOrEarly.stream().filter(p -> finalI2 == (numberOfDaysBetween(p.getTimeStart()))).collect(Collectors.toList());
                            if(isLate.size() > 0 && isLate != null) late = isLate.get(0);
                            TimeKeepingEveryDayInfo timeKeepingEveryDayInfo = TinhCongTheoNgay(timekeepingInDay, dayLeave, late, checkHoliday);
                            if (timeKeepingEveryDayInfo != null)
                            {
                                total += timeKeepingEveryDayInfo.getTotalWorking();
                                timeKeepingEveryDayInfoList.add(timeKeepingEveryDayInfo);
                            }
                        }
                    TimeKeepingReportCMSResponse timeKeepingReportCMSResponse = new TimeKeepingReportCMSResponse();
                    timeKeepingReportCMSResponse.setFullName(user.getFullName());
                    timeKeepingReportCMSResponse.setWorkingDays(timeKeepingEveryDayInfoList);
                    timeKeepingReportCMSResponse.setOvertime(10);
                    timeKeepingReportCMSResponse.setParkingSupport(0);
                    timeKeepingReportCMSResponse.setTotalWorking(total);
                    timeKeepingReportCMSResponse.setCode("NV01");
                    timeKeepingReportCMSResponse.setGoLateLeaveEarlyMinutes(50L);
                    timeKeepingReportCMSResponses.add(timeKeepingReportCMSResponse);
                }
            }
            return  timeKeepingReportCMSResponses;
        } catch (Exception ex) {
            logger.error("#getListAgent ERROR ", ex);
            throw ex;
        }
    }


    public TimeKeepingEveryDayInfo TinhCongTheoNgay(TimeKeepingReportCMSInfo timekeeping, TimeKeepingReportCMSInfo dataNghiPhep, TimeKeepingReportCMSInfo dataMuonSom, TimeKeepingReportCMSInfo isNghiLe) throws ParseException {
        TimeKeepingEveryDayInfo data = new TimeKeepingEveryDayInfo();
        if (timekeeping != null) {
            Long sumCong = 0L;
            DateFormat dateFormat = new SimpleDateFormat("hh:mm");
            if (timekeeping.getTimeStart() != null && timekeeping.getTimeEnd() != null && !CommonUtils.isNullOrEmpty(timekeeping.getFromTime())) {
                Date fromTime = dateFormat.parse(timekeeping.getFromTime());
                Date toTime = dateFormat.parse(timekeeping.getToTime());
                long spanChuan = fromTime.getTime() - toTime.getTime();
                long spanThucTe = timekeeping.getTimeStart().getTime() - timekeeping.getTimeEnd().getTime();
                if (spanChuan <= spanThucTe) {
                    data.setWorking("1");
                    data.setTitle("");
                    data.setTotalWorking(1);
                    data.setTotalMinuteLate(0);
                    return data;
                } else {
                    long ms = spanChuan - spanThucTe;
                    long diffMinutes = ms / (60 * 1000);
                    data.setWorking("MS");
                    data.setTitle("Minute late: " + diffMinutes);
                    data.setTotalWorking(1);
                    data.setTotalMinuteLate(diffMinutes);
                    return data;
                }
            }
        }
        if (dataNghiPhep != null) {
            if (dataNghiPhep.getCoeffcientOvertime() == 1) {
                data.setWorking("NP");
                data.setTitle("Day Leave");
                data.setTotalWorking(dataNghiPhep.getCoeffcientOvertime() * dataNghiPhep.getTotalDay());
                data.setTotalMinuteLate(0);
                return data;
            } else {
                data.setWorking("NP");
                data.setTitle("Day Leave");
                data.setTotalWorking(0);
                data.setTotalMinuteLate(0);
                return data;
            }
        }
        if (isNghiLe != null) {
            data.setWorking("NL");
            data.setTitle("Holiday");
            data.setTotalWorking(1);
            data.setTotalMinuteLate(0);
            return data;
        }
        data.setWorking("");
        data.setTitle("");
        data.setTotalWorking(0);
        data.setTotalMinuteLate(0);
        return data ;
    }
    public TimeKeepingEveryDayInfo TinhCongTheoGio(TimeKeepingReportCMSInfo timekeeping, TimeKeepingReportCMSInfo dataNghiPhep, TimeKeepingReportCMSInfo dataMuonSom, TimeKeepingReportCMSInfo isNghiLe) throws ParseException {
        TimeKeepingEveryDayInfo data = new TimeKeepingEveryDayInfo();
        if (timekeeping != null) {
            Long sumCong = 0L;
            DateFormat dateFormat = new SimpleDateFormat("hh:mm");
            if (timekeeping.getTimeStart() != null && timekeeping.getTimeEnd() != null && !CommonUtils.isNullOrEmpty(timekeeping.getFromTime())) {
                Date fromTime = dateFormat.parse(timekeeping.getFromTime());
                Date toTime = dateFormat.parse(timekeeping.getToTime());
                long secs = (fromTime.getTime() - toTime.getTime()) / 1000;
                int hours = (int) (secs / 3600);
                long spanChuan = fromTime.getTime() - toTime.getTime();
                long spanThucTe = timekeeping.getTimeStart().getTime() - timekeeping.getTimeEnd().getTime();
                if (spanChuan <= spanThucTe) {
                    data.setWorking("1");
                    data.setTitle("");
                    data.setTotalWorking(1);
                    data.setTotalMinuteLate(0);
                    return data;
                } else {
                    long ms = spanChuan - spanThucTe;
                    long diffMinutes = ms / (60 * 1000);
                    data.setWorking("MS");
                    data.setTitle("Minute late: " + diffMinutes);
                    data.setTotalWorking(1);
                    data.setTotalMinuteLate(diffMinutes);
                    return data;
                }
            }
        }
        if (dataNghiPhep != null) {
            if (dataNghiPhep.getCoeffcientOvertime() == 1) {
                data.setWorking("NP");
                data.setTitle("Day Leave");
                data.setTotalWorking(dataNghiPhep.getCoeffcientOvertime() * dataNghiPhep.getTotalDay());
                data.setTotalMinuteLate(0);
                return data;
            } else {
                data.setWorking("NP");
                data.setTitle("Day Leave");
                data.setTotalWorking(0);
                data.setTotalMinuteLate(0);
                return data;
            }
        }
        if (isNghiLe != null) {
            data.setWorking("NL");
            data.setTitle("Holiday");
            data.setTotalWorking(1);
            data.setTotalMinuteLate(0);
            return data;
        }
        data.setWorking("");
        data.setTitle("");
        data.setTotalWorking(0);
        data.setTotalMinuteLate(0);
        return data ;
    }
    public boolean deleteTimeKeepingIds(Session session, List<Long> objIds) {
        logger.info("#deleteTimeKeepingIds START -- ");
        try {

            Transaction t = session.getTransaction();
            if (t == null || !t.isActive()) {
                t = session.beginTransaction();
            }

            StringBuilder sql = new StringBuilder();
            sql.append(" DELETE TIMEKEEPING WHERE id in (:objIds) ");

            Query query = session.createNativeQuery(sql.toString());
            query.setParameterList("objIds", objIds);
            query.executeUpdate();

            session.flush();
            t.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            logger.error("#deleteTimeKeepingIds Exception: ", e);
        }
        return true;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
    public int numberOfDaysBetween(Date date1) {
        try {
            Calendar cal1 = new GregorianCalendar();
            cal1.setTime(date1);
            int days= cal1.get(Calendar.DAY_OF_MONTH);
            return days;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

}
