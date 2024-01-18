/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import vn.supperapp.apigw.beans.*;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dto.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Taink
 */
public class PersonalScheduleDAO extends BaseDAO {

    public PersonalScheduleDAO() {
        this.logger = LogManager.getLogger(PersonalScheduleDAO.class);
    }

    public PersonalSchedule getPersonalScheduleById(Session session , Long id, Long orgId) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From PersonalSchedule p ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And p.orgId = :orgId");
            sql.append("   And p.id = :id");
            sql.append("   And p.type = 'PERSONAL_SCHEDULE'");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameter("id", id);

            return (PersonalSchedule) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public PersonalSchedule deleteCompanyScheduleById(Session session , Long id, Long orgId) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From PersonalSchedule p ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And p.orgId = :orgId");
            sql.append("   And p.id = :id");
            sql.append("   And p.type = 'COMPANY_SCHEDULE'");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameter("id", id);

            return (PersonalSchedule) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public PersonalScheduleInfo getCompanyScheduleById(Session session, Long orgId, Long id) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" Select p.ID as id, p.USER_ID as userId, p.TITLE as title, p.CONTENT as content, p.START_DATE as startDate, p.TO_DATE as toDate, p.type as type, p.REMIND as remind, p.REPEAT as repeat, p.address as address, p.color as color, p.ALL_DAY as allDay ");
            sql.append(" From PERSONAL_SCHEDULE p");
            sql.append(" Where 1 = 1 ");
            sql.append("   And p.org_Id = :orgId");
            sql.append("   And p.type = 'COMPANY_SCHEDULE'");
            sql.append("   And p.id = :id");

            NativeQuery<PersonalScheduleInfo> query = session.createNativeQuery(sql.toString(), PersonalSchedule.PERSONAL_SCHEDULE_MAPPING);
            query.setParameter("orgId", orgId);
            query.setParameter("id", id);

            return query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public List<ScheduleConfig> getRemind(Session session, Long orgId, String language) throws Exception {
        logger.info("#getRemindAllDay");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From ScheduleConfig p ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And p.orgId = :orgId");
            sql.append("   And p.type = 'REMIND'");
            sql.append("   And p.language = :language");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameter("language", language);

            return (List<ScheduleConfig>) query.getResultList();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public List<ScheduleConfig> getRepeat(Session session, Long orgId, String language) throws Exception {
        logger.info("#getRemindAllDay");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From ScheduleConfig p ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And p.orgId = :orgId");
            sql.append("   And p.type = 'REPEAT'");
            sql.append("   And p.language = :language");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameter("language", language);

            return (List<ScheduleConfig>) query.getResultList();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public List<PersonalScheduleInfo> getPersonalSchedule(Session session, Long orgId, Long userId, String date) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" Select p.ID as id, p.USER_ID as userId, p.TITLE as title, p.CONTENT as content, p.START_DATE as startDate, p.TO_DATE as toDate, p.type as type, p.REMIND as remind, p.REPEAT as repeat, p.address as address, p.color as color, p.ALL_DAY as allDay ");
            sql.append(" From PERSONAL_SCHEDULE p");
            sql.append(" Where 1 = 1 ");
            sql.append("   And p.org_Id = :orgId");
            sql.append("   And p.type = 'PERSONAL_SCHEDULE'");
            sql.append("   And p.user_Id = :userId");
            sql.append("   And TO_CHAR(p.start_Date, 'DD-MM-YYYY') = :dateNow");
            sql.append("   order by create_At desc");

            NativeQuery<PersonalScheduleInfo> query = session.createNativeQuery(sql.toString(), PersonalSchedule.PERSONAL_SCHEDULE_MAPPING);
            query.setParameter("orgId", orgId);
            query.setParameter("userId", userId);
            query.setParameter("dateNow", date);

            return query.getResultList();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public List<PersonalScheduleInfo> getCompanySchedule(Session session, Long orgId) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" Select p.ID as id, p.USER_ID as userId, p.TITLE as title, p.CONTENT as content, p.START_DATE as startDate, p.TO_DATE as toDate, p.type as type, p.REMIND as remind, p.REPEAT as repeat, p.address as address, p.color as color, p.ALL_DAY as allDay ");
            sql.append(" From PERSONAL_SCHEDULE p");
            sql.append(" Where 1 = 1 ");
            sql.append("   And p.org_Id = :orgId");
            sql.append("   And p.type = 'COMPANY_SCHEDULE'");
            sql.append("   order by create_At desc");

            NativeQuery<PersonalScheduleInfo> query = session.createNativeQuery(sql.toString(), PersonalSchedule.PERSONAL_SCHEDULE_MAPPING);
            query.setParameter("orgId", orgId);
            return query.getResultList();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public boolean deleteAllTagByPSId(Session session, Long id) {
        logger.info("#deleteAllTagByPSId START -- ");
        try {

            Transaction t = session.getTransaction();
            if (t == null || !t.isActive()) {
                t = session.beginTransaction();
            }

            StringBuilder sql = new StringBuilder();
            sql.append(" DELETE BLOG_TAG WHERE obj_Id = :id AND type = 'PERSONAL_SCHEDULE'");

            Query query = session.createNativeQuery(sql.toString());
            query.setParameter("id", id);
            query.executeUpdate();

            session.flush();
            t.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            logger.error("#deleteAllTagByPSId Exception: ", e);
        }
        return true;
    }

}
