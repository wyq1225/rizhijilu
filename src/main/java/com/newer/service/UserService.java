package com.newer.service;

import com.newer.pojo.User;

public interface UserService {
    //注册
    public int insert(User user);

    //验证（登录）

    public User login(User user);


    //检测用户是否存在
    public User queryByuserName(String userName);
}
