package vn.supperapp.apigw.restful.controllers.auth;

import com.viettel.ewallet.utils.iso.msg.IsoObject;
import vn.supperapp.apigw.beans.OrganizationInfo;
import vn.supperapp.apigw.db.dao.EmployeeDAO;
import vn.supperapp.apigw.db.dao.OrganizationDAO;
import vn.supperapp.apigw.db.dto.*;
import vn.supperapp.apigw.emvqrcode.consumer.QrCodeConsumerInfo;
import vn.supperapp.apigw.beans.UserInfo;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.beans.V_AccountStatusBean;
import vn.supperapp.apigw.configs.AppConfigurations;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.AccountDAO;
import vn.supperapp.apigw.db.dao.UserDAO;
import vn.supperapp.apigw.restful.controllers.BaseController;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.auth.LoginRequest;
import vn.supperapp.apigw.restful.models.auth.LoginResponse;
import vn.supperapp.apigw.services.ConfigDataService;
import vn.supperapp.apigw.services.UrlLoginInfoConfig;
import vn.supperapp.apigw.utils.Constants;
import vn.supperapp.apigw.utils.ErrorCode;
import vn.supperapp.apigw.messaging.MessagingClient;
import vn.supperapp.apigw.messaging.beans.Message;
import vn.supperapp.apigw.messaging.beans.MessageContent;
import vn.supperapp.apigw.redis.RedisService;
import vn.supperapp.apigw.services.EMVQrCodeService;
import vn.supperapp.apigw.services.OtpService;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.CryptUtils;
import vn.supperapp.apigw.utils.LanguageUtils;
import org.hibernate.Session;
import org.slf4j.LoggerFactory;
import vn.supperapp.apigw.utils.enums.*;


import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/auth")
@RsDefaultFilterMapping
@RsResponseFilterMapping
public class AuthController extends BaseController {
    public AuthController() {
        this.logger = LoggerFactory.getLogger(AuthController.class);
    }

    @POST
    @Path("/info")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse info(@NotNull LoginRequest request) {
        logger.info("#info - Start: " + request.toLogString());
        LoginResponse response = new LoginResponse(ErrorCode.SUCCESS, language);
        Session session = null;
        try {
            logger.info("#info validate data");
            //region - Validate
            if (!verifySignature(request.getSignature(), request.getAccountNumber())) {
                logger.info("#info - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }

            //TODO: Validate phone
            if (CommonUtils.isNullOrEmpty(request.getAccountNumber())) {
                logger.info("#info - Validate: Account number invalid");
                return BaseResponse.build(ErrorCode.ERR_LOGIN_INVALID_INFORMATION, language);
            }

            String accountNumber = CommonUtils.getMsisdn(request.getAccountNumber().trim());
            //endregion

            V_AccountStatusBean v_accountStatusBean = getDataFromRedis(accountNumber);
            logger.info("#login - v_accountStatusBean:" + v_accountStatusBean);
            AppUser user = new AppUser();
            if (v_accountStatusBean == null || v_accountStatusBean.getAccountStateId() == com.viettel.ewallet.utils.config.Constants.ACCOUNT_STATE_ID.CANCELED
                    || v_accountStatusBean.getAccountStateId() == com.viettel.ewallet.utils.config.Constants.ACCOUNT_STATE_ID.BLOCKED
                    || v_accountStatusBean.getAccountStateId() == com.viettel.ewallet.utils.config.Constants.ACCOUNT_STATE_ID.INVALID_PIN
            ) {
                logger.info("#user Open db app session");
                session = DbSessionMgt.shared().getSession();
                user = new UserDAO().getAppUserByMsisdn(session, accountNumber);
                if (user == null) {
                    logger.info("#checkUser: Fail to check response");
                    return BaseResponse.commonError(this.language);
                }
            } else {
                user.setRoleId(v_accountStatusBean.getRoleId());
                user.setStatus(v_accountStatusBean.getAccountStateId());
            }
            if (Constants.ACCOUNT_STATE_ID_RESET_PIN.equals(user.getStatus())) {
                logger.info("#login - ISO: Account is in reset PIN state");
                return BaseResponse.build(ErrorCode.ERR_LOGIN_ACCOUNT_RESET_PIN, language);
            }

            if (Constants.ACCOUNT_STATE_ID_INVALID_PIN.equals(user.getStatus())) {
                logger.info("#login - ISO: Account is in invalid PIN state");
                return BaseResponse.build(ErrorCode.ERR_LOGIN_ACCOUNT_INVALID_PIN, language);
            }

            if (!Constants.ACCOUNT_STATE_ACTIVE.equals(user.getStatus())) {
                return BaseResponse.build(ErrorCode.ERR_ACCOUNT_NOT_ACTIVE, language);
            }
            if (!Constants.CUSTOMER.equals(user.getRoleId())) {
                return BaseResponse.build(ErrorCode.ERR_ACCOUNT_NOT_END_USER, language);
            }

            return response;
        } catch (Exception ex) {
            logExceptions("#login - Error: ", ex);
            ex.printStackTrace();
            logger.error("#login - EXCEPTION: ", ex);

        }
        return new BaseResponse();
    }


    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse login(@NotNull LoginRequest request) {
        logger.info("#login - Start: " + request.toLogString());
        Session session = null;
        LoginResponse response = new LoginResponse(ErrorCode.SUCCESS, language);
        UserDAO dao = null;
        IsoObject isoResponsePin = new IsoObject();

        try {
            logger.info("#login validate data");
            //region - Validate
            if (!verifySignature(request.getSignature(), request.getAccountNumber(), request.getPass())) {
                logger.info("#login - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }

            //TODO: Validate phone
            if (CommonUtils.isNullOrEmpty(request.getAccountNumber())) {
                logger.info("#login - Validate: Account number invalid");
                return BaseResponse.build(ErrorCode.ERR_LOGIN_INVALID_INFORMATION, language);
            }

            String accountNumber = CommonUtils.getMsisdn(request.getAccountNumber().trim());

            if (!CommonUtils.validatePin(request.getPass())) {
                logger.info("#login - Validate: PIN invalid");
                return BaseResponse.build(ErrorCode.ERR_LOGIN_INVALID_INFORMATION, language);
            }

            //endregion

            String transId = CommonUtils.generateUUID();

            logger.info("#login Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#login Check session");
            if (!checkDbSession(session)) {
                logger.info("#login DB Connection error");
                return BaseResponse.commonError(language);
            }

            dao = new UserDAO();

            AppDevice appDevice = dao.getUserAppDeviceById(session, deviceId);
            if (appDevice == null) {
                logger.info("#login - not found device at db");
                return BaseResponse.commonError(language);
            } else if (AppDeviceStatus.INACTIVE.is(appDevice.getStatus())) {
                logger.info("#login - Device inactive");
                return BaseResponse.commonError(language);
            }

            V_AccountStatusBean v_accountStatusBean = getDataFromRedis(accountNumber);
            AppUser user = new AppUser();
            Account account = new Account();
            HrEmployee employee = new HrEmployee();
            logger.info("#login - v_accountStatusBean:" + v_accountStatusBean);
            if (v_accountStatusBean == null || v_accountStatusBean.getAccountStateId() == com.viettel.ewallet.utils.config.Constants.ACCOUNT_STATE_ID.CANCELED
                    || v_accountStatusBean.getAccountStateId() == com.viettel.ewallet.utils.config.Constants.ACCOUNT_STATE_ID.BLOCKED
                    || v_accountStatusBean.getAccountStateId() == com.viettel.ewallet.utils.config.Constants.ACCOUNT_STATE_ID.INVALID_PIN
            ) {
                logger.info("#user Open db app session");
                session = DbSessionMgt.shared().getSession();
                user = new UserDAO().getAppUserBy(session, appDevice.getAppUserId());
                account = new AccountDAO().getAccountById(session, user.getAccountId());
                employee = new EmployeeDAO().getEmployeeByAppUser(session, user.getId(), user.getOrgId());
                if (account == null) {
                    logger.info("#checkUser: Fail to check response");
                    return BaseResponse.commonError(this.language);
                }
            } else {
                account.setTier(v_accountStatusBean.getTier());
                account.setPan(v_accountStatusBean.getPan());
                user.setFullName(v_accountStatusBean.getLastName());
                user.setMsisdn(v_accountStatusBean.getMsisdn());
                user.setId(v_accountStatusBean.getAccountId());
                user.setRoleId(v_accountStatusBean.getRoleId());
                user.setLanguage(v_accountStatusBean.getPreferredLanguage());
                account.setPaperType(Long.valueOf(v_accountStatusBean.getPaperType()));
                account.setPaperNumber(v_accountStatusBean.getPaperNumber());
                account.setGender(Long.valueOf(v_accountStatusBean.getGender()));
                user.setStatus(Long.valueOf(v_accountStatusBean.getAccountStateId()));
            }

            if (!Constants.ACCOUNT_STATE_ACTIVE.equals(user.getStatus().toString())) {
                return BaseResponse.build(ErrorCode.ERR_ACCOUNT_NOT_ACTIVE, language);
            }
            if (user.getRoleId() == null || !Constants.CUSTOMER.equals(user.getRoleId().toString())) {
                return BaseResponse.build(ErrorCode.ERR_ACCOUNT_NOT_END_USER, language);
            }

            logger.info("#login - Check AppUser from DB");
            AppUser appUser = null;
            if (user == null) {
                logger.info("#login - Generate QR Code");
                QrCodeConsumerInfo qrConsumer = EMVQrCodeService.shared().generateConsumerQrCode(accountNumber,
                        account.getPan(), accountNumber, user.getFullName(), Language.get(language).name());

                //TODO: Create app user first
                logger.info("#login - Create new App User");
                appUser = new AppUser();
                appUser.setMsisdn(accountNumber);
                appUser.setFullName(user.getFullName());
                appUser.setAccountType(account.getTier()); //TODO: Change later, check from ISO
                appUser.setStatus(AppUserStatus.ACTIVE.value());
                appUser.setExpiredToken(30L); //TODO: Change later, by config
                appUser.setClientType(ClientType.END_USER_APP.value());
                appUser.setLanguage(language);
                account.setLastLogin(new Date());
                if (qrConsumer != null) {
                    appUser.setConsumerQrCode(qrConsumer.getQrCode());
                }

                logger.info("#login - Save app user to db");
                dao.save(session, appUser);

                logger.info("#login - Update app device to db");
                appDevice.setMsisdn(appUser.getMsisdn());
                appDevice.setAppUserId(appUser.getId());
                //update firebase token
                if (request.getFirebaseToken() != null) {
                    appDevice.setFirebaseToken(request.getFirebaseToken());
                }
                //end update firebase token
                dao.update(session, appDevice);

            } else {
                appUser = user;
                if (AppUserStatus.LOCKED.is(appUser.getStatus())) {
                    logger.info("#login - ISO: App user locked, not allow to use App");
                    return BaseResponse.build(ErrorCode.ERR_USER_LOCKED, language);
                } else if (CommonUtils.isNullOrEmpty(appDevice.getMsisdn()) || !appDevice.getMsisdn().equals(accountNumber)) {
                    logger.info("#login - Check account is login in other devices");
                    List<AppDevice> listDeviceACtive = dao.getListActiveDevice(session, appUser.getId());
                    if (listDeviceACtive.size() > 0) {
                        response.setShowHideDeactiveDevice(true);
                    }
                }
            }

            if (!request.getPass().equals(account.getPass())) {
                logger.info("#login - TX: ResponsePin is NOT success");
                isoResponsePin.setResponseCode("10155");
                isoResponsePin.setResponseDescription(null);
                return BaseResponse.buildError(isoResponsePin, language);
            }

            UserLoggedInfo loggedInfo = new UserLoggedInfo();
            loggedInfo.setAppUser(appUser);
            loggedInfo.setAppDevice(appDevice);
            Branch branch = dao.getBranchName(session, employee.getBranchId(), appUser.getOrgId());
            UserInfo userInfo = new UserInfo(account, CryptUtils.encrypt(request.getPass()), appUser.getAvatarUrl(), employee.getCode());
            userInfo.setAvatarUrl(appUser.getAvatarUrl());
            userInfo.setEmail(employee.getEmail());
            userInfo.setGender(account.getGender());
            userInfo.setBirthDay(account.getBirthday());
            userInfo.setPaperNumber(account.getPaperNumber());
            userInfo.setTimeKeepingType(employee.getTimekeepingType());
            userInfo.setOrgId(appUser.getOrgId());
            userInfo.setBranchId(employee.getBranchId());
            userInfo.setBranchCode(branch.getCode());
            userInfo.setDepartmentId(employee.getDepartmentId());
            loggedInfo.setUserInfo(userInfo);
            loggedInfo.setPan(account.getPan());

            AppUserWidget appUserWidget = dao.getAppUserWidget(session, loggedInfo.getAppUser().getId());
            if (appUserWidget == null) {
                appUserWidget = new AppUserWidget();
                AppWidget appWidget = dao.getAppWidgetPersonal(session);
                if (appWidget != null) {
                    appUserWidget.setWidgetId(appWidget.getId());
                } else {
                    appUserWidget.setWidgetId(1L);
                }
                appUserWidget.setUserId(loggedInfo.getAppUser().getId());
                appUserWidget.setCreateBy(loggedInfo.getAppUser().getMsisdn()); //TODO: Change later, check from ISO
                appUserWidget.setCreateAt(new Date());
                logger.info("#login - Save app widget to db");
                dao.save(session, appUser);
            }

            processLoginComplete(session, dao, response, loggedInfo, request.getTransId(), appDevice, appUser, accountNumber, request);
            response.setTransId(transId);
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
    @Path("/short-login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse shortLogin(@NotNull LoginRequest request) {
        logger.info("#login - Start: " + request.toLogString());
        Session session = null;
        LoginResponse response = new LoginResponse(ErrorCode.SUCCESS, language);
        UserDAO dao = null;
        IsoObject isoResponsePin = new IsoObject();

        try {
            logger.info("#login validate data");
            //region - Validate
            if (!verifySignature(request.getSignature(), request.getAccountNumber(), request.getPass())) {
                logger.info("#login - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }

            //TODO: Validate phone
            if (CommonUtils.isNullOrEmpty(request.getAccountNumber())) {
                logger.info("#login - Validate: Account number invalid");
                return BaseResponse.build(ErrorCode.ERR_LOGIN_INVALID_INFORMATION, language);
            }

            String accountNumber = CommonUtils.getMsisdn(request.getAccountNumber().trim());

            if (!CommonUtils.validatePin(request.getPass())) {
                logger.info("#login - Validate: PIN invalid");
                return BaseResponse.build(ErrorCode.ERR_LOGIN_INVALID_INFORMATION, language);
            }

            //endregion

            String transId = CommonUtils.generateUUID();

            logger.info("#login Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#login Check session");
            if (!checkDbSession(session)) {
                logger.info("#login DB Connection error");
                return BaseResponse.commonError(language);
            }

            dao = new UserDAO();

            AppDevice appDevice = dao.getUserAppDeviceByIdMsisdn(session, deviceId, accountNumber);
            if ((appDevice == null || appDevice.getAppUserId() == null || AppDeviceStatus.INACTIVE.is(appDevice.getStatus())) && request.getOrgId() == null) {
                logger.info("#login - not found device at db");
                List<OrganizationInfo> organizationInfoList = new OrganizationDAO().getListOrgInfo(session, accountNumber);
                if (organizationInfoList.size() > 0) {
                    response.setListOrg(organizationInfoList);
                    return response;
                } else {
                    logger.info("#login DB Connection error");
                    return BaseResponse.commonError(language);
                }
            } else if (appDevice.getAppUserId() == null || request.getOrgId() != null) {
                AppUser appUser = dao.getAppUserByPhoneAngOrg(session, accountNumber, request.getOrgId());
                if (appUser == null) {
                    logger.info("#login - Validate: Account number invalid");
                    return BaseResponse.build(ErrorCode.ERR_LOGIN_INVALID_INFORMATION, language);
                } else {
                    appDevice.setAppUserId(appUser.getId());
                    appDevice.setMsisdn(appUser.getMsisdn());
                    dao.update(session, appDevice);
                }
            }
            V_AccountStatusBean v_accountStatusBean = getDataFromRedis(accountNumber);
            AppUser user = new AppUser();
            Account account = new Account();
            HrEmployee employee = new HrEmployee();
            logger.info("#login - v_accountStatusBean:" + v_accountStatusBean);
            if (v_accountStatusBean == null || v_accountStatusBean.getAccountStateId() == com.viettel.ewallet.utils.config.Constants.ACCOUNT_STATE_ID.CANCELED
                    || v_accountStatusBean.getAccountStateId() == com.viettel.ewallet.utils.config.Constants.ACCOUNT_STATE_ID.BLOCKED
                    || v_accountStatusBean.getAccountStateId() == com.viettel.ewallet.utils.config.Constants.ACCOUNT_STATE_ID.INVALID_PIN
            ) {
                logger.info("#user Open db app session");
                session = DbSessionMgt.shared().getSession();
                user = new UserDAO().getAppUserBy(session, appDevice.getAppUserId());
                account = new AccountDAO().getAccountById(session, user.getAccountId());
                employee = new EmployeeDAO().getEmployeeByAppUser(session, user.getId(), user.getOrgId());
                if (user == null) {
                    logger.info("#checkUser: Fail to check response");
                    return BaseResponse.commonError(this.language);
                }
                if (employee == null) {
                    logger.info("#checkUser: Fail to check response");
                    return BaseResponse.commonError(this.language);
                }
            } else {
                account.setTier(v_accountStatusBean.getTier());
                account.setPan(v_accountStatusBean.getPan());
                user.setFullName(v_accountStatusBean.getLastName());
                user.setMsisdn(v_accountStatusBean.getMsisdn());
                user.setId(v_accountStatusBean.getAccountId());
                user.setRoleId(v_accountStatusBean.getRoleId());
                user.setLanguage(v_accountStatusBean.getPreferredLanguage());
                account.setPaperType(Long.valueOf(v_accountStatusBean.getPaperType()));
                account.setPaperNumber(v_accountStatusBean.getPaperNumber());
                account.setGender(Long.valueOf(v_accountStatusBean.getGender()));
                user.setStatus(Long.valueOf(v_accountStatusBean.getAccountStateId()));
            }

            if (!Constants.ACCOUNT_STATE_ACTIVE.equals(user.getStatus().toString())) {
                return BaseResponse.build(ErrorCode.ERR_ACCOUNT_NOT_ACTIVE, language);
            }
            if (user.getRoleId() == null || !Constants.CUSTOMER.equals(user.getRoleId().toString())) {
                return BaseResponse.build(ErrorCode.ERR_ACCOUNT_NOT_END_USER, language);
            }

            logger.info("#login - Check AppUser from DB");
            List<AppUser> appUsers = dao.getAppUserBy(session, accountNumber);
            if (appUsers != null && !appUsers.isEmpty() && account.getTier() != null) {
                AppUser appUser = appUsers.get(0);
                appUser.setAccountType(user.getAccountType());
                dao.update(session, appUser);
            }
            AppUser appUser = null;
            if (appUsers.isEmpty()) {
                logger.info("#login - Generate QR Code");
                QrCodeConsumerInfo qrConsumer = EMVQrCodeService.shared().generateConsumerQrCode(accountNumber,
                        account.getPan(), accountNumber, user.getFullName(), Language.get(language).name());

                //TODO: Create app user first
                logger.info("#login - Create new App User");
                appUser = new AppUser();
                appUser.setMsisdn(accountNumber);
                appUser.setFullName(user.getFullName());
                appUser.setAccountType(account.getTier()); //TODO: Change later, check from ISO
                appUser.setStatus(AppUserStatus.ACTIVE.value());
                appUser.setExpiredToken(30L); //TODO: Change later, by config
                appUser.setClientType(ClientType.END_USER_APP.value());
                appUser.setLanguage(language);
                account.setLastLogin(new Date());
                if (qrConsumer != null) {
                    appUser.setConsumerQrCode(qrConsumer.getQrCode());
                }

                logger.info("#login - Save app user to db");
                dao.save(session, appUser);

                logger.info("#login - Update app device to db");
                appDevice.setMsisdn(appUser.getMsisdn());
                appDevice.setAppUserId(appUser.getId());
                //update firebase token
                if (request.getFirebaseToken() != null) {
                    appDevice.setFirebaseToken(request.getFirebaseToken());
                }
                //end update firebase token
                dao.update(session, appDevice);

            } else {
                appUser = appUsers.get(0);
                if (AppUserStatus.LOCKED.is(appUser.getStatus())) {
                    logger.info("#login - ISO: App user locked, not allow to use App");
                    return BaseResponse.build(ErrorCode.ERR_USER_LOCKED, language);
                } else if (CommonUtils.isNullOrEmpty(appDevice.getMsisdn()) || !appDevice.getMsisdn().equals(accountNumber)) {
                    logger.info("#login - Check account is login in other devices");
                    List<AppDevice> listDeviceACtive = dao.getListActiveDevice(session, appUser.getId());
                    if (listDeviceACtive.size() > 0) {
                        response.setShowHideDeactiveDevice(true);
                    }
                }
            }

            if (!request.getPass().equals(account.getPass())) {
                logger.info("#login - TX: ResponsePin is NOT success");
                isoResponsePin.setResponseCode("10155");
                isoResponsePin.setResponseDescription(null);
                return BaseResponse.buildError(isoResponsePin, language);
            }

            UserLoggedInfo loggedInfo = new UserLoggedInfo();
            loggedInfo.setAppUser(appUser);
            loggedInfo.setAppDevice(appDevice);

            UserInfo userInfo = new UserInfo(account, CryptUtils.encrypt(request.getPass()), appUser.getAvatarUrl(), employee.getCode());
            userInfo.setAvatarUrl(appUser.getAvatarUrl());
            userInfo.setEmail(employee.getEmail());
            userInfo.setGender(account.getGender());
            userInfo.setBirthDay(account.getBirthday());
            userInfo.setPaperNumber(account.getPaperNumber());
            userInfo.setTimeKeepingType(employee.getTimekeepingType());
            userInfo.setOrgId(appUser.getOrgId());
            userInfo.setBranchId(employee.getBranchId());
            userInfo.setDepartmentId(employee.getDepartmentId());
            loggedInfo.setUserInfo(userInfo);
            loggedInfo.setPan(account.getPan());

            AppUserWidget appUserWidget = dao.getAppUserWidget(session, loggedInfo.getAppUser().getId());
            if (appUserWidget == null) {
                appUserWidget = new AppUserWidget();
                AppWidget appWidget = dao.getAppWidgetPersonal(session);
                if (appWidget != null) {
                    appUserWidget.setWidgetId(appWidget.getId());
                } else {
                    appUserWidget.setWidgetId(1L);
                }
                appUserWidget.setUserId(loggedInfo.getAppUser().getId());
                appUserWidget.setCreateBy(loggedInfo.getAppUser().getMsisdn()); //TODO: Change later, check from ISO
                appUserWidget.setCreateAt(new Date());
                logger.info("#login - Save app widget to db");
                dao.save(session, appUser);
            }

            processLoginComplete(session, dao, response, loggedInfo, request.getTransId(), appDevice, appUser, appUser.getMsisdn(), request);

            logger.info("#encAccessToken db session");
            if (CommonUtils.isNullOrEmpty(request.getDeviceId()) || CommonUtils.isNullOrEmpty(request.getBuildKey())) {
                return new BaseResponse();
            }

            String cryptDataDecrypt = CryptUtils.decryptRSA(response.getAccessToken(), request.getRsaPrivateKey());

            logger.info("#encAccessToken db session");
            if (CommonUtils.isNullOrEmpty(request.getDeviceId()) || CommonUtils.isNullOrEmpty(request.getBuildKey())) {
                return new BaseResponse();
            }
            response.setCryptData(CryptUtils.encryptRSA(cryptDataDecrypt, request.getRsaPublicKey()));

            response.setTransId(transId);
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
    @Path("/login-otp")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse loginOtp(@NotNull LoginRequest request) {
        logger.info("#loginOtp - Start: " + request.toLogString());
        Session session = null;
        LoginResponse response = new LoginResponse(ErrorCode.SUCCESS, language);
        UserDAO dao = null;
        IsoObject isoRequest = null;
        IsoObject isoResponse = null;
        try {
            logger.info("#loginOtp validate data");
            //region - Validate
            if (!verifySignature(request.getSignature(), request.getTransId(), request.getOtp())) {
                logger.info("#loginOtp - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }

            if (CommonUtils.isNullOrEmpty(request.getTransId()) || CommonUtils.isNullOrEmpty(request.getOtp())) {
                logger.info("#loginOtp - Validate: Account number invalid");
                return BaseResponse.build(ErrorCode.ERR_LOGIN_INVALID_INFORMATION, language);
            }

            //endregion

            logger.info("#loginOtp - Get data from cached");
            OtpService otpService = RedisService.shared().get(OtpService.class, Constants.REDIS_DB_LOGIN_CACHED, request.getTransId());

            if (otpService == null) {
                logger.info("#loginOtp - Not found OTP or expired");
                return BaseResponse.build(ErrorCode.ERR_TRANSACTION_EXPIRED, language);
            }

            if (!otpService.verifyOtp(request.getOtp())) {
                //TODO: Update fail counter
                logger.info("#loginOtp: OTP invalid");
                return BaseResponse.build(ErrorCode.ERR_OTP_ID_INVALID, language);
            }

            UserLoggedInfo loggedInfo = otpService.getData(UserLoggedInfo.class);
            if (loggedInfo == null) {
                logger.info("#loginOtp - Not found OTP or expired");
                return BaseResponse.build(ErrorCode.ERR_TRANSACTION_EXPIRED, language);
            }

            //isoResponse = otpService.getIsoData();

            logger.info("#loginOtp Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#loginOtp Check session");
            if (!checkDbSession(session)) {
                logger.info("#loginOtp DB Connection error");
                return BaseResponse.commonError(language);
            }

            dao = new UserDAO();

            logger.info("#login - Get AppDevice from db");
            AppDevice appDevice = dao.getUserAppDeviceById(session, loggedInfo.getAppDevice().getId());

            if (appDevice == null) {
                logger.info("#loginOtp - Cached invalid (AppDevice)");
                return BaseResponse.commonError(language);
            }

            AppUser appUser = dao.getAppUserBy(session, loggedInfo.getAppUser().getId());
            if (appUser == null) {
                logger.info("#loginOtp - Cached invalid (AppDevice)");
                return BaseResponse.commonError(language);
            }

            processLoginComplete(session, dao, response, loggedInfo, request.getTransId(), appDevice, appUser, appUser.getMsisdn(), request);

            logger.info("#loginOtp - Response");
            return response;
        } catch (Exception ex) {
            logExceptions("#loginOtp - Error: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

    @POST
    @Path("/access-token")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getAccessToken(@NotNull LoginRequest request) {
        logger.info("#loginOtp - Start: " + request.toLogString());
        Session session = null;
        LoginResponse response = new LoginResponse(ErrorCode.SUCCESS, language);
        UserDAO dao = null;
        IsoObject isoRequest = null;
        IsoObject isoResponse = null;
        try {
            logger.info("#loginOtp validate data");
            //region - Validate
            if (!verifySignature(request.getSignature())) {
                logger.info("#loginOtp - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }

            //endregion

            logger.info("#loginOtp - Get data from cached");
            OtpService otpService = RedisService.shared().get(OtpService.class, Constants.REDIS_DB_LOGIN_CACHED, request.getTransId());

            if (otpService == null) {
                logger.info("#loginOtp - Not found OTP or expired");
                return BaseResponse.build(ErrorCode.ERR_TRANSACTION_EXPIRED, language);
            }

            UserLoggedInfo loggedInfo = otpService.getData(UserLoggedInfo.class);
            if (loggedInfo == null) {
                logger.info("#loginOtp - Not found OTP or expired");
                return BaseResponse.build(ErrorCode.ERR_TRANSACTION_EXPIRED, language);
            }

            //isoResponse = otpService.getIsoData();

            logger.info("#loginOtp Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#loginOtp Check session");
            if (!checkDbSession(session)) {
                logger.info("#loginOtp DB Connection error");
                return BaseResponse.commonError(language);
            }

            dao = new UserDAO();

            logger.info("#login - Get AppDevice from db");
            AppDevice appDevice = dao.getUserAppDeviceById(session, loggedInfo.getAppDevice().getId());

            if (appDevice == null) {
                logger.info("#loginOtp - Cached invalid (AppDevice)");
                return BaseResponse.commonError(language);
            }

            AppUser appUser = dao.getAppUserBy(session, loggedInfo.getAppUser().getId());
            AppUserWidget appUserWidget = dao.getAppUserWidget(session, loggedInfo.getAppUser().getId());
            if (appUserWidget == null) {
                appUserWidget = new AppUserWidget();
                appUserWidget.setUserId(loggedInfo.getAppUser().getId());
                appUserWidget.setWidgetId(1L);
                appUserWidget.setCreateBy(loggedInfo.getAppUser().getMsisdn()); //TODO: Change later, check from ISO
                appUserWidget.setCreateAt(new Date());

                logger.info("#login - Save app user to db");
                dao.save(session, appUser);
            }
            if (appUser == null) {
                logger.info("#loginOtp - Cached invalid (AppDevice)");
                return BaseResponse.commonError(language);
            }

            processLoginComplete(session, dao, response, loggedInfo, request.getTransId(), appDevice, appUser, appUser.getMsisdn(), request);

            logger.info("#loginOtp - Response");
            return response;
        } catch (Exception ex) {
            logExceptions("#loginOtp - Error: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

    private void processLoginComplete(Session session, UserDAO dao, LoginResponse response, UserLoggedInfo loggedInfo, String transId,
                                      AppDevice appDevice, AppUser appUser, String accountNumber, LoginRequest request) throws Exception {
        loggedInfo.setTransId(transId);
        response.setTransId(transId);

        logger.info("#processLoginComplete - Generate access token");
        String accessToken = String.format("%d%s%s", System.currentTimeMillis(), appDevice.getDeviceId(), accountNumber);
        if (accessToken.length() < 128) {
            accessToken = String.format("%s%s", accessToken, CommonUtils.generateRandomString(128 - accessToken.length()));
        }

        appDevice.setAccessToken(accessToken);
        loggedInfo.setAccessToken(accessToken);
        response.setAccessToken(CryptUtils.encryptRSA(accessToken, appDevice.getAppPublicKey()));

        logger.info("#processLoginComplete - Generate refresh token");
        String refreshToken = CommonUtils.generateUUID();
        if (refreshToken.length() < 128) {
            refreshToken = String.format("%s%s", refreshToken, CommonUtils.generateRandomString(128 - refreshToken.length()));
        }

        appDevice.setRefreshToken(refreshToken);
        loggedInfo.setRefreshToken(refreshToken);
        response.setRefreshToken(CryptUtils.encryptRSA(refreshToken, appDevice.getAppPublicKey()));

        response.setUserInfo(loggedInfo.getUserInfo());
        response.setUrl(getUrlLoginConfig(language, session, dao));
//        response.setDevice(appDevice);

        logger.info("#processLoginComplete - Set time to check token expired");
        loggedInfo.setStartTime(System.currentTimeMillis());
        loggedInfo.setUpdateTime(System.currentTimeMillis());

        logger.info("#processLoginComplete - Save token to cached");
        String loginCachedKey = String.format("TOKEN_%s", deviceId);
//        RedisService.shared().set(Constants.REDIS_DB_LOGIN_CACHED, loginCachedKey, loggedInfo, 30 * 24 * 60 * 60);
        RedisService.shared().set(Constants.REDIS_DB_LOGIN_CACHED, loginCachedKey, loggedInfo, AppConfigurations.shared().getLoginTokenCachedExpired());

        logger.info("#processLoginComplete - Update user to db");
        appDevice.setLastLoginTime(new Date());
        appDevice.setStatus(AppDeviceStatus.LOGGED_IN.value());
        appDevice.setAppUserId(appUser.getId());
        appDevice.setMsisdn(appUser.getMsisdn());
        dao.update(session, appDevice);

        if (request.getDeactiveOthersDevice() == 1) {
            logger.info("#processLoginComplete - logout on all device other");
            List<AppDevice> listDeviceLogin = dao.getListDeviceLogin(session, loggedInfo, deviceId);
            if (listDeviceLogin != null && !listDeviceLogin.isEmpty()) {
                for (int i = 0; i < listDeviceLogin.size(); i++) {
                    AppDevice deviceLogout = listDeviceLogin.get(i);
                    deviceLogout.setStatus(AppDeviceStatus.LOGGED_OUT.value());
                    deviceLogout.setAccessToken("");
                    deviceLogout.setRefreshToken("");
                    deviceLogout.setUpdateTime(new Date());
                    deviceLogout.setSignatureKey("");
                    dao.update(session, deviceLogout);

                    logger.info("#processLoginComplete - remove info cached on device other");
                    String cachedKeyOnDeviceOther = String.format("TOKEN_%s", deviceLogout.getDeviceId());
                    RedisService.shared().remove(Constants.REDIS_DB_LOGIN_CACHED, cachedKeyOnDeviceOther);

                }
            }
        }

        Account account = new AccountDAO().getAccountById(session, appUser.getAccountId());
        account.setLastLogin(new Date());
        dao.update(session, appUser);
        new AccountDAO().update(session, account);
    }

    @POST
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RsAuthFilterMapping
    public BaseResponse logout() {
        logger.info("#logout - Start: ");
        Session session = null;
        LoginResponse response = new LoginResponse(ErrorCode.SUCCESS, language);
        UserDAO dao = null;
        IsoObject isoRequest = null;
        IsoObject isoResponse = null;
        try {
            logger.info("#logout validate data");

            //endregion
            logger.info("#logout - Get data from cached");
            UserLoggedInfo loggedInfo = getUserLoggedInfo();

            if (loggedInfo == null) {
                logger.info("#logout - Not found OTP or expired");
                return BaseResponse.build(ErrorCode.ERR_TRANSACTION_EXPIRED, language);
            }
            String loginCachedKey = String.format("TOKEN_%s", deviceId);
            RedisService.shared().remove(Constants.REDIS_DB_LOGIN_CACHED, loginCachedKey);
            //isoResponse = otpService.getIsoData();

            logger.info("#logout Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#logout Check session");
            if (!checkDbSession(session)) {
                logger.info("#logout DB Connection error");
                return BaseResponse.commonError(language);
            }

            dao = new UserDAO();

            logger.info("#logout - Get AppDevice from db");
            AppDevice appDevice = dao.getUserAppDeviceById(session, loggedInfo.getAppDevice().getId());

            if (appDevice == null) {
                logger.info("#logout - Cached invalid (AppDevice)");
                return BaseResponse.commonError(language);
            }

            AppUser appUser = dao.getAppUserBy(session, loggedInfo.getAppUser().getId());
            if (appUser == null) {
                logger.info("#logout - Cached invalid (AppDevice)");
                return BaseResponse.commonError(language);
            }

            appDevice.setStatus(AppDeviceStatus.LOGGED_OUT.value());
            dao.update(session, appDevice);
            logger.info("#logout - Response");
            return response;
        } catch (Exception ex) {
            logExceptions("#logout - Error: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

    private String sendOtpLogin(UserLoggedInfo loggedInfo, String otp, String transId) {
        try {
            String msgKey = "MSG_LOGIN_OTP_TO_CUSTOMER";

            MessageContent mc = MessageContent.builder()
                    .addReplacement("[OTP]", otp)
                    .addTemplate(Language.ENGLISH.key(), String.format("%s", LanguageUtils.getString(msgKey, Language.ENGLISH.key())))
                    .addTemplate(Language.VIETNAMESE.key(), String.format("%s", LanguageUtils.getString(msgKey, Language.VIETNAMESE.key())))
                    .addTitles(MessagingClient.shared().getApsTitle("login"))
                    .build();

            Message message = Message.builder()
                    .setChannel(ChannelType.APIGW_ENDUSER.code())
                    .setRefId(transId)
                    .setReceiverObj("enduser")
                    .setSender(MessagingClient.shared().getSender())
                    .setReceiver(loggedInfo.getUserInfo().getAccountNumber())
                    .setMessageContent(mc)
                    .setPushNotification(false)
                    .setSaveNotificationLog(false)
                    .build();

            logger.info(message.toJsonString());
            MessagingClient.shared().sendAsyncTask(message);

            return mc.getContents().get(language);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#sendOtpTransaction - EXCEPTION: ", ex);
        }
        return "";
    }

    private String getUrlLoginConfig(String language, Session session, UserDAO dao) {
        logger.info("#getUrlLoginConfig - START");
        try {

            UrlLoginInfoConfig urlLoginInfoConfig = ConfigDataService.shared().getUrlLoginConfigByLanguage(language);

            String configKey = urlLoginInfoConfig.getConfigKey();
            String objectType = urlLoginInfoConfig.getObjectType();
            String objectValue = urlLoginInfoConfig.getObjectValue();

            AppConfig appConfig = dao.getUrlLoginConfigByKey(session, language, configKey, objectType, objectValue);
            if (appConfig == null) {
                logger.info("#getUrlLoginConfig AppConfig is null");
                return "";
            }
            return appConfig.getConfigValue();

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#getUrlLoginConfig - EXCEPTION: ", ex);
        }
        return "";
    }


}
