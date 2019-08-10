package xyz.zhuoxuan.jinnuo.base.service;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.zhuoxuan.jinnuo.base.dao.BaseMapper;
import xyz.zhuoxuan.jinnuo.base.entity.BaseEntity;
import xyz.zhuoxuan.jinnuo.commom.ServerResponse;


/**
 * Service抽象类
 * 
 * @author peng.xy
 * @createDate 2018年5月20日 下午12:06:31
 */
public abstract class AbstractService<T extends BaseEntity, D extends BaseMapper<T>> implements BaseService<T> {

	/**
	 * 业务日志记录
	 */
	protected static Logger log = Logger.getLogger(AbstractService.class);

	/**
	 * 自动注入主表mapper
	 */
	@Autowired
	protected D mapper;

	/**
	 * 根据entityId删除记录
	 * 
	 * @author peng.xy
	 * @createDate 2018年5月20日 下午12:09:10
	 */
	public ServerResponse deleteByPrimaryKey(Integer entityId) {
		return ServerResponse.createBySuccess("功能暂未实现！");
	}

	/**
	 * 新增entity
	 * 
	 * @author peng.xy
	 * @createDate 2018年5月20日 下午12:09:57
	 */
	public ServerResponse insert(T entity) {
		return ServerResponse.createBySuccess("功能暂未实现！");
	}

	/**
	 * 动态新增entity
	 * 
	 * @author peng.xy
	 * @createDate 2018年5月20日 下午12:12:06
	 */
	public ServerResponse insertSelective(T entity) {
		return ServerResponse.createBySuccess("功能暂未实现！");
	}

	/**
	 * 根据entityId查询
	 * 
	 * @author peng.xy
	 * @createDate 2018年5月20日 下午12:13:27
	 */
	public ServerResponse<T> selectByPrimaryKey(Integer entityId) {
		return ServerResponse.createBySuccess("功能暂未实现！",null);
	}

	/**
	 * 动态更新entity
	 * 
	 * @param entity
	 * @author peng.xy
	 * @createDate 2018年5月20日 下午12:17:40
	 */
	public ServerResponse updateByPrimaryKeySelective(T entity) {
		return ServerResponse.createBySuccess("功能暂未实现！");
	}

	/**
	 * 更新entity
	 * 
	 * @author peng.xy
	 * @createDate 2018年5月20日 下午12:18:21
	 */
	public ServerResponse updateByPrimaryKey(T entity) {
		return ServerResponse.createBySuccess("功能暂未实现！");
	}


}
