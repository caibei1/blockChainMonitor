package com.fuzamei.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuzamei.entity.Project;
import com.fuzamei.mapperInterface.ProjectMonitorMapperInterface;
import com.fuzamei.service.ProjectMonitorService;

@Service
public class ProjectMonitorServiceImpl implements ProjectMonitorService {

	@Autowired
	ProjectMonitorMapperInterface projectMonitorMapperInterface;

	@Override
	public List<Integer> getProjectIdInMonitorList() {
		// TODO Auto-generated method stub
		return projectMonitorMapperInterface.getProjectIdInMonitorList();
	}
	
	
}
