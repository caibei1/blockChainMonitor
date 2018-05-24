package com.fuzamei.web;

import com.fuzamei.constant.HintMSG;
import com.fuzamei.entity.Answer;
import com.fuzamei.service.TestService;
import com.fuzamei.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author huang
 * 2018/4/11
 */
@Controller
public class TestAction {
    @Autowired
    private TestService testService;
    Logger logger = Logger.getLogger(TestAction.class.getName());
    @RequestMapping(path = "/queryAnswer")
    private ModelAndView test(){
        logger.info("进入查询页面");
        try{
            ModelAndView mv = new ModelAndView();
            Map<String,Object> map = new HashMap<String, Object>();
            List<Answer> list = testService.queryAnswer();
            map.put("list",list);
            map = JSONUtil.getJsonMap(200,true, HintMSG.LOGIN_SUCCESS,map);
            mv.addObject("map",map);
            mv.setViewName("queryAnswer");
            return mv;
        }catch (Exception e){
            ModelAndView mv = new ModelAndView();
            Map map = JSONUtil.getJsonMap(300, false, HintMSG.LOGIN_FAIL+":"+e.getMessage(), null);
            mv.addObject("map",map);
            return mv;
        }
    }
}
