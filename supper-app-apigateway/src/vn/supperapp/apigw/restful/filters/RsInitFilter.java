package vn.supperapp.apigw.restful.filters;

import vn.supperapp.apigw.configs.AppConfigurations;
import vn.supperapp.apigw.configs.RsInitFilterMapping;
import vn.supperapp.apigw.utils.Constants;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

@Priority(100)
@RsInitFilterMapping
public class RsInitFilter implements ContainerRequestFilter {

    private static Logger logger = LoggerFactory.getLogger(RsInitFilter.class);

    @Context
    HttpServletRequest httpServletRequest;

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        logger.info("############################# RsInitFilter - ContainerRequestFilter ########################");
        try {
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

            String deviceId = CommonUtils.trim(crc.getHeaderString("x-device-id"));
            String authorizationHeader = CommonUtils.trim(crc.getHeaderString(HttpHeaders.AUTHORIZATION));

            if (CommonUtils.isNullOrEmpty(deviceId) || CommonUtils.isNullOrEmpty(authorizationHeader)) {
                logger.info("Device ID or Authorization Header is null");
                crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build());
                return;
            }

            String authStr = CryptUtils.decryptRSA(authorizationHeader, AppConfigurations.shared().getAppPrivateKey());
            if (CommonUtils.isNullOrEmpty(authStr)) {
                logger.info("Authorization decrypted fail");
                crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build());
                return;
            }
            String[] tmps = authStr.split("##");
            if (tmps == null || tmps.length != 2) {
                logger.info("Auth decrypted invalid");
                crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build());
                return;
            }

            //Check device ID
            if (!deviceId.equals(tmps[0])) {
                logger.info("Device ID decrypted invalid:" + deviceId + ":" +  tmps[0]);
                crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build());
                return;
            }
            //Check client key (build key or bundle id)
            if (!AppConfigurations.shared().checkClientRestriction(tmps[1])) {
                logger.info("Build key invalid:" + tmps[1]);
                crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build());
                return;
            }

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
