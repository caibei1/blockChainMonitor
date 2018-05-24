package com.fuzamei.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuzamei.entity.Project;
import com.fuzamei.entity.Server;
import com.fuzamei.entity.ServerPurpose;
import com.fuzamei.mapperInterface.ServerMapper;
import com.fuzamei.service.ServerService;
import com.fuzamei.util.PageDTO;

@Service
public class ServerServiceImpl implements ServerService{

	@Autowired
	private ServerMapper serverMapper;
	
	@Override
	public PageDTO findAllServer(Server server) {
		server.setStart(server.getRows()*server.getPage()-server.getRows());
		server.setEnd(server.getStart()+server.getRows());
		List<Server> servers = serverMapper.findServer(server);
		int count = 0;
		if(servers.size()<server.getRows()){
			count = servers.size();
		}else
			count = serverMapper.findServerCount(server);
		PageDTO p = new PageDTO();
		p.setTotal(count);
		p.setRows(servers);
		for(Server s : servers){
			List<Project> projects = serverMapper.findProjectByServerId(s.getServerId());
			s.setProjects(projects);
		}
		return p;
	}

	/* (non-Javadoc)
	 * @see com.fuzamei.service.ServerService#addServer(com.fuzamei.entity.Server)
	 * 添加服务器
	 */
	@Override
	public void addServer(Server server) {
		serverMapper.addServer(server);
	}

	@Override
	//删除服务器
	public void deleteServer(int serverId) {
		serverMapper.deleteServer(serverId);
		
	}

	@Override
	public List<ServerPurpose> findAllServerPurpose() {
		List<ServerPurpose> serverPurposes  = serverMapper.findAllServerPurpose();
		return serverPurposes;
	}

	@Override
	public boolean findServerName(String serverName) {
		int count = serverMapper.findServerName(serverName);
		if(count==0){
			return true;
		}else
			return false;
	}

	@Override
	public void updateServer(Server server) {
		serverMapper.updateServer(server);
		
	}

}
