package com.fuzamei.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fuzamei.constant.HintEnum;
import com.fuzamei.entity.Application;
import com.fuzamei.entity.PojoServerPurpose;
import com.fuzamei.entity.ServerPurpose;
import com.fuzamei.service.ServerPurposeService;
import com.fuzamei.util.ResultResp;

@RestController
@RequestMapping(value="v1/serverPurpose")
public class ServerPurposeAction {
	@Autowired
	ServerPurposeService serverPurposeService;
	  /**
   	 * @Title: insertApplication
   	 * @Description: 插入服务器用途信息
   	 * @Detail: 
   	 * @AddFuntion:
   	 * @Usage: 
   	 * @return 
   	 * @author hsw
   	 * @date 2018 5/24 15点28分
   	 */
	    @RequestMapping(value="insert",method = RequestMethod.POST)
	    public ResultResp insertServerPurpose(@RequestBody ServerPurpose serverPurpose){
	    	try {
	    		serverPurposeService.insertServerPurpose(serverPurpose);
	    		return ResultResp.getResult(HintEnum.OPERATION_SUCCESS.getCode(),true,HintEnum.OPERATION_SUCCESS.getHintMsg(), null);
	    	}catch (Exception e) {
				// TODO: handle exception
	    		return ResultResp.getResult(HintEnum.OPERATION_FAIL.getCode(), false, HintEnum.OPERATION_SUCCESS.getHintMsg(), null);
			}
	    }
	    /**
	   	 * @Title: insertApplication
	   	 * @Description: 查询服务器用途
	   	 * @Detail: 
	   	 * @AddFuntion:
	   	 * @Usage: 
	   	 * @return  
	   	 * @author hsw
	   	 * @date 2018 5/24 15点28分
	   	 */
	    @RequestMapping(value="select",method = RequestMethod.GET)
	    public ResultResp selectByProjectId(){
	    	try {
	    		List<ServerPurpose> data =serverPurposeService.selectAll();
	    		return ResultResp.getResult(HintEnum.QUERY_SUCCESS.getCode(),true,HintEnum.QUERY_SUCCESS.getHintMsg(), data);
	    	}catch (Exception e) {
				// TODO: handle exception
	    		return ResultResp.getResult(HintEnum.QUERY_FAIL.getCode(), false, HintEnum.QUERY_FAIL.getHintMsg(), null);
			}
	    }
	    /**
	   	 * @Title: insertApplication
	   	 * @Description: 更新服务器用途
	   	 * @Detail: 
	   	 * @AddFuntion:
	   	 * @Usage: 
	   	 * @return  
	   	 * @author hsw
	   	 * @date 2018 5/24 15点28分
	   	 */
	    @RequestMapping(value="update",method = RequestMethod.POST)
	    public ResultResp updateApplication(@RequestBody PojoServerPurpose pojoServerPurpose){
	    	try {
	    		serverPurposeService.updateServerPurpose(pojoServerPurpose);
	    		return ResultResp.getResult(HintEnum.OPERATION_SUCCESS.getCode(),true,HintEnum.OPERATION_SUCCESS.getHintMsg(), null);
	    	}catch (Exception e) {
				// TODO: handle exception
	    		return ResultResp.getResult(HintEnum.OPERATION_FAIL.getCode(), false, HintEnum.OPERATION_SUCCESS.getHintMsg(), null);
			}
	    }
}
