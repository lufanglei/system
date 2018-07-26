package com.coosv.common.config.shiro;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.apache.shiro.web.util.WebUtils;

/**
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年4月16日
 */
public class MyDefaultWebSecurityManager extends DefaultWebSecurityManager{
	
	
	private static final String AUTHORIZATION = "Authorization";  

	@Override
	protected SessionKey getSessionKey(SubjectContext context) {
		// TODO Auto-generated method stub
		if (WebUtils.isWeb(context)) {
			ServletRequest request = WebUtils.getRequest(context);
            ServletResponse response = WebUtils.getResponse(context);
            HttpServletRequest httpreq = WebUtils.toHttp(request);
            Serializable sessionId = httpreq.getHeader(AUTHORIZATION);
  
            if(sessionId==null) {
            	sessionId = context.getSessionId();
            }
            return new WebSessionKey(sessionId, request, response);
        } else {
            return super.getSessionKey(context);

        }
	}



}
