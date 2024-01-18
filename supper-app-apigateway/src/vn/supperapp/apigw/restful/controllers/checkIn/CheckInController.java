package vn.supperapp.apigw.restful.controllers.checkIn;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.hibernate.Session;
import org.slf4j.LoggerFactory;
import vn.supperapp.apigw.beans.CheckInInfo;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.configs.AppConfigurations;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.CheckInDAO;
import vn.supperapp.apigw.db.dao.CheckInImagesDAO;
import vn.supperapp.apigw.db.dao.UserDAO;
import vn.supperapp.apigw.db.dto.CheckIn;
import vn.supperapp.apigw.db.dto.CheckInImages;
import vn.supperapp.apigw.objs.UploadConfigInfo;
import vn.supperapp.apigw.restful.controllers.BaseController;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.checkin.CheckInRequest;
import vn.supperapp.apigw.restful.models.checkin.CheckInResponse;
import vn.supperapp.apigw.restful.models.user.UploadResponse;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.DateTimeUtils;
import vn.supperapp.apigw.utils.ErrorCode;
import vn.supperapp.apigw.utils.enums.FileMgtUtils;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Path("/check-in")
@RsDefaultFilterMapping
@RsAuthFilterMapping
@RsResponseFilterMapping
public class CheckInController extends BaseController {
    private final String CHECK_IN = "CHECK_IN";
    private UserDAO userDao;
    private CheckInDAO checkInDAO;
    private CheckInImagesDAO checkInImagesDAO;

    public CheckInController() {
        this.logger = LoggerFactory.getLogger(CheckInController.class);
        userDao = new UserDAO();
        checkInDAO = new CheckInDAO();
        checkInImagesDAO = new CheckInImagesDAO();
    }

    @POST
    @Path("/create-checkin")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse registerManualApprove(@HeaderParam("content-length") long contentLength,
                                              @FormDataParam("fileImages") List<FormDataBodyPart> fileImages,
                                              @FormDataParam("address") String address,
                                              @FormDataParam("time") String time,
                                              @FormDataParam("latitude") Double latitude,
                                              @FormDataParam("longitude") Double longitude) {

        logger.info("#userUploadAvatar - Start: fileImages={}", fileImages);

        Session session = null;
        UploadResponse response = new UploadResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo = null;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("#checkInscene error : loggedInfo is null");
                return BaseResponse.commonError(language);
            }
            logger.info("#checkInscene validate data");
            //region - Validate

            if (fileImages.size() == 0 || fileImages == null) {
                logger.info("#checkInscene - Validate: file invalid");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            if (CommonUtils.isNullOrEmpty(address) || CommonUtils.isNullOrEmpty(time) ) {
                logger.info("#checkInscene - Validate: time invalid");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }
            if (latitude == null || longitude == null ) {
                logger.info("#checkInscene - Validate: address invalid");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }
            CheckIn checkIn = new CheckIn();
            checkIn.setMsisdn(loggedInfo.getUserInfo().getAccountNumber());
            checkIn.setType(CHECK_IN);
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date checkInTime = formatter.parse(time);
            checkIn.setCheckInTime(checkInTime);
            checkIn.setAddress(address);
            checkIn.setLatitude(latitude);
            checkIn.setLongitude(longitude);
            checkIn.setOrgId(loggedInfo.getAppUser().getOrgId());
            checkIn.setUserId(loggedInfo.getAppUser().getId());
            checkIn.setCreateTime(new Date());
            checkIn.setCreateBy(loggedInfo.getAppUser().getMsisdn());
            logger.info("#checkInscene Open db app session");
            session = DbSessionMgt.shared().getSession();
            checkInDAO.save(session, checkIn);
            if(checkIn.getId() != null && fileImages.size() > 0) {
                UploadConfigInfo uploadConfig = AppConfigurations.shared().getUploadConfig();

                logger.info("#checkInscene - Validate content length");
                long allowLength = (uploadConfig.getMaxFileSize() * 1024 * 1024 * 4);
                if (contentLength > allowLength) {
                    logger.info("#checkInscene - Validate: allowLength file max");
                    return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
                }
                for (FormDataBodyPart file : fileImages) {
                    if (!validateFileFormat(uploadConfig, file)) {
                        logger.info("#checkInscene - Validate: fileImage malformed");
                        return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
                    }
                }

                logger.info("#checkInscene - Upload file image");
                    for (FormDataBodyPart file : fileImages) {
                        String imagePath = uploadFile(loggedInfo.getUserInfo().getAccountNumber(), "checkin", uploadConfig, file);
                        logger.info("#checkInscene Check session");
                        if (!checkDbSession(session)) {
                            logger.info("#checkInscene DB Connection error");
                            return BaseResponse.commonError(language);
                        }
                        CheckInImages checkInImages = new CheckInImages();
                        checkInImages.setLanguage(language);
                        checkInImages.setCheckInId(checkIn.getId());
                        checkInImages.setUrlImage(imagePath);
                        checkInImages.setCreateTime(new Date());
                        checkInImages.setCreateBy(loggedInfo.getAppUser().getMsisdn());
                        checkInImages.setOrgId(loggedInfo.getAppUser().getOrgId());
                        checkInImagesDAO.save(session, checkInImages);
                    }
            }
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
    @Path("/get-check-in")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BaseResponse getCheckIn(@NotNull CheckInRequest request) {
        logger.info("#getCheckIn - Start: " + request.toLogString());
        CheckInResponse response = null;
        UserLoggedInfo loggedInfo;
        CheckInDAO checkInDAO;
        Session session = null;
        try {
            loggedInfo = getUserLoggedInfo();

            checkInDAO = new CheckInDAO();
            logger.info("#getCheckIn Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#getCheckIn Check session");
            if (!checkDbSession(session)) {
                logger.info("#getCheckIn DB Connection error");
                return BaseResponse.commonError(language);
            }

            List<CheckInInfo> checkInInfor = checkInDAO.getCheckIn(session,loggedInfo.getAppUser().getId(), loggedInfo.getAppUser().getOrgId());
            if(checkInInfor == null || checkInInfor.size() == 0)
            {
                logger.info("#getCheckIn no data");
                return BaseResponse.commonError(language);
            }

            response = new CheckInResponse(ErrorCode.SUCCESS, language);
            response.setCheckIns(checkInInfor);
            return response;

        } catch (Exception e) {
            logger.error("#getListMessages - ERROR: ", e);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return BaseResponse.commonError(language);
    }

    @POST
    @Path("/update-check-in")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BaseResponse updateCheckIn(@HeaderParam("content-length") long contentLength,
                                      @FormDataParam("fileImages") List<FormDataBodyPart> fileImages,
                                      @FormDataParam("id") Long id,
                                      @FormDataParam("address") String address,
                                      @FormDataParam("time") String time,
                                      @FormDataParam("latitude") Double latitude,
                                      @FormDataParam("longitude") Double longitude) {
        logger.info("#updateCheckIn - Start: " + id.toString());
        CheckInResponse response = null;
        UserLoggedInfo loggedInfo;
        CheckInDAO checkInDAO;
        Session session = null;
        try {
            loggedInfo = getUserLoggedInfo();

            checkInDAO = new CheckInDAO();
            logger.info("#updateCheckIn Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#updateCheckIn Check session");
            if (!checkDbSession(session)) {
                logger.info("#updateCheckIn DB Connection error");
                return BaseResponse.commonError(language);
            }

            if (id == null || fileImages == null || fileImages.size() == 0) {
                logger.info("#updateCheckIn - Validate: id is null || files are null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }
            if (CommonUtils.isNullOrEmpty(address) || CommonUtils.isNullOrEmpty(time) ) {
                logger.info("#checkInscene - Validate: type invalid");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            UploadConfigInfo uploadConfig = AppConfigurations.shared().getUploadConfig();
            logger.info("#updateCheckIn - Validate content length");
            long allowLength = (uploadConfig.getMaxFileSize() * 1024 * 1024 * 4);
            if (contentLength > allowLength) {
                logger.info("#updateCheckIn - Validate: allowLength file max");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }
            for (FormDataBodyPart file : fileImages) {
                if (!validateFileFormat(uploadConfig, file)) {
                    logger.info("#updateCheckIn - Validate: fileImage malformed");
                    return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
                }
            }

            CheckIn checkIn = checkInDAO.getCheckInById(session,id,loggedInfo.getAppUser().getOrgId());

            if(checkIn == null)
            {
                logger.info("#updateCheckIn no data");
                return BaseResponse.commonError(language);
            }
            else
            {
                checkIn.setCheckInTime(DateTimeUtils.toDate(time, "dd/MM/yyyy HH:mm:ss"));
                checkIn.setAddress(address);
                checkIn.setLatitude(latitude);
                checkIn.setLongitude(longitude);
                checkIn.setUpdateTime(new Date());
                checkIn.setUpdateBy(loggedInfo.getAppUser().getMsisdn());
                checkInDAO.update(session, checkIn);
                List<CheckInImages> checkInImagesList = checkInDAO.getCheckInImagesById(session,checkIn.getId(), loggedInfo.getAppUser().getOrgId());
                if(checkInImagesList.size() > 0){
                    checkInImagesDAO.deleteAllImageByCheckInId(session, id);
                    for (CheckInImages image : checkInImagesList){
                        String fullFolderPath = String.format("%s/%s", uploadConfig.getBaseDocumentFolder(), image.getUrlImage());
                        boolean b = FileMgtUtils.deleteFile(fullFolderPath);
                        if (!b)
                        {
                            logger.info("#updateCheckIn - can not delete file");
                            return BaseResponse.build(ErrorCode.CAN_NOT_DELETE_FILE, language);
                        }
                    }
                }
                if(fileImages.size() > 0){
                    logger.info("#updateCheckIn - Upload file image");
                    for (FormDataBodyPart file : fileImages) {
                        String imagePath = uploadFile(loggedInfo.getUserInfo().getAccountNumber(), "checkin", uploadConfig, file);
                        logger.info("#updateCheckIn Check session");
                        if (!checkDbSession(session)) {
                            logger.info("#updateCheckIn DB Connection error");
                            return BaseResponse.commonError(language);
                        }
                        CheckInImages checkInImages = new CheckInImages();
                        checkInImages.setLanguage(language);
                        checkInImages.setCheckInId(checkIn.getId());
                        checkInImages.setUrlImage(imagePath);
                        checkInImages.setCreateTime(new Date());
                        checkInImages.setCreateBy(loggedInfo.getAppUser().getMsisdn());
                        checkInImages.setOrgId(loggedInfo.getAppUser().getOrgId());
                        checkInImagesDAO.save(session, checkInImages);
                    }
                }

                response = new CheckInResponse(ErrorCode.SUCCESS, language);
                return response;

            }

        } catch (Exception e) {
            logger.error("#getListMessages - ERROR: ", e);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return BaseResponse.commonError(language);
    }
    @POST
    @Path("/delete-check-in")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteCheckIn(@NotNull CheckInRequest request) {
        logger.info("#widgetData - Start: " + request.toLogString());
        Session session = null;
        CheckInResponse response = new CheckInResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            if (CommonUtils.isNullOrEmpty(request.getId().toString())
            ) {
                logger.info("#updateInfor - Validate: Account number invalid");
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

            if (CommonUtils.isNullOrEmpty(request.getId().toString())  ) {
                logger.info("#createBlog - Validate: type invalid");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }
            CheckIn checkIn = checkInDAO.getCheckInById(session, request.getId(), loggedInfo.getAppUser().getOrgId());
            if(checkIn != null)
            {
                List<CheckInImages> checkInImagesList = checkInDAO.getCheckInImagesById(session,checkIn.getId(), loggedInfo.getAppUser().getOrgId());
                if(checkInImagesList.size() > 0)
                {
                    checkInDAO.deleteDetailsByCheckInId(session, checkIn.getId(), loggedInfo.getAppUser().getOrgId());
                    UploadConfigInfo uploadConfig = AppConfigurations.shared().getUploadConfig();
                    for (CheckInImages image : checkInImagesList) {
                        String fullFolderPath = String.format("%s/%s", uploadConfig.getBaseDocumentFolder(), image.getUrlImage());
                        boolean b = FileMgtUtils.deleteFile(fullFolderPath);
                        if (!b)
                        {
                            logger.info("#updateCheckIn - can not delete file");
                            return BaseResponse.build(ErrorCode.CAN_NOT_DELETE_FILE, language);
                        }
                    }

                }
                checkInDAO.delete(session, checkIn.getClass(), checkIn.getId());
            }
            response = new CheckInResponse(ErrorCode.SUCCESS, language);
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
    @Path("/update-checkin-details")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse updateCheckInDetails(@NotNull CheckInRequest request) {
        logger.info("#updateCheckInDetails - Start: " + request.toLogString());
        Session session = null;
        CheckInResponse response = new CheckInResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            if(CommonUtils.isNullOrEmpty(request.getId().toString()) || CommonUtils.isNullOrEmpty(request.getDescription())) {
                logger.info("#updateCheckInDetails - Validate: id invalid");
                return BaseResponse.build(ErrorCode.ERR_INVALID_INFORMATION, language);
            }
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("#updateCheckInDetails error : loggedInfo is null");
                return BaseResponse.commonError(language);
            }
            logger.info("#updateCheckInDetails Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#updateCheckInDetails Check session");
            if (!checkDbSession(session)) {
                logger.info("#updateCheckInDetails DB Connection error");
                return BaseResponse.commonError(language);
            }
            CheckInImages checkInImages = checkInImagesDAO.getCheckInImagesById(session, loggedInfo.getAppUser().getOrgId(), request.getId() );
            if(checkInImages != null)
            {
               checkInImages.setDescription(request.getDescription());
               checkInImagesDAO.update(session,checkInImages);
               response = new CheckInResponse(ErrorCode.SUCCESS, language);
               return response;
            }
            else
            {
                logger.info("#updateCheckInDetails DB Connection error");
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
    @POST
    @Path("/get-checkin-details")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getCheckInDetails(@NotNull CheckInRequest request) {
        logger.info("#getCheckInDetails - Start: " + request.toLogString());
        Session session = null;
        CheckInResponse response = new CheckInResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            if(CommonUtils.isNullOrEmpty(request.getId().toString())) {
                logger.info("#getCheckInDetails - Validate: id invalid");
                return BaseResponse.build(ErrorCode.ERR_INVALID_INFORMATION, language);
            }
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("#getCheckInDetails error : loggedInfo is null");
                return BaseResponse.commonError(language);
            }
            logger.info("#getCheckInDetails Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#getCheckInDetails Check session");
            if (!checkDbSession(session)) {
                logger.info("#getCheckInDetails DB Connection error");
                return BaseResponse.commonError(language);
            }
            CheckInImages checkInImages = checkInImagesDAO.getCheckInImagesById(session, loggedInfo.getAppUser().getOrgId(), request.getId() );
            if(checkInImages != null)
            {
                response.setCheckInImages(checkInImages);
                return response;
            }
            else
            {
                logger.info("#updateCheckInDetails DB Connection error");
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

    @POST
    @Path("/get-check-in-by-id")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BaseResponse getCheckInById(@NotNull CheckInRequest request) {
        logger.info("#getCheckInById - Start: " + request.toLogString());
        CheckInResponse response = null;
        UserLoggedInfo loggedInfo;
        CheckInDAO checkInDAO;
        Session session = null;
        try {
            loggedInfo = getUserLoggedInfo();
            if(CommonUtils.isNullOrEmpty(request.getId().toString())) {
                logger.info("#getCheckInDetails - Validate: id invalid");
                return BaseResponse.build(ErrorCode.ERR_INVALID_INFORMATION, language);
            }
            checkInDAO = new CheckInDAO();
            logger.info("#getCheckInById Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#getCheckInById Check session");
            if (!checkDbSession(session)) {
                logger.info("#getCheckInById DB Connection error");
                return BaseResponse.commonError(language);
            }

            CheckInInfo checkInInfo = checkInDAO.getCheckInDetails(session, request.getId(), loggedInfo.getAppUser().getOrgId());
            if(checkInInfo == null)
            {
                logger.info("#getCheckInById no data");
                return BaseResponse.build(ErrorCode.ERR_CHECK_IN, language);
            }
            else {
                response = new CheckInResponse(ErrorCode.SUCCESS, language);
                response.setCheckInInfo(checkInInfo);
                return response;
            }
        } catch (Exception e) {
            logger.error("#getCheckInById - ERROR: ", e);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return BaseResponse.commonError(language);
    }


    private String uploadFile(String accountNumber, String fileUploadType, UploadConfigInfo uploadConfig, FormDataBodyPart file) {
        try {
            String fileExt = file.getMediaType().getSubtype();
            String fileName = String.format("%s_%s_%d.%s", fileUploadType, accountNumber, System.currentTimeMillis(), fileExt);
            String folderPath = String.format("%s/%s", uploadConfig.getCheckInFolder(), accountNumber);

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
}
