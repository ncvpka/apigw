package vn.supperapp.apigw.wso2.service;

import vn.supperapp.apigw.wso2.Wso2ConfigInfo;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import org.openide.util.Exceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class ServiceGenerator {
    final static Logger logger = LoggerFactory.getLogger(ServiceGenerator.class);
    private static final String CONFIG_PATH = "../etc/services/wso2-client-config.yml";
    private static final Object LOCK = new Object();
    public static final String ACCEPT_LANGUAGE = "Accept-Language";

    private static ServiceGenerator sPartnerService = null;
    private static ServiceGenerator sWso2Service = null;
    private String mAuthenToken;
    private static Retrofit retrofitWSO2 = null;
    private static Retrofit retrofitPartner = null;
    Wso2ConfigInfo config;

    public static ServiceGenerator getPartnerInstance(HttpServletRequest httpServletRequest, String baseUrl) {
        if (sPartnerService == null) {
            synchronized (LOCK) {
                sPartnerService = new ServiceGenerator(httpServletRequest, baseUrl);
            }
        }
        return sPartnerService;
    }

    public static ServiceGenerator getInstanceWSO2() {
        if (sWso2Service == null) {
            synchronized (LOCK) {
                sWso2Service = new ServiceGenerator();

            }
        }
        return sWso2Service;
    }

    private ServiceGenerator() {
        loadConfig();
        retrofitWSO2 = createWSO2();
    }

    private ServiceGenerator(HttpServletRequest httpServletRequest, String baseUrl) {
        retrofitPartner = createPartner(httpServletRequest, baseUrl);
    }

    /**
     * using for api must have Authorization in header
     * just make sure to use the token already exists, otherwise the result will be the same as calling api unsafe
     */
    public static ServiceGenerator getSafeApiServiceInstance(HttpServletRequest httpServletRequest, String baseUrl) {
        if (sPartnerService == null) {
            synchronized (LOCK) {
                sPartnerService = new ServiceGenerator(httpServletRequest, baseUrl);
            }
        }
        return sPartnerService;
    }

    private Retrofit createPartner(HttpServletRequest httpServletRequest, String baseUrl) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient.Builder builder = getUnsafeOkHttpClient()
                .addInterceptor(interceptor);
        builder.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder();
            requestBuilder.addHeader("Accept", "application/json");
            String language = httpServletRequest.getHeader("Accept-Language");
            if (language == null) {
                language = "en";
            }
            requestBuilder.addHeader("Accept-Language", language);
            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        OkHttpClient okHttpClient = builder
                .connectTimeout(config.getConnectionTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(config.getReadTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(config.getWriteTimeout(), TimeUnit.MILLISECONDS).build();
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    private Retrofit createWSO2() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient.Builder builder = getUnsafeOkHttpClient()
                .addInterceptor(interceptor);
        builder.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder();
            requestBuilder.addHeader("Accept", "application/json");
            Request request = requestBuilder.build();
            return chain.proceed(request);
        });
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(config.getMaxTotal());
        dispatcher.setMaxRequestsPerHost(config.getMaxConnectionPerHost());
        OkHttpClient okHttpClient = builder
                .connectTimeout(config.getConnectionTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(config.getReadTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(config.getWriteTimeout(), TimeUnit.MILLISECONDS)
                .dispatcher(dispatcher)
                .build();
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        return new Retrofit.Builder()
                .baseUrl(config.getUrl())
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    private void loadConfig() {
        try {
            logger.info("#loadConfig - Read config from file: {}", CONFIG_PATH);
            Yaml yaml = new Yaml();
            InputStream inputStream = new FileInputStream(CONFIG_PATH);
            this.config = yaml.loadAs(inputStream, Wso2ConfigInfo.class);

        } catch (Exception ex) {
            logger.error("loadConfig - ERROR: ", ex);
            Exceptions.printStackTrace(ex);
        }
    }

    private OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            X509TrustManager trustManager = (X509TrustManager) trustAllCerts[0];

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, trustManager)
                    .hostnameVerifier((hostname, session) -> true);

            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <S> S createServiceWSO2(Class<S> serviceClass) {
        return retrofitWSO2.create(serviceClass);
    }

    public <S> S createServicePartner(Class<S> serviceClass) {
        return retrofitPartner.create(serviceClass);
    }

}
