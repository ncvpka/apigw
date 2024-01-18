package vn.supperapp.apigw.restful.controllers.blog;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.hibernate.Session;
import org.slf4j.LoggerFactory;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.configs.AppConfigurations;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.AppImageDAO;
import vn.supperapp.apigw.db.dao.BlogDAO;
import vn.supperapp.apigw.db.dao.BlogTagDAO;
import vn.supperapp.apigw.db.dto.*;
import vn.supperapp.apigw.messaging.MessagingClient;
import vn.supperapp.apigw.messaging.beans.Message;
import vn.supperapp.apigw.messaging.beans.MessageContent;
import vn.supperapp.apigw.objs.UploadConfigInfo;
import vn.supperapp.apigw.restful.controllers.BaseController;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.blog.BlogRequest;
import vn.supperapp.apigw.restful.models.blog.BlogResponse;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.Constants;
import vn.supperapp.apigw.utils.ErrorCode;
import vn.supperapp.apigw.utils.LanguageUtils;
import vn.supperapp.apigw.utils.enums.ChannelType;
import vn.supperapp.apigw.utils.enums.FileMgtUtils;
import vn.supperapp.apigw.utils.enums.Language;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/blog")
@RsDefaultFilterMapping
@RsAuthFilterMapping
@RsResponseFilterMapping
public class BlogController extends BaseController {
    private BlogTagDAO blogTagDAO;
    private BlogDAO blogDAO;
    private AppImageDAO appImageDAO;

    public BlogController() {
        this.logger = LoggerFactory.getLogger(BlogController.class);
        blogTagDAO = new BlogTagDAO();
        blogDAO = new BlogDAO();
        appImageDAO = new AppImageDAO();
    }

    @POST
    @Path("/create-blog")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse createBlog(@HeaderParam("content-length") long contentLength,
                                             @FormDataParam("fileImages") List<FormDataBodyPart> fileImages,
                                              @FormDataParam("category") String category,
                                              @FormDataParam("content") String content,
                                              @FormDataParam("tagList") String tagList) {

        logger.info("#createBlog - Start: fileImages={}", fileImages);
        Session session = null;
        BlogResponse response = new BlogResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo = null;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("#createBlog error : loggedInfo is null");
                return BaseResponse.commonError(language);
            }
            if (fileImages == null) {
                fileImages = new ArrayList<>();
            }
            logger.info("#homeInfo Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#homeInfo Check session");
            if (!checkDbSession(session)) {
                logger.info("#homeInfo DB Connection error");
                return BaseResponse.commonError(language);
            }
            logger.info("#createBlog validate data");

            if (CommonUtils.isNullOrEmpty(category) || CommonUtils.isNullOrEmpty(content) ) {
                logger.info("#createBlog - Validate: type invalid");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }
            Blog blog = new Blog();
            blog.setContent(content);
            blog.setCategory(Long.valueOf(category));
            blog.setOrgId(loggedInfo.getAppUser().getOrgId());
            blog.setUserId(loggedInfo.getAppUser().getId());
            blog.setStatus("APPROVE");
            blog.setCountLike(0L);
            blog.setCountView(0L);
            blog.setCountComment(0L);
            blog.setCreateAt(new Date());
            blog.setCreateBy(loggedInfo.getUserInfo().getAccountNumber());
            blogDAO.save(session, blog);
            if (blog != null)
            {
                if(!CommonUtils.isNullOrEmpty(tagList)){
                    String [] arrTagList = tagList.split(",");
                    if (blog.getId() != null && arrTagList.length > 0){
                        for(String item : arrTagList){
                            BlogTag blogTag = new BlogTag();
                                blogTag.setObjId(blog.getId());
                                blogTag.setType("BLOG");
                                blogTag.setUserId(Long.valueOf(item));
                                blogTag.setFullName(blogTagDAO.getNameByAppUser(session,Long.valueOf(item), loggedInfo.getAppUser().getOrgId()));
                                blogTag.setOrgId(loggedInfo.getAppUser().getOrgId());
                                blogTag.setCreateAt(new Date());
                                blogTag.setCreateBy(loggedInfo.getAppUser().getMsisdn());
                                blogTagDAO.save(session,blogTag);
                        }
                        sendSmsSuccessToCustomer(loggedInfo, blog, tagList);
                    }
                }
                if(blog.getId() != null && fileImages.size() > 0) {
                    UploadConfigInfo uploadConfig = AppConfigurations.shared().getUploadConfig();

                    logger.info("#checkInscene - Validate content length");
                    long allowLength = (uploadConfig.getMaxFileSize() * 1024 * 1024 * 4);
                    if (contentLength > allowLength) {
                        logger.info("#checkInscene - Validate: allowLength file max");
                        return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
                    }
                    for (FormDataBodyPart file : fileImages) {
                        if (!blogDAO.validateFileFormat(uploadConfig, file)) {
                            logger.info("#checkInscene - Validate: fileImage malformed");
                            return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
                        }
                    }

                    logger.info("#checkInscene - Upload file image");
                    for (FormDataBodyPart file : fileImages) {
                        String imagePath = blogDAO.uploadFile(loggedInfo.getAppUser().getMsisdn(), "blog", uploadConfig, file);
                        logger.info("#checkInscene Check session");
                        if (!checkDbSession(session)) {
                            logger.info("#checkInscene DB Connection error");
                            return BaseResponse.commonError(language);
                        }
                        AppImage appImage = new AppImage();
                        appImage.setLanguage(language);
                        appImage.setObjType("BLOG");
                        appImage.setObjId(blog.getId());
                        appImage.setStatus(1);
                        appImage.setImageType("BLOG_IMAGE");
                        appImage.setUrl(imagePath);
                        appImage.setCreateTime(new Date());
                        appImageDAO.save(session, appImage);
                    }
                }
            }
            response.setBlog(blog);
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#userUploadAvatar - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return BaseResponse.commonError(language);
    }

    @POST
    @Path("/delete-blog")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteBlog(@NotNull BlogRequest request) {
        logger.info("#deleteBlog - Start: " + request.toLogString());
        Session session = null;
        BlogResponse response = new BlogResponse(ErrorCode.SUCCESS, language);
        AppDevice device = null;
        UserLoggedInfo loggedInfo;
        try {
            if (CommonUtils.isNullOrEmpty(request.getId().toString())
            ) {
                logger.info("#deleteBlog - Validate: Account number invalid");
                return BaseResponse.build(ErrorCode.ERR_INVALID_INFORMATION, language);
            }
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("#deleteBlog error : loggedInfo is null");
                return BaseResponse.commonError(language);
            }
            logger.info("#deleteBlog Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#deleteBlog Check session");
            if (!checkDbSession(session)) {
                logger.info("#deleteBlog DB Connection error");
                return BaseResponse.commonError(language);
            }
            if (CommonUtils.isNullOrEmpty(request.getId().toString())  ) {
                logger.info("#deleteBlog - Validate: type invalid");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            Blog blog = blogDAO.getBlogById(session, request.getId(), loggedInfo.getAppUser().getOrgId(), language);
            if(blog != null)
            {
                List<AppImage> appImages = blogDAO.getAppImageByBlogId(session, blog.getId(), language);
                if(appImages.size() > 0)
                {
                    blogDAO.deleteAllImageByBlogId(session, blog.getId());
                    UploadConfigInfo uploadConfig = AppConfigurations.shared().getUploadConfig();
                    for (AppImage image : appImages){
                        String fullFolderPath = String.format("%s/%s", uploadConfig.getBaseDocumentFolder(), image.getUrl());
                        boolean b = FileMgtUtils.deleteFile(fullFolderPath);
                        if (!b)
                        {
                            logger.info("#deleteBlog - can not delete file");
                            return BaseResponse.build(ErrorCode.CAN_NOT_DELETE_FILE, language);
                        }
                    }

                }
                else
                {
                    logger.info("#deleteBlog - not have image");
                }
                List<BlogTag> blogTagList = blogTagDAO.getBlogTagById(session, blog.getId(),loggedInfo.getAppUser().getOrgId(), language);
                if(blogTagList.size() > 0)
                {
                        blogTagDAO.deleteAllTagByBlogId(session, blog.getId());
                }
                blogDAO.delete(session, blog.getClass(), blog.getId());
            }
            response = new BlogResponse(ErrorCode.SUCCESS, language);
            return response;
        } catch (Exception ex) {
            logExceptions("#homeInfo - Error: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    @POST
    @Path("/update-blog")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse update(@HeaderParam("content-length") long contentLength,
                                   @FormDataParam("id") Long id,
                                   @FormDataParam("fileImages") List<FormDataBodyPart> fileImages,
                                   @FormDataParam("category") String category,
                                   @FormDataParam("content") String content,
                                   @FormDataParam("tagList") String tagList) {

        logger.info("#update - Start: fileImages={}", fileImages);
        Session session = null;
        BlogResponse response = new BlogResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo = null;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("#update error : loggedInfo is null");
                return BaseResponse.commonError(language);
            }
            if (fileImages == null) {
                fileImages = new ArrayList<>();
            }
            logger.info("#update Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#update Check session");
            if (!checkDbSession(session)) {
                logger.info("#update DB Connection error");
                return BaseResponse.commonError(language);
            }
            logger.info("#update validate data");
            //region - Validate

            if (CommonUtils.isNullOrEmpty(category) || CommonUtils.isNullOrEmpty(content) || CommonUtils.isNullOrEmpty(id.toString())  ) {
                logger.info("#update - Validate: type invalid");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }
            Blog blog = blogDAO.getBlogById(session, id, loggedInfo.getAppUser().getOrgId(), language );
            if(blog != null)
            {
                blog.setContent(content);
                blog.setCategory(Long.valueOf(category));
                blog.setUpdateAt(new Date());
                blog.setUpdateBy(loggedInfo.getUserInfo().getAccountNumber());
                blogDAO.update(session, blog);
                    if(!CommonUtils.isNullOrEmpty(tagList)){
                        String [] arrTagList = tagList.split(",");
                        List<BlogTag> blogTagList = blogTagDAO.getBlogTagById(session, blog.getId(),loggedInfo.getAppUser().getOrgId(), language);
                        if(blogTagList.size() > 0)
                        {
                            blogTagDAO.deleteAllTagByBlogId(session, blog.getId());
                        }
                        if (blog.getId() != null && arrTagList.length > 0){
                            for(String item : arrTagList){
                                BlogTag blogTag = new BlogTag();
                                blogTag.setObjId(blog.getId());
                                blogTag.setType("BLOG");
                                blogTag.setUserId(Long.valueOf(item));
                                blogTag.setFullName(blogTagDAO.getNameByAppUser(session,Long.valueOf(item), loggedInfo.getAppUser().getOrgId()));
                                blogTag.setOrgId(loggedInfo.getAppUser().getOrgId());
                                blogTag.setCreateAt(new Date());
                                blogTag.setCreateBy(loggedInfo.getAppUser().getMsisdn());
                                blogTagDAO.save(session,blogTag);
                            }
                        }
                    }
                    if(fileImages.size() >= 0)
                    {
                        List<AppImage> appImages = blogDAO.getAppImageByBlogId(session, blog.getId(), language);
                        if(appImages.size() > 0)
                        {
                            blogDAO.deleteAllImageByBlogId(session, blog.getId());
                            UploadConfigInfo uploadConfig = AppConfigurations.shared().getUploadConfig();
                            for (AppImage image : appImages){
                                String fullFolderPath = String.format("%s/%s", uploadConfig.getBaseDocumentFolder(), image.getUrl());
                                boolean b = FileMgtUtils.deleteFile(fullFolderPath);
                                if (!b)
                                {
                                    logger.info("#updateCheckIn - can not delete file");
                                    return BaseResponse.build(ErrorCode.CAN_NOT_DELETE_FILE, language);
                                }
                            }

                        }
                    }
                    if(blog.getId() != null && fileImages.size() > 0) {
                        UploadConfigInfo uploadConfig = AppConfigurations.shared().getUploadConfig();

                        logger.info("#checkInscene - Validate content length");
                        long allowLength = (uploadConfig.getMaxFileSize() * 1024 * 1024 * 4);
                        if (contentLength > allowLength) {
                            logger.info("#checkInscene - Validate: allowLength file max");
                            return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
                        }
                        for (FormDataBodyPart file : fileImages) {
                            if (!blogDAO.validateFileFormat(uploadConfig, file)) {
                                logger.info("#checkInscene - Validate: fileImage malformed");
                                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
                            }
                        }

                        logger.info("#checkInscene - Upload file image");
                        for (FormDataBodyPart file : fileImages) {
                            String imagePath = blogDAO.uploadFile(loggedInfo.getUserInfo().getAccountNumber(), "blog", uploadConfig, file);
                            logger.info("#checkInscene Check session");
                            if (!checkDbSession(session)) {
                                logger.info("#checkInscene DB Connection error");
                                return BaseResponse.commonError(language);
                            }
                            AppImage appImage = new AppImage();
                            appImage.setLanguage(language);
                            appImage.setObjType("BLOG");
                            appImage.setObjId(blog.getId());
                            appImage.setStatus(1);
                            appImage.setImageType("BLOG_IMAGE");
                            appImage.setUrl(imagePath);
                            appImage.setCreateTime(new Date());
                            appImageDAO.save(session, appImage);
                        }
                    }
                response = new BlogResponse(ErrorCode.SUCCESS, language);
                return response;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#userUploadAvatar - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return BaseResponse.commonError(language);
    }
    private String sendSmsSuccessToCustomer(UserLoggedInfo loggedInfo, Blog blog , String listIdU) {
        try {
            String msgKey = "MSG_TAG_BLOG";

            MessageContent mc = MessageContent.builder()
                    .addReplacement("[user tag]", loggedInfo.getAppUser().getFullName())
                    .addReplacement("[title]", blog.getContent())
                    .addTemplate(Language.ENGLISH.key(), String.format("%s", LanguageUtils.getString(msgKey, Language.ENGLISH.key())))
                    .addTemplate(Language.VIETNAMESE.key(), String.format("%s", LanguageUtils.getString(msgKey, Language.VIETNAMESE.key())))
                    .addTitles(MessagingClient.shared().getApsTitle("personalSchedule"))
                    .build();

            Message message = Message.builder()
                    .setChannel(ChannelType.APIGW_ENDUSER.code())
                    .setReceiverObj("enduser")
                    .setSender(MessagingClient.shared().getSender())
                    .setReceiver(listIdU)
                    .setMessageContent(mc)
                    .setPushNotification(true)
                    .setAccountId(loggedInfo.getUserInfo().getAccountId())
                    .setNotificationType(Constants.NOTIFICATION_TYPE_BLOG)
                    .setObjType(Constants.OBJ_TYPE_TRANSACTION)
                    .setSendSms(false)
                    .build();

            logger.info(message.toJsonString());
            MessagingClient.shared().sendAsyncTask(message);

            return mc.getContents().get(language);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#sendSmsSuccessToCustomer - EXCEPTION: ", ex);
        }
        return "";

    }


}
