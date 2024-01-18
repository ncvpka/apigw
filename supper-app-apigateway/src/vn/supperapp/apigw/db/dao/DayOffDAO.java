/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import org.apache.log4j.LogManager;
import org.assertj.core.util.DateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import vn.supperapp.apigw.beans.DayOffInfo;
import vn.supperapp.apigw.beans.TimeKeepingInfo;
import vn.supperapp.apigw.db.dto.DayOff;
import vn.supperapp.apigw.db.dto.DayOffType;
import vn.supperapp.apigw.db.dto.TimeKeeping;
import vn.supperapp.apigw.restful.models.dayoff.DayOffRequest;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.DateTimeUtils;

import java.util.Date;
import java.util.List;

/**
 * @author Taink
 */
public class DayOffDAO extends BaseDAO {

    public DayOffDAO() {
        this.logger = LogManager.getLogger(DayOffDAO.class);
    }

    public DayOffType getDayOffTypeById(Session session, Long orgId, Long id) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From DayOffType d ");
            sql.append(" Where 1 = 1 ");
           // sql.append("   And d.orgId = :orgId");
            sql.append("   And d.id = :id");

            Query query = session.createQuery(sql.toString());
            //query.setParameter("orgId", orgId);
            query.setParameter("id", id);
            return (DayOffType) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public List<DayOffType> getDayOffType(Session session, Long orgId) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From DayOffType d ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And d.orgId = :orgId");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            return (List<DayOffType>) query.getResultList();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public DayOff getDayOffById(Session session, Long orgId, Long id) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From DayOff d ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And d.orgId = :orgId");
            sql.append("   And d.id = :id");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameter("id", id);
            return (DayOff) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public DayOffInfo getDayOffInfoById(Session session, Long orgId, Long id) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" Select d.id, d.USER_ID as userId, h.FULL_NAME as fullName, d.DATE_START dateStart,  d.DATE_END dateEnd, d.TOTAL_DAY as totalDay, d.REASON as reason, d.status as status, t.NAME as type ");
            sql.append(" From DAY_OFF d JOIN DAY_OFF_TYPE t on d.TYPE_ID = t.ID JOIN HR_EMPLOYEE h on d.USER_ID = h.APP_USER_ID ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And d.org_Id = :orgId");
            sql.append("   And d.id = :id");

            NativeQuery<DayOffInfo> query = session.createNativeQuery(sql.toString(), DayOff.DAY_OFF_MAPPING);
            query.setParameter("orgId", orgId);
            query.setParameter("id", id);

            return query.getSingleResult();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public List<DayOffInfo> getDayOff(Session session, Long orgId, DayOffRequest request) throws Exception {
        Date fromDate = null;
        Date toDate = null;
        if(!CommonUtils.isNullOrEmpty(request.getFromDate()) && !CommonUtils.isNullOrEmpty(request.getToDate())) {
            fromDate = DateTimeUtils.toDate(request.getFromDate());
            toDate = DateTimeUtils.toDate(request.getToDate());
        }
        logger.info("#getDayOff");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" Select d.id, d.USER_ID as userId, h.FULL_NAME as fullName, d.DATE_START dateStart,  d.DATE_END dateEnd, d.TOTAL_DAY as totalDay, d.REASON as reason, d.status as status, t.NAME as type ");
            sql.append(" From DAY_OFF d JOIN DAY_OFF_TYPE t on d.TYPE_ID = t.ID JOIN HR_EMPLOYEE h on d.USER_ID = h.APP_USER_ID ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And d.org_Id = :orgId");
            if (fromDate != null && toDate != null) {
                sql.append("  and (d.date_Start between :fromDate and :toDate)");
            }
            if(request.getBranchId() != null) {
                sql.append(" And h.branch_Id = :branchId");
            }
            if(request.getStatus() != null) {
                sql.append(" And d.status = :status");
            }
            if(request.getIdType() != null) {
                sql.append(" And d.type_Id = :typeId");
            }
            if(!CommonUtils.isNullOrEmpty(request.getType())) {
                sql.append(" And d.type = :type");
            }
            sql.append(" order by d.create_At desc ");

            NativeQuery<DayOffInfo> query = session.createNativeQuery(sql.toString(), DayOff.DAY_OFF_MAPPING);
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
            if(request.getIdType() != null) {
                query.setParameter("typeId", request.getIdType());
            }
            if(!CommonUtils.isNullOrEmpty(request.getType())) {
                query.setParameter("type", request.getType());
            }
            return query.getResultList();
        } catch (Exception ex) {
            logger.error("#getDayOff ERROR ", ex);
            throw ex;
        }
    }
    public List<DayOffInfo> getDayOffByUser(Session session, Long orgId, Long userId) throws Exception {
        logger.info("#getDayOffByUser");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" Select d.id, d.USER_ID as userId, h.FULL_NAME as fullName, d.DATE_START dateStart,  d.DATE_END dateEnd, d.TOTAL_DAY as totalDay, d.REASON as reason, d.status as status, t.NAME as type ");
            sql.append(" From DAY_OFF d JOIN DAY_OFF_TYPE t on d.TYPE_ID = t.ID JOIN HR_EMPLOYEE h on d.USER_ID = h.APP_USER_ID ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And d.org_Id = :orgId");
            sql.append("   And d.status = 0");
            sql.append("   And d.user_Id = :userId");
            sql.append(" order by d.create_At desc ");
            NativeQuery<DayOffInfo> query = session.createNativeQuery(sql.toString(), DayOff.DAY_OFF_MAPPING);
            query.setParameter("orgId", orgId);
            query.setParameter("userId", userId);
            return query.getResultList();
        } catch (Exception ex) {
            logger.error("#getDayOffByUser ERROR ", ex);
            throw ex;
        }
    }
    public List<DayOff> getDayOffByListId(Session session, Long orgId, List<Long> objIds) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From DayOff d ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And d.orgId = :orgId");
            sql.append("   And d.id in (:objIds) ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameterList("objIds", objIds);
            return (List<DayOff>) query.getResultList();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public boolean deleteDayOffIds(Session session, List<Long> objIds) {
        logger.info("#deletecheckInImages START -- ");
        try {

            Transaction t = session.getTransaction();
            if (t == null || !t.isActive()) {
                t = session.beginTransaction();
            }

            StringBuilder sql = new StringBuilder();
            sql.append(" DELETE DAY_OFF WHERE id in (:objIds) ");

            Query query = session.createNativeQuery(sql.toString());
            query.setParameterList("objIds", objIds);
            query.executeUpdate();

            session.flush();
            t.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            logger.error("#deletecheckInImages Exception: ", e);
        }
        return true;
    }

}
