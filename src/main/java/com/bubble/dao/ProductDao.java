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
    public int insertProduct(ProductEntity product){
        Session session = null; //声明Session对象
        int result;
        try {
            session = HibernateUtil.getSession(); //获取Session
            session.beginTransaction();//开启事务
            session.save(product);  //保存用户信息
            session.getTransaction().commit(); //提交事务
            result = 1;
        }catch (Exception e){
            result = 0;
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return result;
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

    /*
   * 查找数据库中所有product信息
   * */
    public List<ProductEntity> getProducts(){
        Session session = null;
        List<ProductEntity> list = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();
            String hql = "from ProductEntity u";
            Query query = session.createQuery(hql);
            list = query.list();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction();
        }
        return list;
    }

    /*
   * 管理员根据id删除相应商品
   */
    public int deleteProduct(Integer id){
        int result;
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            ProductEntity product = session.load(ProductEntity.class,new Integer(id));
            session.delete(product);
            session.getTransaction().commit();
            System.out.println("删除成功"+id);
            result = 1;
        }catch (Exception e){
            result = 0;
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return result;
    }

    /*
   * 保存或更改product对象
   * */
    public int saveOrUpdateProduct(ProductEntity product){
        Session session = null;
        int result;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            result = 1;
        }catch (Exception e){
            result = 0;
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return result;
    }
    public ProductEntity findProductById(Integer id){
        Session session = null;
        ProductEntity product = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();
            product = (ProductEntity) session.get(ProductEntity.class,id);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return product;
    }


    public static void main(String[] args){
        ProductDao dao = new ProductDao();
//        System.out.println(dao.getProducts());
//        ProductEntity product = new ProductEntity();
//        product.setName("LenovoR720");
//        product.setType("electronic_product");
//        product.setDescription("computer");
//        dao.insertProduct(product);
//        System.out.println(dao.findProductByName("Tphone"));
//        dao.findProductByType("clothing");
    }
}
