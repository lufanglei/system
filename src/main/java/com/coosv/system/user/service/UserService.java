package com.coosv.system.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coosv.common.service.BaseService;
import com.coosv.system.user.dao.UserDao;
import com.coosv.system.user.entity.User;

/**
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年7月26日
 */
@Service
public class UserService extends BaseService<UserDao, User> {
	
	@Autowired
	private UserDao userDao;
	
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}
}
