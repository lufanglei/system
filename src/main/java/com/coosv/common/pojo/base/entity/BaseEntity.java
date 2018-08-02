package com.coosv.common.pojo.base.entity;

import java.io.Serializable;
import java.util.Date;

import com.coosv.common.exception.DefaultException;
import com.coosv.common.utils.session.SessionUtils;
import com.coosv.common.utils.string.StringUtils;
import com.coosv.system.user.entity.User;

public class BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String id;
	
	private User creater;

    private Date createDate;

    private User updater;

    private Date updateDate;

    private String tenant;

    private Boolean del;
    
    private String remark;
	
	public void preSave()throws Exception {
		Date date = new Date();
		this.setId(StringUtils.uuid());
		this.setCreater(SessionUtils.getCurrentUser());
		this.setCreateDate(date);
		this.setUpdater(SessionUtils.getCurrentUser());
		this.setUpdateDate(date);
		this.setDel(false);
		this.setTenant(tenant);
	}
	
	public void preUpdate()throws Exception {
		if(StringUtils.isBlank(this.getId())) {
			throw new DefaultException("更新数据请设置主键");
		}
		Date date = new Date();
		this.setUpdater(SessionUtils.getCurrentUser());
		this.setUpdateDate(date);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public Boolean getDel() {
		return del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	public User getCreater() {
		return creater;
	}

	public void setCreater(User creater) {
		this.creater = creater;
	}

	public User getUpdater() {
		return updater;
	}

	public void setUpdater(User updater) {
		this.updater = updater;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
