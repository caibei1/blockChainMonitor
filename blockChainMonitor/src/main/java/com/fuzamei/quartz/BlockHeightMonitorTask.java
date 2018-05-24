package com.fuzamei.quartz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fuzamei.entity.ExceptionLog;
import com.fuzamei.entity.HistoryHeight;
import com.fuzamei.entity.Project;
import com.fuzamei.entity.ServerByProject;
import com.fuzamei.service.NodeMonitorService;
import com.fuzamei.service.serviceImpl.NodeMonitorServiceImpl;
import com.fuzamei.service.serviceImpl.ProjectMonitorServiceImpl;
import com.fuzamei.util.MulChainUtils;
import com.fuzamei.util.SpringContextUtil;

/**
 * @author hbj
 * 2018年5月21日-下午3:04:48
 */
@Component
public class BlockHeightMonitorTask {
	@Autowired
	NodeMonitorServiceImpl nodeMonitorService;
	@Autowired
	ProjectMonitorServiceImpl projectMonitorService;
	
	public void service() {
		System.out.println("————————————————————————————————————————————————————————————————————");
//		NodeMonitorServiceImpl nodeMonitorService = (NodeMonitorServiceImpl) SpringContextUtil.getBean("nodeMonitorServiceImpl");
//		ProjectMonitorServiceImpl projectMonitorService = (ProjectMonitorServiceImpl) SpringContextUtil.getBean("projectMonitorService");
		Map<String, Object> map = new HashMap<>();
		List<ServerByProject> serverList = nodeMonitorService.getServerList();
		if(serverList.size() == 0) {
			map.put("error", "暂时无需要查询的数据");
			System.out.println(map.toString());
		}else {
			List<ServerByProject> serverListInMonitor = new ArrayList<ServerByProject>();
			for(ServerByProject serverByProject : serverList) {
				if(serverByProject.getIsMonitor() == 1) {
					serverListInMonitor.add(serverByProject);
				}
			}
			
			//项目ID清单
			List<Integer> projectIdList = projectMonitorService.getProjectIdInMonitorList();
			if(projectIdList.size() == 0) {
				map.put("error", "暂无项目");
				System.out.println(map.toString());
			}else {
				//将项目的url的内容等放入list  list根据项目的id存储在map中
				Map<String,List<ServerByProject>> serverMap = new HashMap<String,List<ServerByProject>>();
				for(int i : projectIdList) {
					List<ServerByProject> serverListById = new ArrayList<ServerByProject>();
					for(ServerByProject serverByProject : serverList) {
						if(serverByProject.getProjectId() == i) {
							String url = serverByProject.getOutIp() + ":" + serverByProject.getPort() + "/validators";
							serverByProject.setUrl(url);
							serverListById.add(serverByProject);
						}
					}
					serverMap.put(String.valueOf(i), serverListById);
				}
				for(int i : projectIdList) {
					List<HistoryHeight> heightList = new ArrayList<HistoryHeight>(); 
					List<HistoryHeight> heightGetList = new ArrayList<HistoryHeight>();
					List<ServerByProject> serverListByMap = serverMap.get(String.valueOf(i));
					int chainNum = serverListByMap.size();
					int[] checkHight = new int[chainNum*5];
					for(ServerByProject serverByProject : serverListByMap) {
						HistoryHeight historyHeight = nodeMonitorService.getNewestNodeHeight(serverByProject);
						HistoryHeight historyHeightQuery = new HistoryHeight();
						historyHeightQuery.setServerId(serverByProject.getServerId());
						historyHeightQuery.setProjectId(serverByProject.getProjectId());
						List<HistoryHeight> list = nodeMonitorService.getTimeHistoryHeightListByServerId(historyHeightQuery);
						heightGetList.add(historyHeight);
						heightList.add(historyHeight);
						heightList.addAll(list);
						
						HistoryHeight insertHeight = new HistoryHeight();
						insertHeight.setHeight(historyHeight.getHeight());
						insertHeight.setProjectId(historyHeight.getProjectId());
						insertHeight.setServerId(historyHeight.getServerId());
						nodeMonitorService.saveNodeHighest(insertHeight);
						
					}
					for(int k=0;k<heightList.size();k++) {
						checkHight[k] = heightList.get(k).getHeight();
					}
					 
//					checkHight[0] = 33;
//					checkHight[5] = 33;
//					checkHight[10] = 33;
//					for(int ccc=0;ccc<checkHight.length;ccc++) {  
//					      System.out.println(checkHight[ccc]);  
//					} 
//					System.out.println("checkHight " + checkHight.length);
					
					ServerByProject useToGetProject = serverListByMap.get(0);
					Project projectInfo = nodeMonitorService.getProjectInfo(useToGetProject);
					int diff = projectInfo.getMinHeight();

					Map<Integer,Integer> checkResult = MulChainUtils.isNormal(checkHight, diff);
//					System.out.println("result = " + checkResult.toString());
//					System.out.println("serverListByMap = " + serverListByMap.toString());
					
//					Map<String,Object> checkResultMap = BlockChainCheck.checkChain(heightGetList,heightList,diff,chainNum);
					int chainListSize = checkResult.size() - 1;
					for(int chainSort = 0;chainSort < chainListSize;chainSort ++) {
						//等于0链异常
						if(checkResult.get(chainSort) == 0) {
							ServerByProject serverByProject = serverListByMap.get(chainSort);
							serverByProject.setStatus(0);
							nodeMonitorService.updateChainStatus(serverByProject);
							System.out.println("有链异常   异常链为  ： " + serverByProject.toString());
							
							ExceptionLog exceptionLog = new ExceptionLog();
							exceptionLog.setProjectId(serverByProject.getProjectId());
							exceptionLog.setServerId(serverByProject.getServerId());
							exceptionLog.setPort(serverByProject.getPort());
							exceptionLog.setState(0);
							exceptionLog.setContext("链   " + serverByProject.getOutIp() + serverByProject.getPort() + "  异常");
							nodeMonitorService.insertNodeException(exceptionLog);
							
						}
						
						
					}
					
					Integer projectStatus = checkResult.get(checkResult.size());
					if(projectStatus != projectInfo.getState()) {
						projectInfo.setState(projectStatus);
						nodeMonitorService.updateProjectState(projectInfo);
						System.out.println("有项目异常   异常项目为  ： " + projectInfo.toString());
					}
					
					
				}
				
				
//				List<ServerByProject> serverListWithoutMonitor = new ArrayList<ServerByProject>();
//				for(ServerByProject serverByProject : serverList) {
//					if(serverByProject.getIsMonitor() == 0) {
//						serverListWithoutMonitor.add(serverByProject);
//					}
//				}
				
//				return map;
				System.out.println("一次查询结束");
				System.out.println("————————————————————————————————————————————————————————————————————");
			}
			
			
		}
		
		

	}
}
