package vn.supperapp.apigw.restful.filters;

import vn.supperapp.apigw.configs.AppConfigurations;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.UserDAO;
import vn.supperapp.apigw.db.dto.AppDevice;
import vn.supperapp.apigw.utils.Constants;
import vn.supperapp.apigw.redis.RedisService;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.CryptUtils;
import org.glassfish.jersey.message.internal.ReaderWriter;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Priority(100)
@RsDefaultFilterMapping
public class RsDefaultFilter implements ContainerRequestFilter {

    private static Logger logger = LoggerFactory.getLogger(RsDefaultFilter.class);

    @Context
    HttpServletRequest httpServletRequest;

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        logger.info("############################# RsDefaultFilter - ContainerRequestFilter ########################");
        try {
//            crc.getHeaders().putSingle("Accept", "application/json");
//            crc.getHeaders().putSingle("ContentType", "application/json");
//            crc.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
//            crc.getHeaders().putSingle("Access-Control-Allow-Credentials", "true");
//            crc.getHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
//            crc.getHeaders().putSingle("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
            crc.setProperty(Constants.LOG_ITEM_START_TIME, new Date());
            //Log header
            MultivaluedMap<String, String> headers = crc.getHeaders();
            if (headers != null) {
                StringBuilder strLog = new StringBuilder();
                strLog.append("\n###################[ Header ####################");
                for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                    String key = entry.getKey();
                    List<String> value = entry.getValue();
                    strLog.append(String.format("\n# %s: %s", key, value));
                }
                strLog.append("\n###################] Header ####################");
                logger.info(strLog.toString());
            }

            //Get PATH (api name)
            UriInfo uri = crc.getUriInfo();
            String path = uri.getPath();
            if (!path.startsWith("/")) {
                path = String.format("/%s", path);
            }
            logger.info("Path: " + path);

            logger.info("# - Get request entity");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = crc.getEntityStream();
            if (in.available() > 0) {
                ReaderWriter.writeTo(in, out);
                byte[] requestEntity = out.toByteArray();
                if (requestEntity.length != 0) {
                    crc.setProperty(Constants.LOG_ITEM_REQ, new String(requestEntity));
                }
                crc.setEntityStream(new ByteArrayInputStream(requestEntity));
            }

            String deviceId = CommonUtils.trim(crc.getHeaderString("x-device-id"));
            String firebaseToken = CommonUtils.trim(crc.getHeaderString("x-firebase-token"));
            String authorizationHeader = CommonUtils.trim(crc.getHeaderString(HttpHeaders.AUTHORIZATION));
            if (CommonUtils.isNullOrEmpty(deviceId) || CommonUtils.isNullOrEmpty(authorizationHeader)) {
                logger.info("Device ID or Authorization Header is null");
                crc.abortWith(Response.status(Response.Status.GONE).entity("Unauthorized").build());
                return;
            }

            String[] tmps = authorizationHeader.split("##");
            if (tmps == null || tmps.length <= 0) {
                logger.info("Authorization Header invalid");
                crc.abortWith(Response.status(Response.Status.GONE).entity("Unauthorized").build());
                return;
            }

            AppDevice appDevice = RedisService.shared().get(AppDevice.class, Constants.REDIS_DB_DEVICE_CACHED, deviceId);
            UserDAO userDao = new UserDAO();
            Session session = DbSessionMgt.shared().getSession();
            if (appDevice == null) {
                logger.info("##################### CHECK DB SESSION");
                if (session != null) {
                    appDevice = userDao.getUserAppDeviceById(session, deviceId);
                    DbSessionMgt.shared().closeObject(session);
                }
            }

            if (appDevice == null) {
                logger.info("Device not found");
                crc.abortWith(Response.status(Response.Status.GONE).entity("Unauthorized").build());
                return;
            }

            session = DbSessionMgt.shared().getSession();
            if (firebaseToken != null && !firebaseToken.isEmpty() && !firebaseToken.equals(appDevice.getFirebaseToken())){
                appDevice.setFirebaseToken(firebaseToken);
                userDao.update(session, appDevice);
                logger.info("update firebase token for device " + appDevice.getDeviceId());
            }
            RedisService.shared().set(Constants.REDIS_DB_DEVICE_CACHED, deviceId, appDevice);
            if (session != null) {
                DbSessionMgt.shared().closeObject(session);
            }

            String accessToken = CryptUtils.decryptRSA(tmps[0], appDevice.getAppPrivateKey());
            if (CommonUtils.isNullOrEmptyAny(accessToken)) {
                logger.info("Authorization invalid");
                crc.abortWith(Response.status(Response.Status.GONE).entity("Unauthorized").build());
                return;
            }

            tmps = accessToken.split("##");
            if (tmps == null || tmps.length != 2) {
                logger.info("Auth decrypted invalid");
                crc.abortWith(Response.status(Response.Status.GONE).entity("Unauthorized").build());
                return;
            }

            //Check device ID
            if (!deviceId.equals(tmps[0])) {
                logger.info("Device ID decrypted invalid");
                crc.abortWith(Response.status(Response.Status.GONE).entity("Unauthorized").build());
                return;
            }
            //Check client key (build key or bundle id)
            if (!AppConfigurations.shared().checkClientRestriction(tmps[1])) {
                logger.info("Device ID decrypted invalid");
                crc.abortWith(Response.status(Response.Status.GONE).entity("Unauthorized").build());
                return;
            }

            crc.setProperty(Constants.USER_DEVICE_INFO_KEY, appDevice);
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
