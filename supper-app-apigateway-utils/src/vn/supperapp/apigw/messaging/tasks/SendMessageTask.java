/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.messaging.tasks;

import vn.supperapp.apigw.messaging.MessagingClient;
import vn.supperapp.apigw.messaging.beans.Message;
import vn.supperapp.apigw.process.tasks.BaseTask;
import org.slf4j.LoggerFactory;

/**
 * @author truonglq
 */
public class SendMessageTask extends BaseTask {
    public static final String EXECUTOR_CONFIG_NAME = "sendMessageExecutor";

    private Message message;
    //Tạo object data và nhận dữ liệu thông quan constructor

    public SendMessageTask(Message message) {
        this.logger = LoggerFactory.getLogger(SendMessageTask.class);
        this.message = message;
    }

    @Override
    public void run() {
        logger.info("#run");
        try {
            if (this.message == null || !this.message.validateRequiredFields()) {
                logger.error("Message OBJECT is NOT VALID");
                return;
            }

            //TODO: Execute send SMS to gateway
            MessagingClient.shared().send(message);
        } catch (Exception ex) {
            logger.error("#run - ERROR: ", ex);
        }
    }


}
