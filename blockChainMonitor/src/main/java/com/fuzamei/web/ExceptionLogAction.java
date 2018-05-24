package com.fuzamei.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fuzamei.constant.HintEnum;
import com.fuzamei.entity.ExceptionInfo;
import com.fuzamei.entity.ExceptionLog;
import com.fuzamei.service.ExceptionService;
import com.fuzamei.util.ResultResp;

@RequestMapping(value = "alarm-log")
@RestController
public class ExceptionLogAction {
	
	@Autowired
	ExceptionService exceptionService;
	
	@RequestMapping(value = "list")
	public List<ExceptionInfo> list(){
		List<ExceptionInfo> list = exceptionService.getExceptionLogList();
		return list;
	}
	
	@RequestMapping(value = "info")
	public ResultResp info(@RequestBody ExceptionInfo exceptionInfo) {
		try {
			ExceptionInfo exceptionInfo2 = exceptionService.getExceptionLogListMore(exceptionInfo);
			return ResultResp.getResult(HintEnum.QUERY_SUCCESS.getCode(),true,HintEnum.QUERY_SUCCESS.getHintMsg(),exceptionInfo2);
		}catch (Exception e) {
			// TODO: handle exception
			return ResultResp.getResult(HintEnum.QUERY_FAIL.getCode(),true,HintEnum.QUERY_FAIL.getHintMsg(),null);
		}
	}
	
	@RequestMapping(value = "log-update")
	public ResultResp logUpdate(@RequestBody ExceptionInfo exceptionInfo){
		try {
			Map<String,Object> map = new HashMap<>();
			ExceptionInfo exceptionInfo2  = exceptionService.getExceptionLogListMore(exceptionInfo);
			System.out.println("exceptionInfo2 " + exceptionInfo2.toString());
			int state = exceptionInfo2.getState();
			if(state == 1) {
				ExceptionInfo returnExceptionInfo = new ExceptionInfo();
				returnExceptionInfo.setId(exceptionInfo2.getId());
				return ResultResp.getResult(HintEnum.OPERATION_SUCCESS.getCode(),true,"日志已被处理,无需处理",returnExceptionInfo);
			}
			Integer id = exceptionService.dealLogByProjectId(exceptionInfo2);
			if(id == -1) {
				return ResultResp.getResult(HintEnum.OPERATION_FAIL.getCode(),false,"处理日志失败",id);
			}
			
			return ResultResp.getResult(HintEnum.OPERATION_SUCCESS.getCode(),true,"处理日志成功",id);
		}catch (Exception e) {
			// TODO: handle exception
			return ResultResp.getResult(HintEnum.OPERATION_FAIL.getCode(), false, "处理日志失败", null);
		}
		
		
	}
	
}
