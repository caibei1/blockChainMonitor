package com.fuzamei.util;


import java.util.HashMap;
import java.util.Map;

/**
 * @author huang
 * 2018/4/10
 */
public class JSONUtil {
    public static Map<String,Object> getJsonMap(int code, boolean success, String message, Object data){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("code", code);
        map.put("success", success);
        map.put("message", message);
        map.put("data", data);
        return map;
    }
}
