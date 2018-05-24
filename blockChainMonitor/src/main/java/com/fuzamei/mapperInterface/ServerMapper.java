package com.fuzamei.mapperInterface;

import java.util.List;

import com.fuzamei.entity.Project;
import com.fuzamei.entity.Server;
import com.fuzamei.entity.ServerPurpose;

public interface ServerMapper {


	//查询server下的项目
	List<Project> findProjectByServerId(int serverId);

	//添加服务器
	void addServer(Server server);

	//删除服务器
	void deleteServer(int serverId);

	//查找所有服务器用途
	List<ServerPurpose> findAllServerPurpose();

	List<Server> findServer(Server server);

	Integer findServerCount(Server server);

	//查找用户名是否存在
	Integer findServerName(String serverName);
	
	//更新服务器的数据
	void updateServer(Server server);
}
