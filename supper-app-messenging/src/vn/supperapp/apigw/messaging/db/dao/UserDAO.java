/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.messaging.db.dao;

import vn.supperapp.apigw.messaging.db.dto.AppDevice;
import vn.supperapp.apigw.messaging.db.dto.AppUser;
import vn.supperapp.apigw.utils.enums.AppDeviceStatus;
import vn.supperapp.apigw.utils.enums.ClientType;
import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 *
 * @author luand
 */
public class UserDAO extends BaseDAO {

    public UserDAO() {
        this.logger = LogManager.getLogger(UserDAO.class);
    }

    public  List<AppUser> getAppUserBy(Session session, List<Long> userIds, Long clientType) throws Exception {
        logger.info("#getAppUserBy: accountNumber");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppUser au ");
            sql.append(" Where au.id in (:ids) ");
            sql.append("   And au.clientType = :clientType ");

            Query query = session.createQuery(sql.toString());
            query.setParameterList("ids", userIds);
            query.setParameter("clientType", clientType);

            return query.list();
        } catch (Exception ex) {
            logger.error("#getAppUserBy ERROR ", ex);
            throw ex;
        }
    }

    public List<AppDevice> getListActiveDeviceSendNotification(Session session, List<Long> appUserId) throws Exception {
        logger.info("#getListActiveDeviceSendNotification");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppDevice ad ");
            sql.append(" Where ad.appUserId = :appUserId ");
            sql.append("   And ad.status = :status ");

            Query query = session.createQuery(sql.toString());
            query.setParameterList("appUserId", appUserId);
            query.setParameter("status", AppDeviceStatus.LOGGED_IN.value());

            return query.list();
        } catch (Exception ex) {
            logger.error("#getListActiveDeviceSendNotification ERROR ", ex);
            throw ex;
        }
    }

}
