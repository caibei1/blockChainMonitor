package com.fuzamei.mapperInterface;

import java.util.List;

import com.fuzamei.entity.Project;

public interface ProjectMonitorMapperInterface {
	List<Project> getProjectList();
	List<Integer> getProjectIdInMonitorList();
}
