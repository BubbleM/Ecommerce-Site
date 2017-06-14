package com.bubble.controller;

import com.alibaba.fastjson.JSON;
import com.bubble.dao.UserDao;
import com.bubble.entity.UsersEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/getUsers.do")
    @ResponseBody
    public void getUsers(HttpServletResponse response){
        List<String> arr = new ArrayList<>();
        List<UsersEntity> users = dao.getUsers();
        System.out.println("get user info!");
        response.setCharacterEncoding("utf-8");
        Writer writer;
        try {
            writer = response.getWriter();
            String jsonString = JSON.toJSONString(users);
            System.out.println(jsonString);
            writer.append(JSON.toJSONString(users));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//            System.out.println(list);
//        for(UsersEntity user:list){
//            if(list != null){
//                String json = JSON.toJSONString(user);
//                arr.add(json);
//            }
//        }
//        return arr;
    }

    @RequestMapping("/add.do")
    public void userAdd(String firstname,String lastname,String password,String phone,String type,HttpServletResponse response){
        UsersEntity user = new UsersEntity();
        user.setName(firstname+lastname);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setPassword(password);
        user.setPhone(phone);
        user.setType(type);
        int r = dao.insertUser(user);
        response.setCharacterEncoding("utf-8");
        Writer writer = null;
        try {
            writer = response.getWriter();
            if(r>0){
                writer.append("OK");
            }else{
                writer.append("NO");
            }
            writer.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            if(writer != null){
                try {
                    writer.close();
                    writer = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping("/updateUser.do")
    public void updateUser(Integer id,String firstname,String lastname,String password,String phone,String type,HttpServletResponse response){
        System.out.println("*****************update user!************");
        String name = firstname+lastname;
        System.out.println("id="+id+" name="+name+" password="+password+" phone"+phone+" type="+type);
        UsersEntity user = dao.findUserById(id);
//        System.out.println(user.getName());
        user.setName(name);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setPhone(phone);
        user.setType(type);
        int r =  dao.saveOrUpdateUser(user);
        response.setCharacterEncoding("utf-8");
        Writer writer = null;
        try {
            writer = response.getWriter();
            if(r>0){
                writer.append("OK");
            }else{
                writer.append("NO");
            }
            writer.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            if(writer != null){
                try {
                    writer.close();
                    writer = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping("/delete.do")
    public void userDelete(Integer id,HttpServletResponse response){
        int r = dao.deleteUser(id);
        response.setCharacterEncoding("utf-8");
        Writer writer = null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            writer = response.getWriter();
            if(r>0){
                System.out.println("delete success!");
                map.put("success", true);
            }else{
                map.put("success", false);
                System.out.println("delete fail!");
            }
            writer.append(JSON.toJSONString(map));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(writer != null){
                try {
                    writer.close();
                    writer = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args){
        UserController u = new UserController();
//        u.updateUser(66,"Nicho","wei","12345432","18229060856","admin");
    }
}

