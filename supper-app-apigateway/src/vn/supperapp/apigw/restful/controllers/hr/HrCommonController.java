package vn.supperapp.apigw.restful.controllers.hr;

import org.hibernate.Session;
import org.slf4j.LoggerFactory;
import vn.supperapp.apigw.beans.DropDown;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.HrCommonDAO;
import vn.supperapp.apigw.restful.controllers.BaseController;
import vn.supperapp.apigw.restful.controllers.checkIn.CheckInController;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.hr.HrCommonRequest;
import vn.supperapp.apigw.restful.models.hr.HrCommonResponse;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.ErrorCode;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/hr/common")
@RsDefaultFilterMapping
@RsAuthFilterMapping
@RsResponseFilterMapping
public class HrCommonController extends BaseController {
    private HrCommonDAO dao;

    public HrCommonController() {
        this.logger = LoggerFactory.getLogger(HrCommonController.class);
        dao = new HrCommonDAO();
    }

    @POST
    @Path("/get-dropdown")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getDropdown(@NotNull HrCommonRequest request) {
        logger.info("#login - Start: " + request.toLogString());
        Session session = null;
        HrCommonResponse response = new HrCommonResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            logger.info("#updateInfor validate data");
            //TODO: Validate phone
            if (CommonUtils.isNullOrEmpty(request.getType())) {
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
            String type = request.getType();
            response = new HrCommonResponse(ErrorCode.SUCCESS, language);
            List<DropDown> list = dao.getDropDown(session, loggedInfo.getAppUser().getOrgId(), type);
            response.setDropdown(list);
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
}
