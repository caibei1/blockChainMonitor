package com.fuzamei.mapperInterface;

import java.util.List;

import com.fuzamei.entity.ExceptionInfo;
import com.fuzamei.entity.ExceptionLog;
import com.fuzamei.entity.Project;
import com.fuzamei.entity.ProjectServer;

public interface ExceptionMapperInterface {
	List<ExceptionInfo> getExceptionLogList();
	ExceptionInfo getExceptionLogListMore(ExceptionInfo exceptionInfo);
	List<ExceptionLog> getExceptionListByProjectId(ExceptionInfo exceptionInfo);
	Integer updateExceptionLogState(ExceptionLog exceptionLog);
	Integer updateProjectState(Project project);
	Integer updateProjectServerState(ProjectServer projectServer);
}
