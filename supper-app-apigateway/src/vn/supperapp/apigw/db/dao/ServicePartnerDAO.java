package vn.supperapp.apigw.db.dao;

import vn.supperapp.apigw.db.dto.ServicePartner;
import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ServicePartnerDAO extends BaseDAO {

    public ServicePartnerDAO() {
        this.logger = LogManager.getLogger(ServicePartnerDAO.class);
    }

    public ServicePartner getServicePartner(Session session, String code) {
        logger.info("#getServicePartner  START -- ");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From ServicePartner g ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And g.status = 1 ");
            sql.append("   And g.featureCode = :featureCode ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("featureCode", code);

            return (ServicePartner) query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("#getServicePartner Exception: ", e);
        }
        return null;
    }
}
