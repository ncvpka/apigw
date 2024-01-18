package vn.supperapp.apigw.db.dao;

import vn.supperapp.apigw.beans.GroupDataPackageInfo;
import vn.supperapp.apigw.db.dto.GroupDataPackage;
import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class GroupDataPackageDAO extends BaseDAO {

    public GroupDataPackageDAO() {
        this.logger = LogManager.getLogger(GroupDataPackageDAO.class);
    }

    public List<GroupDataPackageInfo> getListGroupDataPackage(Session session, String language) {
        logger.info("#getListGroupData  START -- ");
        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT g.id, g.code, ");
            sql.append(" (select ac.config_Value from App_Config ac where ac.obj_Type = 'GROUP_DATA_PACKAGE' and ac.obj_id = g.id and ac.config_key = g.title and ac.language = :language ) title, ");
            sql.append(" (select ac.config_Value from App_Config ac where ac.obj_Type = 'GROUP_DATA_PACKAGE' and ac.obj_id = g.id and ac.config_key = g.sub_title and ac.language = :language ) subTitle, ");
            sql.append(" (select ac.config_Value from App_Config ac where ac.obj_Type = 'GROUP_DATA_PACKAGE' and ac.obj_id = g.id and ac.config_key = g.description and ac.language = :language ) description, ");
            sql.append(" g.value, ");
            sql.append(" (select ac.config_Value from App_Config ac where ac.obj_Type = 'GROUP_DATA_PACKAGE' and ac.obj_id = g.id and ac.config_key = g.name and ac.language = :language) name, ");
            sql.append(" (select ac.config_Value from App_Config ac where ac.obj_Type = 'GROUP_DATA_PACKAGE' and ac.obj_id = g.id and ac.config_key = g.short_name and ac.language = :language) shortName, ");
            sql.append(" g.group_data_id groupDataId, ");
            sql.append(" g.URL ");
            sql.append(" FROM group_data_package g ");
            sql.append(" WHERE g.status = 1 ");
            sql.append(" order by g.id asc ");

            NativeQuery<GroupDataPackageInfo> query = session.createNativeQuery(sql.toString(), GroupDataPackage.GROUP_DATA_PACKAGE_MAPPING);
            query.setParameter("language", language);
            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("#getListMessages Exception: ", e);
            throw e;
        }
    }

    public GroupDataPackageInfo getGroupDataPackageByCode(Session session, String code, String language) {
        logger.info("#GroupDataPackageInfo  START -- ");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT g.id, g.code, ");
            sql.append(" (select ac.config_Value from App_Config ac where ac.obj_Type = 'GROUP_DATA_PACKAGE' and ac.obj_id = g.id and ac.config_key = g.title and ac.language = :language ) title, ");
            sql.append(" (select ac.config_Value from App_Config ac where ac.obj_Type = 'GROUP_DATA_PACKAGE' and ac.obj_id = g.id and ac.config_key = g.sub_title and ac.language = :language ) subTitle, ");
            sql.append(" (select ac.config_Value from App_Config ac where ac.obj_Type = 'GROUP_DATA_PACKAGE' and ac.obj_id = g.id and ac.config_key = g.description and ac.language = :language ) description, ");
            sql.append(" g.value, ");
            sql.append(" (select ac.config_Value from App_Config ac where ac.obj_Type = 'GROUP_DATA_PACKAGE' and ac.obj_id = g.id and ac.config_key = g.name and ac.language = :language) name, ");
            sql.append(" (select ac.config_Value from App_Config ac where ac.obj_Type = 'GROUP_DATA_PACKAGE' and ac.obj_id = g.id and ac.config_key = g.short_name and ac.language = :language) shortName, ");
            sql.append(" g.group_data_id groupDataId, ");
            sql.append(" g.URL ");
            sql.append(" FROM group_data_package g ");
            sql.append(" WHERE g.status = 1 ");
            sql.append(" And g.code = :code ");

            NativeQuery<GroupDataPackageInfo> query = session.createNativeQuery(sql.toString(), GroupDataPackage.GROUP_DATA_PACKAGE_MAPPING);
            query.setParameter("language", language);
            query.setParameter("code", code);

            return query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("#getListMessages Exception: ", e);
        }
        return null;
    }
}
