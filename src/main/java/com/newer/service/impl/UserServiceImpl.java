package com.newer.service.impl;

import com.newer.mapper.UserMapper;
import com.newer.pojo.User;
import com.newer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("user")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public User queryByuserName(String userName) {
        return userMapper.queryByuserName(userName);
    }
}
