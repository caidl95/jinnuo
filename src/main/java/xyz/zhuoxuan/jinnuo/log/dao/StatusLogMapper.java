package xyz.zhuoxuan.jinnuo.log.dao;

import xyz.zhuoxuan.jinnuo.base.dao.BaseMapper;
import xyz.zhuoxuan.jinnuo.log.entity.StatusLog;

import java.util.List;



public interface StatusLogMapper extends BaseMapper<StatusLog> {
    
    /**
     * 获取登录日志列表
     * @param statusLog
     * @return
     * @author deng.yang.yang
     * @createDate Oct 16, 2018 8:10:59 PM
     */
    List<StatusLog> findStatusLogList(StatusLog statusLog);
}