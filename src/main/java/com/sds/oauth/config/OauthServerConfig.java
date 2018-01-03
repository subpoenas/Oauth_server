package com.sds.oauth.config;

import java.util.Arrays;
import java.util.Collections;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.sds.oauth.controller.oauthController;
import com.sds.oauth.service.UserSecurityService;

@Configuration
@EnableAuthorizationServer
public class OauthServerConfig extends AuthorizationServerConfigurerAdapter {	   	  
	  	  
	  private static final Logger logger = LoggerFactory.getLogger(OauthServerConfig.class);
	 
	  @Autowired 
	  private UserSecurityService 				  securityService;
		
	  @Autowired
	  private JdbcTokenStore		  			  jdbcToken;
	  
	  @Autowired
	  private AuthorizationCodeServices		  	  codeServices;
		
	  @Autowired
	  private AuthenticationManager 	  	      authenticationManager;
	  
	  @Autowired
	  private DataSourceConfig 	          		  datasourceConfig;

	  @Override
	  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		  	security
	                .tokenKeyAccess("permitAll()")
	                .checkTokenAccess("isAuthenticated()")
	                .allowFormAuthenticationForClients()	               
	                .passwordEncoder(new BCryptPasswordEncoder());	// for client_secret
	  }
	  
	  @Override
	  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {		  
	        clients.jdbc(datasourceConfig.dataSource()).passwordEncoder(new BCryptPasswordEncoder());
	  }	  	   
	  
	  @Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		  	endpoints.authenticationManager(authenticationManager).tokenStore(jdbcToken)
		  		.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
	        
		  	endpoints.userDetailsService(securityService);
		  	endpoints.authorizationCodeServices(codeServices);		  
	  }
}
