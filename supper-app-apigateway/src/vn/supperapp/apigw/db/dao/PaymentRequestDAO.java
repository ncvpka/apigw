/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import vn.supperapp.apigw.beans.DayOffInfo;
import vn.supperapp.apigw.db.dto.Branch;
import vn.supperapp.apigw.db.dto.DayOff;
import vn.supperapp.apigw.db.dto.DayOffType;
import vn.supperapp.apigw.db.dto.PaymentRequest;
import vn.supperapp.apigw.restful.models.dayoff.DayOffRequest;
import vn.supperapp.apigw.restful.models.payrequest.PaymentReqRequest;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.DateTimeUtils;

import java.util.Date;
import java.util.List;

/**
 * @author Taink
 */
public class PaymentRequestDAO extends BaseDAO {

    public PaymentRequestDAO() {
        this.logger = LogManager.getLogger(PaymentRequestDAO.class);
    }

    public List<PaymentRequest> getPaymentRequestApprove(Session session, Long orgId, PaymentReqRequest paymentReqRequest) throws Exception {
        logger.info("#getPaymentRequest");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From PaymentRequest d ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And (d.status = 2 OR d.status = 3 OR d.status = 4) ");
            if(paymentReqRequest.getFromDate() != null && paymentReqRequest.getToDate() != null){
                sql.append("   And a.createAt between TO_DATE(:fromDate, 'YYYY-MM-DD') and TO_DATE(:toDate, 'YYYY-MM-DD')");
            }
            if(paymentReqRequest.getBranchId() != null){
                sql.append("   And a.branchId = :branchId");
            }
            sql.append("   And d.orgId = :orgId");
            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            if(paymentReqRequest.getFromDate() != null && paymentReqRequest.getToDate() != null){
                query.setParameter("fromDate", paymentReqRequest.getFromDate());
                query.setParameter("toDate", paymentReqRequest.getToDate());
            }
            if(paymentReqRequest.getStatus() != null){
                query.setParameter("branchId", paymentReqRequest.getBranchId());
            }
            return (List<PaymentRequest>) query.list();
        } catch (Exception ex) {
            logger.error("#getPaymentRequest ERROR ", ex);
            throw ex;
        }
    }
    public int getTotalRecord(Session session, Long orgId) {
        logger.info("#getTotalRecordUnread START -- ");
        int total = 0;
        try {
            Query query = session.createNativeQuery("SELECT COUNT(ID) FROM PAYMENT_REQUEST WHERE ORG_ID = :orgId");
            query.setParameter("orgId", orgId);
            total = ((Number) query.getSingleResult()).intValue();
            return total;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("#getTotalRecordUnread Exception: ", e);
        }
        return 0;
    }
    public List<PaymentRequest> getPaymentRequestByUser(Session session, Long orgId, Long userId, PaymentReqRequest paymentReqRequest) throws Exception {
        logger.info("#getPaymentRequestByUser");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From PaymentRequest d ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And d.orgId = :orgId");
            sql.append("   And (d.idUser = :userId or 0 = :userId)");
            if(paymentReqRequest.getFromDate() != null && paymentReqRequest.getToDate() != null){
                sql.append("   And d.createAt between TO_DATE(:fromDate, 'YYYY-MM-DD') and TO_DATE(:toDate, 'YYYY-MM-DD')");
            }
            if(paymentReqRequest.getStatus() != null){
                sql.append("   And d.status = :status");
            }
            sql.append("   ORDER BY createAt DESC");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameter("userId", userId);
            if(paymentReqRequest.getFromDate() != null && paymentReqRequest.getToDate() != null){
                query.setParameter("fromDate", paymentReqRequest.getFromDate());
                query.setParameter("toDate", paymentReqRequest.getToDate());
            }
            if(paymentReqRequest.getStatus() != null){
                query.setParameter("status", paymentReqRequest.getStatus());
            }
            return (List<PaymentRequest>) query.list();
        } catch (Exception ex) {
            logger.error("#getPaymentRequestByUser ERROR ", ex);
            throw ex;
        }
    }

    public List<Branch> getListBranch(Session session, Long orgId) throws Exception {
        logger.info("#getListBranch");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From Branch d ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And d.orgId = :orgId");
            sql.append("   ORDER BY name ASC");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            return (List<Branch>) query.list();
        } catch (Exception ex) {
            logger.error("#getPaymentRequestByUser ERROR ", ex);
            throw ex;
        }
    }
    public PaymentRequest getPaymentRequestById(Session session, Long id, Long orgId) throws Exception {
        logger.info("#getPaymentRequestById");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From PaymentRequest d ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And d.id = :id");
            sql.append("   And d.orgId = :orgId");

            Query query = session.createQuery(sql.toString());
            query.setParameter("id", id);
            query.setParameter("orgId", orgId);
            return (PaymentRequest) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getPaymentRequestById ERROR ", ex);
            throw ex;
        }
    }
}
