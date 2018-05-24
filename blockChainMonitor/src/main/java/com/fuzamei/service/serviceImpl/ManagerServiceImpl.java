package com.fuzamei.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fuzamei.entity.User;
import com.fuzamei.mapperInterface.ManagerMapper;
import com.fuzamei.service.ManagerService;
@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerMapper managermapper;
	@Override
	public List<User> selectAllManager() {
		return managermapper.selectAllManager();
	}
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void insertmanager(User user) {
		managermapper.insertManager(user);
	}
	@Override
	public User findByName(String name) {
		return managermapper.findByName(name);		
	}
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void updatemanager(User user) {
		managermapper.updateManager(user);
	
	}

}
