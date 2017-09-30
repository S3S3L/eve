/** 
 * Project Name:eve-server 
 * File Name:CommonComponent.java 
 * Package Name:com.s3s3l.eve.component 
 * Date:Sep 8, 201712:00:44 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.component;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Pool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.s3s3l.data.cache.CacheHelper;
import com.s3s3l.data.cache.RedisCacheChecker;
import com.s3s3l.data.cache.caffeine.CaffeineCacheHelper;
import com.s3s3l.eve.configuration.ESIConfiguration;
import com.s3s3l.eve.handler.GlobalizationHelper;
import com.s3s3l.http.HttpUtil;
import com.s3s3l.redis.JedisUtil;
import com.s3s3l.redis.RedisHandler;
import com.s3s3l.resource.JacksonUtil;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <p>
 * </p>
 * ClassName:CommonComponent <br>
 * Date: Sep 8, 2017 12:00:44 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component
public class CommonComponent {

    @Bean
    public HttpUtil httpUtil() {
        return new HttpUtil();
    }

    @Bean(name = "expireCache")
    public Cache<String, String> expireCache() {
        return CacheBuilder.newBuilder()
                .concurrencyLevel(4)
                .weakKeys()
                .maximumSize(Integer.MAX_VALUE)
                .expireAfterWrite(600, TimeUnit.SECONDS)
                .build();
    }

    @Primary
    @Bean
    public Cache<String, String> commonCache() {
        return CacheBuilder.newBuilder()
                .concurrencyLevel(4)
                .weakKeys()
                .maximumSize(Integer.MAX_VALUE)
                .build();
    }

    @Bean
    public RedisHandler jedisUtils(RedisTemplate<String, String> redisTemplate, RedisProperties redisProp) {
        return JedisUtil.create(redisTemplate, getJedisPool(redisProp), JacksonUtil.defaultHelper);
    }

    @Bean
    public RedisTemplate<String, String> stringRedisTemplate(JedisConnectionFactory connectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        template.setConnectionFactory(connectionFactory);
        template.afterPropertiesSet();

        return template;
    }

    @Bean
    public CacheHelper<String, Object> localCache(RedisHandler redis) {
        return new CaffeineCacheHelper<String, Object>(() -> {
            return Caffeine.newBuilder()
                    .build(key -> {
                        return redis.get(key);
                    });
        }, new RedisCacheChecker(redis));
    }

    @Bean
    public GlobalizationHelper globalizationHelper(ESIConfiguration esiConfiguration) {
        return new GlobalizationHelper(esiConfiguration);
    }

    private JedisPool getJedisPool(RedisProperties redisProp) {
        if (redisProp == null) {
            return null;
        }
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        Pool pool = redisProp.getPool();
        poolConfig.setMaxTotal(pool.getMaxActive());
        poolConfig.setMaxIdle(pool.getMaxIdle());
        poolConfig.setMaxWaitMillis(pool.getMaxWait());
        JedisPool jedisPool = new JedisPool(poolConfig, redisProp.getHost(), redisProp.getPort(),
                redisProp.getTimeout(), redisProp.getPassword(), redisProp.getDatabase());
        return jedisPool;
    }
}
