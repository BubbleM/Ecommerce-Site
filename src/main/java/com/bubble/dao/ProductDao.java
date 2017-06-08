package com.bubble.dao;

import com.bubble.entity.ProductEntity;
import com.bubble.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by bubble on 17-6-8.
 */
public class ProductDao {
    /*
    * 添加商品
    * */
    public void insertProduct(ProductEntity product){
        Session session = null; //声明Session对象
        try {
            session = HibernateUtil.getSession(); //获取Session
            session.beginTransaction();//开启事务
            session.save(product);  //保存用户信息
            session.getTransaction().commit(); //提交事务
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    /*
   * 根据输入的商品名查询是否已经存在
   */
    public boolean findProductByName(String name){
        Session session = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();
            String hql = "from ProductEntity product where product.name='"+name+"'";
            Query query = session.createQuery(hql);
            List<ProductEntity> list = query.list();
            for(ProductEntity product:list){
                if(list != null){
                    System.out.println("该商品已经存在");
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

    /*
    * 根据商品类型查询商品
    * */
    public boolean findProductByType(String type){
        Session session = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();
            String hql = "from ProductEntity product where product.type='"+type+"'";
            Query query = session.createQuery(hql);
            List<ProductEntity> list = query.list();
            for(ProductEntity product:list){
                System.out.println(product.getId());
                System.out.println(product.getName());
                System.out.println(product.getType());
                System.out.println(product.getDescription());
            }
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction();
        }
        return false;
    }

    public static void main(String[] args){
        ProductDao dao = new ProductDao();
//        ProductEntity product = new ProductEntity();
//        product.setName("Tphone");
//        product.setType("elep");
//        product.setDescription("this is apple phone");
//        dao.insertProduct(product);
//        System.out.println(dao.findProductByName("Tphone"));
//        dao.findProductByType("clothing");
    }
}
