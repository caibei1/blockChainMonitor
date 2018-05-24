package com.fuzamei.service;

import com.fuzamei.entity.Application;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ApplicationService {
	   boolean insertApplication(Application application);
	   boolean deleteByApplicationId(@Param("applicationId")int applicationId);
	   boolean updateAppMonitor(Application application);
	   boolean updateAppState(Application application);
	   boolean updateApplication(Application application);
	   List<Application>  selectByProjectId(@Param("projectId")int projectId);
	   List<Application>  selectByApplicationId(@Param("applicationId")int applicationId);
	   List<Application>  selectForMonitor(@Param("projectId")int projectId);
	   List<Application>  selectAppStatus(@Param("projectId")int projectId);
}
