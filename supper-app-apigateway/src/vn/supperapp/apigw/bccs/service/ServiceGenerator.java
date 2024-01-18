package vn.supperapp.apigw.bccs.service;

import vn.supperapp.apigw.bccs.BCCSConfigInfo;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import org.openide.util.Exceptions;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import rx.schedulers.Schedulers;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class ServiceGenerator {
    final static Logger logger = LoggerFactory.getLogger(ServiceGenerator.class);
    private static final String CONFIG_PATH = "../etc/services/bccs-client-config.yml";
    private static final Object LOCK = new Object();
    private static ServiceGenerator sPartnerService = null;
    private static ServiceGenerator sWso2Service = null;
    private static Retrofit retrofitWSO2 = null;
    BCCSConfigInfo config;

    public static ServiceGenerator getInstanceBCCS() {
        if (sWso2Service == null) {
            synchronized (LOCK) {
                sWso2Service = new ServiceGenerator();

            }
        }
        return sWso2Service;
    }

    private ServiceGenerator() {
        loadConfig();
        retrofitWSO2 = createBCCS();
    }




    private Retrofit createBCCS() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient.Builder builder = getUnsafeOkHttpClient()
                .addInterceptor(interceptor);
        builder.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder();

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
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(
                        new Persister(new AnnotationStrategy())))
                .client(okHttpClient)
                .build();
    }

    private void loadConfig() {
        try {
            logger.info("#loadConfig - Read config from file: {}", CONFIG_PATH);
            Yaml yaml = new Yaml();
            InputStream inputStream = new FileInputStream(CONFIG_PATH);
            this.config = yaml.loadAs(inputStream, BCCSConfigInfo.class);

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

    public <S> S createServiceBCCS(Class<S> serviceClass) {
        return retrofitWSO2.create(serviceClass);
    }



}
