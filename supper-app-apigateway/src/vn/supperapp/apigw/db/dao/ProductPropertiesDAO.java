package vn.supperapp.apigw.db.dao;

import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import vn.supperapp.apigw.beans.ProductPropertiesInfo;
import vn.supperapp.apigw.db.dto.ProductProperties;

import java.util.List;


public class ProductPropertiesDAO extends BaseDAO {

    public ProductPropertiesDAO() {
        this.logger = LogManager.getLogger(ProductPropertiesDAO.class);
    }



    public static ProductProperties checkCode(Session session, String code) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From ProductProperties d ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And d.code = :code");

            Query query = session.createQuery(sql.toString());
            query.setParameter("code", code);
            return (ProductProperties) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public List<ProductPropertiesInfo> getProductPropertiesInfoById(Session session, Long id) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" Select d.id, d.NAME as name, d.IMAGE as image, d.CREATE_AT as createAt, d.UPDATE_AT as updateAt, d.CODE as code, t.PROPERTIES_ID as propertiesId, t.VALUE as value ");
            sql.append(" From PRODUCT_PROPERTIES d JOIN PRODUCT_PROPERTIES_VALUE t on d.id = t.properties_id ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And d.id = :id");
            Query<ProductPropertiesInfo> query = session.createNativeQuery(sql.toString(), ProductProperties.PRODUCT_PROPERTIES_MAPPING);
            query.setParameter("id", id);
            return query.getResultList();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public static ProductProperties getProductPropertiesById(Session session, Long id) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From ProductProperties d ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And d.id = :id");

            Query query = session.createQuery(sql.toString());
            query.setParameter("id", id);
            return (ProductProperties) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }


}

