package vn.supperapp.apigw.db.dao;

import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import vn.supperapp.apigw.db.dto.ProductPropertiesValue;
import javax.transaction.Transactional;

public class ProductPropertiesValueDAO extends BaseDAO {


    public ProductPropertiesValueDAO() {
        this.logger = LogManager.getLogger(ProductPropertiesDAO.class);
    }



    public static ProductPropertiesValue getProductPropertiesValueById(Session session, String code) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From ProductPropertiesValue d ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And d.code = :code");

            Query query = session.createQuery(sql.toString());
            query.setParameter("code", code);
            return (ProductPropertiesValue) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }

    public static ProductPropertiesValue getProductPropertiesValueBy_Id(Session session, Long id) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From ProductPropertiesValue d ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And d.id = :id");

            Query query = session.createQuery(sql.toString());
            query.setParameter("id", id);
            return (ProductPropertiesValue) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    @Transactional
    public static void delete(Session session, Class<?> type, Long id) {
        logger.info("#BaseDAO.delete - start");
        try {
            Transaction t = session.getTransaction();
            if (t == null || !t.isActive()) {
                t = session.beginTransaction();
            }
            Object persistentInstance = session.load(type, id);
            if (persistentInstance != null) {
                session.delete(persistentInstance);
            }
            session.flush();
            t.commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            ex.printStackTrace();
            logger.error("#BaseDAO.delete failed: ", ex);
            throw ex;
        }
    }
}
