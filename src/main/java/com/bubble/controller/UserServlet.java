package com.bubble.controller;

import com.bubble.dao.UserDao;
import com.bubble.entity.UsersEntity;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

/**
 * Created by bubble on 17-6-7.
 */
public class UserServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户注册信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username != null && password != null){
            UsersEntity user = new UsersEntity();
            user.setName(username);
            user.setPassword(password);
            UserDao dao = new UserDao(); //
            dao.savaUser(user);
            request.setAttribute("info","恭喜,注册成功!");
        }
        request.getRequestDispatcher("index.jsp").forward(request,response);//转发到首页
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
