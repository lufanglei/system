package com.coosv.system.organization.entity;

import java.util.Date;

import com.coosv.common.pojo.base.entity.TreeEntity;

public class Organization extends TreeEntity{
	
	public static final int TYPE_SUPER = 0;
	public static final int TYPE_COMPANY = 1;
	public static final int TYPE_DEPARTMENT = 2;
	
	private String code;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}