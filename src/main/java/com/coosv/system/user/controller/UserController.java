package com.coosv.system.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coosv.common.web.HttpCode;
import com.coosv.common.web.HttpResult;
import com.coosv.system.user.entity.User;
import com.coosv.system.user.service.UserService;

/**
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年7月26日
 */
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="test")
	public HttpResult test() {
		HttpResult result = new HttpResult();
		try {
			List<User> userList = userService.findList(null);
			result.setData(userList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(HttpCode.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
}
