/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import vn.supperapp.apigw.beans.BlogInfo;
import vn.supperapp.apigw.beans.NewsPromotionInfo;
import vn.supperapp.apigw.beans.PagingResult;
import vn.supperapp.apigw.db.dto.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Taink
 */
public class BankDAO extends BaseDAO {

    public BankDAO() {
        this.logger = LogManager.getLogger(BankDAO.class);
    }

    public List<Bank> getListBank(Session session, Long orgId, String language) throws Exception {
        logger.info("#getListBank");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From Bank i ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And i.status = 1 ");

            Query query = session.createQuery(sql.toString());
            return query.list();
        } catch (Exception ex) {
            logger.error("#getListBank ERROR ", ex);
            throw ex;
        }
    }
    public Bank getBankById(Session session, Long id, Long orgId, String language) throws Exception {
        logger.info("#getBankById");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From Bank a ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And a.id = :id");
            sql.append("   And a.orgId = :orgId");

            Query query = session.createQuery(sql.toString());
            query.setParameter("id", id);
            query.setParameter("orgId", orgId);

            return (Bank) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getBankById ERROR ", ex);
            throw ex;
        }
    }

}
