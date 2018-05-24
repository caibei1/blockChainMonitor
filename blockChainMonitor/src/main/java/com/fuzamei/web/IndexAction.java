package com.fuzamei.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

/**
 * @author huang
 * 2018/4/10
 */
@Controller
public class IndexAction {
    Logger logger = Logger.getLogger(IndexAction.class.getName());
    @RequestMapping(path = "/index")
    public String index(){
        logger.info("进入主页！");
        return "index";
    }
}
