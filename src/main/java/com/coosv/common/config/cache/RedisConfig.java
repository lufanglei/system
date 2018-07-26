package com.coosv.common.config.cache;

import org.apache.commons.lang3.StringUtils;
import org.crazycake.shiro.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年7月26日
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{
	
	
	@Value("${spring.redis.host:127.0.0.1}")
    private String host;
    
    @Value("${spring.redis.port:6379}")
	private Integer port;
    
    @Value("${spring.redis.password:}")
	private String password;
	
	@Value("${spring.redis.timeout:30000}")
	private Integer timeout;
	
	@Value("${spring.redis.database:0}")
	private Integer database;
	
	@Bean(name="shiroRedisManager")
	public RedisManager shiroRedisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(host+":"+port);
		redisManager.setDatabase(database);
		if(StringUtils.isNotBlank(password)) {
			redisManager.setPassword(password);
		}
		redisManager.setTimeout(timeout);
		return redisManager;
	}
	
    @Bean 
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheManager cacheManager = RedisCacheManager.create(factory);
        return cacheManager;
    }

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        RedisSerializer<String> keySerializer = new StringRedisSerializer();
        RedisSerializer<Object> valueSerializer = new JdkSerializationRedisSerializer();
        template.setKeySerializer(keySerializer);
        template.setValueSerializer(valueSerializer);
        template.setHashKeySerializer(keySerializer);
        template.setHashValueSerializer(valueSerializer);
        template.afterPropertiesSet();
        return template;
	}

    
}
