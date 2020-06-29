package com.aaa.yk.springboot.service.impl;

import com.aaa.yk.springboot.mapper.UserMapper;
import com.aaa.yk.springboot.model.User;
import com.aaa.yk.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Param
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author yk
 * @Date 2020/6/29 0029 14:53
 * @Return
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByName(String username) {
        return userMapper.findByName(username);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
