package com.bubble.util;

import com.bubble.entity.UsersEntity;
import org.hibernate.Session;

/**
 * Created by bubble on 17-6-7.
 */
public class Save {
    public static void main(String[] args){
        Session session = null; //声明Session对象
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();
            UsersEntity user = new UsersEntity();
            user.setName("BubbleM");
            user.setPassword("123456");
            session.save(user);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            HibernateUtil.closeSession(session);
        }

    }
}
