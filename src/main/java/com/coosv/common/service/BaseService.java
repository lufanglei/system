package com.coosv.common.service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.coosv.common.dao.BaseDao;
import com.coosv.common.exception.DefaultException;
import com.coosv.common.pojo.base.entity.BaseEntity;
import com.coosv.common.utils.string.StringUtils;
import com.coosv.common.web.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年7月26日
 */
public abstract class BaseService<D extends BaseDao<T>,T extends BaseEntity> {
	@Autowired
	protected D dao;
	
	public int save(T entity)throws Exception {
		entity.preSave();
		return dao.save(entity);
	}
	
	public int delete(String id)throws Exception {
		if(StringUtils.isBlank(id)) {
			throw new DefaultException("根据id删除数据请指定id");
		}
		return dao.delete(id);
	}
	
	public int update(T entity)throws Exception {
		entity.preUpdate();
		return dao.update(entity);
	}
	
	public T get(String id)throws Exception {
		if(StringUtils.isBlank(id)) {
			throw new DefaultException("根据id获取数据请指定id");
		}
		return dao.get(id);
	}
	
	public List<T> findList(T entity){
		return dao.findList(entity);
	}
	
	public Page findPage(Page page,T entity){
		PageHelper.startPage(page.getPageNo(),page.getPageSize());
		List<T> list = dao.findList(entity);
		PageInfo<T> pageInfo = new PageInfo<T>(list);
		page.setList(pageInfo.getList());
		page.setTotal(pageInfo.getTotal());
		return page;
	}
	
	public int count(T entity) {
		return dao.count(entity);
	}
	
}
