package com.fuzamei.service.serviceImpl;

import com.fuzamei.entity.Application;
import com.fuzamei.entity.ExceptionLog;
import com.fuzamei.mapperInterface.ApplicationMapper;
import com.fuzamei.mapperInterface.ExceptionLogMapper;
import com.fuzamei.service.ApplicationService;
import com.fuzamei.service.ExceptionLogService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

@Service
public class ExceptionImpl implements ExceptionLogService{
	@Autowired
	ExceptionLogMapper exceptionLogMapper;
	@Override
	public boolean insertAppLog(ExceptionLog exceptionLog) {
		// TODO Auto-generated method stub
		return exceptionLogMapper.insertAppLog(exceptionLog);
	}

	@Override
	public boolean updateExceptionState(int id, int state) {
		// TODO Auto-generated method stub
		return exceptionLogMapper.updateExceptionState(id, state);
	}
}
