package com.coosv.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.coosv.common.dao.BaseDao;
import com.coosv.common.pojo.base.entity.BaseEntity;

/**
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年7月26日
 */
public abstract class BaseService<D extends BaseDao<T>,T> {
	@Autowired
	protected D dao;
	
	public int save(T entity) {
		return dao.save(entity);
	}
	
	public int delete(String id) {
		return dao.delete(id);
	}
	public int delete(T entity) {
		return dao.delete(entity);
	}
	
	public int update(T entity) {
		return dao.update(entity);
	}
	
	public T get(String id) {
		return dao.get(id);
	}
	
	public T get(T entity) {
		return dao.get(entity);
	}
	
	public List<T> findList(T entity){
		return dao.findList(entity);
	}
	
	
}
