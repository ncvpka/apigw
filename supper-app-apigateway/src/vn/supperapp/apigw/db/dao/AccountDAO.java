/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import vn.supperapp.apigw.db.dto.Account;
import vn.supperapp.apigw.db.dto.UserBank;

/**
 * @author TruongLe
 */
public class AccountDAO extends BaseDAO {

    public AccountDAO() {
        this.logger = LogManager.getLogger(AccountDAO.class);
    }

    public Account getAccountById(Session session, Long accountId) throws Exception {
        logger.info("#getAccountById");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From Account ac ");
            sql.append(" Where ac.id = :accountId ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("accountId", accountId);

            return (Account) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getAccountById ERROR ", ex);
            throw ex;
        }
    }
    public UserBank getUserBankByUser(Session session, Long userId) throws Exception {
        logger.info("#getUserBankByUser");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From UserBank u ");
            sql.append(" Where u.userId = :userId ");
            sql.append("   And u.status = 1 ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("userId", userId);

            return (UserBank) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getUserAppDeviceById ERROR ", ex);
            throw ex;
        }
    }
}
