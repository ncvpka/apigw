package vn.supperapp.apigw.messaging.configs;

import java.util.Map;

public class MsgGwConfigInfo {
    private boolean enabled;
    private String url;
    private String alias;
    private String clientName;
    private String clientKey;
    private int connectionTimeout;
    private int soTimeout;
    private int maxHostConnection;
    private String releaseMode;

    private Map<String, Map<String, String>> apsMessagingTitle;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    public int getMaxHostConnection() {
        return maxHostConnection;
    }

    public void setMaxHostConnection(int maxHostConnection) {
        this.maxHostConnection = maxHostConnection;
    }

    public Map<String, Map<String, String>> getApsMessagingTitle() {
        return apsMessagingTitle;
    }

    public void setApsMessagingTitle(Map<String, Map<String, String>> apsMessagingTitle) {
        this.apsMessagingTitle = apsMessagingTitle;
    }

    public String getReleaseMode() {
        return releaseMode;
    }

    public void setReleaseMode(String releaseMode) {
        this.releaseMode = releaseMode;
    }
}
