package com.fuzamei.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fuzamei.constant.HintEnum;
import com.fuzamei.entity.ExceptionLog;
import com.fuzamei.entity.ExceptionLogVO;
import com.fuzamei.entity.ProjectVO;
import com.fuzamei.service.ProjectService;
import com.fuzamei.util.PageDTO;
import com.fuzamei.util.ResultResp;
import com.fuzamei.util.ValidationUtil;



@RestController
@RequestMapping(value="/addProject")
public class ProjectAction {

	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResultResp addProject(@RequestBody ProjectVO projectVO){
		try{
			ValidationUtil.checkEmptyString(projectVO.getName());
			ValidationUtil.checkEmptyString(projectVO.getCompany());
			ValidationUtil.checkEmptyString(projectVO.getVersion());
			ValidationUtil.checkEmptyString(projectVO.getDescribe());
			ValidationUtil.checkAndAssignInt(projectVO.getNodePort());
		}catch(Exception exception){
			return ResultResp.getResult(HintEnum.VALI_FAIL.getCode(), false, HintEnum.VALI_FAIL.getHintMsg()+exception.getMessage(), null);
		}
		try{					
			projectService.addProjectNew(projectVO);
			return ResultResp.getResult(HintEnum.ADDPROJECT_SUCCESS.getCode(), true, HintEnum.ADDPROJECT_SUCCESS.getHintMsg(), null);
		}catch(Exception exception){
			return ResultResp.getResult(HintEnum.ADDPROJECT_FAIL.getCode(), false, HintEnum.ADDPROJECT_FAIL.getHintMsg()+exception.getMessage(), null);
		}
		
	}
	
	@RequestMapping(value="queryException")
	@ResponseBody
	public ResultResp queryException(@RequestBody ExceptionLogVO exceptionLogVO){
		try {
			int page = ValidationUtil.checkMinAndAssignInt(exceptionLogVO.getPage(), 1);
			int rowNum=ValidationUtil.checkMinAndAssignInt(exceptionLogVO.getRowNum(), 1);
			exceptionLogVO.setStartPage((page-1)*rowNum);
		} catch (Exception e) {
			return ResultResp.getResult(HintEnum.VALI_FAIL.getCode(), false, HintEnum.VALI_FAIL.getHintMsg()+":"+e.getMessage(), null);
		}
		try{
			PageDTO pageDTO = projectService.queryException(exceptionLogVO);
			return ResultResp.getResult(HintEnum.QUERYEXCEPTION_SUCCESS.getCode(),true,HintEnum.QUERYEXCEPTION_SUCCESS.getHintMsg(),pageDTO);
		}catch(Exception exception){
			return ResultResp.getResult(HintEnum.QUERYEXCEPTION_FAIL.getCode(), false,HintEnum.QUERYEXCEPTION_FAIL.getHintMsg()+exception.getMessage(), null);
		}
		
	}
			
}
