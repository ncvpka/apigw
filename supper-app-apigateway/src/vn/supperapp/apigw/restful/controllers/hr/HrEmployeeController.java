package vn.supperapp.apigw.restful.controllers.hr;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.hibernate.Session;
import org.slf4j.LoggerFactory;
import vn.supperapp.apigw.beans.*;
import vn.supperapp.apigw.configs.AppConfigurations;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.HrEmployeeDAO;
import vn.supperapp.apigw.db.dao.HrEmployeeEducationDAO;
import vn.supperapp.apigw.db.dto.*;
import vn.supperapp.apigw.objs.UploadConfigInfo;
import vn.supperapp.apigw.restful.controllers.BaseController;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.hr.*;
import vn.supperapp.apigw.restful.models.payrequest.PaymentResponse;
import vn.supperapp.apigw.restful.models.user.UploadResponse;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.DateTimeUtils;
import vn.supperapp.apigw.utils.ErrorCode;
import vn.supperapp.apigw.utils.enums.FileMgtUtils;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Path("/hr/employee")
@RsDefaultFilterMapping
@RsAuthFilterMapping
@RsResponseFilterMapping
public class HrEmployeeController extends BaseController {
    private HrEmployeeDAO dao;
    private HrEmployeeEducationDAO educationDao;
    private static final String CFG_NEWS_LIST_PAGESIZE = "news.list.page-size";

    public HrEmployeeController() {
        this.logger = LoggerFactory.getLogger(HrEmployeeController.class);
        dao = new HrEmployeeDAO();
        educationDao = new HrEmployeeEducationDAO();
    }

    @POST
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getList(HrEmployeeRequest request) {
        logger.info("#getList - Start");
        Session session = null;
        HrEmployeeResponse response;
        UserLoggedInfo loggedInfo;
        try {
            logger.info("#getList validate data");

            session = DbSessionMgt.shared().getSession();
            logger.info("#getList Check session");
            if (!checkDbSession(session)) {
                logger.info("#getList DB Connection error");
                return BaseResponse.commonError(language);
            }
            loggedInfo = getUserLoggedInfo();
            int listPageSize = AppConfigurations.shared().getConfigAsInt(CFG_NEWS_LIST_PAGESIZE);
            response = new HrEmployeeResponse(ErrorCode.SUCCESS, language);
            PagingResult pagingResult = dao.getListEmployee(session, loggedInfo.getAppUser().getOrgId(), listPageSize, request.getPage(), request);
            logger.info("#getList - Response");
            if(pagingResult.getResults() != null) {
                response.setData(pagingResult);
                return response;
            }
            else
            {
                return response;
            }
        } catch (Exception ex) {
            logExceptions("#getList - Error: ", ex);
            ex.printStackTrace();
            logger.error("#getList - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse save(HrEmployeeRequest request) {
        logger.info("#save-employee - Start");
        Session session = null;
        HrEmployeeResponse response = new HrEmployeeResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            logger.info("#save-employe validate data");

            session = DbSessionMgt.shared().getSession();
            logger.info("#getList Check session");
            if (!checkDbSession(session)) {
                logger.info("#save-employe DB Connection error");
                return BaseResponse.commonError(language);
            }
            loggedInfo = getUserLoggedInfo();

            if (CommonUtils.isNullOrEmpty(request.getFullName()) || CommonUtils.isNullOrEmpty(request.getPhone()) || CommonUtils.isNullOrEmpty(request.getBranchId().toString()) || CommonUtils.isNullOrEmpty(request.getIdTypeWork().toString()))
            {
                logger.info("#save-employe - Validate: missing data");
                return BaseResponse.build(ErrorCode.ERR_MISSING_DATA, language);
            }
            Date birthDay = null;
            Date startWork = null;
            Date endDate = null;
            Date datePersonNumber = null;
            Date dateSinging = null;
            if(!CommonUtils.isNullOrEmpty(request.getBirthDay())) { birthDay =new SimpleDateFormat("dd/MM/yyyy").parse(request.getBirthDay());}
            if(!CommonUtils.isNullOrEmpty(request.getDateStartWork())) { startWork =new SimpleDateFormat("dd/MM/yyyy").parse(request.getDateStartWork());}
            if(!CommonUtils.isNullOrEmpty(request.getDatePersonNumber())) { datePersonNumber =new SimpleDateFormat("dd/MM/yyyy").parse(request.getDatePersonNumber());}
            if(!CommonUtils.isNullOrEmpty(request.getDateSinging())) { dateSinging =new SimpleDateFormat("dd/MM/yyyy").parse(request.getDateSinging());}
            if(!CommonUtils.isNullOrEmpty(request.getEndDate())) { endDate =new SimpleDateFormat("dd/MM/yyyy").parse(request.getEndDate());}

            if(request.getId() == null) {
                logger.info("#insertPaymentRequest start insert");
                HrEmployee hrEmployee = new HrEmployee();
                int code = dao.getMaxId(session);
                hrEmployee.setFullName(request.getFullName());
                hrEmployee.setBirthday(birthDay);
                hrEmployee.setCode("EMPLOYEE" + code);
                hrEmployee.setEmployeeType(request.getEmployeeType());
                hrEmployee.setDateStartWork(startWork);
                hrEmployee.setGender(request.getGender());
                hrEmployee.setPlaceOfBirth(request.getPlaceOfBirth());
                hrEmployee.setHomeTown(request.getHomeTown());
                hrEmployee.setUserIdManager(request.getUserIdManager());
                hrEmployee.setIdTypeWork(request.getIdTypeWork());
                hrEmployee.setIdNationnality(request.getIdNationnality());
                hrEmployee.setIdReligion(request.getIdReligion());
                hrEmployee.setIdMaritalStatus(request.getIdMaritalStatus());
                hrEmployee.setIdPosition(request.getIdPosition());
                hrEmployee.setPersonNumber(request.getPersonNumber());
                hrEmployee.setDatePersonNumber(datePersonNumber);
                hrEmployee.setPlacePersonNumber(request.getPlacePersonNumber());
                hrEmployee.setCurrentAddress(request.getCurrentAddress());
                hrEmployee.setStatus(1L);
                hrEmployee.setAvatar(request.getAvatar());
                hrEmployee.setIsFullSaturday(request.getIsFullSaturday());
                hrEmployee.setBankId(request.getBankId());
                hrEmployee.setBankNumber(request.getBankNumber());
                hrEmployee.setNote(request.getNote());
                hrEmployee.setMst(request.getMst());
                hrEmployee.setDateSinging(dateSinging);
                hrEmployee.setEndDate(endDate);
                hrEmployee.setHomePhone(request.getHomePhone());
                hrEmployee.setEthnicId(request.getIdEthnic());
                hrEmployee.setPermanentResidence(request.getPermanentResidence());
                hrEmployee.setInsurancePremium(request.getInsurancePremium());
                hrEmployee.setPhone(request.getPhone());
                hrEmployee.setEmail(request.getEmail());
                hrEmployee.setEmailCompany(request.getEmailCompany());
                hrEmployee.setBranchId(request.getBranchId());
                hrEmployee.setDepartmentId(request.getDepartmentId());
                hrEmployee.setTimekeepingType(request.getTimekeepingType());
                hrEmployee.setSalary(request.getSalary());
                hrEmployee.setTypeSalary(request.getTypeSalary());
                hrEmployee.setCreateAt(new Date());
                hrEmployee.setRank(request.getRank());
                hrEmployee.setUserIdManager(request.getUserIdManager());
                hrEmployee.setUserName(request.getUserName());
                hrEmployee.setOrgId(loggedInfo.getAppUser().getOrgId());
                hrEmployee.setCreateBy(loggedInfo.getUserInfo().getAccountNumber());
                dao.save(session, hrEmployee);
                if (hrEmployee != null) {
                    response.setEmployee(hrEmployee);
                } else {
                    response = new HrEmployeeResponse(ErrorCode.ERR_COMMON, language);
                }
            }
            else {
                logger.info("#insertPaymentRequest start update");
                HrEmployee hrEmployee = dao.getEmployeeById(session, request.getId(), loggedInfo.getAppUser().getOrgId());
                if (hrEmployee == null) {
                    response = new HrEmployeeResponse(ErrorCode.ERR_PARAMETERS_INVALID, language);
                } else {
                    hrEmployee.setFullName(request.getFullName());
                    hrEmployee.setBirthday(birthDay);
                    hrEmployee.setEmployeeType(request.getEmployeeType());
                    hrEmployee.setDateStartWork(startWork);
                    hrEmployee.setGender(request.getGender());
                    hrEmployee.setPlaceOfBirth(request.getPlaceOfBirth());
                    hrEmployee.setHomeTown(request.getHomeTown());
                    hrEmployee.setUserIdManager(request.getUserIdManager());
                    hrEmployee.setIdTypeWork(request.getIdTypeWork());
                    hrEmployee.setIdNationnality(request.getIdNationnality());
                    hrEmployee.setIdReligion(request.getIdReligion());
                    hrEmployee.setIdMaritalStatus(request.getIdMaritalStatus());
                    hrEmployee.setIdPosition(request.getIdPosition());
                    hrEmployee.setPersonNumber(request.getPersonNumber());
                    hrEmployee.setDatePersonNumber(datePersonNumber);
                    hrEmployee.setPlacePersonNumber(request.getPlacePersonNumber());
                    hrEmployee.setCurrentAddress(request.getCurrentAddress());
                    hrEmployee.setStatus(1L);
                    hrEmployee.setAvatar(request.getAvatar());
                    hrEmployee.setIsFullSaturday(request.getIsFullSaturday());
                    hrEmployee.setBankId(request.getBankId());
                    hrEmployee.setBankNumber(request.getBankNumber());
                    hrEmployee.setNote(request.getNote());
                    hrEmployee.setMst(request.getMst());
                    hrEmployee.setDateSinging(dateSinging);
                    hrEmployee.setEndDate(endDate);
                    hrEmployee.setHomePhone(request.getHomePhone());
                    hrEmployee.setEthnicId(request.getIdEthnic());
                    hrEmployee.setPermanentResidence(request.getPermanentResidence());
                    hrEmployee.setInsurancePremium(request.getInsurancePremium());
                    hrEmployee.setPhone(request.getPhone());
                    hrEmployee.setEmail(request.getEmail());
                    hrEmployee.setEmailCompany(request.getEmailCompany());
                    hrEmployee.setBranchId(request.getBranchId());
                    hrEmployee.setDepartmentId(request.getDepartmentId());
                    hrEmployee.setTimekeepingType(request.getTimekeepingType());
                    hrEmployee.setSalary(request.getSalary());
                    hrEmployee.setTypeSalary(request.getTypeSalary());
                    hrEmployee.setRank(request.getRank());
                    hrEmployee.setUserIdManager(request.getUserIdManager());
                    hrEmployee.setUserName(request.getUserName());
                    hrEmployee.setOrgId(loggedInfo.getAppUser().getOrgId());
                    hrEmployee.setUpdateAt(new Date());
                    hrEmployee.setUpdateBy(loggedInfo.getUserInfo().getAccountNumber());
                    dao.update(session, hrEmployee);
                    response.setEmployee(hrEmployee);
                }
            }
            return response;
        } catch (Exception ex) {
            logExceptions("#getList - Error: ", ex);
            ex.printStackTrace();
            logger.error("#getList - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    @POST
    @Path("/get-by-id")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getById(HrEmployeeRequest request) {
        logger.info("#getList - Start");
        Session session = null;
        HrEmployeeResponse response = new HrEmployeeResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            logger.info("#getById validate data");

            session = DbSessionMgt.shared().getSession();
            logger.info("#getById Check session");
            if (!checkDbSession(session)) {
                logger.info("#getById DB Connection error");
                return BaseResponse.commonError(language);
            }
            loggedInfo = getUserLoggedInfo();
            if (request.getId() == null) {
                logger.info("#getById-employe - Validate: missing data");
                return BaseResponse.build(ErrorCode.ERR_MISSING_DATA, language);
            }
            HREmployeeInfoDetail hrEmployeeInfoDetail = dao.getEmployeeDetail(session, loggedInfo.getAppUser().getOrgId(), request.getId());
            if(hrEmployeeInfoDetail != null)
            {
                logger.info("#homeBlog - Response");
                response.setDetail(hrEmployeeInfoDetail);
            }
            return response;
        } catch (Exception ex) {
            logExceptions("#getList - Error: ", ex);
            ex.printStackTrace();
            logger.error("#getList - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    @POST
    @Path("/save-education")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse saveEducation(HrEmployeeEducationRequest request) {
        logger.info("#save-employee - Start");
        Session session = null;
        HrEmployeeEducationResponse response = new HrEmployeeEducationResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            logger.info("#save-employe validate data");

            session = DbSessionMgt.shared().getSession();
            logger.info("#getList Check session");
            if (!checkDbSession(session)) {
                logger.info("#save-employe DB Connection error");
                return BaseResponse.commonError(language);
            }
            loggedInfo = getUserLoggedInfo();

            if (request.getUserId() == null)
            {
                logger.info("#save-employe - Validate: missing data");
                return BaseResponse.build(ErrorCode.ERR_MISSING_DATA, language);
            }
            if (CommonUtils.isNullOrEmpty(request.getStartDate()) || CommonUtils.isNullOrEmpty(request.getEndDate()) || request.getIdLiteracy() == null) {
                logger.info("#save-employe - Validate: missing data");
                return BaseResponse.build(ErrorCode.ERR_MISSING_DATA, language);
            }
            Date startDate = null;
            Date endDate = null;
            if(!CommonUtils.isNullOrEmpty(request.getStartDate())) { startDate =new SimpleDateFormat("dd/MM/yyyy").parse(request.getStartDate());}
            if(!CommonUtils.isNullOrEmpty(request.getEndDate())) { endDate =new SimpleDateFormat("dd/MM/yyyy").parse(request.getEndDate());}

            if(request.getEducationId() == null) {
                logger.info("#insertPaymentRequest start insert");
                HrEmployeeEducation education = new HrEmployeeEducation();
                education.setIdUser(request.getUserId());
                education.setIdLiteracy(request.getIdLiteracy());
                education.setStartDate(startDate);
                education.setEndDate(endDate);
                education.setIdDiploma(request.getIdDiploma());
                education.setLanguageLevel(request.getIdEngLevel());
                education.setIdTrainType(request.getIdTypeTraining());
                education.setAddress(request.getPlace());
                education.setIdCategorize(request.getIdCategorize());
                education.setIdSpecialized(request.getIdSpecialization());
                education.setTechLevel(request.getIdComputerSkill());
                education.setCreateAt(new Date());
                education.setOrgId(loggedInfo.getAppUser().getOrgId());
                education.setCreateBy(loggedInfo.getUserInfo().getAccountNumber());
                dao.save(session, education);
                if (education != null) {
                    response.setInfo(education);
                } else {
                    response = new HrEmployeeEducationResponse(ErrorCode.ERR_COMMON, language);
                }
            }
            else {
                logger.info("#insertPaymentRequest start update");
                HrEmployeeEducation education = educationDao.getById(session, loggedInfo.getAppUser().getOrgId(), request.getEducationId());
                if (education == null) {
                    response = new HrEmployeeEducationResponse(ErrorCode.ERR_PARAMETERS_INVALID, language);
                } else {
                    education.setIdLiteracy(request.getIdLiteracy());
                    education.setStartDate(startDate);
                    education.setEndDate(endDate);
                    education.setIdDiploma(request.getIdDiploma());
                    education.setLanguageLevel(request.getIdEngLevel());
                    education.setIdTrainType(request.getIdTypeTraining());
                    education.setAddress(request.getPlace());
                    education.setIdCategorize(request.getIdCategorize());
                    education.setIdSpecialized(request.getIdSpecialization());
                    education.setTechLevel(request.getIdComputerSkill());
                    education.setOrgId(loggedInfo.getAppUser().getOrgId());
                    education.setUpdateAt(new Date());
                    education.setUpdateBy(loggedInfo.getUserInfo().getAccountNumber());
                    dao.update(session, education);
                    response.setInfo(education);
                }
            }
            return response;
        } catch (Exception ex) {
            logExceptions("#getList - Error: ", ex);
            ex.printStackTrace();
            logger.error("#getList - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    @POST
    @Path("/list-education")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getListEducation(HrEmployeeEducationRequest request) {
        logger.info("#getList - Start");
        Session session = null;
        HrEmployeeEducationResponse response;
        UserLoggedInfo loggedInfo;
        try {
            logger.info("#getList validate data");

            session = DbSessionMgt.shared().getSession();
            logger.info("#getList Check session");
            if (!checkDbSession(session)) {
                logger.info("#getList DB Connection error");
                return BaseResponse.commonError(language);
            }
            loggedInfo = getUserLoggedInfo();
            if (request.getUserId() == null)
            {
                logger.info("#save-employe - Validate: missing data");
                return BaseResponse.build(ErrorCode.ERR_MISSING_DATA, language);
            }
            response = new HrEmployeeEducationResponse(ErrorCode.SUCCESS, language);
            List<HREmployeeEducationInfo> list = educationDao.getListEducation(session, request.getUserId(), loggedInfo.getAppUser().getOrgId());
            logger.info("#getList - Response");
            if(list != null && list.size() > 0) response.setList(list);
            return response;
        } catch (Exception ex) {
            logExceptions("#getList - Error: ", ex);
            ex.printStackTrace();
            logger.error("#getList - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

    @POST
    @Path("/delete-education")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteEducation(HrEmployeeEducationRequest request) {
        logger.info("#deleteEducation - Start");
        Session session = null;
        HrEmployeeEducationResponse response = new HrEmployeeEducationResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            logger.info("#deleteEducation validate data");

            session = DbSessionMgt.shared().getSession();
            logger.info("#deleteEducation Check session");
            if (!checkDbSession(session)) {
                logger.info("#deleteEducation DB Connection error");
                return BaseResponse.commonError(language);
            }
            loggedInfo = getUserLoggedInfo();

            if (request.getEducationId() == null)
            {
                logger.info("#deleteEducation - Validate: missing data");
                return BaseResponse.build(ErrorCode.ERR_MISSING_DATA, language);
            }
            logger.info("#deleteEducation start delete");
            HrEmployeeEducation education = educationDao.getById(session, loggedInfo.getAppUser().getOrgId(), request.getEducationId());
            if (education == null) {
                response = new HrEmployeeEducationResponse(ErrorCode.ERR_PARAMETERS_INVALID, language);
                } else {
                educationDao.delete(session, education.getClass(), education.getId());
            }
            return response;
        } catch (Exception ex) {
            logExceptions("#deleteEducation - Error: ", ex);
            ex.printStackTrace();
            logger.error("#getList - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }


    @POST
    @Path("/upload-avatar")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse uploadAvatar(@NotNull HrEmployeeRequest request) {
        Session session = null;
        UserLoggedInfo loggedInfo = null;
        UploadResponse uploadResponse = new UploadResponse(ErrorCode.SUCCESS, language);
        try {
            logger.info("Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("Check session");
            if (!checkDbSession(session)) {
                logger.info("DB Connection error");
                return BaseResponse.commonError(language);
            }
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("#getUserLogged error : loggedInfo is null");
                return BaseResponse.commonError(language);
            }
            if(CommonUtils.isNullOrEmpty(request.getImage())){
                logger.info("#rateProduct - Validate: invalid rate");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            String pathImg01 = uploadImgFromBase64(loggedInfo.getAppUser().getMsisdn(), "avatar", request.getImage(), "jpg");
            uploadResponse.setUrl(pathImg01);
            return uploadResponse;

        } catch (Exception ex) {
            logExceptions("#natshop-rate-product - Error: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }


    private String uploadFile(String subFolder, String fileUploadType, UploadConfigInfo uploadConfig, FormDataBodyPart file) {
        try {
            String fileExt = file.getMediaType().getSubtype();
            String fileName = String.format("%s_%d.%s", fileUploadType, System.currentTimeMillis(), fileExt);
            String folderPath = String.format("%s/%s", uploadConfig.getDocumentFolder(), subFolder);

            String fullFolderPath = String.format("%s/%s", uploadConfig.getBaseDocumentFolder(), folderPath);

            InputStream fileIs = file.getEntityAs(InputStream.class);
            String ftmp = FileMgtUtils.saveFileTo(fullFolderPath, fileName, fileIs);
            IOUtils.closeQuietly(fileIs);

            return String.format("%s/%s", folderPath, fileName);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#uploadFile - EXCEPTION: ", ex);
        }
        return null;
    }

    private boolean validateFileFormat(UploadConfigInfo config, FormDataBodyPart file) {
        try {
            String fType = file.getMediaType().getType();
            String fSubType = file.getMediaType().getSubtype().toUpperCase();
            return "image".equals(fType) && config.getListFileExt().contains(fSubType);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#validateFileFormat - EXCEPTION: ", ex);
        }
        return false;
    }
    private static String uploadImgFromBase64(String accountNumber, String fileUploadType, String fileBase64String, String fileType) {
        try {
            UploadConfigInfo uploadConfig = AppConfigurations.shared().getUploadConfig();
            String subFolder = DateTimeUtils.toString(new Date(), "yyyyMMdd");
            String fileName = String.format("%s_%s_%d.%s", fileUploadType, accountNumber, System.currentTimeMillis(), fileType);
            String folderPath = String.format("%s/%s/%s", "avatars", subFolder, accountNumber);

            String fullFolderPath = String.format("%s/%s", uploadConfig.getBaseDocumentFolder(), folderPath);

            InputStream file = new ByteArrayInputStream(Base64.getDecoder().decode(fileBase64String));
            FileMgtUtils.saveFileTo(fullFolderPath, fileName, file);
            IOUtils.closeQuietly(file);

            return String.format("%s/%s", folderPath, fileName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
