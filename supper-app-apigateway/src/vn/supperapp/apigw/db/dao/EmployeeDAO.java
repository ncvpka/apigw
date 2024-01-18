/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import vn.supperapp.apigw.beans.EmployeeInfo;
import vn.supperapp.apigw.beans.UserInfo;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.db.dto.*;
import vn.supperapp.apigw.utils.enums.AppDeviceStatus;
import vn.supperapp.apigw.utils.enums.ClientType;
import vn.supperapp.apigw.utils.enums.UserCheckCode;

import java.util.List;

/**
 * @author Taink
 */
public class EmployeeDAO extends BaseDAO {

    public EmployeeDAO() {
        this.logger = LogManager.getLogger(EmployeeDAO.class);
    }


    public HrEmployee getEmployeeByAppUser(Session session, Long appUserId, Long orgId) throws Exception {
        logger.info("#getEmployeeByAppUser");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From HrEmployee e ");
            sql.append(" Where e.appUserId = :appUserId ");
            sql.append("   And e.orgId = :orgId ");


            Query query = session.createQuery(sql.toString());
            query.setParameter("appUserId", appUserId);
            query.setParameter("orgId", orgId);

            return (HrEmployee) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getEmployeeByAppUser ERROR ", ex);
            throw ex;
        }
    }
    public EmployeeInfo getUserInfo(Session session, Long appUserId, Long orgId) throws Exception {
        logger.info("#getUserInfo");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From HrEmployee e ");
            sql.append(" Where e.appUserId = :appUserId ");
            sql.append("   And e.orgId = :orgId ");


            Query query = session.createQuery(sql.toString());
            query.setParameter("appUserId", appUserId);
            query.setParameter("orgId", orgId);

            return (EmployeeInfo) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getUserInfo ERROR ", ex);
            throw ex;
        }
    }

    public AppUserWidget getAppUserWidget(Session session, Long appUserId) throws Exception {
        logger.info("#getAppUserWidget");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppUserWidget aw ");
            sql.append(" Where aw.userId = :appUserId ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("appUserId", appUserId);

            return (AppUserWidget) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getAppUserBy ERROR ", ex);
            throw ex;
        }
    }

    public AppWidget getAppWidgetPersonal(Session session) throws Exception {
        logger.info("#getAppUserWidget");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppWidget aw ");
            sql.append(" Where aw.code = 'PERSONAL'");
            Query query = session.createQuery(sql.toString());
            return (AppWidget) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getAppUserBy ERROR ", ex);
            throw ex;
        }
    }

    public void inactiveAppDeviceWhenInit(Session session, String deviceId) throws Exception {
        logger.info("#inactiveAppDeviceWhenInit");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" update AppDevice ad ");
            sql.append(" set ad.status = :status ");
            sql.append(" Where ad.deviceId = :deviceId ");
            sql.append("   And ad.status != :status ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("deviceId", deviceId);
            query.setParameter("status", AppDeviceStatus.INACTIVE.value());

            query.executeUpdate();
        } catch (Exception ex) {
            logger.error("#inactiveAppDeviceWhenInit ERROR ", ex);
            throw ex;
        }
    }

    public AppConfig getUrlLoginConfigByKey(Session session, String language, String configKey, String objectType, String objectValue) {
        logger.info("#getUrlLoginConfigByKey START -- ");
        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" From AppConfig ap ");
            sql.append(" Where 1 = 1 ");
            sql.append(" And ap.language = :language ");
            sql.append(" And ap.configKey = :configKey ");
            sql.append(" And ap.objType = :objType ");
            sql.append(" And ap.objValue = :objValue ");
            sql.append(" And ap.status = :status ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("language", language);
            query.setParameter("configKey", configKey);
            query.setParameter("objType", objectType);
            query.setParameter("objValue", objectValue);
            query.setParameter("status", 1);

            return (AppConfig) query.uniqueResult();

        } catch (Exception e) {
            logger.error("#getUrlLoginConfigByKey ERROR ", e);
            e.printStackTrace();
        }
        return null;
    }

    public List<AppDevice> getListDeviceLogin(Session session, UserLoggedInfo loggedInfo, String deviceId) {
        logger.info("#getListDeviceLogin START -- ");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppDevice ad ");
            sql.append(" Where ad.deviceId != :deviceId ");
            sql.append(" AND ad.msisdn = :msisdn ");
            sql.append("   And ad.status = :status ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("deviceId", deviceId);
            query.setParameter("msisdn", loggedInfo.getUserInfo().getAccountNumber());
            query.setParameter("status", AppDeviceStatus.LOGGED_IN.value());

            return (List<AppDevice>) query.getResultList();
        } catch (Exception ex) {
            logger.error("#getListDeviceLogin ERROR ", ex);
            throw ex;
        }
    }
}
