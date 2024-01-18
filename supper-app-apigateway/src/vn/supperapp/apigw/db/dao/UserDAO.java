/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import vn.supperapp.apigw.db.dto.*;
import vn.supperapp.apigw.utils.enums.UserCheckCode;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.utils.enums.AppDeviceStatus;
import vn.supperapp.apigw.utils.enums.ClientType;
import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Taink
 */
public class UserDAO extends BaseDAO {

    public UserDAO() {
        this.logger = LogManager.getLogger(UserDAO.class);
    }

    public AppDevice getUserAppDeviceById(Session session, Long appDeviceId) throws Exception {
        logger.info("#getUserAppDeviceById");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppDevice ad ");
            sql.append(" Where ad.id = :appDeviceId ");
            sql.append("   And ad.status != :status ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("appDeviceId", appDeviceId);
            query.setParameter("status", AppDeviceStatus.INACTIVE.value());

            return (AppDevice) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getUserAppDeviceById ERROR ", ex);
            throw ex;
        }
    }

    public AppDevice getUserAppDeviceById(Session session, String deviceId) throws Exception {
        logger.info("#getUserAppDeviceById");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppDevice ad ");
            sql.append(" Where ad.deviceId = :deviceId ");
            sql.append("   And ad.status != :status ");
            sql.append("   ORDER BY ad.createTime desc");

            Query query = session.createQuery(sql.toString());
            query.setParameter("deviceId", deviceId);
            query.setParameter("status", AppDeviceStatus.INACTIVE.value());
            query.setMaxResults(1);
            return (AppDevice) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getUserAppDeviceById ERROR ", ex);
            throw ex;
        }
    }

    public AppDevice getUserAppDeviceByIdMsisdn(Session session, String deviceId, String msisdn) throws Exception {
        logger.info("#getUserAppDeviceById");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppDevice ad ");
            sql.append(" Where ad.deviceId = :deviceId ");
            sql.append("   And ad.msisdn = :msisdn ");
            sql.append("   And ad.status != :status ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("deviceId", deviceId);
            query.setParameter("msisdn", msisdn);
            query.setParameter("status", AppDeviceStatus.INACTIVE.value());

            return (AppDevice) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getUserAppDeviceById ERROR ", ex);
            throw ex;
        }
    }

    public List<AppDevice> getListActiveDevice(Session session, Long appUserId) throws Exception {
        logger.info("#getListActiveDevice");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppDevice ad ");
            sql.append(" Where ad.appUserId = :appUserId ");
            sql.append("   And ad.status != :status ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("appUserId", appUserId);
            query.setParameter("status", AppDeviceStatus.INACTIVE.value());

            return query.list();
        } catch (Exception ex) {
            logger.error("#getListActiveDevice ERROR ", ex);
            throw ex;
        }
    }

    public List<AppUser> getAppUserBy(Session session, String accountNumber) throws Exception {
        logger.info("#getAppUserBy");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppUser au ");
            sql.append(" Where au.msisdn = :accountNumber ");
            sql.append("   And au.clientType = :clientType ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("accountNumber", accountNumber);
            query.setParameter("clientType", ClientType.END_USER_APP.value());

            return  query.list();
        } catch (Exception ex) {
            logger.error("#getAppUserBy ERROR ", ex);
            throw ex;
        }
    }

    public AppUser getAppUserBy(Session session, Long appUserId) throws Exception {
        logger.info("#getAppUserBy");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppUser au ");
            sql.append(" Where au.id = :appUserId ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("appUserId", appUserId);

            return (AppUser) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getAppUserBy ERROR ", ex);
            throw ex;
        }
    }

    public List<AppUser> getUserBy(Session session, Long orgId) throws Exception {
        logger.info("#getUserBy");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppUser au ");
            sql.append(" Where au.orgId = :orgId ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);

            return  query.list();
        } catch (Exception ex) {
            logger.error("#getUserBy ERROR ", ex);
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

    public AppUser getAppUserByMsisdn(Session session, String msisdn) throws Exception {
        logger.info("#getAppUserByMsisdn");
        AppUser user = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppUser au ");
            sql.append(" Where au.msisdn = :msisdn ");
            sql.append("   And au.clientType = :clientType ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("msisdn", msisdn);
            query.setParameter("clientType", ClientType.END_USER_APP.value());
            user = (AppUser) query.uniqueResult();
            return user;
        } catch (Exception ex) {
            logger.error("#getAppUserByMsisdn ERROR ", ex);
            throw ex;
        }
    }
    public AppUser getAppUserByPhoneAngOrg(Session session, String msisdn, Long orgId) throws Exception {
        logger.info("#getAppUserBy");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppUser au ");
            sql.append(" Where au.msisdn = :msisdn ");
            sql.append("   And au.orgId = :orgId ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("msisdn", msisdn);
            query.setParameter("orgId", orgId);

            return (AppUser) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getAppUserBy ERROR ", ex);
            throw ex;
        }
    }
    public AppUser getAppUserByPhone(Session session, String msisdn) throws Exception {
        logger.info("#getAppUserBy");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppUser au ");
            sql.append(" Where au.msisdn = :msisdn order by createTime DESC  ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("msisdn", msisdn);
            query.setMaxResults(1);
            return (AppUser) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getAppUserBy ERROR ", ex);
            throw ex;
        }
    }
    public String getOrgName(Session session, Long id) throws Exception {
        logger.info("#getOrgName");
        AppUser user = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From Organization o ");
            sql.append(" Where o.id = :id ");
            sql.append("   And o.status = 1 ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("id", id);

            Organization organization = (Organization) query.uniqueResult();;
            return organization.getName();
        } catch (Exception ex) {
            logger.error("#getOrgName ERROR ", ex);
            throw ex;
        }
    }
    public Branch getBranchName(Session session, Long id, Long orgId) throws Exception {
        logger.info("#getOrgName");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From Branch o ");
            sql.append(" Where o.id = :id ");
            sql.append("   And o.status = 1 ");
            sql.append("   And o.orgId = :orgId ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("id", id);
            query.setParameter("orgId", orgId);

            Branch branch = (Branch) query.uniqueResult();;
            return branch;
        } catch (Exception ex) {
            logger.error("#getOrgName ERROR ", ex);
            throw ex;
        }
    }
}
