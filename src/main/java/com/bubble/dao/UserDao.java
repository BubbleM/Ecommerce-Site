package com.bubble.dao;

import com.bubble.entity.UsersEntity;
import com.bubble.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Created by bubble on 17-6-7.
 */
public class UserDao {
    public void savaUser(UsersEntity user){
        Session session = null; //声明Session对象
        try {
            session = HibernateUtil.getSession(); //获取Session
            session.beginTransaction();//开启事务
            session.save(user);  //保存用户信息
            session.getTransaction().commit(); //提交事务
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}
