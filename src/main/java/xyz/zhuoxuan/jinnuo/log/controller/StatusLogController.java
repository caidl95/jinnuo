package xyz.zhuoxuan.jinnuo.log.controller;

import java.util.List;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.zhuoxuan.jinnuo.base.conreoller.BaseController;
import xyz.zhuoxuan.jinnuo.commom.ServerResponse;
import xyz.zhuoxuan.jinnuo.log.entity.StatusLog;
import xyz.zhuoxuan.jinnuo.log.service.StatusLogService;
import xyz.zhuoxuan.jinnuo.util.PageUtil;


/**
 * 登录日志控制器
 *
 * @author deng.yang.yang
 * @CreateDate Oct 16, 2018 7:22:03 PM
 */
@Controller
@RequestMapping("/statusLog")
public class StatusLogController extends BaseController {

    @Autowired
    private StatusLogService statusLogService;

    /**
     * 获取登录日志列表
     *
     * @param statusLog
     * @param pageIndex
     * @param pageSize
     * @return
     * @author deng.yang.yang
     * @createDate Oct 16, 2018 8:18:32 PM
     */
    @ResponseBody
    @RequestMapping("findStatusLogAllList")
    public ServerResponse<PageInfo<StatusLog>> findStatusLogAllList(StatusLog statusLog, Integer pageIndex, Integer pageSize) {
        PageUtil.setPage(pageIndex, pageSize);
        List<StatusLog> list  =   statusLogService.findStatusLogAllList(statusLog);
        return PageUtil.getPageJsonResult(list);
    }

    /**
     * 新增登录日志
     *
     * @param statusLog
     * @return
     * @author deng.yang.yang
     * @createDate Oct 16, 2018 8:25:14 PM
     */
    @ResponseBody
    @RequestMapping("insertStatusLog")
    public ServerResponse<StatusLog> insertStatusLog(StatusLog statusLog) {
        return statusLogService.insert(statusLog);
    }

    /**
     * 根据主键删除登录日志
     *
     * @param statusLogId
     * @return
     * @author deng.yang.yang
     * @createDate Oct 16, 2018 8:25:41 PM
     */
    @ResponseBody
    @RequestMapping("deleteByStatusLogId")
    public ServerResponse<Integer> deleteByStatusLogId(@RequestParam Integer statusLogId) {
        return statusLogService.deleteByPrimaryKey(statusLogId);
    }

    /**
     * 修改登录日志
     *
     * @param statusLog
     * @return
     * @author deng.yang.yang
     * @createDate Oct 16, 2018 8:26:12 PM
     */
    @ResponseBody
    @RequestMapping("updateByStatusLogId")
    public ServerResponse<StatusLog> updateByStatusLogId(StatusLog statusLog) {
        return statusLogService.updateByStatusLogId(statusLog);
    }

    /**
     * 根据主键查询登录日志
     *
     * @param statusLogId
     * @return
     * @author deng.yang.yang
     * @createDate Oct 16, 2018 8:26:26 PM
     */
    @ResponseBody
    @RequestMapping("selectByStatusLogId")
    public ServerResponse selectByStatusLogId(@RequestParam Integer statusLogId) {
        return statusLogService.selectByPrimaryKey(statusLogId);
    }

}
