package com.bubble.controller;

import com.alibaba.fastjson.JSON;
import com.bubble.dao.InventoryDao;
import com.bubble.dao.ProductDao;
import com.bubble.entity.InventoryEntity;
import com.bubble.entity.ProductEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bubble on 17-6-14.
 */
@Controller
@RequestMapping("/inventory")
public class InventoryController {

    InventoryDao dao = new InventoryDao();

    @RequestMapping("/getInventory.do")
    @ResponseBody
    public void getInventory(HttpServletResponse response){
        List<InventoryEntity> inventory = dao.getInventory();
        System.out.println("get product info!");
        response.setCharacterEncoding("utf-8");
        Writer writer;
        try{
            writer = response.getWriter();
            String jsonString = JSON.toJSONString(inventory);
//           System.out.println(jsonString);
            writer.append(JSON.toJSONString(inventory));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/add.do")
    public void inventoryAdd(Integer productId,String user,Integer sum,HttpServletResponse response){
        InventoryEntity inventory = new InventoryEntity();
        ProductDao pd = new ProductDao();
        ProductEntity product = pd.findProductById(productId);
        inventory.setProductByProductId(product);
        inventory.setUser(user);
        inventory.setSum(sum);
        int r = dao.insertInventory(inventory);
        response.setCharacterEncoding("utf-8");
        Writer writer = null;
        try {
            writer = response.getWriter();
            if(r>0){
                writer.append("OK");
            }else{
                writer.append("NO");
            }
            writer.flush();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            if(writer != null){
                try {
                    writer.close();
                    writer = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping("/delete.do")
    public void inventoryDelete(Integer id,HttpServletResponse response){
        int r = dao.deleteInventory(id);
        response.setCharacterEncoding("utf-8");
        Writer writer = null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            writer = response.getWriter();
            if(r>0){
                System.out.println("delete success!");
                map.put("success", true);
            }else{
                map.put("success", false);
                System.out.println("delete fail!");
            }
            writer.append(JSON.toJSONString(map));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(writer != null){
                try {
                    writer.close();
                    writer = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping("/updateInventory.do")
    public void updateUser(Integer id,Integer productId,String user,Integer sum,HttpServletResponse response){
        System.out.println("*****************update Inventory!************");
        System.out.println("id="+id+" productId="+productId+" user="+user+" sum"+sum);
        InventoryEntity inventory = dao.findInventoryById(id);
        inventory.setUser(user);
        inventory.setSum(sum);
        /*不设置更改相关联的商品id*/
        int r = dao.saveOrUpdateInventory(inventory);
        response.setCharacterEncoding("utf-8");
        Writer writer = null;
        try {
            writer = response.getWriter();
            if(r>0){
                writer.append("OK");
            }else{
                writer.append("NO");
            }
            writer.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            if(writer != null){
                try {
                    writer.close();
                    writer = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
