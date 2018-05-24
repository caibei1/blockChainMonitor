package com.fuzamei.mapperInterface;

import java.util.List;

import com.fuzamei.entity.Application;
import com.fuzamei.entity.ExceptionInfo;
import com.fuzamei.entity.ExceptionLog;
import com.fuzamei.entity.HistoryHeight;
import com.fuzamei.entity.Project;
import com.fuzamei.entity.ProjectServer;
import com.fuzamei.entity.Server;
import com.fuzamei.entity.ServerByProject;

/**
 * @author hbj
 * 2018年5月21日-下午3:04:43
 */
public interface NodeMonitorMapperInterface {
	List<ServerByProject> getServerInfo();
	List<HistoryHeight> getTimeHistoryHeightListByServerId(HistoryHeight historyHeight);
	HistoryHeight getMinTimeHistoryHeight(HistoryHeight historyHeight);
	Integer updateHistoryHeight(HistoryHeight historyHeight);
	Integer insertHistoryHeight(HistoryHeight historyHeight);
	Project getProjectInfo(ServerByProject serverByProject);
	Integer updateChainStatus(ServerByProject serverByProject);
	Integer updateProjectState(Project project);
	Integer insertNodeException(ExceptionLog exceptionLog);
	List<ExceptionLog> getExceptionLogList(ExceptionLog exceptionLog);
	List<ProjectServer> getChainByProjectId(ExceptionInfo exceptionInfo);
	Server getServerInfoByServerId(ServerByProject serverByProject);
}
