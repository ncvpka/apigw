/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.process.tasks;

import vn.supperapp.apigw.beans.ApiLogData;
import vn.supperapp.apigw.db.dao.TransactionDAO;
import org.hibernate.Session;
import org.slf4j.LoggerFactory;

/**
 * @author TruongLe
 */
public class ApiLogTask extends BaseTask {
    public static final String EXECUTOR_CONFIG_NAME = "apiLogExecutor";

    private ApiLogData logData;
    //Tạo object data và nhận dữ liệu thông quan constructor

    public ApiLogTask(ApiLogData logData) {
        this.logger = LoggerFactory.getLogger(ApiLogTask.class);
        this.logData = logData;
    }

    @Override
    public void run() {
        logger.info("#run - Start SaveTxTemplateTask ");

        String LOG_TAG = "#run";
        Session session = null;
        TransactionDAO dao = null;
        try {

        } catch (Exception ex) {
            logger.error(LOG_TAG + "- ERROR: ", ex);
        } finally {
//            try {
//                DbSessionMgt.shared().closeObject(session);
//            } catch (Exception ex) {
//                logger.error("#run close connection error: ", ex);
//            }
        }
    }

}
