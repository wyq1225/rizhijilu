package com.newer.mapper;

import com.newer.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    //注册
    public int insert(User user);

    //验证（登录）

    public User login(User user);


    //检测用户是否存在
    public User queryByuserName(String userName);

}
