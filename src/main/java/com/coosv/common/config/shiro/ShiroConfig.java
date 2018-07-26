package com.coosv.common.config.shiro;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coosv.common.config.shiro.coosv.CoosvAuthRealm;
import com.coosv.common.config.shiro.coosv.CredentialsMatcher;
import com.coosv.common.utils.string.StringUtils;
/**
 * 
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年5月30日
 * 
 */
@Configuration
public class ShiroConfig {
	
	@Value("${shiro.session.prefix:shiro}")
	private String prefix;
	@Value("${shiro.filter:true}")
	private boolean shiroIsFilter;
	
	@Bean(name="shiroCacheManager")
	public RedisCacheManager shiroCacheManager(@Qualifier("shiroRedisManager")RedisManager shiroRedisManager) {
		RedisCacheManager cacheManager = new RedisCacheManager();
		cacheManager.setRedisManager(shiroRedisManager);
		cacheManager.setExpire(1800);
		cacheManager.setKeyPrefix(prefix+":cache:");
		return cacheManager;
	}
	
	@Bean(name="shiroRedisSessionDAO")
	public RedisSessionDAO shiroRedisSessionDAO(@Qualifier("shiroRedisManager")RedisManager shiroRedisManager) {
		RedisSessionDAO dao = new RedisSessionDAO();
		dao.setRedisManager(shiroRedisManager);
		dao.setExpire(1800);
		dao.setKeyPrefix(prefix+":session:");
		dao.setSessionIdGenerator(new SessionIdGenerator() {
			@Override
			public Serializable generateId(Session session) {
				// TODO Auto-generated method stub
				return StringUtils.uuid();
			}
		});
		return dao;
	}
	
	@Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
		ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //配置登录的url和登录成功的url
        bean.setLoginUrl("/authentication");
        
        //未授权界面;
        bean.setUnauthorizedUrl("/authorization");

        //配置访问权限
        Map<String, String> filterChainDefinitionMap = new HashMap<String, String>(10);
        if(shiroIsFilter) {
        	// 配置不会被拦截的链接 顺序判断
            filterChainDefinitionMap.put("/token", "anon");
            
            //配置swagger不被拦截
            filterChainDefinitionMap.put("/swagger-ui.html", "anon");
            filterChainDefinitionMap.put("/swagger-resources", "anon");
            filterChainDefinitionMap.put("/swagger-resources/**", "anon");
            filterChainDefinitionMap.put("/v2/api-docs", "anon");
            filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
            
            //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
            filterChainDefinitionMap.put("/logout", "logout");
            filterChainDefinitionMap.put("/auth/permission", "authc");
            filterChainDefinitionMap.put("/auth/menu/curuser", "authc");
            filterChainDefinitionMap.put("/*", "user");
            filterChainDefinitionMap.put("/**", "user");
            
        }else {
        	filterChainDefinitionMap.put("/*", "anon");
            filterChainDefinitionMap.put("/**", "anon");
        }
        
        
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }
	
	@Bean(name="shiroSessionManager")
	public SessionManager shiroSessionManager(@Qualifier("shiroRedisSessionDAO")RedisSessionDAO shiroRedisSessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(shiroRedisSessionDAO);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }
	
    /**
     * 配置核心安全事务管理器
     * @param authRealm
     * @param shiroSessionManager
     * @param shiroCacheManager
     * @return
     */
    @Bean(name="securityManager")
    public SecurityManager securityManager(@Qualifier("coosvAuthRealm") CoosvAuthRealm authRealm,@Qualifier("credentialsMatcher") CredentialsMatcher matcher,@Qualifier("shiroSessionManager") SessionManager shiroSessionManager,@Qualifier("shiroCacheManager") RedisCacheManager shiroCacheManager) {
        DefaultWebSecurityManager manager=new MyDefaultWebSecurityManager();
        authRealm.setCredentialsMatcher(matcher);
        manager.setRealm(authRealm);
        manager.setSessionManager(shiroSessionManager);
        manager.setCacheManager(shiroCacheManager);
        
        return manager;
    }

    @Bean(name="credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }
    
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
}
