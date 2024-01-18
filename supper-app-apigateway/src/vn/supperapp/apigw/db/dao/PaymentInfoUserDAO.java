/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import vn.supperapp.apigw.db.dto.PaymentInfoUser;
import vn.supperapp.apigw.db.dto.PaymentRequest;

/**
 * @author Taink
 */
public class PaymentInfoUserDAO extends BaseDAO {

    public PaymentInfoUserDAO() {
        this.logger = LogManager.getLogger(PaymentInfoUserDAO.class);
    }

    public PaymentInfoUser getPaymentInfoUser(Session session, Long orgId) throws Exception {
        logger.info("#getPaymentInfoUser");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From PaymentInfoUser d ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And d.orgId = :orgId");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            return (PaymentInfoUser) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getPaymentInfoUser ERROR ", ex);
            throw ex;
        }
    }
    public PaymentInfoUser getPaymentInfoUserById(Session session, Long orgId, Long id) throws Exception {
        logger.info("#getPaymentInfoUserById");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From PaymentInfoUser d ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And d.orgId = :orgId");
            sql.append("   And d.id = :id");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameter("id", id);
            return (PaymentInfoUser) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getPaymentInfoUserById ERROR ", ex);
            throw ex;
        }
    }
}
