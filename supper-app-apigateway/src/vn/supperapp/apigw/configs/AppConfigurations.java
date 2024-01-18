package vn.supperapp.apigw.configs;

import vn.supperapp.apigw.beans.AppConfigInfo;
import vn.supperapp.apigw.objs.OtpConfigInfo;
import vn.supperapp.apigw.objs.UploadConfigInfo;
import vn.supperapp.apigw.utils.enums.ReleaseMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;

public class AppConfigurations {
    private static final Logger logger = LoggerFactory.getLogger(AppConfigurations.class);
    private static final String APP_CONFIG_PATH = "../etc/config-app.yml";

    //region - Initialize configurations
    private static volatile AppConfigurations instance;
    private static Object mutex = new Object();

    public static AppConfigurations shared() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new AppConfigurations();
                    instance.loadConfigurations();
                }
            }
        }
        return instance;
    }

    public void loadConfigurations() {
        logger.info("#loadConfigurations - load config");
        try {
            logger.info("#loadConfigurations - load SMS CONFIG INFO");
            Yaml yml = new Yaml();
            InputStream is = new FileInputStream(APP_CONFIG_PATH);
            this.configs = yml.loadAs(is, AppConfigInfo.class);

        } catch (Exception ex) {
            logger.error("#loadConfigurations - Errors: ", ex);
//            System.exit(0);
        }
    }

    //endregion - Initialize configurations

    //region - Properties
    private AppConfigInfo configs;

    public String getAppPrivateKey() {
        return this.configs != null ? this.configs.getAppPrivateKey() : null;
    }
    public String getAppPublicKey() {
        return this.configs != null ? this.configs.getAppPublicKey() : null;
    }
    public long getLoginTokenExpired() {
        return this.configs.getLoginTokenExpired();
    }
    public long getLoginTokenCachedExpired() {
        return this.configs.getLoginTokenCachedExpired();
    }
    public boolean checkClientRestriction(String clientKey) {
        return this.configs != null && this.configs.getClientRestriction() != null && this.configs.getClientRestriction().contains(clientKey);
    }

    public Integer getOthersConfigAsInteger(String key) {
        if (this.configs == null || this.configs.getOthersConfigs() == null) {
            return null;
        }

        Object tmp = this.configs.getOthersConfigs().get(key);
        return tmp != null ? Integer.valueOf(tmp.toString()) : null;
    }

    public Integer getConfigAsInt(String key) {
        if (this.configs == null || this.configs.getOthersConfigs() == null) {
            return null;
        }

        Object tmp = this.configs.getOthersConfigs().get(key);
        return tmp != null ? Integer.valueOf(tmp.toString()) : null;
    }

    public String getConfigAsString(String key) {
        if (this.configs == null || this.configs.getOthersConfigs() == null) {
            return null;
        }

        Object tmp = this.configs.getOthersConfigs().get(key);
        return tmp != null ? tmp.toString() : null;
    }

    public Double getConfigAsDouble(String key) {
        if (this.configs == null || this.configs.getOthersConfigs() == null) {
            return null;
        }

        Object tmp = this.configs.getOthersConfigs().get(key);
        return tmp != null ? Double.valueOf(tmp.toString()) : null;
    }

    public AppConfigInfo getConfigs() {
        return configs;
    }

    public void setConfigs(AppConfigInfo configs) {
        this.configs = configs;
    }



    //endregion - Properties

    //region - Common Functions
    public boolean isProductionMode() {
        return ReleaseMode.PRODUCTION.is(this.configs.getReleaseMode());
    }

    public OtpConfigInfo getOtpConfig() {
        return this.configs.getOtpConfigs();
    }
    //endregion - Common Functions

    public String getLottoPrivateKey() {
        return this.configs != null ? this.configs.getLottoPrivateKey() : null;
    }
    public String getLottoPublicKey() {
        return this.configs != null ? this.configs.getLottoPublicKey() : null;
    }
    public UploadConfigInfo getUploadConfig() {
        return this.configs.getUploadConfigs();
    }

}
