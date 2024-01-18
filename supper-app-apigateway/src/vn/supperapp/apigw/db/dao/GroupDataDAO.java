package vn.supperapp.apigw.db.dao;

import vn.supperapp.apigw.beans.GroupDataInfo;
import vn.supperapp.apigw.db.dto.GroupData;
import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class GroupDataDAO extends BaseDAO {

    public GroupDataDAO() {
        this.logger = LogManager.getLogger(GroupDataDAO.class);
    }

    public List<GroupDataInfo> getListGroupData(Session session, String language) {
        logger.info("#getListGroupData  START -- ");
        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT g.ID, g.code, (select ac.config_Value from App_Config ac where ac.obj_Type = 'GROUP_DATA' and ac.obj_id = g.id and ac.config_key = g.name and ac.language = :language ) name, g.service_code serviceCode, g.partner_code partnerCode, g.status ");
            sql.append(" FROM group_data g ");
            sql.append(" WHERE 1 = 1  and g.status = 1 ");
            sql.append(" order by g.id asc ");

            NativeQuery<GroupDataInfo> query = session.createNativeQuery(sql.toString(), GroupData.GROUP_DATA_MAPPING);
            query.setParameter("language", language);
            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("#getListMessages Exception: ", e);
            throw e;
        }
    }

    public GroupData getGroupDateByCode(Session session, String code) {
        logger.info("#getListGroupData  START -- ");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From GroupData g ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And g.status = 1 ");
            sql.append("   And g.code = :code ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("code", code);

            return (GroupData) query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("#getListMessages Exception: ", e);
        }
        return null;
    }
}
