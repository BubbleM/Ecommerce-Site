package com.bubble.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Created by bubble on 17-6-7.
 */
//Hibernate的初始化类
    /*
    * 在Hibernate初始化类中将SessionFactory对象置于静态块中,实现在程序的应用过程中对其只创建一次，从而节省资源的占用
    * */
public class HibernateUtil {
    private static SessionFactory factory = null;

    static {
        try {
            Configuration cfg = new Configuration().configure(); //加载Hibernate配置文件
            factory = cfg.buildSessionFactory(); //实例化SessionFactory
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    //    获取Session对象的方法
    public static Session getSession() {
//        如果SessionFactory不为空,则开启Session
        Session session = (factory != null) ? factory.openSession() : null;
        return session;
    }

    //    获取SessionFactory对象的方法
    public static SessionFactory getSessionFactory() {
        return factory;
    }

    //    关闭Session的方法
    public static void closeSession(Session session) {
        if (session != null) {
            if (session.isOpen()) {
                session.close();
            }
        }
    }
}
