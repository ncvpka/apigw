package vn.supperapp.apigw.utils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class WalletRedisPoolManage {
    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    public static final String REDIS = "../etc/redis-db.properties";

    private String ListHost;
    private String redisPass;
    private Integer redisReadTimeout;


    private Set<HostAndPort> jedisClusterNode;
    private Object objectSync = new Object();

    private static WalletRedisPoolManage instance;


    public static WalletRedisPoolManage getInstance() {
        if (instance == null) {
            synchronized (WalletRedisPoolManage.class) {
                if (instance == null) {
                    instance = new WalletRedisPoolManage();
                }
            }
        }
        return instance;
    }


    public void setConfig() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(REDIS));
            setRedisHost(properties.getProperty("Redis.host"));
            setRedisPass(properties.getProperty("Redis.password"));
            String strTimeout = properties.getProperty("Redis.readTimeout");
            strTimeout = strTimeout == null ? "2000" : strTimeout;
            setRedisReadTimeout(Integer.parseInt(strTimeout));

        } catch (Exception e) {
            logger.error("service language ", e);
        }
        logger.info("Get application config success.");

    }

    public WalletRedisPoolManage() {
        setConfig();
        createJedisClusterNode();
    }

    public JedisCluster createJedis() {
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNode, redisReadTimeout, 0, 10, redisPass, buildPoolConfig());
        return jedisCluster;
    }

    private void createJedisClusterNode() {
        jedisClusterNode = new HashSet<>();
        try {
            if (ListHost != null) {
                String[] list = ListHost.split(";");
                for (String elem : list) {
                    if (elem != null) {
                        String[] listElem = elem.split(":");
                        jedisClusterNode.add(new HostAndPort(listElem[0], Integer.parseInt(listElem[1])));
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }


    }


    private GenericObjectPoolConfig buildPoolConfig() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxWaitMillis(redisReadTimeout);
        poolConfig.setMaxTotal(1000);
        return poolConfig;
    }


    public String getRedisPass() {
        return redisPass;
    }

    public void setRedisPass(String redisPass) {
        this.redisPass = redisPass;
    }

    public String getRedisHost() {
        return ListHost;
    }

    public void setRedisHost(String redisHost) {
        this.ListHost = redisHost;
    }

//    public Integer getRedisPort() {
//        return redisPort;
//    }
//
//    public void setRedisPort(Integer redisPort) {
//        this.redisPort = redisPort;
//    }

    public Integer getRedisReadTimeout() {
        return redisReadTimeout;
    }

    public void setRedisReadTimeout(Integer redisReadTimeout) {
        this.redisReadTimeout = redisReadTimeout;
    }

}
