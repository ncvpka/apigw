package vn.supperapp.apigw.restful.controllers.mail;

import org.hibernate.Session;
import org.slf4j.LoggerFactory;
import vn.supperapp.apigw.beans.BlogTagInfo;
import vn.supperapp.apigw.beans.PersonalScheduleInfo;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.BlogTagDAO;
import vn.supperapp.apigw.db.dao.PersonalScheduleDAO;
import vn.supperapp.apigw.db.dto.BlogTag;
import vn.supperapp.apigw.db.dto.PersonalSchedule;
import vn.supperapp.apigw.db.dto.ScheduleConfig;
import vn.supperapp.apigw.restful.controllers.BaseController;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.mail.EmailRequest;
import vn.supperapp.apigw.restful.models.mail.EmailResponse;
import vn.supperapp.apigw.restful.models.schedule.PersonalScheduleRequest;
import vn.supperapp.apigw.restful.models.schedule.PersonalScheduleResponse;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.DateTimeUtils;
import vn.supperapp.apigw.utils.ErrorCode;

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

@Path("/email")
@RsDefaultFilterMapping
@RsAuthFilterMapping
@RsResponseFilterMapping
public class EmailController extends BaseController {

    private final String PERSONAL_SCHEDULE = "PERSONAL_SCHEDULE";
    private final String COMPANY_SCHEDULE = "COMPANY_SCHEDULE";
    private PersonalScheduleDAO personalScheduleDAO;
    private BlogTagDAO blogTagDAO;

    public EmailController() {
        this.logger = LoggerFactory.getLogger(EmailController.class);
        personalScheduleDAO = new PersonalScheduleDAO();
        blogTagDAO = new BlogTagDAO();
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse saveCompanySchedule(@NotNull PersonalScheduleRequest request) {
        logger.info("#saveCompanySchedule - Start: " + request.toLogString());
        PersonalScheduleResponse response = new PersonalScheduleResponse(ErrorCode.SUCCESS, language);
        Session session = null;
        UserLoggedInfo userLoggedInfo;
        try {
            logger.info("#saveCompanySchedule validate data");
            session = DbSessionMgt.shared().getSession();
            logger.info("#saveCompanySchedule Check session");
            if (!checkDbSession(session)) {
                logger.info("#saveCompanySchedule DB Connection error");
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
                logger.info("#saveCompanySchedule - Validate: Signature invalid");
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
                personalSchedule.setType(COMPANY_SCHEDULE);
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
                            blogTag.setType(COMPANY_SCHEDULE);
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
                                blogTag.setType(COMPANY_SCHEDULE);
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
    @Path("/delete-company-schedule")
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
            PersonalSchedule personalSchedule = personalScheduleDAO.deleteCompanyScheduleById(session, request.getId(), loggedInfo.getAppUser().getOrgId());
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
    @Path("/get-email")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getEmail(@NotNull EmailRequest request) {
        logger.info("#getPersonalSchedule - Start: " + request.toLogString());
        Session session = null;
        EmailResponse response = new EmailResponse(ErrorCode.SUCCESS, language);
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
            List<Long> ids = new ArrayList<>();
            List<PersonalScheduleInfo> personalScheduleList = personalScheduleDAO.getCompanySchedule(session, loggedInfo.getAppUser().getOrgId());
            if(personalScheduleList != null && personalScheduleList.size() > 0)
            {
                List<Long> tmps = personalScheduleList.stream().map(PersonalScheduleInfo::getId).collect(Collectors.toList());
                ids.addAll(tmps);
                List<BlogTagInfo> tagInfos = null;
                if (ids != null && !ids.isEmpty()) {
                    tagInfos = blogTagDAO.getBlogTagByIdsCompanySchedule(session, ids, loggedInfo.getAppUser().getOrgId(), language);
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
    @Path("/get-company-schedule-by-id")
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
            PersonalScheduleInfo personalSchedule = personalScheduleDAO.getCompanyScheduleById(session, loggedInfo.getAppUser().getOrgId(), request.getId());
            if(personalSchedule != null )
            {
                response.setPersonalSchedule(personalSchedule);
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
}
