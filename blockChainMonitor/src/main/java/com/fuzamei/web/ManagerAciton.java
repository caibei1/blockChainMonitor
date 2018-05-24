package com.fuzamei.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fuzamei.constant.HintEnum;
import com.fuzamei.entity.User;
import com.fuzamei.service.ManagerService;
import com.fuzamei.util.ResultResp;

@RestController
@RequestMapping(value="/manager")
public class ManagerAciton {
    @Autowired
    private ManagerService managerService;
    
    @RequestMapping(value="/allManager",method=RequestMethod.POST)
    public List<User> selectAllManager(){
    	return managerService.selectAllManager();
    }
    
    @RequestMapping(value="/insertManager")
    public ResultResp insert(@RequestBody User user){
    	try{
    		
    		
    		User user2 = managerService.findByName(user.getUsername());
    		if(user2 == null){
        		managerService.insertmanager(user);
        		return ResultResp.getResult(HintEnum.INSERT_SUCCESS.getCode(), true, HintEnum.INSERT_SUCCESS.getHintMsg(), null);
    		}else{
    			return ResultResp.getResult(HintEnum.INSERT_FAIL.getCode(), false,HintEnum.UPDATE_FAIL.getHintMsg(),null);
    		}    		
    	}catch(Exception exception){
    		return ResultResp.getResult(HintEnum.INSERT_FAIL.getCode(), false,HintEnum.INSERT_FAIL.getHintMsg()+exception.getMessage(),null);
    	}
    }
    
    @RequestMapping(value="/updateManager")
    public ResultResp update(@RequestBody User user){
    	try{
    		User user2 = managerService.findByName(user.getUsername());
    		if(user2 == null){       		
        		return ResultResp.getResult(HintEnum.UPDATE_FAIL.getCode(), false, HintEnum.UPDATE_FAIL.getHintMsg(), null);
    		}else{
    			managerService.updatemanager(user);
    			return ResultResp.getResult(HintEnum.UPDATE_SUCCESS.getCode(),true ,HintEnum.UPDATE_SUCCESS.getHintMsg(),null);
    		}    		
    	}catch(Exception exception){
    		return ResultResp.getResult(HintEnum.UPDATE_FAIL.getCode(), false,HintEnum.UPDATE_FAIL.getHintMsg()+exception.getMessage(),null);
    	}
    }
}
