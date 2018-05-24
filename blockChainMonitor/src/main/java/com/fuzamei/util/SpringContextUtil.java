package com.fuzamei.util;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * @author hbj
 * 2018年5月8日-上午10:23:27
 */
public class SpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext = null;

    @Override
    @SuppressWarnings("static-access" ) 
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         this.applicationContext = applicationContext;
    }

    public static ApplicationContext  getApplicationContext(){
        return applicationContext;
    }

    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }

    public static Object getBean(Class c){
    	System.out.println(applicationContext.getBean(c));
        return applicationContext.getBean(c);
    }
    public static String getMessage(String key){  
        return applicationContext.getMessage(key, null, Locale.getDefault());  
    }  
}
