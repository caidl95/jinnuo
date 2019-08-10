package xyz.zhuoxuan.jinnuo.base.conreoller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import xyz.zhuoxuan.jinnuo.commom.Const;
import xyz.zhuoxuan.jinnuo.commom.ServerResponse;
import xyz.zhuoxuan.jinnuo.entity.User;
import xyz.zhuoxuan.jinnuo.serivce.ex.*;
import javax.servlet.http.HttpSession;

/**
 *
 */

public class BaseController{



    /**
     * 获取当前用户id
     */
    protected final Integer getUidFromSession(HttpSession session) {
       User user = (User)session.getAttribute(Const.CURRENT_USER);
        return user.getId();
    }

    /**
     * 获取当前用户
     */
    protected final User getUserFromSession(HttpSession session){
        return (User)session.getAttribute(Const.CURRENT_USER);
    }

    /**
     * 判断当前用户是否是管理员
     */
    protected final boolean getRoleAdminFromSession(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user.getRole() == Const.Role.ROLE_ADMIN){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 统一处理异常
     * @param e
     * @return
     */
    @ExceptionHandler({ServiceException.class})
    public ServerResponse handleException(Throwable e) {
        if (e instanceof UpdateException)
            return ServerResponse.createByErrorMessage(e.getMessage());
        if (e instanceof ProductQuantityNotSufficientFoundException)
            return ServerResponse.createByErrorMessage(e.getMessage());
        if (e instanceof FileUploadException)
            return ServerResponse.createByErrorMessage(e.getMessage());
        if (e instanceof ParameterIsNullException)
            return ServerResponse.createByErrorMessage(e.getMessage());


        return ServerResponse.createByErrorMessage("未知异常");
    }


}
