package com.fuzamei.service;

import java.util.List;

import com.fuzamei.entity.User;

public interface ManagerService {
	//查询所有管理员
	List<User> selectAllManager();
	//根据name查找管理员信息
	public User findByName(String name);
	//插入管理员
	public void insertmanager(User user);
	//修改管理员资料
	public void updatemanager(User user);
}
