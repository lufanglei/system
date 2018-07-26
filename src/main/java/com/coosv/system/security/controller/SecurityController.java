package com.coosv.system.security.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coosv.common.web.HttpCode;
import com.coosv.common.web.HttpResult;
import com.coosv.system.user.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年7月26日
 */
public class SecurityController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "用户认证", notes = "通过用户名密码获取以及记住状态用户token")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "用户名", paramType = "query", required = true, dataType = "String")
			,@ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true, dataType = "String")
			,@ApiImplicitParam(name = "rememberMe", value = "记住我", paramType = "query", required = false, dataType = "boolean")
	})
	@RequestMapping(value = "/token", method = {RequestMethod.POST,RequestMethod.GET})
	public HttpResult token(String username,String password,boolean rememberMe) {
		HttpResult result = new HttpResult();
		try {
			
			Subject subject = SecurityUtils.getSubject();
			
			if(!subject.isAuthenticated()) {
				UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);
				subject.login(token);
			}

			result.setData(subject.getSession().getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setCode(HttpCode.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@ApiOperation(value = "用户退出", notes = "清除用户session信息以及缓存信息")
	@RequestMapping(value = "/logout", method = {RequestMethod.POST,RequestMethod.GET})
	public HttpResult logout() {
		HttpResult result = new HttpResult();
		try {
			
			Subject subject = SecurityUtils.getSubject();
			subject.logout();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			result.setCode(HttpCode.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/authentication", method = RequestMethod.GET)
	public HttpResult authentication() {
		return new HttpResult(HttpCode.UNAUTHORIZED);
	}
}
