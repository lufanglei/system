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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coosv.common.utils.string.StringUtils;
import com.coosv.system.user.entity.User;
import com.coosv.system.user.service.UserService;

/**
 * 
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年5月30日
 */
@Component
public class CoosvAuthRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;
	
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
	    if(usernamePasswordToken.getPassword() == null || StringUtils.isBlank(usernamePasswordToken.getUsername())) {
	    	throw new AuthenticationException("请输入用户名或密码。");
	    }
	    
	    User tempUser = new User();
	    tempUser.setUsername(usernamePasswordToken.getUsername());
	    
	    User user = userService.get(tempUser);
	    if(user == null) {
	    	throw new AuthenticationException("系统无此用户。");
	    }
	    return new SimpleAuthenticationInfo(new Principal(user.getId(),user.getUsername()), user.getPassword(), getName());
	}



	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

}
