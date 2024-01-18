/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.restful.filters;

import com.google.gson.Gson;
import vn.supperapp.apigw.beans.ApiLogData;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.dto.AppDevice;
import vn.supperapp.apigw.process.tasks.ApiLogTask;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.Constants;
import vn.supperapp.apigw.process.ProcessManager;
import vn.supperapp.apigw.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;

/**
 *
 * @author TruongLev
 */
@RsResponseFilterMapping
public class RsResponseFilter implements ContainerResponseFilter {

    static Logger logger = LoggerFactory.getLogger(RsResponseFilter.class);
    
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        requestContext.getHeaders().putSingle("Content-Type", "application/json; charset=utf8");
        logger.info("############################## [ RsResponseFilter - Log response ########################");
        logger.info("Api: {}", requestContext.getUriInfo().getRequestUri().getPath());
        logger.info("HTTP Status: {}", responseContext.getStatus());


//        responseContext.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
//        responseContext.getHeaders().putSingle("Access-Control-Allow-Credentials", "true");
//        responseContext.getHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
//        responseContext.getHeaders().putSingle("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
        ApiLogData logData = new ApiLogData();
        logData.setHttpMethod(requestContext.getMethod());
        logData.setHttpStatus(responseContext.getStatus());

        Gson gson = new Gson();
        UriInfo uri = requestContext.getUriInfo();
        String path = uri.getPath();
        if (!path.startsWith("/")) {
            path = String.format("/%s", path);
        }
        logData.setPath(path);

        String req = (String) requestContext.getProperty(Constants.LOG_ITEM_REQ);
        logData.setRequestContent(req);

        String res = "";
        BaseResponse baseRes = null;
        try {
            if (MediaType.APPLICATION_JSON_TYPE.equals(responseContext.getMediaType())
                    && responseContext.getEntity() != null && responseContext.getStatus() == 200) {
                res = gson.toJson(responseContext.getEntity());
                logger.info(res);
                baseRes = CommonUtils.parseJsonToObject(BaseResponse.class, res);
                logData.setResponseObj(baseRes);
            } else {
                logger.info("Content type is not Application/Json - Cannot log");
                res = "HTML RESPONSE";
            }
            logData.setResponseContent(res);

            AppDevice appDevice = (AppDevice) requestContext.getProperty(Constants.USER_DEVICE_INFO_KEY);
            logData.setAppDevice(appDevice);
            UserLoggedInfo loggedInfo = (UserLoggedInfo) requestContext.getProperty(Constants.USER_LOGGED_INFO_KEY);
            logData.setLoggedInfo(loggedInfo);

            logger.info("# - Process Async to save log to db");
            ApiLogTask logTask = new ApiLogTask(logData);
            ProcessManager.shared().executeTask(ApiLogTask.EXECUTOR_CONFIG_NAME, logTask);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#RsResponseFilter - EXCEPTION: ", ex);
        }
        logger.info("############################## ] RsResponseFilter - Log response ########################");
    }
    
}
