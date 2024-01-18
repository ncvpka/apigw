package vn.supperapp.apigw.db.dao;


import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.db.dto.InviteReferralLink;
import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class InviteReferralLinkDAO extends BaseDAO {

    public InviteReferralLinkDAO() {
        this.logger = LogManager.getLogger(InviteReferralLinkDAO.class);
    }

    public InviteReferralLink getInviteReferralLinkByAccountNumber(Session session, UserLoggedInfo loggedInfo) {
        logger.info("#getInviteReferralLinkByAccountNumber START -- ");
        try {


            StringBuilder sql = new StringBuilder();
            sql.append(" from InviteReferralLink i ");
            sql.append(" where 1 = 1 ");
            sql.append(" and i.referralCode = :referralCode ");
            sql.append(" and i.msisdn = :msisdn ");
            sql.append(" and i.status = 1 ");
            sql.append(" and i.referralType = 1 ");
            sql.append(" and i.referralLink is not null ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("referralCode", loggedInfo.getUserInfo().getAccountNumber());
            query.setParameter("msisdn", loggedInfo.getUserInfo().getAccountNumber());

            return (InviteReferralLink) query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("#getInviteReferralLinkByAccountNumber Exception: ", e);
        }
        return null;
    }

}
