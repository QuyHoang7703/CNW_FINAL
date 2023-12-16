/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bo;

import java.util.ArrayList;
import model.bean.ItemCart;
import model.bean.Product;
import model.dao.ItemCart_DAO;
import org.apache.tomcat.util.digester.ArrayStack;

/**
 *
 * @author QUYHOANG
 */
public class ItemCart_BO {

    private ItemCart_DAO dao;

    public ItemCart_BO() {
        this.dao = new ItemCart_DAO();
    }
    
//    public ArrayList<ItemCart> getListItemCart(){
//        ArrayList<ItemCart> list = new ArrayStack<ItemCart>();
//        ArrayList<String> listId = dao.getListId();
//        ArrayList<String> listIdCart = dao.getListIdCart();
//        
//        Product_BO bo = new Product_BO();
//        for(String id: listIdCart){
//            if(listId.contains(id)){
//                Product product = bo.getProductById(id);
//                
//            }
//        }
//    }

}
