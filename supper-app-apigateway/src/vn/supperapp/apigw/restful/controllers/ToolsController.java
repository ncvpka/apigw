package vn.supperapp.apigw.restful.controllers;

import vn.supperapp.apigw.configs.AppConfigurations;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.NewsDAO;
import vn.supperapp.apigw.db.dao.UserDAO;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.examples.ToolsRequest;
import vn.supperapp.apigw.restful.models.examples.ToolsResponse;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.CryptUtils;
import org.hibernate.Session;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/tools")
@RsResponseFilterMapping
public class ToolsController extends BaseController {
    public ToolsController() {
        this.logger = LoggerFactory.getLogger(ToolsController.class);
    }

    @POST
    @Path("/enc-first-token")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object encFirstToken(@NotNull ToolsRequest request) {
        logger.info("#encFirstToken - Start: " + request.toLogString());
        Session session = null;
        try {
            logger.info("#encFirstToken db session");
            if (CommonUtils.isNullOrEmpty(request.getDeviceId()) || CommonUtils.isNullOrEmpty(request.getBuildKey())) {
                return new BaseResponse();
            }

            String token = String.format("%s##%s", request.getDeviceId().trim(), request.getBuildKey().trim());

            ToolsResponse response = new ToolsResponse();
            response.setAccessToken(CryptUtils.encryptRSA(token, AppConfigurations.shared().getAppPublicKey()));
            return response;
        } catch (Exception ex) {
            logExceptions("#encFirstToken - Error: ", ex);
        }
        return new BaseResponse();
    }

    @POST
    @Path("/enc-access-token")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object encAccessToken(@NotNull ToolsRequest request) {
        logger.info("#encAccessToken - Start: " + request.toLogString());
        Session session = null;
        UserDAO userDao = null;
        try {
            logger.info("#encAccessToken db session");
            if (CommonUtils.isNullOrEmpty(request.getDeviceId()) || CommonUtils.isNullOrEmpty(request.getBuildKey())) {
                return new BaseResponse();
            }

            String token = String.format("%s##%s", request.getDeviceId().trim(), request.getBuildKey().trim());

            ToolsResponse response = new ToolsResponse();
            response.setAccessToken(CryptUtils.encryptRSA(token, request.getRsaPublicKey()));
            return response;
        } catch (Exception ex) {
            logExceptions("#encAccessToken - Error: ", ex);
        }
        return new BaseResponse();
    }

    @POST
    @Path("/generate-signature")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
//    @RsDefaultFilterMapping
    public Object generateSignature(@NotNull ToolsRequest request) {
        logger.info("#generateSignature - Start: " + request.toLogString());
        Session session = null;
        try {
            logger.info("#generateSignature db session");
            if (CommonUtils.isNullOrEmpty(request.getSignatureKey()) || CommonUtils.isNullOrEmpty(request.getSignatureData())) {
                return new BaseResponse();
            }

            logger.info("#login Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#login Check session");
            if (!checkDbSession(session)) {
                logger.info("#login DB Connection error");
                return BaseResponse.commonError(language);
            }

            ToolsResponse response = new ToolsResponse();
            response.setSignature(CryptUtils.hmac256Encrypt(request.getSignatureData(), request.getSignatureKey()));
            return response;
        } catch (Exception ex) {
            logExceptions("#generateSignature - Error: ", ex);
        }finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

    @POST
    @Path("/encrypt")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object encrypt(@NotNull ToolsRequest request) {
        logger.info("#encAccessToken - Start: " + request.toLogString());
        Session session = null;
        UserDAO userDao = null;
        try {
            logger.info("#encAccessToken db session");
            if (CommonUtils.isNullOrEmpty(request.getDeviceId()) || CommonUtils.isNullOrEmpty(request.getBuildKey())) {
                return new BaseResponse();
            }

            ToolsResponse response = new ToolsResponse();
            response.setCryptData(CryptUtils.encryptRSA(request.getCryptData(), request.getRsaPublicKey()));
            return response;
        } catch (Exception ex) {
            logExceptions("#encAccessToken - Error: ", ex);
        }
        return new BaseResponse();
    }


    @POST
    @Path("/decrypt")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object decrypt(@NotNull ToolsRequest request) {
        logger.info("#encAccessToken - Start: " + request.toLogString());
        Session session = null;
        UserDAO userDao = null;
        try {
            logger.info("#encAccessToken db session");
            if (CommonUtils.isNullOrEmpty(request.getDeviceId()) || CommonUtils.isNullOrEmpty(request.getBuildKey())) {
                return new BaseResponse();
            }

            ToolsResponse response = new ToolsResponse();
            String a = CryptUtils.decryptRSA(request.getCryptData(), request.getRsaPrivateKey());
            response.setCryptData(a);
            return response;
        } catch (Exception ex) {
            logExceptions("#encAccessToken - Error: ", ex);
        }
        return new BaseResponse();
    }
}
