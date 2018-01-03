package com.sds.oauth.service.Impl;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sds.oauth.dao.ApiDao;
import com.sds.oauth.model.JsonReturnVO;
import com.sds.oauth.model.UserVO;
import com.sds.oauth.service.ApiService;
import com.sds.oauth.util.JsonUtil;
import com.sds.oauth.util.ObjectUtil;

@Service
public class ApiServiceImpl implements ApiService{
	
	private static final Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);

	private java.text.SimpleDateFormat dbformat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private ApiDao apiDao;
	
//	@Transactional(value = "restTx")
//	public JsonReturnVO			 		getUser(UserVO userVO) {
//		
//		Map<String, Object> param = ObjectUtil.ConverObjectToMap(userVO);
//		
//		Map<String, Object> userMap = apiDao.getUser(param);
//		
//		JSONObject datum = JsonUtil.getJsonStringFromMap(userMap);
//		
//		JsonReturnVO returnObject = new JsonReturnVO();
//		returnObject.setData("datum", datum);
//		
//		return returnObject;
//	}
//	
//	@Transactional(value = "restTx")
//	public JsonReturnVO			 		getUsers(UserVO userVO) {
//		
//		JSONObject returnJson = new JSONObject();
//		Map<String, Object> param = ObjectUtil.ConverObjectToMap(userVO);
//		
//		List<Map<String, Object>> usersMap = apiDao.getUsers(param);
//		 
//		JSONArray data = JsonUtil.getJsonArrayFromList(usersMap);		
//		
//		JsonReturnVO returnObject = new JsonReturnVO();
//		returnObject.setData("data", data);
//		
//		return returnObject;
//	}
//		
//	@Transactional(value = "restTx")
//	public JsonReturnVO			 		createUser(UserVO userVO) {
//		
//		JSONObject returnJson = new JSONObject();
//		Map<String, Object> param = ObjectUtil.ConverObjectToMap(userVO);
//		
//		apiDao.createUser(param);
//		
//		JsonReturnVO returnObject = new JsonReturnVO();
//
//		return returnObject;
//	}
//	
//	@Transactional(value = "restTx")
//	public JsonReturnVO			 		deleteUser(UserVO userVO) {
//		
//		JSONObject returnJson = new JSONObject();
//		Map<String, Object> param = ObjectUtil.ConverObjectToMap(userVO);
//		
//		apiDao.deleteUser(param);
//		
//		JsonReturnVO returnObject = new JsonReturnVO();
//
//		return returnObject;
//	}
//	
//	@Transactional(value = "restTx")
//	public JsonReturnVO			 		updateUser(UserVO userVO) {
//		
//		JSONObject returnJson = new JSONObject();
//		Map<String, Object> param = ObjectUtil.ConverObjectToMap(userVO);
//		
//		apiDao.updateUser(param);
//		
//		JsonReturnVO returnObject = new JsonReturnVO();
//
//		return returnObject;
//	}
//	
//	@Transactional(value = "restTx")
//	public JsonReturnVO			 		updatePartialOfUser(UserVO userVO) {
//		
//		JSONObject returnJson = new JSONObject();
//		Map<String, Object> param = ObjectUtil.ConverObjectToMap(userVO);
//		
//		apiDao.updatePartialOfUser(param);
//		
//		JsonReturnVO returnObject = new JsonReturnVO();
//
//		return returnObject;
//	}
}
