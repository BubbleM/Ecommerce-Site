package com.bubble.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by bubble on 17-6-6.
 */
/*Controller注解　可以明确的定义该类为处理请求的Controller类*/
@Controller
public class MainController {

    /*RequestMapping注解　用于定义一个请求映射,value为请求的url,值为/说明该请求首页请求　method指定请求类型*/
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";//处理完请求后返回的页面index.jsp
    }
}
