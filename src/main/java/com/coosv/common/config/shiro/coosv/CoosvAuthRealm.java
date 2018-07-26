package com.coosv.common.config.shiro.coosv;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.coosv.system.user.entity.User;

/**
 * 
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年5月30日
 */
public class CoosvAuthRealm extends AuthorizingRealm{
	
	
	@Override
	protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		if (principals == null) {
            return null;
        }
		
        AuthorizationInfo info = null;

        
        
        Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession(false);
		if (session == null){
			session = subject.getSession();
		}
		info = (AuthorizationInfo)session.getAttribute("authInfo");

        return info;
	}


	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
	    UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
	    User userInfo = new User();
	    return new SimpleAuthenticationInfo(userInfo, "123456", getName());
	}



	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

}
