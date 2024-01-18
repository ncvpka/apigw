/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.messaging.db.dao;

import vn.supperapp.apigw.utils.enums.AppDeviceStatus;
import vn.supperapp.apigw.utils.enums.ClientType;
import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author luand
 */
public class MessageDAO extends BaseDAO {

    public MessageDAO() {
        this.logger = LogManager.getLogger(MessageDAO.class);
    }

    public int getUnreadNotification(Session session, String accountNumber, String accountId) throws Exception {
        logger.info("#getAppUserBy: getUnreadNotification");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("Select count(1) From NotificationLog nl ");
            sql.append(" Where nl.msisdn = :accountNumber ");
            sql.append("  And nl.accountId = :accountId  ");
            sql.append("  And nl.status = 0 ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("accountNumber", accountNumber);
            query.setParameter("accountId", accountId);

            return ((Number) query.uniqueResult()).intValue();
        } catch (Exception ex) {
            logger.error("#getUnreadNotification ERROR ", ex);
            throw ex;
        }
    }
}
