package com.coosv.system.organization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coosv.common.web.HttpCode;
import com.coosv.common.web.HttpResult;
import com.coosv.system.organization.entity.Organization;
import com.coosv.system.organization.service.OrganizationService;
import com.coosv.system.user.entity.User;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/organization")
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;
	
	@ApiOperation(value = "获取机构", notes = "获取机构")
	@RequestMapping(value="/get",method = RequestMethod.GET)
	public HttpResult get(String id) {
		HttpResult result = new HttpResult();
		try {
			Organization organization = organizationService.get(id);
			result.setData(organization);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(HttpCode.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@ApiOperation(value = "获取机构列表", notes = "获取机构列表")
	@RequestMapping(value="/findList",method = RequestMethod.GET)
	public HttpResult findList() {
		HttpResult result = new HttpResult();
		try {
			result.setData(organizationService.findList(null));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(HttpCode.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@ApiOperation(value = "添加机构", notes = "添加机构")
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public HttpResult save(Organization organization) {
		HttpResult result = new HttpResult();
		try {
			int count = organizationService.save(organization);
			result.setData(count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(HttpCode.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
}
