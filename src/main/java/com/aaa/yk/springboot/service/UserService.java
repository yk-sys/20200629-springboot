package com.aaa.yk.springboot.service;

import com.aaa.yk.springboot.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Param
 * @ClassName UserService
 * @Description TODO
 * @Author yk
 * @Date 2020/6/29 0029 14:53
 * @Return
 **/
public interface UserService {
    User findByName(String username);
    User selectByPrimaryKey( Integer id);
}
