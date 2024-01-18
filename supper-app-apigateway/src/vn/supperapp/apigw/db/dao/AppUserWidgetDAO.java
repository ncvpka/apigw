/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import vn.supperapp.apigw.db.dto.AppUserWidget;
import vn.supperapp.apigw.db.dto.AppWidget;
import vn.supperapp.apigw.db.dto.Organization;
import vn.supperapp.apigw.db.dto.TimeKeeping;
import vn.supperapp.apigw.utils.enums.AppDeviceStatus;
import vn.supperapp.apigw.utils.enums.ClientType;

import java.util.List;

/**
 * @author TruongLQ
 */
public class AppUserWidgetDAO extends BaseDAO {

    public AppUserWidgetDAO() {
        this.logger = LogManager.getLogger(AppUserWidgetDAO.class);
    }

    public AppWidget getWidgetByCode(Session session, Long orgId, String code) throws Exception {
        logger.info("#getTimeKeeping");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppWidget d");
            sql.append(" Where 1 = 1 ");
            sql.append("   And d.orgId = :orgId");
            sql.append("   And d.code = :code");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameter("code", code);
            return (AppWidget) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getDayOff ERROR ", ex);
            throw ex;
        }
    }
}
