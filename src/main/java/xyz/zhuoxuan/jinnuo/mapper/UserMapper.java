package xyz.zhuoxuan.jinnuo.mapper;

import org.apache.ibatis.annotations.Param;

import xyz.zhuoxuan.jinnuo.entity.User;

import java.util.List;


public interface UserMapper  {


    Integer insert(User user);

    Integer insertSelective(User user);

    User selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(User entity);

    Integer updateByPrimaryKey(User entity);

    Integer deleteByPrimaryKey(Integer id);

    Integer checkUsername(String username);

    Integer checkEmail(String email);

    User selectLogin(@Param("username") String username, @Param("password") String password);

    User selectPhoneLogin(String phone);

    String selectQuestionByUsername (String username);

    Integer checkAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

    Integer updatePasswordByUsername(@Param("username") String username, @Param("passwordNew") String passwordNew);

    Integer checkPassword(@Param(value = "password") String password, @Param("userId") Integer userId);

    Integer checkEmailByUserId(@Param(value = "email") String email, @Param(value = "userId") Integer userId);

    Integer checkPhone(String phone);

    List<User> getListUser();

}