package com.fuzamei.service;

import com.fuzamei.entity.ExceptionLog;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface ExceptionLogService {
	boolean insertAppLog(ExceptionLog exceptionLog);
	   boolean updateExceptionState(@Param("id")int id,@Param("state")int state);
}
