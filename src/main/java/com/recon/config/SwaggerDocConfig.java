package com.recon.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerDocConfig extends WebMvcConfigurationSupport {
	
	  @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.recon.web"))
	                .paths(PathSelectors.any())
	                .build()
	                .apiInfo(getApiInfo());
	    }
	  @Override
	    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("swagger-ui.html")
	                .addResourceLocations("classpath:/META-INF/resources/");
	 
	        registry.addResourceHandler("/webjars/**")
	                .addResourceLocations("classpath:/META-INF/resources/webjars/");
	    }
	  private ApiInfo getApiInfo() {
		    return new ApiInfo(
		            "MyResume Spring boot Api server",
		            "Spring api for building resume online ",
		            "v1.0",
		            "TERMS OF SERVICE URL",
		            new Contact("Vishal Sanserwal","https://github.com/vishu-chaudhary","vishal2421@outlook.com"),
		            "Apache License Version 2.0",
	                "https://www.apache.org/licenses/LICENSE-2.0",
		            Collections.emptyList()
		    );
		}
	  


}
