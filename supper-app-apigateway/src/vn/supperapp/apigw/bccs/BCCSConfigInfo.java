package vn.supperapp.apigw.bccs;

public class BCCSConfigInfo {
    private String url;
    private int maxTotal = 100;
    private int maxConnectionPerHost = 100;
    private int connectionTimeout = 60000;
    private int readTimeout = 60000;
    private int writeTimeout = 60000;
    private String wsUser;
    private String wsPass;



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxConnectionPerHost() {
        return maxConnectionPerHost;
    }

    public void setMaxConnectionPerHost(int maxConnectionPerHost) {
        this.maxConnectionPerHost = maxConnectionPerHost;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(int writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public String getWsUser() {
        return wsUser;
    }

    public void setWsUser(String wsUser) {
        this.wsUser = wsUser;
    }

    public String getWsPass() {
        return wsPass;
    }

    public void setWsPass(String wsPass) {
        this.wsPass = wsPass;
    }
}
