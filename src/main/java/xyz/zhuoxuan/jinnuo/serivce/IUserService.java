package xyz.zhuoxuan.jinnuo.serivce;

import xyz.zhuoxuan.jinnuo.commom.ServerResponse;
import xyz.zhuoxuan.jinnuo.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    ServerResponse<User> login(HttpServletRequest request, String username, String password);

    /**
     * 手机短信验证
     */
    ServerResponse<User> mobileLogin(String phone, String code, String sessionPhone, String sessionCode);

    /**
     * 注册
     * @param user
     * @return
     */
    ServerResponse<String> register(User user);

    /**
     * 效验用户
     * @param str
     * @param type
     * @return
     */
    ServerResponse<String> checkValid(String str, String type);

    /**
     * 查询用户的忘记密码提示问题
     * @param username
     * @return
     */
    ServerResponse<String> selectQuestion(String username);

    /**
     * 效验用户名问题答案
     * @param username
     * @param question
     * @param answer
     * @return 一个Token
     */
    ServerResponse<String> checkAnswer(String username, String question, String answer);

    /**
     * 通过Token更新密码
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken);

    /**
     * 登录状态时更新密码
     */
    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    /**
     * 更新用户资料
     */
    ServerResponse<User> updateInformation(User user);

    /**
     * 获取用户登录信息
     */
    ServerResponse<User> getInformation(Integer userId);



    /**
     * 校验是不是管理员
     */
    ServerResponse checkAdminRole(User user);

    /**
     * 获取所有用户
     */
    ServerResponse<List<User>> getListUser();

    /**
     * 根据entityId删除记录
     *
     */
    ServerResponse deleteByPrimaryKey(Integer id);

    /**
     * 根据id获取用户信息
     */
    ServerResponse<User> selectByPrimaryKey(Integer id);


}
