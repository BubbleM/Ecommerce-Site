package com.bubble.dao;

import com.bubble.entity.InventoryEntity;
import com.bubble.entity.ProductEntity;
import com.bubble.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Created by bubble on 17-6-12.
 */
public class InventoryDao {

    /*
    *插入新库存信息
    */
    public void insertInventory(InventoryEntity inventory){
        Session session = null; //声明Session对象
        try {
            session = HibernateUtil.getSession(); //获取Session
            session.beginTransaction();//开启事务
            session.save(inventory);  //保存用户信息
            session.getTransaction().commit(); //提交事务
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    /*
    * 修改库存信息
    * */
//    public void updateInventory(String name,String password){
//        Session session = null;
//        String pwd = null;
//        try{
//            session = HibernateUtil.getSession();
//            session.beginTransaction();
//            String hql = "update UsersEntity set password='"+password+"' where name='"+name+"' ";
//            int result = session.createQuery(hql).executeUpdate();
//            if(result == 1){
//                System.out.println("update success");
//            }else {
//                System.out.println("sorry please try agagin");
//            }
//            session.getTransaction().commit();
//        }catch (Exception e){
//            e.printStackTrace();
//            session.getTransaction();
//        }
//    }
//    public static void main(String[] args){
//        InventoryEntity invent = new InventoryEntity();
//        ProductEntity product = new ProductEntity();
//        product.setId(4);
//        invent.setProductByProductId(product);
//        invent.setSum(12);
//        invent.setUser("BubbleM");
//        InventoryDao dao = new InventoryDao();
//        dao.insertInventory(invent);
//    }
}
