package com.fuzamei.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuzamei.entity.ExceptionInfo;
import com.fuzamei.entity.ExceptionLog;
import com.fuzamei.entity.Project;
import com.fuzamei.entity.ProjectServer;
import com.fuzamei.mapperInterface.ExceptionMapperInterface;
import com.fuzamei.mapperInterface.NodeMonitorMapperInterface;
import com.fuzamei.service.ExceptionService;

@Service
public class ExceptionServiceImpl implements ExceptionService {

	@Autowired
	ExceptionMapperInterface exceptionMapperInterface;
	@Autowired
	NodeMonitorMapperInterface nodeMonitorMapperInterface;
	
	@Override
	public List<ExceptionInfo> getExceptionLogList() {
		// TODO Auto-generated method stub
		return exceptionMapperInterface.getExceptionLogList();
	}

	@Override
	public ExceptionInfo getExceptionLogListMore(ExceptionInfo exceptionInfo) {
		// TODO Auto-generated method stub
		return exceptionMapperInterface.getExceptionLogListMore(exceptionInfo);
	}

	@Override
	public Integer dealLogByProjectId(ExceptionInfo exceptionInfo) {
		// TODO Auto-generated method stub
//		Map<String,Integer> data = new HashMap<String,Integer>();
		Object data = new Object();
		int checkSucc = 0;
		List<ExceptionLog> projectUndealList = exceptionMapperInterface.getExceptionListByProjectId(exceptionInfo);
		int undealSize = projectUndealList.size();
		List<ProjectServer> projectChainList = nodeMonitorMapperInterface.getChainByProjectId(exceptionInfo);
		int chainSize = projectChainList.size();
		Project project = new Project();
		ProjectServer projectServer = new ProjectServer();
		ExceptionLog exceptionLog = new ExceptionLog();
		
		if(undealSize >= chainSize/2+1) {
			System.out.println("项目仍为警报");
			//更换项目的状态
			project.setProjectId(exceptionInfo.getProjectId());
			project.setState(3);
			//更换链的状态
			projectServer.setServerId(exceptionInfo.getServerId());
			projectServer.setProjectId(exceptionInfo.getProjectId());
			projectServer.setStatus(1);
			//更改错误日志状态
			exceptionLog.setProjectId(exceptionInfo.getProjectId());
			exceptionLog.setPort(exceptionInfo.getPort());
			exceptionLog.setServerId(exceptionInfo.getServerId());
			exceptionLog.setState(1);
			
			checkSucc += exceptionMapperInterface.updateProjectState(project);
			checkSucc += exceptionMapperInterface.updateProjectServerState(projectServer);
			checkSucc += exceptionMapperInterface.updateExceptionLogState(exceptionLog);

			System.out.println(" 1 checkSucc : " + checkSucc);
			
			if(checkSucc >= 3) {
				
//				data.put("id", exceptionInfo.getId());
//				return data;
				return exceptionInfo.getId();
			}
//			data.put("id", -1);
//			return data;
			return -1;
			
		}else if(undealSize >0 && undealSize<chainSize/2+1){
			System.out.println("项目仍为预警");
			project.setProjectId(exceptionInfo.getProjectId());
			project.setState(2);
			//更换链的状态
			projectServer.setServerId(exceptionInfo.getServerId());
			projectServer.setProjectId(exceptionInfo.getProjectId());
			projectServer.setStatus(1);
			//更改错误日志状态
			exceptionLog.setProjectId(exceptionInfo.getProjectId());
			exceptionLog.setPort(exceptionInfo.getPort());
			exceptionLog.setServerId(exceptionInfo.getServerId());
			exceptionLog.setState(1);

			checkSucc += exceptionMapperInterface.updateProjectState(project);
			checkSucc += exceptionMapperInterface.updateProjectServerState(projectServer);
			checkSucc += exceptionMapperInterface.updateExceptionLogState(exceptionLog);

			System.out.println(" 2 checkSucc : " + checkSucc);
			
			if(checkSucc >= 3) {
//				data.put("id", exceptionInfo.getId());
//				return data;
				return exceptionInfo.getId();
			}
//			data.put("id", -1);
			return -1;
			
		}else {
			System.out.println("项目正常");
			project.setProjectId(exceptionInfo.getProjectId());
			project.setState(1);
			//更换链的状态
			projectServer.setServerId(exceptionInfo.getServerId());
			projectServer.setProjectId(exceptionInfo.getProjectId());
			projectServer.setStatus(1);
			//更改错误日志状态
			exceptionLog.setProjectId(exceptionInfo.getProjectId());
			exceptionLog.setPort(exceptionInfo.getPort());
			exceptionLog.setServerId(exceptionInfo.getServerId());
			exceptionLog.setState(1);

			checkSucc += exceptionMapperInterface.updateProjectState(project);
			checkSucc += exceptionMapperInterface.updateProjectServerState(projectServer);
			checkSucc += exceptionMapperInterface.updateExceptionLogState(exceptionLog);

			System.out.println(" 3 checkSucc : " + checkSucc);
			
			if(checkSucc > 3) {
				return exceptionInfo.getId();
			}
//			data.put("id", -1);
			return -1;
		}
//		return null;
	}
	
	
}
