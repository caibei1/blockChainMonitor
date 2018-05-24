package com.fuzamei.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fuzamei.entity.Server;
import com.fuzamei.entity.ServerPurpose;
import com.fuzamei.util.PageDTO;


public interface ServerService {

	
	//查找未删除的服务器信息
	public PageDTO findAllServer(Server server);

	public void addServer(Server server);

	//删除服务器
	public void deleteServer(int serverId);

	//查找服务器用途
	public List<ServerPurpose> findAllServerPurpose();

	//查找用户名是否存在
	public boolean findServerName(String serverName);
	
	public void updateServer(Server server);
}
