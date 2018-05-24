package com.fuzamei.service;

import com.fuzamei.entity.User;

/**
 * @author huang
 * 2018/4/10
 */
public interface LoginService {
    public User queryUser(String username, String password);
}
