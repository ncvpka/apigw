/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import vn.supperapp.apigw.beans.BlogTagInfo;
import vn.supperapp.apigw.beans.TimeKeepingInfo;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dto.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Taink
 */
public class BlogTagDAO extends BaseDAO {

    public BlogTagDAO() {
        this.logger = LogManager.getLogger(BlogTagDAO.class);
    }

    public List<BlogTag> getBlogTagById(Session session, Long id, Long orgId, String language) throws Exception {
        logger.info("#getBlogTagById");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From BlogTag s ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And s.orgId = :orgId");
            sql.append("   And s.objId = :id");
            sql.append("   And s.type = 'BLOG'");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameter("id", id);

            List<BlogTag> list = (List<BlogTag>) query.getResultList();
            if (list == null)
            {
                list = new ArrayList<BlogTag>();
            }
            return list;
        } catch (Exception ex) {
            logger.error("#getBlogTagById ERROR ", ex);
            throw ex;
        }
    }
    public List<BlogTag> getBlogTagByIds(Session session, List<Long> objIds, Long orgId, String language) throws Exception {
        logger.info("#getBlogTagById");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From BlogTag s ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And s.orgId = :orgId");
            sql.append("   And s.objId in (:objIds) ");
            sql.append("   And s.type = 'BLOG'");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameterList("objIds", objIds);

            List<BlogTag> list = (List<BlogTag>) query.getResultList();
            if (list == null)
            {
                list = new ArrayList<BlogTag>();
            }
            return list;
        } catch (Exception ex) {
            logger.error("#getBlogTagById ERROR ", ex);
            throw ex;
        }
    }
    public List<BlogTagInfo> getBlogTagByIdsPersonalSchedule(Session session, List<Long> objIds, Long orgId, String language) throws Exception {
        logger.info("#getBlogTagById");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" Select n.id, n.USER_ID as userId, n.OBJ_ID as objId, a.FULL_NAME as fullName, n.TYPE as objType, n.CREATE_AT as createAt, n.CREATE_BY as createBy ");
            sql.append(" From BLOG_TAG n JOIN APP_USER a on n.USER_ID = a.ID ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And n.org_Id = :orgId");
            sql.append("   And n.obj_Id in (:objIds) ");
            sql.append("   And n.type = 'PERSONAL_SCHEDULE'");
            NativeQuery<BlogTagInfo> query = session.createNativeQuery(sql.toString(), BlogTag.BLOG_TAG_MAPPING);
            query.setParameter("orgId", orgId);
            query.setParameterList("objIds", objIds);
            return query.getResultList();
        } catch (Exception ex) {
            logger.error("#getBlogTagById ERROR ", ex);
            throw ex;
        }
    }
    public List<BlogTagInfo> getBlogTagByIdsCompanySchedule(Session session, List<Long> objIds, Long orgId, String language) throws Exception {
        logger.info("#getBlogTagByIdsCompanySchedule");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" Select n.id, n.USER_ID as userId, n.OBJ_ID as objId, a.FULL_NAME as fullName, n.TYPE as objType, n.CREATE_AT as createAt, n.CREATE_BY as createBy ");
            sql.append(" From BLOG_TAG n JOIN APP_USER a on n.USER_ID = a.ID ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And n.org_Id = :orgId");
            sql.append("   And n.obj_Id in (:objIds) ");
            sql.append("   And n.type = 'COMPANY_SCHEDULE'");
            NativeQuery<BlogTagInfo> query = session.createNativeQuery(sql.toString(), BlogTag.BLOG_TAG_MAPPING);
            query.setParameter("orgId", orgId);
            query.setParameterList("objIds", objIds);
            return query.getResultList();
        } catch (Exception ex) {
            logger.error("#getBlogTagByIdsCompanySchedule ERROR ", ex);
            throw ex;
        }
    }
    public List<BlogTag> getPersonalTagById(Session session, Long id, Long orgId, String language) throws Exception {
        logger.info("#getBlogTagById");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From BlogTag s ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And s.orgId = :orgId");
            sql.append("   And s.objId = :id");
            sql.append("   And s.type = 'PERSONAL_SCHEDULE'");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameter("id", id);

            return (List<BlogTag>) query.getResultList();
        } catch (Exception ex) {
            logger.error("#getBlogTagById ERROR ", ex);
            throw ex;
        }
    }
    public boolean deleteAllTagByBlogId(Session session, Long id) {
        logger.info("#deletecheckInImages START -- ");
        try {

            Transaction t = session.getTransaction();
            if (t == null || !t.isActive()) {
                t = session.beginTransaction();
            }

            StringBuilder sql = new StringBuilder();
            sql.append(" DELETE BLOG_TAG WHERE obj_Id = :id AND type = 'BLOG'");

            Query query = session.createNativeQuery(sql.toString());
            query.setParameter("id", id);
            query.executeUpdate();

            session.flush();
            t.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            logger.error("#deletecheckInImages Exception: ", e);
        }
        return true;
    }
    public String getNameByAppUser(Session session, Long appUserId, Long orgId) throws Exception {
        String name = "";
        logger.info("#getAppUserBy");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppUser au ");
            sql.append(" Where au.id = :appUserId and au.orgId = :orgId ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("appUserId", appUserId);
            query.setParameter("orgId", orgId);

           AppUser appUser = (AppUser) query.uniqueResult();
           if(appUser != null)
           {
              return name = appUser.getFullName();
           }
           else
           {
               return null;
           }
        } catch (Exception ex) {
            logger.error("#getAppUserBy ERROR ", ex);
            throw ex;
        }
    }
}
