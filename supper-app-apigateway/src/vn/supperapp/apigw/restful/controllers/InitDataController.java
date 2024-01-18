package vn.supperapp.apigw.restful.controllers;

import vn.supperapp.apigw.configs.RsInitFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.UserDAO;
import vn.supperapp.apigw.db.dto.AppDevice;
import vn.supperapp.apigw.restful.models.*;
import vn.supperapp.apigw.restful.models.examples.ToolsResponse;
import vn.supperapp.apigw.utils.ErrorCode;
import vn.supperapp.apigw.redis.RedisService;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.CryptUtils;
import vn.supperapp.apigw.utils.enums.AppDeviceStatus;
import vn.supperapp.apigw.utils.enums.ClientType;
import org.hibernate.Session;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@Path("/")
@RsInitFilterMapping
@RsResponseFilterMapping
public class InitDataController extends BaseController {
    public InitDataController() {
        this.logger = LoggerFactory.getLogger(InitDataController.class);
    }

    @POST
    @Path("/init-data")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse firstInitData(@NotNull InitDataRequest request) {
        logger.info("#firstInitData - Start: " + request.toLogString());
        Session session = null;
        InitDataResponse response = new InitDataResponse(ErrorCode.SUCCESS, language);
        UserDAO dao = null;
        try {
            logger.info("#firstInitData validate data");
            //region - Validate
            if (CommonUtils.isNullOrEmptyAny(request.getDeviceModel(), request.getAppVersion(), request.getOsVersion(), request.getOsName())) {
                logger.info("#firstInitData - Validate: Missing parameters");
                return BaseResponse.missingParameters(this.language);
            }
            //endregion

            logger.info("#firstInitData - Generate RSA key pairs - First key");
            String[] rsaKey1 = CryptUtils.generateRSAKeys();
            if (rsaKey1 == null || rsaKey1.length != 2) {
                logger.info("#firstInitData - Generate RSA Key1 fail");
                return BaseResponse.commonError(language);
            }

            logger.info("#firstInitData - First public key will be returned to client");
            response.setRsaPublicKey(rsaKey1[0]);

            logger.info("#firstInitData - Generate RSA key pairs - Second key");
            String[] rsaKey2 = CryptUtils.generateRSAKeys();
            if (rsaKey2 == null || rsaKey2.length != 2) {
                logger.info("#firstInitData - Generate RSA Key2 fail");
                return BaseResponse.commonError(language);
            }

            logger.info("#firstInitData - Second private key will be returned to client");
            response.setRsaPrivateKey(rsaKey2[1]);

            logger.info("#firstInitData - Generate signature key");
            String signatureKey = CommonUtils.generateRandomString(16);
            response.setSignatureKey(signatureKey);

            logger.info("#firstInitData Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#firstInitData Check session");
            if (!checkDbSession(session)) {
                logger.info("#firstInitData DB Connection error");
                return BaseResponse.commonError(language);
            }

            dao = new UserDAO();
            AppDevice appDevice = dao.getUserAppDeviceById(session, this.getDeviceId());
            //Init device object to save to db
            if (appDevice == null)
                appDevice = new AppDevice();
            appDevice.setDeviceId(this.getDeviceId());
            appDevice.setDeviceModel(request.getDeviceModel().trim());
            appDevice.setOsName(request.getOsName().trim());
            appDevice.setOsVersion(request.getOsVersion());
            appDevice.setAppVersion(request.getAppVersion());
            appDevice.setClientType(ClientType.END_USER_APP.value().intValue());
            appDevice.setFirebaseToken(request.getFirebaseToken());
            appDevice.setStatus(Math.toIntExact(AppDeviceStatus.INIT.value()));
            appDevice.setCreateTime(new Date());
            //Fist private key save to db
            appDevice.setAppPrivateKey(rsaKey1[1]);
            //First public key save to db (save for client)
            appDevice.setClientPublicKey(rsaKey1[0]);
            //Second public key save to db
            appDevice.setAppPublicKey(rsaKey2[0]);
            //Second private key save to db (save for client)
            appDevice.setClientPrivateKey(rsaKey2[1]);
            appDevice.setSignatureKey(signatureKey);

            logger.info("#firstInitData - Save AppDevice to db");
            dao.save(session, appDevice);

            logger.info("#firstInitData - Save AppDevice to cached");
            RedisService.shared().set(0, deviceId, appDevice);

            logger.info("#firstInitData - Response");
            return response;
        } catch (Exception ex) {
            logExceptions("#firstInitData - Error: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }


    @POST
    @Path("/init-token")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse initData(@NotNull InitRequest request) {
        logger.info("#encFirstToken - Start: " + request.toLogString());
        Session session = null;
        InitResponse response = new InitResponse(ErrorCode.SUCCESS, language);
        UserDAO dao = null;
        try {
            logger.info("#firstInitData validate data");
            //region - Validate
            if (CommonUtils.isNullOrEmptyAny(request.getDeviceModel(), request.getAppVersion(), request.getOsVersion(), request.getOsName())) {
                logger.info("#firstInitData - Validate: Missing parameters");
                return BaseResponse.missingParameters(this.language);
            }
            //endregion

            logger.info("#firstInitData - Generate RSA key pairs - First key");
            String[] rsaPublicKey = CryptUtils.generateRSAKeys();
            if (rsaPublicKey == null || rsaPublicKey.length != 2) {
                logger.info("#firstInitData - Generate RSA Key1 fail");
                return BaseResponse.commonError(language);
            }

            logger.info("#firstInitData - Generate RSA key pairs - Second key");
            String[] rsaPrivateKey = CryptUtils.generateRSAKeys();
            if (rsaPrivateKey == null || rsaPrivateKey.length != 2) {
                logger.info("#firstInitData - Generate RSA Key2 fail");
                return BaseResponse.commonError(language);
            }

            response.setRsaPublicKey(rsaPublicKey[0]);
            response.setRsaPrivateKey(rsaPrivateKey[1]);

            logger.info("#firstInitData - Generate signature key");
            String signatureKey = CommonUtils.generateRandomString(16);

            logger.info("#firstInitData Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#firstInitData Check session");
            if (!checkDbSession(session)) {
                logger.info("#firstInitData DB Connection error");
                return BaseResponse.commonError(language);
            }

            dao = new UserDAO();
            AppDevice appDevice = dao.getUserAppDeviceById(session, this.getDeviceId());
            //Init device object to save to db
            if (appDevice == null)
                appDevice = new AppDevice();
            appDevice.setDeviceId(this.getDeviceId());
            appDevice.setDeviceModel(request.getDeviceModel().trim());
            appDevice.setOsName(request.getOsName().trim());
            appDevice.setOsVersion(request.getOsVersion());
            appDevice.setAppVersion(request.getAppVersion());
            appDevice.setClientType(ClientType.END_USER_APP.value().intValue());
            appDevice.setFirebaseToken(request.getFirebaseToken());
            appDevice.setStatus(Math.toIntExact(AppDeviceStatus.INIT.value()));
            appDevice.setCreateTime(new Date());
            //Fist private key save to db
            appDevice.setAppPrivateKey(rsaPublicKey[1]);
            //First public key save to db (save for client)
            appDevice.setClientPublicKey(rsaPublicKey[0]);
            //Second public key save to db
            appDevice.setAppPublicKey(rsaPrivateKey[0]);
            //Second private key save to db (save for client)
            appDevice.setClientPrivateKey(rsaPrivateKey[1]);
            appDevice.setSignatureKey(signatureKey);

            logger.info("#firstInitData - Save AppDevice to db");
            dao.save(session, appDevice);

            logger.info("#firstInitData - Save AppDevice to cached");
            RedisService.shared().set(0, deviceId, appDevice);

            logger.info("#encAccessToken db session");
            if (CommonUtils.isNullOrEmpty(request.getDeviceId()) || CommonUtils.isNullOrEmpty(request.getBuildKey())) {
                return new BaseResponse();
            }

            String token = String.format("%s##%s", request.getDeviceId().trim(), request.getBuildKey().trim());

            response.setAccessToken(CryptUtils.encryptRSA(token, rsaPublicKey[0]));

            String signatureData = request.getAccountNumber() + request.getPass();
            logger.info("#generateSignature db session");
            if (CommonUtils.isNullOrEmpty(signatureKey) || CommonUtils.isNullOrEmpty(signatureData)) {
                return new BaseResponse();
            }

            logger.info("#login Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#login Check session");
            if (!checkDbSession(session)) {
                logger.info("#login DB Connection error");
                return BaseResponse.commonError(language);
            }

            response.setSignature(CryptUtils.hmac256Encrypt(signatureData, signatureKey));

            logger.info("#firstInitData - Response");

            return response;
        } catch (Exception ex) {
            logExceptions("#firstInitData - Error: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

}
