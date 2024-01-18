package vn.supperapp.apigw.clients.epayment;

import java.util.Map;

public class EMoneyPaymentConfigInfo {
    private String url;
    private boolean sslDisable;
    private String apiKey;
    private String merchantCode;
    private int maxTotal = 100;
    private int maxConnectionPerHost = 100;
    private int connectionTimeout = 60000;
    private int soTimeout = 60000;
    private int connectionRequestTimeout = 60000;
    private int idleTimeout = 60000;
    private Map<String, String> apis;
    private Map<String, Object> configurations;
    private String privateKey;
    private String publicKey;
    private String secretCode;

    private byte[] privateKeyByte;
    private byte[] publicKeyByte;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isSslDisable() {
        return sslDisable;
    }

    public void setSslDisable(boolean sslDisable) {
        this.sslDisable = sslDisable;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
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

    public int getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(int idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public int getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    public Map<String, String> getApis() {
        return apis;
    }

    public void setApis(Map<String, String> apis) {
        this.apis = apis;
    }

    public Map<String, Object> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(Map<String, Object> configurations) {
        this.configurations = configurations;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

    public byte[] getPrivateKeyByte() {
        return privateKeyByte;
    }

    public void setPrivateKeyByte(byte[] privateKeyByte) {
        this.privateKeyByte = privateKeyByte;
    }

    public byte[] getPublicKeyByte() {
        return publicKeyByte;
    }

    public void setPublicKeyByte(byte[] publicKeyByte) {
        this.publicKeyByte = publicKeyByte;
    }
}
