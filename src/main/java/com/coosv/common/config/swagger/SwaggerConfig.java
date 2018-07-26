package com.coosv.common.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年7月25日
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
		    	
	@Value("${info.app.name}")
	private String name;
	@Value("${info.app.description}")
	private String description;
	@Value("${info.app.version}")
	private String version;
	
	
	@Bean
	public Docket openApi() {
		Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {

			/* (non-Javadoc)
			 * @see com.google.common.base.Predicate#apply(java.lang.Object)
			 */
			@Override
			public boolean apply(RequestHandler handler) {
				// TODO Auto-generated method stub
				if (handler.isAnnotatedWith(ApiOperation.class))// 只有添加了ApiOperation注解的方法才在API中显示
					return true;
				if (handler.isAnnotatedWith(Api.class))
					return true;

				return false;
			}
			
		};
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(openApiInfo())
				.select()
				.apis(predicate)
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo openApiInfo() {
		return new ApiInfoBuilder()
				.title(name)
				.description(description)
				.version(version)
				.build();
	}

}
