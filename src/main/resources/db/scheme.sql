[Mysql 계정 추가]

create database oauth;

create user oauth;

update user set authentication_string=password('oauth12#$') where user='oauth';
flush privileges;


grant all privileges on *.* to oauth@'127.0.0.1' identified by 'oauth12#$';
flush privileges;

grant all privileges on *.* to oauth@'localhost' identified by 'oauth12#$';
flush privileges;

grant all privileges on *.* to oauth@'70.30.20.170' identified by 'oauth12#$';
flush privileges;

grant all privileges on *.* to oauth@'70.30.120.162' identified by 'oauth12#$';
flush privileges;

grant all privileges on *.* to oauth@'58.143.159.51' identified by 'oauth12#$';
flush privileges;

grant all privileges on *.* to oauth@'*.*' identified by 'oauth12#$';
flush privileges;

*****************************************
***************** Oauth *****************
*****************************************
create table if not exists oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

create table if not exists oauth_client_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

create table if not exists oauth_access_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(255)
);

create table if not exists oauth_refresh_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);

create table if not exists oauth_code (
  code VARCHAR(255), authentication LONG VARBINARY
);

create table if not exists oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
);

create table if not exists ClientDetails (
  appId VARCHAR(255) PRIMARY KEY,
  resourceIds VARCHAR(255),
  appSecret VARCHAR(255),
  scope VARCHAR(255),
  grantTypes VARCHAR(255),
  redirectUrl VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(255)
);


*****************************************
*********** User Credential *************
*****************************************
-- ACCOUNT
DROP TABLE TB_S_ACCOUNT;
DROP TABLE TB_S_AUTHORITY;

CREATE TABLE TB_S_ACCOUNT (
	  id					bigint NOT NULL AUTO_INCREMENT,	    	
    name            		varchar(64) NOT NULL,    
    pwd        		        varchar(64) NOT NULL,
    isAccountNotExpired		tinyint default 1,
    isAccountNonLocked		tinyint default 1,	
    isCredentialsNonExpired tinyint default 1, 
    isEnabled				tinyint default 1,
    createDate      		datetime default now(),
    updateDate      		datetime default now(),
    PRIMARY KEY (id)
);

CREATE TABLE TB_S_AUTHORITY (
    id              		bigint NOT NULL AUTO_INCREMENT,
    accountId       		bigint NOT NULL,
    authority      		 	varchar(32),
    createDate      		datetime default now(),
    updateDate      		datetime default now(),
    PRIMARY KEY (id),
    FOREIGN KEY (accountId) REFERENCES TB_S_ACCOUNT (id) ON DELETE CASCADE ON UPDATE CASCADE  
);
