package vn.supperapp.apigw.messaging;

import com.google.gson.Gson;
import vn.supperapp.apigw.messaging.beans.Message;
import vn.supperapp.apigw.messaging.configs.MsgGwConfigInfo;
import vn.supperapp.apigw.messaging.tasks.SendMessageTask;
import vn.supperapp.apigw.process.ProcessManager;
import vn.supperapp.apigw.utils.enums.Language;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

public class MessagingClient {
    private static final Logger logger = LoggerFactory.getLogger(MessagingClient.class);
    private static final String CONFIG_PATH = "../etc/services/msggw-client.yml";
//    private static final String CONFIG_PATH = "D:\\emoney\\sources\\emoney-messaging\\emoney-messaging-client\\etc\\emoney-msggw-client.yml";

    public MessagingClient() {
    }

    private static volatile MessagingClient instance;
    private static Object muxtex = new Object();
    public static MessagingClient shared() {
        if (instance == null) {
            synchronized (muxtex) {
                if (instance == null) {
                    instance = new MessagingClient();
                    instance.initialize(CONFIG_PATH);
                }
            }
        }
        return instance;
    }

    private MsgGwConfigInfo configInfo;
    private HttpClient httpClient;
    private MultiThreadedHttpConnectionManager multiThreadConnectionMgr;

    public void initialize(String path) {
        logger.info("#initialize - Load configuration with path: {}", path);
        try {
            Yaml yml = new Yaml();
            InputStream is = new FileInputStream(path);
            configInfo = yml.loadAs(is, MsgGwConfigInfo.class);

            initHttpClient();

        } catch (Exception ex) {
            logger.error("#initialize - ERROR: ", ex);
        }
    }

    private void initHttpClient() {
        logger.info("#initHttpClient - init http client");
        try {
            if (httpClient == null || multiThreadConnectionMgr == null) {
                multiThreadConnectionMgr = new MultiThreadedHttpConnectionManager();
                httpClient = new HttpClient(multiThreadConnectionMgr);
                httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(this.configInfo.getConnectionTimeout() * 1000);
                httpClient.getHttpConnectionManager().getParams().setSoTimeout(this.configInfo.getSoTimeout() * 100);
                httpClient.getHttpConnectionManager().getParams().setMaxConnectionsPerHost(HostConfiguration.ANY_HOST_CONFIGURATION, this.configInfo.getMaxHostConnection());
            } else {
                multiThreadConnectionMgr.closeIdleConnections(5 * 60 * 1000);
                multiThreadConnectionMgr.deleteClosedConnections();
            }
        } catch (Exception ex) {
            logger.error("#initHttpClient - ERROR: ", ex);
        }
    }

    public String getSender() {
        return this.configInfo != null ? this.configInfo.getAlias() : null;
    }

    public Map<String, String> getApsTitle(String key) {
        if (this.configInfo != null && this.configInfo.getApsMessagingTitle() != null) {
            return this.configInfo.getApsMessagingTitle().get(key);
        }
        return null;
    }

    public int send(Message message) {
        PostMethod post = null;
        String response = "";
        int error = 1;
        try {
            post = new PostMethod(this.configInfo.getUrl());
            Gson g = new Gson();
            String reqContent = g.toJson(message);
            RequestEntity entity = new StringRequestEntity(reqContent, "application/json", "UTF-8");
            post.setRequestEntity(entity);

            Language language = Language.get(message.getPreferLanguage());
            String hLang = "km";
            if (language != null) {
                hLang = language.key();
            }
            post.setRequestHeader("language", hLang);
            post.setRequestHeader("Authorization", String.format("%s %s", this.configInfo.getClientName(), this.configInfo.getClientKey()));

            logger.info("# - init http client");
            initHttpClient();
            this.httpClient.executeMethod(post);
            response = post.getResponseBodyAsString();
            this.logger.info(response);

            if (response != null && response.contains("MSG_SUCCESS")) {
                error = 0;
            }
        } catch (Exception ex) {
            this.logger.error("soap message error " + ex.getMessage());
            this.logger.error("response content:" + response);
            error = 1;
        } finally {
            if (post != null) {
                post.releaseConnection();
            }
        }
        return error;
    }

    public int sendAsyncTask(Message message) {
        logger.info("#sendSmsAsyncTask - Start send");
        try {
//            MsgProcessManager.shared().executeSenderTask(message);
            SendMessageTask task = new SendMessageTask(message);
            ProcessManager.shared().executeTask(SendMessageTask.EXECUTOR_CONFIG_NAME, task);
            logger.info("Insert queue to send sms success");
            return 0;
        } catch (Exception ex) {
            logger.error("#sendSmsAsyncTask - ERROR: ", ex);
        }
        return 1;
    }

}
