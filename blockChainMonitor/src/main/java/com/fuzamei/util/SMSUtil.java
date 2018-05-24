package com.fuzamei.util;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SMSUtil {
	public static void sendSMS(String phonenumber,String messageTxt) throws Exception {
        HttpClient client = new HttpClient();  
        PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");  
        post.addRequestHeader("Content-Type",  "application/x-www-form-urlencoded;charset=gbk"); //在头文件中设置转码  
        NameValuePair[] data = { new NameValuePair("Uid", "yehanlong"),        //注册的用户名  
        new NameValuePair("Key", "d41d8cd98f00b204e980"),   //注册成功后，登录网站，在"修改短信接口密钥"这一栏里面
        new NameValuePair("smsMob", phonenumber),               // 需要发送的手机号码  
        new NameValuePair("smsText", messageTxt) };           //需要发送的短信内容            
//        new NameValuePair("smsMob", "13588208796"),               // 需要发送的手机号码  
//        new NameValuePair("smsText", "验证码：9999【复杂美公司】") };           //需要发送的短信内容    
        post.setRequestBody(data);  

        client.executeMethod(post);  
        Header[] headers = post.getResponseHeaders();  
        int statusCode = post.getStatusCode();  

        String result = new String(post.getResponseBodyAsString().getBytes( "gbk"));  
        System.out.println(result);  
        post.releaseConnection();  
}  

}
