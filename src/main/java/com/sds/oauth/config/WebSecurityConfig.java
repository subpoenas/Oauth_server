package com.sds.oauth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.sds.oauth.service.UserSecurityService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
		
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
	  
	@Autowired 
	private UserSecurityService 		 securityService;
	   
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
            .authorizeRequests()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()               
                .permitAll();
       
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
      web
        .ignoring()
           .antMatchers("/static/img/**", "/static/js/**", "/static/vendor/**");   
    }
    
	@Override 
	protected void configure(AuthenticationManagerBuilder builder) throws Exception { 
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(securityService);
		authProvider.setPasswordEncoder(securityService.passwordEncoder());
		
		builder.authenticationProvider(authProvider);
	}

}
