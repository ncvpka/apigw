package vn.supperapp.apigw.clients.firebase;

import com.google.gson.*;
import vn.supperapp.apigw.clients.firebase.objs.FirebaseHttpConfigInfo;
import vn.supperapp.apigw.clients.firebase.objs.FirebaseShortLinkResponse;
import vn.supperapp.apigw.utils.DateTimeUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.openide.util.Exceptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Date;

public class FirebaseHttpClient {
    final static Logger logger = LogManager.getLogger(FirebaseHttpClient.class.getName());
    private static final String CONFIG_PATH = "../etc/services/firebase-http-config.yml";

    private static FirebaseHttpClient instance;
    private static final Object mutex = new Object();

    private FirebaseHttpConfigInfo config;
    private JerseyClient client;

    public static FirebaseHttpClient shared() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new FirebaseHttpClient();
                    instance.loadConfig();
                }
            }
        }
        return instance;
    }

    private void loadConfig() {
        try {
            Yaml yaml = new Yaml(new Constructor(FirebaseHttpConfigInfo.class));
            InputStream inputStream = new FileInputStream(CONFIG_PATH);
            this.config = yaml.loadAs(inputStream, FirebaseHttpConfigInfo.class);

        } catch (Exception ex) {
            logger.error("loadConfig - ERROR: ", ex);
            Exceptions.printStackTrace(ex);
        }
    }

    private void initClient() {
        logger.info("#initClient - Start");
        try {
            if (config != null) {

                logger.info("#initClient - Init client object");
                JerseyClientBuilder clientBuilder = new JerseyClientBuilder();
                ClientConfig clientConfig = clientBuilder.getConfiguration();
                if (clientConfig == null) {
                    clientConfig = new ClientConfig();
                }
                // values are in milliseconds
                clientConfig.property(ClientProperties.READ_TIMEOUT, 60000);
                clientConfig.property(ClientProperties.CONNECT_TIMEOUT, 60000);

//                if (this.config.getUrl().startsWith("https")) { //Is SSL
//                    logger.info("#initClient - set SSL handshake");
//                    SSLContext sc = SSLContext.getInstance("SSL");
//                    TrustManager[] trustAllCerts = {new InsecureTrustManager()};
//                    sc.init(null, trustAllCerts, new java.security.SecureRandom());
//
//                    clientBuilder.sslContext(sc).hostnameVerifier(new InsecureHostnameVerifier());
//                }
                logger.info("#initClient - create new client object");
                this.client = clientBuilder.withConfig(clientConfig).build();
            } else {
                logger.error("#initClient - ERROR: - NO CONFIGURATION FOUND");
            }
        } catch (Exception ex) {
            logger.error("#initClient - ERROR: ", ex);
        }
    }

    public FirebaseShortLinkResponse getFirebaseDeepLink(String reqData) {
        logger.info("#getFirebaseDeepLink - START");
        try {
            if (this.client == null) {
                initClient();
            }

            Response res = client.target(this.config.getUrl())
//                    .path(api)
                    .request(MediaType.APPLICATION_JSON)
//                    .headers(reqHeaders)
//                    .post(Entity.entity(parameters, MediaType.APPLICATION_JSON))
                    .post(Entity.entity(reqData, MediaType.APPLICATION_JSON));

            if (res.getStatus() != 200) {
                logger.error("#getFirebaseDeepLink - ERROR: " + res.getStatus());
                return null;
            }

            String jsonStr = res.readEntity(String.class);
            logger.info("#getFirebaseDeepLink ## RESPONSE: " + jsonStr);

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                @Override
                public Date deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
                    return DateTimeUtils.toDate(je.getAsString(), "yyyy-MM-dd'T'HH:mm:ss");
                }
            });
            Gson gson = builder.create();

            FirebaseShortLinkResponse objRes = gson.fromJson(jsonStr, FirebaseShortLinkResponse.class);

            return objRes;
        } catch (Exception ex) {
            logger.error("#getFirebaseDeepLink - ERROR: ", ex);
        }
        return null;
    }

    public FirebaseHttpConfigInfo getConfig() {
        return config;
    }

    public String getDefaultReferralLink() {
        return this.config.getDefaultReferralLink();
    }

}
