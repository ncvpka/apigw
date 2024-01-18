package vn.supperapp.apigw.restful.filters;

import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.configs.AppConfigurations;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.db.dto.AppDevice;
import vn.supperapp.apigw.utils.Constants;
import vn.supperapp.apigw.redis.RedisService;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.CryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.*;
import java.io.IOException;

@Priority(101)
@RsAuthFilterMapping
public class RsAuthFilter implements ContainerRequestFilter {

    private static Logger logger = LoggerFactory.getLogger(RsAuthFilter.class);

    @Context
    HttpServletRequest httpServletRequest;

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        logger.info("############################# RsAuthFilter - ContainerRequestFilter ########################");
        try {
            String deviceId = CommonUtils.trim(crc.getHeaderString("x-device-id"));
            String authorizationHeader = CommonUtils.trim(crc.getHeaderString(HttpHeaders.AUTHORIZATION));
            if (CommonUtils.isNullOrEmpty(deviceId) || CommonUtils.isNullOrEmpty(authorizationHeader)) {
                logger.info("Device ID or Authorization Header is null");
                crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build());
                return;
            }

            String[] tmps = authorizationHeader.split("##");
            if (tmps == null || tmps.length < 2) {
                logger.info("Authorization Header invalid");
                crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build());
                return;
            }

            AppDevice appDevice= (AppDevice) crc.getProperty(Constants.USER_DEVICE_INFO_KEY);
            if (appDevice == null) {
                logger.info("Authorization Header invalid");
                crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build());
                return;
            }

            String loginCachedKey = String.format("TOKEN_%s", deviceId);
            UserLoggedInfo loggedInfo = RedisService.shared().get(UserLoggedInfo.class, Constants.REDIS_DB_LOGIN_CACHED, loginCachedKey);
            if (loggedInfo == null) {
                logger.info("Cached not found");
                crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build());
                return;
            }

            String accessToken = CryptUtils.decryptRSA(tmps[1], appDevice.getAppPrivateKey());
            if (CommonUtils.isNullOrEmpty(accessToken)) {
                logger.info("Authorization Header invalid - decrypt fail");
                crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build());
                return;
            }

            if (!accessToken.equals(loggedInfo.getAccessToken())) {
                logger.info("Token invalid");
                crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build());
                return;
            }

            //TODO: Check more token expired here
            long tCheckExpired = AppConfigurations.shared().getLoginTokenExpired();
            long tDelta = System.currentTimeMillis() - loggedInfo.getUpdateTime();
            if (tDelta > tCheckExpired * 1000) {
                logger.info("Token EXPIRED");
                crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build());
                return;
            }

            logger.info("#filter - Update new time for new success request");
            loggedInfo.setUpdateTime(System.currentTimeMillis());
            RedisService.shared().set(Constants.REDIS_DB_LOGIN_CACHED, loginCachedKey, loggedInfo, AppConfigurations.shared().getLoginTokenCachedExpired());

            crc.setProperty(Constants.USER_LOGGED_INFO_KEY, loggedInfo);
        } catch (Exception ex) {
            logger.error("#filter - ERROR: ", ex);
        }
    }

    private static final String[] HEADERS_LIST = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    public static String getClientIp(HttpServletRequest request) {
        for (String header : HEADERS_LIST) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

}
