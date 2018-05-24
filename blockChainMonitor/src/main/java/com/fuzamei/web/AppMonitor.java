package com.fuzamei.web;

import com.fuzamei.constant.HintEnum;
import com.fuzamei.entity.Application;
import com.fuzamei.service.ApplicationService;
import com.fuzamei.util.JSONUtil;
import com.fuzamei.util.ResultResp;

import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="v1/App")
public class AppMonitor {
    @Autowired
    ApplicationService applicationService;
	/**
	 * @Title: insertApplication
	 * @Description: 插入应用接口
	 * @Detail: 
	 * @AddFuntion:
	 * @Usage: 
	 * @return ResultResp
	 * @author hsw
	 * @date 2018 5/24 15点28分
	 */
//    @RequestMapping(value="insertApplication",method = RequestMethod.POST)
//    public ResultResp insertApplication(@RequestBody Application application){
//    	
//    	try {
//    		applicationService.insertApplication(application);
//    		return ResultResp.getResult(HintEnum.OPERATION_SUCCESS.getCode(),true,HintEnum.OPERATION_SUCCESS.getHintMsg(), null);
//    	}catch (Exception e) {
//			// TODO: handle exception
//    		return ResultResp.getResult(HintEnum.OPERATION_FAIL.getCode(), false, HintEnum.OPERATION_SUCCESS.getHintMsg(), null);
//		}
//    }
    /**
	 * @Title: insertApplication
	 * @Description: 删除应用接口
	 * @Detail: 
	 * @AddFuntion:
	 * @Usage: 
	 * @return ResultResp
	 * @author hsw
	 * @date 2018 5/24 15点28分
	 */
    @RequestMapping(value="deleteByApplicationId",method = RequestMethod.POST)
    public ResultResp deleteByApplicationId(@RequestParam(value="ApplicationId") int ApplicationId){
    	try {
    		applicationService.deleteByApplicationId(ApplicationId);
    		return ResultResp.getResult(HintEnum.DELETE_SUCCESS.getCode(),true,HintEnum.DELETE_SUCCESS.getHintMsg(), null);
    	}catch (Exception e) {
			// TODO: handle exception
    		return ResultResp.getResult(HintEnum.DELETE_FAIL.getCode(), false, HintEnum.DELETE_FAIL.getHintMsg(), null);
		}
    }
    /**
   	 * @Title: insertApplication
   	 * @Description: 更新应用接口
   	 * @Detail: 
   	 * @AddFuntion:
   	 * @Usage: 
   	 * @return ResultResp
   	 * @author hsw
   	 * @date 2018 5/24 15点28分
   	 */
    @RequestMapping(value="updateAppState",method = RequestMethod.POST)
    public ResultResp updateAppState(@RequestBody Application application){
    	try {
    		applicationService.updateAppState(application);
    		return ResultResp.getResult(HintEnum.OPERATION_SUCCESS.getCode(),true,HintEnum.OPERATION_SUCCESS.getHintMsg(), null);
    	}catch (Exception e) {
			// TODO: handle exception
    		return ResultResp.getResult(HintEnum.OPERATION_FAIL.getCode(), false, HintEnum.OPERATION_SUCCESS.getHintMsg(), null);
		}
    }
    /**
   	 * @Title: insertApplication
   	 * @Description: 查询应用接口
   	 * @Detail: 根据项目id查询总的应用
   	 * @AddFuntion:
   	 * @Usage: 
   	 * @return 所有的应用信息
   	 * @author hsw
   	 * @date 2018 5/24 15点28分
   	 */
    @RequestMapping(value="selectByProjectId",method = RequestMethod.POST)
    public ResultResp selectByProjectId(@RequestParam(value="projectId") int projectId){
    	try {
    		List<Application> data =applicationService.selectByProjectId(projectId);
    		return ResultResp.getResult(HintEnum.QUERY_SUCCESS.getCode(),true,HintEnum.QUERY_SUCCESS.getHintMsg(), data);
    	}catch (Exception e) {
			// TODO: handle exception
    		return ResultResp.getResult(HintEnum.QUERY_FAIL.getCode(), false, HintEnum.QUERY_FAIL.getHintMsg(), null);
		}
    }
    /**
   	 * @Title: insertApplication
   	 * @Description: 查询应用用于监控接口
   	 * @Detail: 根据项目id查询用于监控的应用信息
   	 * @AddFuntion:
   	 * @Usage: 
   	 * @return  port,description,status
   	 * @author hsw
   	 * @date 2018 5/24 15点28分
   	 */
    @RequestMapping(value="project-status",method = RequestMethod.GET)
    public ResultResp selectProjectStatus(@RequestParam(value="projectId") int projectId){
    	try {
    		applicationService.selectAppStatus(projectId);
    		return ResultResp.getResult(HintEnum.QUERY_SUCCESS.getCode(),true,HintEnum.QUERY_SUCCESS.getHintMsg(), null);
    	}catch (Exception e) {
			// TODO: handle exception
    		return ResultResp.getResult(HintEnum.QUERY_FAIL.getCode(), false, HintEnum.QUERY_FAIL.getHintMsg(), null);
		}
    }
    
    /**
   	 * @Title: insertApplication
   	 * @Description: 更新应用
   	 * @Detail: 
   	 * @AddFuntion:
   	 * @Usage: 
   	 * @return 
   	 * @author hsw
   	 * @date 2018 5/24 15点28分
   	 */
    @RequestMapping(value="updateApplication",method = RequestMethod.POST)
    public ResultResp updateApplication(@RequestBody Application application){
    	try {
    		applicationService.updateApplication(application);
    		return ResultResp.getResult(HintEnum.OPERATION_SUCCESS.getCode(),true,HintEnum.OPERATION_SUCCESS.getHintMsg(), null);
    	}catch (Exception e) {
			// TODO: handle exception
    		return ResultResp.getResult(HintEnum.OPERATION_FAIL.getCode(), false, HintEnum.OPERATION_SUCCESS.getHintMsg(), null);
		}
    }
    
}
