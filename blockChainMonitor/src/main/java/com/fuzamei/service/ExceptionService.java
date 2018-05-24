package com.fuzamei.service;

import java.util.List;
import java.util.Map;

import com.fuzamei.entity.ExceptionInfo;
import com.fuzamei.entity.ExceptionLog;

public interface ExceptionService {
	List<ExceptionInfo> getExceptionLogList();
	ExceptionInfo getExceptionLogListMore(ExceptionInfo exceptionInfo);
	Integer dealLogByProjectId(ExceptionInfo exceptionInfo);
}
