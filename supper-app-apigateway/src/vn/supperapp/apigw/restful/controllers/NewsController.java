package vn.supperapp.apigw.restful.controllers;

import vn.supperapp.apigw.beans.NewsPromotionInfo;
import vn.supperapp.apigw.beans.PagingResult;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.configs.AppConfigurations;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.MessageDAO;
import vn.supperapp.apigw.db.dao.NewsDAO;
import vn.supperapp.apigw.db.dto.AppImage;
import vn.supperapp.apigw.db.dto.NotificationLog;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.news.NewsRequest;
import vn.supperapp.apigw.restful.models.news.NewsResponse;
import vn.supperapp.apigw.utils.ErrorCode;
import org.hibernate.Session;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/news")
@RsDefaultFilterMapping
@RsResponseFilterMapping
public class NewsController extends BaseController {
    private NewsDAO newsDao;

    public NewsController() {
        this.logger = LoggerFactory.getLogger(NewsController.class);
        newsDao = new NewsDAO();
    }

    private static final String CFG_NEWS_TOP_PAGESIZE = "news.top.page-size";
    private static final String CFG_NEWS_LIST_PAGESIZE = "news.list.page-size";

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse news(@NotNull NewsRequest request) {
        logger.info("#news - Start: " + request.toLogString());
        Session session = null;
        NewsResponse response = new NewsResponse(ErrorCode.SUCCESS, language);
        try {

            logger.info("#news Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#news Check session");
            if (!checkDbSession(session)) {
                logger.info("#news DB Connection error");
                return BaseResponse.commonError(language);
            }

            int topPageSize = AppConfigurations.shared().getConfigAsInt(CFG_NEWS_TOP_PAGESIZE);
            logger.info("#news - Get list news to show on BANNER area - Size = {}", topPageSize);
            List<NewsPromotionInfo> hotNews = newsDao.getListTopNews(session, language, topPageSize);

            int listPageSize = AppConfigurations.shared().getConfigAsInt(CFG_NEWS_LIST_PAGESIZE);

            logger.info("#news - Get list news to show - Size = {}", listPageSize);
            PagingResult pagingResult = newsDao.getListPromotionNews(session, language, listPageSize, request.getPage(), request.getPromotionType());

            logger.info("#news - filter ids to get images");
            List<Long> ids = new ArrayList<>();
            if (hotNews != null && !hotNews.isEmpty()) {
                List<Long> tmps = hotNews.stream().map(NewsPromotionInfo::getId).collect(Collectors.toList());
                ids.addAll(tmps);
            }

            List<NewsPromotionInfo> lst = null;
            if (pagingResult != null && pagingResult.getResults() != null && !pagingResult.getResults().isEmpty()) {
                lst = pagingResult.getResults();
            }

            if (lst != null) {
                List<Long> tmps = lst.stream().map(NewsPromotionInfo::getId).collect(Collectors.toList());
                ids.addAll(tmps);
            }

            logger.info("#news - Get images");
            List<AppImage> lstImages = null;
            if (ids != null && !ids.isEmpty()) {
                lstImages = newsDao.getListAppImage(session, ids, language);
            }

            logger.info("#news - filter images");
            if (lstImages != null && !lstImages.isEmpty()) {
                List<AppImage> finalLstImages = lstImages;
                if (hotNews != null && !hotNews.isEmpty()) {
                    hotNews.forEach(it -> {
                        List<AppImage> tmpImages = finalLstImages.stream().filter(im -> im.getObjId() == it.getId()).collect(Collectors.toList());
                        it.setImages(tmpImages);
                    });
                }

                if (lst != null && !lst.isEmpty()) {
                    lst.forEach(it -> {
                        List<AppImage> tmpImages = finalLstImages.stream().filter(im -> im.getObjId() == it.getId()).collect(Collectors.toList());
                        it.setImages(tmpImages);
                    });
                }
            }

            List<NewsPromotionInfo> newsPromotionInfos = newsDao.getCategoryPromotion(session, language);

            logger.info("#news - Response");
            response.setHotNews(hotNews);
//            response.setNews(news);
            response.setData(pagingResult);
            response.setCategoryPromotion(newsPromotionInfos);
            return response;
        } catch (Exception ex) {
            logExceptions("#news - Error: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

    @POST
    @Path("/getNewsById")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RsAuthFilterMapping(combine = "/news/getNewsById")
    public BaseResponse getNewsById(@NotNull NewsRequest request) {
        logger.info("#getNewsById - getNewsById: " + request.toLogString());
        Session session = null;
        NewsResponse response = new NewsResponse(ErrorCode.SUCCESS, language);
        MessageDAO messageDao;
        UserLoggedInfo loggedInfo;
        try {

            logger.info("#getNewsById validate data");
            if (request.getId() == null) {
                logger.info("#getNewsById - Validate: getId is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            logger.info("#getNewsById Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#getNewsById Check session");
            if (!checkDbSession(session)) {
                logger.info("#getNewsById DB Connection error");
                return BaseResponse.commonError(language);
            }

            NewsPromotionInfo news = newsDao.getNewsById(session, request.getId(), language);
            if (news == null) {
                logger.info("#getNewsById - ISO: news is null ");
                return BaseResponse.commonError(this.language);
            } else {
                if (request.getRefId() != null) {
                    loggedInfo = getUserLoggedInfo();
                    if (loggedInfo == null) {
                        logger.info("#getNewsById - Validate: loggedInfo is null");
                        return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
                    }
                    messageDao = new MessageDAO();
                    NotificationLog notificationLog = messageDao.getNotifycationByRefId(session, request.getRefId(), loggedInfo);
                    if (notificationLog != null) {
                        if (notificationLog.getStatus() != 1L) {
                            notificationLog.setStatus(1L);
                            messageDao.update(session, notificationLog);
                            logger.info("#getNewsById : update status success");
                        }
                    }

                }
            }

            List<Long> ids = new ArrayList<>();
            if (news != null) {
                ids.add(news.getId());
            }

            logger.info("#getNewsById - Get images");
            List<AppImage> lstImages = null;
            if (ids != null && !ids.isEmpty()) {
                lstImages = newsDao.getListAppImage(session, ids, language);
            }

            logger.info("#getNewsById - filter images");
            if (lstImages != null && !lstImages.isEmpty()) {
                List<AppImage> finalLstImages = lstImages;
                if (news != null) {
                    List<AppImage> tmpImages = finalLstImages.stream().filter(im -> im.getObjId() == news.getId()).collect(Collectors.toList());
                    news.setImages(tmpImages);
                }
            }

            response.setNewsInfo(news);
            return response;

        } catch (Exception e) {
            logExceptions("#getNewsById - Error: ", e);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

}
