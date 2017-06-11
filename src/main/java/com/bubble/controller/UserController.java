package com.bubble.controller;

import com.bubble.dao.UserDao;
import com.bubble.entity.UsersEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public String register(HttpServletRequest request, HttpServletResponse response, String username, String password) throws IOException {
//        String username = request.getParameter("name");
//        String password = request.getParameter("password");
        UsersEntity user = new UsersEntity();
        if(!dao.findUserByName(username)){
            user.setName(username);
            user.setPassword(password);
            dao.insertUser(user);
            System.out.println(username);
            System.out.println(password);
            System.out.println("注册成功");
            return "login";
        }else{
            return "error";
        }
    }
    @RequestMapping("/login.do")
    @ResponseBody
    public String login(String username, String password){
        System.out.println(username);
        System.out.println(password);
        String pwd = dao.findPwdByName(username);
        if(pwd.equals(password)){
            return "true";
        }else {
            return "false";
        }
    }
}

