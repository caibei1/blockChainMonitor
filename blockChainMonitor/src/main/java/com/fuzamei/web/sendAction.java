package com.fuzamei.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fuzamei.constant.HintEnum;
import com.fuzamei.entity.User;
import com.fuzamei.service.ManagerService;
import com.fuzamei.util.EmailUtil;
import com.fuzamei.util.ResultResp;
import com.fuzamei.util.SMSUtil;

@RestController
@RequestMapping(value="/send")
public class sendAction {

		@Autowired
		private ManagerService managerService;
		//邮件
	    @RequestMapping(value="/sendEmail" , method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	    @ResponseBody
	    public ResultResp sendEmail(@RequestBody String name,HttpServletRequest request)throws Exception, ClassNotFoundException {
	      try{
	    	  User user = managerService.findByName(name);
		      Map<String,String> map = new HashMap<String,String>();
		      String msg="ok";
		      String toEMAIL = user.getEmail();
		      String TITLE= "监控系统异常报警";
		      String CONTENT ="";//查询异常记录所得
//		      String msg = "ok";   //发送状态
//		      String toEMAIL = "13588208796@163.com"; //对方邮箱
//		      String TITLE = "hello";          //标题
//		      String CONTENT ="email";        //内容
		      EmailUtil.sendEmail(toEMAIL, TITLE, CONTENT);
		      map.put("result", msg);
		      return ResultResp.getResult(HintEnum.SENDEMAIL_SUCCESS.getCode(), true, HintEnum.SENDTXT_SUCCESS.getHintMsg(), map);
	      }catch(Exception exception){
	    	  return ResultResp.getResult(HintEnum.SENDEMAIL_FAIL.getCode(), false, HintEnum.SENDEMAIL_FAIL.getHintMsg()+exception.getMessage(), null);
	      }
	    }
	    //短信
	    @RequestMapping(value="/sendTxt")
	    public ResultResp sendTxt(@RequestBody String name) throws Exception{
	    	try{
	    		String phonenumber = "13588208796";
		    	String messageTxt ="验证码：9999【复杂美公司】";
//		    	User user = managerService.findByName(name);	    	
//		    	String phonenumber =user.getPhone().toString();
//		    	//查询异常表
//		    	String messageTxt ="";
		    	SMSUtil.sendSMS(phonenumber, messageTxt);
		    	return ResultResp.getResult(HintEnum.SENDTXT_SUCCESS.getCode(), true, HintEnum.SENDTXT_SUCCESS.getHintMsg(), null);
	    	}catch(Exception exception){
	    		return ResultResp.getResult(HintEnum.SENDTXT_FAIL.getCode(), false, HintEnum.SENDTXT_FAIL.getHintMsg()+exception.getMessage(), null);
	    	}

	    }
}
