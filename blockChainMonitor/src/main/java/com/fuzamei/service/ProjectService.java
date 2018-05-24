package com.fuzamei.service;

import java.util.List;

import com.fuzamei.entity.ExceptionLog;
import com.fuzamei.entity.ExceptionLogVO;
import com.fuzamei.entity.ProjectVO;
import com.fuzamei.util.PageDTO;

public interface ProjectService {
	
	//public void addProject(String json);
	
	public void addProjectNew(ProjectVO projectVO);
	
	public PageDTO queryException(ExceptionLogVO exceptionLogVO);

}
