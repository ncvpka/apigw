package vn.supperapp.apigw.restful.controllers;

import org.hibernate.Session;
import org.slf4j.LoggerFactory;
import vn.supperapp.apigw.beans.DayOffInfo;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.AccountDAO;
import vn.supperapp.apigw.db.dao.DayOffDAO;
import vn.supperapp.apigw.db.dao.ShiftDAO;
import vn.supperapp.apigw.db.dao.UserBankDAO;
import vn.supperapp.apigw.db.dto.DayOff;
import vn.supperapp.apigw.db.dto.DayOffType;
import vn.supperapp.apigw.db.dto.Shift;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.ShiftRequest;
import vn.supperapp.apigw.restful.models.ShiftResponse;
import vn.supperapp.apigw.restful.models.dayoff.DayOffRequest;
import vn.supperapp.apigw.restful.models.dayoff.DayOffResponse;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.ErrorCode;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/shift")
@RsDefaultFilterMapping
@RsAuthFilterMapping
@RsResponseFilterMapping
public class ShiftController extends BaseController {
    private ShiftDAO shiftDAO;

    private UserBankDAO userBankDAO;
    public ShiftController() {
        this.logger = LoggerFactory.getLogger(ShiftController.class);
        shiftDAO = new ShiftDAO();
    }
    @POST
    @Path("/get-all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getAllShift(@NotNull ShiftRequest request) {
        logger.info("#checkOut - Start: " + request.toLogString());
        Session session = null;
        ShiftResponse response = new ShiftResponse(ErrorCode.SUCCESS, language);
        UserLoggedInfo loggedInfo;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }
            session = DbSessionMgt.shared().getSession();
            logger.info("#info Check session");
            if (!checkDbSession(session)) {
                logger.info("#info DB Connection error");
                return BaseResponse.commonError(language);
            }
            List<Shift> list = shiftDAO.listShift(session, loggedInfo.getAppUser().getOrgId(), language);
            if(list != null && list.size() > 0)
            {
                response.setList(list);
            }
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
