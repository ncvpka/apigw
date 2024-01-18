/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dto.Branch;
import vn.supperapp.apigw.db.dto.CheckInImages;

import java.util.List;

/**
 *
 * @author ManhDD
 */
public class BranchDAO extends BaseDAO {

    public BranchDAO() {
        this.logger = LogManager.getLogger(BranchDAO.class);
    }

    public boolean deleteAllImageByCheckInId(Session session, Long id) {
        logger.info("#deletecheckInImages START -- ");
        try {

            Transaction t = session.getTransaction();
            if (t == null || !t.isActive()) {
                t = session.beginTransaction();
            }

            StringBuilder sql = new StringBuilder();
            sql.append(" DELETE CHECK_IN_IMAGES WHERE CHECK_IN_ID = :checkInId");

            Query query = session.createNativeQuery(sql.toString());
            query.setParameter("checkInId", id);
            query.executeUpdate();

            session.flush();
            t.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            logger.error("#deletecheckInImages Exception: ", e);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return true;
    }
    public Branch getBranchById(Session session, Long orgId, Long id) throws Exception {
        logger.info("#getBranchById");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From Branch t ");
            sql.append("   Where t.id = :id");
            sql.append("   And t.orgId = :orgId");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameter("id", id);

            return (Branch) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getBranchById ERROR ", ex);
            throw ex;
        }
    }
    public List<Branch> getListBranch(Session session, Long orgId) throws Exception {
        logger.info("#getBranchById");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From Branch t ");
            sql.append("   Where t.orgId = :orgId");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);

            return query.getResultList();
        } catch (Exception ex) {
            logger.error("#getBranchById ERROR ", ex);
            throw ex;
        }
    }

}
