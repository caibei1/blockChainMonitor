package com.fuzamei.mapperInterface;

import com.fuzamei.entity.ExceptionLog;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface ExceptionLogMapper {
	boolean insertAppLog(ExceptionLog exceptionLog);
   boolean updateExceptionState(@Param("id")int id,@Param("state")int state);
}
