package com.coosv.common.utils.json;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

/**
 * @Description Json工具类
 * @author fanglei.lu
 * @date 2018年4月24日
 */
public class GsonUtils {
	
	/**
	 * 把json解析成对象
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T parseObject(String json, Class<T> clazz) {
        T t = null;
        try {
        	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create(); 
            t = gson.fromJson(json, clazz);
        } catch (Exception e) {
            return null;
        }
        return t;
    }
	
	/**
	 * 把json解析成List
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> parseList(String json, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(json,clazz);
        return Arrays.asList(arr);
    }
	
	/**
	 * 把json 解析成map
	 * @param json
	 * @return
	 */
	public static Map<String, Object> parseMap(String json) {
        Map<String, Object> ans = null;
        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
            ans = gson.fromJson(json,new TypeToken<Map<String, Object>>() {}.getType());
        } catch (Exception e) {
            return null;
        }
        return ans;
    }
	
	/**
	 * 把对象封装成json
	 * @param obj
	 * @return
	 */
	public static String getJson(Object obj){
		
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(obj);
        return json;
    }
	/**
	 * 获取gson对象
	 * @return
	 */
	public static Gson getGson(){
        Gson gson = new GsonBuilder().create();
        return gson;
    }
	
}
