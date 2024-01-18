package vn.supperapp.apigw.restful.controllers.user;

import com.viettel.ewallet.utils.iso.msg.IsoObject;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.hibernate.Session;
import org.slf4j.LoggerFactory;
import vn.supperapp.apigw.beans.UserInfo;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.beans.V_AccountStatusBean;
import vn.supperapp.apigw.configs.AppConfigurations;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.*;
import vn.supperapp.apigw.db.dto.*;
import vn.supperapp.apigw.emvqrcode.consumer.QrCodeConsumerInfo;
import vn.supperapp.apigw.messaging.MessagingClient;
import vn.supperapp.apigw.messaging.beans.Message;
import vn.supperapp.apigw.messaging.beans.MessageContent;
import vn.supperapp.apigw.objs.UploadConfigInfo;
import vn.supperapp.apigw.redis.RedisService;
import vn.supperapp.apigw.restful.controllers.BaseController;
import vn.supperapp.apigw.restful.controllers.HomeController;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.BaseShopResponse;
import vn.supperapp.apigw.restful.models.auth.LoginRequest;
import vn.supperapp.apigw.restful.models.auth.LoginResponse;
import vn.supperapp.apigw.restful.models.bank.BankRequest;
import vn.supperapp.apigw.restful.models.bank.BankResponse;
import vn.supperapp.apigw.restful.models.user.UploadResponse;
import vn.supperapp.apigw.restful.models.user.UserRequest;
import vn.supperapp.apigw.restful.models.user.UserResponse;
import vn.supperapp.apigw.utils.*;
import vn.supperapp.apigw.utils.enums.*;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Path("/user")
@RsDefaultFilterMapping
@RsAuthFilterMapping
@RsResponseFilterMapping
public class UserController extends BaseController {
    private UserDAO userDAO;
    private HrEmployeeDAO hrEmployeeDAO;
    private AccountDAO accountDAO;
    private BankDAO bankDAO;

    private UserBankDAO userBankDAO;
    public UserController() {
        this.logger = LoggerFactory.getLogger(UserController.class);
        userDAO = new UserDAO();
        accountDAO = new AccountDAO();
        userBankDAO = new UserBankDAO();
        bankDAO = new BankDAO();
        hrEmployeeDAO = new HrEmployeeDAO();
    }

    @POST
    @Path("/info")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse info(@NotNull LoginRequest request) {
        logger.info("#info - Start: " + request.toLogString());
        UserResponse response = new UserResponse(ErrorCode.SUCCESS, language);
        Session session = null;
        UserLoggedInfo userLoggedInfo;
        try {
            logger.info("#info validate data");
            session = DbSessionMgt.shared().getSession();
            logger.info("#info Check session");
            if (!checkDbSession(session)) {
                logger.info("#info DB Connection error");
                return BaseResponse.commonError(language);
            }
            userLoggedInfo = getUserLoggedInfo();
            UserInfo userInfo = null;
            if (userLoggedInfo != null)
            {
                userInfo = userLoggedInfo.getUserInfo();
            }
            else
            {
                return BaseResponse.build(ErrorCode.ERR_LOGIN_INVALID_INFORMATION, language);
            }

            response.setUserInfo(userInfo);
            return response;
        } catch (Exception ex) {
            logExceptions("#login - Error: ", ex);
            ex.printStackTrace();
            logger.error("#login - EXCEPTION: ", ex);

        }
        return new BaseResponse();
    }


    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse update(@NotNull UserRequest request) {
        logger.info("#login - Start: " + request.toLogString());
        Session session = null;
        UserResponse response = new UserResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            logger.info("#updateInfor validate data");
            //TODO: Validate phone
            if (CommonUtils.isNullOrEmpty(request.getAddress()) || CommonUtils.isNullOrEmpty(request.getName()) || CommonUtils.isNullOrEmpty(request.getEmail())
              || CommonUtils.isNullOrEmpty(request.getPaperNumber()) || CommonUtils.isNullOrEmpty(request.getBirthDay()) || CommonUtils.isNullOrEmpty(request.getBankId().toString())
            ) {
                logger.info("#updateInfor - Validate: Account number invalid");
                return BaseResponse.build(ErrorCode.ERR_INVALID_INFORMATION, language);
            }

            session = DbSessionMgt.shared().getSession();
            logger.info("#homeBlog Check session");
            if (!checkDbSession(session)) {
                logger.info("#homeBlog DB Connection error");
                return BaseResponse.commonError(language);
            }
            loggedInfo = getUserLoggedInfo();
            AppUser user = new AppUser();
            Account account = new Account();

            user = new UserDAO().getAppUserByMsisdn(session, loggedInfo.getAppUser().getMsisdn());
            account = new AccountDAO().getAccountById(session, user.getAccountId());
            if (user == null) {
                logger.info("#checkUser: Fail to check response");
                return BaseResponse.commonError(this.language);
            }
            else
            {
                user.setFullName(request.getName());
                userDAO.update(session,user);
            }

            if(account != null)
            {
                account.setPaperNumber(request.getPaperNumber());
                account.setBirthday(DateTimeUtils.toDate(request.getBirthDay()));
                account.setGender(Long.parseLong(request.getGender()));
                account.setFullName(request.getName());
                accountDAO.update(session, account);
            }
            UserBank userBank = accountDAO.getUserBankByUser(session, loggedInfo.getAppUser().getId());
            if (userBank == null)
            {
                UserBank userBank1 = new UserBank();
                userBank1.setBankId(request.getBankId());
                userBank1.setUserId(loggedInfo.getAppUser().getId());
                userBank1.setOrgId(loggedInfo.getAppUser().getOrgId());
                userBank1.setBankNumber(request.getBankNumber());
                userBank1.setStatus(1L);
                userBank1.setCreateAt(new Date());
                userBank1.setCreateBy(loggedInfo.getAppUser().getMsisdn());
                userBankDAO.save(session, userBank1);
            }
            else
            {
                userBank.setBankId(request.getBankId());
                userBank.setBankNumber(request.getBankNumber());
                userBankDAO.update(session, userBank);
            }
            response = new UserResponse(ErrorCode.SUCCESS, language);
            return response;
        } catch (Exception ex) {
            logExceptions("#login - Error: ", ex);
            ex.printStackTrace();
            logger.error("#login - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    @POST
    @Path("/get-list-bank")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getListBank(@NotNull BankRequest request) {
        logger.info("#getListBank - Start: " + request.toLogString());
        Session session = null;
        BankResponse response = new BankResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            logger.info("#getListBank validate data");
            //TODO: Validate phone
            session = DbSessionMgt.shared().getSession();
            logger.info("#getListBank Check session");
            if (!checkDbSession(session)) {
                logger.info("#getListBank DB Connection error");
                return BaseResponse.commonError(language);
            }
            loggedInfo = getUserLoggedInfo();
            List<Bank> bankList = bankDAO.getListBank(session, loggedInfo.getAppUser().getOrgId(), language);
            if (bankList.size() == 0) {
                logger.info("#checkUser: Fail to check response");
                return BaseResponse.commonError(this.language);
            }
            else
            {
                response.setBankList(bankList);
                return response;
            }

        } catch (Exception ex) {
            logExceptions("#login - Error: ", ex);
            ex.printStackTrace();
            logger.error("#login - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    @POST
    @Path("/get-list-users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getListUsers() {
        Session session = null;
        UserResponse response = new UserResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            logger.info("#getListUsers validate data");
            //TODO: Validate phone
            session = DbSessionMgt.shared().getSession();
            logger.info("#getListUsers Check session");
            if (!checkDbSession(session)) {
                logger.info("#getListUsers DB Connection error");
                return BaseResponse.commonError(language);
            }
            loggedInfo = getUserLoggedInfo();
            List<AppUser> userList = userDAO.getUserBy(session, loggedInfo.getAppUser().getOrgId());
            if (userList.size() == 0) {
                logger.info("#checkUser: Fail to check response");
                return BaseResponse.commonError(this.language);
            }
            else
            {
                response.setUserList(userList);
                return response;
            }

        } catch (Exception ex) {
            logExceptions("#login - Error: ", ex);
            ex.printStackTrace();
            logger.error("#login - EXCEPTION: ", ex);
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
    public BaseResponse registerManualApprove(@HeaderParam("content-length") long contentLength,
                                              @FormDataParam("fileAvatar") FormDataBodyPart fileAvatar) {

        logger.info("#userUploadAvatar - Start: fileAvatar={}",
                fileAvatar);
        Session session = null;
        UploadResponse response = new UploadResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo = null;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("#userUploadAvatar error : loggedInfo is null");
                return BaseResponse.commonError(language);
            }
            logger.info("#userUploadAvatar validate data");
            //region - Validate

            if (CommonUtils.isNullAny(fileAvatar)) {
                logger.info("#userUploadAvatar - Validate: file invalid");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            UploadConfigInfo uploadConfig = AppConfigurations.shared().getUploadConfig();

            logger.info("#userUploadAvatar - Validate content length");
            long allowLength = (uploadConfig.getMaxFileSize() * 1024 * 1024 * 4);
            if (contentLength > allowLength) {
                logger.info("#userUploadAvatar - Validate: allowLength file max");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            if (!validateFileFormat(uploadConfig, fileAvatar)) {
                logger.info("#userUploadAvatar - Validate: fileAvatar malformed");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            logger.info("#userUploadAvatar - Upload avatar");
            String subFolder = DateTimeUtils.toString(new Date(), "yyyyMMdd");
            logger.info("#userUploadAvatar - Upload file avatar");
            String avatarPath = uploadFile(subFolder, loggedInfo.getUserInfo().getAccountNumber(), "avatar", uploadConfig, fileAvatar);

            logger.info("#userUploadAvatar Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#userUploadAvatar Check session");
            if (!checkDbSession(session)) {
                logger.info("#userUploadAvatar DB Connection error");
                return BaseResponse.commonError(language);
            }

            AppUser appUser = userDAO.getAppUserByMsisdn(session, loggedInfo.getAppUser().getMsisdn());
            appUser.setAvatarUrl(avatarPath);
            userDAO.save(session, appUser);
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


    private String uploadFile(String subFolder, String accountNumber, String fileUploadType, UploadConfigInfo uploadConfig, FormDataBodyPart file) {
        try {
            String fileExt = file.getMediaType().getSubtype();
            String fileName = String.format("%s_%s_%d.%s", fileUploadType, accountNumber, System.currentTimeMillis(), fileExt);
            String folderPath = String.format("%s/%s/%s", uploadConfig.getDocumentFolder(), subFolder, accountNumber);

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
