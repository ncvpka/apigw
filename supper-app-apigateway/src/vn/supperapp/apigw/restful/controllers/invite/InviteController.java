package vn.supperapp.apigw.restful.controllers.invite;


import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.clients.firebase.FirebaseHttpClient;
import vn.supperapp.apigw.clients.firebase.objs.FirebaseDynamicLinkInfo;
import vn.supperapp.apigw.clients.firebase.objs.FirebaseDynamicLinkSuffixType;
import vn.supperapp.apigw.clients.firebase.objs.FirebaseHttpConfigInfo;
import vn.supperapp.apigw.clients.firebase.objs.FirebaseShortLinkResponse;
import vn.supperapp.apigw.configs.RsAuthFilterMapping;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.InviteDAO;
import vn.supperapp.apigw.db.dao.InviteReferralLinkDAO;
import vn.supperapp.apigw.db.dto.InviteLog;
import vn.supperapp.apigw.db.dto.InviteReferralLink;
import vn.supperapp.apigw.restful.controllers.BaseController;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.invite.InviteRequest;
import vn.supperapp.apigw.utils.ErrorCode;
import vn.supperapp.apigw.utils.ReferralLinkType;
import vn.supperapp.apigw.wso2.objs.Invite;
import vn.supperapp.apigw.wso2.service.InviteService;
import vn.supperapp.apigw.messaging.MessagingClient;
import vn.supperapp.apigw.messaging.beans.Message;
import vn.supperapp.apigw.messaging.beans.MessageContent;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.LanguageUtils;
import vn.supperapp.apigw.utils.enums.ChannelType;
import vn.supperapp.apigw.utils.enums.Language;
import org.hibernate.Session;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author TruongLe
 * @Date 18/04/2022
 * @see InviteController
 */

@Path("/load-contacts")
@RsDefaultFilterMapping
@RsAuthFilterMapping
@RsResponseFilterMapping
public class InviteController extends BaseController {
    private InviteService inviteService;
    private InviteDAO inviteDAO;
    private InviteReferralLinkDAO inviteReferralLinkDAO;

    public InviteController() {
        this.logger = LoggerFactory.getLogger(InviteController.class);
        inviteService = new InviteService();inviteReferralLinkDAO = new InviteReferralLinkDAO();
    }

    @POST
    @Path("/info")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse inviteInfo(@NotNull InviteRequest request) {
        logger.info("#inviteInfo - Start: " + request.toLogString());
        UserLoggedInfo loggedInfo = null;
        Session session = null;
        vn.supperapp.apigw.restful.models.invite.InviteResponse response = new vn.supperapp.apigw.restful.models.invite.InviteResponse(ErrorCode.SUCCESS, language);
        int size = 300;
        String accountEWalletArr = "";
        try {

            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }

            String msisdnArr = request.getMsisdnArr();
            if (CommonUtils.isNullOrEmpty(msisdnArr)) {
                logger.info("#inviteInfo - Validate: msisdnArr is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }
            msisdnArr = msisdnArr.replace("+", "").replaceAll("\\s", "");
            List<String> listMsisdnArr = Arrays.asList(msisdnArr.split(","));
            List<List<String>> partitions = new ArrayList<List<String>>();
            List<String> listAllAccountEWallet = new ArrayList<>();

            for (int i = 0; i < listMsisdnArr.size(); i += size) {
                partitions.add(listMsisdnArr.subList(i, Math.min(i + size, listMsisdnArr.size())));
            }

            List<String> list = new ArrayList<>();
            List<String> listNotAccountEWallet = new ArrayList<>();
            for (List<String> listSmall : partitions) {
                vn.supperapp.apigw.wso2.objs.InviteResponse inviteResponse = inviteService.getListAccountIsEWallet(String.join(",", listSmall));
                if (inviteResponse.getInviteCollection().getInvites() == null) {
                    logger.info("#inviteInfo - getInviteCollection inviteResponse = null (list account not eWallet) ");
                    listNotAccountEWallet.add(String.join(",", listSmall));

                } else if (inviteResponse.getInviteCollection().getInvites() != null) {
                    logger.info("#inviteInfo - getInviteCollection: " + inviteResponse.getInviteCollection().getInvites());
                    List<Invite> inviteList = inviteResponse.getInviteCollection().getInvites();
                    for (int i = 0; i < inviteList.size(); i++) {
                        list.add(inviteList.get(i).getPhoneNumber());
                    }
                    listAllAccountEWallet.add(String.join(",", list));
                }
            }

            if (listAllAccountEWallet.size() > 0) {
                accountEWalletArr = String.join(",", listAllAccountEWallet);
                response.setListPhoneNumberWithWallet(accountEWalletArr);
            } else {
                response.setListPhoneNumberWithWallet("");
            }

            logger.info("#inviteInfo Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#inviteInfo Check session");
            if (!checkDbSession(session)) {
                logger.info("#inviteInfo DB Connection error");
                return BaseResponse.commonError(language);
            }

            if (request.getType().equals("invite")) {
                logger.info("#inviteInfo - getListInvite ");
                List<String> listInviteNumber = inviteDAO.getListInviteNumber(session, loggedInfo, msisdnArr);
                String inviteNumberArr = String.join(",", listInviteNumber);
                response.setListInviteNumber(inviteNumberArr);

                logger.info("#inviteInfo - inviteInfo listNotAccountEWallet ");
                String accountEWalletOrInvite = "";
                if (!CommonUtils.isNullOrEmpty(inviteNumberArr) && !CommonUtils.isNullOrEmpty(accountEWalletArr)) {
                    accountEWalletOrInvite = accountEWalletArr + "," + inviteNumberArr;
                } else if (!CommonUtils.isNullOrEmpty(inviteNumberArr)) {
                    accountEWalletOrInvite = inviteNumberArr;
                } else {
                    accountEWalletOrInvite = accountEWalletArr;
                }

                if (listAllAccountEWallet.size() > 0) {
                    List<String> listStr = getListNotAccountEWallet(msisdnArr, accountEWalletOrInvite);
                    response.setListNotAccountEWallet(String.join(",", listStr));
                } else if (listNotAccountEWallet.size() > 0) {
                    List<String> listStr = getListNotAccountEWallet(msisdnArr, accountEWalletOrInvite);
                    response.setListNotAccountEWallet(String.join(",", listStr));
                }
                return response;

            } else if (request.getType().equals("notInvite")) {
                logger.info("#inviteInfo - notInvite listNotAccountEWallet ");
                listNotAccountEWallet = getListNotAccountEWallet(msisdnArr, accountEWalletArr);
                response.setListNotAccountEWallet(String.join(",", listNotAccountEWallet));
                return response;
            }

        } catch (Exception ex) {
            logger.error("#inviteInfo - Error: ", ex);
            ex.printStackTrace();
        }
        return BaseResponse.commonError(language);
    }

    @POST
    @Path("/invite")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse invite(@NotNull InviteRequest request) {
        logger.info("#invite - Start: " + request.toLogString());
        UserLoggedInfo loggedInfo = null;
        Session session = null;
        vn.supperapp.apigw.restful.models.invite.InviteResponse response = new vn.supperapp.apigw.restful.models.invite.InviteResponse(ErrorCode.SUCCESS, language);

        try {

            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }

            String msisdnArr = request.getMsisdnArr();
            if (CommonUtils.isNullOrEmpty(msisdnArr)) {
                logger.info("#invite - Validate: msisdnArr is null");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }

            List<String> listMsisdn = Arrays.asList(msisdnArr.split(","));
            if (listMsisdn.size() > 10) {
                logger.info("#invite - Validate: listMsisdn is more than 10");
                return BaseResponse.build(ErrorCode.ERR_INVITED_PHONE_GREATER, language);
            }

            for (int i = 0; i < listMsisdn.size(); i++) {

                String phoneNumber = listMsisdn.get(i);
                phoneNumber = phoneNumber.replace("+", "").replaceAll("\\s", "");
                if (!CommonUtils.isPhoneNumber(phoneNumber)) {
                    logger.info("#inviteInfo - Validate: accountNumber invalid");
                    String error = LanguageUtils.getString("ERR_ACCOUNT_NUMBER_INVALID", language);
                    error = error.replace("[ACCOUNT_NUMBER]", phoneNumber);
                    return new BaseResponse(5, "ERR_ACCOUNT_NUMBER_INVALID", error);
                }

                vn.supperapp.apigw.wso2.objs.InviteResponse inviteResponse = inviteService.getListAccountIsEWallet(phoneNumber);
                if (inviteResponse.getInviteCollection().getInvites() != null) {
                    logger.info("#inviteInfo - getInviteCollection  wallet number: " + phoneNumber);
                    return new BaseResponse(ErrorCode.ERR_INVITED_WALLET_NUMBER, language);
                }
            }


            logger.info("#inviteInfo Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#inviteInfo Check session");
            if (!checkDbSession(session)) {
                logger.info("#inviteInfo DB Connection error");
                return BaseResponse.commonError(language);
            }

            List<InviteLog> inviteLogList = new ArrayList<>();
            for (int i = 0; i < listMsisdn.size(); i++) {
                InviteLog inviteLog = new InviteLog();
                inviteLog.setReferenceNumber(loggedInfo.getUserInfo().getAccountNumber());
                inviteLog.setInvitedNumber(listMsisdn.get(i));
                inviteLog.setCreateDate(new Date());
                inviteLog.setStatus(1);
                inviteLogList.add(inviteLog);
            }

            logger.info("#inviteInfo Save data into inviteLogList");
            inviteDAO.saveAll(session, inviteLogList);

            logger.info("#inviteInfo check dynamic link exists -- ");
            InviteReferralLink inviteReferralLink = inviteReferralLinkDAO.getInviteReferralLinkByAccountNumber(session, loggedInfo);

            String shortLink = "";
            if (inviteReferralLink == null) {
                logger.info("#inviteInfo save dynamic link into db -- ");
                InviteReferralLink referralLink = new InviteReferralLink();
                referralLink.setMsisdn(loggedInfo.getUserInfo().getAccountNumber());
                referralLink.setReferralCode(loggedInfo.getUserInfo().getAccountNumber());
                shortLink = getShortLink(loggedInfo);
                referralLink.setReferralLink(shortLink);
                referralLink.setReferralType(ReferralLinkType.REGISTER.code());
                referralLink.setStatus(1);
                referralLink.setCreateDate(new Date());

                session.beginTransaction();
                session.save(referralLink);
                session.flush();
                DbSessionMgt.commitObject(session);

            } else {
                shortLink = inviteReferralLink.getReferralLink();
            }

            for (int i = 0; i < listMsisdn.size(); i++) {
                sendSmsInviteYourFriends(listMsisdn.get(i), shortLink, loggedInfo.getUserInfo().getAccountNumber());
            }

            logger.info("#inviteInfo setReferralLink into response");
            response.setSetReferralLink(shortLink);
            return response;

        } catch (Exception ex) {
            logger.error("#invite - Error: ", ex);
            ex.printStackTrace();
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return BaseResponse.commonError(language);
    }

    @POST
    @Path("/share-link")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse shareLink() {
        logger.info("#shareLink START -- ");
        UserLoggedInfo loggedInfo = null;
        Session session = null;
        vn.supperapp.apigw.restful.models.invite.InviteResponse response = new vn.supperapp.apigw.restful.models.invite.InviteResponse(ErrorCode.SUCCESS, language);

        try {
            loggedInfo = getUserLoggedInfo();
            if (loggedInfo == null) {
                logger.info("Common error");
                return BaseResponse.commonError(language);
            }

            logger.info("#shareLink Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#shareLink Check session");
            if (!checkDbSession(session)) {
                logger.info("#shareLink DB Connection error");
                return BaseResponse.commonError(language);
            }

            logger.info("#shareLink setReferralLink into response");
            InviteReferralLink inviteReferralLink = inviteReferralLinkDAO.getInviteReferralLinkByAccountNumber(session, loggedInfo);
            if (inviteReferralLink == null) {
                logger.info("#shareLink setReferralLink inviteReferralLink is null ");
                String shortLink = getShortLink(loggedInfo);
                InviteReferralLink referralLink = new InviteReferralLink();
                referralLink.setMsisdn(loggedInfo.getUserInfo().getAccountNumber());
                referralLink.setReferralCode(loggedInfo.getUserInfo().getAccountNumber());
                referralLink.setReferralLink(shortLink);
                referralLink.setReferralType(ReferralLinkType.REGISTER.code());
                referralLink.setStatus(1);
                referralLink.setCreateDate(new Date());

                session.beginTransaction();
                session.save(referralLink);
                session.flush();
                DbSessionMgt.commitObject(session);

                logger.info("#shareLink setReferralLink ");
                response.setSetReferralLink(shortLink);

            } else {
                logger.info("#shareLink setReferralLink inviteReferralLink is null ");
                response.setSetReferralLink(inviteReferralLink.getReferralLink());
            }
            return response;

        } catch (Exception ex) {
            logger.error("#shareLink - Error: ", ex);
            ex.printStackTrace();
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return BaseResponse.commonError(language);
    }

    private String getShortLink(UserLoggedInfo loggedInfo) {
        FirebaseHttpConfigInfo fbConfig = FirebaseHttpClient.shared().getConfig();
        String dl = generateDataLink(fbConfig.getLink(), ReferralLinkType.REGISTER, loggedInfo.getUserInfo().getAccountNumber());
        String reqData = FirebaseDynamicLinkInfo.builder()
                .setAndroidPackageName(fbConfig.getAndroidPackageName())
                .setIosBundleId(fbConfig.getIosBundleId())
                .setSuffix(FirebaseDynamicLinkSuffixType.UNGUESSABLE)
                .setDomainUriPrefix(fbConfig.getDomainUriPrefix())
                .setLink(dl).buildJson();

        FirebaseShortLinkResponse fsRes = FirebaseHttpClient.shared().getFirebaseDeepLink(reqData);
        return fsRes.getShortLink();
    }

    private String generateDataLink(String baseLink, ReferralLinkType type, String accountNumber) {
        if (ReferralLinkType.REGISTER.is(type.value())) {
            return String.format("%s?phoneNumber=%s", baseLink, accountNumber);
        }
        return baseLink;
    }

    private String sendSmsInviteYourFriends(String receiverPhone, String shortLink, String senderPhone) {

        String msgKey = "MSG_INVITE_YOUR_FRIENDS";
        String otpPrefix = LanguageUtils.getString(msgKey, language);

        MessageContent mc = MessageContent.builder()
                .addReplacement("[ACCOUNT_NUMBER]", senderPhone)
                .addReplacement("[LINK]", shortLink)
                .addTemplate(Language.ENGLISH.key(), String.format("%s", otpPrefix, LanguageUtils.getString(msgKey, Language.ENGLISH.key())))
                .addTemplate(Language.VIETNAMESE.key(), String.format("%s", otpPrefix, LanguageUtils.getString(msgKey, Language.VIETNAMESE.key())))
                .build();

        Message message = Message.builder()
                .setChannel(ChannelType.APIGW_ENDUSER.code())
                .setRefId(CommonUtils.generateUUID())
                .setReceiverObj("enduser")
                .setSender(MessagingClient.shared().getSender())
                .setReceiver(receiverPhone)
                .setMessageContent(mc)
                .setPushNotification(false)
                .setSaveNotificationLog(false)
                .build();

        logger.info(message.toJsonString());
        MessagingClient.shared().sendAsyncTask(message);
        return mc.getContents().get(language);
    }

    private List<String> getListNotAccountEWallet(String source, String tmp) {
        source = source.replaceAll(" ", "");
        tmp = tmp.replaceAll(" ", "");

        Set<String> setSourceAccount = new HashSet<>(Arrays.asList(source.split(",")));
        List<String> listTmp = Arrays.asList(tmp.split(","));
        setSourceAccount.removeAll(listTmp);

        return setSourceAccount.stream().collect(Collectors.toList());
    }
}
