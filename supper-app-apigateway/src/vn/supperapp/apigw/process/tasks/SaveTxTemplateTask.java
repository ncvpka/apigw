/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.process.tasks;

import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.BaseDAO;
import vn.supperapp.apigw.db.dao.TransactionDAO;
import vn.supperapp.apigw.db.dto.TransactionTemplate;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.enums.AppFeatureCode;
import org.hibernate.Session;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author TruongLe
 */
public class SaveTxTemplateTask extends BaseTask {
    public static final String EXECUTOR_CONFIG_NAME = "saveTransactionTemplateExecutor";

    AppFeatureCode feature;
    private UserLoggedInfo loggedInfo;
    private BaseResponse response;
    private Object trans;
    //Tạo object data và nhận dữ liệu thông quan constructor

    public SaveTxTemplateTask(AppFeatureCode feature, UserLoggedInfo loggedInfo, BaseResponse response, Object trans) {
        this.logger = LoggerFactory.getLogger(SaveTxTemplateTask.class);
        this.feature = feature;
        this.loggedInfo = loggedInfo;
        this.response = response;
        this.trans = trans;
    }

    @Override
    public void run() {
        logger.info("#run - Start SaveTxTemplateTask ");

        String LOG_TAG = "#run";
        Session session = null;
        TransactionDAO dao = null;
        try {
            logger.info("#run - Process for TransId: " + response.getTransId());

            logger.info("#run Open db app session");
            session = DbSessionMgt.shared().getSession();
            logger.info("#run Check session");
            if (!BaseDAO.checkSession(session)) {
                logger.info("#run DB Connection error");
                return;
            }

            dao = new TransactionDAO();

            String msisdn = loggedInfo.getUserInfo().getAccountNumber();

            TransactionTemplate txTpl = new TransactionTemplate();
            txTpl.setAppUserId(loggedInfo.getAppUser().getId());
            txTpl.setAppDeviceId(loggedInfo.getAppDevice().getId());
            txTpl.setMsisdn(msisdn);
            txTpl.setStatus(1);
            txTpl.setFeatureCode(feature.code());
            txTpl.setCreateTime(new Date());
            txTpl.setTransId(response.getTransId());
            txTpl.setAccountId(loggedInfo.getUserInfo().getAccountId());

            /*if (AppFeatureCode.TRANSFER_MONEY.is(feature.code())) {
                TransferResponse txRes = (TransferResponse) response;
                TransferMoneyCachedInfo txCached = (TransferMoneyCachedInfo) trans;
                txTpl.setResponseType(TransferResponse.class.getName());
                txTpl.setResponseContent(txRes.toLogString());

                txTpl.setTplAccountCode(txCached.getReceiver().getAccountNumber());
                txTpl.setTplName(txCached.getReceiver().getAccountName());
                txTpl.setTplContent(txCached.getContent());
                txTpl.setTplAmount(String.format("%.2f", txCached.getAmount()));

            } else {

            }*/

            txTpl.buildTextSearch();

            logger.info("#run - Save template to db");
            dao.save(session, txTpl);

        } catch (Exception ex) {
            logger.error(LOG_TAG + "- ERROR: ", ex);
        } finally {
            try {
                DbSessionMgt.shared().closeObject(session);
            } catch (Exception ex) {
                logger.error("#run close connection error: ", ex);
            }
        }
    }

}
