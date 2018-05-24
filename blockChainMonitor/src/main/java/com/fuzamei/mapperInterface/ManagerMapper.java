package com.fuzamei.mapperInterface;

import java.util.List;

import com.fuzamei.entity.User;

public interface ManagerMapper {

	//查询所有管理员
	List<User> selectAllManager(); 
	//根据名字查找管理员信息
	User findByName(String name);
	//添加管理员
	void insertManager(User user);
	//修改管理员资料（电话邮箱）
	void updateManager(User user);
	
}
