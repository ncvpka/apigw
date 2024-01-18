package vn.supperapp.apigw.clients.epayment;

import com.google.gson.*;
import vn.supperapp.apigw.clients.IdleConnectionMonitor;
import vn.supperapp.apigw.clients.InsecureHostnameVerifier;
import vn.supperapp.apigw.clients.epayment.objs.EPaymentRequest;
import vn.supperapp.apigw.clients.epayment.objs.EPaymentResponse;
import vn.supperapp.apigw.clients.epayment.objs.TransDetail;
import vn.supperapp.apigw.utils.CommonUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.openide.util.Exceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Date;

public class EMoneyPaymentClient {
    final static Logger logger = LoggerFactory.getLogger(EMoneyPaymentClient.class);
    private static volatile EMoneyPaymentClient instance;
    private static Object mutex = new Object();
    private static final String CONFIG_PATH = "../etc/services/epayment-client-config.yml";
//    private static final String CONFIG_PATH = "D:\\camid\\sources\\camid-process-app\\etc\\epayment\\epayment-client-config.yml";

    private CloseableHttpClient httpClient;
    private PoolingHttpClientConnectionManager cm = null; // new PoolingHttpClientConnectionManager();
    private IdleConnectionMonitor monitor;

    EMoneyPaymentConfigInfo config;

    public EMoneyPaymentClient() {
        try {
            logger.info("Init MochaMovieV3Client");
            //TODO: Do something to init

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public static EMoneyPaymentClient shared() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new EMoneyPaymentClient();
                    instance.loadConfig();
                }
            }
        }
        return instance;
    }

    private void loadConfig() {
        try {
            logger.info("#loadConfig - Read config from file: {}", CONFIG_PATH);
            Yaml yaml = new Yaml();
            InputStream inputStream = new FileInputStream(CONFIG_PATH);
            this.config = yaml.loadAs(inputStream, EMoneyPaymentConfigInfo.class);

            logger.info("#loadConfig - Init connection manager with configuration");
            if (this.config.isSslDisable()) {
                SSLContext sslContext = SSLContexts.custom().loadTrustMaterial((chain, authType) -> true).build();

                SSLConnectionSocketFactory sslConnectionSocketFactory =
                        new SSLConnectionSocketFactory(sslContext, new String[]
                                { "SSLv3", "TLSv1","TLSv1.1", "TLSv1.2" }, null,
                                NoopHostnameVerifier.INSTANCE);
                cm = new PoolingHttpClientConnectionManager(RegistryBuilder.
                        <ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", sslConnectionSocketFactory).build());
            } else {
                cm = new PoolingHttpClientConnectionManager();
            }

            cm.setMaxTotal(this.config.getMaxTotal());
            cm.setDefaultMaxPerRoute(this.config.getMaxConnectionPerHost());

            logger.info("#loadConfig - Init timeout config");
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(this.config.getConnectionTimeout())
                    .setConnectionRequestTimeout(this.config.getConnectionRequestTimeout())
                    .setSocketTimeout(this.config.getSoTimeout()).build();

            logger.info("#loadConfig - build client");
            HttpClientBuilder builder = HttpClients.custom()
                    .setDefaultRequestConfig(config)
                    .setRedirectStrategy(new LaxRedirectStrategy())
                    .setSSLHostnameVerifier(new InsecureHostnameVerifier())
                    .setConnectionManager(cm)
                    ;

            httpClient = builder.build();

            logger.info("#loadConfig - build close idle connection monitor");
            monitor = new IdleConnectionMonitor(cm);
            // Start up the monitor.
            Thread monitorThread = new Thread(monitor);
            monitorThread.setDaemon(true);
            monitorThread.start();

        } catch (Exception ex) {
            logger.error("loadConfig - ERROR: ", ex);
            Exceptions.printStackTrace(ex);
        }
    }

    public void shutdownMonitor() throws InterruptedException, IOException {
        if (httpClient != null) {
            httpClient.close();
        }
        if (this.monitor != null) {
            this.monitor.shutdown();
        }
    }

    public String getConfigAsString(String key) {
        try {
            if (this.config != null && this.config.getConfigurations() != null) {
                Object tmp = this.config.getConfigurations().get(key);
                return tmp != null ? tmp.toString() : null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#getConfigAsString - ERROR: ", ex);
        }
        return null;
    }

    /**
     * initTransferMoney:
     * Response status in:
     * -1: Exception error
     * 0: Success http response
     * 4: Unknown error
     * Other case: Error
     * @param request
     * @param language (en: English, km: Khmer)
     * @return
     */
    public EPaymentResponse initTransferMoney(EPaymentRequest request, String language) {
        logger.info("#initTransferMoney - START: language = {}; REQUEST = {}", language, (request != null ? request.toLogString() : "NULL"));
        long timeStart = System.currentTimeMillis();
        EPaymentResponse response = null;
        HttpPost hPost = null;
        CloseableHttpResponse hRes = null;
        try {
            if (CommonUtils.isNullOrEmpty(language)) {
                language = "km";
            }

            String fullUrl = String.format("%s/%s/%s", this.config.getUrl(), this.config.getMerchantCode(), this.config.getApis().get("initTransfer"));
            logger.info("#initTransferMoney - Api URL: {}", fullUrl);
            hPost = new HttpPost(fullUrl);
            hPost.addHeader("Content-Type", "application/json; charset=utf8");
            hPost.addHeader("Accept", "application/json");
            hPost.addHeader("Authorization", this.config.getApiKey());
            hPost.addHeader("e-language", language);

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Date.class, new JsonDeserializer() {
                @Override
                public Date deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
                    Timestamp stamp = new Timestamp(je.getAsLong());
                    Date date = new Date(stamp.getTime());
//                    return CommonUtils.toDate(je.getAsString(), "yyyy-MM-dd'T'HH:mm:ss");
                    return date;
                }
            });

//            Gson gson = new Gson();
            Gson gson = builder.create();
            String requestStr = gson.toJson(request);
            logger.info("#initTransferMoney - REQUEST: " + requestStr);

            StringEntity entity = new StringEntity(requestStr);
            hPost.setEntity(entity);

            logger.info("#initTransferMoney: START TO SENDING REQUEST TO SERVER");
            hRes = httpClient.execute(hPost);

            logger.info("#initTransferMoney: EXECUTE SOAP SUCCESS - check HTTP STATUS");
            if (hRes == null || hRes.getStatusLine().getStatusCode() != 200) {
                logger.info("#initTransferMoney HTTP STATUS: {}", hRes.getStatusLine().getStatusCode());
                return EPaymentResponse.build(-1, "HTTP_ERROR", String.format("Http error: ", (hRes != null ? hRes.getStatusLine().getStatusCode() : "NULL")));
            }

            logger.info("#initTransferMoney: EXECUTE SOAP SUCCESS - start to get response");
            String res = EntityUtils.toString(hRes.getEntity());

            if (CommonUtils.isNullOrEmpty(res)) {
                logger.info("#initTransferMoney RESPONSE IS NULL");
                return EPaymentResponse.build(-1, "RESPONSE_CONTENT_NULL", "Response content is null or empty");
            }

            logger.info("#initTransferMoney RESPONSE: " + res);
            response = gson.fromJson(res, EPaymentResponse.class);
            if (response == null) {
                logger.info("#initTransferMoney - PARSE RESPONSE NULL");
                return EPaymentResponse.build(4, "PARSE_RESPONSE_ERROR", "Cannot parse response content (gson)");
            }

            logger.info("#initTransferMoney RESPONSE: SUCCESS");
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage(), ex);
            response = EPaymentResponse.build(-1, "EXCEPTION", ex.getMessage());
        } finally {
            if (hPost != null) {
                hPost.releaseConnection();
            }
            if (hRes != null) {
                try {
                    hRes.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        logger.info("#initTransferMoney END - Time : " + (System.currentTimeMillis() - timeStart));
        return response;
    }

    /**
     * initTransferMoney:
     * Response status in:
     * -1: Exception error
     * 0: Success http response
     * 4: Unknown error
     * Other case: Error
     * @param request
     * @param language (en: English, km: Khmer)
     * @return
     */
    public EPaymentResponse confirmTransferMoney(EPaymentRequest request, String language) {
        logger.info("#confirmTransferMoney - START: language = {}; REQUEST = {}", language, (request != null ? request.toLogString() : "NULL"));
        long timeStart = System.currentTimeMillis();
        EPaymentResponse response = null;
        HttpPost hPost = null;
        CloseableHttpResponse hRes = null;
        try {
            if (CommonUtils.isNullOrEmpty(language)) {
                language = "km";
            }

            String fullUrl = String.format("%s/%s/%s", this.config.getUrl(), this.config.getMerchantCode(), this.config.getApis().get("confirmTransfer"));
            logger.info("#confirmTransferMoney - Api URL: {}", fullUrl);
            hPost = new HttpPost(fullUrl);
            hPost.addHeader("Content-Type", "application/json; charset=utf8");
            hPost.addHeader("Accept", "application/json");
            hPost.addHeader("Authorization", this.config.getApiKey());
            hPost.addHeader("e-language", language);

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Date.class, new JsonDeserializer() {
                @Override
                public Date deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
                    Timestamp stamp = new Timestamp(je.getAsLong());
                    Date date = new Date(stamp.getTime());
//                    return CommonUtils.toDate(je.getAsString(), "yyyy-MM-dd'T'HH:mm:ss");
                    return date;
                }
            });

//            Gson gson = new Gson();
            Gson gson = builder.create();

            logger.info("#confirmTransfer - Check and generate key");
            if (CommonUtils.isNullOrEmpty(this.config.getSecretCode())) {
                logger.info("#confirmTransfer - Not found secret code, please check configuration");
                return EPaymentResponse.build(-1, "SECRET_CODE_NULL", "Secret code is null, please check configuration");
            }

            if (CommonUtils.isNullOrEmpty(request.getTxPaymentTokenId())) {
                logger.info("#confirmTransfer - Payment Token ID is null, please check input");
                return EPaymentResponse.build(-1, "PAYMENT_TOKEN_NULL", "Payment Token ID is null, please check input");
            }

//            String tmp = AppCryptUtils.decryptData(request.getTxPaymentTokenId(), this.config.getPrivateKey());
            String tmp = ""; //AppCryptUtils.decryptRSA(request.getTxPaymentTokenId(), this.config.getPrivateKeyByte());
            if (CommonUtils.isNullOrEmpty(tmp)) {
                logger.info("#confirmTransfer - Payment Token ID INVALID, please check input");
                return EPaymentResponse.build(-1, "PAYMENT_TOKEN_INVALID", "Payment Token ID invalid, cannot decrypt, please check input");
            }

//            String encPaymentTokenId = AppCryptUtils.encryptData(String.format("%s|%s", tmp, this.config.getSecretCode()), this.config.getPublicKey());
            String encPaymentTokenId = "";//AppCryptUtils.encryptRSA(String.format("%s|%s", tmp, this.config.getSecretCode()), this.config.getPublicKeyByte());
            if (CommonUtils.isNullOrEmpty(encPaymentTokenId)) {
                logger.info("#confirmTransfer - Cannot encrypt payment token ID, please check configuration");
                return EPaymentResponse.build(-1, "PAYMENT_TOKEN_ENCRYPTED_FAIL", "Cannot encrypt payment token ID, please check configuration");
            }

            logger.info("#confirmTransferMoney - Encrypted payment token success, re-set payment token to request and start to consume api");
            request.setTxPaymentTokenId(encPaymentTokenId);

            String requestStr = gson.toJson(request);
            logger.info("#confirmTransferMoney - REQUEST: " + requestStr);

            StringEntity entity = new StringEntity(requestStr);
            hPost.setEntity(entity);

            logger.info("#confirmTransferMoney: START TO SENDING REQUEST TO SERVER");
            hRes = httpClient.execute(hPost);

            logger.info("#confirmTransferMoney: EXECUTE SOAP SUCCESS - check HTTP STATUS");
            if (hRes == null) {
                logger.info("#confirmTransferMoney HTTP RESPONSE IS NULL");
                return EPaymentResponse.build(4, "HTTP_RESPONSE_NULL", "Response is null, unknown payment");
            }

            if (hRes.getStatusLine().getStatusCode() != 200) {
                logger.info("#confirmTransferMoney HTTP STATUS: {}", hRes.getStatusLine().getStatusCode());
                return EPaymentResponse.build(-1, "HTTP_ERROR", String.format("Http error: ", (hRes != null ? hRes.getStatusLine().getStatusCode() : "NULL")));
            }

            logger.info("#confirmTransferMoney: EXECUTE SOAP SUCCESS - start to get response");
            String res = EntityUtils.toString(hRes.getEntity());

            if (CommonUtils.isNullOrEmpty(res)) {
                logger.info("#confirmTransferMoney RESPONSE IS NULL");
                return EPaymentResponse.build(4, "RESPONSE_CONTENT_NULL", "Response content is null or empty, unknown payment");
            }

            logger.info("#confirmTransferMoney RESPONSE: " + res);
            response = gson.fromJson(res, EPaymentResponse.class);
            if (response == null) {
                logger.info("#confirmTransferMoney - PARSE RESPONSE NULL");
                return EPaymentResponse.build(4, "PARSE_RESPONSE_ERROR", "Cannot parse response content (gson), unknown payment");
            }

            TransDetail tx = response.getTxDetail();
            if (tx == null) {
                logger.info("#confirmTransferMoney - TRANS DETAIL NULL");
                return EPaymentResponse.build(4, "TRANS_DETAIL_RESPONSE_NULL", "Not found detail transaction, unknown payment");
            }

            //0, 1: New
            //2: Inprogress
            //4: Error
            //6: Unknown
            //5: Cancel
            //3: Paid (success)
            if (tx.getStatus() == 4 || tx.getStatus() == 6 || tx.getStatus() == 0 || tx.getStatus() == 1 || tx.getStatus() == 2) {
                logger.info("#confirmTransferMoney - TRANS DETAIL STATUS UNKNOWN");
                return EPaymentResponse.build(response, 4, "TRANS_DETAIL_STATUS_UNKNOWN", "Unknown status from trans detail, unknown payment");
            }

            if (tx.getStatus() != 3) {
                logger.info("#confirmTransferMoney - TRANS DETAIL STATUS ERROR");
                return EPaymentResponse.build(response, -1, "TRANS_DETAIL_STATUS_ERROR", "Error status from trans detail");
            }

            response.setStatus(0); //Update status to success

            logger.info("#confirmTransferMoney RESPONSE: SUCCESS");
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage(), ex);
            //TODO: set status to unknown for confirm api
            response = EPaymentResponse.build(4, "EXCEPTION", ex.getMessage());
        } finally {
            if (hPost != null) {
                hPost.releaseConnection();
            }
            if (hRes != null) {
                try {
                    hRes.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        logger.info("#confirmTransferMoney END - Time : " + (System.currentTimeMillis() - timeStart));
        return response;
    }


    /**
     * checkTransStatus:
     * Response status in:
     * -1: Exception error
     * 0: Success http response
     * 4: Unknown error
     * Other case: Error
     * @param request
     * @param language (en: English, km: Khmer)
     * @return
     */
    public EPaymentResponse checkTransStatus(EPaymentRequest request, String language) {
        logger.info("#checkTransStatus - START: language = {}; REQUEST = {}", language, (request != null ? request.toLogString() : "NULL"));
        long timeStart = System.currentTimeMillis();
        EPaymentResponse response = null;
        HttpPost hPost = null;
        CloseableHttpResponse hRes = null;
        try {
            if (CommonUtils.isNullOrEmpty(language)) {
                language = "km";
            }

            String fullUrl = String.format("%s/%s/%s", this.config.getUrl(), this.config.getMerchantCode(), this.config.getApis().get("checkTransaction"));
            logger.info("#checkTransStatus - Api URL: {}", fullUrl);
            hPost = new HttpPost(fullUrl);
            hPost.addHeader("Content-Type", "application/json; charset=utf8");
            hPost.addHeader("Accept", "application/json");
            hPost.addHeader("Authorization", this.config.getApiKey());
            hPost.addHeader("e-language", language);

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Date.class, new JsonDeserializer() {
                @Override
                public Date deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
                    Timestamp stamp = new Timestamp(je.getAsLong());
                    Date date = new Date(stamp.getTime());
//                    return CommonUtils.toDate(je.getAsString(), "yyyy-MM-dd'T'HH:mm:ss");
                    return date;
                }
            });

//            Gson gson = new Gson();
            Gson gson = builder.create();
            String requestStr = gson.toJson(request);
            logger.info("#checkTransStatus - REQUEST: " + requestStr);

            StringEntity entity = new StringEntity(requestStr);
            hPost.setEntity(entity);

            logger.info("#checkTransStatus: START TO SENDING REQUEST TO SERVER");
            hRes = httpClient.execute(hPost);

            logger.info("#checkTransStatus: EXECUTE SOAP SUCCESS - check HTTP STATUS");
            if (hRes == null || hRes.getStatusLine().getStatusCode() != 200) {
                logger.info("#checkTransStatus HTTP STATUS: {}", hRes.getStatusLine().getStatusCode());
                return EPaymentResponse.build(-1, "HTTP_ERROR", String.format("Http error: ", (hRes != null ? hRes.getStatusLine().getStatusCode() : "NULL")));
            }

            logger.info("#checkTransStatus: EXECUTE SOAP SUCCESS - start to get response");
            String res = EntityUtils.toString(hRes.getEntity());

            if (CommonUtils.isNullOrEmpty(res)) {
                logger.info("#checkTransStatus RESPONSE IS NULL");
                return EPaymentResponse.build(-1, "RESPONSE_CONTENT_NULL", "Response content is null or empty");
            }

            logger.info("#checkTransStatus RESPONSE: " + res);
            response = gson.fromJson(res, EPaymentResponse.class);
            if (response == null) {
                logger.info("#checkTransStatus - PARSE RESPONSE NULL");
                return EPaymentResponse.build(4, "PARSE_RESPONSE_ERROR", "Cannot parse response content (gson)");
            }

            logger.info("#checkTransStatus RESPONSE: SUCCESS");
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage(), ex);
            response = EPaymentResponse.build(-1, "EXCEPTION", ex.getMessage());
        } finally {
            if (hPost != null) {
                hPost.releaseConnection();
            }
            if (hRes != null) {
                try {
                    hRes.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        logger.info("#getHot END - Time : " + (System.currentTimeMillis() - timeStart));
        return response;
    }

    public static void main(String[] args) {
        try {

            if (true) {
                //Check status
                Long transDetailId = 23674L;
                String refId = "L119";
                EPaymentRequest request = new EPaymentRequest();
                request.setTransDetailId(transDetailId);
                request.setRefId(refId);

                EPaymentResponse response = EMoneyPaymentClient.shared().checkTransStatus(request, "en");
                System.out.println("TEST CHECK");
                return;
            }

            String refId = "L119";
            EPaymentRequest request = new EPaymentRequest();
            request.setPaymentType(0);
            request.setRefId(refId);
            request.setTransAmount(1.0D);
            request.setCurrency("USD");
            request.setContent("Test trans from CamID");
            request.setCustomerPhoneNumber("0888288688");

            EPaymentResponse response = EMoneyPaymentClient.shared().initTransferMoney(request, "en");

            request.setTxPaymentTokenId(response.getTxDetail().getTxPaymentTokenId());
            request.setRefNo(refId);
            response = EMoneyPaymentClient.shared().confirmTransferMoney(request, "en");

            System.out.println("TEST");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
