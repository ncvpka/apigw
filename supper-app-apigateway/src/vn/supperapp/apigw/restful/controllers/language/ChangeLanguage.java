package vn.supperapp.apigw.restful.controllers.language;


import com.viettel.ewallet.utils.iso.msg.IsoObject;
import org.hibernate.Session;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.UserDAO;
import vn.supperapp.apigw.db.dto.AppUser;
import vn.supperapp.apigw.restful.controllers.BaseController;
import vn.supperapp.apigw.restful.models.BaseResponse;

import vn.supperapp.apigw.restful.models.language.LanguageResponse;
import vn.supperapp.apigw.utils.Constants;
import vn.supperapp.apigw.utils.ErrorCode;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.enums.TransactionType;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


/**
 * @author TruongLe
 * @see ChangeLanguage
 */
@Path("/change-language")
@RsDefaultFilterMapping
@RsResponseFilterMapping
@RsAuthFilterMapping
public class ChangeLanguage extends BaseController {
    public ChangeLanguage() {
        this.logger = LoggerFactory.getLogger(ChangeLanguage.class);
    }

    @GET
    @Path("/confirm")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public BaseResponse changeLanguage(@HeaderParam("language") String language) {
        logger.info("#changelanguge - Start: " + language);

        UserLoggedInfo loggedInfo = null;
        IsoObject isoResponse = null;
        LanguageResponse response = null;
        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }
            if (CommonUtils.isNullOrEmpty(language)) {
                logger.info("#changelanguge - Validate: language is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            if (language.equals("en") || language.equals("fr") || language.equals("ht")) {
                logger.info("#changelanguge- Validate: language" + language);
            } else {
                logger.info("#changelanguge- Validate: language is not exist");
                return BaseResponse.build(ErrorCode.ERR_LANGUAGE_NOT_EXISTS, language);
            }

            Session session = DbSessionMgt.shared().getSession();
            AppUser user = new UserDAO().getAppUserByMsisdn(session, loggedInfo.getUserInfo().getAccountNumber());
            user.setLanguage(language);
            new UserDAO().update(session, user);
            response = new LanguageResponse(ErrorCode.SUCCESS, language);
            response.setLanguage(language);
            return response;
        } catch (Exception ex) {
            logger.error("#changelanguge Exception: ", ex);
            ex.printStackTrace();
        }
        return BaseResponse.commonError(language);
    }

    private IsoObject makeIsoChangeLanguage(UserLoggedInfo loggedInfo, TransactionType transType, String language) {
        IsoObject iso = null;
        try {
            iso = initIsoObject2CoreApp();
            iso.setProcessCode(transType.processCode());
            iso.setAccountID(loggedInfo.getUserInfo().getAccountId());
            iso.setCarriedAccountID(loggedInfo.getUserInfo().getAccountId());
            iso.setPhoneNumber(loggedInfo.getUserInfo().getAccountNumber());
            iso.setCarriedPhone(loggedInfo.getUserInfo().getAccountNumber());
            iso.setRoleId(Constants.CUSTOMER);
            iso.setPAN(loggedInfo.getPan());
            iso.setCarriedName(loggedInfo.getUserInfo().getFullName());
            if (language.equals("en")) {
                iso.setLanguage(Constants.English);
            } else if (language.equals("fr")) {
                iso.setLanguage(Constants.France);
            } else if (language.equals("ht")) {
                iso.setLanguage(Constants.CREOLE);
            }
        } catch (Exception ex) {
            logger.error("#makeIsochangelanguge - EXCEPTION: ", ex);
            ex.printStackTrace();
        }
        return iso;
    }
}
