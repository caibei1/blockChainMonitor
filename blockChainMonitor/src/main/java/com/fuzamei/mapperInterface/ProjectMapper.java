package com.fuzamei.mapperInterface;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fuzamei.entity.Application;
import com.fuzamei.entity.ExceptionLog;
import com.fuzamei.entity.ExceptionLogVO;
import com.fuzamei.entity.HistoryHeight;
import com.fuzamei.entity.Project;
import com.fuzamei.entity.ProjectServer;
import com.fuzamei.entity.Server;
import com.fuzamei.entity.User;
import com.fuzamei.entity.UserProject;

public interface ProjectMapper {
	
	//添加项目信息插project
	 public int insertProject(Project project);
	 
	 //通过项目名称找到项目ID
	 public int findProjectIdByName(String projectName);
	 
	 //查找所有服务器
	 public List<Server> findAllServer(int serverPurposeId);
	 
	 //通过服务器名称找到服务器id
	 public int findServerIdByName(String serverName);
 
	 //根据名字查找管理员信息
	 public int findUserByName(String name);
		
	 //添加节点服务器信息
	 public void insertProjectNodeServer(ProjectServer projectServer);
	 
	 //添加应用服务器信息
	 public void insertProjectApplictionServer(Application application);
	 
	 //添加管理者信息
	 public void insertProjectManager(UserProject userProject);
	 
	 //插链空数据供其他人用
	 public void insertHistoryHeight(HistoryHeight historyHeight);
	 
	 //查询异常记录
	 public List<ExceptionLog> queryException(ExceptionLogVO exceptionLogVO);

	 //查询异常记录总条数
	 public int queryExceptionCount(ExceptionLogVO exceptionLogVO);
}
