package com.fuzamei.quartz;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fuzamei.entity.Application;
import com.fuzamei.entity.ExceptionLog;
import com.fuzamei.service.ApplicationService;
import com.fuzamei.service.ExceptionLogService;
import com.fuzamei.util.JSONUtil;
@Component
public class AppQuartz{
	@Autowired
	ExceptionLogService exceptionLogService;
	@Autowired
	ApplicationService applicationService;
	public void service() {
		// TODO Auto-generated method stub
		try {
			 Application application =new Application();
			 Application state =new Application();
			ExceptionLog exce =new ExceptionLog();
			int   responseCode=0;
			String   ResponseMessage="";		
				int projectId =1568;
				System.out.println("----------------------");
				List<Application> data =applicationService.selectForMonitor(projectId);
				 for(int i=0;i<data.size();i++) {
						try {
				  application=	 data.get(i);
//				  System.out.println(application.toString());
				  String url ="http://"+application.getUrl();
				  URL realUrl = new URL(url);
				  HttpURLConnection   httpConnection   =   (HttpURLConnection)realUrl.openConnection();  
				  httpConnection.getURL();
				  responseCode=httpConnection.getResponseCode();  
				  ResponseMessage=httpConnection.getResponseMessage();
				  if( responseCode==200) {
					  state.setApplicationId(application.getApplicationId());
					  state.setStatus(1);
					  applicationService.updateAppState(state);
				  }  
				  if( responseCode!=200) {
					  state.setApplicationId(application.getApplicationId());
					  state.setStatus(0);
					  applicationService.updateAppState(state);
					  exce.setProjectId(projectId);
					  exce.setApplicationId(application.getApplicationId());
					  exce.setContext("应用"+application.getDescription()+"：异常");
					  exceptionLogService.insertAppLog(exce);
				  }
						}catch (Exception e) {
							// TODO: handle exception
							  state =new Application();
							  state.setApplicationId(application.getApplicationId());
							  state.setStatus(0);
							  applicationService.updateAppState(state);
							  exce.setProjectId(projectId);
							  exce.setApplicationId(application.getApplicationId());
							  exce.setContext("应用"+application.getDescription()+"：异常");
							  exceptionLogService.insertAppLog(exce);
							System.out.println("------------"+e.toString());
						}
				 }
		
//	          URLConnection conn = realUrl.openConnection();
	}catch (Exception e) {
		// TODO: handle exception
		System.out.println("------------"+e.toString());
	}
	}

}
