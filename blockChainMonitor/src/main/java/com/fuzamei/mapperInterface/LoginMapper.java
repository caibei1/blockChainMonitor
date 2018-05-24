package com.fuzamei.mapperInterface;

import com.fuzamei.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author huang
 * 2018/4/10
 */
public interface LoginMapper {
    public User queryUser(@Param("username") String username, @Param("password") String password);
}
