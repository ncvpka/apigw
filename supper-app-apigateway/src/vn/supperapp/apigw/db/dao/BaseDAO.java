/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.db.dto.AppConfig;
import vn.supperapp.apigw.db.dto.TransactionTemplate;
import vn.supperapp.apigw.utils.enums.ClientType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

/**
 * @author TruongLev
 */
public class BaseDAO {

    protected static Logger logger;

    public BaseDAO() {
        this.logger = LogManager.getLogger(BaseDAO.class);
    }

    public static boolean checkSession(Session session) {
        return session != null;
    }

    public Long getSequence(Session session, String sequenceName) throws Exception {
        String sql = "select " + sequenceName + ".NEXTVAL from dual";
        NativeQuery query = session.createNativeQuery(sql);
        Object seqId = query.uniqueResult();
        if (seqId != null) {
            return Long.parseLong(seqId.toString());
        }
        return null;
    }

    protected void logCallMethod(String logName) {
        if (this.logger != null) {
            this.logger.info(logName);
        }
    }

    @Transactional
    public <T> T update(Session session, T obj) throws Exception {
        logger.info("#BaseDAO.update - start");
        try {
            Transaction t = session.getTransaction();
            if (t == null || !t.isActive()) {
                t = session.beginTransaction();
            }
            session.update(obj);
            session.flush();
            t.commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            ex.printStackTrace();
            logger.error("#BaseDAO.update failed: ", ex);
            throw ex;
        }
        return obj;
    }

    @Transactional
    public <T> T save(Session session, T obj) throws Exception {
        logger.info("#BaseDAO.update - start");
        try {
            Transaction t = session.getTransaction();
            if (t == null || !t.isActive()) {
                t = session.beginTransaction();
            }
            obj = (T) session.save(obj);
            session.flush();
            t.commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            ex.printStackTrace();
            logger.error("#BaseDAO.save failed: ", ex);
            throw ex;
        }
        return obj;
    }

    @Transactional
    public <T> List<T> saveAll(Session session, List<T> listObj) throws Exception {
        logger.info("#BaseDAO.saveAll - start");
        try {
            Transaction t = session.getTransaction();
            if (t == null || !t.isActive()) {
                t = session.beginTransaction();
            }
            for (int i = 0; i < listObj.size(); i++) {
                session.save(listObj.get(i));
            }
            session.flush();
            t.commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            ex.printStackTrace();
            logger.error("#BaseDAO.saveAll failed: ", ex);
            throw ex;
        }
        return listObj;
    }

    @Transactional
    public static void delete(Session session, Class<?> type, Long id) {
        logger.info("#BaseDAO.delete - start");
        try {
            Transaction t = session.getTransaction();
            if (t == null || !t.isActive()) {
                t = session.beginTransaction();
            }
            Object persistentInstance = session.load(type, id);
            if (persistentInstance != null) {
                session.delete(persistentInstance);
            }
            session.flush();
            t.commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            ex.printStackTrace();
            logger.error("#BaseDAO.delete failed: ", ex);
            throw ex;
        }
    }

    @Transactional
    public AppConfig getConfigValueHeader(Session session, String featureCode, String language) {
        logger.info("#BaseDAO.getConfigValueHeader - start");
        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" From AppConfig ap ");
            sql.append(" Where 1 = 1 ");
            sql.append(" And ap.language = :language ");
            sql.append(" And ap.featureCode = :featureCode ");
            sql.append(" And ap.objValue = 'HEADER' ");
            sql.append(" And ap.status = :status ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("language", language);
            query.setParameter("featureCode", featureCode);
            query.setParameter("status", 1);

            return (AppConfig) query.uniqueResult();

        } catch (Exception e) {
            logger.error("#getConfigValueHeader ERROR ", e);
            e.printStackTrace();
        }
        return null;
    }

    public TransactionTemplate getTransactionFavorite(Session session, UserLoggedInfo loggedInfo, String transId, String featureCode) {
        logger.info("#getTransactionFavorite START -- ");
        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" From TransactionTemplate ap ");
            sql.append(" Where 1 = 1 ");
            sql.append(" And ap.appDeviceId = :appDeviceId ");
            sql.append(" And ap.appUserId = :appUserId ");
            sql.append(" And ap.msisdn = :msisdn ");
            sql.append(" And ap.featureCode = :featureCode ");
            sql.append(" And ap.transId = :transId ");
            sql.append(" And ap.status = :status ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("appDeviceId", loggedInfo.getAppDevice().getId());
            query.setParameter("appUserId", loggedInfo.getAppUser().getId());
            query.setParameter("msisdn", loggedInfo.getUserInfo().getAccountNumber());
            query.setParameter("featureCode", featureCode);
            query.setParameter("transId", transId);
            query.setParameter("status", 1);

            return (TransactionTemplate) query.uniqueResult();

        } catch (Exception e) {
            logger.error("#getUrlLoginConfigByKey ERROR ", e);
            e.printStackTrace();
        }
        return null;
    }

    public List<AppConfig> getListIntroduce(Session session, String featureCode, String language) {
        logger.info("#getListIntroduce START -- ");
        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" From AppConfig ap ");
            sql.append(" Where 1 = 1 ");
            sql.append(" And ap.language = :language ");
            sql.append(" And ap.featureCode = :featureCode ");
            sql.append(" And ap.objType = :objType ");
            sql.append(" And ap.objValue = 'FULL_COTENT' ");
            sql.append(" And ap.configKey = :configKey ");
            sql.append(" And ap.status = :status ");
            sql.append(" order by ap.objId asc ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("language", language);
            query.setParameter("featureCode", featureCode);
            query.setParameter("objType", featureCode);
            query.setParameter("configKey", "introduce.".concat(featureCode.toLowerCase()));
            query.setParameter("status", 1);

            return (List<AppConfig>) query.getResultList();

        } catch (Exception e) {
            logger.error("#getListIntroduce ERROR ", e);
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public AppConfig getAppConfig(Session session, String featureCode, String configKey, ClientType clientType) {
        logger.info("#BaseDAO.getAppConfig - start");
        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" From AppConfig ap ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And ap.featureCode = :featureCode ");
            sql.append("   And ap.configKey = :configKey ");
            sql.append("   And ap.objValue = :objValue ");
            sql.append("   And ap.status = :status ");
            sql.append(" Order by ap.createTime desc ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("featureCode", featureCode);
            query.setParameter("configKey", configKey);
            query.setParameter("objValue", clientType.code());
            query.setParameter("status", 1);

            query.setMaxResults(1);

            return (AppConfig) query.uniqueResult();

        } catch (Exception e) {
            logger.error("#getAppConfig ERROR ", e);
            e.printStackTrace();
        }
        return null;
    }
}
