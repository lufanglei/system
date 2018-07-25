package com.coosv.common.pojo.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.coosv.common.pojo.base.entity.BaseEntity;


/**
 * The persistent class for the coosv_sys_permission database table.
 * 
 */
public class Permission extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private Date createDate;

	private String creater;

	private String resourceId;

	private String roleId;

	private Date updateDate;

	private String updater;

	public Permission() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdater() {
		return this.updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

}