/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import vn.supperapp.apigw.beans.BlogInfo;
import vn.supperapp.apigw.beans.DropDown;
import vn.supperapp.apigw.db.dto.Blog;
import vn.supperapp.apigw.db.dto.HrDepartment;
import vn.supperapp.apigw.db.dto.HrParamValue;
import vn.supperapp.apigw.db.dto.Shift;
import vn.supperapp.apigw.utils.enums.HrCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Taink
 */
public class HrCommonDAO extends BaseDAO {

    public HrCommonDAO() {
        this.logger = LogManager.getLogger(HrCommonDAO.class);
    }

    public List<HrParamValue> getByParam(Session session, Long orgId, String code) throws Exception {
        logger.info("#getListHrParamValue");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From HrParamValue s ");
            sql.append(" Where s.status = 1");
            sql.append("   And s.orgId = :orgId");
            sql.append("   And s.code = :code");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameter("code", code);

            return (List<HrParamValue>) query.setMaxResults(1);
        } catch (Exception ex) {
            logger.error("#getListHrParamValue ERROR ", ex);
            throw ex;
        }
    }
    public HrParamValue getById(Session session, Long id) throws Exception {
        logger.info("#getHrParamValue");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From HrParamValue s ");
            sql.append(" Where s.id = :id");

            Query query = session.createQuery(sql.toString());
            query.setParameter("id", id);

            return (HrParamValue) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getHrParamValue ERROR ", ex);
            throw ex;
        }
    }

    public List<HrDepartment> getListDepartment(Session session, Long orgId) throws Exception {
        logger.info("#getListHrDepartment");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From HrDepartment s ");
            sql.append(" Where s.status = 1");
            sql.append("   And s.orgId = :orgId");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);

            return (List<HrDepartment>) query.setMaxResults(1);
        } catch (Exception ex) {
            logger.error("#getListHrDepartment ERROR ", ex);
            throw ex;
        }
    }
    public HrDepartment getDepartment(Session session, Long id) throws Exception {
        logger.info("#getHrDepartment");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From HrDepartment s ");
            sql.append(" Where s.id = :id");

            Query query = session.createQuery(sql.toString());
            query.setParameter("id", id);

            return (HrDepartment) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getHrDepartment ERROR ", ex);
            throw ex;
        }
    }

    public List<DropDown>  getDropDown(Session session, Long orgId, String type) throws Exception {
        logger.info("#getDropDown");
        List<DropDown> result=new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder();
            if (HrCategory.DEPARTMENT.is(type)) {
                sql.append("Select s.id as value, s.name as title From Hr_Department s ");
                sql.append(" Where s.status = 1");
                sql.append("   And s.org_Id = :orgId");

                NativeQuery<DropDown> querySelect = session.createNativeQuery(sql.toString(), HrParamValue.DROPDOWN_MAPPING);
                querySelect.setParameter("orgId", orgId);
                result = querySelect.getResultList();
            } else {
                sql.append("Select s.id as value, s.name as title From HR_PARAM_VALUE s join Hr_Param p on p.id = s.PARAM_ID");
                sql.append(" Where s.status = 1");
                sql.append("   And p.code = :type");
                sql.append("   And s.org_Id = :orgId");

                NativeQuery<DropDown> querySelect = session.createNativeQuery(sql.toString(), HrParamValue.DROPDOWN_MAPPING);
                querySelect.setParameter("orgId", orgId);
                querySelect.setParameter("type", type);
                result = querySelect.getResultList();
            }

            return result;
        } catch (Exception ex) {
            logger.error("#getDropDown ERROR ", ex);
            throw ex;
        }
    }
}
