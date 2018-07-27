package com.coosv.system.user.dao;

import com.coosv.common.annotation.MyBatisDao;
import com.coosv.common.dao.BaseDao;
import com.coosv.system.user.entity.User;

@MyBatisDao
public interface UserDao extends BaseDao<User>{
	public User getUserByUsername(String username);
}