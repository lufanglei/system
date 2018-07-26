package com.coosv.common.utils.string;

import java.util.UUID;

/**
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年7月26日
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
