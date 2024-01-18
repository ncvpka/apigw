package vn.supperapp.apigw.db.dao;

import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.db.DbSessionMgt;
import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InviteDAO extends BaseDAO {

    public InviteDAO() {
        this.logger = LogManager.getLogger(InviteDAO.class);
    }


    public List<String> getListInviteNumber(Session session, UserLoggedInfo loggedInfo, String msisdnArr) {
        logger.info("#getListInviteNumber START -- ");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" Select invitedNumber ");
            sql.append(" From InviteLog i ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And i.status = 1 ");
            sql.append("   And i.referenceNumber = :referenceNumber ");
            sql.append("   And i.invitedNumber in (:invitedNumber) ");
            sql.append(" Order by i.createDate desc ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("referenceNumber", loggedInfo.getUserInfo().getAccountNumber());
            query.setParameterList("invitedNumber", Arrays.asList(msisdnArr.split(",")));

            return query.list();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("#getListInviteNumber Exception: ", e);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return Collections.emptyList();
    }
}
