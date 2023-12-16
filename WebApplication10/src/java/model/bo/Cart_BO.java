/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bo;

import java.util.ArrayList;
import model.bean.Cart;
import model.bean.ItemCart;
import model.dao.Cart_DAO;
import model.dao.Product_DAO;

public class Cart_BO {

    private Cart_DAO dao;

    public Cart_BO() {
        this.dao = new Cart_DAO();
    }

    public int addItemCart_BO(Cart cart) {
        return dao.addItemCart(cart);
    }

    public ArrayList<ItemCart> getListCart(int id_user) {
        return dao.getListCart(id_user);
    }

    public int updateCart(Cart cart) {
        return dao.updateCart(cart);
    }

    public int deleteItemCart(int id_user, String id_product, String name_size) {
        return dao.deleteItemCart(id_user, id_product, name_size);
    }

    public ArrayList<String> getListID() {
        return dao.getListID();
    }

    public int getQuantity_byID(int id_user, String id_product) {
        return dao.getQuantity_byID(id_user, id_product);
    }
    
    public boolean checkAvailableItemInCart(int id_user, String id_product, String name_size){
        boolean check = false;
        ArrayList<Cart> list = dao.getAllCart(id_user);
        for(Cart cart : list){
            if(cart.getId_product().equals(id_product) && cart.getName_size().equals(name_size)){
                check = true;
                break;
               
            }
        }
         return check;
    }

}
