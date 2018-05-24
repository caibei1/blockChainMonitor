package com.fuzamei.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fuzamei.entity.Application;
import com.fuzamei.entity.ExceptionLog;
import com.fuzamei.entity.ExceptionLogVO;
import com.fuzamei.entity.HistoryHeight;
import com.fuzamei.entity.Project;
import com.fuzamei.entity.ProjectServer;
import com.fuzamei.entity.ProjectVO;
import com.fuzamei.entity.Server;
import com.fuzamei.entity.UserProject;
import com.fuzamei.mapperInterface.ManagerMapper;
import com.fuzamei.mapperInterface.ProjectMapper;
import com.fuzamei.service.ProjectService;
import com.fuzamei.util.PageDTO;
import com.fuzamei.util.RandTimeUtils;
@Service
public class ProjectServiceImpl implements ProjectService {


	@Autowired
	private ProjectMapper projectMapper;
	
//	@Override
//	@Transactional(rollbackFor=Exception.class)
//	public void addProject(String json) {
//		//解析json
//		JSONObject jsonObject = JSON.parseObject(json);
//		JSONObject result =  jsonObject.getJSONObject("result");
//		JSONObject projectjson =result.getJSONObject("project");
//		JSONArray NodeServer = result.getJSONArray("NodeServer");		
//		JSONArray ApplicationServer = result.getJSONArray("ApplicationServer");
//		JSONArray projectManager =result.getJSONArray("projectManager");
//		//拿数据set
//		Project project2= new Project();
//		project2.setProjectName(projectjson.getString("projectName"));
//		project2.setDescription(projectjson.getString("description"));
//		project2.setCompany(projectjson.getString("company"));
//		project2.setVersion(projectjson.getString("version"));
//		project2.setState(1);
//		project2.setIsOnline(1);
//		int projectId1 = projectMapper.insertProject(project2);
//		int projectId = project2.getProjectId();
//		//插节点服务器
//		ProjectServer projectServer = new ProjectServer();
//		HistoryHeight historyHeight = new HistoryHeight();
//		for(int i=0;i<NodeServer.size();i++){
//			JSONObject node = NodeServer.getJSONObject(i);
//			projectServer.setProjectId(projectId);
//			projectServer.setIsMonitor(1);
//			int nodeServerId= projectMapper.findServerIdByName(node.getString("serverName"));
//			projectServer.setServerId(nodeServerId);
//			projectServer.setPort(node.getIntValue("port"));
//			projectMapper.insertProjectNodeServer(projectServer);
//			//插四条空高度到history_height
//			for(int m=0;m<4;m++){
//				historyHeight.setServerId(nodeServerId);
//				historyHeight.setHeight(0);
//				String updateTime= RandTimeUtils.createTime();
//				historyHeight.setUpdateTime(updateTime);
//				projectMapper.insertHistoryHeight(historyHeight);
//			}
//			
//		}
//		//插应用服务器
//		Application application= new Application();
//		for(int j=0;j<ApplicationServer.size();j++){
//			JSONObject application1 = ApplicationServer.getJSONObject(j);
//			application.setProjectId(projectId);
//			application.setUrl(application1.getString("url"));
//			//application.setPort(application1.getIntValue("port"));
//			application.setDescription(application1.getString("description"));
//			int applicationServerId= projectMapper.findServerIdByName(application1.getString("serverName"));
//			application.setServerId(applicationServerId);
//			projectMapper.insertProjectApplictionServer(application);
//		}
//		//插管理员
//		UserProject userProject = new UserProject();
//		for(int n=0;n<projectManager.size();n++){
//			JSONObject projectmanager = projectManager.getJSONObject(n);
//			userProject.setProjectId(projectId);
//			int userId =projectMapper.findUserByName(projectmanager.getString("userName"));
//			userProject.setUserId(userId);
//			projectMapper.insertProjectManager(userProject);
//		}
//	}

	@Override
	public PageDTO queryException(ExceptionLogVO exceptionLogVO) {
		List<ExceptionLog> list = projectMapper.queryException(exceptionLogVO);
		int count = projectMapper.queryExceptionCount(exceptionLogVO);
		return PageDTO.getPagination(count, list);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addProjectNew(ProjectVO projectVO) {
		String[] ManagerIds = projectVO.getAdminIds().split(",");
		String[] machineIds = projectVO.getMachineIds().split(",");
		String[] webPorts = projectVO.getWebPorts().split(",");
		//先插入project表拿到project_id
		Project project = new Project();
		project.setProjectName(projectVO.getName());
		project.setCompany(projectVO.getCompany());
		project.setDescription(projectVO.getDescribe());
		project.setVersion(projectVO.getVersion());
		project.setState(1);
		project.setIsOnline(1);
		int projectId1 = projectMapper.insertProject(project);
		int projectId = project.getProjectId();
		//插节点服务器信息
		ProjectServer projectServer = new ProjectServer();
		HistoryHeight historyHeight = new HistoryHeight();
		projectServer.setPort(Integer.parseInt(projectVO.getNodePort()));
		projectServer.setIsMonitor(1);
		projectServer.setProjectId(projectId);
		for(int i=0;i<machineIds.length;i++){
			projectServer.setServerId(Integer.parseInt(machineIds[i]));
			projectMapper.insertProjectNodeServer(projectServer);
			//插四条空高度到history_height
			for(int m=0;m<4;m++){
				historyHeight.setProjectId(projectId);
				historyHeight.setServerId(Integer.parseInt(machineIds[i]));
				historyHeight.setHeight(0);
				String updateTime= RandTimeUtils.createTime();
				historyHeight.setUpdateTime(updateTime);
				projectMapper.insertHistoryHeight(historyHeight);
			}
		}
		//插应用服务器信息
		Application application= new Application();
		for(int i=0;i<webPorts.length;i++){
			String[] webPorts1 = webPorts[i].split(":");
			application.setPort(webPorts1[1]);
			application.setDescription(webPorts1[0]);
			application.setProjectId(projectId);
			application.setUrl(projectVO.getWebCountPort());
			application.setServerId(Integer.parseInt(projectVO.getWebMachine()));
			projectMapper.insertProjectApplictionServer(application);
		}
		//插管理员
		UserProject userProject = new UserProject();
		for(int n=0;n<ManagerIds.length;n++){
			userProject.setProjectId(projectId);
			userProject.setUserId(Integer.parseInt(ManagerIds[n]));
			projectMapper.insertProjectManager(userProject);
		}
	}
	
	
	

}
