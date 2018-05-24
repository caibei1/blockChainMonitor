package com.fuzamei.service;

import java.util.List;
import java.util.Map;

import com.fuzamei.entity.Application;
import com.fuzamei.entity.ExceptionLog;
import com.fuzamei.entity.HistoryHeight;
import com.fuzamei.entity.Project;
import com.fuzamei.entity.Server;
import com.fuzamei.entity.ServerByProject;

/**
 * @author hbj
 * 2018年5月21日-下午3:04:33
 */

public interface NodeMonitorService {
	List<ServerByProject> getServerList();
	HistoryHeight getNewestNodeHeight(ServerByProject serverByProject);
	int saveNodeHighest(HistoryHeight historyHeight);
	List<HistoryHeight> getTimeHistoryHeightListByServerId(HistoryHeight historyHeight);
	Project getProjectInfo(ServerByProject serverByProject);
	Integer updateChainStatus(ServerByProject serverByProject);
	Integer updateProjectState(Project project);
	Integer insertNodeException(ExceptionLog exceptionLog);
	Server getServerInfoByServerId(ServerByProject serverByProject);
}
