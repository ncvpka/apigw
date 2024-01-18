package vn.supperapp.apigw.messaging.apps;

import vn.supperapp.apigw.messaging.configs.AppConfigurations;
import vn.supperapp.apigw.messaging.db.DbSessionMgt;
import vn.supperapp.apigw.messaging.db.dto.*;
import vn.supperapp.apigw.messaging.restful.filters.RsAuthFilter;
import vn.supperapp.apigw.messaging.restful.filters.RsResponseFilter;
import vn.supperapp.apigw.utils.LanguageUtils;
import vn.supperapp.apigw.utils.WebLocalizableUtils;
import io.dropwizard.Application;
import io.dropwizard.bundles.assets.ConfiguredAssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.eclipse.jetty.server.session.SessionHandler;

import java.util.Map;

public class EApplication extends Application<EApplicationConfiguration> {

    private final HibernateBundle<EApplicationConfiguration> dbAppHibernateBundle
            = new HibernateBundle<EApplicationConfiguration>(
            AppDevice.class,
            AppUser.class,
            AppConfig.class,
            NotificationLog.class,
            MessageLog.class
    ) {
        @Override
        public DataSourceFactory getDataSourceFactory(EApplicationConfiguration configuration) {
            return configuration.getDbApp();
        }

        @Override
        protected String name() {
            return "dbApp";
        }
    };

    @Override
    public String getName() {
        return "Natcash Messaging";
    }

    @Override
    public void initialize(Bootstrap<EApplicationConfiguration> bootstrap) {

        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        bootstrap.addBundle(dbAppHibernateBundle);

        //bootstrap.addBundle(new AssetsBundle());
        //bootstrap.addBundle(new AssetsBundle("/../lana", "/assets", "index.html"));
        //bootstrap.addBundle(new ConfiguredAssetsBundle("/assets/", "/assets/"));
        bootstrap.addBundle(new ConfiguredAssetsBundle());

        bootstrap.addBundle(new ViewBundle<EApplicationConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(EApplicationConfiguration configuration) {
                return configuration.getViewRendererConfiguration();
            }
        });
    }

    @Override
    public void run(EApplicationConfiguration configuration, Environment environment) {
        SessionHandler sessionHandler = new SessionHandler();
        sessionHandler.setMaxInactiveInterval(15 * 60); //15 minutes
        environment.servlets().setSessionHandler(sessionHandler);

//        RedisService.shared().initialize(configuration.getRedisConfig());
        DbSessionMgt.shared().initDefaultDbSession(dbAppHibernateBundle.getSessionFactory());
//        DbSessionMgt.shared().addDbSession(DbSessionMgt.DB_APP1, dbApp1HibernateBundle.getSessionFactory());

        AppConfigurations.shared().loadConfigurations();
        LanguageUtils.init();
        WebLocalizableUtils.shared();

        //Filter for RESTful services
        environment.jersey().register(RsAuthFilter.class);
        environment.jersey().register(RsResponseFilter.class);

        //Filter for Web App services

        //Scan resources package to register RESTful API or Web App path
        environment.jersey().packages(configuration.getControllerPackages());

//        final ErrorPageErrorHandler epeh = new ErrorPageErrorHandler();
////         // 400 - Bad Request, leave alone
////        epeh.addErrorPage(401, "/error/general-error");
////        epeh.addErrorPage(402, "/error/general-error");
////        epeh.addErrorPage(403, "/error/403");
//        epeh.addErrorPage(404, "/error/404");
////        epeh.addErrorPage(405, 499, "/error/general-error");
////        epeh.addErrorPage(500, 599, "/error/general-error");
//        environment.getApplicationContext().setErrorHandler(epeh);
////        //environment.getAdminContext().setErrorHandler(epeh);
////
//        environment.jersey().register(ErrorPageWebController.class);
    }
}
