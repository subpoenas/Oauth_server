package com.sds.oauth.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
	  
    @Value("${rest.datasource.username}")
    private String username;
    
    @Value("${rest.datasource.password}")
    private String password;
    
    @Value("${rest.datasource.driverClassName}")
    private String driverClassName;    
    
    @Value("${rest.datasource.dbname}")
    private String dbname;
    
    @Value("${rest.datasource.options}")
    private String options;

    @Value("${rest.datasource.url}")
    private String url;
    
    @Value("${mybatis.mapper:classpath:mybatis/mapper/OauthMapper.xml}") 		   	
    private String  oauthMapper;

    @Value("${mybatis.mapper:classpath:mybatis/mapper/SecurityMapper.xml}") 		   	
    private String  securityMapper;

	@Bean
	@Primary
    public DataSource dataSource() {		
		DataSourceBuilder db= DataSourceBuilder.create();
		
		db.username(username);
		db.password(password);
		db.driverClassName(driverClassName);	
		db.url(url +"/"+dbname + options);
				
        return db.build();
    }

	@Qualifier("restTx")
	@Bean(name= "restTx")
	public DataSourceTransactionManager transactionManager(@Qualifier("dataSource")DataSource datasource){
		
		DataSourceTransactionManager apiTx = new DataSourceTransactionManager();
		apiTx.setDataSource(datasource);
		
		return apiTx;
	}
	
    @Bean(name = "restSqlSessionFactory") 
    public SqlSessionFactory apiSqlSessionFactory(@Qualifier("dataSource")DataSource restDataSource, ApplicationContext applicationContext) throws Exception {

    	Resource[] resources = new Resource[4];
    	
    	resources[0] = applicationContext.getResource(oauthMapper);
    	resources[1] = applicationContext.getResource(securityMapper);
       	
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(restDataSource);
        sqlSessionFactoryBean.setMapperLocations(resources);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name="restSqlSessionTemplate")    
    public SqlSessionTemplate gameSqlSessionTemplate(@Qualifier("restSqlSessionFactory")SqlSessionFactory restSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(restSqlSessionFactory);
    }
}
