package xyz.zhuoxuan.jinnuo.log.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.zhuoxuan.jinnuo.base.service.AbstractService;
import xyz.zhuoxuan.jinnuo.commom.ServerResponse;
import xyz.zhuoxuan.jinnuo.log.dao.StatusLogMapper;
import xyz.zhuoxuan.jinnuo.log.entity.StatusLog;
import xyz.zhuoxuan.jinnuo.serivce.ex.UpdateException;
import xyz.zhuoxuan.jinnuo.util.BeanUtil;
import xyz.zhuoxuan.jinnuo.util.ParamUtil;

/**
 * 登录日志service
 * 
 * @author deng.yang.yang
 * @CreateDate 2018年10月16日 上午11:20:09
 */
@Service
public class StatusLogService extends AbstractService<StatusLog, StatusLogMapper> {

	/**
	 * 添加登录日志
	 * 
	 * @param statusLog
	 * @return
	 * @author deng.yang.yang
	 * @createDate Oct 16, 2018 7:48:05 PM
	 */
	public ServerResponse insert(StatusLog statusLog) {
		Date loginDate = new Date();
		statusLog.setLoginDate(loginDate);
		statusLog.setLoginTime(loginDate);
		statusLog.preInsert();
		Integer rows = mapper.insert(statusLog);
		if (rows != 1)
			throw new UpdateException("添加登录日志时出现未知异常！");
		return ServerResponse.createBySuccess("添加成功！");
	}

	/**
	 * 根据主键修改登录日志
	 * 
	 * @param statusLog
	 * @return
	 * @author deng.yang.yang
	 * @createDate Oct 16, 2018 8:05:17 PM
	 */
	@Transactional
	public ServerResponse updateByStatusLogId(StatusLog statusLog) {
		ParamUtil.validateParam(statusLog.getStatusLogId(), "状态主键不能为空");
		StatusLog oldStatusLog = mapper.selectByPrimaryKey(statusLog.getStatusLogId());
		BeanUtil.copyProperty(statusLog, oldStatusLog);
		Date loginDate = new Date();
		statusLog.setLoginDate(loginDate);
		statusLog.setLoginTime(loginDate);
		Integer rows = mapper.updateByPrimaryKey(statusLog);
		if (rows != 1)
			throw new UpdateException("更新登录日志出现未知异常！");
		return ServerResponse.createBySuccess("更新登录日志成功！");
	}

	/**
	 * 获取登录日志列表
	 * 
	 * @param statusLog
	 * @return
	 * @author deng.yang.yang
	 * @createDate Oct 16, 2018 8:15:12 PM
	 */
	public List<StatusLog> findStatusLogAllList(StatusLog statusLog) {
		return  mapper.findStatusLogList(statusLog);
	}

}
