package vn.supperapp.apigw.restful.controllers;

import vn.supperapp.apigw.beans.*;
import vn.supperapp.apigw.configs.AppConfigurations;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.*;
import vn.supperapp.apigw.db.dto.*;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.BaseShopResponse;
import vn.supperapp.apigw.restful.models.HomeRequest;
import vn.supperapp.apigw.restful.models.HomeResponse;
import vn.supperapp.apigw.restful.models.auth.LoginResponse;
import vn.supperapp.apigw.restful.models.schedule.PersonalScheduleResponse;
import vn.supperapp.apigw.restful.models.timekeeping.TimeKeepingRequest;
import vn.supperapp.apigw.restful.models.timekeeping.TimeKeepingResponse;
import vn.supperapp.apigw.utils.Constants;
import vn.supperapp.apigw.utils.ErrorCode;
import vn.supperapp.apigw.redis.RedisService;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.LanguageUtils;
import vn.supperapp.apigw.utils.enums.ClientType;
import org.hibernate.Session;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Path("/home")
@RsDefaultFilterMapping
@RsAuthFilterMapping
@RsResponseFilterMapping
public class HomeController extends BaseController {
    private static final String PERSONAL = "PERSONAL";
    private BlogTagDAO blogTagDAO;
    private HomeDAO homeDao;
    private UserDAO userDAO;
    private AppUserWidgetDAO appUserWidgetDAO;
    private static final String CFG_NEWS_LIST_PAGESIZE = "news.list.page-size";

    public HomeController() {
        this.logger = LoggerFactory.getLogger(HomeController.class);
        homeDao = new HomeDAO();
        userDAO = new UserDAO();
        appUserWidgetDAO = new AppUserWidgetDAO();
        blogTagDAO = new BlogTagDAO();
    }

    @POST
    @Path("/home-blog")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseShopResponse homeBlog(@NotNull HomeRequest request) {
        logger.info("#homeBlog - Start: " + request.toLogString());
        Session session = null;
        HomeResponse response = new HomeResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            session = DbSessionMgt.shared().getSession();
            logger.info("#homeBlog Check session");
            if (!checkDbSession(session)) {
                logger.info("#homeBlog DB Connection error");
                return BaseShopResponse.commonError(language);
            }
            loggedInfo = getUserLoggedInfo();
            int listPageSize = AppConfigurations.shared().getConfigAsInt(CFG_NEWS_LIST_PAGESIZE);
            logger.info("#homeBlog - Get listPageSize news to show - Size = {}", listPageSize);

            Long orgId = loggedInfo.getAppUser().getOrgId();
            logger.info("#homeBlog - Get list news to show on home BANNER");
            String type = "ALL";
            if(!CommonUtils.isNullOrEmpty(request.getType()))
            {
                type = request.getType();
            }
            PagingResult pagingResult = homeDao.getBlogForHome(session, orgId, type, listPageSize, request.getPage());

            logger.info("#homeBlog - filter ids to get images");
            List<Long> ids = new ArrayList<>();
            List<BlogInfo> lst = null;
            if (pagingResult != null && pagingResult.getResults() != null && !pagingResult.getResults().isEmpty()) {
                lst = pagingResult.getResults();
            }
            if (lst != null) {
                List<Long> tmps = lst.stream().map(BlogInfo::getId).collect(Collectors.toList());
                ids.addAll(tmps);
                Session finalSession = session;
                lst.forEach(it -> {
                    try {
                        it.setLike(homeDao.checkLikeCurrentUser(finalSession,loggedInfo.getAppUser().getId(),it.getId()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            logger.info("#homeBlog - Get images");
            List<AppImage> lstImages = null;
            List<BlogTag> tagInfos = null;
            if (ids != null && !ids.isEmpty()) {
                lstImages = homeDao.getListAppImageForBlog(session, ids);
                tagInfos = blogTagDAO.getBlogTagByIds(session, ids, loggedInfo.getAppUser().getOrgId(), language);
            }

            logger.info("#homeInfo - filter images");
            if (lstImages != null && !lstImages.isEmpty()) {
                List<AppImage> finalLstImages = lstImages;
                if (lst != null && !lst.isEmpty()) {
                    lst.forEach(it -> {
                        List<AppImage> beerDrinkers = finalLstImages.stream()
                                .filter(p -> p.getObjId().equals(it.getId())).collect(Collectors.toList());
                        it.setImages(beerDrinkers);
                    });
                }
            }
            if (tagInfos != null && !tagInfos.isEmpty()) {
                if (lst != null && !lst.isEmpty()) {
                    List<BlogTag> finalTagInfos = tagInfos;
                    lst.forEach(it -> {
                        List<BlogTag> tmpTags = finalTagInfos.stream().filter(im -> im.getObjId().equals(it.getId())).collect(Collectors.toList());
                        it.setBlogTags(tmpTags);
                    });
                }
            }
            logger.info("#homeBlog - Response");
            response.setData(pagingResult);
            return response;

        } catch (Exception ex) {
            logExceptions("#homeInfo - Error: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseShopResponse();
    }

    @POST
    @Path("/widget-data")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseShopResponse widgetData(@NotNull HomeRequest request) {
        logger.info("#widgetData - Start: " + request.toLogString());
        Session session = null;
        HomeResponse response = new HomeResponse(ErrorCode.SUCCESS, language);
        AppDevice device = null;
        UserLoggedInfo loggedInfo;
        try {
            device = getAppDevice();

            logger.info("#homeInfo Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#homeInfo Check session");
            if (!checkDbSession(session)) {
                logger.info("#homeInfo DB Connection error");
                return BaseShopResponse.commonError(language);
            }
            loggedInfo = getUserLoggedInfo();

            logger.info("#homeInfo - Check app version upgrade");
            String currentVersionCachedKey = "";
            if ("android".equals(device.getOsName())) {
                currentVersionCachedKey = Constants.ANDROID_CURRENT_VERSION_CACHED_KEY;
            } else if ("ios".equals(device.getOsName())) {
                currentVersionCachedKey = Constants.IOS_CURRENT_VERSION_CACHED_KEY;
            }
            logger.info("#homeInfo - Version key = {}", currentVersionCachedKey);
            AppConfig currentVersion = RedisService.shared().get(AppConfig.class, Constants.REDIS_DB_APP_CONFIG_CACHED, currentVersionCachedKey);

            if (currentVersion == null) {
                logger.info("#homeInfo - Not found current version in cached, start to get from DB");
                currentVersion = homeDao.getAppConfig(session, "APP_CONFIG", currentVersionCachedKey, ClientType.END_USER_APP);
                if (currentVersion != null) {
                    logger.info("#homeInfo - Got version info from DB, start to update cached");
                    RedisService.shared().set(Constants.REDIS_DB_APP_CONFIG_CACHED, currentVersionCachedKey, currentVersion, 10*60); //Save in 10 minutes only
                }
            }

            if (currentVersion != null) {
                logger.info("#homeInfo - Found current version, start to check app version");
                if (CommonUtils.isNullOrEmpty(this.getAppVersion())) {
                    logger.info("#homeInfo - Not found App Version in request header, no need to check app upgrade");
                } else if (CommonUtils.versionCompare(this.getAppVersion(), currentVersion.getConfigValue()) == -1) {
                    logger.info("#homeInfo - Found new version, start to process update require");
                    MobileAppVersionInfo appVersionInfo = new MobileAppVersionInfo(ClientType.END_USER_APP.value().intValue(), device.getOsName(), this.language, currentVersion);
                    String timeToShowUpgradeCachedKey = String.format("upgrade_%s_%s", device.getDeviceId(), device.getOsName());
                    String timeToShowUpgrade = RedisService.shared().get(Constants.REDIS_DB_DEVICE_CACHED, timeToShowUpgradeCachedKey);
                    if (CommonUtils.isNullOrEmpty(timeToShowUpgrade)) {
                        logger.info("#homeInfo - Show dialog upgrade in cached expired, require and set new one to cached");
                        appVersionInfo.setShowDialogUpgrade(1);
                        RedisService.shared().set(Constants.REDIS_DB_DEVICE_CACHED, timeToShowUpgradeCachedKey, 60*60); //Save in cached 60 minutes
                    } else {
                        appVersionInfo.setShowDialogUpgrade(0);
                    }
                    response.setNewAppVersion(appVersionInfo);
                }
            } else {
                logger.info("#homeInfo - Not found current version in DB also, no need to check app upgrade");
            }
            String type = "";
            if(!CommonUtils.isNullOrEmpty(request.getType()))
            {
                type = request.getType();
            }
            else
            {
                AppUserWidget appUserWidget = homeDao.getWidgetCurrentUser(session, loggedInfo.getAppUser().getId(),"en");
                if(appUserWidget == null)
                {
                    type = PERSONAL;
                }
                else
                {
                    AppWidget appWidget = homeDao.getAppWidgetCurrentUser(session,appUserWidget.getWidgetId());
                    type = appWidget.getCode();
                }
            }
            logger.info("#homeInfo - Response");
            response.setDataWidget(homeDao.dataWidget(type));
            response.setWidgetType(type);
            return response;

        } catch (Exception ex) {
            logExceptions("#homeInfo - Error: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseShopResponse();
    }

    @POST
    @Path("/update-dashboard")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseShopResponse updateDashboard(@NotNull HomeRequest request) {
        logger.info("#updateDashboard - Start: " + request.toLogString());
        Session session = null;
        HomeResponse response = new HomeResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseShopResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#info Check session");
            if (!checkDbSession(session)) {
                logger.info("#info DB Connection error");
                return BaseShopResponse.commonError(language);
            }
            if (CommonUtils.isNullOrEmpty(request.getType()))
            {
                logger.info("#updateDashboard - Validate: Signature invalid");
                return BaseShopResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            AppUserWidget appUserWidget = userDAO.getAppUserWidget(session, loggedInfo.getAppUser().getId());
            if(appUserWidget ==  null)
            {
                AppWidget appWidget = appUserWidgetDAO.getWidgetByCode(session, loggedInfo.getAppUser().getOrgId(), request.getType());
                if(appWidget == null)
                {
                    logger.info("#updateDashboard - Validate: Signature invalid");
                    return BaseShopResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
                }
                else
                {
                    AppUserWidget appUserWidget1 = new AppUserWidget();
                    appUserWidget1.setUserId(loggedInfo.getAppUser().getId());
                    appUserWidget1.setWidgetId(appWidget.getId());
                    appUserWidget1.setCreateAt(new Date());
                    appUserWidget1.setCreateBy(loggedInfo.getAppUser().getMsisdn());
                }
            }
            else
            {
                AppWidget appWidget = appUserWidgetDAO.getWidgetByCode(session, loggedInfo.getAppUser().getOrgId(), request.getType());
                if(appWidget == null)
                {
                    logger.info("#updateDashboard - Validate: Signature invalid");
                    return BaseShopResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
                }
                else
                {
                    appUserWidget.setWidgetId(appWidget.getId());
                    appUserWidget.setUpdateAt(new Date());
                    appUserWidget.setUpdateBy(loggedInfo.getAppUser().getMsisdn());
                    appUserWidgetDAO.update(session, appUserWidget);
                }
            }
            response = new HomeResponse(ErrorCode.SUCCESS, language);
            return response;
        } catch (Exception ex) {
            logExceptions("#login - Error: ", ex);
            ex.printStackTrace();
            logger.error("#login - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseShopResponse();
    }

    @POST
    @Path("/list-org")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse listOrg(@NotNull HomeRequest request) {
        logger.info("#homeBlog - Start: " + request.toLogString());
        Session session = null;
        LoginResponse response = new LoginResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            session = DbSessionMgt.shared().getSession();
            logger.info("#homeBlog Check session");
            if (!checkDbSession(session)) {
                logger.info("#homeBlog DB Connection error");
                return BaseResponse.commonError(language);
            }
            loggedInfo = getUserLoggedInfo();
            if(CommonUtils.isNullOrEmpty(request.getMsisdn()))
            {
                logger.info("#login - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            List<OrganizationInfo> organizationInfoList = new OrganizationDAO().getListOrgInfo(session, request.getMsisdn());
            if (organizationInfoList.size() > 0) {
                response.setListOrg(organizationInfoList);
                return response;
            } else {
                logger.info("#login DB Connection error");
                return BaseResponse.commonError(language);
            }

        } catch (Exception ex) {
            logExceptions("#homeInfo - Error: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

}
