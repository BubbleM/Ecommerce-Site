package com.bubble.controller;

import com.alibaba.fastjson.JSON;
import com.bubble.dao.ProductDao;
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
 * Created by bubble on 17-6-8.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    ProductDao dao = new ProductDao();

    @RequestMapping("/getProducts.do")
    @ResponseBody
    public void getProducts(HttpServletResponse response){
        List<ProductEntity> products = dao.getProducts();
        System.out.println("get product info!");
        response.setCharacterEncoding("utf-8");
        Writer writer;
        try{
           writer = response.getWriter();
           String jsonString = JSON.toJSONString(products);
//           System.out.println(jsonString);
           writer.append(JSON.toJSONString(products));
           writer.flush();
           writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/add.do")
    public void productAdd(String name,String type,String description,HttpServletResponse response){
        ProductEntity product = new ProductEntity();
        product.setName(name);
        product.setType(type);
        product.setDescription(description);
        int r = dao.insertProduct(product);
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
    public void productDelete(Integer id,HttpServletResponse response){
        int r = dao.deleteProduct(id);
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

    @RequestMapping("/updateProduct.do")
    public void updateUser(Integer id,String name,String type,String description,HttpServletResponse response){
        System.out.println("*****************update Product!************");
        System.out.println("id="+id+" name="+name+" type="+type+" description"+description);
        ProductEntity product = dao.findProductById(id);
        product.setName(name);
        product.setType(type);
        product.setDescription(description);
        int r =  dao.saveOrUpdateProduct(product);
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
