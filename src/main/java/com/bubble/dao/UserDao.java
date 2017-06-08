package com.bubble.dao;

import com.bubble.entity.UsersEntity;
import com.bubble.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;

/**
 * Created by bubble on 17-6-7.
 */
public class UserDao {

    /*
    *插入新用户
    */
    public void insertUser(UsersEntity user){
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

    /*
    * 管理员根据id删除相应用户
    */
    public void deleteUser(Integer id){
        Session session = null;
    }

    /*
    * 根据用户输入的用户名查找密码
    */

    public String findPwdByName(String name){
        Session session = null;
        String pwd = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();
            String hql = "select u.password from UsersEntity u where name='"+name+"' ";
            List list = session.createQuery(hql).list();
            Iterator it = list.iterator();
            while (it.hasNext()){
                pwd = (String) it.next();
            }
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction();
        }
        return pwd;
    }

    public static void main(String[] args){
        UserDao dao = new UserDao();
        UsersEntity user = null;
//      String password =  dao.findPwdByName("BubbleM");
//        String password = user.getPassword();
//        System.out.print(password);
//        System.out.println(user.getName());
    }
}
