package com.bubble.dao;

import com.alibaba.fastjson.JSON;
import com.bubble.entity.InventoryEntity;
import com.bubble.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by bubble on 17-6-12.
 */
public class InventoryDao {

    /*
    *插入新库存信息
    */
    public int insertInventory(InventoryEntity inventory) {
        Session session = null; //声明Session对象
        int result;
        try {
            session = HibernateUtil.getSession(); //获取Session
            session.beginTransaction();//开启事务
            session.save(inventory);  //保存用户信息
            session.getTransaction().commit(); //提交事务
            result = 1;
        } catch (Exception e) {
            result = 0;
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return result;
    }

    /*
  * 查找数据库中所有Inventory信息
  * */
    public List<InventoryEntity> getInventory() {
        Session session = null;
        List<InventoryEntity> list = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            String hql = "from InventoryEntity inventory";
            Query query = session.createQuery(hql);
            list = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction();
        }
        return list;
    }

    /*
 * 管理员根据id删除相应库存信息
 */
    public int deleteInventory(Integer id) {
        int result;
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            InventoryEntity inventory = session.load(InventoryEntity.class, new Integer(id));
            session.delete(inventory);
            session.getTransaction().commit();
            System.out.println("删除成功" + id);
            result = 1;
        } catch (Exception e) {
            result = 0;
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return result;
    }

    /*
  * 保存或更改Inventory对象
  * */
    public int saveOrUpdateInventory(InventoryEntity inventory) {
        Session session = null;
        int result;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.saveOrUpdate(inventory);
            session.getTransaction().commit();
            result = 1;
        } catch (Exception e) {
            result = 0;
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return result;
    }

    /*
    * 根据id查找对应的商品并返回对应实体对象
    * */
    public InventoryEntity findInventoryById(Integer id) {
        Session session = null;
        InventoryEntity inventory = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            inventory = (InventoryEntity) session.get(InventoryEntity.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return inventory;
    }

    /*
    * 修改库存信息
    * */
/*    public void updateInventory(String name,String password){
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
    }*/
    public static void main(String[] args) {
//        InventoryEntity invent = new InventoryEntity();
//        ProductEntity product = new ProductEntity();
//        product.setId(4);
//        invent.setProductByProductId(product);
//        invent.setSum(12);
//        invent.setUser("BubbleM");
        InventoryDao dao = new InventoryDao();
        List<InventoryEntity> list = dao.getInventory();
        System.out.println(dao.getInventory());
        for (InventoryEntity inventory : list) {
            if (list != null) {
//                System.out.println("___________________"+inventory.getProductByProductId().getName());
                String json = JSON.toJSONString(inventory);
                System.out.println(json);
            }
        }
//        dao.insertInventory(invent);
    }
}
