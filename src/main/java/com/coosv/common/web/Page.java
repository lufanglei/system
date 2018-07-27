package com.coosv.common.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.coosv.common.utils.string.StringUtils;

/**
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年7月26日
 */
public class Page {
	
	private static final int PAGE_SIZE = 10;
	
	/**
	 * 页码
	 */
	private int pageNo;
	/**
	 * 分页大小
	 */
	private int pageSize;
	/**
	 * 排序字段
	 */
	private String order;
	/**
	 * 排序方向
	 */
	private String direction;
	
	private List list;
	
	private Long total;
	
	
	
	/**
	 * @param pageNo
	 */
	public Page(HttpServletRequest request) {
		String pageNo_str = request.getParameter("pageNo");
		String pageSize_str = request.getParameter("pageSize");
		String order = request.getParameter("order");
		String direction = request.getParameter("direction");
		
		if(StringUtils.isNotBlank(pageNo_str)) {
			this.pageNo = Integer.parseInt(pageNo_str);
			
		}else {
			this.pageNo = 1 ;
		}
		
		if(StringUtils.isNotBlank(pageSize_str)) {
			this.pageSize = Integer.parseInt(pageSize_str);
		}else {
			this.pageSize = 10 ;
		}
		
		if("asc".equals(direction)) {
			this.direction = "asc";
		}else {
			this.direction = "desc";
		}
	}
	/**
	 * @param pageNo
	 * @param pageSize
	 * @param order
	 */
	public Page(int pageNo, int pageSize, String order) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.order = order;
	}
	/**
	 * @param pageNo
	 * @param pageSize
	 */
	public Page(int pageNo, int pageSize) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	/**
	 * @param pageNo
	 * @param pageSize
	 * @param order
	 * @param direction
	 */
	public Page(int pageNo, int pageSize, String order, String direction) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.order = order;
		this.direction = direction;
	}
	public int getPageNo() {
		return pageNo<1?1:pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize==0?PAGE_SIZE:pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String isDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}

	
}
