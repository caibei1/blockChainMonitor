package com.fuzamei.service.serviceImpl;

import com.fuzamei.entity.User;
import com.fuzamei.mapperInterface.LoginMapper;
import com.fuzamei.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huang
 * 2018/4/10
 */
@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public User queryUser(String username, String password) {
        return loginMapper.queryUser(username, password);
    }
}
