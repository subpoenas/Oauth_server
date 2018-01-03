package com.sds.oauth.controller;

import java.util.Calendar;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.sds.oauth.model.ContextVO;
import com.sds.oauth.model.JsonReturnVO;
import com.sds.oauth.model.UserVO;
import com.sds.oauth.service.ApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "oauthController", description = "Oauth Controller",basePath = "/")
@CrossOrigin(origins = "*")
@Controller
@EnableWebMvc
@RequestMapping("/")
public class oauthController extends WebMvcConfigurerAdapter{
	
	@Autowired
	private ApiService apiService;
		
	private static final Logger logger = LoggerFactory.getLogger(oauthController.class);
		
	private java.text.SimpleDateFormat dbformat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Value("${server.contextPath}") 		   			  			private String  serverContextPath;	
    @Value("${rest.proxy.ip}")										private String  proxyIp;    
    @Value("${rest.proxy.port ?: 0}")								private Integer proxyPort;
				

	@PostConstruct
	public void bootUp() {
		
			ContextVO _context = ContextVO.getInstance();	
			
			_context.setProxyIp(proxyIp);
			_context.setProxyPort(proxyPort);
			
			logger.info("bootUp....");
	}

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".html");
        return resolver;
    }
    
    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    } 
    
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(Model model) {
		
		logger.info("login called");
		return "login";						// login.html called
	}
}