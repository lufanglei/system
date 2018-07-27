package com.coosv.common.dao;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年7月26日
 */
public interface BaseDao<T> {
	/**
	 * 保存对象
	 * @param entity
	 * @return
	 */
	public int save(T entity);
	
	/**
	 * 删除数据
	 * @param entity
	 * @return
	 */
	public int delete(String id);
	
	/**
	 * 更新一条数据
	 * @param entity
	 * @return
	 */
	public int update(T entity);
	
	/**
	 * 获取一条数据
	 * @param id
	 * @return
	 */
	public T get(String id);
	
	/**
	 * 获取一个列表
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);
	
	/**
	 * 按条件统计
	 * @param entity
	 * @return
	 */
	public int count(T entity);
}
