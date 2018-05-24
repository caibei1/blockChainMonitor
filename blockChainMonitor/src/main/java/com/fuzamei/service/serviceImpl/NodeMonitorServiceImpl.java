package com.fuzamei.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fuzamei.entity.Application;
import com.fuzamei.entity.ExceptionLog;
import com.fuzamei.entity.HistoryHeight;
import com.fuzamei.entity.Project;
import com.fuzamei.entity.Server;
import com.fuzamei.entity.ServerByProject;
import com.fuzamei.mapperInterface.NodeMonitorMapperInterface;
import com.fuzamei.mapperInterface.ProjectMonitorMapperInterface;
import com.fuzamei.service.NodeMonitorService;
import com.fuzamei.util.HttpRequest;

/**
 * @author hbj
 * 2018年5月21日-下午3:04:25
 */

@Service
public class NodeMonitorServiceImpl implements NodeMonitorService {

	@Autowired
	NodeMonitorMapperInterface nodeMonitorMapperInterface;
	
	@Override
	public List<ServerByProject> getServerList() {
		// TODO Auto-generated method stub
		List<ServerByProject> serverList = nodeMonitorMapperInterface.getServerInfo();
		return serverList;
	}

	@Override
	public HistoryHeight getNewestNodeHeight(ServerByProject serverByProject) {
		// TODO Auto-generated method stub

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(new Date());
		String result = HttpRequest.sendGet(serverByProject.getUrl(),serverByProject);
		JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject resultObject = jsonObject.getJSONObject("result");
        Integer maxBlockHeight = resultObject.getInteger("block_height");
        
        HistoryHeight saveHistoryHeight = new HistoryHeight();
        saveHistoryHeight.setServerId(serverByProject.getServerId());
        saveHistoryHeight.setProjectId(serverByProject.getProjectId());
        saveHistoryHeight.setHeight(maxBlockHeight);
        saveHistoryHeight.setUpdateTime(nowTime);
		return saveHistoryHeight;
	}

	@Override
	public int saveNodeHighest(HistoryHeight historyHeight) {
		// TODO Auto-generated method stub
		List<HistoryHeight> listInDataBase = nodeMonitorMapperInterface.getTimeHistoryHeightListByServerId(historyHeight);
		int result = -1;
		if(listInDataBase.size() == 4) {
			HistoryHeight minTime = nodeMonitorMapperInterface.getMinTimeHistoryHeight(historyHeight);
			minTime.setHeight(historyHeight.getHeight());
			result = nodeMonitorMapperInterface.updateHistoryHeight(minTime);
//			map.put(historyHeight.getServerId() + "result" , updateResult);
		}else {
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String nowTime = df.format(new Date());
//			historyHeight.setUpdateTime(nowTime);
			result = nodeMonitorMapperInterface.insertHistoryHeight(historyHeight);
//			map.put(historyHeight.getServerId() + "result" , insertResult);
		}
		
		return result;
	}

	@Override
	public List<HistoryHeight> getTimeHistoryHeightListByServerId(HistoryHeight historyHeight) {
		// TODO Auto-generated method stub
		return nodeMonitorMapperInterface.getTimeHistoryHeightListByServerId(historyHeight);
	}

	@Override
	public Project getProjectInfo(ServerByProject serverByProject) {
		// TODO Auto-generated method stub
		return nodeMonitorMapperInterface.getProjectInfo(serverByProject);
	}

	@Override
	public Integer updateChainStatus(ServerByProject serverByProject) {
		// TODO Auto-generated method stub
		try {
			Integer integer = nodeMonitorMapperInterface.updateChainStatus(serverByProject);
			return integer;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}

	@Override
	public Integer updateProjectState(Project project) {
		// TODO Auto-generated method stub
		return nodeMonitorMapperInterface.updateProjectState(project);
	}

	@Override
	public Integer insertNodeException(ExceptionLog exceptionLog) {
		// TODO Auto-generated method stub
		List<ExceptionLog> exceptionList = nodeMonitorMapperInterface.getExceptionLogList(exceptionLog);
		if(exceptionList.size() > 0) {
			return 1;
		}
		System.out.println("插入异常日志    :  " + exceptionLog.toString());
		return nodeMonitorMapperInterface.insertNodeException(exceptionLog);
	}

	@Override
	public Server getServerInfoByServerId(ServerByProject serverByProject) {
		// TODO Auto-generated method stub
		return nodeMonitorMapperInterface.getServerInfoByServerId(serverByProject);
	}
	
}
