package com.bubble.controller;

import com.bubble.dao.UserDao;
import com.bubble.entity.UsersEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bubble on 17-6-7.
 */
/*Controller注解　可以明确的定义该类为处理请求的Controller类*/
@Controller
@RequestMapping("/user")
public class UserController {

    UserDao dao = new UserDao();

    /*RequestMapping注解　用于定义一个请求映射,value为请求的url,值为/说明该请求首页请求　method指定请求类型*/
//    @RequestMapping(value = "/",method = RequestMethod.GET)
    @RequestMapping("/register.do")
    // xxx/user/register.do
    public String register(HttpServletRequest request, HttpServletResponse response, String name, String password) throws IOException {
//        String username = request.getParameter("name");
//        String password = request.getParameter("password");
        UsersEntity user = new UsersEntity();
        user.setName(name);
        user.setPassword(password);
        dao.insertUser(user);
        System.out.println(name);
        System.out.println(password);
        System.out.println("注册成功");
        return "index";
    }
    @RequestMapping("/login.do")
    public String login(){
        return "redirect:login";
    }
}

