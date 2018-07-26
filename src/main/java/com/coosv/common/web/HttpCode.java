package com.coosv.common.web;

/**
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年7月26日
 */
public enum HttpCode {
	OK("200","操作成功"),
	BAD_REQUEST("400","错误请求-请求参数有误"),
	UNAUTHORIZED("401", "未授权的访问"),
	INTERNAL_SERVER_ERROR("500", "服务器未知异常");
	
	private String code;
	private String message;
	private HttpCode(String code,String message) {
        this.code = code;
        this.message = message;
    }
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
}
