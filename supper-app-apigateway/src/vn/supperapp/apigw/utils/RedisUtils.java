/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.utils;

import org.apache.log4j.Logger;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;

/**
 * @author TruongLe
 */
public class RedisUtils {


    private static final Logger logger = Logger.getLogger(RedisUtils.class);
    private static volatile RedisUtils instance;

    public static RedisUtils getInstance() {
        if (instance == null) {
            synchronized (RedisUtils.class) {
                if (instance == null) {
                    instance = new RedisUtils();
                }
            }
        }
        return instance;
    }

    public RedisUtils()  {

    }

    public JedisCluster createJedis() {
        JedisCluster jedis = WalletRedisPoolManage.getInstance().createJedis();
        logger.info("======Connected to Redis successful!");
        return jedis;
    }


    public String getKey(String key) {
        JedisCluster jedis = null;
        String result = null;
        try {
            jedis = createJedis();
            result = jedis.get(key);
        } catch (Exception ex) {
            logger.error(ex, ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    public void setKey(String key, String value) {
        JedisCluster jedis = null;
        try {
            jedis = createJedis();
            jedis.set(key, value);
        } catch (Exception ex) {
            logger.error(ex, ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void setKey(String key, String value, Integer expireTime) {
        JedisCluster jedis = null;
        try {
            jedis = createJedis();
            jedis.set(key, value);
            jedis.expire(key, expireTime);
        } catch (Exception ex) {
            logger.error(ex, ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

    public void delKey(String key) {
        JedisCluster jedis = null;
        try {
            jedis = createJedis();
            jedis.del(key);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }
}
