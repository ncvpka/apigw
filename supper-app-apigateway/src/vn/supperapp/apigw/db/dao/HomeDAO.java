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
import vn.supperapp.apigw.beans.NewsPromotionInfo;
import vn.supperapp.apigw.beans.PagingResult;
import vn.supperapp.apigw.db.dto.*;
import vn.supperapp.apigw.utils.CommonUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Taink
 */
public class HomeDAO extends BaseDAO {
    private static final String BLOG_WORK = "WORK";
    private static final String BLOG_LIFE = "LIFE";
    private static final String TURNOVER = "TURNOVER";
    private static final String CUSTOMER = "CUSTOMER";
    private static final String WARNING = "WARNING";
    private static final String DEBT = "DEBT";
    private static final String PERSONAL = "PERSONAL";
    private static final String DELIVERY = "DELIVERY";

    public HomeDAO() {
        this.logger = LogManager.getLogger(HomeDAO.class);
    }

    public PagingResult getBlogForHome(Session session, Long orgId, String type, int pageSize, int pageNumber) throws Exception {
        logger.info("#getListTopNews4Home");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From blog n join blog_type a");
            sql.append(" on n.category = a.id join app_user u");
            sql.append(" on u.id = n.user_id");
            sql.append(" Where 1 = 1 ");
            sql.append("   and n.status = 'APPROVE' ");
            sql.append("   and n.org_id = :orgId");
            if(type.equals(BLOG_WORK)){
                sql.append("   And a.CODE = :code");
            }
            if(type.equals(BLOG_LIFE)){
                sql.append("   And a.CODE = :code");
            }

            StringBuilder sqlCount = new StringBuilder();
            sqlCount.append(" Select count(1) ");
            sqlCount.append(sql);

            StringBuilder sqlSelect = new StringBuilder();
            sqlSelect.append(" Select n.id, u.id as userId, u.full_name as fullName, u.avatar_url as avatarUrl, n.category, ");
            sqlSelect.append("  a.name as type, ");
            sqlSelect.append("  n.title, n.content, n.status, ");
            sqlSelect.append("  n.create_at as createAt, ");
            sqlSelect.append("   n.count_view as countView, n.count_like as countLike, n.count_comment as countComment ");
            sqlSelect.append(sql);
            sqlSelect.append(" Order by n.create_at desc");

            NativeQuery<BigDecimal> queryCount = session.createNativeQuery(sqlCount.toString());
            queryCount.setParameter("orgId", orgId);
            if(type.equals(BLOG_WORK)){
                queryCount.setParameter("code", BLOG_WORK);
            }
            if(type.equals(BLOG_LIFE)){
                queryCount.setParameter("code", BLOG_LIFE);
            }
            BigDecimal tmp = (BigDecimal) queryCount.uniqueResult();
            int count = 0;
            if (tmp != null) {
                count = tmp.intValue();
            }
            if (count == 0) {
                return new PagingResult(0, 0, 1, pageSize, null);
            }

            PagingResult result = new PagingResult();

            int totalRecords = count;
            result.setTotalRecords(totalRecords);
            result.setPageSize(pageSize);
            int totalPages = totalRecords / pageSize;
            if (totalPages * pageSize < totalRecords) {
                result.setTotalPages(totalPages + 1);
            } else {
                result.setTotalPages(totalPages);
            }
            result.setCurrentPage(pageNumber);
            if (pageNumber > result.getTotalPages()) {
                return result;
            }

            NativeQuery<BlogInfo> querySelect = session.createNativeQuery(sqlSelect.toString(), Blog.BLOG_MAPPING);
            querySelect.setParameter("orgId", orgId);
            if(type.equals(BLOG_WORK)){
                querySelect.setParameter("code", BLOG_WORK);
            }
            if(type.equals(BLOG_LIFE)){
                querySelect.setParameter("code", BLOG_LIFE);
            }
            querySelect.setFirstResult(pageNumber * pageSize);
            querySelect.setMaxResults(pageSize);

            List<BlogInfo> list = querySelect.getResultList();
            result.setResults(list);
            return result;
        } catch (Exception ex) {
            logger.error("#getListTopNews4Home ERROR ", ex);
            throw ex;
        }
    }
    public List<AppImage> getListAppImage(Session session, List<Long> objIds, String language) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppImage i ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And i.status = 1 ");
            sql.append("   And (i.objType = 'NEWS' or i.objType = 'PROMOTION') ");
            sql.append("   And i.objId in (:objIds) ");
            sql.append("   And i.language = :language ");
            sql.append(" Order by i.createTime desc ");

            Query query = session.createQuery(sql.toString());
            query.setParameterList("objIds", objIds);
            query.setParameter("language", language);

            return query.list();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }

    public List<AppImage> getListAppImageForBlog(Session session, List<Long> objIds) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppImage i ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And i.status = 1 ");
            sql.append("   And i.objType = 'BLOG'");
            sql.append("   And i.objId in (:objIds) ");
            sql.append(" Order by i.createTime desc ");

            Query query = session.createQuery(sql.toString());
            query.setParameterList("objIds", objIds);

            return query.list();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }

    public AppUserWidget getWidgetCurrentUser(Session session, Long userId, String language) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppUserWidget a ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And a.userId = :userId");

            Query query = session.createQuery(sql.toString());
            query.setParameter("userId", userId);

            return (AppUserWidget) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }

    public boolean checkLikeCurrentUser(Session session, Long userId, Long blogId) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From BlogLike a ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And a.blogId = :blogId");
            sql.append("   And a.userId = :userId");

            Query query = session.createQuery(sql.toString());
            query.setParameter("blogId", blogId);
            query.setParameter("userId", userId);
            BlogLike blogLike = (BlogLike) query.uniqueResult();
            if(blogLike != null) {
                return true;
            }
            else
                return false;
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }

    public AppWidget getAppWidgetCurrentUser(Session session, Long id) throws Exception {
        logger.info("#getAppUserWidget");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppWidget aw ");
            sql.append(" Where aw.id = :id");
            Query query = session.createQuery(sql.toString());
            query.setParameter("id", id);
            return (AppWidget) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getAppUserBy ERROR ", ex);
            throw ex;
        }
    }
    public NewsPromotionInfo getNewsById(Session session, Long id, String language) {
        logger.info("#getNewsById START -- ");
        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" From News n ");
            sql.append(" Where 1 = 1 ");
            sql.append("   and n.status = 1 ");
            sql.append("   and n.id = :id ");
            sql.append("   and n.TRANSACTION_NEWS = 1 ");
            sql.append("   and (n.client_Type = 'BOTH' or n.client_Type = 'END_USER_APP' or n.client_Type = 'ENDUSER') ");
            sql.append("   and (sysdate between n.start_Time and n.end_Time) ");

            StringBuilder sqlCount = new StringBuilder();
            sqlCount.append(" Select count(1) ");
            sqlCount.append(sql);

            StringBuilder sqlSelect = new StringBuilder();
            sqlSelect.append(" Select n.id, n.news_Type newsType, n.status, n.transaction_news transactionNews, n.transaction_data transactionData, ");
            sqlSelect.append("   (to_char(n.start_Time, 'dd/MM/yyyy HH24:MI:SS')) startTime, ");
            sqlSelect.append("   (to_char(n.end_Time, 'dd/MM/yyyy HH24:MI:SS')) endTime, ");
            sqlSelect.append("   (to_char(n.create_Time, 'dd/MM/yyyy HH24:MI:SS')) createTime, ");
            sqlSelect.append("   n.content_Type contentType, ");
            sqlSelect.append("   (select ac.config_Value from App_Config ac where (ac.obj_Type = 'NEWS' or ac.obj_Type = 'PROMOTION') and ac.obj_Id = n.id and ac.config_Key = n.title and ac.language = :language) title, ");
            sqlSelect.append("   (select ac.config_Value from App_Config ac where (ac.obj_Type = 'NEWS' or ac.obj_Type = 'PROMOTION') and ac.obj_Id = n.id and ac.config_Key = n.short_Content and ac.language = :language) shortContent, ");
            sqlSelect.append("   (select ac.config_Value from App_Config ac where (ac.obj_Type = 'NEWS' or ac.obj_Type = 'PROMOTION') and ac.obj_Id = n.id and ac.config_Key = n.full_Content and ac.language = :language) fullContent ");
            sqlSelect.append(sql);
            sqlSelect.append(" Order by n.create_Time desc ");

            NativeQuery<NewsPromotionInfo> querySelect = session.createNativeQuery(sqlSelect.toString(), News.NEWS_PROMOTION_MAPPING);
            querySelect.setParameter("id", id);
            querySelect.setParameter("language", language);

            NewsPromotionInfo newsPromotionInfo = querySelect.uniqueResult();
            return newsPromotionInfo;

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#getNewsById EXCEPTION: ", ex);
        }
        return null;
    }
    public List<Map<String, Map<String, String>>> dataWidget(String type)
    {
        List<Map< String, Map<String, String>>> total = new ArrayList<>();
        switch (type)
        {
            case TURNOVER:
            {
                Map<String, String> online  = new HashMap<>();
                Map< String, Map<String, String>> ol = new HashMap<>();
                Map< String, Map<String, String>> of = new HashMap<>();
                Map< String, Map<String, String>> bb = new HashMap<>();
                Map< String, Map<String, String>> rb = new HashMap<>();
                Map< String, Map<String, String>> cc = new HashMap<>();
                Map<String, String> offline  = new HashMap<>();
                Map<String, String> wholesale  = new HashMap<>();
                Map<String, String> reback  = new HashMap<>();
                Map<String, String> cancel  = new HashMap<>();
                online.put("day","10065400");
                online.put("week", "23223430000");
                online.put("month", "43223436500");
                online.put("total", "1535436343");
                ol.put("online", online);
                total.add(0, ol);
                offline.put("day","1006540098");
                offline.put("week", "23223438907");
                offline.put("month", "43223430450");
                offline.put("total", "15543543234");
                of.put("offline", offline);
                total.add(1, of);
                wholesale.put("day","10065454344");
                wholesale.put("week", "232234398076");
                wholesale.put("month", "432234398707");
                wholesale.put("total", "43007621343");
                bb.put("wholesale", wholesale);
                total.add(2, bb);
                reback.put("day","12");
                reback.put("week", "12");
                reback.put("month", "15");
                reback.put("total", "15");
                rb.put("reback", reback);
                total.add(3, rb);
                cancel.put("day","12");
                cancel.put("week", "12");
                cancel.put("month", "15");
                cancel.put("total", "15");
                cc.put("cancel", cancel);
                total.add(4, cc);
                return total;
            }
            case CUSTOMER: //customer
            {
                Map<String, String> customer  = new HashMap<>();
                Map< String, Map<String, String>> ct = new HashMap<>();
                customer.put("web","10000");
                customer.put("app","1221");
                customer.put("live","5354");
                customer.put("other","432");
                customer.put("social","1253");
                customer.put("total","18260");
                ct.put("customer", customer);
                total.add(0, ct);
                return total;
            }
            case PERSONAL: //personal
            {
                Map<String, String> personal  = new HashMap<>();
                Map< String, Map<String, String>> ps = new HashMap<>();
                personal.put("position","Manager");
                personal.put("image","image");
                personal.put("name","Alexander");
                personal.put("salary","6000000");
                personal.put("bonus", "1000000");
                personal.put("turnover", "150000000");
                personal.put("workDay","24");
                personal.put("late","90");
                personal.put("leaveDay","2");
                ps.put("personal", personal);
                total.add(0, ps);
                return total;
            }
            case DEBT: // debt
            {
                Map<String, String> recentDay = new HashMap<>();
                Map< String, Map<String, String>> rd = new HashMap<>();
                Map< String, Map<String, String>> de = new HashMap<>();
                Map<String, String> totalDebt = new HashMap<>();
                recentDay.put("pay", "1000");
                recentDay.put("recall", "2000");
                rd.put("recentDay", recentDay);
                total.add(0, rd);
                totalDebt.put("pay", "3416000");
                totalDebt.put("recall", "21981000");
                de.put("totalDebt", totalDebt);
                total.add(1, de);
                return total;

            }
            case WARNING:
            {
                Map<String, String> inventory = new HashMap<>();
                Map< String, Map<String, String>> iv = new HashMap<>();
                Map< String, Map<String, String>> de = new HashMap<>();
                Map<String, String> debt = new HashMap<>();
                inventory.put("nearlyBelow", "100");
                inventory.put("below", "50");
                inventory.put("above", "20");
                inventory.put("poor", "10");
                iv.put("inventory", inventory);
                total.add(0, iv);
                debt.put("nearlyRecall", "1087541204");
                debt.put("outDateRecall", "984170000");
                debt.put("nearlyPay", "175412000410");
                debt.put("outDatePay", "104104324322");
                de.put("debt", debt);
                total.add(1, de);
                return total;
            }
            case DELIVERY:
            {
                Map<String, String> delivery = new HashMap<>();
                Map< String, Map<String, String>> dv = new HashMap<>();
                delivery.put("image", "image");
                delivery.put("code", "2000123");
                delivery.put("name", "Hữu Tùng Ke");
                delivery.put("total", "100");
                delivery.put("received", "70");
                delivery.put("notReceived", "20");
                delivery.put("delivering", "10");
                delivery.put("overdue", "0");
                delivery.put("salary", "6000000");
                delivery.put("bonus", "1000");
                delivery.put("perMonth", "60");
                dv.put("delivery", delivery);
                total.add(0, dv);
                return total;
            }
        }
        return null;
    }

}
