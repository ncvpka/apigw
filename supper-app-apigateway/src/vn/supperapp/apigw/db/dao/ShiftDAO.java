/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import vn.supperapp.apigw.db.dto.*;

import java.util.List;

/**
 * @author Taink
 */
public class ShiftDAO extends BaseDAO {

    public ShiftDAO() {
        this.logger = LogManager.getLogger(ShiftDAO.class);
    }

    public Shift getShiftAdministrative(Session session, Long orgId, String language) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From Shift s ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And s.status = 1");
            sql.append("   And s.orgId = :orgId");
            sql.append("   And s.timekeepingType = 2");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);

            return (Shift) query.setMaxResults(1);
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public Shift getShiftById(Session session, Long id, Long orgId, String language) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From Shift s ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And s.status = 1");
            sql.append("   And s.orgId = :orgId");
            sql.append("   And s.id = :id");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameter("id", id);

            return (Shift) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public List<Shift> listShift(Session session, Long orgId, String language) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From Shift s ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And s.status = 1");
            sql.append("   And s.orgId = :orgId");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);

            return query.getResultList();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
}
