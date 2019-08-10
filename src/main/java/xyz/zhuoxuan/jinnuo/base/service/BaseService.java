package xyz.zhuoxuan.jinnuo.base.service;

import xyz.zhuoxuan.jinnuo.base.entity.BaseEntity;
import xyz.zhuoxuan.jinnuo.commom.ServerResponse;

/**
 * Service公共接口
 * 
 * @author peng.xy
 * @createDate 2018年5月20日 下午1:46:07
 */
public interface BaseService<T extends BaseEntity> {

	/**
	 * 根据entityId删除记录
	 * 
	 * @author peng.xy
	 * @createDate 2018年5月20日 下午12:09:10
	 */
	ServerResponse deleteByPrimaryKey(Integer entityId);

	/**
	 * 新增entity
	 * 
	 * @author peng.xy
	 * @createDate 2018年5月20日 下午12:09:57
	 */
	ServerResponse insert(T entity);

	/**
	 * 动态新增entity
	 * 
	 * @author peng.xy
	 * @createDate 2018年5月20日 下午12:12:06
	 */
	ServerResponse insertSelective(T entity);

	/**
	 * 根据entityId查询
	 * 
	 * @author peng.xy
	 * @createDate 2018年5月20日 下午12:13:27
	 */
	ServerResponse<T> selectByPrimaryKey(Integer entityId);

	/**
	 * 动态更新entity
	 * 
	 * @param entity
	 * @author peng.xy
	 * @createDate 2018年5月20日 下午12:17:40
	 */
	ServerResponse updateByPrimaryKeySelective(T entity);

	/**
	 * 更新entity
	 * 
	 * @author peng.xy
	 * @createDate 2018年5月20日 下午12:18:21
	 */
	ServerResponse updateByPrimaryKey(T entity);
}
