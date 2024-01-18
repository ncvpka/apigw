/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.process.tasks;

import org.slf4j.LoggerFactory;

/**
 * @author TruongLe
 */
public class TestExecuteTask extends BaseTask {
    public static final String EXECUTOR_CONFIG_NAME = "testExecutor";

    private String data1;
    private String data2;
    //Tạo object data và nhận dữ liệu thông quan constructor

    public TestExecuteTask(String data1, String data2) {
        this.logger = LoggerFactory.getLogger(TestExecuteTask.class);
        this.data1 = data1;
        this.data2 = data2;
    }

    @Override
    public void run() {
        logger.info("#run - Start TestExecuteTask ");

        String LOG_TAG = "#run";
        try {
            logger.info("#{} - data1 = {} - data2 = {}", LOG_TAG, data1, data2);
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
