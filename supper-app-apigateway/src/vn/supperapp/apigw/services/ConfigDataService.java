package vn.supperapp.apigw.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openide.util.Exceptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author TruongLe
 * @Date 24/02/2022
 * @see ConfigDataService
 */

public class ConfigDataService {
    final static Logger logger = LogManager.getLogger(ConfigDataService.class.getName());
    private static ConfigDataService instance;

    public static ConfigDataService shared() {
        if (instance == null) {
            instance = new ConfigDataService();
            instance.loadConfig();
        }
        return instance;
    }

    private static final String CONFIG_PATH = "../etc/web-data-config.yml";
    private static final String SERVICE_BUY_DATA = "buyData";
    private static final String SERVICE_TRANS_HISTORY = "transHisProcessCodeConfigs";
    private static final String SERVICE_FEE = "fee";
    private static final String SERVICE_CASH_IN = "cashIn";
    private static final String SERVICE_TOP_UP = "topUp";
    private static final String SERVICE_URL_LOGIN_CONFIG = "urlLoginConfig";
    private static final String CONFIG_PARTNER_INFO = "configPartnerInfo";
    private List<UrlLoginInfoConfig> urlLoginConfig;

    private void loadConfig() {
        try {

            Class<Map<String, Object>> cls = (Class<Map<String, Object>>) (Object) Map.class;
            Yaml yaml = new Yaml(new Constructor(cls));
            InputStream inputStream = new FileInputStream(CONFIG_PATH);
            Map<String, Object> tmp = yaml.loadAs(inputStream, cls);

            ObjectMapper mapper = new ObjectMapper();
            if (tmp != null) {
                tmp.forEach((k, v) -> {
                    if (SERVICE_URL_LOGIN_CONFIG.equals(k)) {
                        urlLoginConfig = new ArrayList<>();
                        List<UrlLoginInfoConfig> list = ((ArrayList) v);
                        for (int i = 0; i < list.size(); i++) {
                            UrlLoginInfoConfig data = mapper.convertValue(list.get(i), UrlLoginInfoConfig.class);
                            urlLoginConfig.add(data);
                        }
                    }
                });
            }

        } catch (Exception ex) {
            logger.error("loadConfig - ERROR: ", ex);
            Exceptions.printStackTrace(ex);
        }
    }

    public List<UrlLoginInfoConfig> getUrlLoginConfig() {
        return urlLoginConfig;
    }

    public UrlLoginInfoConfig getUrlLoginConfigByLanguage(String language) {
        for (int i = 0; i < urlLoginConfig.size(); i++) {
            if (language.equals(urlLoginConfig.get(i).getLanguage())) {
                return urlLoginConfig.get(i);
            }
        }
        return null;
    }

    public static String getConfigPath() {
        return CONFIG_PATH;
    }


}
