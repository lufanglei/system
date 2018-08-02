package com.coosv.system.resource.entity;

import java.util.Date;

import com.coosv.common.pojo.base.entity.BaseEntity;
import com.coosv.common.pojo.base.entity.TreeEntity;

public class Resource extends TreeEntity{

    private String url;

    private String code;

    private Integer type;



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}