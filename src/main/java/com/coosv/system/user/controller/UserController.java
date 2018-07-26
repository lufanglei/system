package com.coosv.system.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coosv.common.web.HttpCode;
import com.coosv.common.web.HttpResult;
import com.coosv.system.user.entity.User;
import com.coosv.system.user.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年7月26日
 */
@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "获取用户", notes = "根据id获取用户")
	@ApiImplicitParam(name = "userid", value = "用户id", paramType = "query", required = true, dataType = "String")
	@RequestMapping(value="/get",method = RequestMethod.GET)
	public HttpResult test(String userid) {
		HttpResult result = new HttpResult();
		try {
			User user = userService.get(new User(userid));
			result.setData(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(HttpCode.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
}
