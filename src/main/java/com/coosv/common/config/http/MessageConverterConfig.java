package com.coosv.common.config.http;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.coosv.common.utils.json.adapter.DateTypeAdapter;
import com.coosv.common.utils.json.adapter.SpringfoxJsonToGsonAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import springfox.documentation.spring.web.json.Json;

/**
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年6月13日
 */
@Configuration
public class MessageConverterConfig {
	
	
	@Bean
    public HttpMessageConverters converters(){
        List<MediaType> list = new ArrayList<MediaType>();
        list.add(MediaType.TEXT_HTML);
        list.add(MediaType.APPLICATION_JSON_UTF8);
        list.add(MediaType.TEXT_PLAIN);
        
        Gson gson = new GsonBuilder().registerTypeAdapter(Json.class, new SpringfoxJsonToGsonAdapter()).registerTypeAdapter(Date.class,new DateTypeAdapter("yyyy-MM-dd HH:mm:ss")).create();
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter(gson);
//		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(list);
        return new HttpMessageConverters(converter);    
    }
}
