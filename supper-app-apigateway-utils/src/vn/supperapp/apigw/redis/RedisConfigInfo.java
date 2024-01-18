/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.redis;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author truonglq
 */
public class RedisConfigInfo {
    private Set<String> sentinels;
    private String monitorName;
    private String password;
    private long defaultExpired = 1800;
    private String prefixApp;
    private int maxClients = 2000;
    private int defaultDbIndex = 0;

    public RedisConfigInfo() {
    }

    public RedisConfigInfo(Map<String, String> configs) {
        String tmp = configs.get("redis.sentinels");
        if (tmp != null && !"".equals(tmp.trim())) {
            String[] tmps = tmp.split("\\|");
            sentinels = new HashSet<String>(Arrays.asList(tmps));
        }

        tmp = configs.get("redis.monitor-name");
        this.monitorName = tmp != null ? tmp.trim() : null;

        tmp = configs.get("redis.pass");
        this.password = tmp != null ? tmp.trim() : null;

        tmp = configs.get("redis.max-clients");
        try {
            this.maxClients = Integer.valueOf(tmp);
        } catch (Exception ex) {
            ex.printStackTrace();
            this.maxClients = 2000;
        }

        tmp = configs.get("redis.default-expired");
        try {
            this.defaultExpired = Long.valueOf(tmp);
        } catch (Exception ex) {
            ex.printStackTrace();
            this.defaultExpired = 1800;
        }

        tmp = configs.get("redis.prefix-app");
        this.prefixApp = tmp != null ? tmp.trim() : "";

        tmp = configs.get("redis.default-db-index");
        try {
            this.defaultDbIndex = Integer.valueOf(tmp);
        } catch (Exception ex) {
            ex.printStackTrace();
            this.defaultDbIndex = 0;
        }

    }

    public Set<String> getSentinels() {
        return sentinels;
    }

    public void setSentinels(Set<String> sentinels) {
        this.sentinels = sentinels;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getDefaultExpired() {
        return defaultExpired;
    }

    public void setDefaultExpired(long defaultExpired) {
        this.defaultExpired = defaultExpired;
    }

    public String getPrefixApp() {
        return prefixApp;
    }

    public void setPrefixApp(String prefixApp) {
        this.prefixApp = prefixApp;
    }

    public int getMaxClients() {
        return maxClients;
    }

    public void setMaxClients(int maxClients) {
        this.maxClients = maxClients;
    }

    public int getDefaultDbIndex() {
        return defaultDbIndex;
    }

    public void setDefaultDbIndex(int defaultDbIndex) {
        this.defaultDbIndex = defaultDbIndex;
    }
}
