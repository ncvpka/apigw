/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import vn.supperapp.apigw.beans.NewsPromotionInfo;
import vn.supperapp.apigw.beans.PagingResult;
import vn.supperapp.apigw.db.dto.AppConfig;
import vn.supperapp.apigw.db.dto.AppImage;
import vn.supperapp.apigw.db.dto.News;
import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author TruongLe
 */
public class NewsDAO extends BaseDAO {

    public NewsDAO() {
        this.logger = LogManager.getLogger(NewsDAO.class);
    }

    public List<NewsPromotionInfo> getListTopNews4Home(Session session, String language, int numRecord) throws Exception {
        logger.info("#getListTopNews4Home");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" Select n.id, n.news_Type newsType, n.status, n.transaction_news transactionNews, n.transaction_data transactionData, ");
            sql.append("   (to_char(n.start_Time, 'dd/MM/yyyy HH24:MI:SS')) startTime, ");
            sql.append("   (to_char(n.end_Time, 'dd/MM/yyyy HH24:MI:SS')) endTime, ");
            sql.append("   (to_char(n.create_Time, 'dd/MM/yyyy HH24:MI:SS')) createTime, ");
            sql.append("   n.content_Type contentType, ");
            sql.append("   (select ac.config_Value from App_Config ac where (ac.obj_Type = 'NEWS' or ac.obj_Type = 'PROMOTION') and ac.obj_Id = n.id and ac.config_Key = n.title and ac.language = :language) title, ");
            sql.append("   (select ac.config_Value from App_Config ac where (ac.obj_Type = 'NEWS' or ac.obj_Type = 'PROMOTION') and ac.obj_Id = n.id and ac.config_Key = n.short_Content and ac.language = :language) shortContent, ");
            sql.append("   (select ac.config_Value from App_Config ac where (ac.obj_Type = 'NEWS' or ac.obj_Type = 'PROMOTION') and ac.obj_Id = n.id and ac.config_Key = n.full_Content and ac.language = :language) fullContent ");
            sql.append(" From News n ");
            sql.append(" Where 1 = 1 ");
            sql.append("   and n.status = 1 ");
            sql.append("   and (n.client_Type = 'BOTH' or n.client_Type = 'END_USER_APP' or n.client_Type = 'ENDUSER') ");
            sql.append("   and (sysdate between n.start_Time and n.end_Time) ");
            sql.append("   and n.show_At_Home = 1 ");
            sql.append("   and n.hot_News = 1 ");
            sql.append(" Order by n.create_Time desc ");

            NativeQuery<NewsPromotionInfo> query = session.createNativeQuery(sql.toString(), News.NEWS_PROMOTION_MAPPING);
            query.setParameter("language", language);
            query.setMaxResults(numRecord);

            return query.getResultList();
        } catch (Exception ex) {
            logger.error("#getListTopNews4Home ERROR ", ex);
            throw ex;
        }
    }

    public List<NewsPromotionInfo> getListNews4Home(Session session, String language, int numRecord) throws Exception {
        logger.info("#getListNews4Home");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" Select n.id, n.news_Type newsType, n.status, n.transaction_news transactionNews, n.transaction_data transactionData, ");
            sql.append("   (to_char(n.start_Time, 'dd/MM/yyyy HH24:MI:SS')) startTime, ");
            sql.append("   (to_char(n.end_Time, 'dd/MM/yyyy HH24:MI:SS')) endTime, ");
            sql.append("   (to_char(n.create_Time, 'dd/MM/yyyy HH24:MI:SS')) createTime, ");
            sql.append("   n.content_Type contentType, ");
            sql.append("   (select ac.config_Value from App_Config ac where (ac.obj_Type = 'NEWS' or ac.obj_Type = 'PROMOTION') and ac.obj_Id = n.id and ac.config_Key = n.title and ac.language = :language) title, ");
            sql.append("   (select ac.config_Value from App_Config ac where (ac.obj_Type = 'NEWS' or ac.obj_Type = 'PROMOTION') and ac.obj_Id = n.id and ac.config_Key = n.short_Content and ac.language = :language) shortContent, ");
            sql.append("   (select ac.config_Value from App_Config ac where (ac.obj_Type = 'NEWS' or ac.obj_Type = 'PROMOTION') and ac.obj_Id = n.id and ac.config_Key = n.full_Content and ac.language = :language) fullContent ");
            sql.append(" From News n ");
            sql.append(" Where 1 = 1 ");
            sql.append("   and n.status = 1 ");
            sql.append("   and (n.client_Type = 'BOTH' or n.client_Type = 'END_USER_APP' or n.client_Type = 'ENDUSER')"); //1: active; 2: favourite
            sql.append("   and (sysdate between n.start_Time and n.end_Time) "); //1: active; 2: favourite
            sql.append("   and n.show_At_Home = 1 "); //1: active; 2: favourite
            sql.append(" Order by n.create_Time desc, n.hot_News desc ");

            NativeQuery<NewsPromotionInfo> query = session.createNativeQuery(sql.toString(), News.NEWS_PROMOTION_MAPPING);
            query.setParameter("language", language);
            query.setMaxResults(numRecord);

            return query.getResultList();
        } catch (Exception ex) {
            logger.error("#getListNews4Home ERROR ", ex);
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

    public List<NewsPromotionInfo> getListTopNews(Session session, String language, int numRecord) throws Exception {
        logger.info("#getListTopNews");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" Select n.id, n.news_Type newsType, n.status, n.transaction_news transactionNews, n.transaction_data transactionData,");
            sql.append("   (to_char(n.start_Time, 'dd/MM/yyyy HH24:MI:SS')) startTime, ");
            sql.append("   (to_char(n.end_Time, 'dd/MM/yyyy HH24:MI:SS')) endTime, ");
            sql.append("   (to_char(n.create_Time, 'dd/MM/yyyy HH24:MI:SS')) createTime, ");
            sql.append("   n.content_Type contentType, ");
            sql.append("   (select ac.config_Value from App_Config ac where (ac.obj_Type = 'NEWS' or ac.obj_Type = 'PROMOTION') and ac.obj_Id = n.id and ac.config_Key = n.title and ac.language = :language) title, ");
            sql.append("   (select ac.config_Value from App_Config ac where (ac.obj_Type = 'NEWS' or ac.obj_Type = 'PROMOTION') and ac.obj_Id = n.id and ac.config_Key = n.short_Content and ac.language = :language) shortContent, ");
            sql.append("   (select ac.config_Value from App_Config ac where (ac.obj_Type = 'NEWS' or ac.obj_Type = 'PROMOTION') and ac.obj_Id = n.id and ac.config_Key = n.full_Content and ac.language = :language) fullContent ");
            sql.append(" From News n ");
            sql.append(" Where 1 = 1 ");
            sql.append("   and n.status = 1 ");
            sql.append("   and (n.client_Type = 'BOTH' or n.client_Type = 'END_USER_APP' or n.client_Type = 'ENDUSER') ");
            sql.append("   and (sysdate between n.start_Time and n.end_Time) ");
            sql.append("   and n.hot_News = 1 ");
            sql.append(" Order by n.create_Time desc ");

            NativeQuery<NewsPromotionInfo> query = session.createNativeQuery(sql.toString(), News.NEWS_PROMOTION_MAPPING);
            query.setParameter("language", language);
            query.setMaxResults(numRecord);

            return query.getResultList();
        } catch (Exception ex) {
            logger.error("#getListTopNews ERROR ", ex);
            throw ex;
        }
    }

    public PagingResult getListNews(Session session, String language, int pageSize, int pageNumber) {
        logger.info("#getListNews");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From News n ");
            sql.append(" Where 1 = 1 ");
            sql.append("   and n.status = 1 ");
            sql.append("   and (n.client_Type = 'BOTH' or n.client_Type = 'END_USER_APP') ");
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
            sqlSelect.append("   (select ac.config_Value from App_Config ac where ac.obj_Type = 'NEWS' and ac.obj_Id = n.id and ac.config_Key = n.title and ac.language = :language) title, ");
            sqlSelect.append("   (select ac.config_Value from App_Config ac where ac.obj_Type = 'NEWS' and ac.obj_Id = n.id and ac.config_Key = n.short_Content and ac.language = :language) shortContent, ");
            sqlSelect.append("   (select ac.config_Value from App_Config ac where ac.obj_Type = 'NEWS' and ac.obj_Id = n.id and ac.config_Key = n.full_Content and ac.language = :language) fullContent ");
            sqlSelect.append(sql);
            sqlSelect.append(" Order by n.create_Time desc, n.hot_News desc ");

            NativeQuery<BigDecimal> queryCount = session.createNativeQuery(sqlCount.toString());
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

            NativeQuery<NewsPromotionInfo> querySelect = session.createNativeQuery(sqlSelect.toString(), News.NEWS_PROMOTION_MAPPING);
            querySelect.setParameter("language", language);
            querySelect.setFirstResult((pageNumber - 1) * pageSize);
            querySelect.setMaxResults(pageSize);

            List<NewsPromotionInfo> list = querySelect.list();
            result.setResults(list);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#getListNews");
        }
        return new PagingResult(0, 0, 1, pageSize, null);
    }

    public List<NewsPromotionInfo> getListPromotion(Session session, String language, int pageSize, int type) {
        logger.info("#getListPromotion START -- ");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" Select n.id, n.news_Type newsType, n.status, n.transaction_news transactionNews, n.transaction_data transactionData, ");
            sql.append("   (to_char(n.start_Time, 'dd/MM/yyyy HH24:MI:SS')) startTime, ");
            sql.append("   (to_char(n.end_Time, 'dd/MM/yyyy HH24:MI:SS')) endTime, ");
            sql.append("   (to_char(n.create_Time, 'dd/MM/yyyy HH24:MI:SS')) createTime, ");
            sql.append("   n.content_Type contentType, ");
            sql.append("   (select ac.config_Value from App_Config ac where (ac.obj_Type = 'NEWS' or ac.obj_Type = 'PROMOTION') and ac.obj_Id = n.id and ac.config_Key = n.title and ac.language = :language) title, ");
            sql.append("   (select ac.config_Value from App_Config ac where (ac.obj_Type = 'NEWS' or ac.obj_Type = 'PROMOTION') and ac.obj_Id = n.id and ac.config_Key = n.short_Content and ac.language = :language) shortContent, ");
            sql.append("   (select ac.config_Value from App_Config ac where (ac.obj_Type = 'NEWS' or ac.obj_Type = 'PROMOTION') and ac.obj_Id = n.id and ac.config_Key = n.full_Content and ac.language = :language) fullContent ");
            sql.append(" From News n ");
            sql.append(" Where 1 = 1 ");
            sql.append("   and n.status = 1 ");
            sql.append("   and n.show_At_Home = 1 ");
            sql.append("   and (n.client_Type = 'BOTH' or n.client_Type = 'END_USER_APP' or n.client_Type = 'ENDUSER') ");
            sql.append("   and (sysdate between n.start_Time and n.end_Time) ");

            if (type == 1) {
                sql.append(" and n.hot_deal = 1 ");
            } else if (type == 2) {
                sql.append(" and n.big_vourcher = 1 ");
            } else if (type == 3) {
                sql.append(" and n.promotion = 1 ");
            }
            else if (type == 4) {
                sql.append(" and n.hotnewsHighlights = 1 ");
            }

            sql.append(" Order by n.create_Time desc ");

            NativeQuery<NewsPromotionInfo> query = session.createNativeQuery(sql.toString(), News.NEWS_PROMOTION_MAPPING);
            query.setParameter("language", language);
            query.setMaxResults(pageSize);

            return query.getResultList();
        } catch (Exception ex) {
            logger.error("#getListPromotion ERROR ", ex);
            throw ex;
        }
    }

    public PagingResult getListPromotionNews(Session session, String language, int pageSize, int pageNumber, int type) {
        logger.info("#getListPromotionNews START -- ");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From News n ");
            sql.append(" Where 1 = 1 ");
            sql.append("   and n.status = 1 ");
            sql.append("   and (n.client_Type = 'BOTH' or n.client_Type = 'END_USER_APP' or n.client_Type = 'ENDUSER') ");
            sql.append("   and (sysdate between n.start_Time and n.end_Time) ");

            if (type == 1) {
                sql.append(" and n.hot_deal = 1 ");
            } else if (type == 2) {
                sql.append(" and n.big_vourcher = 1 ");
            } else if (type == 3) {
                sql.append(" and n.promotion = 1 ");
            }

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
            sqlSelect.append(" Order by n.create_Time desc, n.hot_News desc ");

            NativeQuery<BigDecimal> queryCount = session.createNativeQuery(sqlCount.toString());
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

            NativeQuery<NewsPromotionInfo> querySelect = session.createNativeQuery(sqlSelect.toString(), News.NEWS_PROMOTION_MAPPING);
            querySelect.setParameter("language", language);
            querySelect.setFirstResult((pageNumber - 1) * pageSize);
            querySelect.setMaxResults(pageSize);

            List<NewsPromotionInfo> list = querySelect.list();
            result.setResults(list);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#getListPromotionNews EXCEPTION: ", ex);
        }
        return new PagingResult(0, 0, 1, pageSize, null);
    }

    public List<NewsPromotionInfo> getCategoryPromotion(Session session, String language) {
        logger.info("#getCategoryPromotion START -- ");
        List<NewsPromotionInfo> newsPromotionInfoList = new ArrayList<>();
        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" From AppConfig ap ");
            sql.append(" Where 1 = 1 ");
            sql.append(" And ap.language = :language ");
            sql.append(" And ap.featureCode = 'PROMOTION' ");
            sql.append(" And ap.objValue = 'TITLE' ");
            sql.append(" And ap.status = :status ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("language", language);
            query.setParameter("status", 1);

            List<AppConfig> appConfigList = (List<AppConfig>) query.getResultList();
            for (int i = 0; i < appConfigList.size(); i++) {
                newsPromotionInfoList.add(new NewsPromotionInfo(appConfigList.get(i).getObjId(), appConfigList.get(i).getConfigValue()));
            }
            return newsPromotionInfoList;

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#getCategoryPromotion EXCEPTION: ", ex);
        }
        return Collections.emptyList();
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
}
