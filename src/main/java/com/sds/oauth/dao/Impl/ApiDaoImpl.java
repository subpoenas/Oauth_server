package com.sds.oauth.dao.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.sds.oauth.dao.ApiDao;
import com.sds.oauth.util.MyBatisUtil;

@Repository
public class ApiDaoImpl implements ApiDao {
	
	@Value("${dao.prefix.rest}")
    private String prefix;
	 
	@Autowired
	private MyBatisUtil myBatisUtil;
	
}