package cn.yx.com.dao.demo.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.apache.commons.lang3.ObjectUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @param <T>
 * 
 * 
 */
@Repository
public class BaseMybatisDao<T> extends SqlSessionDaoSupport {
 
	/**
	 * 查询List
	 * 
	 * @param queryStr
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getSearchList(String queryStr, Object params) {

		List<T> list = new ArrayList<T>();
		try {
			list = getSqlSession().selectList(queryStr, params);
		} catch (Exception e) {
			logger.error("getSearchList()异常", e);
			throw new PersistenceException("getSearchList()异常", e);
		}
		return list;
	}
	
	/**
	  
	 * 
	 * @param queryStr
	 * @param params 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getSearchListToMap(String queryStr, Object params) {
 
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = getSqlSession().selectList(queryStr, params); 
		} catch (Exception e) {
			logger.error("getSearchList，返回值List<Map<String,Object>>异常", e);
		}
		return list;
	}


	 
	/**
	 * 插入SQL执行
	 * 
	 * @param updateStr
	 * @param params
	 * @return
	 */
	public int insert(String updateStr, Map<String, Object> params) {
		int result = 0;
		try {
			result = getSqlSession().insert(updateStr, params);
		} catch (Exception e) {
			logger.error("update()异常", e);
			throw new PersistenceException("insert()异常", e);
		}
		return result;
	}

	/**
	 * 更新SQL执行
	 * 
	 * @param updateStr
	 * @param params
	 * @return
	 */
	public int update(String updateStr, Map<String, Object> params) {
		int result = 0;
		try {
			result = getSqlSession().update(updateStr, params);
		} catch (Exception e) {
			logger.error("update()异常", e);
			throw new PersistenceException("update()异常", e);
		}
		return result;
	}

	/**
	 * 删除SQL执行
	 * 
	 * @param updateStr
	 * @param params
	 * @return
	 */
	public int delete(String updateStr, Map<String, Object> params) {
		int result = 0;
		try {
			result = getSqlSession().delete(updateStr, params);
		} catch (Exception e) {
			logger.error("delete()异常", e);
			throw new PersistenceException("delete()异常", e);
		}
		return result;
	}

	/**
	 * 根据条件返回单个对象
	 * 
	 * @param selectStr
	 * @param params
	 * @return
	 */
	public Object getOneInfo(String selectStr, Map<String, Object> params) {
		Object result = null;
		try {
			result = getSqlSession().selectOne(selectStr, params);
		} catch (Exception e) {
			logger.error("select()异常", e);
			throw new PersistenceException("select()异常", e);
		}
		return result;
	} 
	
	/**
	 * 计算数量
	 * 
	 * @param countStr
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer getSearchSize(String countStr, Map<String, Object> params) {
		Integer totalCount = 0;
		if (!"".equalsIgnoreCase(countStr)) {
			try {
				List<Map<String, Object>> list = getSqlSession().selectList(countStr, params);
				if (list.size() > 0) {
					totalCount = Integer.parseInt(ObjectUtils.toString(list.get(0).get("totalCount")));
				}
			} catch (Exception e) {
				logger.error("getSearchSize()异常", e);
			}
		}
		return totalCount;
	}
	
}
