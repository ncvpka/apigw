/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.redis;

import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.FileInputStream;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author truonglq
 */
public class RedisService {
    
    public final static Logger logger = LogManager.getLogger(RedisService.class.getName());

    private static volatile RedisService instance;
    private static Object mutex = new Object();
    private static final String CONFIG_PATH = "../etc/redis-config.properties";

    private Map<String, String> allConfigs = new ConcurrentHashMap<String, String>();
    private RedisConfigInfo config;
    
    private JedisSentinelPool jedisPool;
       
    public static RedisService shared() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new RedisService();
                    instance.loadConfig();
                }
            }
        }
        return instance;
    }

    private void loadConfig() {
        logger.info("#loadConfig - Start");
        try {
            //Load config
            Properties prop = new Properties();
            prop.load(new FileInputStream(CONFIG_PATH));

            logger.info("#loadConfig - Load all key and value");
            Iterator<Object> keys = prop.keySet().iterator();
            while (keys.hasNext()) {
                String k = keys.next().toString();
                String v = prop.getProperty(k);
                allConfigs.put(k, v);
            }

            logger.info("#loadConfig - Init config");
            this.config = new RedisConfigInfo(allConfigs);

            logger.info("#loadConfig - Init pool");
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(config.getMaxClients());
            jedisPool = new JedisSentinelPool(config.getMonitorName(), config.getSentinels(), jedisPoolConfig, config.getPassword());
        } catch (Exception ex) {
            logger.error("#loadConfig - ERROR: ", ex);
        }
    }

    public String get(int dbIndex, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.auth(config.getPassword());
            jedis.select(dbIndex);
            return jedis.get(String.format("%s_%s", config.getPrefixApp(), key));
        } catch (JedisConnectionException jce) {
            logger.error("JEDIS RESOURCE EX: ", jce);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("RedisService.get exception: ", ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public void set(int dbIndex, String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.auth(config.getPassword());
            jedis.select(dbIndex);
            jedis.set(String.format("%s_%s", config.getPrefixApp(), key), value);
        } catch (JedisConnectionException jce) {
            logger.error("JEDIS RESOURCE EX: ", jce);
        } catch (Exception ex) {
            logger.error("RedisService.set exception: ", ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void jedisHmset(int dbIndex, String key, Map<String, String> value) {
        Jedis jedis = null;
        try {
            logger.info("#jedisHmset - Get connection");
            jedis = jedisPool.getResource();
            jedis.auth(config.getPassword());

            jedis.select(dbIndex);
            jedis.hmset(String.format("%s_%s", config.getPrefixApp(), key), value);
        } catch (Exception ex) {
            logger.error("#jedisHmset - Exception: ", ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Map<String, String> getRedisValueAsHashMap(int dbIndex, String key) throws Exception {
        Jedis jedis = null;
        try {
            logger.info("#getRedisValueAsHashMap - Get connection");
            jedis = jedisPool.getResource();
            jedis.auth(config.getPassword());

            jedis.select(dbIndex);
            return jedis.hgetAll(String.format("%s_%s", config.getPrefixApp(), key));
        } catch (Exception ex) {
            logger.info("#getRedisValueAsHashMap - Exception: ", ex);
            throw ex;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.auth(config.getPassword());
            return jedis.get(String.format("%s_%s", config.getPrefixApp(), key));
        } catch (JedisConnectionException jce) {
            logger.error("JEDIS RESOURCE EX: ", jce);
        } catch (Exception ex) {
            logger.error("RedisService.get exception: ", ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }
    
    public <T> T get(Class<T> clazz, String key) {
        Jedis jedis = null;
        try {
            logger.info("START RedisService.getresources");
            jedis = jedisPool.getResource();
            jedis.auth(config.getPassword());
            logger.info("END RedisService.getresources");
            String json = jedis.get(String.format("%s_%s", config.getPrefixApp(), key));
            if (json != null && !json.isEmpty()) {
                return new Gson().fromJson(json, clazz);
            }
        } catch (JedisConnectionException jce) {
            logger.error("JEDIS RESOURCE EX: ", jce);
        } catch (Exception ex) {
            logger.error("Exception RedisService.get with object: ", ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public <T> T get(Class<T> clazz, int dbIndex, String key) {
        Jedis jedis = null;
        try {
            logger.info("START RedisService.getresources");
            jedis = jedisPool.getResource();
            jedis.auth(config.getPassword());
            jedis.select(dbIndex);
            logger.info("END RedisService.getresources");
            String json = jedis.get(String.format("%s_%s", config.getPrefixApp(), key));
            if (json != null && !json.isEmpty()) {
                return new Gson().fromJson(json, clazz);
            }
        } catch (JedisConnectionException jce) {
            logger.error("JEDIS RESOURCE EX: ", jce);
        } catch (Exception ex) {
            logger.error("Exception RedisService.get with object: ", ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public <T> T getNoExcept(Class<T> clazz, String key) throws Exception {
        Jedis jedis = null;
        try {
            logger.info("START RedisService.getresources");
            jedis = jedisPool.getResource();
            jedis.auth(config.getPassword());
            logger.info("END RedisService.getresources");
            String json = jedis.get(String.format("%s_%s", config.getPrefixApp(), key));
            if (json != null && !json.isEmpty()) {
                return new Gson().fromJson(json, clazz);
            }
        } catch (JedisConnectionException jce) {
            logger.error("JEDIS RESOURCE EX: ", jce);
            throw jce;
        } catch (Exception ex) {
            logger.error("Exception RedisService.get with object: ", ex);
            throw ex;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }
    
    public <T> T getNoPrefix(Class<T> clazz, String key) {
        Jedis jedis = null;
        try {
            logger.info("START RedisService.getresources");
            jedis = jedisPool.getResource();
            jedis.auth(config.getPassword());
            logger.info("END RedisService.getresources");
            String json = jedis.get(key);
            if (json != null && !json.isEmpty()) {
                return new Gson().fromJson(json, clazz);
            }
        } catch (JedisConnectionException jce) {
            logger.error("JEDIS RESOURCE EX: ", jce);
        } catch (Exception ex) {
            logger.error("Exception RedisService.get with object: ", ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }
    
    public <T> T get(Type typeToken, String key) {
        Jedis jedis = null;
        try {
            logger.info("START RedisService.getresources");
            jedis = jedisPool.getResource();
            jedis.auth(config.getPassword());
            logger.info("END RedisService.getresources");
            String json = jedis.get(String.format("%s_%s", config.getPrefixApp(), key));
            if (json != null && !json.isEmpty()) {
                return new Gson().fromJson(json, typeToken);
            }
        } catch (JedisConnectionException jce) {
            logger.error("JEDIS RESOURCE EX: ", jce);
        } catch (Exception ex) {
            logger.error("Exception RedisService.get with object: ", ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }
    
    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.auth(config.getPassword());
            jedis.set(String.format("%s_%s", config.getPrefixApp(), key), value);
//            jedis.expire(String.format("%s_%s", REDIS_PREFIX, key), DEFAULT_EXPIRED);
        } catch (JedisConnectionException jce) {
            logger.error("JEDIS RESOURCE EX: ", jce);
        } catch (Exception ex) {
            logger.error("RedisService.set exception: ", ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void set(String key, String value, int expired) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.auth(config.getPassword());
            jedis.set(String.format("%s_%s", config.getPrefixApp(), key), value);
            jedis.expire(String.format("%s_%s", config.getPrefixApp(), key), expired);
        } catch (JedisConnectionException jce) {
            logger.error("JEDIS RESOURCE EX: ", jce);
        } catch (Exception ex) {
            logger.error("RedisService.set exception: ", ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
    
    public void set(int dbIndex, String key, Object obj, long expired) {
        Jedis jedis = null;
        try {
            String json = new Gson().toJson(obj);

            jedis = jedisPool.getResource();
            jedis.auth(config.getPassword());
            jedis.select(dbIndex);

            jedis.set(String.format("%s_%s", config.getPrefixApp(), key), json);
            jedis.expire(String.format("%s_%s", config.getPrefixApp(), key), (int) expired);
        } catch (JedisConnectionException jce) {
            logger.error("JEDIS RESOURCE EX: ", jce);
        } catch (Exception ex) {
            logger.error("RedisService.set exception: ", ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
    
    public void set(String key, Object obj) {
        Jedis jedis = null;
        try {
            String json = new Gson().toJson(obj);
            jedis = jedisPool.getResource();
            jedis.auth(config.getPassword());
            jedis.set(String.format("%s_%s", config.getPrefixApp(), key), json);
        } catch (JedisConnectionException jce) {
            logger.error("JEDIS RESOURCE EX: ", jce);
        } catch (Exception ex) {
            logger.error("Exception RedisService.set: ", ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void set(int dbIndex, String key, Object obj) {
        Jedis jedis = null;
        try {
            String json = new Gson().toJson(obj);
            jedis = jedisPool.getResource();
            jedis.auth(config.getPassword());
            jedis.select(dbIndex);
            jedis.set(String.format("%s_%s", config.getPrefixApp(), key), json);
        } catch (JedisConnectionException jce) {
            logger.error("JEDIS RESOURCE EX: ", jce);
        } catch (Exception ex) {
            logger.error("Exception RedisService.set: ", ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
    
    public void set(String key, Object obj, int expired) {
        Jedis jedis = null;
        try {
            String json = new Gson().toJson(obj);
            logger.info("################## expired: " + expired);
            //logger.info(json);
            jedis = jedisPool.getResource();
            jedis.auth(config.getPassword());
            jedis.set(String.format("%s_%s", config.getPrefixApp(), key), json);
            jedis.expire(String.format("%s_%s", config.getPrefixApp(), key), expired);
        } catch (JedisConnectionException jce) {
            logger.error("JEDIS RESOURCE EX: ", jce);
        } catch (Exception ex) {
            logger.error("Exception RedisService.set: ", ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
    
    public void setNoPrefix(String key, Object obj, int expired) {
        Jedis jedis = null;
        try {
            String json = new Gson().toJson(obj);
            logger.info("################## expired: " + expired);
            //logger.info(json);
            jedis = jedisPool.getResource();
            jedis.auth(config.getPassword());
            jedis.set(key, json);
            jedis.expire(key, expired);
        } catch (JedisConnectionException jce) {
            logger.error("#setNoPrefix - JEDIS RESOURCE EX: ", jce);
        } catch (Exception ex) {
            logger.error("#setNoPrefix - Exception: ", ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
    
    public void remove(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.auth(config.getPassword());
            jedis.del(String.format("%s_%s", config.getPrefixApp(), key));
        } catch (JedisConnectionException jce) {
            logger.error("#remove - JEDIS RESOURCE EX: ", jce);
        } catch (Exception ex) {
            logger.error("#remove - Exception: ", ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void remove(int dbIndex, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.auth(config.getPassword());
            jedis.select(dbIndex);
            jedis.del(String.format("%s_%s", config.getPrefixApp(), key));
        } catch (JedisConnectionException jce) {
            logger.error("#remove - JEDIS RESOURCE EX: ", jce);
        } catch (Exception ex) {
            logger.error("#remove - Exception: ", ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
