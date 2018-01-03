package com.sds.oauth.util;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author SDS
 *
 *	This util MUST set instance in each class using this class.
 */

@Component
public class MyBatisUtil {
	
	@Autowired
	@Qualifier("restSqlSessionTemplate")
    private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(MyBatisUtil.class);

	public String getQueryPath(String prefix, String sqlId) {
		return new StringBuffer(prefix).append(sqlId).toString();
	}
	
	public List<Map<String, Object>> selectList(String prefix, String sqlId, Map<String, Object> param) {
		List<Map<String, Object>> returnValue = null;
		try {
			returnValue = sqlSession.selectList(getQueryPath(prefix, sqlId), param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return returnValue;
	}
	
	public Map<String, Object> selectOne(String prefix, String sqlId, Map<String, Object> param) {
		Map<String, Object> returnValue = null;
		try {
			returnValue = sqlSession.selectOne(getQueryPath(prefix, sqlId), param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return returnValue;
	}
	
	public List<String> selectStrList(String prefix, String sqlId, Map<String, Object> param) {
		try {
			return sqlSession.selectList(getQueryPath(prefix, sqlId), param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	public String selectStr(String prefix, String sqlId, Map<String, Object> param) {
		try {
			return sqlSession.selectOne(getQueryPath(prefix, sqlId), param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	public Integer selectInt(String prefix, String sqlId, Map<String, Object> param) {
		try {
			return sqlSession.selectOne(getQueryPath(prefix, sqlId), param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	
	public List<Long> selectLongList(String prefix, String sqlId, Map<String, Object> param) {
		try {
			return sqlSession.selectList(getQueryPath(prefix, sqlId), param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	public Long selectLong(String prefix, String sqlId, Map<String, Object> param) {
		try {
			return sqlSession.selectOne(getQueryPath(prefix, sqlId), param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	public int update(String prefix, String sqlId, Map<String, Object> param) {
		int returnValue = -1;
		try {
			returnValue = sqlSession.update(getQueryPath(prefix, sqlId), param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return returnValue;
	}
	
	public int insert(String prefix, String sqlId, Map<String, Object> param) {
		int returnValue = -1;
		try {
			returnValue = sqlSession.insert(getQueryPath(prefix, sqlId), param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return returnValue;
	}
	
	public int delete(String prefix, String sqlId, Map<String, Object> param) {
		int returnValue = -1;
		try {
			returnValue = sqlSession.delete(getQueryPath(prefix, sqlId), param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return returnValue;
	}
	
	
	
}
