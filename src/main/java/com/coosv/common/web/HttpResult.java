package com.coosv.common.web;

/**
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年7月26日
 */
public class HttpResult {
	private String code;
	private String message;
	private Object data;
	

	public HttpResult(HttpCode httpCode) {
		this.setCode(httpCode);
	}


	public HttpResult() {
		this.setCode(HttpCode.OK);
	}

	public String getCode() {
		return code;
	}
	public void setCode(HttpCode httpCode) {
		this.code = httpCode.getCode();
		this.message = httpCode.getMessage();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
