package vn.supperapp.apigw.restful.controllers.schedule;

import com.viettel.ewallet.utils.iso.msg.IsoObject;
import org.hibernate.Session;
import org.slf4j.LoggerFactory;
import vn.supperapp.apigw.beans.BlogInfo;
import vn.supperapp.apigw.beans.BlogTagInfo;
import vn.supperapp.apigw.beans.PersonalScheduleInfo;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.configs.AppConfigurations;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.AccountDAO;
import vn.supperapp.apigw.db.dao.BlogTagDAO;
import vn.supperapp.apigw.db.dao.PersonalScheduleDAO;
import vn.supperapp.apigw.db.dto.*;
import vn.supperapp.apigw.messaging.MessagingClient;
import vn.supperapp.apigw.messaging.beans.Message;
import vn.supperapp.apigw.messaging.beans.MessageContent;
import vn.supperapp.apigw.objs.UploadConfigInfo;
import vn.supperapp.apigw.restful.controllers.BaseController;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.blog.BlogRequest;
import vn.supperapp.apigw.restful.models.blog.BlogResponse;
import vn.supperapp.apigw.restful.models.schedule.PersonalScheduleRequest;
import vn.supperapp.apigw.restful.models.schedule.PersonalScheduleResponse;
import vn.supperapp.apigw.utils.*;
import vn.supperapp.apigw.utils.enums.ChannelType;
import vn.supperapp.apigw.utils.enums.FileMgtUtils;
import vn.supperapp.apigw.utils.enums.Language;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Path("/personal-schedule")
@RsDefaultFilterMapping
@RsAuthFilterMapping
@RsResponseFilterMapping
public class PersonalScheduleController extends BaseController {

    private final String PERSONAL_SCHEDULE = "PERSONAL_SCHEDULE";
    private PersonalScheduleDAO personalScheduleDAO;
    private BlogTagDAO blogTagDAO;

    public PersonalScheduleController() {
        this.logger = LoggerFactory.getLogger(PersonalScheduleController.class);
        personalScheduleDAO = new PersonalScheduleDAO();
        blogTagDAO = new BlogTagDAO();
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse savePersonalSchedule(@NotNull PersonalScheduleRequest request) {
        logger.info("#savePersonalSchedule - Start: " + request.toLogString());
        PersonalScheduleResponse response = new PersonalScheduleResponse(ErrorCode.SUCCESS, language);
        Session session = null;
        UserLoggedInfo userLoggedInfo;
        try {
            logger.info("#savePersonalSchedule validate data");
            session = DbSessionMgt.shared().getSession();
            logger.info("#savePersonalSchedule Check session");
            if (!checkDbSession(session)) {
                logger.info("#savePersonalSchedule DB Connection error");
                return BaseResponse.commonError(language);
            }
            userLoggedInfo = getUserLoggedInfo();
            if (userLoggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }

            if (CommonUtils.isNullOrEmpty(request.getTitle()) || CommonUtils.isNullOrEmpty(request.getStartDate()) || CommonUtils.isNullOrEmpty(request.getToDate()) ||
                    CommonUtils.isNullOrEmpty(request.getAddress()))
            {
                logger.info("#savePersonalSchedule - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            PersonalSchedule personalSchedule = new PersonalSchedule();
            if(request.getId() == null)
            {
//                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//                Date startDate = formatter.parse(request.getStartDate());
//                Date toDate = formatter.parse(request.getToDate());
                personalSchedule.setUserId(userLoggedInfo.getAppUser().getId());
                personalSchedule.setTitle(request.getTitle());
                personalSchedule.setContent(request.getContent());
                personalSchedule.setAddress(request.getAddress());
                personalSchedule.setStartDate(DateTimeUtils.toDateTime(request.getStartDate()));
                personalSchedule.setToDate(DateTimeUtils.toDateTime(request.getToDate()));
                personalSchedule.setType(PERSONAL_SCHEDULE);
                personalSchedule.setAllDay(request.getAllDay());
                personalSchedule.setRemind(request.getRemind());
                personalSchedule.setRepeat(request.getRepeat());
                personalSchedule.setColor(request.getColor());
                personalSchedule.setOrgId(userLoggedInfo.getAppUser().getOrgId());
                personalSchedule.setCreateAt(new Date());
                personalSchedule.setCreateBy(userLoggedInfo.getAppUser().getMsisdn());
                personalScheduleDAO.save(session,personalSchedule);
                if(!CommonUtils.isNullOrEmpty(request.getTags()))
                {
                    String [] arrTagList = request.getTags().split(",");
                    if (arrTagList.length > 0) {
                        for (String item : arrTagList) {
                            BlogTag blogTag = new BlogTag();
                            blogTag.setObjId(personalSchedule.getId());
                            blogTag.setType(PERSONAL_SCHEDULE);
                            blogTag.setUserId(Long.valueOf(item));
                            blogTag.setOrgId(userLoggedInfo.getAppUser().getOrgId());
                            blogTag.setCreateAt(new Date());
                            blogTag.setCreateBy(userLoggedInfo.getAppUser().getMsisdn());
                            blogTagDAO.save(session,blogTag);
                        }
                    }
                }
            }
            else
            {
                personalSchedule = personalScheduleDAO.getPersonalScheduleById(session,userLoggedInfo.getAppUser().getOrgId(),request.getId());
                if(personalSchedule != null)
                {
                    personalSchedule.setTitle(request.getTitle());
                    personalSchedule.setContent(request.getContent());
                    personalSchedule.setAddress(request.getAddress());
                    personalSchedule.setStartDate(DateTimeUtils.toDateTime(request.getStartDate()));
                    personalSchedule.setToDate(DateTimeUtils.toDateTime(request.getToDate()));
                    personalSchedule.setAllDay(request.getAllDay());
                    personalSchedule.setRemind(request.getRemind());
                    personalSchedule.setRepeat(request.getRepeat());
                    personalSchedule.setColor(request.getColor());
                    personalSchedule.setUpdateAt(new Date());
                    personalSchedule.setUpdateBy(userLoggedInfo.getAppUser().getMsisdn());
                    personalScheduleDAO.update(session,personalSchedule);
                    if(!CommonUtils.isNullOrEmpty(request.getTags()))
                    {
                        List<BlogTag> personalScheduleTagList = blogTagDAO.getPersonalTagById(session, personalSchedule.getId(), userLoggedInfo.getAppUser().getOrgId(), language);
                        if(personalScheduleTagList.size() > 0)
                        {
                            personalScheduleDAO.deleteAllTagByPSId(session, personalSchedule.getId());
                        }
                        String [] arrTagList = request.getTags().split(",");
                        if (arrTagList.length > 0) {
                            for (String item : arrTagList) {
                                BlogTag blogTag = new BlogTag();
                                blogTag.setObjId(personalSchedule.getId());
                                blogTag.setType(PERSONAL_SCHEDULE);
                                blogTag.setUserId(Long.valueOf(item));
                                blogTag.setOrgId(userLoggedInfo.getAppUser().getOrgId());
                                blogTag.setCreateAt(new Date());
                                blogTag.setCreateBy(userLoggedInfo.getAppUser().getMsisdn());
                                blogTagDAO.save(session,blogTag);
                            }
                        }
                    }
                }
                else
                {
                    logger.info("#savePersonalSchedule: not data");
                    return BaseResponse.commonError(ErrorCode.ERR_MISSING_DATA.name());
                }
            }
            sendSmsSuccessToCustomer(userLoggedInfo, personalSchedule, request.getTags());
            response.setSchedule(personalSchedule);
            return response;
        } catch (Exception ex) {
            logExceptions("#login - Error: ", ex);
            ex.printStackTrace();
            logger.error("#login - EXCEPTION: ", ex);

        }
        return new BaseResponse();
    }
    @POST
    @Path("/delete-personal-schedule")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteBlog(@NotNull PersonalScheduleRequest request) {
        logger.info("#widgetData - Start: " + request.toLogString());
        Session session = null;
        PersonalScheduleResponse response = new PersonalScheduleResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            if (CommonUtils.isNullOrEmpty(request.getId().toString())
            ) {
                logger.info("#updateInfor - Validate: id invalid");
                return BaseResponse.build(ErrorCode.ERR_INVALID_INFORMATION, language);
            }
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("#createBlog error : loggedInfo is null");
                return BaseResponse.commonError(language);
            }
            logger.info("#homeInfo Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#homeInfo Check session");
            if (!checkDbSession(session)) {
                logger.info("#homeInfo DB Connection error");
                return BaseResponse.commonError(language);
            }
            PersonalSchedule personalSchedule = personalScheduleDAO.getPersonalScheduleById(session, request.getId(), loggedInfo.getAppUser().getOrgId());
            if(personalSchedule != null)
            {
                List<BlogTag> personalScheduleTagList = blogTagDAO.getPersonalTagById(session, personalSchedule.getId(),loggedInfo.getAppUser().getOrgId(), language);
                if(personalScheduleTagList.size() > 0)
                {
                    personalScheduleDAO.deleteAllTagByPSId(session, personalSchedule.getId());
                }
                personalScheduleDAO.delete(session, personalSchedule.getClass(), personalSchedule.getId());
                response = new PersonalScheduleResponse(ErrorCode.SUCCESS, language);
                return response;
            }
            else
            {
                response = new PersonalScheduleResponse(ErrorCode.ERR_MISSING_DATA, language);
                return response;
            }
        } catch (Exception ex) {
            logExceptions("#homeInfo - Error: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    @POST
    @Path("/get-personal-schedule")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getPersonalSchedule(@NotNull PersonalScheduleRequest request) {
        logger.info("#getPersonalSchedule - Start: " + request.toLogString());
        Session session = null;
        PersonalScheduleResponse response = new PersonalScheduleResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("#getPersonalSchedule error : loggedInfo is null");
                return BaseResponse.commonError(language);
            }
            logger.info("#getPersonalSchedule Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#getPersonalSchedule Check session");
            if (!checkDbSession(session)) {
                logger.info("#getPersonalSchedule DB Connection error");
                return BaseResponse.commonError(language);
            }
            String strDate = "";
            if(!CommonUtils.isNullOrEmpty(request.getStartDate())) {strDate = request.getStartDate();} else {
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");
                strDate = formatter.format(date);
            }
            List<Long> ids = new ArrayList<>();
            List<PersonalScheduleInfo> personalScheduleList = personalScheduleDAO.getPersonalSchedule(session, loggedInfo.getAppUser().getOrgId(), loggedInfo.getAppUser().getId(), strDate);
            if(personalScheduleList != null && personalScheduleList.size() > 0)
            {
                List<Long> tmps = personalScheduleList.stream().map(PersonalScheduleInfo::getId).collect(Collectors.toList());
                ids.addAll(tmps);
                List<BlogTagInfo> tagInfos = null;
                if (ids != null && !ids.isEmpty()) {
                    tagInfos = blogTagDAO.getBlogTagByIdsPersonalSchedule(session, ids, loggedInfo.getAppUser().getOrgId(), language);
                }
                if (tagInfos != null && !tagInfos.isEmpty()) {

                    for(PersonalScheduleInfo personalSchedule : personalScheduleList){
                        List<BlogTagInfo> tmpTags = tagInfos.stream().filter(im -> im.getObjId() == personalSchedule.getId()).collect(Collectors.toList());
                        personalSchedule.setBlogTagInfoList(tmpTags);
                    }
                }
                response.setPersonalScheduleList(personalScheduleList);
                return response;
            }
            else
            {
                logger.info("#getPersonalSchedule DB Connection error");
                return BaseResponse.build(ErrorCode.ERR_MISSING_DATA, language);
            }

        } catch (Exception ex) {
            logExceptions("#getPersonalSchedule - Error: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    @POST
    @Path("/get-personal-schedule-by-id")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getPersonalScheduleById(@NotNull PersonalScheduleRequest request) {
        logger.info("#getPersonalScheduleById - Start: " + request.toLogString());
        Session session = null;
        PersonalScheduleResponse response = new PersonalScheduleResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("#getPersonalScheduleById error : loggedInfo is null");
                return BaseResponse.commonError(language);
            }
            logger.info("#getPersonalScheduleById Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#getPersonalScheduleById Check session");
            if (!checkDbSession(session)) {
                logger.info("#getPersonalScheduleById DB Connection error");
                return BaseResponse.commonError(language);
            }
            String strDate = "";
            if(CommonUtils.isNullOrEmpty(request.getId().toString())) {  return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);}
            PersonalSchedule personalSchedule = personalScheduleDAO.getPersonalScheduleById(session, loggedInfo.getAppUser().getOrgId(), request.getId());
            if(personalSchedule != null )
            {
                response.setSchedule(personalSchedule);
                return response;
            }
            else
            {
                logger.info("#getPersonalScheduleById DB Connection error");
                return BaseResponse.commonError(language);
            }

        } catch (Exception ex) {
            logExceptions("#getPersonalScheduleById - Error: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    @POST
    @Path("/get-dropdown")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getDropDown(@NotNull PersonalScheduleRequest request) {
        logger.info("#getDropDown - Start: " + request.toLogString());
        Session session = null;
        PersonalScheduleResponse response = new PersonalScheduleResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("#getDropDown error : loggedInfo is null");
                return BaseResponse.commonError(language);
            }
            logger.info("#getDropDown Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#getDropDown Check session");
            if (!checkDbSession(session)) {
                logger.info("#getDropDown DB Connection error");
                return BaseResponse.commonError(language);
            }
            List<ScheduleConfig> reminds = personalScheduleDAO.getRemind(session, loggedInfo.getAppUser().getOrgId(), language);
            if(reminds != null && reminds.size() > 0)
            {
                response.setRemind(reminds);
            }
            List<ScheduleConfig> repeat = personalScheduleDAO.getRepeat(session, loggedInfo.getAppUser().getOrgId(), language);
            if(repeat != null && repeat.size() > 0)
            {
                response.setRepeat(repeat);
            }
            return response;
        } catch (Exception ex) {
            logExceptions("#getDropDown - Error: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    private String sendSmsSuccessToCustomer(UserLoggedInfo loggedInfo, PersonalSchedule personalScheduleInfo , String listIdU) {
        try {
            String msgKey = "MSG_TAG_PERSONAL_SCHEDULE";

            MessageContent mc = MessageContent.builder()
                    .addReplacement("[user tag]", loggedInfo.getAppUser().getFullName())
                    .addReplacement("[title]", personalScheduleInfo.getTitle())
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
                    .setNotificationType(Constants.NOTIFICATION_TYPE_PERSONAL_SCHEDULE)
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
