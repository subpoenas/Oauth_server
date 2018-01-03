package com.sds.oauth;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.google.common.base.Predicates;
import com.sds.oauth.model.ContextVO;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author Alton.jung
 * Oauth Server 설정
 * 
 * 	- 2 legged token 획득 방법
 * 		* POST http://localhost:8080/oauth/token
 * 		  Header Authorization: Basic base64(clientId: client_secret)
 * 
 * 		  username:
 * 		  password: Bcrpyt(password)
 * 		
 *  - 3 legged token 획득 방법 	
 *  	* Get http://localhost:8080/oauth/authorize?response_type=code&client_id=test&scope=read&redirect_uri=http://localhost:8080/test/time
 * 
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@EnableSwagger2
public class OauthStarter {
	
	private static final Logger logger = LoggerFactory.getLogger(OauthStarter.class);
	
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(OauthStarter.class);
	}
	
	/**
	 *  Oauth Token 저장  설정  
	 **/
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public JdbcTokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	@Bean
	protected AuthorizationCodeServices authorizationCodeServices() {
		return new JdbcAuthorizationCodeServices(dataSource);
	}	
	/**
	 *  Swagger 설정  
	 **/
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())               
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .build();
    }
     
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring REST Sample with Swagger")
                .description("Spring REST Sample with Swagger")
                .termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
                .version("2.0")
                .build();
    }
    
    public static void main(String[] args) throws InterruptedException {

    	ContextVO context = ContextVO.getInstance();
    	
		logger.info("Default Charset=" + Charset.defaultCharset());
    	
    	ConfigurableApplicationContext appContext =SpringApplication.run(OauthStarter.class, args);
    	
    	context.setDaemonStart(true);
    	
    	logger.info("Hello, Oauth2Provider");          
    }
}