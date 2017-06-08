package com.bubble.dao;

import com.bubble.entity.UsersEntity;
import com.bubble.util.HibernateUtil;
import org.hibernate.Query;
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
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            UsersEntity user = session.load(UsersEntity.class,new Integer(id));
            session.delete(user);
            session.getTransaction().commit();
            System.out.println("删除成功"+id);
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    /*
    * 修改用户密码
    */
    public void updatePwdByName(String name,String password){
        Session session = null;
        String pwd = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();
            String hql = "update UsersEntity set password='"+password+"' where name='"+name+"' ";
            int result = session.createQuery(hql).executeUpdate();
            if(result == 1){
                System.out.println("update success");
            }else {
                System.out.println("sorry please try agagin");
            }
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction();
        }
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

    /*
    * 根据输入的用户名查询是否已经存在
    */
    public boolean findUserByName(String name){
        Session session = null;
        String pwd = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();
            String hql = "from UsersEntity user where user.name='"+name+"'";
            Query query = session.createQuery(hql);
            List<UsersEntity> list = query.list();
            for(UsersEntity user:list){
                if(list != null){
                    System.out.println("该用户已经存在");
                    return true;
                }
            }
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction();
        }
        return false;
    }

    public static void main(String[] args){
        UserDao dao = new UserDao();
//        UsersEntity user = null;
//        dao.deleteUser(42);
//        dao.updatePwdByName("ddds","099");
//      String password =  dao.findPwdByName("BubbleM");
//        System.out.println(dao.findUserByName("jj"));
//        String password = user.getPassword();
//        System.out.print(password);
//        System.out.println(user.getName());
    }
}
