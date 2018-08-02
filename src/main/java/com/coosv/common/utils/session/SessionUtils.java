package com.coosv.common.utils.session;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.coosv.common.config.shiro.coosv.Principal;
import com.coosv.system.user.entity.User;

public class SessionUtils {
	public static User getCurrentUser() {
		User user = new User();
		Principal principal = SessionUtils.getPrincipal();
		user.setUsername(principal.getUsername());
		user.setId(principal.getId());
		return user;
	}
	
	public static Principal getPrincipal() {
		Principal principal = (Principal)SecurityUtils.getSubject().getPrincipal();
		return principal;
	}
	
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession(); 
	}
}
