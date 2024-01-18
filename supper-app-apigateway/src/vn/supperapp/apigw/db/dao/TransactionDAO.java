/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.db.dto.TransactionTemplate;
import vn.supperapp.apigw.utils.enums.AppFeatureCode;
import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author TruongLe
 */
public class TransactionDAO extends BaseDAO {

    private static final String ALL = "ALL";

    public TransactionDAO() {
        this.logger = LogManager.getLogger(TransactionDAO.class);
    }

    public List<TransactionTemplate> getListTransactionTemplate(Session session, String msisdn, AppFeatureCode feature,
                                                                int status, int numRecord, UserLoggedInfo loggedInfo, String type) throws Exception {
        logger.info("#getTransactionTemplate");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From TransactionTemplate tx ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And tx.status = :status "); //1: active; 2: favourite
            sql.append("   And tx.msisdn = :msisdn ");
            sql.append("   And tx.accountId = :accountId ");
            sql.append("   And tx.featureCode = :featureCode ");
            if (type.equals(ALL)) {
                sql.append("   And tx.createTime >= trunc(add_months(sysdate, - 3), 'MONTH') ");
            }
            sql.append("   AND id = (SELECT MAX (id) FROM TransactionTemplate  WHERE msisdn = tx.msisdn AND accountId = tx.accountId AND featureCode = tx.featureCode AND tplAccountCode = tx.tplAccountCode AND tx.featureCode = :featureCode1) ");
            sql.append(" Order by tx.updateTime desc, tx.createTime desc ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("msisdn", msisdn);
            query.setParameter("status", status);
            query.setParameter("accountId", loggedInfo.getUserInfo().getAccountId());
            query.setParameter("featureCode", feature.code());
            query.setParameter("featureCode1", feature.code());

            if (!type.equals(ALL)) {
                query.setMaxResults(numRecord);
            }

            return query.list();
        } catch (Exception ex) {
            logger.error("#getTransactionTemplate ERROR ", ex);
            throw ex;
        }
    }

    public List<TransactionTemplate> getListTransactionTemplate(Session session, String msisdn, AppFeatureCode feature, int numRecord) throws Exception {
        logger.info("#getTransactionTemplate");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From TransactionTemplate tx ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And tx.status != 0"); //0: off; 1: active; 2: favourite
            sql.append("   And tx.msisdn = :msisdn ");
            sql.append(" Order by tx.updateTime desc, tx.createTime desc ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("msisdn", msisdn);

            query.setMaxResults(numRecord);

            return query.list();
        } catch (Exception ex) {
            logger.error("#getTransactionTemplate ERROR ", ex);
            throw ex;
        }
    }

    public void updateRecentTransaction(Session session, String msisdn, AppFeatureCode feature,
                                        UserLoggedInfo loggedInfo) throws Exception {
        logger.info("#deleteRecentTransaction");
        try {
            Transaction t = session.getTransaction();
            if (t == null || !t.isActive()) {
                t = session.beginTransaction();
            }
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE TransactionTemplate tx ");
            sql.append(" SET status = 0 ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And tx.status in ( 1, 2) ");
            sql.append("   And tx.tplAccountCode = :msisdn ");
            sql.append("   And tx.accountId = :accountId ");
            sql.append("   And tx.featureCode = :featureCode ");
            sql.append("   And tx.msisdn = :accountNumber ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("msisdn", msisdn);
            query.setParameter("accountId", loggedInfo.getUserInfo().getAccountId());
            query.setParameter("featureCode", feature.code());
            query.setParameter("accountNumber", loggedInfo.getUserInfo().getAccountNumber());
            query.executeUpdate();

            session.flush();
            t.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            logger.error("#deleteRecentTransaction Exception: ", e);
        } finally {
            session.close();
        }
    }
}
