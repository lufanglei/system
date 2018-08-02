package com.coosv.system.resource.dao;

import com.coosv.system.resource.entity.Resource;

public interface ResourceDao {
    int deleteByPrimaryKey(String id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
}