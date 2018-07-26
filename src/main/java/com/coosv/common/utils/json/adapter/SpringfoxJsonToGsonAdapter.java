package com.coosv.common.utils.json.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import springfox.documentation.spring.web.json.Json;

/**
 * @Description 主要用于swagger,因为本身spring数据处理不支持json,只支持jackjson
 * @author fanglei.lu
 * @date 2018年7月25日
 */
public class SpringfoxJsonToGsonAdapter implements JsonSerializer<Json>{

	/* (non-Javadoc)
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(Json json, Type type, JsonSerializationContext jsonSerializationContext) {
		// TODO Auto-generated method stub
		JsonParser parse = new JsonParser();
		return parse.parse(json.value());
	}

}
