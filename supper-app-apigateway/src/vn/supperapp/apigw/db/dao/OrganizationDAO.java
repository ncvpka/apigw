/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import vn.supperapp.apigw.beans.OrganizationInfo;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.db.dto.*;
import vn.supperapp.apigw.utils.enums.AppDeviceStatus;
import vn.supperapp.apigw.utils.enums.ClientType;
import vn.supperapp.apigw.utils.enums.UserCheckCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TruongLQ
 */
public class OrganizationDAO extends BaseDAO {

    public OrganizationDAO() {
        this.logger = LogManager.getLogger(OrganizationDAO.class);
    }

    public List<Organization> getOrganizationOfAccount(Session session, String accountNumber) throws Exception {
        logger.info("#getUserAppDeviceById");
        try {
            List<Organization> result;
            StringBuilder sql = new StringBuilder();
            sql.append(" Select o From Organization o ");
            sql.append(" Join AppUser a on o.id = a.orgId ");
            sql.append(" Where a.msisdn = :accountNumber ");
            sql.append("   And a.clientType = :clientType ");
            sql.append("   And o.status != :status ");
            sql.append("   And a.status != :status ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("accountNumber", accountNumber);
            query.setParameter("clientType", ClientType.END_USER_APP.value());
            query.setParameter("status", Long.valueOf(AppDeviceStatus.INACTIVE.value()));

            result = query.list();

            for (Organization item : result) {
                sql = new StringBuilder();
                sql.append(" Select b From Organization o ");
                sql.append(" Join Branch b on o.id = b.orgId ");
                sql.append(" Where o.id = :orgId ");
                sql.append("   And o.status != :status ");

                query = session.createQuery(sql.toString());
                query.setParameter("orgId", item.getId());
                query.setParameter("status", Long.valueOf(AppDeviceStatus.INACTIVE.value()));
                item.setListBranch(query.list());
            }

            return result;
        } catch (Exception ex) {
            logger.error("#getUserAppDeviceById ERROR ", ex);
            throw ex;
        }
    }

    public List<OrganizationInfo> getListOrgInfo(Session session, String accountNumber) throws Exception {
        List<OrganizationInfo> organizationInfoList = new ArrayList<>();
        List<Organization> listOrg = getOrganizationOfAccount(session, accountNumber);
        if (listOrg.size() > 0) {
            for (Organization organization : listOrg) {
                OrganizationInfo organizationInfo = new OrganizationInfo();
                organizationInfo.setId(organization.getId());
                organizationInfo.setCode(organization.getCode());
                organizationInfo.setName(organization.getName());
                organizationInfo.setAddress(organization.getAddress());
                organizationInfo.setPhone(organization.getPhone());
                organizationInfo.setBranchList(organization.getListBranch());
                organizationInfoList.add(organizationInfo);
            }
        }
    return organizationInfoList;
    }

}
