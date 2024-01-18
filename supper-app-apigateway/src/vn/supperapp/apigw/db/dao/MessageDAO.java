package vn.supperapp.apigw.db.dao;

import vn.supperapp.apigw.beans.NotificationLogInfo;
import vn.supperapp.apigw.beans.PagingResult;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dto.NotificationLog;
import vn.supperapp.apigw.restful.models.NotificationRequest;
import vn.supperapp.apigw.utils.CommonUtils;
import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TruongLe
 * @Date 14/03/2022
 * @see MessageDAO
 */

public class MessageDAO extends BaseDAO {
    private static final String ALL = "ALL";
    private static final String UNREAD = "UNREAD";
    private static final String APPLICATION = "APPLICATION";
    private static final String COMPANY = "COMPANY";

    public MessageDAO() {
        this.logger = LogManager.getLogger(MessageDAO.class);
    }

    public PagingResult getListMessages(Session session, String language, UserLoggedInfo loggedInfo, int pageSize, int pageNumber, String type, NotificationRequest request) {
        logger.info("#getListMessages");
        try {
            if (CommonUtils.isNullOrEmpty(language)) {
                language = "ht";
            }
            StringBuilder sql = new StringBuilder();
            sql.append(" From NotificationLog nl ");
            sql.append(" Where 1 = 1 ");
            sql.append("   and nl.appUserId = :appUserId");
            if (type == null) {
                sql.append(" and nl.status in (0, 1, 2) ");
            }
            if (type.equals(UNREAD)) {
                    sql.append(" and nl.status = 0 ");
                }
            if (type.equals(COMPANY)) {
                    sql.append(" and nl.status in (0, 1, 2) ");
                    sql.append(" and nl.objType = 2 ");
                }
            if (type.equals(APPLICATION)) {
                    sql.append(" and nl.status in (0, 1, 2) ");
                    sql.append(" and nl.objType = 1 ");
                }


            StringBuilder sqlCount = new StringBuilder();
            sqlCount.append(" Select count(1) ");
            sqlCount.append(sql);

            StringBuilder sqlSelect = new StringBuilder();
            sqlSelect.append(" Select new vn.supperapp.apigw.beans.NotificationLogInfo(nl, '").append(language).append("') ");
            sqlSelect.append(sql);
            sqlSelect.append(" Order by nl.createTime desc ");

            Query queryCount = session.createQuery(sqlCount.toString());
            queryCount.setParameter("appUserId", loggedInfo.getAppUser().getId());

            Query<NotificationLogInfo> querySelect = session.createQuery(sqlSelect.toString());
            querySelect.setParameter("appUserId", loggedInfo.getAppUser().getId());

            Long count = (Long) queryCount.uniqueResult();
            if (count == 0) {
                return new PagingResult(0, 0, 1, pageSize, new ArrayList());
            }

            PagingResult result = new PagingResult();

            int totalRecords = count.intValue();
            result.setTotalRecords(totalRecords);
            result.setPageSize(pageSize);
            int totalPages = totalRecords / pageSize;
            if (totalPages * pageSize < totalRecords) {
                result.setTotalPages(totalPages + 1);
            } else {
                result.setTotalPages(totalPages);
            }
            result.setCurrentPage(pageNumber);
            if (pageNumber > result.getTotalPages()) {
                return null;
            }

            querySelect.setFirstResult((pageNumber - 1) * pageSize);
            querySelect.setMaxResults(pageSize);

            List<NotificationLogInfo> list = querySelect.list();

            result.setResults(list);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#getListMessages");
        }
        return null;
    }

    public NotificationLog getNotificationById(Session session, NotificationRequest request, UserLoggedInfo loggedInfo) {
        logger.info("#getNotifycationById START -- ");
        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" From NotificationLog ap ");
            sql.append(" Where 1 = 1 ");
            sql.append(" AND ap.id = :id ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("id", request.getId());

            return (NotificationLog) query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("#getListMessages Exception: ", e);
        }
        return null;
    }

    public int getTotalRecordUnread(Session session, UserLoggedInfo loggedInfo) {
        logger.info("#getTotalRecordUnread START -- ");
        int total = 0;
        try {

            Query query = session.createNativeQuery("SELECT COUNT(*) FROM notification_log WHERE status = 0 and create_time >= TRUNC(sysdate - 30) AND APP_USER_ID = :appUserId");
            query.setParameter("appUserId", loggedInfo.getAppUser().getId());
            total = ((Number) query.getSingleResult()).intValue();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("#getTotalRecordUnread Exception: ", e);
        }
        return total;
    }

    public void updateAllNotificationToRead(Session session, UserLoggedInfo loggedInfo, NotificationRequest request) {
        logger.info("#updateAllNotificationToRead START -- ");
        try {

            Transaction t = session.getTransaction();
            if (t == null || !t.isActive()) {
                t = session.beginTransaction();
            }

            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE notification_log SET status = 1 WHERE msisdn = :msisdn and (account_id = :account_id or APP_USER_ID = :appUserId)");
            if (request.getType().equals(ALL) || request.getType().equals(UNREAD)) {
                sql.append(" and status = 0 ");
            } else if (request.getType().equals(COMPANY)) {
                sql.append(" and status = 0 ");
                sql.append(" and obj_type = 1 ");
            } else if (request.getType().equals(APPLICATION)) {
                sql.append(" and status = 0 ");
                sql.append(" and obj_type = 2 ");
            }

            Query query = session.createNativeQuery(sql.toString());
            query.setParameter("msisdn", loggedInfo.getUserInfo().getAccountNumber());
            query.setParameter("account_id", loggedInfo.getUserInfo().getAccountId());
            query.setParameter("appUserId", loggedInfo.getAppUser().getId());
            query.executeUpdate();

            session.flush();
            t.commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            logger.error("#updateAllNotificationToRead Exception: ", e);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
    }

    public NotificationLog getNotifycationByRefId(Session session, String refId, UserLoggedInfo loggedInfo) {
        logger.info("#getNotifycationByRefId START -- ");
        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" From NotificationLog ap ");
            sql.append(" Where 1 = 1 ");
            sql.append(" AND ap.msisdn = :msisdn ");
            sql.append(" AND ap.refNotiId = :refId ");
            sql.append(" AND ap.appUserId = :appUserId ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("msisdn", loggedInfo.getUserInfo().getAccountNumber());
            query.setParameter("refId", refId);
            query.setParameter("appUserId", loggedInfo.getAppUser().getId());

            return (NotificationLog) query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("#getNotifycationByRefId Exception: ", e);
        }
        return null;
    }

}
